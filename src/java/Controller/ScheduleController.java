/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ScheduleDAO;
import Service.AuthorizationService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import model.Slot;
import model.User;

/**
 *
 * @author TGDD
 */
public class ScheduleController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            if (!AuthorizationService.gI().Authorization(request, response)) {
                return;
            }
        } catch (Exception e) {
        }
        try {
            User u = (User) request.getSession().getAttribute("User");
            if(request.getParameter("type") != null && request.getParameter("type").equalsIgnoreCase("confirm")) {
                String sid = request.getParameter("id");
                int id = Integer.parseInt(sid);
                boolean check = ScheduleDAO.confirmSlot(id, u.getId(), u.getRole());
                if(!check) {
                    request.setAttribute("alert", "Something went wrong when confirm slot!");
                }
            }
            java.util.Date today = new java.util.Date();
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            int year = c.get(Calendar.YEAR);
            int week = ScheduleDAO.weekOfYear(today);
            ArrayList<Slot> arr = null;
            if(u.getRole().equalsIgnoreCase("mentor")) {
                arr = ScheduleDAO.getSlots(year, week, u.getId());
            } else {
                arr = ScheduleDAO.menteeGetSlots(year, week, u.getId());
            }
            Collections.sort(arr, new Comparator<Slot>() {
                @Override
                public int compare(Slot o1, Slot o2) {
                    return o1.getSlotTime().before(o2.getSlotTime()) ? -1 : 1;
                }
            });
            request.setAttribute("Slots", arr);
            request.setAttribute("fistDate", ScheduleDAO.FirstDateOfWeek(c.get(Calendar.YEAR), 1));
            request.setAttribute("year", year);
            request.setAttribute("weekOfYear", week);
            request.setAttribute("numberOfWeek", ScheduleDAO.numberOfWeek(c.get(Calendar.YEAR)));
            request.setAttribute("firstOfWeek", ScheduleDAO.FirstDateOfWeek(c.get(Calendar.YEAR), ScheduleDAO.weekOfYear(today)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("schedule.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (!AuthorizationService.gI().Authorization(request, response)) {
                return;
            }
        } catch (Exception e) {
        }
        if (request.getParameter("type") == null) {
            User u = (User) request.getSession().getAttribute("User");
            String sYear = request.getParameter("year");
            String sWeek = request.getParameter("week");
            try {
                int year = Integer.parseInt(sYear);
                int week = Integer.parseInt(sWeek);
            ArrayList<Slot> arr = null;
            if(u.getRole().equalsIgnoreCase("mentor")) {
                arr = ScheduleDAO.getSlots(year, week, u.getId());
            } else {
                arr = ScheduleDAO.menteeGetSlots(year, week, u.getId());
            }
            Collections.sort(arr, new Comparator<Slot>() {
                @Override
                public int compare(Slot o1, Slot o2) {
                    return o1.getSlotTime().before(o2.getSlotTime()) ? -1 : 1;
                }
            });
            request.setAttribute("Slots", arr);
                request.setAttribute("fistDate", ScheduleDAO.FirstDateOfWeek(year, 1));
                request.setAttribute("year", year);
                request.setAttribute("weekOfYear", week);
                request.setAttribute("numberOfWeek", ScheduleDAO.numberOfWeek(year));
                request.setAttribute("firstOfWeek", ScheduleDAO.FirstDateOfWeek(year, week));
            } catch (Exception e) {
            }
        } else {
            User u = (User) request.getSession().getAttribute("User");
            String link = request.getParameter("link");
            String shour = request.getParameter("hour");
            String start = request.getParameter("start");
            float hour = Float.parseFloat(shour);
            if (request.getParameter("type").equalsIgnoreCase("byDay")) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
                try {
                    java.util.Date startDate = format.parse(start);
                    Calendar c = Calendar.getInstance();
                    c.setTime(startDate);
                    ScheduleDAO.addSlot(link, hour, startDate, ScheduleDAO.weekOfYear(startDate), c.get(Calendar.YEAR), u.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (request.getParameter("type").equalsIgnoreCase("byWeek")) {
                String[] weekday = request.getParameterValues("weekday");
                ArrayList<Integer> weekdays = new ArrayList();
                for (int i = 0; i < weekday.length; i++) {
                    switch (weekday[i].toLowerCase()) {
                        case "mon": {
                            weekdays.add(2);
                            break;
                        }
                        case "tue": {
                            weekdays.add(3);
                            break;
                        }
                        case "wen": {
                            weekdays.add(4);
                            break;
                        }
                        case "thu": {
                            weekdays.add(5);
                            break;
                        }
                        case "fri": {
                            weekdays.add(6);
                            break;
                        }
                        case "sat": {
                            weekdays.add(7);
                            break;
                        }
                        case "sun": {
                            weekdays.add(1);
                            break;
                        }
                    }
                }
                String sweek = request.getParameter("weekTime");
                int week = Integer.parseInt(sweek);
                String[] hours = start.split(":");
                java.util.Date today = new java.util.Date();
                for (int i = 0; i < weekdays.size(); i++) {
                    Calendar c = Calendar.getInstance();
                    c.setTime(today);
                    try {
                        c = ScheduleDAO.FirstDateOfWeek(c.get(Calendar.YEAR), ScheduleDAO.weekOfYear(today));
                        c.set(Calendar.HOUR, Integer.parseInt(hours[0]));
                        c.set(Calendar.MINUTE, Integer.parseInt(hours[1]));
                        c.set(Calendar.SECOND, 0);
                        while (c.get(Calendar.DAY_OF_WEEK) != weekdays.get(i)) {
                            c.add(Calendar.DATE, 1);
                        }
                        int woy = ScheduleDAO.weekOfYear(c.getTime());
                        for (int j = 0; j < week; j++) {
                            ScheduleDAO.addSlot(link, hour, c.getTime(), woy, c.get(Calendar.YEAR), u.getId());
                            c.add(Calendar.DATE, 7);
                            woy++;
                        }
                    } catch (Exception e) {
                    }
                }
            }
            try {
                java.util.Date today = new java.util.Date();
                Calendar c = Calendar.getInstance();
                c.setTime(today);
                int year = c.get(Calendar.YEAR);
                int week = ScheduleDAO.weekOfYear(today);
            ArrayList<Slot> arr = null;
            if(u.getRole().equalsIgnoreCase("mentor")) {
                arr = ScheduleDAO.getSlots(year, week, u.getId());
            } else {
                arr = ScheduleDAO.menteeGetSlots(year, week, u.getId());
            }
            Collections.sort(arr, new Comparator<Slot>() {
                @Override
                public int compare(Slot o1, Slot o2) {
                    return o1.getSlotTime().before(o2.getSlotTime()) ? -1 : 1;
                }
            });
            request.setAttribute("Slots", arr);
                request.setAttribute("fistDate", ScheduleDAO.FirstDateOfWeek(c.get(Calendar.YEAR), 1));
                request.setAttribute("year", year);
                request.setAttribute("weekOfYear", week);
                request.setAttribute("numberOfWeek", ScheduleDAO.numberOfWeek(c.get(Calendar.YEAR)));
                request.setAttribute("firstOfWeek", ScheduleDAO.FirstDateOfWeek(c.get(Calendar.YEAR), ScheduleDAO.weekOfYear(today)));
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("schedule.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

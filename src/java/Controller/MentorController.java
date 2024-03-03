/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Service.AuthorizationService;
import DAO.CvDAO;
import DAO.FollowDAO;
import DAO.MentorDAO;
import DAO.RateDAO;
import DAO.RequestDAO;
import DAO.ScheduleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import model.CV;
import model.Mentor;
import model.User;

/**
 *
 * @author TGDD
 */
@WebServlet(name = "MentorController", urlPatterns = {"/mentor"})
public class MentorController extends HttpServlet {

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
        try {
            if (!AuthorizationService.gI().Authorization(request, response)) {
                return;
            }
        } catch (Exception e) {
        }
        String sid = request.getParameter("id");
        if (sid == null) {
            response.sendRedirect("index");
            return;
        }
        try {
            int id = Integer.parseInt(sid);
            Mentor m = MentorDAO.getMentor(id);
            String rate = "";
            int c = 0;
            for (int i = 1; i < m.getRate(); i++) {
                rate += "<i class=\"fas fa-star\"></i>\n";
                c++;
            }
            if (m.getRate() - Math.floor(m.getRate()) >= 0.5) {
                rate += "<i class=\"fas fa-star-half-alt\"></i>";
                c++;
            }
            while (c < 5) {
                rate += "<i class=\"far fa-star\"></i>";
                c++;
            }
            request.setAttribute("Rate", rate);
            request.setAttribute("CurrMentor", m);
            CV cv = CvDAO.getCV(m.getCvID());
            request.setAttribute("CurrCV", cv);
            java.util.Date today = new java.util.Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            int year = calendar.get(Calendar.YEAR);
            int week = ScheduleDAO.weekOfYear(today);
            request.setAttribute("year", year);
            request.setAttribute("week", week);
            Calendar firstDay = ScheduleDAO.FirstDateOfWeek(year, week);
            request.setAttribute("firstOfWeek", firstDay);
            request.setAttribute("Rates", RateDAO.getRates(id));
            request.setAttribute("FreeSlot", ScheduleDAO.getFreeSlots(new java.util.Date(), id));
        } catch (Exception e) {
            response.sendRedirect("index");
            return;
        }
        request.getRequestDispatcher("mentor.jsp").forward(request, response);
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
        String sid = request.getParameter("id");
        if (sid == null) {
            response.sendRedirect("index");
            return;
        }
        User u = (User) request.getSession().getAttribute("User");
        if (u == null || !u.getRole().equalsIgnoreCase("mentee")) {
            request.setAttribute("status", "Bạn chưa đăng nhập hoặc không phải mentee!");
            request.getRequestDispatcher("mentor.jsp").forward(request, response);
            return;
        }
        String[] skills = request.getParameterValues("skill");
        String[] slots = request.getParameterValues("slot");
        String deadline = request.getParameter("deadline");
        String subject = request.getParameter("subject");
        String reason = request.getParameter("reason");
        try {
            int id = Integer.parseInt(sid);
            Mentor m = MentorDAO.getMentor(id);
            String rate = "";
            int c = 0;
            for (int i = 1; i < m.getRate(); i++) {
                rate += "<i class=\"fas fa-star\"></i>\n";
                c++;
            }
            if (m.getRate() - Math.floor(m.getRate()) >= 0.5) {
                rate += "<i class=\"fas fa-star-half-alt\"></i>";
                c++;
            }
            while (c < 5) {
                rate += "<i class=\"far fa-star\"></i>";
                c++;
            }
            request.setAttribute("Rate", rate);
            request.setAttribute("CurrMentor", m);
            CV cv = CvDAO.getCV(m.getCvID());
            request.setAttribute("CurrCV", cv);
            if (request.getParameter("type") != null) {
                if(request.getParameter("type").equalsIgnoreCase("follow")) {
                    String title = request.getParameter("title");
                    String FollowReason = request.getParameter("reason");
                    FollowDAO.sendRequest(id, title, FollowReason, u.getId());
                    request.setAttribute("status", "Yêu cầu follow thành công!");
                } else if(request.getParameter("type").equalsIgnoreCase("unfollow")) {
                    FollowDAO.unFollow(id, u.getId());
                } else if(request.getParameter("type").equalsIgnoreCase("cancel follow")) {
                    FollowDAO.cancelFollowRequest(id, u.getId());
                }
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
                Timestamp tm = Timestamp.from(formatter.parse(deadline).toInstant());
                boolean check = RequestDAO.createRequest(skills, tm, subject, reason, u.getId(), id, slots);
                if (check) {
                    request.setAttribute("status", "Gửi request thành công!");
                }
            }
            java.util.Date today = new java.util.Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            int year = calendar.get(Calendar.YEAR);
            int week = ScheduleDAO.weekOfYear(today);
            request.setAttribute("year", year);
            request.setAttribute("week", week);
            request.setAttribute("Rates", RateDAO.getRates(id));
            request.setAttribute("firstOfWeek", ScheduleDAO.FirstDateOfWeek(year, week));
            request.setAttribute("FreeSlot", ScheduleDAO.getFreeSlots(new java.util.Date(), id));
        } catch (Exception e) {
            request.setAttribute("status", "Gửi request thất bại!");
        }
        request.getRequestDispatcher("mentor.jsp").forward(request, response);
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

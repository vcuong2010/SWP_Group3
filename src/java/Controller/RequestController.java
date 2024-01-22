/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.CvDAO;
import DAO.MentorDAO;
import DAO.RequestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.CV;
import model.Mentor;
import model.Request;
import model.User;

/**
 *
 * @author TGDD
 */
@WebServlet(name="RequestController", urlPatterns={"/request"})
public class RequestController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if (request.getSession().getAttribute("User") == null) {
            response.sendRedirect("index");
            return;
        }
        User u = (User)request.getSession().getAttribute("User");
        String type = request.getParameter("type");
        if(type != null && type.equalsIgnoreCase("delete")) {
            if(request.getParameterValues("id") != null && request.getParameterValues("id").length < 2) {
            String sid = request.getParameter("id");
            if(sid != null && sid.equalsIgnoreCase("all")) {
                try {
                    RequestDAO.deleteAll(u.getId());
                } catch(Exception e) {
                    e.printStackTrace();
                }
            } else if(sid != null) {
                int id = Integer.parseInt(sid);
                try {
                RequestDAO.deleteRequest(id);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            } else if(request.getParameterValues("id") != null) {
                String[] sid = request.getParameterValues("id");
                for (int i = 0; i < sid.length; i++) {
                    int id = Integer.parseInt(sid[i]);
                    try {
                        RequestDAO.deleteRequest(id);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
        ArrayList<Request> arr = RequestDAO.getRequests(u.getId());
        request.setAttribute("requests", arr);
        } catch(Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("request.jsp").forward(request, response);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String sid = request.getParameter("id");
        if(sid == null) {
            response.sendRedirect("request");
            return;
        }
        String type = request.getParameter("type");
        if(type != null && type.equalsIgnoreCase("update")) {
            User u = (User)request.getSession().getAttribute("User");
        if(u == null || !u.getRole().equalsIgnoreCase("mentee")) {
            response.sendRedirect("index");
            return;
        }
        String[] skills = request.getParameterValues("skill");
        String deadline = request.getParameter("deadline");
        String subject = request.getParameter("subject");
        String reason = request.getParameter("reason");
        String status = request.getParameter("status");
        try {
            int id = Integer.parseInt(sid);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            Timestamp tm = Timestamp.from(formatter.parse(deadline).toInstant());
            boolean check = RequestDAO.updateRequest(skills, tm, subject, reason, u.getId(), status, id);
            if(check) {
                request.setAttribute("status", "Success");
            }
        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("request");
            return;
        } 
        }
        response.sendRedirect("request");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

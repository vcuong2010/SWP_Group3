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
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import model.CV;
import model.Mentor;
import model.User;

/**
 *
 * @author TGDD
 */
@WebServlet(name="MentorController", urlPatterns={"/mentor"})
public class MentorController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String sid = request.getParameter("id");
        if(sid == null) {
            response.sendRedirect("index");
            return;
        }
        try {
            int id = Integer.parseInt(sid);
            Mentor m = MentorDAO.getMentor(id);
            request.setAttribute("CurrMentor", m);
            CV cv = CvDAO.getCV(m.getCvID());
            request.setAttribute("CurrCV", cv);
        } catch(Exception e) {
            response.sendRedirect("index");
            return;
        } 
        request.getRequestDispatcher("mentor.jsp").forward(request, response);
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
            response.sendRedirect("index");
            return;
        }
        User u = (User)request.getSession().getAttribute("User");
        if(u == null || !u.getRole().equalsIgnoreCase("mentee")) {
            response.sendRedirect("index");
            return;
        }
        String[] skills = request.getParameterValues("skill");
        String deadline = request.getParameter("deadline");
        String subject = request.getParameter("subject");
        String reason = request.getParameter("reason");
        try {
            int id = Integer.parseInt(sid);
            Mentor m = MentorDAO.getMentor(id);
            request.setAttribute("CurrMentor", m);
            CV cv = CvDAO.getCV(m.getCvID());
            request.setAttribute("CurrCV", cv);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
            Timestamp tm = Timestamp.from(formatter.parse(deadline).toInstant());
            boolean check = RequestDAO.createRequest(skills, tm, subject, reason, u.getId(), id);
            if(check) {
                request.setAttribute("status", "Success");
            }
        } catch(Exception e) {
            response.sendRedirect("index");
            return;
        } 
        request.getRequestDispatcher("mentor.jsp").forward(request, response);
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

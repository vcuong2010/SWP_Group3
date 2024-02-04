/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Service.AuthorizationService;
import DAO.MentorDAO;
import DAO.SkillDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Mentee;
import model.Mentor;
import model.Skill;
import model.User;

/**
 *
 * @author TGDD
 */
@WebServlet(name = "IndexController", urlPatterns = {"/index"})
public class IndexController extends HttpServlet {

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
            if(!AuthorizationService.gI().Authorization(request, response)) {
                return;
            }
            ArrayList<Skill> a = SkillDAO.getAll(true);
            for (int i = 0; i < a.size(); i++) {
                for (int j = i; j < a.size(); j++) {
                    if(a.get(i).getName().compareToIgnoreCase(a.get(j).getName()) > 0) {
                        Skill temp = a.get(i);
                        a.set(i, a.get(j));
                        a.set(j, temp);
                    }
                }
            }
            request.setAttribute("skills", a); //All skill
            //General Statistic
            request.setAttribute("skill", a.size());
            request.setAttribute("mentee", UserDAO.menteeCount());
            request.setAttribute("mentor", UserDAO.mentorCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
        User u = (User) request.getSession().getAttribute("User");
        if (u != null) {
            if (UserDAO.isMentee(u)) {
                Mentee r = (Mentee) UserDAO.getRole(u.getId(), u.getRole());
                request.getSession().setAttribute("Mentee", r);
            } else {
                Mentor r = (Mentor) UserDAO.getRole(u.getId(), u.getRole());
                request.getSession().setAttribute("Mentor", r);
            }
        }
        try {
        ArrayList<Mentor> arr = MentorDAO.getAll();
        request.setAttribute("Mentors", arr);
        } catch(Exception e) {}
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        processRequest(request, response);
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

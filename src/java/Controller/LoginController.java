/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Service.AuthorizationService;
import DAO.SkillDAO;
import DAO.UserDAO;
import model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author TGDD
 */
@WebServlet(name="Login", urlPatterns={"/login"})
public class LoginController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
        } catch(Exception e) {}
        Cookie[] cs = request.getCookies();
        for (int i = 0; i < cs.length; i++) {
            if(cs[i].getName().equals("user")) {
                String[] atr = cs[i].getValue().split("_");
                request.setAttribute("username", atr[0].replace("User|", ""));
                request.setAttribute("password", atr[1].replace("Pass|", ""));
                break;
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        try {
            if (!AuthorizationService.gI().Authorization(request, response)) {
                return;
            }
        } catch(Exception e) {}
            Cookie[] cs = request.getCookies();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String remember = request.getParameter("remember");
            try {
                User u = UserDAO.getUser(username, password);
                if(u != null) {
                    request.getSession().setAttribute("User", u);
                    if(remember != null) {
                        Cookie c = new Cookie("user", "User|"+username+"_Pass|"+password);
                        c.setMaxAge(60*24*7);
                        response.addCookie(c);
                    } else {
                        for (int i = 0; i < cs.length; i++) {
                            if(cs[i].getName().equals("user")) {
                                cs[i].setMaxAge(0);
                                response.addCookie(cs[i]);
                                break;
                            }
                        }
                    }
                    if(u.getRole().equalsIgnoreCase("admin") || u.getRole().equalsIgnoreCase("manager")) {
                        response.sendRedirect("admin/request");
                    } else {
                        response.sendRedirect("index");
                    }
                    return;
                } else {
                    request.getSession().setAttribute("error", "Don't have account");
                }
            } catch(Exception e) {
                    request.getSession().setAttribute("error", e.getMessage());
            }
        for (int i = 0; i < cs.length; i++) {
            if(cs[i].getName().equals("user")) {
                String[] atr = cs[i].getValue().split("_");
                request.setAttribute("username", atr[0].replace("User|", ""));
                request.setAttribute("password", atr[1].replace("Pass|", ""));
                break;
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
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

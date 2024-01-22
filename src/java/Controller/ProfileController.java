/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Mentee;
import model.Mentor;
import model.User;

/**
 *
 * @author TGDD
 */
@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {

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
        if (request.getSession().getAttribute("User") == null) {
            response.sendRedirect("index");
            return;
        }
        User u = (User) request.getSession().getAttribute("User");
        if (!UserDAO.isMentor(u)) {
            Mentee r = (Mentee) UserDAO.getRole(u.getId(), u.getRole());
            request.getSession().setAttribute("Mentee", r);
        } else {
            Mentor r = (Mentor) UserDAO.getRole(u.getId(), u.getRole());
            request.getSession().setAttribute("Mentor", r);
        }
        //Update avatar
        if (request.getParameter("avt") != null) {
            try {
                if (request.getSession().getAttribute("Mentee") != null) {
                    UserDAO.updateAvatar(u.getId(), request.getParameter("avt"), false, false);
                } else if (request.getSession().getAttribute("Mentor") != null) {
                    UserDAO.updateAvatar(u.getId(), request.getParameter("avt"), true, false);
                } else {
                    if (UserDAO.isMentor(u)) {
                        UserDAO.updateAvatar(u.getId(), request.getParameter("avt"), true, true);
                    } else {
                        UserDAO.updateAvatar(u.getId(), request.getParameter("avt"), false, true);
                    }
                }
            } catch (Exception e) {
            }
            response.sendRedirect("profile");
            return;
        }
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
        User u = (User) request.getSession().getAttribute("User");
        //Update fullname
        String fullname = request.getParameter("fullname");
        if (fullname != null && !fullname.isEmpty()) {
            try {
                if (request.getSession().getAttribute("Mentor") != null) {
                    UserDAO.updateFullname(u.getId(), fullname, true, false);
                } else if (request.getSession().getAttribute("Mentee") != null) {
                    UserDAO.updateFullname(u.getId(), fullname, false, false);
                } else {
                    UserDAO.updateFullname(u.getId(), fullname, UserDAO.isMentor(u), true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Update Phone
        String sdt = request.getParameter("sdt");
        if (!(sdt.isEmpty() || u.getPhone().equals(sdt))) {
            //Phone validate
            boolean isPhone = true;
            for (int i = 0; i < sdt.length(); i++) {
                if (!(sdt.charAt(i) >= '0' && sdt.charAt(i) <= '9')) {
                    isPhone = false;
                    break;
                }
            }
            //End Phone validate
            if (isPhone) {
                try {
                    UserDAO.updatePhone(u.getId(), sdt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //Update Date Of Birth
        String dob = request.getParameter("dob");
        if (!(dob.isEmpty() || u.getDob().equals(dob))) {
            try {
                UserDAO.updateDob(u.getId(), dob);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Update address
        String address = request.getParameter("address");
        if (!(address.isEmpty() || u.getAddress().equals(address))) {

            try {
                UserDAO.updateAddress(u.getId(), address);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Update gender
        String gender = request.getParameter("gender");
        if (!gender.isEmpty()) {
            if (gender.equalsIgnoreCase("male") && u.isGender()) {
                try {
                    UserDAO.updateGender(u.getId(), false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (gender.equalsIgnoreCase("female") && !u.isGender()) {
                try {
                    UserDAO.updateGender(u.getId(), true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            u = UserDAO.getUser(u.getUsername(), u.getPassword());
            request.getSession().setAttribute("User", u);
        } catch(Exception e) {
            
        }
        response.sendRedirect("profile");
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

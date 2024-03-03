/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.AuthorizeDAO;
import DAO.ReportDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import model.User;

/**
 *
 * @author TGDD
 */
public class AuthorizationService {
    
    static AuthorizationService instance;
    
    HashMap<String, String> AuthorizeMap = new HashMap();
    
    public AuthorizationService() {
    }
    
    public static AuthorizationService gI() {
        if(instance == null) {
            instance = new AuthorizationService();
            try {
                AuthorizeDAO.loadAuthorizeMap();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    
    public HashMap<String, String> getMap() {
        return AuthorizeMap;
    }
    
    public void setAuthorite(String path, String roles) {
        try {
            AuthorizeDAO.setAuthorize(path, roles);
        } catch(Exception e) {
            e.printStackTrace();
        }
        getMap().put(path, roles);
    }
    
    public void sendReport(int uid, String content) throws Exception {
        ReportDAO.sendReport(uid, content);
    }
    
    public boolean Authorization(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getServletPath();
        if(AuthorizeMap.containsKey(path)) {
            if(AuthorizeMap.get(path.toLowerCase()).equalsIgnoreCase("guest")) {
                if(request.getMethod().equalsIgnoreCase("POST") && request.getParameter("type") != null && request.getParameter("type").equalsIgnoreCase("report")) {
                    try {
                        String sid = request.getParameter("id");
                        int id = Integer.parseInt(sid);
                        String content = request.getParameter("reason");
                        sendReport(id, content);
                        request.setAttribute("alert", "Send Report Successfully!");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
            User u = (User)request.getSession().getAttribute("User");
            if(u == null) {
                request.getRequestDispatcher("/404.jsp").forward(request, response);
                return false;
            } else if(!AuthorizeMap.get(path.toLowerCase()).contains(u.getRole().toLowerCase()) && !AuthorizeMap.get(path.toLowerCase()).contains("all user")) {
                request.getRequestDispatcher("/404.jsp").forward(request, response);
                return false;
            }
        }
        if(request.getMethod().equalsIgnoreCase("POST") && request.getParameter("type") != null && request.getParameter("type").equalsIgnoreCase("report")) {
                    try {
                        String sid = request.getParameter("id");
                        int id = Integer.parseInt(sid);
                        String content = request.getParameter("reason");
                        sendReport(id, content);
                        request.setAttribute("alert", "Send Report Successfully!");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
        return true;
    }
}

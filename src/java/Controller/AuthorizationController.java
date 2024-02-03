/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import model.User;

/**
 *
 * @author TGDD
 */
public class AuthorizationController {
    
    static AuthorizationController instance;
    
    HashMap<String, String> AuthorizeMap = new HashMap();
    
    public AuthorizationController() {
        AuthorizeMap.put("/admin/request", "admin, manager");
        AuthorizeMap.put("/admin/skill", "admin");
        AuthorizeMap.put("/admin/mentor", "admin");
        AuthorizeMap.put("/email", "all");
        AuthorizeMap.put("/profile", "all");
        AuthorizeMap.put("/setting", "all");
        AuthorizeMap.put("/request", "mentee");
        AuthorizeMap.put("/cv", "mentor");
    }
    
    public static AuthorizationController gI() {
        if(instance == null) {
            instance = new AuthorizationController();
        }
        return instance;
    }
    
    public boolean Authorization(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getServletPath();
        if(AuthorizeMap.containsKey(path)) {
            User u = (User)request.getSession().getAttribute("User");
            if(u == null) {
                request.getRequestDispatcher("/404.jsp").forward(request, response);
                return false;
            } else if(!AuthorizeMap.get(path).contains(u.getRole().toLowerCase()) && !AuthorizeMap.get(path).contains("all")) {
                request.getRequestDispatcher("/404.jsp").forward(request, response);
                return false;
            }
        }
        return true;
    }
}

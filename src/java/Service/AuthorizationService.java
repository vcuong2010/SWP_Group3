/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

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
        AuthorizeMap.put("/admin/authorization", "admin");
        AuthorizeMap.put("/admin/request", "admin, manager");
        AuthorizeMap.put("/admin/skill", "admin");
        AuthorizeMap.put("/admin/mentor", "admin");
        AuthorizeMap.put("/email", "all user");
        AuthorizeMap.put("/forgot", "guest");
        AuthorizeMap.put("/index", "guest");
        AuthorizeMap.put("/login", "guest");
        AuthorizeMap.put("/logout", "guest");
        AuthorizeMap.put("/register", "guest");
        AuthorizeMap.put("/profile", "all user");
        AuthorizeMap.put("/setting", "all user");
        AuthorizeMap.put("/request", "mentee");
        AuthorizeMap.put("/cv", "mentor");
    }
    
    public static AuthorizationService gI() {
        if(instance == null) {
            instance = new AuthorizationService();
        }
        return instance;
    }
    
    public HashMap<String, String> getMap() {
        return AuthorizeMap;
    }
    
    public void setAuthorite(String path, String roles) {
        getMap().put(path, roles);
    }
    
    public boolean Authorization(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getServletPath();
        if(AuthorizeMap.containsKey(path)) {
            if(AuthorizeMap.get(path.toLowerCase()).equalsIgnoreCase("guest")) {
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
        return true;
    }
}

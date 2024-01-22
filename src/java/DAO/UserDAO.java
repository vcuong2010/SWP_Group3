/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DataConnector.DatabaseUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Mentee;
import model.Mentor;
import model.User;

/**
 *
 * @author TGDD
 */
public class UserDAO {
    
    public static boolean verifyAccount(int uid, String email) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("UPDATE [User] SET [isValidate] = 1, [email] = ? WHERE [UserID] = ?");
            ps.setString(1, email);
            ps.setInt(2, uid);
            int k = ps.executeUpdate();
            dbo.commit();
            dbo.close();
            if(k > 0)
                return true;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return false;
    }
    
    public static int mentorCount() throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT Count([UserID]) FROM [User] WHERE [RoleID] = (SELECT [RoleID] FROM [Role] WHERE roleName = 'Mentor')");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int k = rs.getInt(1);
            dbo.close();
            return k;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return 0;
    }
    
    public static boolean changePass(int id, String old, String newPass) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT [password] FROM [User] WHERE [UserID] = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if(old.equals(rs.getString("password"))) {
                ps.close();
                ps = dbo.prepareStatement("UPDATE [User] SET [password] = ? WHERE [UserID] = ?");
                ps.setString(1, newPass);
                ps.setInt(2, id);
                ps.executeUpdate();
                dbo.commit();
                rs.close();
                ps.close();
                dbo.close();
                return true;
            }
            rs.close();
            ps.close();
            dbo.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static int menteeCount() throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT Count([UserID]) FROM [User] WHERE [RoleID] = (SELECT [RoleID] FROM [Role] WHERE roleName = 'Mentee')");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int k = rs.getInt(1);
            dbo.close();
            return k;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return 0;
    }
    
    public static boolean changePassword(String email, String password) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [User] WHERE [email] = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                if(rs.getString("password").equals(password)) {
                    ps.close();
                    rs.close();
                    dbo.close();
                    return false;
                }
            }
            ps.close();
            rs.close();
            ps = dbo.prepareStatement("UPDATE [User] SET [password] = ? WHERE [email] = ?");
            ps.setString(1, password);
            ps.setString(2, email);
            int k = ps.executeUpdate();
            dbo.commit();
            if(k > 0) {
                ps.close();
                dbo.close();
                return true;
            }
            ps.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return false;
    }
    
    public static boolean isMentor(User u) {
        if(u.getRole().equalsIgnoreCase("mentor")) {
            return true;
        }
        return false;
    }
    
    public static boolean isMentee(User u) {
        if(u.getRole().equalsIgnoreCase("mentee")) {
            return true;
        }
        return false;
    }
    
    public static void updateAddress(int id, String address) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("UPDATE [User] SET [address] = ? WHERE [UserID] = ?");
            ps.setString(1, address);
            ps.setInt(2, id);
            ps.executeUpdate();
            dbo.commit();
            ps.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
    }
    
    public static void updateAvatar(int id, String avatar, boolean isMentor, boolean create) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            if(create) {
                if(isMentor) {
                    PreparedStatement ps = dbo.prepareStatement("INSERT INTO [Mentor] ([UserID], [Avatar]) VALUES (?, ?)");
                    ps.setInt(1, id);
                    ps.setString(2, avatar);
                    ps.executeUpdate();
                } else {
                    PreparedStatement ps = dbo.prepareStatement("INSERT INTO [Mentee] ([UserID], [Avatar]) VALUES (?, ?)");
                    ps.setInt(1, id);
                    ps.setString(2, avatar);
                    ps.executeUpdate();
                }
            } else {
                if(isMentor) {
                    PreparedStatement ps = dbo.prepareStatement("UPDATE [Mentor] SET [Avatar] = ? WHERE [UserID] = ?");
                    ps.setInt(2, id);
                    ps.setString(1, avatar);
                    ps.executeUpdate();
                } else {
                    PreparedStatement ps = dbo.prepareStatement("UPDATE [Mentee] SET [Avatar] = ? WHERE [UserID] = ?");
                    ps.setInt(2, id);
                    ps.setString(1, avatar);
                    ps.executeUpdate();
                }
            }
            dbo.commit();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
    }
    
    public static void updateFullname(int id, String fullname, boolean isMentor, boolean create) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            if(create) {
                if(isMentor) {
                    PreparedStatement ps = dbo.prepareStatement("INSERT INTO [Mentor] ([UserID], [fullname]) VALUES (?, ?)");
                    ps.setInt(1, id);
                    ps.setString(2, fullname);
                    ps.executeUpdate();
                } else {
                    PreparedStatement ps = dbo.prepareStatement("INSERT INTO [Mentee] ([UserID], [fullname]) VALUES (?, ?)");
                    ps.setInt(1, id);
                    ps.setString(2, fullname);
                    ps.executeUpdate();
                }
            } else {
                if(isMentor) {
                    PreparedStatement ps = dbo.prepareStatement("UPDATE [Mentor] SET [fullname] = ? WHERE [UserID] = ?");
                    ps.setInt(2, id);
                    ps.setString(1, fullname);
                    ps.executeUpdate();
                } else {
                    PreparedStatement ps = dbo.prepareStatement("UPDATE [Mentee] SET [fullname] = ? WHERE [UserID] = ?");
                    ps.setInt(2, id);
                    ps.setString(1, fullname);
                    ps.executeUpdate();
                }
            }
            dbo.commit();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
    }
    
    public static void updateGender(int id, boolean gender) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("UPDATE [User] SET [sex] = ? WHERE [UserID] = ?");
            ps.setInt(1, gender ? 1 : 0);
            ps.setInt(2, id);
            ps.executeUpdate();
            dbo.commit();
            ps.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
    }
    
    public static void updateDob(int id, String Dob) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("UPDATE [User] SET [dob] = ? WHERE [UserID] = ?");
            ps.setDate(1, Date.valueOf(Dob));
            ps.setInt(2, id);
            ps.executeUpdate();
            dbo.commit();
            ps.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
    }
    
    public static void updatePhone(int id, String phone) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("UPDATE [User] SET [phoneNumber] = ? WHERE [UserID] = ?");
            ps.setString(1, phone);
            ps.setInt(2, id);
            ps.executeUpdate();
            dbo.commit();
            ps.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
    }
    
    public static boolean isRegistered(String email, String username) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [User] WHERE [email] = ? AND [username] = ?");
            ps.setString(1, email);
            ps.setString(2, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                rs.close();
                ps.close();
                dbo.close();
                return true;
            }
            rs.close();
            ps.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return false;
    }
    
    public static User getUser(String username, String password) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [User] WHERE [username] = ? AND [password] = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                PreparedStatement ps2 = dbo.prepareStatement("SELECT * FROM [Role] WHERE [RoleID] = ?");
                ps2.setInt(1, rs.getInt("RoleID"));
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                User u = new User(username, password, rs.getString("email"), rs.getString("phoneNumber"), rs.getString("address"), rs2.getString("roleName"), rs.getDate("dob"), rs.getInt("wallet"), rs.getInt("UserID"), rs.getInt("activeStatus") == 1 ? true : false, rs.getInt("sex") == 1 ? true : false);
                if(rs.getInt("isValidate") != 0) {
                    u.setValidate(true);
                }
                return u;
            }
            rs.close();
            ps.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return null;
    }
    
    public static Object getRole(int id, String role) {
        Connection dbo = DatabaseUtil.getConn();
        try {
            if(role.equalsIgnoreCase("mentee")) {
                PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Mentee] WHERE [UserID] = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    return new Mentee(rs.getString("Avatar"), rs.getString("fullname"), id);
                }
            } else if(role.equalsIgnoreCase("mentor")) {
                PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Mentor] WHERE [UserID] = ?");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    return new Mentor(rs.getString("Avatar"), rs.getString("fullname"), rs.getString("Achivement"), rs.getString("Description"), id, rs.getInt("CvID"));
                }
            }
        } catch(Exception e) {}
        return null;
    }
    
    public static User register(String username, String password, String email, String phone, String address, String role, String gender, String fullname, String dob) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            if(address == null) {
                address = "";
            }
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [User] WHERE [username] = ? OR [email] = ?");
            ps.setString(1, username);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()) {
                ps = dbo.prepareStatement("INSERT INTO [User] ([username], [password], [email], [sex], [dob], [phoneNumber], [address], [RoleID], [wallet], [activeStatus]) VALUES (?, ?, ?, ?, ?, ?, ?, (SELECT [RoleID] FROM [Role] WHERE [roleName] = ?), 0, 1)");
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.setInt(4, gender.equals("f") ? 1 : 0);
                ps.setDate(5, Date.valueOf(dob));
                ps.setString(6, phone);
                ps.setString(7, address);
                ps.setString(8, role);
                int k = ps.executeUpdate();
                dbo.commit();
                User u = UserDAO.getUser(username, password);
                if(fullname != null) {
                    if(role.equalsIgnoreCase("mentor")) {
                        ps = dbo.prepareStatement("INSERT INTO [Mentor] ([fullname], [UserID]) VALUES (?, ?)");
                        ps.setString(1, fullname);
                        ps.setInt(2, u.getId());
                        ps.executeUpdate();
                        dbo.commit();
                    } else {
                        ps = dbo.prepareStatement("INSERT INTO [Mentee] ([fullname], [UserID]) VALUES (?, ?)");
                        ps.setString(1, fullname);
                        ps.setInt(2, u.getId());
                        ps.executeUpdate();
                        dbo.commit();
                    }
                }
                if(k > 0)
                    return u;
            }
            rs.close();
            ps.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return null;
    }
}

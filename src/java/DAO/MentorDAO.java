/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DataConnector.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import model.Mentor;
import model.MentorDetail;

/**
 *
 * @author TGDD
 */
public class MentorDAO {
    
    public static Mentor getMentor(int id) {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Mentor] WHERE [UserID] = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Mentor m = new Mentor(rs.getString("Avatar"), rs.getString("fullname"), rs.getString("Achivement"), rs.getString("Description"), rs.getInt("UserID"), rs.getInt("CvID"));
                dbo.close();
                return m;
            }
        } catch(Exception e) {}
        return null;
    }
    
    
    
    public static HashMap<Mentor, MentorDetail> getAllBySkillId(int id) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        HashMap<Mentor, MentorDetail> arr = new HashMap();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Mentor] WHERE [UserID] in (SELECT [MentorID] FROM [MentorSkills] WHERE [SkillID] = ?)");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                PreparedStatement ps2 = dbo.prepareStatement("SELECT AVG([noStar]) AS Rate FROM [Rating] WHERE [MentorID] = ?");
                ps2.setInt(1, rs.getInt("UserID"));
                ResultSet rs2 = ps2.executeQuery();
                int rate = 0;
                if(rs2.next()) {
                    rate = rs2.getInt("Rate");
                }
                rs2.close();
                ps2.close();
                ps2 = dbo.prepareStatement("SELECT Count([RequestID]) as [accept]  FROM [Request] WHERE [UserID] = ? AND [RequestStatus] = 'Confirmed' OR [RequestStatus] = 'Done' OR [RequestStatus] = 'In Progress'");
                ps2.setInt(1, rs.getInt("UserID"));
                rs2 = ps2.executeQuery();
                int accept = 0;
                if(rs2.next()) {
                    accept = rs2.getInt("accept");
                }
                rs2.close();
                ps2.close();
                ps2 = dbo.prepareStatement("SELECT Count([RequestID]) as [done]  FROM [Request] WHERE [UserID] = ? AND [RequestStatus] = 'Confirmed' OR [RequestStatus] = 'Done'");
                ps2.setInt(1, rs.getInt("UserID"));
                rs2 = ps2.executeQuery();
                int done = 0;
                if(rs2.next()) {
                    done = rs2.getInt("done");
                }
                rs2.close();
                ps2.close();
                ps2 = dbo.prepareStatement("SELECT [username], [activeStatus]  FROM [User] WHERE [UserID] = ?");
                ps2.setInt(1, rs.getInt("UserID"));
                rs2 = ps2.executeQuery();
                boolean active = false;
                String account = "";
                if(rs2.next()) {
                    account = rs2.getString("username");
                    active = rs2.getInt("activeStatus") == 1;
                }
                rs2.close();
                ps2.close();
                ps2 = dbo.prepareStatement("SELECT [ProfessionIntroduction] FROM [CV] WHERE [CvID] = ?");
                ps2.setInt(1, rs.getInt("CvID"));
                rs2 = ps2.executeQuery();
                String profession = "";
                if(rs2.next()) {
                    profession = rs2.getString("ProfessionIntroduction");
                }
                rs2.close();
                ps2.close();
                ps2 = dbo.prepareStatement("SELECT Count([RequestID]) as [requests]  FROM [Request] WHERE [UserID] = ?");
                ps2.setInt(1, rs.getInt("UserID"));
                rs2 = ps2.executeQuery();
                int request = 0;
                if(rs2.next()) {
                    request = rs2.getInt("requests");
                }
                rs2.close();
                ps2.close();
                MentorDetail md = new MentorDetail(rs.getInt("UserID"), rate, accept, (accept > 0 ? (done / (accept / 100)) : 0), account, profession, active);
                md.setRequests(request);
                arr.put(new Mentor(rs.getString("Avatar"), rs.getString("fullname"), rs.getString("Achivement"), rs.getString("Description"), rs.getInt("UserID"), rs.getInt("CvID")), md);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return arr;
    }
    
    public static ArrayList<Mentor> getAll() throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        ArrayList<Mentor> arr = new ArrayList();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Mentor]");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                arr.add(new Mentor(rs.getString("Avatar"), rs.getString("fullname"), rs.getString("Achivement"), rs.getString("Description"), rs.getInt("UserID"), rs.getInt("CvID")));
            }
        } catch(Exception e) {} finally {
            dbo.close();
        }
        return arr;
    }
    
    public static HashMap<Mentor, MentorDetail> getAllWithDetail() throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        HashMap<Mentor, MentorDetail> arr = new HashMap();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Mentor]");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                PreparedStatement ps2 = dbo.prepareStatement("SELECT AVG([noStar]) AS Rate FROM [Rating] WHERE [MentorID] = ?");
                ps2.setInt(1, rs.getInt("UserID"));
                ResultSet rs2 = ps2.executeQuery();
                int rate = 0;
                if(rs2.next()) {
                    rate = rs2.getInt("Rate");
                }
                rs2.close();
                ps2.close();
                ps2 = dbo.prepareStatement("SELECT Count([RequestID]) as [accept]  FROM [Request] WHERE [UserID] = ? AND [RequestStatus] = 'Confirmed' OR [RequestStatus] = 'Done' OR [RequestStatus] = 'In Progress'");
                ps2.setInt(1, rs.getInt("UserID"));
                rs2 = ps2.executeQuery();
                int accept = 0;
                if(rs2.next()) {
                    accept = rs2.getInt("accept");
                }
                rs2.close();
                ps2.close();
                ps2 = dbo.prepareStatement("SELECT Count([RequestID]) as [done]  FROM [Request] WHERE [UserID] = ? AND [RequestStatus] = 'Confirmed' OR [RequestStatus] = 'Done'");
                ps2.setInt(1, rs.getInt("UserID"));
                rs2 = ps2.executeQuery();
                int done = 0;
                if(rs2.next()) {
                    done = rs2.getInt("done");
                }
                rs2.close();
                ps2.close();
                ps2 = dbo.prepareStatement("SELECT [username], [activeStatus]  FROM [User] WHERE [UserID] = ?");
                ps2.setInt(1, rs.getInt("UserID"));
                rs2 = ps2.executeQuery();
                boolean active = false;
                String account = "";
                if(rs2.next()) {
                    account = rs2.getString("username");
                    active = rs2.getInt("activeStatus") == 1;
                }
                rs2.close();
                ps2.close();
                ps2 = dbo.prepareStatement("SELECT [ProfessionIntroduction] FROM [CV] WHERE [CvID] = ?");
                ps2.setInt(1, rs.getInt("CvID"));
                rs2 = ps2.executeQuery();
                String profession = "";
                if(rs2.next()) {
                    profession = rs2.getString("ProfessionIntroduction");
                }
                arr.put(new Mentor(rs.getString("Avatar"), rs.getString("fullname"), rs.getString("Achivement"), rs.getString("Description"), rs.getInt("UserID"), rs.getInt("CvID")), new MentorDetail(rs.getInt("UserID"), rate, accept, (accept > 0 ? (done / (accept / 100)) : 0), account, profession, active));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return arr;
    }
    
    public static boolean toggle(boolean type,int id) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        PreparedStatement ps = dbo.prepareStatement("UPDATE [User] SET [activeStatus] = "+(type ? 1 : 0)+" WHERE [UserID] = ?");
        ps.setInt(1, id);
        int k = ps.executeUpdate();
        dbo.commit();
        if(k > 0) {
            dbo.close();
            return true;
        }
        dbo.close();
        return false;
    }
    
    public static void updateAchivement(int id, String achivement) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Mentor] WHERE [UserID] = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                rs.close();
                ps.close();
                ps = dbo.prepareStatement("UPDATE [Mentor] SET [Achivement] = ? WHERE [UserID] = ?");
                ps.setInt(2, id);
                ps.setString(1, achivement);
                ps.executeUpdate();
            } else {
                rs.close();
                ps.close();
                ps = dbo.prepareStatement("INSERT INTO [Mentor] ([Achivement], [UserID]) VALUES (?, ?)");
                ps.setInt(2, id);
                ps.setString(1, achivement);
                ps.executeUpdate();
            }
            dbo.commit();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
    }
    
    public static void updateDescription(int id, String description) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Mentor] WHERE [UserID] = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                rs.close();
                ps.close();
                ps = dbo.prepareStatement("UPDATE [Mentor] SET [Description] = ? WHERE [UserID] = ?");
                ps.setInt(2, id);
                ps.setString(1, description);
                ps.executeUpdate();
            } else {
                rs.close();
                ps.close();
                ps = dbo.prepareStatement("INSERT INTO [Mentor] ([Description], [UserID]) VALUES (?, ?)");
                ps.setInt(2, id);
                ps.setString(1, description);
                ps.executeUpdate();
            }
            dbo.commit();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
    }
}

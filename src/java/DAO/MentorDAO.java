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
import model.MentorStatistic;

/**
 *
 * @author TGDD
 */
public class MentorDAO {
    
    public static boolean acceptedCv(int id) {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Mentor] WHERE [MentorStatus] = N'Accepted' AND [UserID] = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                dbo.close();
                return true;
            }
            dbo.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
        
    }
    
    public static int getSlotCash(int id) {
        int cash = 0;
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT [CashPerSlot] FROM [CV] WHERE [CvID] = (SELECT [CvID] FROM [Mentor] WHERE [UserID] = ?)");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            cash = rs.getInt("CashPerSlot");
            dbo.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return cash;
    }
    
    public static MentorStatistic getMentorStatistic(int id) {
        MentorStatistic ms = null;
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("  SELECT Count([RequestID]) as TotalRequest, \n" +
"  (SELECT Count([RequestID]) FROM [Request] WHERE [UserID] = ? AND ([RequestStatus] = N'Accept' OR [RequestStatus] = N'Processing' OR [RequestStatus] = N'Done')) as TotalAccepted, \n" +
"  (SELECT Count([RequestID]) FROM [Request] WHERE [UserID] = ? AND [RequestStatus] = N'Reject') as TotalRejected, \n" +
"  (SELECT AVG(Cast([noStar] as Float)) FROM [Rating] WHERE [MentorID] = ?) as Rating, \n" +
"  (SELECT Count([RequestID]) FROM [Request] WHERE [UserID] = ? AND [RequestStatus] = N'Done') as TotalDone \n" +
"  FROM [Request] WHERE [UserID] = ?");
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.setInt(3, id);
            ps.setInt(4, id);
            ps.setInt(5, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                ms = new MentorStatistic();
                ms.setId(id);
                ms.setInvitedRequest(rs.getInt("TotalRequest"));
                ms.setAccepedRequest(rs.getInt("TotalAccepted"));
                ms.setRejectedRequest(rs.getInt("TotalRejected"));
                ms.setRating(rs.getFloat("Rating"));
                if(ms.getInvitedRequest() > 0) {
                    ms.setRejectPercent((float)ms.getRejectedRequest() / (float)ms.getInvitedRequest());
                } else {
                    ms.setRejectPercent(0);
                }
                if(ms.getAccepedRequest() > 0) {
                    ms.setCompletePercent((float) rs.getInt("TotalDone") / (float)ms.getAccepedRequest());
                } else {
                    ms.setCompletePercent(0);
                }
                dbo.close();
            }
        } catch(Exception e) {}
        return ms;
    }
    
    public static Mentor getMentor(int id) {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Mentor] WHERE [UserID] = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                ps = dbo.prepareStatement("SELECT * FROM [User] WHERE [UserID] = ?");
                ps.setInt(1, id);
                ResultSet rs2 = ps.executeQuery();   
                rs2.next();
                Mentor m = new Mentor(rs.getString("MentorStatus"), rs.getString("Achivement"), rs.getString("Description"), rs.getInt("UserID"), rs.getInt("CvID"), rs2.getString("fullname"), rs2.getString("Avatar"));
                ps = dbo.prepareStatement("SELECT Count([FollowID]) as Follow FROM [Follow] WHERE [MentorID] = ?");
                ps.setInt(1, id);
                rs2 = ps.executeQuery();   
                rs2.next();
                m.setFollow(rs2.getInt("Follow"));
                ps = dbo.prepareStatement("SELECT Count(*) as ratingTime, AVG(Cast([noStar] as Float)) as Rate FROM [Rating] WHERE MentorID = ?");
                ps.setInt(1, id);
                rs2 = ps.executeQuery();   
                rs2.next();
                m.setRatingTime(rs2.getInt("ratingTime"));
                m.setRate(rs2.getFloat("Rate"));
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
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Mentor] WHERE [UserID] in (SELECT [MentorID] FROM [MentorSkills] WHERE [SkillID] = ?) AND (SELECT [activeStatus] FROM [User] WHERE [UserID] = [Mentor].[UserID]) = 1 AND (SELECT Count(*) as FreeSlot FROM [Slot] WHERE (Select MentorID FROM Schedule WHERE [Slot].ScheduleID = Schedule.ScheduleID) = [Mentor].[UserID] AND SkillID IS NULL) > 0 AND [MentorStatus] = N'Accepted'");
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
                ps2 = dbo.prepareStatement("SELECT Count([RequestID]) as [accept]  FROM [Request] WHERE [UserID] = ? AND ([RequestStatus] = 'Confirmed' OR [RequestStatus] = 'Done' OR [RequestStatus] = 'Processing')");
                ps2.setInt(1, rs.getInt("UserID"));
                rs2 = ps2.executeQuery();
                int accept = 0;
                if(rs2.next()) {
                    accept = rs2.getInt("accept");
                }
                rs2.close();
                ps2.close();
                ps2 = dbo.prepareStatement("SELECT Count([RequestID]) as [done]  FROM [Request] WHERE [UserID] = ? AND ([RequestStatus] = 'Confirmed' OR [RequestStatus] = 'Done')");
                ps2.setInt(1, rs.getInt("UserID"));
                rs2 = ps2.executeQuery();
                int done = 0;
                if(rs2.next()) {
                    done = rs2.getInt("done");
                }
                rs2.close();
                ps2.close();
                ps2 = dbo.prepareStatement("SELECT * FROM [User] WHERE [UserID] = ?");
                ps2.setInt(1, rs.getInt("UserID"));
                rs2 = ps2.executeQuery();
                boolean active = false;
                String account = "";
                String fullname = "";
                String avatar = "";
                if(rs2.next()) {
                    account = rs2.getString("username");
                    active = rs2.getInt("activeStatus") == 1;
                    fullname = rs2.getString("fullname");
                    avatar = rs2.getString("Avatar");
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
                MentorDetail md = new MentorDetail(rs.getInt("UserID"), rate, accept, (accept > 0 ? ((accept) == 0 ? 0 : (int)((float)done / (float)(accept / 100))) : 0), account, profession, active);
                md.setRequests(request);
                arr.put(new Mentor(rs.getString("MentorStatus"), rs.getString("Achivement"), rs.getString("Description"), rs.getInt("UserID"), rs.getInt("CvID"), fullname, avatar), md);
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
                ps = dbo.prepareStatement("SELECT * FROM [User] WHERE [UserID] = ?");
                ps.setInt(1, rs.getInt("UserID"));
                ResultSet rs2 = ps.executeQuery();
                rs2.next();
                arr.add(new Mentor(rs.getString("MentorStatus"), rs.getString("Achivement"), rs.getString("Description"), rs.getInt("UserID"), rs.getInt("CvID"), rs2.getString("fullname"), rs2.getString("Avatar")));
            }
        } catch(Exception e) {} finally {
            dbo.close();
        }
        return arr;
    }
    
    public static ArrayList<Mentor> searchMentor(String name, String city, String skill, String gender, String ready) throws Exception {
        ArrayList<Mentor> arr = new ArrayList();
        String sql = "SELECT [Mentor].[UserID] ,[Description] ,[CvID] ,[Achivement] ,[MentorStatus], [Avatar], [fullname] FROM [SWP_Project].[dbo].[Mentor] INNER JOIN [User] ON [User].[UserID] = [Mentor].[UserID] WHERE ";
        int filter = 0;
        if(name != null) {
            sql += "([User].[username] LIKE N'%"+name+"%' OR [User].[fullname] LIKE N'%"+name+"%')";
            filter++;
        }
        if(city != null) {
            if(filter > 0) {
                sql += " AND ";
            }
            sql += "([User].[address] LIKE N'%"+city+"%')";
            filter++;
        }
        if(gender != null) {
            if(filter > 0) {
                sql += " AND ";
            }
            sql += "([User].[sex] = "+ (gender.equalsIgnoreCase("female") ? 1 : 0 ) +")";
            filter++;
        }
        if(ready != null) {
            if(filter > 0) {
                sql += " AND ";
            }
            sql += "([Mentor].[MentorStatus] "+ (ready.equalsIgnoreCase("true") ? "= N'Accepted'" : "!= N'Accepted'" ) +")";
            filter++;
        }
        if(skill != null) {
            if(filter > 0) {
                sql += " AND ";
            }
            sql += "(SELECT [SkillID] FROM [MentorSkills] WHERE [MentorID] = [Mentor].[UserID]) = " + skill;
        }
        Connection dbo = DatabaseUtil.getConn();
        PreparedStatement ps = dbo.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            arr.add(new Mentor(rs.getString("MentorStatus"), rs.getString("Achivement"), rs.getString("Description"), rs.getInt("UserID"), rs.getInt("CvID"), rs.getString("fullname"), rs.getString("Avatar")));
        }
        dbo.close();
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
                ps2 = dbo.prepareStatement("SELECT Count([RequestID]) as [accept]  FROM [Request] WHERE [UserID] = ? AND ([RequestStatus] = 'Confirmed' OR [RequestStatus] = 'Done' OR [RequestStatus] = 'Processing')");
                ps2.setInt(1, rs.getInt("UserID"));
                rs2 = ps2.executeQuery();
                int accept = 0;
                if(rs2.next()) {
                    accept = rs2.getInt("accept");
                }
                rs2.close();
                ps2.close();
                ps2 = dbo.prepareStatement("SELECT Count([RequestID]) as [done]  FROM [Request] WHERE [UserID] = ? AND ([RequestStatus] = 'Confirmed' OR [RequestStatus] = 'Done')");
                ps2.setInt(1, rs.getInt("UserID"));
                rs2 = ps2.executeQuery();
                int done = 0;
                if(rs2.next()) {
                    done = rs2.getInt("done");
                }
                rs2.close();
                ps2.close();
                ps2 = dbo.prepareStatement("SELECT * FROM [User] WHERE [UserID] = ?");
                ps2.setInt(1, rs.getInt("UserID"));
                rs2 = ps2.executeQuery();
                boolean active = false;
                String account = "";
                String fullname = "";
                String avatar = "";
                if(rs2.next()) {
                    account = rs2.getString("username");
                    active = rs2.getInt("activeStatus") == 1;
                    fullname = rs2.getString("fullname");
                    avatar = rs2.getString("Avatar");
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
                arr.put(new Mentor(rs.getString("MentorStatus"), rs.getString("Achivement"), rs.getString("Description"), rs.getInt("UserID"), rs.getInt("CvID"), fullname, avatar), new MentorDetail(rs.getInt("UserID"), rate, accept, (accept > 0 ? (int)((float)done / ((float)accept / 100)) : 0), account, profession, active));
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

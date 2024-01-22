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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Mentor;
import model.Request;
import model.Skill;

/**
 *
 * @author TGDD
 */
public class RequestDAO {
    
    public static boolean deleteAll(int uid) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("DELETE FROM [RequestSkill] WHERE [RequestID] in (SELECT [RequestID] FROM [Request] WHERE [SenderID] = ?)");
            ps.setInt(1, uid);
            ps.executeUpdate();
            ps = dbo.prepareStatement("DELETE FROM [Request] WHERE [SenderID] = ?");
            ps.setInt(1, uid);
            int k = ps.executeUpdate();
            dbo.commit();
            if(k > 0) {
                dbo.close();
                return true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return false;
    }
    
    public static boolean deleteRequest(int rid) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("DELETE FROM [RequestSkill] WHERE [RequestID] = ?");
            ps.setInt(1, rid);
            ps.executeUpdate();
            ps = dbo.prepareStatement("DELETE FROM [Request] WHERE [RequestID] = ?");
            ps.setInt(1, rid);
            int k = ps.executeUpdate();
            dbo.commit();
            if(k > 0) {
                dbo.close();
                return true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return false;
    }
    
    public static Request getRequest(int rid) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Request] WHERE [RequestID] = ?");
            ps.setInt(1, rid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Request r = new Request(rs.getInt("RequestID"), rs.getInt("SenderID"), rs.getInt("UserID"), rs.getString("RequestReason"), rs.getString("RequestStatus"), rs.getString("RequestSubject"), rs.getTimestamp("RequestTime"), rs.getTimestamp("DeadlineTime"));
                ps = dbo.prepareStatement("SELECT [fullname] FROM [Mentor] WHERE [UserID] = ?");
                ps.setInt(1, r.getUserID());
                ResultSet rs2 = ps.executeQuery();
                rs2.next();
                r.setMentor(rs2.getString("fullname"));
                ps = dbo.prepareStatement("SELECT [SkillName], [SkillID] FROM [Skills] WHERE [SkillID] in (SELECT [SkillID] FROM [RequestSkill] WHERE [RequestID] = ?");
                ps.setInt(1, rs.getInt("RequestID"));
                rs2 = ps.executeQuery();
                while(rs2.next()) {
                    r.getSkills().add(new Skill(rs2.getInt("SkillID"), rs2.getString("SkillName")));
                }
                dbo.close();
                return r;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return null;
    }
    
    public static ArrayList<Request> getRequests(int uid) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        ArrayList<Request> arr = new ArrayList();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Request] WHERE [SenderID] = ?");
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Request r = new Request(rs.getInt("RequestID"), rs.getInt("SenderID"), rs.getInt("UserID"), rs.getString("RequestReason"), rs.getString("RequestStatus"), rs.getString("RequestSubject"), rs.getTimestamp("RequestTime"), rs.getTimestamp("DeadlineTime"));
                ps = dbo.prepareStatement("SELECT [fullname] FROM [Mentor] WHERE [UserID] = ?");
                ps.setInt(1, r.getUserID());
                ResultSet rs2 = ps.executeQuery();
                rs2.next();
                r.setMentor(rs2.getString("fullname"));
                ps = dbo.prepareStatement("SELECT [SkillName], [SkillID] FROM [Skills] WHERE [SkillID] in (SELECT [SkillID] FROM [RequestSkill] WHERE [RequestID] = ?)");
                ps.setInt(1, rs.getInt("RequestID"));
                rs2 = ps.executeQuery();
                while(rs2.next()) {
                    r.getSkills().add(new Skill(rs2.getInt("SkillID"), rs2.getString("SkillName")));
                }
                arr.add(r);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return arr;
    }
    
    public static boolean updateRequest(String[] skills, Timestamp deadline, String subject, String reason, int sid, String status, int rid) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("UPDATE [Request] SET [SenderID] = ?, [RequestSubject] = ?, [RequestReason] = ?, [DeadlineTime] = ?, [RequestStatus] = ? WHERE [RequestID] = ?");
            ps.setInt(1, sid);
            ps.setString(2, subject);
            ps.setString(3, reason);
            ps.setTimestamp(4, deadline);
            ps.setString(5, status);
            ps.setInt(6, rid);
            int k = ps.executeUpdate();
            dbo.commit();
            if(k > 0) {
                ps = dbo.prepareStatement("DELETE FROM [RequestSkill] WHERE [RequestID] = ?");
                ps.setInt(1, rid);
                ps.executeUpdate();
                for (int i = 0; i < skills.length; i++) {
                    int skid = Integer.parseInt(skills[i]);
                    ps = dbo.prepareStatement("INSERT INTO [RequestSkill] ([RequestID], [SkillID]) VALUES (?, ?)");
                    ps.setInt(1, rid);
                    ps.setInt(2, skid);
                    ps.executeUpdate();
                }
                dbo.commit();
                dbo.close();
                return true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return false;
    }
    
    public static boolean createRequest(String[] skills, Timestamp deadline, String subject, String reason, int sid, int uid) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("INSERT INTO [Request] ([SenderID], [UserID], [RequestSubject], [RequestReason], [DeadlineTime], [RequestStatus]) VALUES (?, ?, ?, ?, ?, 'Open')");
            ps.setInt(1, sid);
            ps.setInt(2, uid);
            ps.setString(3, subject);
            ps.setString(4, reason);
            ps.setTimestamp(5, deadline);
            int k = ps.executeUpdate();
            dbo.commit();
            if(k > 0) {
                ps = dbo.prepareStatement("SELECT Top(1) [RequestID] FROM [Request] ORDER BY [RequestID] DESC");
                ResultSet rs = ps.executeQuery();
                rs.next();
                int rid = rs.getInt("RequestID");
                for (int i = 0; i < skills.length; i++) {
                    int skid = Integer.parseInt(skills[i]);
                    ps = dbo.prepareStatement("INSERT INTO [RequestSkill] ([RequestID], [SkillID]) VALUES (?, ?)");
                    ps.setInt(1, rid);
                    ps.setInt(2, skid);
                    ps.executeUpdate();
                }
                dbo.commit();
                dbo.close();
                return true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return false;
    }
}

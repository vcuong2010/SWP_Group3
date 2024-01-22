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
import model.CV;
import model.Skill;

/**
 *
 * @author TGDD
 */
public class CvDAO {
    
    public static boolean createCV(int userID, String ProfessionIntroduction, String ServiceDescription, String[] skills) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("INSERT INTO [CV] ([ProfessionIntroduction], [ServiceDescription]) VALUES (?, ?)");
            ps.setString(1, ProfessionIntroduction);
            ps.setString(2, ServiceDescription);
            ps.executeUpdate();
            dbo.commit();
            ps = dbo.prepareStatement("SELECT TOP (1) [CvID] FROM [CV] ORDER BY [CvID] DESC");
            ResultSet rs = ps.executeQuery();
            rs.next();
            ps = dbo.prepareStatement("UPDATE [Mentor] SET [CvID] = ? WHERE [UserID] = ?");
            ps.setInt(1, rs.getInt("CvID"));
            ps.setInt(2, userID);
            ps.executeUpdate();
            dbo.commit();
            for (int i = 0; i < skills.length; i++) {
                int id = Integer.parseInt(skills[i]);
                ps = dbo.prepareStatement("INSERT INTO [MentorSkills] ([SkillID], [MentorID]) VALUES (?, ?)");
                ps.setInt(1, id);
                ps.setInt(2, userID);
                ps.executeUpdate();
            }
            dbo.commit();
            dbo.close();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return false;
    }
    
    public static CV getCV(int id) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [CV] WHERE [CvID] = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                ps = dbo.prepareStatement("SELECT [UserID] FROM [Mentor] WHERE [CvID] = ?");
                ps.setInt(1, id);
                ResultSet rs2 = ps.executeQuery();
                rs2.next();
                ps = dbo.prepareStatement("SELECT * FROM [MentorSkills] WHERE [MentorID] = ?");
                ps.setInt(1, rs2.getInt("UserID"));
                rs2 = ps.executeQuery();
                CV cv = new CV(rs.getInt("CvID"), rs.getString("ProfessionIntroduction"), rs.getString("ServiceDescription"));
                while(rs2.next()) {
                    ps = dbo.prepareStatement("SELECT * FROM [Skills] WHERE [SkillID] = ?");
                    ps.setInt(1, rs2.getInt("SkillID"));
                    rs = ps.executeQuery();
                    rs.next();
                    cv.getSkills().add(new Skill(rs2.getInt("SkillID"), rs.getString("SkillName")));
                }
                dbo.close();
                return cv;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return null;
    }
    
    public static boolean updateCV(int CvID, String ProfessionIntroduction, String ServiceDescription) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("UPDATE [CV] SET [ProfessionIntroduction] = ?, [ServiceDescription] = ? WHERE [CvID] = ?");
            ps.setString(1, ProfessionIntroduction);
            ps.setString(2, ServiceDescription);
            ps.setInt(3, CvID);
            ps.executeUpdate();
            dbo.commit();
            dbo.close();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return false;
    }
    
    public static boolean removeSkill(int userID, int skillID) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("DELETE FROM [MentorSkills] WHERE [MentorID] = ? AND SkillID = ?");
            ps.setInt(1, userID);
            ps.setInt(2, skillID);
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
    
    public static boolean addSkill(int userID, int skillID) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [MentorSkills] WHERE [MentorID] = ? AND [SkillID] = ?");
            ps.setInt(1, userID);
            ps.setInt(2, skillID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return false;
            ps = dbo.prepareStatement("INSERT INTO [MentorSkills] ([MentorID], [SkillID]) VALUES (?, ?)");
            ps.setInt(1, userID);
            ps.setInt(2, skillID);
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
}

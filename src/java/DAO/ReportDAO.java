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
import model.Report;

/**
 *
 * @author TGDD
 */
public class ReportDAO {
    
    public static ArrayList<Report> getReports() throws Exception {
        ArrayList<Report> arr = new ArrayList();
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Report]");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                arr.add(new Report(rs.getInt("UserID"), rs.getString("ReportContent"), rs.getTimestamp("reportTime")));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
        return arr;
    }
    
    public static void sendReport(int uid, String report) throws Exception {
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("INSERT INTO [Report] ([ReportContent], [UserID]) VALUES (?, ?)");
            ps.setString(1, report);
            ps.setInt(2, uid);
            ps.executeUpdate();
            dbo.commit();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            dbo.close();
        }
    }
}

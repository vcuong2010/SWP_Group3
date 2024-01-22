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
import java.sql.SQLException;
import java.util.ArrayList;
import model.Slot;

/**
 *
 * @author TGDD
 */
public class SlotDAO {
    
    
    public static Slot getSlotByID(int id) throws SQLException {
        Connection dbo = DatabaseUtil.getConn();
        PreparedStatement ps = dbo.prepareStatement("SELECT * FROM Slot WHERE SlotID = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            Slot s = new Slot(rs.getInt("SlotID"), rs.getDate("Time"), rs.getDate("CreateAt"), rs.getInt("Money"));
            dbo.close();
            return s;
        }
        dbo.close();
        return null;
    }
    
    public static ArrayList<Slot> getSlots() throws SQLException {
        ArrayList<Slot> arr = new ArrayList();
        Connection dbo = DatabaseUtil.getConn();
        PreparedStatement ps = dbo.prepareStatement("SELECT * FROM Slot");
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            arr.add(new Slot(rs.getInt("SlotID"), rs.getDate("Time"), rs.getDate("CreateAt"), rs.getInt("Money")));
        }
        dbo.close();
        return arr;
    }
    
    public static boolean addSlot(Date SlotTime, int money) throws SQLException {
        Connection dbo = DatabaseUtil.getConn();
        PreparedStatement ps = dbo.prepareStatement("INSERT INTO Slot (Time, createAt, Money) VALUES (?, ?, ?)");
        ps.setDate(1, SlotTime);
        ps.setDate(2, new Date(new java.util.Date().getTime()));
        ps.setInt(3, money);
        int k = ps.executeUpdate();
        dbo.commit();
        if(k > 0) {
            dbo.close();
            return true;
        }
        dbo.close();
        return false;
    }
    
    public static boolean EditSlot(int id, Date slotTime, int money) throws SQLException {
        Connection dbo = DatabaseUtil.getConn();
        PreparedStatement ps = dbo.prepareStatement("UPDATE Slot SET Time = ?, Money = ? WHERE SlotID = ?");
        ps.setDate(1, slotTime);
        ps.setInt(2, money);
        ps.setInt(3, id);
        int k = ps.executeUpdate();
        dbo.commit();
        if(k > 0) {
            dbo.close();
            return true;
        }
        dbo.close();
        return false;
    }
    
    public static boolean deleteSlot(int id) throws SQLException {
        Connection dbo = DatabaseUtil.getConn();
        PreparedStatement ps = dbo.prepareStatement("DELETE FROM Slot WHERE SlotID = ?");
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
}

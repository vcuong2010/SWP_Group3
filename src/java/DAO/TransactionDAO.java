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
import java.util.ArrayList;
import model.Transaction;

/**
 *
 * @author TGDD
 */
public class TransactionDAO {
    public static ArrayList<Transaction> getTransactionById(int id) throws Exception {
        ArrayList<Transaction> arr = new ArrayList();
        Connection dbo = DatabaseUtil.getConn();
        try {
            PreparedStatement ps = dbo.prepareStatement("SELECT * FROM [Transaction] WHERE [UserID] = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                arr.add(new Transaction(rs.getInt("ID"), rs.getInt("UserID"), rs.getInt("Balance"), rs.getString("Type"), rs.getString("Content"), rs.getDate("Time"), rs.getString("Status")));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
}

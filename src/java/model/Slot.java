/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DataConnector.DatabaseUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TGDD
 */
public class Slot {
    int id;
    Date SlotTime;
    Date createAt;
    int money;

    public Slot(int id, Date SlotTime, Date createAt, int money) {
        this.id = id;
        this.SlotTime = SlotTime;
        this.createAt = createAt;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public Date getSlotTime() {
        return SlotTime;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public int getMoney() {
        return money;
    }
    
    
}

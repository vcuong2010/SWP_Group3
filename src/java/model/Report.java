/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author TGDD
 */
public class Report {
    int uid;
    String content;
    Timestamp SendTime;

    public Report(int uid, String content, Timestamp SendTime) {
        this.uid = uid;
        this.content = content;
        this.SendTime = SendTime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSendTime() {
        return SendTime;
    }

    public void setSendTime(Timestamp SendTime) {
        this.SendTime = SendTime;
    }
    
}

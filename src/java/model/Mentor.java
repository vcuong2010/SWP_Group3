/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TGDD
 */
public class Mentor {
    String Achivement, Description, status, fullname, Avatar;
    int id, CvID;

    public Mentor(String status, String Achivement, String Description, int id, int CvID, String fullname, String Avatar) {
        this.Avatar = Avatar;
        this.fullname = fullname;
        this.status = status;
        this.Achivement = Achivement;
        this.Description = Description;
        this.id = id;
        this.CvID = CvID;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }
    
    public String getFullname() {
        return fullname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAchivement() {
        return Achivement;
    }

    public void setAchivement(String Achivement) {
        this.Achivement = Achivement;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCvID() {
        return CvID;
    }

    public void setCvID(int CvID) {
        this.CvID = CvID;
    }
}

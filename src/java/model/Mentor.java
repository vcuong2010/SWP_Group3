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
    String avatar, fullname, Achivement, Description;
    int id, CvID;

    public Mentor(String avatar, String fullname, String Achivement, String Description, int id, int CvID) {
        this.avatar = avatar;
        this.fullname = fullname;
        this.Achivement = Achivement;
        this.Description = Description;
        this.id = id;
        this.CvID = CvID;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

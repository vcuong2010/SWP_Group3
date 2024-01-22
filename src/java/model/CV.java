/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author TGDD
 */
public class CV {
    int id;
    String ProfessionIntroduction;
    String ServiceDescription;
    ArrayList<Skill> skills = new ArrayList();

    public CV(int id, String ProfessionIntroduction, String ServiceDescription) {
        this.id = id;
        this.ProfessionIntroduction = ProfessionIntroduction;
        this.ServiceDescription = ServiceDescription;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfessionIntroduction() {
        return ProfessionIntroduction;
    }

    public void setProfessionIntroduction(String ProfessionIntroduction) {
        this.ProfessionIntroduction = ProfessionIntroduction;
    }

    public String getServiceDescription() {
        return ServiceDescription;
    }

    public void setServiceDescription(String ServiceDescription) {
        this.ServiceDescription = ServiceDescription;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author admin
 */
public class PlanDetails {
    private int pdid;
    private ProductionPlanHeader phid;
    private Salaries sid;
    private Date date;
    private int quantity;

    public int getPdid() {
        return pdid;
    }

    public void setPdid(int pdid) {
        this.pdid = pdid;
    }

    public ProductionPlanHeader getPhid() {
        return phid;
    }

    public void setPhid(ProductionPlanHeader phid) {
        this.phid = phid;
    }

    public Salaries getSid() {
        return sid;
    }

    public void setSid(Salaries sid) {
        this.sid = sid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
}

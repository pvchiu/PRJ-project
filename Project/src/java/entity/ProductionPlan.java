/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author sonnt-local
 */
public class ProductionPlan {
    private int id;
    private String name;
    private Date start;
    private Date end;
    private Department dept;
    private Product pid;
    private ProductionPlanHeader phid;
    private ArrayList<ProductionPlanHeader> headers = new ArrayList<>();

    public Product getPid() {
        return pid;
    }

    public void setPid(Product pid) {
        this.pid = pid;
    }

    public ProductionPlanHeader getPhid() {
        return phid;
    }

    public void setPhid(ProductionPlanHeader phid) {
        this.phid = phid;
    }

    

    public ArrayList<ProductionPlanHeader> getHeader() {
        return headers;
    }

    public void setHeader(ArrayList<ProductionPlanHeader> header) {
        this.headers = header;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
    
}

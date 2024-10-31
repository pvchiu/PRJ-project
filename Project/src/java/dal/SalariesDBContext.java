/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Department;
import entity.Employee;
import entity.Salaries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class SalariesDBContext extends DBContext<Salaries>{
    
    public ArrayList<Salaries> get(String salarys) {
        
        ArrayList<Salaries> salary = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT [sid]\n"
                + "      ,[slevel]\n"
                + "      ,[salary]\n"
                + "  FROM [Salaries]\n"
                + "WHERE [type] = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, salarys);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Salaries s = new Salaries();
                s.setId(rs.getByte("sid"));
                s.setSlevel(rs.getNString("slevel"));
                s.setSalary(rs.getDouble("salary"));
                salary.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return salary;
    }

    @Override
    public void insert(Salaries model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Salaries model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Salaries model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Salaries> list() {
        String sql = "SELECT * from  Salaries";
        ArrayList<Salaries> salaries = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Salaries s = new Salaries();
                s.setId(rs.getInt("sid"));
                s.setSlevel(rs.getNString("slevel"));
                s.setSalary(rs.getDouble("salary"));
                
                salaries.add(s);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return salaries;
    }

    @Override
    public Salaries get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

}

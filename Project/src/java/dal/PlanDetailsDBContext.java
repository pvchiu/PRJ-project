/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Department;
import entity.Employee;
import entity.PlanDetails;
import entity.ProductionPlanHeader;
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
public class PlanDetailsDBContext extends DBContext<PlanDetails> {

    @Override
    public void insert(PlanDetails model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(PlanDetails model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(PlanDetails model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PlanDetails> list() {
        ArrayList<PlanDetails> pds = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "select * from PlanDetails";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PlanDetails pl = new PlanDetails();
                pl.setPdid(rs.getByte("pdid"));
                pl.setDate(rs.getDate("date"));
                pl.setQuantity(rs.getByte("quantity"));

                ProductionPlanHeader ph = new ProductionPlanHeader();
                
                ph.setId(rs.getByte("phid"));
                pl.setPhid(ph);

                Salaries s = new Salaries();
                s.setId(rs.getByte("sid"));
                pl.setSid(s);

                pds.add(pl);
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
        return pds;
    }

    @Override
    public PlanDetails get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

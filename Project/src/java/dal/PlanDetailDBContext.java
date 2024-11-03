/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.ProductionPlanDetail;
import entity.ProductionPlanHeader;
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
public class PlanDetailDBContext extends DBContext<ProductionPlanDetail> {

    @Override
    public void insert(ProductionPlanDetail model) {
        String sql = "INSERT INTO [PlanDetails]\n"
                + "           ([phid]\n"
                + "           ,[sid]\n"
                + "           ,[date]\n"
                + "           ,[quantity])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getHeader().getId());
            stm.setInt(2, model.getSid());
            stm.setDate(3, model.getDate());
            stm.setInt(4, model.getQuantity());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    @Override
    public void update(ProductionPlanDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(ProductionPlanDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ProductionPlanDetail> list() {
        String sql = "SELECT [pdid]\n"
                + "      ,[phid]\n"
                + "      ,[sid]\n"
                + "      ,[date]\n"
                + "      ,[quantity]\n"
                + "  FROM [PlanDetails]";
        ArrayList<ProductionPlanDetail> details= new ArrayList<>();
        PreparedStatement stm=null;
        try {
            stm= connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                ProductionPlanDetail detail=new ProductionPlanDetail();
                detail.setDate(rs.getDate("date"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setSid(rs.getInt("sid"));
                ProductionPlanHeader headers= new ProductionPlanHeader();
                headers.setId(rs.getInt("phid"));
                detail.setHeader(headers);
                details.add(detail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        return details;
    }

    @Override
    public ProductionPlanDetail get(int id) {
        String sql = "SELECT [pdid], [phid], [sid], [date], [quantity] FROM [PlanDetails] WHERE pdid = ?";
    PreparedStatement stm = null;
    ProductionPlanDetail detail = null;
    
    try {
        stm = connection.prepareStatement(sql);
        stm.setInt(1, id);  // Set the `pdid` value
        ResultSet rs = stm.executeQuery();
        
        if (rs.next()) {
            detail = new ProductionPlanDetail();
            detail.setDate(rs.getDate("date"));
            detail.setQuantity(rs.getInt("quantity"));
            detail.setSid(rs.getInt("sid"));
            
            ProductionPlanHeader headers = new ProductionPlanHeader();
            headers.setId(rs.getInt("phid"));
            detail.setHeader(headers);
        }
    } catch (SQLException ex) {
        Logger.getLogger(PlanDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (stm != null) stm.close();
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    return detail;
    }

    
}


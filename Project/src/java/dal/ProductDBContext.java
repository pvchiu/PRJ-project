/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Products;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class ProductDBContext extends DBContext<Products> {

    @Override
    public void insert(Products model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Products model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Products model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Products> list() {
        ArrayList<Products> pts = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT [pid]\n"
                    + "      ,[pname]\n"
                    + "      ,[estimation]\n"
                    + "  FROM [Products]";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setPid(rs.getInt("pid"));
                p.setPname(rs.getNString("pname"));
                p.setEstimation(rs.getFloat("estimation"));
                

                pts.add(p);

            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                stm.close();
                connection.close();
//if (stm != null) {
//                stm.close();
//            }
//            if (connection != null) {
//                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pts;
    }

    @Override
    public Products get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Shift;
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
public class ShiftDBContext extends DBContext<Shift>{

    @Override
    public void insert(Shift model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Shift model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Shift model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Shift> list() {
        ArrayList<Shift> shift= new ArrayList<>();
        PreparedStatement stm = null;
            String sql = "SELECT [sid]\n"
                    + "      ,[sname]\n"
                    + "      ,[starttime]\n"
                    + "      ,[endtime]\n"
                    + "  FROM [Shifts]";
        try {
            
           
            stm= connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Shift s = new Shift();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setStart(rs.getTime("starttime"));
                s.setEnd(rs.getTime("endtime"));
                shift.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShiftDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ShiftDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return shift;
    }

    @Override
    public Shift get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

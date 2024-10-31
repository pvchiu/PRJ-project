/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Department;
import entity.Employee;
import entity.Salaries;
import java.sql.Date;
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
public class EmployeeDBContext extends DBContext<Employee> {

    public ArrayList<Employee> search(Integer id, String name, Boolean gender, Date from, Date to, String address,String phonenumber, Integer did) {
        String sql = "SELECT e.eid, e.ename, e.gender, e.address,e.phonenumber, e.dob, d.did, d.dname, s.salary FROM Employees e "
                + "INNER JOIN Departments d ON d.did = e.did "
                + "INNER JOIN Salaries s ON s.sid = e.sid "
                + "WHERE (1=1)";

        ArrayList<Employee> emps = new ArrayList<>();
        ArrayList<Object> paramValues = new ArrayList<>();

        if (id != null) {
            sql += " AND e.eid = ?";
            paramValues.add(id);
        }

        if (name != null) {
            sql += " AND e.ename LIKE '%' + ? + '%'";
            paramValues.add(name);
        }

        if (gender != null) {
            sql += " AND e.gender = ?";
            paramValues.add(gender);
        }

        if (address != null) {
            sql += " AND e.address LIKE '%' + ? + '%'";
            paramValues.add(address);
        }
        if (phonenumber != null) { // Thêm điều kiện lọc cho số điện thoại
        sql += " AND e.phonenumber LIKE '%' + ? + '%'";
        paramValues.add(phonenumber);
    }


        if (from != null) {
            sql += " AND e.dob >= ?";
            paramValues.add(from);
        }

        if (to != null) {
            sql += " AND e.dob <= ?";
            paramValues.add(to);
        }

        if (did != null) {
            sql += " AND e.did = ?";
            paramValues.add(did);
        }

        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            for (int i = 0; i < paramValues.size(); i++) {
                stm.setObject((i + 1), paramValues.get(i));
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getNString("ename"));
                e.setGender(rs.getBoolean("gender")); // Set gender
                e.setDob(rs.getDate("dob")); // Set date of birth
                e.setAddress(rs.getString("address"));
                e.setPhonenumber(rs.getNString("phonenumber"));

                Department d = new Department();
                d.setId(rs.getInt("did"));
                d.setName(rs.getString("dname"));
                e.setDid(d);

                Salaries s = new Salaries();
                s.setSalary(rs.getDouble("salary"));
                e.setSid(s);

                emps.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return emps;
    }

    @Override
    public void insert(Employee model) {
        
        try {
            String sql_insert = ""
                + "INSERT INTO [Employees]\n"
                + "           ([ename]\n"
                + "           ,[gender]\n"
                + "           ,[dob]\n"
                + "           ,[address]\n"
                + "           ,[phonenumber]\n"
                + "           ,[did]\n"
                + "           ,[sid]\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)\n";
        String sql_getEid = "SELECT @@IDENTITY as eid";
        PreparedStatement stm_insert;
        PreparedStatement stm_getEid;
            connection.setAutoCommit(false);
            stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setNString(1, model.getName());
            stm_insert.setBoolean(2, model.isGender());
            stm_insert.setDate(3, (Date) model.getDob());
            stm_insert.setNString(4, model.getAddress());
            stm_insert.setNString(5, model.getPhonenumber());
            stm_insert.setInt(6, model.getDid().getId());
            stm_insert.setInt(7, model.getSid().getId());
            stm_insert.executeUpdate();
            stm_getEid = connection.prepareStatement(sql_getEid);
            ResultSet rs = stm_getEid.executeQuery();
            if (rs.next()) {
                model.setId(rs.getInt("eid"));
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public static void main(String[] args) {
        EmployeeDBContext e = new EmployeeDBContext();
        System.out.println(e.list().size());
    }
    @Override
    public void update(Employee model) {
        String sql_update = "UPDATE [Employees] SET ename = ?, gender = ?, dob = ?, address = ?, phonenumber = ?, did = ?, sid = ? WHERE eid = ?";
        PreparedStatement stm_update = null;
        
        try {
            connection.setAutoCommit(false);
            stm_update = connection.prepareStatement(sql_update);
            stm_update.setString(1, model.getName());
            stm_update.setBoolean(2, model.isGender());
            stm_update.setDate(3, (Date) model.getDob());
            stm_update.setString(4, model.getAddress());
            stm_update.setString(5, model.getPhonenumber());
            stm_update.setInt(6, model.getDid().getId());
            stm_update.setInt(7, model.getSid().getId());
            stm_update.setInt(8, model.getId());
            
            stm_update.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Employee model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Employee> list() {

        ArrayList<Employee> employee = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT e.eid, e.ename, e.phonenumber, e.[address], e.dob, e.gender, d.dname, s.salary "
                    + "FROM Employees e "
                    + "JOIN Departments d ON e.did = d.did "
                    + "JOIN Salaries s ON s.sid = e.sid";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getNString("ename"));
                e.setPhonenumber(rs.getNString("phonenumber"));
                e.setAddress(rs.getNString("address"));
                e.setGender(rs.getBoolean("gender"));
                e.setDob(rs.getDate("dob"));

                Department d = new Department();
                d.setName(rs.getNString("dname"));
                e.setDid(d);

                Salaries s = new Salaries();
                s.setSalary(rs.getDouble("salary"));
                e.setSid(s);

                employee.add(e);
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
        return employee;
    }

    @Override
    public Employee get(int id) {
        Employee e = null;
        PreparedStatement stm = null;
        try {
            String sql = "SELECT e.eid, e.ename, e.gender, e.dob, e.phonenumber, e.[address], d.did, d.dname, "
                    + "s.salary FROM Employees e "
                    + "JOIN Departments d ON d.did = e.did "
                    + "JOIN Salaries s ON s.sid = e.sid "
                    + "WHERE e.eid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                e = new Employee();
                e.setId(rs.getInt("eid"));
                e.setName(rs.getNString("ename"));
                e.setPhonenumber(rs.getNString("phonenumber"));
                e.setAddress(rs.getNString("address"));
                e.setGender(rs.getBoolean("gender")); // Set gender
                e.setDob(rs.getDate("dob")); // Set date of birth

                // Thiết lập thông tin phòng ban
                Department d = new Department();
                d.setId(rs.getInt("did")); // lấy ID phòng ban
                d.setName(rs.getNString("dname"));
                e.setDid(d);

                // Thiết lập thông tin lương
                Salaries s = new Salaries();
                s.setId(rs.getInt("sid")); // lấy ID lương
                s.setSalary(rs.getDouble("salary"));
                e.setSid(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return e;
    }

}

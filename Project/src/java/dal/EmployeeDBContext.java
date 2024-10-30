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
import java.util.Date;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Employee model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.employee;

import dal.DepartmentDBContext;
import dal.EmployeeDBContext;
import dal.SalariesDBContext;
import entity.Department;
import entity.Employee;
import entity.Salaries;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class EmployeeCreate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy tham số từ request
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeDBContext dbEmployee = new EmployeeDBContext();
        DepartmentDBContext dbDept = new DepartmentDBContext();
        SalariesDBContext dbSalary = new SalariesDBContext();
        

        // Lấy danh sách phòng ban và mức lương từ database
        ArrayList<Department> departments = dbDept.list();
        ArrayList<Employee> emps = dbEmployee.list();
        ArrayList<Salaries> salaries = dbSalary.list();

        // Lưu vào request để hiển thị trong view
        request.setAttribute("emps", emps);
        request.setAttribute("departments", departments);
        request.setAttribute("salaries", salaries);
        request.getRequestDispatcher("../view/employee/create.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeDBContext dbEmployee = new EmployeeDBContext();

        String raw_name = request.getParameter("name");
        String raw_gender = request.getParameter("gender");
        String raw_dob = request.getParameter("dob");
        String raw_address = request.getParameter("address");
        String raw_phonenumber = request.getParameter("phonenumber");

        String raw_did = request.getParameter("did");
        String raw_sid = request.getParameter("sid");

        // Xử lý và chuyển đổi dữ liệu
        String name = raw_name;
        Boolean gender = raw_gender != null && raw_gender.equals("male");
        Date dob = (raw_dob != null && !raw_dob.isBlank()) ? Date.valueOf(raw_dob) : null;
        String address = raw_address;
        String phonenumber = raw_phonenumber;

        // Tạo đối tượng Employee
        Employee employee = new Employee();

        employee.setName(name);
        employee.setGender(gender);
        employee.setDob(dob);
        employee.setAddress(address);
        employee.setPhonenumber(phonenumber);

        Department d = new Department();
        d.setId(Integer.parseInt(raw_did));
        employee.setDid(d);

        Salaries s = new Salaries();
        s.setId(Integer.parseInt(raw_sid));
        employee.setSid(s);

        dbEmployee.insert(employee);
        response.sendRedirect("list");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

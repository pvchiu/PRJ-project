/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.employee;

import dal.DepartmentDBContext;
import dal.EmployeeDBContext;
import dal.ProductDBContext;
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
import java.util.ArrayList;
import java.sql.Date;


/**
 *
 * @author admin
 */
public class EmployeeListController extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        EmployeeDBContext dbe = new EmployeeDBContext();
        DepartmentDBContext dbDept = new DepartmentDBContext();
        SalariesDBContext dbSalary = new SalariesDBContext();

        // Lấy tham số từ request để lọc danh sách nhân viên
        String raw_id = request.getParameter("id");
        String raw_name = request.getParameter("name");
        String raw_gender = request.getParameter("gender");
        String raw_address = request.getParameter("address");
        String raw_phonenumber = request.getParameter("phonenumber");
        String raw_dobFrom = request.getParameter("dobFrom");
        String raw_dobTo = request.getParameter("dobTo");
        String raw_did = request.getParameter("did"); // Nếu cần lọc theo department

        // Xử lý và chuyển đổi dữ liệu
        Integer id = (raw_id != null && !raw_id.isBlank()) ? Integer.parseInt(raw_id) : null;
        String name = raw_name;
        Boolean gender = (raw_gender != null && !raw_gender.equals("both")) ? raw_gender.equals("male") : null;
        String address = raw_address;
        String phonenumber = raw_phonenumber;
        Date dobFrom = (raw_dobFrom != null && !raw_dobFrom.isBlank()) ? Date.valueOf(raw_dobFrom) : null;
        Date dobTo = (raw_dobTo != null && !raw_dobTo.isBlank()) ? Date.valueOf(raw_dobTo) : null;
        Integer did = (raw_did != null && !raw_did.equals("-1")) ? Integer.parseInt(raw_did) : null; // Lọc theo phòng ban

        // Lấy danh sách nhân viên theo điều kiện lọc
        ArrayList<Employee> emps = dbe.search(id, name, gender, dobFrom, dobTo, address, phonenumber, did);
        // Lấy danh sách phòng ban
        ArrayList<Department> depts = dbDept.list();
        // Lấy danh sách lương
        ArrayList<Salaries> sals = dbSalary.list();

        // Đưa vào request để hiển thị trong view
        request.setAttribute("emps", emps);
        request.setAttribute("depts", depts);
        request.setAttribute("sals", sals);

        request.getRequestDispatcher("../view/employee/list.jsp").forward(request, response);
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

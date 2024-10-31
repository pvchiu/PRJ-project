/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.employee;


import controller.BaseRBACController;

import dal.DepartmentDBContext;
import dal.EmployeeDBContext;
import dal.SalariesDBContext;
import entity.Department;
import entity.Employee;
import entity.Salaries;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import model.auth.User;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class EmployeeCreateController extends BaseRBACController{

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User account) throws ServletException, IOException {
        String raw_name = request.getParameter("name");
    String raw_dob = request.getParameter("dob");
    String raw_gender = request.getParameter("gender");
    String raw_address = request.getParameter("address");
    String raw_did = request.getParameter("did");
    String raw_phonenumber = request.getParameter("phonenumber"); // Thêm số điện thoại
    String raw_sid = request.getParameter("sid"); // Thêm ID lương

    // Xác thực dữ liệu
    StringBuilder errors = new StringBuilder();
    if (raw_name == null || raw_name.trim().isEmpty()) {
        errors.append("Name is required. ");
    }
    if (raw_dob == null || raw_dob.trim().isEmpty()) {
        errors.append("Date of birth is required. ");
    }
    if (raw_did == null || raw_did.trim().isEmpty()) {
        errors.append("Department is required. ");
    }
    if (raw_sid == null || raw_sid.trim().isEmpty()) {
        errors.append("Salary ID is required. ");
    }
    if (raw_phonenumber == null || raw_phonenumber.trim().isEmpty()) {
        errors.append("Phone number is required. ");
    }

    if (errors.length() > 0) {
        response.getWriter().println(errors.toString());
        return; // Ngừng xử lý nếu có lỗi
    }

    // Liên kết đối tượng
    Employee e = new Employee();
    e.setName(raw_name);
    e.setGender("male".equalsIgnoreCase(raw_gender)); // Chuyển đổi giới tính
    e.setDob(Date.valueOf(raw_dob));
    e.setAddress(raw_address);
    e.setPhonenumber(raw_phonenumber); // Thiết lập số điện thoại

    // Thiết lập phòng ban
    Department d = new Department();
    d.setId(Integer.parseInt(raw_did));
    e.setDid(d);

    // Thiết lập thông tin lương
    Salaries s = new Salaries();
    s.setId(Integer.parseInt(raw_sid)); // Lấy ID lương từ yêu cầu
    e.setSid(s);

//    e.setCreatedby(account); // Gán người tạo

    // Lưu dữ liệu
    EmployeeDBContext db = new EmployeeDBContext();
    db.insert(e);
    // Phản hồi cho người dùng
    response.getWriter().println("New Eid: " + e.getId());
}

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User account) throws ServletException, IOException {
        DepartmentDBContext dbDepartment = new DepartmentDBContext();
    SalariesDBContext dbSalary = new SalariesDBContext(); // Giả định bạn đã có một lớp SalaryDBContext để quản lý lương
    ArrayList<Department> depts = null; // Khai báo mảng phòng ban
    ArrayList<Salaries> salaries = null; // Khai báo mảng lương
    
    request.setAttribute("depts", depts); // Gán danh sách phòng ban vào thuộc tính request
    request.setAttribute("salaries", salaries); // Gán danh sách lương vào thuộc tính request
    request.getRequestDispatcher("../view/employee/create.jsp").forward(request, response); // Chuyển tiếp đến trang tạo nhân viên
    }

    

    
    
    
}

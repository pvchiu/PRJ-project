/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.productionPlan;

import dal.DepartmentDBContext;
import dal.ProductDBContext;
import dal.ProductionPlanDBContext;

import entity.Department;
import entity.Product;
import entity.ProductionPlan;
import entity.ProductionPlanHeader;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;


/**
 *
 * @author sonnt-local hand-some
 */
public class ProductionPlanCreateController extends HttpServlet {
   
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DepartmentDBContext dbDept = new DepartmentDBContext();
        ProductDBContext dbProduct = new ProductDBContext();
        
        request.setAttribute("depts", dbDept.get("Production"));
        request.setAttribute("products", dbProduct.list());
        
        request.getRequestDispatcher("../view/productionplan/create.jsp").forward(request, response);
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
        ProductionPlan plan = new ProductionPlan();
plan.setName(request.getParameter("name"));

// Vérifiez la date de début
String startDate = request.getParameter("from");
if (startDate != null && !startDate.isEmpty()) {
    plan.setStart(Date.valueOf(startDate));
} else {
    response.getWriter().println("Start date is required!");
    return;
}

// Vérifiez la date de fin
String endDate = request.getParameter("to");
if (endDate != null && !endDate.isEmpty()) {
    plan.setEnd(Date.valueOf(endDate));
} else {
    response.getWriter().println("End date is required!");
    return;
}

// Vérifiez l'identifiant du département
String did = request.getParameter("did");
if (did != null && !did.isEmpty()) {
    Department d = new Department();
    d.setId(Integer.parseInt(did));
    plan.setDept(d);
} else {
    response.getWriter().println("Department ID is required!");
    return;
}

// Vérifiez la présence des produits
String[] pids = request.getParameterValues("pid");
if (pids != null) {
    for (String pid : pids) {
        if (pid != null && !pid.isEmpty()) {
            Product p = new Product();
            p.setId(Integer.parseInt(pid));

            ProductionPlanHeader header = new ProductionPlanHeader();
            header.setProduct(p);

            String raw_quantity = request.getParameter("quantity" + pid);
            String raw_effort = request.getParameter("effort" + pid);

            // Validation pour quantité et effort
            header.setQuantity((raw_quantity != null && !raw_quantity.isEmpty()) ? Integer.parseInt(raw_quantity) : 0);
            header.setEstimatedeffort((raw_effort != null && !raw_effort.isEmpty()) ? Float.parseFloat(raw_effort) : 0);

            // Ajout du header seulement si la quantité et l'effort sont valides
            if (header.getQuantity() > 0 && header.getEstimatedeffort() > 0) {
                plan.getHeader().add(header);
            }
        }
    }
} else {
    response.getWriter().println("No products selected!");
    return;
}
        
        if(plan.getHeader().size() >0)
        {
            ProductionPlanDBContext db = new ProductionPlanDBContext();
            db.insert(plan);
            response.getWriter().println("your plan has been added!");
            response.sendRedirect("../productionplan/list");
        }
        else
        {
            response.getWriter().println("your plan does not have any headers! it is not allowed!");
        }
        
    }
   

}

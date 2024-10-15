/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.product;

import controller.BaseRequiredAuthenticationController;
import dal.ProductDBContext;
import entity.Products;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
@WebServlet(name="ProductsListController", urlPatterns={"/product/list"})
public class ProductListController extends BaseRequiredAuthenticationController {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User account)
    throws ServletException, IOException {
        ProductDBContext db = new ProductDBContext();
        ArrayList<Products> pts = db.list();
        request.setAttribute("pts", pts);
        request.getRequestDispatcher("../view/product/list.jsp").forward(request, response);
        
    } 

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        processRequest(req, resp,account);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        processRequest(req, resp,account);
    }

}

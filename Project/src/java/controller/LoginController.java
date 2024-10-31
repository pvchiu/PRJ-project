/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.auth.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.auth.User;

/**
 *
 * @author admin
 */
public class LoginController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param_user = req.getParameter("username");//user input
        String param_pass = req.getParameter("password");
        
        String username = req.getServletContext().getInitParameter("username");  //correct username
        String password = req.getServletContext().getInitParameter("password");
        if(param_user.equals(username) && param_pass.equals(password))
        {
            
            User account = new User();
            account.setUsername(username);
            account.setPassword(password);
            account.setDisplayname("mr A");
            req.getSession().setAttribute("account", account);
//            resp.sendRedirect("home");
        }
        else
        {
            resp.getWriter().println("login failed!");
        }
        
        String url = this.getInitParameter("url");
        resp.getWriter().println(url);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.html").forward(req, resp);
    }
    
}

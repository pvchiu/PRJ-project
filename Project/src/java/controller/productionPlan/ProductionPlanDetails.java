/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.productionPlan;

import dal.PlanDetailDBContext;
import dal.ProductionPlanDBContext;
import dal.ShiftDBContext;
import entity.ProductionPlan;
import entity.ProductionPlanDetail;
import entity.ProductionPlanHeader;
import entity.Shift;
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
public class ProductionPlanDetails extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductionPlanDetails</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductionPlanDetails at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       int plid = Integer.parseInt(request.getParameter("plid"));
    ProductionPlanDBContext dbPlan = new ProductionPlanDBContext();
    ProductionPlan plan = dbPlan.get(plid);
    
    if (plan == null) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Production Plan not found");
        return;
    }

    ArrayList<Date> datePlan = new ArrayList<>();
    Date start = plan.getStart();
    Date end = plan.getEnd();
    
    long milisecondsinDay = 24*60*60*1000;
        
        while(!start.after(end)){
            datePlan.add(start);
            start=new Date(milisecondsinDay+start.getTime());
        }

    ShiftDBContext dbShift = new ShiftDBContext();
    ArrayList<Shift> shifts = dbShift.list();
    
    PlanDetailDBContext dbDetail = new PlanDetailDBContext();
    ArrayList<ProductionPlanDetail> details = dbDetail.list();

    request.setAttribute("details", details);
    request.setAttribute("shifts", shifts);
    request.setAttribute("datePlan", datePlan);
    request.setAttribute("plan", plan);
    
    request.getRequestDispatcher("../view/productionplan/detail.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] dates= request.getParameterValues("date");
        PlanDetailDBContext dbDetail=new PlanDetailDBContext();
        for(String d: dates){
            
            String[] hids= request.getParameterValues("hid"+d);
            String[] sids= request.getParameterValues("sid"+d);
            for(String s: sids){
            for(String h: hids){
                
                    
                    String raw_quantity= request.getParameter("quantity"+h+s+d);  
                    if(raw_quantity != null&&!raw_quantity.isEmpty()){
                        ProductionPlanDetail detail=new ProductionPlanDetail();
                        int hid=Integer.parseInt(h);
                        Date date= Date.valueOf(d);
                        int sid=Integer.parseInt(s);
                        int quantity= Integer.parseInt(raw_quantity);
                        detail.setSid(sid);
                        ProductionPlanHeader header= new ProductionPlanHeader();
                        header.setId(hid);
                        detail.setHeader(header);
                        detail.setDate(date);
                        detail.setQuantity(quantity);
                        dbDetail.insert(detail);
                        
                       
                        
                    }
                   
                }
                
            }
            
        }
        response.sendRedirect("list");
        
        
        
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

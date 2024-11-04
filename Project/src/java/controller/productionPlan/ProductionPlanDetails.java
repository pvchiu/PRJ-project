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
    // Get the production plan ID from the request parameters
    int plid = Integer.parseInt(request.getParameter("plid"));
    
    // Retrieve the production plan using the specified ID
    ProductionPlanDBContext dbPlan = new ProductionPlanDBContext();
    ProductionPlan plan = dbPlan.get(plid);

    // If the production plan does not exist, send a 404 error
    if (plan == null) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Production Plan not found");
        return;
    }

    // Initialize an ArrayList to store dates between start and end dates
    ArrayList<Date> datePlan = new ArrayList<>();
    Date start = plan.getStart();
    Date end = plan.getEnd();
    
    // Define the number of milliseconds in a day
    long millisecondsInDay = 24 * 60 * 60 * 1000;

    // Populate datePlan with each date from start to end
    while (!start.after(end)) {
        datePlan.add(start);
        start = new Date(millisecondsInDay + start.getTime());
    }

    // Retrieve the list of shifts from the database
    ShiftDBContext dbShift = new ShiftDBContext();
    ArrayList<Shift> shifts = dbShift.list();
    
    // Retrieve the list of production plan details
    PlanDetailDBContext dbDetail = new PlanDetailDBContext();
    ArrayList<ProductionPlanDetail> details = dbDetail.list();

    // Set attributes for the request to be used in the JSP page
    request.setAttribute("details", details);
    request.setAttribute("shifts", shifts);
    request.setAttribute("datePlan", datePlan);
    request.setAttribute("plan", plan);
    
    // Forward the request to the JSP page for rendering the production plan details
    request.getRequestDispatcher("../view/productionplan/detail.jsp").forward(request, response);
}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Retrieve the dates from the form submission
    String[] dates = request.getParameterValues("date");

    // Initialize the database context for inserting plan details
    PlanDetailDBContext dbDetail = new PlanDetailDBContext();

    // Loop through each date to process headers and shifts
    for (String d : dates) {
        
        // Retrieve header IDs and shift IDs associated with the current date
        String[] hids = request.getParameterValues("hid" + d);
        String[] sids = request.getParameterValues("sid" + d);

        // Loop through each shift
        for (String s : sids) {
            // Loop through each header
            for (String h : hids) {
                
                // Get the quantity value for the current header, shift, and date
                String raw_quantity = request.getParameter("quantity" + h + s + d);
                
                // Check if quantity is provided and not empty
                if (raw_quantity != null && !raw_quantity.isEmpty()) {
                    // Create a new ProductionPlanDetail object to insert into the database
                    ProductionPlanDetail detail = new ProductionPlanDetail();

                    // Parse and set header ID, date, shift ID, and quantity
                    int hid = Integer.parseInt(h);
                    Date date = Date.valueOf(d);
                    int sid = Integer.parseInt(s);
                    int quantity = Integer.parseInt(raw_quantity);
                    detail.setSid(sid);

                    // Set the header object with its ID
                    ProductionPlanHeader header = new ProductionPlanHeader();
                    header.setId(hid);
                    detail.setHeader(header);
                    
                    // Set date and quantity for the detail
                    detail.setDate(date);
                    detail.setQuantity(quantity);

                    // Insert the detail into the database
                    dbDetail.insert(detail);
                }
            }
        }
    }
    
    // Redirect to the production plan list page after saving
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

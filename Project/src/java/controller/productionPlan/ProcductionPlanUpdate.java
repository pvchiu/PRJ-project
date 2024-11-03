/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.productionPlan;

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
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author admin
 */
public class ProcductionPlanUpdate extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcductionPlanUpdate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcductionPlanUpdate at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        try {
        // Check if the 'plid' parameter is present
        String rawPlid = request.getParameter("plid");
        if (rawPlid == null || rawPlid.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing 'plid' parameter.");
            return;
        }
        
        // Parse 'plid' and retrieve the production plan
        int plid = Integer.parseInt(rawPlid);
        ProductionPlanDBContext dbPlan = new ProductionPlanDBContext();
        ProductionPlan plan = dbPlan.get(plid);

        // If plan is null, the specified ID was not found
        if (plan == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Production plan not found.");
            return;
        }

        // Set the production plan as a request attribute and forward to the update page
        request.setAttribute("plan", plan);
        request.getRequestDispatcher("../view/productionplan/update.jsp").forward(request, response);
    } catch (NumberFormatException e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid 'plid' format.");
    }
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductionPlan plan = new ProductionPlan();

// Retrieve the updated data from the request
        String rawId = request.getParameter("id");
        String rawDid = request.getParameter("did");
        String rawFrom = request.getParameter("from");
        String rawTo = request.getParameter("to");

// Validate ID and department ID before parsing
        if (rawId != null && !rawId.trim().isEmpty() && rawDid != null && !rawDid.trim().isEmpty()) {
            plan.setId(Integer.parseInt(rawId));
            plan.setName(request.getParameter("name"));
            plan.setStart(Date.valueOf(rawFrom));
            plan.setEnd(Date.valueOf(rawTo));

            // Set the department
            Department d = new Department();
            d.setId(Integer.parseInt(rawDid));
            plan.setDept(d);

            // Update headers based on submitted values
            String[] pids = request.getParameterValues("pid");
            ArrayList<ProductionPlanHeader> headers = new ArrayList<>();

            if (pids != null) { // Check if pids is not null
                for (String pid : pids) {
                    if (pid != null && !pid.trim().isEmpty()) { // Check if pid is valid
                        ProductionPlanHeader header = new ProductionPlanHeader();

                        // Create the Product object
                        String productName = request.getParameter("name" + pid);
                        String productDescription = request.getParameter("description" + pid);
                        Product product = new Product(Integer.parseInt(pid), productName, productDescription);

                        // Set the product in the header
                        header.setProduct(product);

                        String rawQuantity = request.getParameter("quantity" + pid);
                        String rawEffort = request.getParameter("effort" + pid);

                        // Validate quantity and effort before parsing
                        header.setQuantity(rawQuantity != null && !rawQuantity.trim().isEmpty() ? Integer.parseInt(rawQuantity) : 0);
                        header.setEstimatedeffort(rawEffort != null && !rawEffort.trim().isEmpty() ? Float.parseFloat(rawEffort) : 0);

                        // Only add headers that have quantity and effort greater than 0
                        if (header.getQuantity() > 0 && header.getEstimatedeffort() > 0) {
                            headers.add(header);
                        }
                    }
                }
            }

            plan.setHeader(headers);

            // Update the production plan in the database
            ProductionPlanDBContext dbPlan = new ProductionPlanDBContext();
            dbPlan.update(plan);

            // Redirect to the list page after updating
            response.sendRedirect("../view/productionplan/list.jsp");
        }
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

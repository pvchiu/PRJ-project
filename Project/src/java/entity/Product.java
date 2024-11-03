/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class Product {
    private int pid;
    private String name;
    private String description;

    public Product() {
    }

    public Product(int pid, String name, String description) {
        this.pid = pid;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    public int getId() {
        return pid;
    }

    public void setId(int id) {
        this.pid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}



//@Override
//    public ArrayList<ProductionPlanHeader> list() {
//        ArrayList<ProductionPlanHeader> pplist = new ArrayList<>();
//        PreparedStatement stm = null;
//        try {
//            String sql = "SELECT pl.plid,pl.plname, pl.startdate, pl.enddate, ph.quantity, p.pname, ph.estimatedeffort  FROM PlanHeaders ph\n"
//                    + "inner join Plans pl on pl.plid = ph.plid\n"
//                    + "inner join Products p on p.pid = ph.pid\n"
//                    + "where 1 = 1";
//
//            stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                ProductionPlanHeader ph = new ProductionPlanHeader();
//                ph.setQuantity(rs.getInt("quantity"));
//                ph.setEstimatedeffort(rs.getFloat("estimatedeffort"));
//
//                ProductionPlan pl = new ProductionPlan();
//                pl.setName(rs.getNString("plname"));
//                pl.setStart(rs.getDate("startdate"));
//                pl.setEnd(rs.getDate("enddate"));
//                pl.setId(rs.getInt("plid"));
//                ph.setPlan(pl);
//
//                Product p = new Product();
//                p.setName(rs.getNString("pname"));
//                ph.setProduct(p);
//
//                pplist.add(ph);
//
//            }
//
//        } catch (SQLException ex) {
//             Logger.getLogger(ProductionPlanHeaderDBContext.class.getName())
//                     .log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                stm.close();
//                connection.close();
//
//            } catch (SQLException ex) {
//                 Logger.getLogger(ProductionPlanHeaderDBContext.class.getName())
//                         .log(Level.SEVERE, null, ex);
//            }
//        }
//        return pplist;
//    }
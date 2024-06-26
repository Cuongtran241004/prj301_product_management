
package controller.product;

import controller.Action;
import controller.account.AddAccount;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Category;
import model.Product;
import model.dao.CategoryDAO;
import model.dao.ProductDAO;

/**
 *
 * @author Trần Quốc Cường
 */
public class UpdateProduct extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("login");
            
            String productId = request.getParameter("id");
            String productName = request.getParameter("name");            
            String brief = request.getParameter("brief");
            String unit = request.getParameter("unit");
            String price = request.getParameter("price");
            String discount = request.getParameter("discount");
            String typeId = request.getParameter("type");
            // Process Date by TimeStamp
            java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp postedDate = new java.sql.Timestamp(utilDate.getTime());

            CategoryDAO cateDao = new CategoryDAO(getServletContext());
            Category type = cateDao.getObjectById(typeId);

            // Set all product attributes
            Product product = new Product();
            ProductDAO dao = new ProductDAO(getServletContext());
            product.setProductId(productId);
            product.setProductName(productName);            
            product.setBrief(brief != null ? brief : "");
            product.setPostedDate(postedDate);
            product.setType(type);
            product.setAccount(account);
            product.setUnit(unit != null ? unit : "");
            product.setPrice(price != null ? Integer.parseInt(price) : 0);
            product.setDiscount(discount != null ? Integer.parseInt(discount) : 0);

            dao.updateRec(product);
            response.sendRedirect("MainController?action=" + Action.LIST_PRODUCT);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProduct.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import context.Navigation;
import context.Action;
import entities.Accounts;
import entities.Categories;
import entities.CategoriesBLO;
import entities.Products;
import entities.ProductsBLO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author ACER
 */
@MultipartConfig
public class ProductController extends HttpServlet {

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
        try {
            request.setCharacterEncoding("UTF-8");
            String action = request.getParameter("action");

            switch (action) {
                /**
                 * Product Request
                 */
                // Add new product
                case Action.ADD_PRODUCT:
                    addProduct(request, response);
                    break;

                // List all products
                case Action.LIST_PRODUCT:
                    listProduct(request, response);
                    break;

                // Update a product
                case Action.UPDATE_PRODUCT:
                    updateProduct(request, response);
                    break;

                // Delete a product
                case Action.DELETE_PRODUCT:
                    deleteProduct(request, response);
                    break;

                // Change image product
                case Action.CHANGE_IMG_PRODUCT:
                    changeImgProduct(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
        } catch (Exception ex) {
            Logger.getLogger(GuestController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Accounts account = (Accounts) request.getSession().getAttribute("login");

        String productId = request.getParameter("id");
        String productName = request.getParameter("name");

        String brief = request.getParameter("brief");
        String unit = request.getParameter("unit");
        String price = request.getParameter("price");
        String discount = request.getParameter("discount");
        String typeId = request.getParameter("type");
        String productImage = handleUploadFile(request, response);

        // Process Date by TimeStamp
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp postedDate = new java.sql.Timestamp(utilDate.getTime());

        CategoriesBLO cateDao = new CategoriesBLO();
        Categories type = cateDao.getObjectById(typeId);

        ProductsBLO proDao = new ProductsBLO();
        Products product = new Products();

        // If product is already existed, then render message for user
        if (proDao.getObjectById(productId) != null) {
            String msg = "Product is already exist!";
            request.setAttribute("productMsg", msg);
            request.getRequestDispatcher(Navigation.ADD_PRODUCT).forward(request, response);
        } else {
            product.setProductId(productId);
            product.setProductName(productName);
            product.setProductImage(productImage != null ? productImage : "");
            product.setBrief(brief != null ? brief : "");
            product.setPostedDate(postedDate);
            product.setTypeId(type);
            product.setAccount(account);
            product.setUnit(unit != null ? unit : "");
            product.setPrice(price != null ? Integer.parseInt(price) : 0);
            product.setDiscount(discount != null ? Integer.parseInt(discount) : 0);

            proDao.insertRec(product);
            response.sendRedirect("ProductController?action=" + Action.LIST_PRODUCT + "&cate=0");
        }

    }

    // Using this method to process upload product image
    private String handleUploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        // Create path components to save the file
        String path = request.getServletContext().getInitParameter("file");
        Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;

        try {
            out = new FileOutputStream(new File(path + "\\images\\sanPham" + File.separator + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

        } catch (FileNotFoundException fne) {

        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }

        }
        return filecontent != null ? "/images/sanPham/" + fileName.trim() : "";
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            String product = request.getParameter("product");

            ProductsBLO dao = new ProductsBLO();

            dao.deleteRec(product);

            response.sendRedirect("ProductController?action=" + Action.LIST_PRODUCT +"&cate=0");

        }
    }
    private void listProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String cateId = request.getParameter("cate");
        int cate = Integer.parseInt(cateId);
        List<Products> list = null;

        if (cate == 0) {
            list = new ProductsBLO().listAll();
        } else {
            list = new ProductsBLO().listByCategory(cate);
        }

        session.setAttribute("listProduct", list);

        response.sendRedirect(Navigation.LIST_PRODUCT);

    }
 private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Accounts account = (Accounts) session.getAttribute("login");

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

        CategoriesBLO cateDao = new CategoriesBLO();
        Categories type = cateDao.getObjectById(Integer.parseInt(typeId));

        // Set all product attributes
        Products product = new Products();
        ProductsBLO dao = new ProductsBLO();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setBrief(brief != null ? brief : "");
        product.setPostedDate(postedDate);
        product.setTypeId(type);
        product.setAccount(account);
        product.setUnit(unit != null ? unit : "");
        product.setPrice(price != null ? Integer.parseInt(price) : 0);
        product.setDiscount(discount != null ? Integer.parseInt(discount) : 0);

        dao.updateRec(product);
        response.sendRedirect("ProductController?action=" + Action.LIST_PRODUCT + "&cate=0");

    }
 
 private void changeImgProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String productId = request.getParameter("id");
        String productName = request.getParameter("name");
        String productImage = handleUploadFile(request, response);

        Products product = new Products();
        ProductsBLO dao = new ProductsBLO();

        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductImage(productImage);

        dao.updatePhoto(product);
        response.sendRedirect(Navigation.CHANGE_IMG_PRODUCT + "?product=" + productId);

    }
}

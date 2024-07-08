/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Products;
import entities.ProductsBLO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
public class UserController extends HttpServlet {

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
        String url = Navigation.INVALID;
        try {
            request.setCharacterEncoding("UTF-8");
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            switch (action) {
                /**
                 * Authen Request
                 */

                // Login
                case Action.LOGIN:
                    url = "Login";
                    break;

                // Logout    
                case Action.LOGOUT:
                    url = "Logout";
                    break;

                case Action.LIST_PRODUCT_BY_CATE_PUBLIC:
                    String cateId = request.getParameter("cate");
                    int cate = Integer.parseInt(cateId);
                    List<Products> list = new ProductsBLO().listAll();

                    if (cate == 0) {
                        list = new ProductsBLO().listAll();
                    } else {
                        list = new ProductsBLO().listByCategory(cate);
                    }

                    session.setAttribute("listProductPublic", list);
                   // url = "UserController?action=" + Action.LIST_PRODUCT_BY_CATE_PUBLIC + "&cate=" + cate + "#productView";
                   url = "";
                    break;
                default:
                    session.setAttribute("listProductPublic", new ProductsBLO().listAll());
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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

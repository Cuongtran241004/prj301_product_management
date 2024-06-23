/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.account.AddAccount;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.dao.AccountDAO;

/**
 *
 * @author ACER
 */
@MultipartConfig
public class MainController extends HttpServlet {

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

            switch (action) {
                // AUTHEN
                case Action.LOGIN:
                    url = "Login";
                    break;

                case Action.LOGOUT:
                    url = "Logout";
                    break;

                // ACCOUNT   
                case Action.ADD_ACCOUNT:
                    url = "AddAccount";
                    break;

                case Action.LIST_ACCOUNT:
                    url = "ListAccount";
                    break;

                case Action.UPDATE_ACCOUNT:
                    url = "UpdateAccount";
                    break;

                case Action.ISUSE_UPDATE_ACCOUNT:
                    url = "IsUseUpdateAccount";
                    break;

                case Action.DELETE_ACCOUNT:
                    url = "DeleteAccount";
                    break;

                // CATEGORY    
                case Action.ADD_CATEGORY:
                    url = "AddCategory";
                    break;

                case Action.LIST_CATEGORY:
                    url = "ListCategory";
                    break;

                case Action.UPDATE_CATEGORY:
                    url = "UpdateCategory";
                    break;

                case Action.DELETE_CATEGORY:
                    url = "DeleteCategory";
                    break;

                // PRODUCT    
                case Action.ADD_PRODUCT:
                    url = "AddProduct";
                    break;

                case Action.LIST_PRODUCT:
                    url = "ListProduct";
                    break;

                case Action.UPDATE_PRODUCT:
                    url = "UpdateProduct";
                    break;

                case Action.DELETE_PRODUCT:
                    url = "DeleteProduct";
                    break;

                case Action.CHANGE_IMG_PRODUCT:
                    url = "ChangeImgProduct";
                    break;
                    
                default:
                    throw new AssertionError();
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

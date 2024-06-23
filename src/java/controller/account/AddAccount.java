/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.account;

import controller.Action;
import controller.Navigation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.dao.AccountDAO;

/**
 *
 * @author ACER
 */
public class AddAccount extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            String account = request.getParameter("account");
            String pass = request.getParameter("pass");
            String lastName = request.getParameter("lastName");
            String firstName = request.getParameter("firstName");

            //--- SimpleDateFormat - Calendar ... Để convert String thành Date
            String ns = request.getParameter("birthday");
            Date birthday = Date.valueOf(ns);

            boolean gender = (request.getParameter("gender").equals("1")) ? true : false;
            String phone = request.getParameter("phone");

            boolean isUse = (request.getParameter("isUse") != null) ? true : false;
            int roleInSystem = (request.getParameter("roleInSystem").equals("1")) ? 1 : 2;

            // AccountDAO
            
            if (new AccountDAO().getObjectById(account) != null) {
                String msg = "Account is already exist!";
                request.setAttribute("accountMsg", msg);
                request.getRequestDispatcher(Navigation.ADD_ACCOUNT).forward(request, response);
            } else {
                // AccountDTO
                Account acc = new Account(account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem);

                //--- 4/- Gọi bussiness logic thi hành
                int result = new AccountDAO().insertRec(acc);
                //--- 5/- Xử lý trong trường hợp thành công

                response.sendRedirect("MainController?action=" + Action.LIST_ACCOUNT);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddAccount.class.getName()).log(Level.SEVERE, null, ex);
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

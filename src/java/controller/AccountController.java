/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import context.Navigation;
import context.Action;
import entities.Accounts;
import entities.AccountsBLO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
public class AccountController extends HttpServlet {

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
            /**
             * Account Request
             */
            // Add new account    
            switch (action) {
                case Action.ADD_ACCOUNT:
                    addAccount(request, response);
                    break;

                // List all accounts
                case Action.LIST_ACCOUNT:
                    listAccount(request, response);
                    break;

                // Update an account
                case Action.UPDATE_ACCOUNT:
                    updateAccount(request, response);
                    break;

                // Update isUse account 
                case Action.ISUSE_UPDATE_ACCOUNT:
                    isUseUpdate(request, response);
                    break;

                // Delete an account
                case Action.DELETE_ACCOUNT:
                    deleteAccount(request, response);
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

    private void addAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String account = request.getParameter("account");
        String pass = request.getParameter("pass");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        
        String ns = request.getParameter("birthday");
        Date birthday = Date.valueOf(ns);
        
        boolean gender = (request.getParameter("gender").equals("1")) ? true : false;
        String phone = request.getParameter("phone");
        
        boolean isUse = (request.getParameter("isUse") != null) ? true : false;
        String roleInSystem = request.getParameter("roleInSystem");

        // If account is already existed, then render message for user
        if (new AccountsBLO().getObjectById(account) != null) {
            String msg = "Account is already exist!";
            request.setAttribute("accountMsg", msg);
            request.getRequestDispatcher(Navigation.ADD_ACCOUNT).forward(request, response);
        } else {
            Accounts acc = new Accounts(account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem);
            int result = new AccountsBLO().insertRec(acc);

            // List all accounts 
            response.sendRedirect("AccountController?action=" + Action.LIST_ACCOUNT);
        }
    }
    
    private void deleteAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            String account = request.getParameter("account");
            new AccountsBLO().deleteRec(account);
            response.sendRedirect("AccountController?action=" + Action.LIST_ACCOUNT);
            
        }
    }
    
    private void isUseUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String account = request.getParameter("account");
        Accounts a = new AccountsBLO().getObjectById(account);

        // Update by call method of AccountDAO
        new AccountsBLO().updateIsUsed(a.getAccount(), !a.getIsUse());

        // List all accounts
       response.sendRedirect("AccountController?action=" + Action.LIST_ACCOUNT);
        
    }
    
    private void listAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        List<Accounts> list = new AccountsBLO().listAll();
        session.setAttribute("listAccount", list);
        response.sendRedirect(Navigation.LIST_ACCOUNT);      
    }

    private void updateAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String account = request.getParameter("account");
        String pass = request.getParameter("pass");
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        
        String ns = request.getParameter("birthday");
        Date birthday = Date.valueOf(ns);
        
        boolean gender = (request.getParameter("gender").equals("1")) ? true : false;
        String phone = request.getParameter("phone");
        
        boolean isUse = (request.getParameter("isUse") != null) ? true : false;
        String roleInSystem = request.getParameter("roleInSystem");
        
        Accounts upAcc = new AccountsBLO().getObjectById(account);
        
        upAcc.setPass(pass);
        upAcc.setLastName(lastName);
        upAcc.setFirstName(firstName);
        upAcc.setBirthday(birthday);
        upAcc.setGender(gender);
        upAcc.setPhone(phone);
        upAcc.setIsUse(isUse);
        upAcc.setRoleInSystem(roleInSystem);

        // Update account by call method of AccountDAO
        int result = new AccountsBLO().updateRec(upAcc);

        // List all accounts                                 
        response.sendRedirect("AccountController?action=" + Action.LIST_ACCOUNT);
        
    }
}

package controller.account;

import controller.Action;
import controller.Navigation;
import entities.Accounts;
import entities.AccountsBLO;
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

/**
 *
 * @author Trần Quốc Cường
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
            response.sendRedirect("MainController?action=" + Action.LIST_ACCOUNT);
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

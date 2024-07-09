/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import context.Navigation;
import context.Action;
import entities.Categories;
import entities.CategoriesBLO;
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
public class CategoryController extends HttpServlet {

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
                 * Category Request
                 */
                // Add new category
                case Action.ADD_CATEGORY:
                    addCategory(request, response);
                    break;

                // List all categories
                case Action.LIST_CATEGORY:
                    listCategory(request, response);
                    break;

                // Update a category
                case Action.UPDATE_CATEGORY:
                    updateCategory(request, response);
                    break;

                // Delete a category
                case Action.DELETE_CATEGORY:
                    deleteCategory(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void addCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String cateName = request.getParameter("cateName");
        String memo = request.getParameter("memo");

        Categories c = new Categories();
        c.setCategoryName(cateName);
        c.setMemo(memo);

        CategoriesBLO dao = new CategoriesBLO();

        // If category name is already existed, then render message for user
        if (dao.getCateByName(cateName) != null) {
            String msg = "Category name is already exist!";
            request.setAttribute("categoryMsg", msg);
            request.getRequestDispatcher(Navigation.ADD_CATEGORY).forward(request, response);
        } else {
            dao.insertRec(c);
            response.sendRedirect("CategoryController?action=" + Action.LIST_CATEGORY);
        }
    }

    private void listCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        CategoriesBLO dao = new CategoriesBLO();
        List<Categories> list = dao.listAll();
        session.setAttribute("listCategory", list);

        response.sendRedirect(Navigation.LIST_CATEGORY);

    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("typeId");
        String name = request.getParameter("cateName");
        String memo = request.getParameter("memo");

        CategoriesBLO dao = new CategoriesBLO();
        // If category is already existed, then render message for user
        if (dao.getCateByName(name) != null) {
            String msg = "Category name is already exist!";
            request.setAttribute("categoryUpdateMsg", msg);
            request.getRequestDispatcher(Navigation.UPDATE_CATEGORY + "?category=" + type).forward(request, response);

        } else {
            Categories updateCate = dao.getObjectById(Integer.parseInt(type));
            updateCate.setCategoryName(name);
            updateCate.setMemo(memo != null ? memo : "");

            int result = dao.updateRec(updateCate);
            response.sendRedirect("CategoryController?action=" + Action.LIST_CATEGORY);
        }

    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String cate = request.getParameter("category");

        CategoriesBLO dao = new CategoriesBLO();
        dao.deleteRec(cate);

        response.sendRedirect("CategoryController?action=" + Action.LIST_CATEGORY);

    }
}

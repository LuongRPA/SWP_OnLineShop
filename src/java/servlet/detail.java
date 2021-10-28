/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import context.DBContext;
import dao.DAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Product;

/**
 *
 * @author sony
 */
public class detail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stringPid = request.getParameter("pid");
        DBContext db = new DBContext();
        DAO dao = new DAO(db);
        int pid = 0;
        try {
            pid = Integer.parseInt(stringPid);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Product p = dao.getProductByID(pid);
        request.setAttribute("product", p);
        request.getRequestDispatcher("detail.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBContext db = new DBContext();
        DAO dao = new DAO(db);
        HttpSession session = request.getSession();
        
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("login");
            return;
        }
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int pID = Integer.parseInt(request.getParameter("pid"));
        int price = Integer.parseInt(request.getParameter("price"));
        int totalPrice = price * quantity;
        dao.Buy(quantity, totalPrice, pID, username);
        request.getRequestDispatcher("thank.jsp").forward(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

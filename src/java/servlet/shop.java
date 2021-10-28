/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import context.DBContext;
import dao.DAO;
import java.io.IOException;
import java.util.List;
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
public class shop extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          processRequest(request, response);
        try {
            DBContext db = new DBContext();
            DAO dao = new DAO(db);
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            //-----------
            //dem so dong trong bang
            int rowCount = dao.getCount();
            //----
            String page_raw = request.getParameter("txtPage");
            page_raw = (page_raw == null) ? "1" : page_raw;
            //------
            int pageIndex = Integer.parseInt(page_raw);
            //lay max page
            int maxPage = rowCount / 6 + (rowCount % 6 > 0 ? 1 : 0);

            List<Product> list = dao.getListProduct(pageIndex);
            request.setAttribute("listProduct", list);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("pageIndex", pageIndex);
            request.getRequestDispatcher("shop.jsp").forward(request, response);
        } catch (Exception e) {
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

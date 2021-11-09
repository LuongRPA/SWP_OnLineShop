/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import context.DBContext;
import DaoImplements.DAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Order;

/**
 *
 * @author sony
 */
public class myorder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBContext db = new DBContext();
        DAO dao = new DAO(db);
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        int rowCount = dao.countHistory(username);
        String page_raw = request.getParameter("txtPage");
        page_raw = (page_raw == null) ? "1" : page_raw;
        int pageIndex = Integer.parseInt(page_raw);

        //lay max page
        int maxPage = rowCount / 6 + (rowCount % 6 > 0 ? 1 : 0);

        List<Order> list = dao.getHistory(pageIndex, username);
        request.setAttribute("list", list);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("pageIndex", pageIndex);
        request.getRequestDispatcher("myorder.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

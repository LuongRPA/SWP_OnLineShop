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
import models.Users;

/**
 *
 * @author sony
 */
public class manager extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!isAdmin(request)) {
            response.sendRedirect("home");
        }
        
        DBContext db = new DBContext();
        DAO dao = new DAO(db);
        String username = request.getParameter("user");
        if (username != null) {
            dao.deleteByUsername(username);
        }

        String user_search = request.getParameter("user_search");
        user_search = user_search == null ? "" : user_search.trim();

        int rowCount = dao.countUser(user_search);
        //----
        String page_raw = request.getParameter("txtPage");
        page_raw = (page_raw == null) ? "1" : page_raw;
        //------
        int pageIndex = Integer.parseInt(page_raw);
        //lay max page
        int maxPage = rowCount / 6 + (rowCount % 6 > 0 ? 1 : 0);

        List<Users> list = dao.searchUser(pageIndex, user_search);
        request.setAttribute("list", list);
        request.setAttribute("user_search", user_search);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("pageIndex", pageIndex);
        request.getRequestDispatcher("manager.jsp").forward(request, response);
    }

    private boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String admin = (String) session.getAttribute("admin");
        return admin != null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        DBContext db = new DBContext();
//        DAO dao = new DAO(db);
//        String username = request.getParameter("user");
//        dao.deleteByUsername(username);
//        List<Users> list = dao.getAllUser();
//        request.setAttribute("list", list);
//        request.getRequestDispatcher("manager.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

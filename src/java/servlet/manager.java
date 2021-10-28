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
import models.Users;

/**
 *
 * @author sony
 */
public class manager extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBContext db = new DBContext();
        DAO dao = new DAO(db);
        String username = request.getParameter("user");
        if (username != null) {
            dao.deleteByUsername(username);
        }
        List<Users> list = dao.getAllUser();
        request.setAttribute("list", list);
        request.getRequestDispatcher("manager.jsp").forward(request, response);
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

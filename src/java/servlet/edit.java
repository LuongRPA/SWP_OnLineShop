/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import context.DBContext;
import dao.DAO;
import dao.UserDao;
import dao.UserDaoImp;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
public class edit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("login");
            return;
        }
        DBContext db = new DBContext();
        UserDao dao = new UserDaoImp();
        Users users = dao.getAccountByUsername(username);
        request.setAttribute("users", users);
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBContext db = new DBContext();
        UserDao dao = new UserDaoImp();
        String errMess = null;
        HttpSession session = request.getSession();
        String username2 = (String) session.getAttribute("username");
        Users users = dao.getAccountByUsername(username2);
        request.setAttribute("users", users);

        String username = request.getParameter("username");
        String oldusername = request.getParameter("oldusername");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String address = request.getParameter("address");

        Users us = new Users();
        us.setUsername(username);
        us.setPassword(password);
        us.setEmail(email);
        us.setPhone(phone);
        us.setGender(gender);
        us.setAddress(address);

        try {
            int result = dao.checkUserAccount(username, password);
            if (result != 0) {
                errMess = "This username is already available. Please choose other username";
            } else {
                dao.UpdateMethod(us, oldusername);
                response.sendRedirect("logout");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if (errMess != null) {
            request.setAttribute("errorMessage", errMess);
            String path = "/edit.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(path);
            dispatcher.forward(request, response);
            return;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

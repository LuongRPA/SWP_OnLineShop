/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import context.DBContext;
import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Users;

/**
 *
 * @author sony
 */
public class dangky extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("dangky.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repPassword = request.getParameter("repassword");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String errMess = null;
         
        Users user = new Users(username, password, email, phone );
        
        request.setAttribute("users", user); 
        
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            errMess = "username and password must not be empty";
        } else if (!repPassword.equals(password)) {
            errMess = "Repeat password is not match with password";
        } else {
            DBContext dbContext = new DBContext();
            DAO dao;
            try {
                dao = new DAO(dbContext);
                int result = dao.checkUserAccount(username, password);
                if (result != 0) {
                    errMess = "This username is already available. Please choose other username";
                } else {
                    dao.signUpAccount(username, password, email, phone);
                    errMess = "Your account has been created";
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        if (errMess != null) {
            request.setAttribute("errorMessage", errMess);
            String path = "/dangky.jsp";
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

/*
 * Copyright(C) 2021,  FPT.
 *  LTS:
 *  LaptopShop
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * 2021/11/6                   1.0               HoanglV                       first comment
 */
package servlet;

import context.DBContext;
import dao.UserDao;
import DaoImplements.UserDaoImp;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Users;

/**
 * The class contains method to forward to view, command changes to database
 * will throw exception in case of an error occurring
 *
 * @author Le Viet Hoang
 */
public class registerController extends HttpServlet {

    /**
     * used to forward user to register page 
     * 
     * @param (request) is HttpServletRequest typee
     * @param (response) is HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response); //forward to view
    }
    /**
     * this method receive data from view and send to lower level to process
     * 
     * @param (request) is HttpServletRequest typee
     * @param (response) is HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        *this block get data from request's attribute
        *
        */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repPassword = request.getParameter("repassword");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int gender = Integer.parseInt(request.getParameter("gender")); //convert binary type of gender form database to boolean to process in system
        String errMess = null; // create error message incase of one

        Users user = new Users(username, password, email, phone, gender, address); //create new user object

        request.setAttribute("users", user);

        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            errMess = "username and password must not be empty"; //requirement check
        } else if (!repPassword.equals(password)) {
            errMess = "Repeat password is not match with password"; //requirement check
        } else {
            DBContext dbContext = new DBContext();
            try {
                UserDao DAO = new UserDaoImp();
                int result = DAO.checkUserAccount(username, password);  //call method to check if user is duplicated
                if (result != 0) {
                    errMess = "This username is already available. Please choose other username"; // user is duplicated
                } else {
                    DAO.signUpAccount(username, password, email, phone, gender, address); //add new user to database
                    errMess = "Your account has been created";
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        /*
        *forward to view with message
        *
        */
        if (errMess != null) {
            request.setAttribute("errorMessage", errMess); 
            String path = "/register.jsp";
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

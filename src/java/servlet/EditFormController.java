/*
 * Copyright(C) 2021,  FPT.
 *  LTS:
 *  LaptopShop
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * 2021/11/6                   1.0               HoanglV                        first comment
 */
package servlet;

import context.DBContext;
import dao.UserDaoImp;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Users;

/**
 * The class used to handle user's change password requests which receive user inputed
 * data and send to lower levels to process
 *
 * @author Le Viet Hoang
 */
public class EditFormController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editform</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editform at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


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
     * receive data and process
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBContext db = new DBContext();
        UserDaoImp dao = new UserDaoImp(db);

        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username"); //get current user
        /*
        * this block get current user data to show later
        */
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String address = request.getParameter("address");
        Users user = new Users(userName, password, email, phone, gender, address);

        /*
        * get new password and confirmation
        */
        String newPassword = request.getParameter("newPassword");
        String reNewPassword = request.getParameter("reNewPassword");

        if (!newPassword.equals(reNewPassword)) { // 2 password not matching
            /*
            * send message that use messed up
            */
            request.setAttribute("user_info", user);
            request.setAttribute("status", "fail");
            request.getRequestDispatcher("userinfo.jsp").forward(request, response); 
        } else {
            /*
            * this block update password and send new data to view
            */
            dao.changePassword(userName, newPassword);
            user.setPassword(newPassword);
            request.setAttribute("user_info", user);
            request.setAttribute("status", "success");
            request.getRequestDispatcher("userinfo.jsp").forward(request, response); //forward to view
        }
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

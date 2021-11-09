/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.servlet.http.HttpSession;
/**
 *
 * @author sony
 */
public class dangnhap extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("dangnhap.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errMess = null;
        
        DBContext db = new DBContext();
        UserDao dao = new UserDaoImp();
        int result = dao.checkUserAccount(username, password);
        
      try {
            if (result == 0) {
                errMess = "This account is not available";
            } else if (result == 1) {
                errMess = "Wrong password";
            }
        } catch (Exception e) {
            errMess = e.getMessage();
        }

        if (errMess != null) {
            request.setAttribute("errorMessage", errMess);
            String path = "/dangnhap.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(path);
            dispatcher.forward(request, response);
            return;
        }
        
        session.setAttribute("username", username);
        
        if(username.equalsIgnoreCase("admin")){
            session.setAttribute("admin", "admin");
        }
        response.sendRedirect("home"); 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

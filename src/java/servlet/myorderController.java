/*							
 * Copyright(C) 2005,  <Name of Organization/Individual own the application>.							
 * <ProjectCode/Short Name of Application> :							
 *  <Full name of project code/Name or application>							
 *							
 * Record of change:							
 * DATE                       Version             AUTHOR                       DESCRIPTION							
 * 7/11/2021      1.0              Longchhe       first comment							
 */							
package servlet;

import context.DBContext;
import DaoImplements.DAO;
import DaoImplements.OrderDaoImpl;
import dao.OrderDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Order;

/**
 * The class used to handle user's change password requests which receive user inputed
 * data and send to lower levels to process
 *
 * @author HP
 * @author Chu Hoang Long
 */
public class myorderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
       OrderDAO dao = new OrderDaoImpl();
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

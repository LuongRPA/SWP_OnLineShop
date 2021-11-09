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
import dao.DAO;
import dao.ProductDao;
import dao.ProductDaoImp;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.LaptopInfo;
import models.Product;

/**
 * this class receive data of chosen product handle view request and forward to view
 *
 *
 * @author HoangLV
 */
public class ProductDetailController extends HttpServlet {

    /**
     * Handles view product request
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /*
        * get product id
        */
        String stringPid = request.getParameter("pid");
        DBContext db = new DBContext();
        ProductDao dao = new ProductDaoImp();
        int pid = 0;
        try {
            pid = Integer.parseInt(stringPid);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        
        Product p = dao.getProductByID(pid); //get produt ProductDetailController
        
        LaptopInfo laptopInfo = dao.getLaptopInfo(p.getLaptopInfoID()); //get produt info
        request.setAttribute("laptopInfo", laptopInfo);
        request.setAttribute("product", p);
        request.getRequestDispatcher("detail.jsp").forward(request, response); //forward

    }

    /**
     * Handles add product to cart request
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
        DAO dao = new DAO(db);
        HttpSession session = request.getSession();
        
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("login");
            return;
        }
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int pID = Integer.parseInt(request.getParameter("pid"));
        int price = Integer.parseInt(request.getParameter("price"));
        int totalPrice = price * quantity;
        dao.Buy(quantity, totalPrice, pID, username);
        request.getRequestDispatcher("thank.jsp").forward(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

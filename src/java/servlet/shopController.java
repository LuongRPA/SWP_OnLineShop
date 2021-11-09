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
import dao.ProductDao;
import DaoImplements.ProductDaoImp;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Product;

/**
 * this class receive data and handle view request and forward to view
 *
 *
 * @author HoangLV
 */
public class shopController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

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
          processRequest(request, response);
        try {
            String nameProduct = request.getParameter("nameProduct");
            nameProduct = nameProduct == null ? "" : nameProduct.trim();
            
            DBContext db = new DBContext();
            ProductDao dao = new ProductDaoImp();
            
            //count rows
            int rowCount = dao.getCount(nameProduct);
            
            String page_raw = request.getParameter("txtPage");
            page_raw = (page_raw == null) ? "1" : page_raw; //get curent page number
            
            int pageIndex = Integer.parseInt(page_raw); 
            
            int maxPage = rowCount / 6 + (rowCount % 6 > 0 ? 1 : 0);//get last page number

            List<Product> list = dao.getListProduct(pageIndex, nameProduct); //get page
            
            request.setAttribute("listProduct", list);
            request.setAttribute("nameProduct", nameProduct);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("pageIndex", pageIndex);
            request.getRequestDispatcher("shop.jsp").forward(request, response);
        } catch (Exception e) {
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

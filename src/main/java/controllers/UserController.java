/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Account;
import entities.OrderDetail;
import entities.OrderHeader;
import entities.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import order.order;
import sessionbean.AccountFacade;
import sessionbean.OrderDetailFacade;
import sessionbean.OrderHeaderFacade;
import sessionbean.ProductFacade;

/**
 *
 * @author quckh
 */
@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {

    @EJB
    private ProductFacade pf;

    @EJB(name = "odf")
    private OrderDetailFacade odf;

    @EJB(name = "ohf")
    private OrderHeaderFacade ohf;

    @EJB
    private AccountFacade af;

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
        String action = request.getAttribute("action").toString();
        switch (action) {
            case "index":
                index(request, response);
                break;
            case "update":
                update(request, response);
                break;
            case "update_handler":
                update_handler(request, response);
                break;
            case "paymentHistory":
                paymentHistory(request, response);
                break;
            case "orderDetail":
                orderDetail(request, response);
                break;
            default:
                request.setAttribute("controller", "error");
                request.setAttribute("action", "index");
                break;
        }
        request.getRequestDispatcher(App.LAYOUT).forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

    private void index(HttpServletRequest request, HttpServletResponse response) {
        List<Account> list = af.findAll();
        HttpSession session = request.getSession();

        int id = (int) session.getAttribute("iduser");
        for (Account acc : list) {
            if (acc.getId().equals(id)) {
                request.setAttribute("name", acc.getName());
                request.setAttribute("email", acc.getEmail());
                request.setAttribute("phone", acc.getPhone());
                request.setAttribute("address", acc.getAddress());

            }
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        List<Account> list = af.findAll();
        HttpSession session = request.getSession();

        int id = (int) session.getAttribute("iduser");
        for (Account acc : list) {
            if (acc.getId().equals(id)) {
                request.setAttribute("name", acc.getName());
                request.setAttribute("email", acc.getEmail());
                request.setAttribute("phone", acc.getPhone());
                request.setAttribute("address", acc.getAddress());

            }
        }
    }

    private void update_handler(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String role = "CUSTOMER";
        int id = (int) session.getAttribute("iduser");
        List<Account> list = af.findAll();

        for (Account acc : list) {
            if (acc.getId().equals(id)) {
                acc = new Account(id, fullName, address, phone, email, acc.getGender(), acc.getUserName(), acc.getPassword(), true, role);
                af.edit(acc);
                index(request, response);
                request.setAttribute("controller", "user");
                request.setAttribute("action", "index");
            }
        }

    }

    private void paymentHistory(HttpServletRequest request, HttpServletResponse response) {
        List<OrderHeader> listOrderHeader = ohf.findAll();
        List<OrderHeader> list2 = new ArrayList<>();;
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("iduser");
        for (OrderHeader oh : listOrderHeader) {
            if (id == oh.getCustomerId()) {
                list2.add(new OrderHeader(oh.getOrderId(), oh.getDate(), oh.getStatus(), oh.getCustomerId(), oh.getStaffId(), oh.getShipToAddress()));
                request.setAttribute("oh", list2);
            }
        }
    }

    private void orderDetail(HttpServletRequest request, HttpServletResponse response) {
        List<Account> list = af.findAll();
        List<OrderDetail> ord = odf.findAll();
        List<OrderDetail> orlist = new ArrayList();
        List<order> temp = new ArrayList();

        int orid = Integer.parseInt(request.getParameter("orderID"));
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("iduser");
        double total = 0;
        for (OrderDetail od : ord) {
            if (orid == od.getOrderId()) {
                total += od.getPrice() * (1 - od.getDiscount()) * od.getQuantity();
                Product product = pf.find(od.getProductId());
//                orlist.add(od);
                order x = new order(od.getQuantity(), od.getPrice(), od.getDiscount(), product.getImg());
                temp.add(x);
            }
        }
        for (Account acc : list) {
            if (acc.getId().equals(id)) {
                request.setAttribute("name", acc.getName());
                request.setAttribute("email", acc.getEmail());
                request.setAttribute("phone", acc.getPhone());
                request.setAttribute("addr", acc.getAddress());
                request.setAttribute("gender", acc.getGender());
            }
        }
        request.setAttribute("total", total);
        request.setAttribute("orlist", temp);
        request.setAttribute("controller", "emp");
        request.setAttribute("action", "Odetail");
    }
}

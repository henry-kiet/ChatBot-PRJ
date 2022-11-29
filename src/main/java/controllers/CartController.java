/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import cart.Cart;
import cart.Item;
import entities.Account;
import entities.OrderDetail;
import entities.OrderHeader;
import entities.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionbean.AccountFacade;
import sessionbean.CustomerFacade;
import sessionbean.OrderDetailFacade;
import sessionbean.OrderHeaderFacade;
import sessionbean.ProductFacade;
import sessionbean.StaffFacade;

@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @EJB
    private AccountFacade as;

    @EJB
    private ProductFacade pf;

    @EJB
    private OrderDetailFacade odf;

    @EJB
    private CustomerFacade cus;

    @EJB
    private OrderHeaderFacade ohf;

    @EJB
    private StaffFacade sf;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Random generator = new Random();

    int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getAttribute("action").toString();
        switch (action) {
            case "index":
                index(request, response);
                break;
            case "add":
                add(request, response);
                break;
            case "add_chatbot":
                add_chatbot(request, response);
                break;
            case "update":
                update(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "empty":
                empty(request, response);
                break;
            case "checkout":
                checkout(request, response);
                break;
            case "success":
                success(request, response);
                break;
            case "addFromIndex":
                addFromIndex(request, response);
                break;
            default:
                request.setAttribute("controller", "error");
                request.setAttribute("action", "index");
        }
        request.getRequestDispatcher(App.LAYOUT).forward(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.check();
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        //Lấy thông tin từ client gửi lên
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        //Lấy cart từ session        
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {//Nếu trong session chưa có cart thì tạo mới cart
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        //Thêm item vào cart
        cart.add(id, quantity);
        //Để cart vào session
        //Cho hiện view home/index.jsp
        request.setAttribute("controller", "product");
        request.setAttribute("action", "index");
        //Đọc danh sách sản phẩm
        List<Product> list = pf.findAll();
        request.setAttribute("list", list);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        //Xoa item trong cart
        cart.delete(id, price, discount);
        //Cho hien cart/index.jsp

        request.setAttribute("action", "index");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));

        //debug
        System.out.println(price);
        System.out.println(discount);
        //
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        //Xoa item trong cart
        cart.update(id, quantity, price, discount);
        //Cho hien cart/index.jsp controller
        request.setAttribute("action", "index");
    }

    private void empty(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        //Xoa item trong cart
        cart.empty();
        //Cho hien cart/index.jsp
        request.setAttribute("action", "index");
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

    private void checkout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("roleuser");
        List<Account> list = as.findAll();

        if (role == null) {
            request.setAttribute("controller", "login");
            request.setAttribute("action", "login");
        } else {
            int idu = (int) session.getAttribute("iduser");
            for (Account account : list) {
                if (idu == account.getId()) {
                    request.setAttribute("email", account.getEmail());
                    request.setAttribute("name", account.getName());
                    request.setAttribute("phone", account.getPhone());
                    request.setAttribute("address", account.getAddress());
                    request.setAttribute("controller", "cart");
                    request.setAttribute("action", "confirmcheckout");
                }
            }
        }
    }

    private int checkDuplicateId() {
        boolean flag = true;
        List<OrderHeader> listOH = ohf.findAll();
        int r = randomNumber(100001, 999999);
        while (flag) {
            boolean flag2 = true;
            for (OrderHeader oh : listOH) {
                if (oh.getOrderId() == r) {
                    flag2 = false;
                    r = randomNumber(1001, 9999);
                }
            }
            if (flag2 == true) {
                return r;
            }
        }
        return 0;
    }

    private void success(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        int oId = checkDuplicateId();
        int uId = (int) session.getAttribute("iduser");
        int sId = 0;
        int id = 0;
        Date date = new java.util.Date();
        String shipToAdress = "Tp.Hcm";
        String status = "new";
        List<Account> listAcc = as.findAll();
        int idu = (int) session.getAttribute("iduser");
        for (Account account : listAcc) {
            if (idu == account.getId()) {
                request.setAttribute("email", account.getEmail());
                request.setAttribute("name", account.getName());
                request.setAttribute("phone", account.getPhone());
                request.setAttribute("address", account.getAddress());
            }
        }
        //-----------------------------------------------------------------------------      
        List cartList;
        List<Item> itemList = new ArrayList<>();
        cartList = cart.getAllItems();
        for (int i = 0; i < cartList.size(); i++) {
            itemList.add((Item) cartList.get(i));
        }
        OrderHeader odh = new OrderHeader(oId, date, status, uId, sId, shipToAdress);
        ohf.create(odh);
        for (int i = 0; i < itemList.size(); i++) {
            OrderDetail od = new OrderDetail(oId, itemList.get(i).getId(), itemList.get(i).getQuantity(), itemList.get(i).getPrice(), itemList.get(i).getDiscount());
            odf.create(od);
        }
        cart.empty();
        request.setAttribute("orderId", oId);
        request.setAttribute("controller", "cart");
        request.setAttribute("action", "success");

    }

    private void add_chatbot(HttpServletRequest request, HttpServletResponse response) {
        //Lấy thông tin từ client gửi lên
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        //Lấy cart từ session        
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {//Nếu trong session chưa có cart thì tạo mới cart
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        //Thêm item vào cart
        cart.add(id, quantity);
        //Để cart vào session
        //Cho hiện view home/index.jsp
        request.setAttribute("controller", "cart");
        request.setAttribute("action", "index");
        //Đọc danh sách sản phẩm
        List<Product> list = pf.findAll();
        request.setAttribute("list", list);
    }

    private void addFromIndex(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = 1;
        //Lấy cart từ session        
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {//Nếu trong session chưa có cart thì tạo mới cart
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        //Thêm item vào cart
        cart.add(id, quantity);
        //Để cart vào session
        //Cho hiện view home/index.jsp
        request.setAttribute("controller", "product");
        request.setAttribute("action", "index");
        //Đọc danh sách sản phẩm
        List<Product> list = pf.findAll();
        request.setAttribute("list", list);
    }

}

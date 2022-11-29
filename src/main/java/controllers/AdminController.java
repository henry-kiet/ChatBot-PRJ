/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Account;
import entities.Chatbot;
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
import sessionbean.AccountFacade;
import sessionbean.ChatbotFacade;
import sessionbean.OrderHeaderFacade;
import sessionbean.ProductFacade;

/**
 *
 * @author quckh
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

    @EJB
    private ChatbotFacade cbf;

    @EJB
    private ProductFacade pf;

    @EJB
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
            case "manageEmployees":
                manageEmployees(request, response);
                break;
            case "manageCustomers":
                manageCustomers(request, response);
                break;
            case "manageProducts":
                manageProducts(request, response);
                break;
            case "manageOrders":
                manageOrders(request, response);
                break;
            case "generateNewEmployee":
                generateNewEmployee(request, response);
                break;
            case "generateNewEmployee_handler":
                generateNewEmployee_handler(request, response);
                break;
            case "updateEmployee":
                updateEmployee(request, response);
                break;
            case "updateEmployee_handler":
                updateEmployee_handler(request, response);
                break;
            case "deleteEmployee":
                deleteEmployee(request, response);
                break;
            case "updateProduct":
                updateProduct(request, response);
                break;
            case "updateProductHandler":
                updateProductHandler(request, response);
                break;
            case "deleteProduct":
                deleteProduct(request, response);
                break;
            case "manageChatbot":
                manageChabot(request, response);
                break;
            case "updateChatbot":
                updateChatbot(request, response);
                break;
            case "deleteChatbot":
                deleteChatbot(request, response);
                break;
            case "updateChatbot_hander":
                updateChatbot_hander(request, response);
                break;
            case "addChatbot":
                addChatbot(request, response);
                break;
            case "addChatbot_handler":
                addChatbot_handler(request, response);
                break;
            case "addNewProduct":
                addNewProduct(request, response);
                break;
            case "addNewProduct_handler":
                addNewProduct_handler(request, response);
                break;
            case "deleteCustomer":
                deleteCustomer(request, response);
                break;
            default:
                request.setAttribute("controller", "error");
                request.setAttribute("action", "index");
        }
        request.getRequestDispatcher(App.LAYOUT).forward(request, response);
    }

    private void manageEmployees(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        String role = "EMPLOYEE";
        List<Account> elist = new ArrayList<>();
        List<Account> list = af.findAll();
        for (Account acc : list) {
            if (acc.getRole().equals(role)) {
                elist.add(acc);
            }
        }
        request.setAttribute("elist", elist);
    }
    
    private void manageCustomers(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        String role = "CUSTOMER";
        List<Account> clist = new ArrayList<>();
        List<Account> list = af.findAll();
        for (Account acc : list) {
            if (acc.getRole().equals(role)) {
                clist.add(acc);
            }
        }
        request.setAttribute("clist", clist);
    }
    
    private void generateNewEmployee(HttpServletRequest request, HttpServletResponse response) {

    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("pid"));
        List<Product> list = pf.findAll();
        List productList = new ArrayList();
        for (Product p : list) {
            if (p.getId().equals(id)) {
                productList.add(p);
                request.setAttribute("productList", productList);
            }
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Account> list = af.findAll();
        for (Account acc : list) {
            if (acc.getId().equals(id)) {
                af.remove(acc);
            }
        }
        manageEmployees(request, response);
        request.setAttribute("controller", "admin");
        request.setAttribute("action", "manageEmployees");
    }

    private void generateNewEmployee_handler(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        boolean flag = false;
        List<Account> list = af.findAll();
        request.setAttribute("list", list);
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String uname = request.getParameter("uName");
        String pw = request.getParameter("password");
        String rpw = request.getParameter("vpassword");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        int id = list.size() + 1;
        String role = "EMPLOYEE";

        for (Account account : list) {
            if (uname.equals(account.getUserName())) {
                request.setAttribute("messuname", "User name already existed !!!");
                request.setAttribute("controller", "admin");
                request.setAttribute("action", "generateNewEmployee");
                flag = true;
            }
        }
        if (!(pw.equals(rpw) == true)) {
            request.setAttribute("messpass", "Wrong password verification !!!");
            request.setAttribute("controller", "admin");
            request.setAttribute("action", "generateNewEmployee");
            flag = true;
        }
        for (Account account : list) {
            if (email.equals(account.getEmail())) {
                request.setAttribute("messmail", "Email already existed !!!");
                request.setAttribute("controller", "admin");
                request.setAttribute("action", "generateNewEmployee");
                flag = true;
            }
        }

        if (!flag) {
            Account a = new Account(id, name, address, phone, email, gender, uname, pw, true, role);
            af.create(a);
            manageEmployees(request, response);
            request.setAttribute("controller", "admin");
            request.setAttribute("action", "manageEmployees");
        }
    }

    private void manageProducts(HttpServletRequest request, HttpServletResponse response) {
        List<Product> list = pf.findAll();
        List<Product> plist = new ArrayList<>();
        for (Product p : list) {
            p = new Product(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getDiscount(), p.getImg());
            plist.add(p);
        }
        request.setAttribute("plist", plist);
    }

    private void manageOrders(HttpServletRequest request, HttpServletResponse response) {
        List<OrderHeader> list = ohf.findAll();
        List<OrderHeader> olist = new ArrayList<>();
        for (OrderHeader oh : list) {
            oh = new OrderHeader(oh.getOrderId(), oh.getDate(), oh.getStatus(), oh.getCustomerId(), oh.getStaffId(), oh.getShipToAddress());
            olist.add(oh);
        }
        request.setAttribute("olist", olist);
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        List<Account> list = af.findAll();
        List elist = new ArrayList();
        for (Account acc : list) {
            if (acc.getId().equals(id)) {
                elist.add(acc);
                request.setAttribute("elist", elist);
            }
        }
    }

    private void updateEmployee_handler(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Account> list = af.findAll();
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String vPassword = request.getParameter("vPassword");
        String role = "EMPLOYEE";

        Account acc = new Account(id, name, address, phoneNumber, email, gender, userName, password, true, role);
        af.edit(acc);
        manageEmployees(request, response);
        request.setAttribute("controller", "admin");
        request.setAttribute("action", "manageEmployees");
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

    private void updateProductHandler(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        double price = Double.parseDouble(request.getParameter("productPrice"));
        double discount = Double.parseDouble(request.getParameter("productDiscount"));
        String img = request.getParameter("image");
        Product p = new Product(id, name, description, price, discount / 100, img);
        pf.edit(p);
        manageProducts(request, response);
        request.setAttribute("controller", "admin");
        request.setAttribute("action", "manageProducts");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("pid"));
        List<Product> list = pf.findAll();
        for (Product p : list) {
            if (p.getId().equals(id)) {
                pf.remove(p);
            }
        }
        manageProducts(request, response);
        request.setAttribute("controller", "admin");
        request.setAttribute("action", "manageProducts");
    }

    private void manageChabot(HttpServletRequest request, HttpServletResponse response) {
        List<Chatbot> list = cbf.findAll();
        List<Chatbot> cblist = new ArrayList<>();
        for (Chatbot bot : list) {
            bot = new Chatbot(bot.getKeyword(), bot.getContent());
            cblist.add(bot);
            request.setAttribute("cblist", cblist);
        }
    }

    private void updateChatbot(HttpServletRequest request, HttpServletResponse response) {
        String key = request.getParameter("key").trim().toLowerCase();
        List<Chatbot> list = cbf.findAll();
        for (Chatbot p : list) {
            if (p.getKeyword().trim().toLowerCase().equals(key)) {
                request.setAttribute("keyword", key);
                request.setAttribute("content", p.getContent());
            }
        }
    }

    private void deleteChatbot(HttpServletRequest request, HttpServletResponse response) {
        String key = request.getParameter("key");
        List<Chatbot> list = cbf.findAll();
        for (Chatbot c : list) {
            if (c.getKeyword().equals(key)) {
                cbf.remove(c);
            }
        }
        manageChabot(request, response);
        request.setAttribute("controller", "admin");
        request.setAttribute("action", "manageChatbot");
    }

    private void updateChatbot_hander(HttpServletRequest request, HttpServletResponse response) {
        String key = request.getParameter("keyword");
        String content = request.getParameter("content");
        Chatbot bot = new Chatbot(key, content);
        cbf.edit(bot);
        manageChabot(request, response);
        request.setAttribute("controller", "admin");
        request.setAttribute("action", "manageChatbot");
    }

    private void index(HttpServletRequest request, HttpServletResponse response) {
    }

    private void addChatbot(HttpServletRequest request, HttpServletResponse response) {
    }

    private void addChatbot_handler(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");
        String content = request.getParameter("content");
        Chatbot b = new Chatbot(keyword, content);
        cbf.create(b);
        manageChabot(request, response);
        request.setAttribute("controller", "admin");
        request.setAttribute("action", "manageChatbot");
    }

    private void addNewProduct(HttpServletRequest request, HttpServletResponse response) {

    }

    private void addNewProduct_handler(HttpServletRequest request, HttpServletResponse response) {
        Product product;
        int id = getTotalID() + 1;
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        String img = request.getParameter("image");
        product = new Product(id, name, description, price, discount / 100, img);
        pf.create(product);
        manageProducts(request, response);
        request.setAttribute("controller", "admin");
        request.setAttribute("action", "manageProducts");
    }

    private int getTotalID() {
        int sum = 0;
        List<Product> list = pf.findAll();
        for (int i = 0; i < list.size(); i++) {
            sum += i;
        }
        return sum;
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idCus"));
        List<Account> list = af.findAll();
        for (Account acc : list) {
            if (acc.getId().equals(id)) {
                af.remove(acc);
            }
        }
        manageCustomers(request, response);
        request.setAttribute("controller", "admin");
        request.setAttribute("action", "manageCustomers");
    }
}

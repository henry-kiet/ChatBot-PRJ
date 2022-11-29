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
import entities.Staff;
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
import sessionbean.StaffFacade;

/**
 *
 * @author PHT
 */
@WebServlet(name = "EmpController", urlPatterns = {"/emp"})
public class EmpController extends HttpServlet {

    @EJB
    private ProductFacade pf;



    @EJB
    private OrderDetailFacade of;

    @EJB
    private OrderHeaderFacade ohf;

    @EJB
    private AccountFacade af;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getAttribute("action").toString();
        switch (action) {
            case "index":
                index(request, response);
                break;
            case "checkbill":
                checkbill(request, response);
                break;
            case "update":
                update(request, response);
                break;
            case "detail":
                detail(request, response);
                break;    
            default:
                request.setAttribute("controller", "error");
                request.setAttribute("action", "index");
        }
        request.getRequestDispatcher(App.LAYOUT2).forward(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) {
        List<Staff> list = sf.findAll();

        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("iduser");

        for (Staff st : list) {
            if (id == st.getId()) {
                request.setAttribute("name", st.getAccount().getName());
                request.setAttribute("email", st.getAccount().getEmail());
                request.setAttribute("phone", st.getAccount().getPhone());
                request.setAttribute("addr", st.getAccount().getAddress());
                request.setAttribute("salary", st.getSalary());
            }
        }

    }

    private void checkbill(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Staff> list = sf.findAll();
        List<OrderHeader> list2 = ohf.findAll();
        List<OrderHeader> list3 = ohf.findAll();
        List<OrderHeader> list4 = ohf.findAll();
        List<OrderHeader> ohnew = new ArrayList();
        List<OrderHeader> ohcf = new ArrayList();
        List<OrderHeader> ohcel = new ArrayList();
        
        int id = (int) session.getAttribute("iduser");
        for (int i = 0; i < list2.size(); i++) {
            if (list2.get(i).getStatus().equalsIgnoreCase("new")) {
                ohnew.add(list2.get(i));
            }
        }

        for (int i = 0; i < list3.size(); i++) {
            if (list3.get(i).getStatus().equalsIgnoreCase("confirmed")) {
                ohcf.add(list3.get(i));
            }
        }
   
        for (int i = 0; i < list4.size(); i++) {
            if (list4.get(i).getStatus().equalsIgnoreCase("canceled")) {
                ohcel.add(list4.get(i));
            }
        }

        request.setAttribute("ohnew", ohnew);
        request.setAttribute("ohcf", ohcf);
        request.setAttribute("ohcel", ohcel);
        

        request.setAttribute("controller", "emp");
        request.setAttribute("action", "checkbill");

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String stt = request.getParameter("stt");
        
        HttpSession session = request.getSession();
          
        int idstaff = (int) session.getAttribute("iduser");
        
        List<OrderHeader> list = ohf.findAll();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getOrderId() == id) {
              OrderHeader odhh   = new OrderHeader(list.get(i).getOrderId(), list.get(i).getDate(), stt, list.get(i).getCustomerId(), idstaff, list.get(i).getShipToAddress());          
               ohf.edit(odhh);
            }
        }

        
        
        request.setAttribute("controller", "emp");
        request.setAttribute("action", "checkbill_trans");

    }
    
        private void detail(HttpServletRequest request, HttpServletResponse response) {
        List<Account> list = af.findAll();
        List<OrderDetail> ord = of.findAll();
        List<OrderDetail> orlist = new ArrayList();
        List<order> temp = new ArrayList();
        
        int cid = Integer.parseInt(request.getParameter("cusid"));
        int orid = Integer.parseInt(request.getParameter("orid"));
        HttpSession session = request.getSession();
        double total=0;
         for (OrderDetail od : ord) {
             if(orid == od.getOrderId()){
                 total += od.getPrice() * (1-od.getDiscount()) * od.getQuantity() ;
                  Product product = pf.find(od.getProductId());
//                orlist.add(od);
                 order x = new order(od.getQuantity(),od.getPrice(),od.getDiscount(),product.getImg());
                 temp.add(x);
             }        
         }
        for (Account st : list) {
            if (cid == st.getId()) {
                request.setAttribute("name", st.getName());
                request.setAttribute("email", st.getEmail());
                request.setAttribute("phone", st.getPhone());
                request.setAttribute("addr", st.getAddress());
                request.setAttribute("gender", st.getGender());
            }
        }    
        request.setAttribute("total",total);      
        request.setAttribute("orlist",temp);   
        request.setAttribute("controller", "emp");
        request.setAttribute("action", "Odetail");
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



}

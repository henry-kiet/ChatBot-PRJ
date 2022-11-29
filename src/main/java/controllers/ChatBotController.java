/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Chat;
import entities.Chatbot;
import entities.OrderDetail;
import entities.OrderHeader;
import entities.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import sessionbean.ChatbotFacade;
import sessionbean.OrderDetailFacade;
import sessionbean.OrderHeaderFacade;
import sessionbean.ProductFacade;

/**
 *
 * @author SE151515 Cao Trong Hieu
 */
@WebServlet(name = "ChatBotController", urlPatterns = {"/chatbot"})
public class ChatBotController extends HttpServlet {

    @EJB
    private OrderHeaderFacade ohf;

    @EJB
    private OrderDetailFacade odf;

    @EJB
    private ChatbotFacade cf;

    @EJB
    private ProductFacade pf;

    List<Chat> chatSession = new ArrayList<>();

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
        PrintWriter out = response.getWriter();
        List<Product> listProduct = pf.findAll();
        List<Chatbot> listChatbot = cf.findAll();
        List<OrderHeader> listOH = ohf.findAll();
        List<OrderDetail> listOD = odf.findAll();
        order order;
        List<order> temp = new ArrayList();
        String uInput;
        String botMsg;
        Chat chat;
        HttpSession session = request.getSession();
        boolean flag = false;
        boolean flagProduct = true;
        boolean flagOrder = true;
        int DupChatCount;
        String preMsg = (String) session.getAttribute("PREVIOUS");
        uInput = request.getParameter("uInput");
        //check for chatbot db-based answers
        for (Chatbot c : listChatbot) {
            if (uInput.toLowerCase().equals(c.getKeyword())) {
                botMsg = c.getContent();
                if ("search".equals(preMsg)) {
                    session.setAttribute("PREVIOUS", null);
                    break;
                }
                if("find my order".equals(preMsg)){
                    session.setAttribute("PREVIOUS", null);
                    flagProduct = false;
                    break;
                } else {
                    flagProduct = true;
                }
                chat = new Chat(uInput, botMsg);
                chatSession.add(chat);
                session.setAttribute("CHAT_SESSION", chatSession);
                out.println("<div class=\"incoming-msg\"> <span class=\"bot-msg\">" + chat.getBotMsg() + "</span></div>\n");
                flag = true;
                flagOrder = false;
                session.setAttribute("PREVIOUS", uInput);
            }
        }
        //end chat session
        if (uInput.equals("bye")) {
            out.println("<div class=\"incoming-msg\"> <span class=\"bot-msg\">See you later!</span></div>\n");
            session.invalidate();
            return;
            //find product
        }
        if (flagProduct) {
            String InitUInput = uInput;
            DupChatCount = 0;
            for (Product p : listProduct) {
                if (DupChatCount > 0) {
                    uInput = "";
                }
                if (p.getName().toLowerCase().contains(InitUInput.toLowerCase())) {
                    NumberFormat formatter = new DecimalFormat("$#,##0.00");
                    double finalPrice = p.getPrice() * (1 - p.getDiscount());
                    botMsg = p.getId() + "*" + p.getDiscount() + "*" + p.getPrice() + "*" + p.getDiscount() * 100 + "*" + finalPrice + "*" + p.getImg();
                    DupChatCount++;
                    chat = new Chat(uInput, botMsg);
                    chatSession.add(chat);
                    session.setAttribute("CHAT_SESSION", chatSession);
                    out.println(
                            "<div class =\"incoming-msg\"> "
                            + "<span class =\"bot-msg\">"
                            + "<form method =\"post\" action=\"/chatbot-test/product/detail.do\">"
                            + "<button style=\"background: white;color : black;border-radius: 5px;\" type=\"submit\">"
                            + " <input type=\"hidden\" value=\"" + p.getId() + "\" name=\"id\"/>"
                            + "<img  src=\"" + p.getImg() + "\" width=\"50%\" /><br/>"
                            + "Discount:" + p.getDiscount() * 100 + "%" + "<br/>\n"
                            + "Price: <strike> " + formatter.format(p.getPrice()) + "</strike></br>"
                            + "<span style=\"color:red;font-size:20px;\">\n"
                            + formatter.format(finalPrice)
                            + "</span><br/>"
                            + " </button>\n"
                            + " <input name='quantity' type='hidden' value='1' />"
                            + " <button formaction=\"/chatbot-test/cart/add_chatbot.do\" style=\"border-radius: 5px;background: #212529;color: #fff;margin: 10px 5px 0 12px;font-size: 15px;padding: 2px 27px;border: solid 2px #212529;transition: all 0.5s ease-in-out 0s;\" type=\"submit\" class=\"round-black-btn\">Add to Cart</button>"
                            + "</span></form></div>");
                    flag = true;
                    flagOrder = false;
                    session.setAttribute("PREVIOUS", "product");
                }
            }
        }

        if (flagOrder) {
            double total = 0;
            NumberFormat formatter = new DecimalFormat("$#,##0.00");
            if (uInput.matches("[0-9]+")) {
                int ordID = Integer.valueOf(uInput);
                String status = null;
                boolean validator = true;
                for (OrderHeader oh : listOH) {
                    if (oh.getOrderId() == ordID) {
                        status = oh.getStatus();
                    }
                }
                for (OrderDetail od: listOD){
                    if(od.getOrderId()==ordID){
                        validator = true;
                        break;
                    } else {
                        validator = false;
                    }
                }
                if(validator){
                    out.println(
                        "<div class =\"incoming-msg\"> "
                        + "<span class =\"bot-msg\">"
                        + "Order status: </br>" + status + "</br></br>"
                );
                for (OrderDetail od : listOD) {
                    if (od.getOrderId() == ordID) {
                        total += od.getPrice() * (1 - od.getDiscount()) * od.getQuantity();
                        Product product = pf.find(od.getProductId());
                        double finalPrice = product.getPrice() * (1 - product.getDiscount());
                        order = new order(od.getQuantity(), od.getPrice(), od.getDiscount(), product.getImg());
                        temp.add(order);
                        out.println(
                                "<div class=\"row\" style=\"margin-left: 1px\">"
                                + "<div class=\"col-6\"><p class=\"mb-0\">" + product.getName() + "</p></div>"
                                + "<div class=\"col-3\"><p class=\"mb-0\">X" + od.getQuantity() + "</p></div>"
                                + "<div class=\"col-5\"><p class=\"mb-0\">" + formatter.format(finalPrice) + "</p></div>"
                                + "</div>"
                        );
                    }
                }
                out.println(
                        "<h3>Total: " + formatter.format(total) + "</h3>"
                        + "</span></div>");
                botMsg = status;
                session.setAttribute("PREVIOUS", "order");
                chat = new Chat(uInput, botMsg, temp);
                chatSession.add(chat);
                flag = true;
                } else {
                    flag = false;
                }
            } else {
                flag = false;
            }
        }

        if (flag == false) {
            botMsg = (String) "I don't understand</br>Bot inputs:</br>- search: find products in the shop</br>- find my order: find order using order ID</br>- bye: end chat session</br>Try again!";
            chat = new Chat(uInput, botMsg);
            chatSession.add(chat);
            session.setAttribute("CHAT_SESSION", chatSession);
            out.println("<div class=\"incoming-msg\"> <span class=\"bot-msg\">" + chat.getBotMsg() + "</span></div>\n");
        }
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

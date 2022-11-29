/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Account;
import entities.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import static java.util.Collections.list;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sessionbean.AccountFacade;
import sessionbean.CustomerFacade;


/**
 *
 * @author quckh
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})

public class RegisterController extends HttpServlet {

    @EJB
    private CustomerFacade cf;

    @EJB
    private AccountFacade as;
    
    


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
    String alpha = "ABCDEFGHIJKLMOPQRSTUVWXYZ1234567890";
    
    public String randomAlpha() {
        int numberOfCharactor = 9;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, alpha.length() - 1);
            char ch = alpha.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
    
    int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
    
    public void vgmail(String x, String y) throws MessagingException, UnsupportedEncodingException {
        final String fromEmail = "hieuctse151515@fpt.edu.vn";
        // Mat khai email cua ban
        final String password = "kqxhhptlpvvcqghx";
        // dia chi email nguoi nhan
        final String toEmail = x;
        final String subject = "Confirm account !!!";
        final String body = "YOUR VERIFY CODE : " + y;
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");
        msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));
        msg.setReplyTo(InternetAddress.parse(fromEmail, false));
        msg.setSubject(subject, "UTF-8");
        msg.setText(body, "UTF-8");
        msg.setSentDate(new Date());
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        Transport.send(msg);
        
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getAttribute("action").toString();
        switch (action) {
            case "index":
                index(request, response);
                break;
            case "register": {
                try {
                    register(request, response);
                } catch (MessagingException | UnsupportedEncodingException ex) {
                    Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "confirm":
                confirm(request, response);
                break;
            default:
                request.setAttribute("controller", "error");
                request.setAttribute("action", "index");
        }
        request.getRequestDispatcher(App.LAYOUT).forward(request, response);
    }
    
    private void index(HttpServletRequest request, HttpServletResponse response) {        
        request.setAttribute("controller", "register");
        request.setAttribute("action", "index");
    }
    
    private void register(HttpServletRequest request, HttpServletResponse response) throws MessagingException, UnsupportedEncodingException {
        response.setContentType("text/html;charset=UTF-8");
        boolean flag = false;
        
        
        List<Account> list = as.findAll();
        
        
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
        String role = "CUSTOMER";
        
        String vcode = randomAlpha();
        for (Account account : list) {
            if (uname.equals(account.getUserName())) {
                request.setAttribute("messuname", "User name already existed !!!");
                request.setAttribute("controller", "register");
                request.setAttribute("action", "index");
                flag = true;
            }
        }
        if (!(pw.equals(rpw) == true)) {
            request.setAttribute("messpass", "Wrong password verification !!!");
            request.setAttribute("controller", "register");
            request.setAttribute("action", "index");
            flag = true;
        }
        for (Account account : list) {
            if (email.equals(account.getEmail())) {
                request.setAttribute("messmail", "Email already existed !!!");
                request.setAttribute("controller", "register");
                request.setAttribute("action", "index");
                flag = true;
            }
        }
        
        if (!flag) {
            
            vgmail(email, vcode);
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("address", address);
            request.setAttribute("phone", phone);
            request.setAttribute("email", email);
            request.setAttribute("gender", gender);
            request.setAttribute("uname", uname);
            request.setAttribute("pw", pw);
            request.setAttribute("role", role);
            
            request.setAttribute("vcode", vcode);
            request.setAttribute("controller", "register");
            request.setAttribute("action", "confirm");
            
        }

//        if (!flag) {
//            Account a = new Account(id, name, address, phone, email, gender, uname, pw, true, role);
//            as.create(a);
//            request.setAttribute("controller", "register");
//            request.setAttribute("action", "index");
//        }
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

    private void confirm(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String uname = request.getParameter("uname");
        String pw = request.getParameter("pw");
        String role = request.getParameter("role");
        
        String vcode = request.getParameter("vcode");
        String input = request.getParameter("inputcode");
        
        if (input.equals(vcode)) {
            
            Account a = new Account(id, name, address, phone, email, gender, uname, pw, true, role);
            as.create(a);
            request.setAttribute("sucmess", "REGISTER SUCCESS !!!");            
            request.setAttribute("controller", "login");
            request.setAttribute("action", "login");
            
        } else {
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("address", address);
            request.setAttribute("phone", phone);
            request.setAttribute("email", email);
            request.setAttribute("gender", gender);
            request.setAttribute("uname", uname);
            request.setAttribute("pw", pw);
            request.setAttribute("role", role);
            request.setAttribute("vcode", vcode);
            
            request.setAttribute("codemess", "Wrong verification code !!!");
            request.setAttribute("controller", "register");
            request.setAttribute("action", "confirm");
            
        }
        
    }
    
}

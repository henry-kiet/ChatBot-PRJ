/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Account;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionbean.AccountFacade;

/**
 *
 * @author quckh
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
            throws ServletException, IOException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getAttribute("action").toString();
        switch (action) {
            case "login":
                login(request, response);
                break;
            case "login_handler":
                login_handler(request, response);
                break;
            case "logout":
                logout(request, response);
                break;
            case "forget":
                forget(request, response);
                break;
            case "forget_handler":
                forget_handler(request, response);
                break;
            case "confirm":
                confirm(request, response);
                break;
            case "resetpass":
                resetpass(request, response);
                break;
        }
        request.getRequestDispatcher(App.LAYOUT).forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = null;
        Cookie cUserName = null;
        Cookie cPassword = null;
        Cookie[] cookies = null;
        List<Account> list = af.findAll();
        boolean flag = false;
        // Get an array of Cookies associated with the this domain
        cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if ((cookie.getName()).equals("userName")) {
                    cUserName = cookie;
                } else if ((cookie.getName()).equals("password")) {
                    cPassword = cookie;
                }
            }
        }
        for (Account acc : list) {
            if (cUserName != null
                    && cPassword != null
                    && cUserName.getValue().equals(acc.getUserName())
                    && cPassword.getValue().equals(acc.getPassword())) {
                //Lưu userName vào session để ghi nhận đã login thành công
                HttpSession session = request.getSession();

                //Chuyển đến trang details.jsp
                request.setAttribute("controller", "home");
                request.setAttribute("action", "index");

                session.setAttribute("login_success", 1);
                session.setAttribute("userName", cUserName.getValue());
                String role = acc.getRole();
                session.setAttribute("iduser", acc.getId());
                session.setAttribute("roleuser", role);

                flag = true;
            }
        }
        if (!flag) {
            request.setAttribute("controller", "login");
            request.setAttribute("action", "login");
        }
    }

    private void login_handler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag = false;
        List<Account> list = af.findAll();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        String role;
        boolean rememberMe = request.getParameter("rememberMe") != null;
        if (rememberMe) {
            for (Account acc : list) {
                if (userName.equals(acc.getUserName()) && password.equals(acc.getPassword()) && acc.getRole().equals("CUSTOMER")) {
                    request.setAttribute("controller", "home");
                    request.setAttribute("action", "index");
                    session.setAttribute("login_success", 1);
                    session.setAttribute("userName", userName);
                    role = acc.getRole();
                    session.setAttribute("iduser", acc.getId());
                    session.setAttribute("roleuser", role);
                    //cookie
                    Cookie cUserName = new Cookie("userName", acc.getUserName());
                    Cookie cPassword = new Cookie("password", acc.getPassword());
                    cUserName.setMaxAge(60 * 60 * 24);
                    cPassword.setMaxAge(60 * 60 * 24);
                    response.addCookie(cUserName);
                    response.addCookie(cPassword);
                    //
                    flag = true;
                }
                if (userName.equals(acc.getUserName()) && password.equals(acc.getPassword()) && acc.getRole().equals("ADMIN")) {
                    request.setAttribute("controller", "home");
                    request.setAttribute("action", "index");
                    session.setAttribute("login_success", 1);
                    session.setAttribute("userName", userName);
                    role = acc.getRole();
                    session.setAttribute("iduser", acc.getId());
                    session.setAttribute("roleuser", role);
                    //cookie
                    Cookie cUserName = new Cookie("userName", acc.getUserName());
                    Cookie cPassword = new Cookie("password", acc.getPassword());
                    cUserName.setMaxAge(60 * 60 * 24);
                    cPassword.setMaxAge(60 * 60 * 24);
                    response.addCookie(cUserName);
                    response.addCookie(cPassword);
                    //
                    flag = true;
                }
                if (userName.equals(acc.getUserName()) && password.equals(acc.getPassword()) && acc.getRole().equals("EMPLOYEE")) {
                    request.setAttribute("controller", "home");
                    request.setAttribute("action", "index");
                    session.setAttribute("login_success", 1);
                    session.setAttribute("userName", userName);
                    role = acc.getRole();
                    session.setAttribute("iduser", acc.getId());
                    session.setAttribute("roleuser", role);
                    //cookie
                    Cookie cUserName = new Cookie("userName", acc.getUserName());
                    Cookie cPassword = new Cookie("password", acc.getPassword());
                    cUserName.setMaxAge(60 * 60 * 24);
                    cPassword.setMaxAge(60 * 60 * 24);
                    response.addCookie(cUserName);
                    response.addCookie(cPassword);
                    //
                    flag = true;
                }
            }
        } else {
            for (Account acc : list) {
                if (userName.equals(acc.getUserName()) && password.equals(acc.getPassword()) && acc.getRole().equals("CUSTOMER")) {
                    request.setAttribute("controller", "home");
                    request.setAttribute("action", "index");
                    session.setAttribute("login_success", 1);
                    session.setAttribute("userName", userName);
                    role = acc.getRole();
                    session.setAttribute("iduser", acc.getId());
                    session.setAttribute("roleuser", role);
                    flag = true;
                }
                if (userName.equals(acc.getUserName()) && password.equals(acc.getPassword()) && acc.getRole().equals("ADMIN")) {
                    request.setAttribute("controller", "home");
                    request.setAttribute("action", "index");
                    session.setAttribute("login_success", 1);
                    session.setAttribute("userName", userName);
                    role = acc.getRole();
                    session.setAttribute("iduser", acc.getId());
                    session.setAttribute("roleuser", role);
                    flag = true;
                }

                if (userName.equals(acc.getUserName()) && password.equals(acc.getPassword()) && acc.getRole().equals("EMPLOYEE")) {
                    request.setAttribute("controller", "home");
                    request.setAttribute("action", "index");
                    session.setAttribute("login_success", 1);
                    session.setAttribute("userName", userName);
                    role = acc.getRole();
                    session.setAttribute("iduser", acc.getId());
                    session.setAttribute("roleuser", role);
                    flag = true;
                }
            }
        }
        if (!flag) {
            request.setAttribute("controller", "login");
            request.setAttribute("action", "login");
            request.setAttribute("mess", "Wrong username or password !");
        }
    }

    private void forget(HttpServletRequest request, HttpServletResponse response) {

    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(); 
        ///
                Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }            
        }
        // session.invalidate();
        session.setAttribute("login_success", null);
        session.setAttribute("iduser", null);
        session.setAttribute("roleuser", null);
        session.setAttribute("userName", null);
        request.setAttribute("controller", "home");
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
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    private void forget_handler(HttpServletRequest request, HttpServletResponse response) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String vcode = randomAlpha();
        int id;
        boolean flag = false;
        List<Account> list = af.findAll();
        for (Account account : list) {
            if (account.getEmail().equals(email)) {
                id = account.getId();
                request.setAttribute("id", id);
                vgmail(email, vcode);
                request.setAttribute("vcode", vcode);
                request.setAttribute("controller","login");
                request.setAttribute("action", "confirm");
                flag = true;
            }
        }
        if (!flag) {
            request.setAttribute("mess", "Wrong email address or email does not exist!");
            request.setAttribute("controller", "login");
            request.setAttribute("action", "forget");
        }
    }

    private void confirm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String vcode = request.getParameter("vcode");
        String input = request.getParameter("inputcode");
        if (input.equals(vcode)) {
            request.setAttribute("id", id);
            request.setAttribute("controller", "login");
            request.setAttribute("action", "resetpw");
        } else {
            request.setAttribute("id", id);
            request.setAttribute("vcode", vcode);
            request.setAttribute("codemess", "Wrong verification code !!!");
            request.setAttribute("controller", "login");
            request.setAttribute("action", "confirm");
        }
    }

    private void resetpass(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String pass = request.getParameter("pw");
        String vpass = request.getParameter("vpw");
        boolean flag = false;
        if (vpass.equals(pass)) {
            List<Account> list = af.findAll();
            for (Account acc : list) {
                if (acc.getId().equals(id)) {
                    acc = new Account(id, acc.getName(), acc.getAddress(), acc.getPhone(), acc.getEmail(), acc.getGender(), acc.getUserName(), vpass, true, acc.getRole());
                    af.edit(acc);
                    request.setAttribute("controller", "login");
                    request.setAttribute("action", "login");
                    flag = true;
                }
            }
        }
        if (!flag) {
            request.setAttribute("id", id);
            request.setAttribute("messpassd", "wrong verify password");
            request.setAttribute("controller", "login");
            request.setAttribute("action", "resetpw");
        }
    }

}

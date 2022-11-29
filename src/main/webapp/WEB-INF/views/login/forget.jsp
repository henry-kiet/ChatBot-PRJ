 <%-- 
    Document   : forget
    Created on : May 24, 2022, 11:48:57 PM
    Author     : SE151515 Cao Trong Hieu
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="${root}/css/forget.css" rel="stylesheet" type="text/css"/>
        <link href="${root}/css/same_login.css" rel="stylesheet" type="text/css"/>  
    </head>
    <body style="background-image: url(/chatbot-test/images/layout.jpg); background-size: cover;">
        <div class="forget_form">
            <div class="flex flex-col justify-conter items-center px-5">
        <form action="${pageContext.request.contextPath}/login/forget_handler.do" method="post">
            <div >
                <div class="title">Find your account</div>
                
                <div class="message">Please enter your email to search for your account.</div>
                
                <input class="input" type="email" name="email" placeholder="Enter your Email" ><br>
                <p class="warning"> ${mess}</p>
              
                <button type="submit" class="button"><span>Find</span></button><br/>
                <button type="submit" class="button" formaction="<c:url value="/login/login.do"/>"><span>Cancel</span></button>
       
        </div>
        </form>
            </div>
    </div>
</body>
</html>

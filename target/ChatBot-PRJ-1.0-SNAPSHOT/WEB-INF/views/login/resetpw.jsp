<%-- 
    Document   : resetpw
    Created on : Jun 9, 2022, 12:07:22 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${root}/css/resetpw.css" rel="stylesheet" type="text/css"/>
        <link href="${root}/css/same_login.css" rel="stylesheet" type="text/css"/>
        <title>reset password</title>
    </head>
    <body style="background-image: url(/chatbot-test/images/layout.jpg); background-size: cover;">
        <div class="reset_form" >
            <div class="flex flex-col justify-conter items-center px-5">
            <div class="title">Choose new password</div>
        
            <form action="${pageContext.request.contextPath}/login/resetpass.do">
          
            <input class="input" type="password" placeholder="Enter new password" name="pw" /><br>
     
            <input class="input" type="password" placeholder="Verify Password" name="vpw" />    <br>
            <input type ="hidden" name="id" value="${id}"<br>
           <div class="warning" >${messpassd}</div><br>
           <button class="button" type ="submit" value="Reset password"><span>Reset password</span></button>
            </form>
            </div>
        </div>
    </body>
</html>

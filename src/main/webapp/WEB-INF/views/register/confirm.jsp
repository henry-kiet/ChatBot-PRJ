<%-- 
    Document   : confirm
    Created on : Jun 6, 2022, 7:57:14 PM
    Author     : SE151515 Cao Trong Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="${root}/css/confirm.css" rel="stylesheet" type="text/css"/>
        <link href="${root}/css/same_login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body style="background-image: url(/chatbot-test/images/layout.jpg); background-size: cover;">
        <!--        header-->
        <div class="comfirm_form">
           
            <div class="flex flex-col justify-conter items-center px-5">
            <form action="${pageContext.request.contextPath}/register/confirm.do" method="post">
                <div class="title" >Verify Account</div>
                <br>
                <div class ="message">Verification code </div>
                <input type="hidden" name="id" value="${id}">
                <input type="hidden" name="name" value="${name}">
                <input type="hidden" name="address" value="${address}">
                <input type="hidden" name="phone" value="${phone}">
                <input type="hidden" name="email" value="${email}">
                <input type="hidden" name="gender" value="${gender}">
                <input type="hidden" name="uname" value="${uname}">
                <input type="hidden" name="pw" value="${pw}">
                <input type="hidden" name="role" value="${role}">
                <input type="hidden" name="vcode" value="${vcode}">
                <input class="input" id="ab1" type="text" placeholder="Enter your code " name="inputcode"><br/> <br/> 
                <div class="warning">${codemess}</div>
                <button  class="button" type="submit"><span>Confirm</span></button>
                
            </form>
        </div>
        </div>
              
</html>
<div class="warning" >${mess}</div><br/>
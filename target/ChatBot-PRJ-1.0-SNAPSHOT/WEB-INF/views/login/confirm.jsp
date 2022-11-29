<%-- 
    Document   : comfirm
    Created on : Jul 15, 2022, 11:23:45 AM
    Author     : nldta
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
<!--                header-->
        <div class="comfirm_form">
           
            <div class="flex flex-col justify-conter items-center px-5">
            <form action="${pageContext.request.contextPath}/login/confirm.do" method="post">
                <div class="title">Verify Account</div>
                <br>
                <div class ="message">Verification code </div>
                <input  type="hidden" name="id" value="${id}">
                <input type="hidden" name="vcode" value="${vcode}">
                
             
                <input class="input" id="ab1" type="text" placeholder="Enter your code " name="inputcode"><br/> <br/> 
                <p class="warning"> ${codemess}</p>
                <button  class="button" type="submit"><span>Confirm</span></button>    <br>
                 
            </form>
        </div>
        </div>
              
</html>
<p >${mess}</p><br/>

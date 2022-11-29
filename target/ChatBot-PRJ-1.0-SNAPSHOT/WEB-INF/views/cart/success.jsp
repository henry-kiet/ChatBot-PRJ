<%-- 
    Document   : success
    Created on : Jun 15, 2022, 10:59:14 PM
    Author     : quckh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p style="font-size: 50px; text-align: center">Thanks for your shopping !</p>
        <p><img src="${root}/images/logo.png" style="height: 110; margin-left: 45%"/></p>

        <div style="background-color: #F2C2DC;height: fit-content; width: 500px; margin-left: 35%; padding: 15px;">
            <h3 style="font-weight: bold">${name}</h3>
            <ul>      
                <li class="text-black mt-1"> Phone number: ${phone}</li>
                <li class="text-black mt-1"> Email: ${email}</li>
                <li class="text-black mt-1"> Address: ${address}</li>
            </ul>
                <h3>Your order information: ${orderId}</h3> 
        </div>
    </body>
</html>

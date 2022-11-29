<%-- 
    Document   : confirmcheckout
    Created on : Jun 14, 2022, 3:29:55 PM
    Author     : Admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body >
        <form action="${pageContext.request.contextPath}/cart/success.do">
            <p style="font-size: 50px; text-align: center">Check out your purchase !</p>
            <p><img src="${root}/images/logo.png" style="height: 110; margin-left: 45%"/></p>

            <div style="background-color: #F2C2DC;height: fit-content; width: 500px; margin-left: 33%; padding: 15px;">
                <h3 style="font-weight: bold">${name}</h3>
                <ul>      
                    <li class="text-black mt-1"> Phone number: ${phone}</li>
                    <li class="text-black mt-1"> Email: ${email}</li>
                    <li class="text-black mt-1"> Address: ${address}</li>
                </ul>
                <table class="table table-hover">
                    <tbody>
                        <tr>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Discount</th>
                            <th>Price</th>
                        </tr>
                        <c:forEach var="item" items="${cart.items}" varStatus="loop">  
                            <tr>
                                <td>${item.name}</td>
                                <td>x ${item.quantity}</td>
                                <td> ${item.discount * 100}% </td>
                                <td><fmt:formatNumber value="${item.cost}" pattern="$#,##0.00"/></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td><a href="<c:url value="/cart/index.do"/>">Edit your cart</a></td>
                            <td></td>
                            <td style="font-weight: bold">Total:</td>
                            <td style="font-weight: bold"><fmt:formatNumber value="${cart.total}" pattern="$#,##0.00" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><button>Purchase</button></td>
                        </tr>
                    </tbody>            
                </table>       
            </div>
            <br/>
        </form>
    </body>
</html>

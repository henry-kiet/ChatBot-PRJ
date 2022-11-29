<%-- 
    Document   : manageOrders
    Created on : Jun 6, 2022, 6:20:59 PM
    Author     : quckh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="padding: 4px;">Manage Orders</h1>
        <hr/>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Status</th>
                    <th>Customer ID</th>
                    <th>Staff ID</th>
                    <th>Ship To</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${olist}" varStatus="loop">
                    <tr>
                        <th>${loop.count}</th>
                        <th>${order.orderId}</th>
                        <th>${order.date}</th>
                        <th>${order.status}</th>
                        <th>${order.customerId}</th>
                        <th>${order.staffId}</th>
                        <th>${order.shipToAddress}</th>    
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>

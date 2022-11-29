<%-- 
    Document   : manageCustomers
    Created on : Jun 6, 2022, 6:20:34 PM
    Author     : quckh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="padding: 4px;">Manage Customers</h1>
        <hr/>
        <div class="card mb-4">
            <div class="card-header">
                <i class="fas fa-table me-1"></i>Data Customers </div>

            <div class="card-body">               
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Email</th>
                            <th>Gender</th>
                            <th>Username</th>
                            <th style="padding-left: 20px;">Manage</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="clist" items="${clist}" varStatus="loop">
                            <tr>
                                <td>${loop.count}</td>
                                <td>${clist.name}</td>
                                <td>${clist.phone}</td>
                                <td>${clist.address}</td>
                                <td>${clist.email}</td>
                                <td>${clist.gender}</td>
                                <td>${clist.userName}</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/admin/deleteCustomer.do" method="post">
                                        <input type="hidden" value="${clist.id}" name="idCus"/>
                                        <button class="manage_btn" style="width: 100px;border-radius: 25px;"><i class="bi bi-eraser"></i> Ban</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                </table>
            </div>
        </div>
    </tbody>
</table>
</body>
</html>

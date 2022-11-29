<%-- 
    Document   : manageEmployees
    Created on : Jun 6, 2022, 6:18:46 PM
    Author     : quckh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <meta charset="UTF-8">

        <title>JSP Page</title>
        <link href="${root}/css/manageEmployee.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1 style="padding: 4px;">Manage Employees</h1>
        <hr/>
        <div class="card mb-4">
            <div class="card-header">
                <i class="fas fa-table me-1"></i>Data Employees  </div>
                
            <div class="card-body">
                
        <form action="${pageContext.request.contextPath}/admin/generateNewEmployee.do">
            <button class="generate_button" ><span>New employee</span> </button>       
        </form>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Phone number</th>
                    <th>Address</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Manage</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="elist" items="${elist}" varStatus="loop">
                    <tr>
                        <td>${loop.count}</td>
                        <td>${elist.name}</td>
                        <td>${elist.phone}</td>
                        <td>${elist.address}</td>
                        <td>${elist.email}</td>
                        <td>${elist.gender}</td>
                        <td>${elist.userName}</td>
                        <td>${elist.password}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/admin/updateEmployee.do" method="post">
                                <input type="hidden" value="${elist.id}" name="id"/>
                                <button class="manage_btn"><i class="bi bi-pencil"></i> Update</button>
                            </form>
                                <form action="${pageContext.request.contextPath}/admin/deleteEmployee.do" method="post">
                                <input type="hidden" value="${elist.id}" name="id"/>
                                <button class="manage_btn"><i class="bi bi-eraser"></i> Delete</button>
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

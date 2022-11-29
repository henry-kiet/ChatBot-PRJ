<%-- 
    Document   : manageProducts
    Created on : Jun 6, 2022, 6:20:47 PM
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
        <link href="${root}/css/btnChatbotManage.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1 style="padding: 4px;">Manage Products</h1>
        <hr/>
        <form action="${pageContext.request.contextPath}/admin/addNewProduct.do">
            <button class="generate_button" ><span>New product</span> </button>       
        </form>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>ID</th>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Discount</th>
                    <th style="text-align: center">Operation</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${plist}" varStatus="loop">
                    <tr>
                        <td>${loop.count}</td>
                        <td>${product.id}</td>
                        <td><image src="${product.img}" style="width: 10%;"/></td>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td><fmt:formatNumber value="${product.price}" pattern="$#,##0.00"/></td>
                        <td><fmt:formatNumber value="${product.discount}" pattern="#%" /></td>
                        <td>
                            <form>
                                <input type="hidden" value="${product.id}" name="pid"/>
                                <button formaction="${pageContext.request.contextPath}/admin/updateProduct.do" style="width: 100px;border-radius: 25px;">Update</button>
                                <button formaction="${pageContext.request.contextPath}/admin/deleteProduct.do"style="width: 100px;border-radius: 25px;">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>

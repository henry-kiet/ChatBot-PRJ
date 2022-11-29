<%-- 
    Document   : manageChatbot
    Created on : Jul 1, 2022, 10:48:45 AM
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
        <h1 style="padding: 4px;">Manage Chatbot</h1>
        <hr/>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Keyword</th>
                    <th>Content</th>
                    <th style="padding-left: 50px;">Operation</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="c" items="${cblist}" varStatus="loop">
                    <tr>
                        <td>${loop.count}</td>
                        <td>${c.keyword}</td>
                        <td>${c.content}</td>
                        <td>
                            <form>
                                <input type="hidden" value="${c.keyword}" name="key"/>
                                <button formaction="${pageContext.request.contextPath}/admin/updateChatbot.do" style="width: 100px;border-radius: 25px;">Update</button>
                                <button formaction="${pageContext.request.contextPath}/admin/deleteChatbot.do" style="width: 100px;border-radius: 25px;">Delete</button>
                            </form>    
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form action="${pageContext.request.contextPath}/admin/addChatbot.do">
            <button class="generate_button" ><span>Add content</span> </button>       
        </form>
    </body>
</html>

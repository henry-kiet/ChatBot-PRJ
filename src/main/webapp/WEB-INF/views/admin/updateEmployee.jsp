<%-- 
    Document   : updateEmployee
    Created on : Jun 6, 2022, 8:55:10 PM
    Author     : quckh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${root}/css/generateNewEmployee.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/admin/updateEmployee_handler.do" method="post">
            <div class="register_form">
                <div style="margin-left: 32px; ">
                    <c:forEach var="elist" items="${elist}">
                        <input type="hidden" value="${elist.id}" name="id"/>
                        Name: 
                        <input style="margin-left: 66px;width: 400px; height: 50px; border-radius: 50px; text-indent: 20px; border: none;" type="text" name="name" value="${elist.name}" required/><br/><br/>
                        User name:
                        <input style="margin-left: 35px;width: 400px; height: 50px; border-radius: 50px; text-indent: 20px;border: none;" type="text" name="userName" value="${elist.userName}" required /><br/>${messuname}<br/>
                        Password:
                        <input style="margin-left: 41px;width: 400px; height: 50px; border-radius: 50px; text-indent: 20px;border: none;" type="password" name="password" value="${elist.password}" required/><br/><br/>
                        Verify Password:
                        <input style="margin-left: 2px;width: 400px; height: 50px; border-radius: 50px; text-indent: 20px;border: none;" type="password" name="vPassword" placeholder="Verify employee password" required /><br/>${messpass}<br/>
                        Address:
                        <input style="margin-left: 51px;width: 400px; height: 50px; border-radius: 50px; text-indent: 20px;border: none;" type="text" maxlength="100" name="address" value="${elist.address}" required/><br/><br/>          
                        Email:
                        <input style="margin-left: 68px;width: 400px; height: 50px; border-radius: 50px; text-indent: 20px;border: none;" type="text"  name="email" value="${elist.email}" required /><br/><br/>
                        Phone number:
                        <input style="margin-left: 12px;width: 170px; height: 50px; border-radius: 50px; text-indent: 20px;border: none;" type="text" onkeypress='validatePhone(event)' maxlength="10" name="phoneNumber" value="${elist.phone}" required/>
                        Gender:
                        <select name="gender" style="margin-left: 5px;width: 165px; height: 50px; border-radius: 50px; text-indent: 20px;border: none;">
                            <option>Male</option>
                            <option>Female</option>
                            <option>Other</option>
                        </select>
                        <br/>
                        <br/>
                    </c:forEach>
                </div>
                <button style=" margin-left: 190px;" class="register_button" type="submit"><span>Save</span></button><br/>
            </div>
        </form>


    </body>
</html>

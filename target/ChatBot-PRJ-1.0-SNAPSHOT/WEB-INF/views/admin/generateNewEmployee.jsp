<%-- 
    Document   : generateNewEmployee
    Created on : Jun 6, 2022, 8:00:37 PM
    Author     : quckh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>JSP Page</title>
        <link href="${root}/css/generateNewEmployee.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/admin/generateNewEmployee_handler.do" method="post">
            <div class="register_form">
                <div style="margin-left: 32px; ">
                    Name: 
                    <input style="margin-left: 95px;width: 400px; height: 50px; border-radius: 50px; text-indent: 20px; border: none;" 
                           type="text" name="name" placeholder="Enter employee name" required/><br/><br/>
                    User name:
                    <input style="margin-left: 50px;width: 400px; height: 50px; border-radius: 50px; text-indent: 20px;border: none; margin-bottom: 10px;" 
                           type="text" name="uName" placeholder="Enter user name" required /><br/>
                    <div style="color:red; margin: 10px 0 10px 0;">${messuname}</div>
                    Password:
                    <input style="margin-left: 60px;width: 400px; height: 50px; border-radius: 50px; text-indent: 20px;border: none;" 
                           type="password" name="password" placeholder="Enter employee password" required/><br/><br/>
                    Verify Password:
                    <input style="margin-left: 10px;width: 400px; height: 50px; border-radius: 50px; text-indent: 20px;border: none; margin-bottom: 10px;" 
                           type="password" name="vpassword" placeholder="Verify employee password" required />
                   <div style="color:red ;margin: 10px 0 10px 0;" >${messpass}</div>
                    Address:
                    <input style="margin-left: 70px;width: 400px; height: 50px; border-radius: 50px; text-indent: 20px;border: none;" 
                           type="text" name="address" placeholder="Enter employee address"  maxlength="300"  required/><br/><br/>          
                    Email:
                    <input style="margin-left: 90px;width: 400px; height: 50px; border-radius: 50px; text-indent: 20px;border: none;" 
                           type="email"  name="email" placeholder="Enter employee email" required /><br/><br/>
                    Phone number:
                    <input style="margin-left: 20px;width: 180px; height: 50px; border-radius: 50px; text-indent: 20px;border: none;" 
                           type="text" onkeypress='validatePhone(event)' maxlength="10" name="phone" placeholder="Enter phone number" required/>
                    Gender:
                    <select name="gender" style="margin-left: 5px;width: 165px; height: 50px; border-radius: 50px; text-indent: 20px;border: none;">
                        <option>Male</option>
                        <option>Female</option>
                        <option>Other</option>
                    </select>
                    <br/>
                    <br/>
                </div>
                <button style=" margin-left: 190px;" class="register_button" type="submit"><span>Generate</span></button><br/>

            </div>
        </form>
    </body>
</html>

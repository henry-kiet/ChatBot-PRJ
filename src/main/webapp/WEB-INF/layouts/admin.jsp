<%-- 
    Document   : main
    Created on : May 23, 2022, 11:34:44 PM
    Author     : quckh
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entities.Chat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<html>
    <head>
        <title>Brown Ted</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>   
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/material-design-icons/3.0.1/iconfont/material-icons.min.css">
        <link href="${root}/css/layout.css" rel="stylesheet" type="text/css"/>
        <link href="${root}/css/admin.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>

                <nav class="navbar navbar-inverse" style="background-color:#F2C2DC;border:none">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>                        
                            </button>
                        </div>
                        <div class="collapse navbar-collapse" id="myNavbar"  >
                            <ul class="nav navbar-nav" style="display: inline;">
                                <li><a style="; padding-right: 5px;" class="navbar-collapse" href="<c:url value="/"/>">
                                        <img src="${root}/images/logo.png" height="50"/>
                                    </a></li>                                            
                                </ul>
                            
                            
                        </div>
                    </div>
                </nav>  
                <div class="container-fluid">
                    <div class="row content">
                        <div class="menu col-sm-2 sidenav" >
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <h3> Welcome ${roleuser} </h3>                                    
                                </li>
                                <hr/>
                                <br/>
                                <form action="${pageContext.request.contextPath}/product/search.do">                                  
                                    <div class="input-group">
                                        <div class="search_form" ><input type="text" placeholder="Search product..." aria-label="Search for..." aria-describedby="btnNavbarSearch"  name="productName"/>
                                        <button class="btn btn-primary" id="btnNavbarSearch" type="submit"><i class="bi bi-search"></i></button></div>
                                    </div>
                                </form>
                                    
                                <div class="nav-link">
                                    <a href="<c:url value="/"/>" class="text-dark"><i class="bi bi-house-door-fill"></i> Home page</a>  
                                </div>
                                
                                <div class="nav-link">
                                    <li><a href="<c:url value="/product/index.do"/>"class="text-dark"><i class="bi bi-bag-fill"></i> Product list</a></li>
                                </div>
                                
                                <div class="nav-link">
                                <a  href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                    <div class="accordion text-dark"><i class="bi bi-table"></i> Tables </div>        
                                    
                                </a>
                                    <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                        <nav >
                                            <form action="${pageContext.request.contextPath}/admin/manageEmployees.do">
                                                <button class="tables text-dark">Manage employees</button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/admin/manageCustomers.do">
                                                <li> <button class="tables text-dark">Manage customers</button></li>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/admin/manageProducts.do">
                                                <li> <button class="tables text-dark">Manage products</button></li>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/admin/manageOrders.do">
                                                <li> <button class="tables text-dark">Manage orders</button></li>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/admin/manageChatbot.do">
                                                <li> <button class="tables text-dark">Manage Chatbot</button></li>
                                            </form>
                                        </nav>
                                    </div>
                                </div>                
                                <div class="nav-link">
                                    <li><a href=""class="text-dark"><i class="bi bi-gear-fill"></i> Setting</a></li>
                                </div>                
                                              
                                <div class="nav-link">
                                    <li><a href="<c:url value="/login/logout.do"/>"class="text-dark"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                                </div>
                                
                            </ul><br/>
                        </div>
                        <div class="col-sm-10">
                            <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp"/>
                        </div>
                    </div>
                </div>

        
                
               

    </body>
    <!-- Chat bot-->
    <section>
        <button class="chat-btn"> 
            <img src="${root}/images/chatbot_icon.png" width="100%"/>
        </button> 
        <div class="chat-popup">
            <div class="chat-header">
                <div style="width: 40px; height: 40px; border-radius: 50%; border: 2px solid #05c702; padding: 2px 0 0 1px;">
                    <img src="${root}/images/logo.png" class="img-fluid rounded-circle" style="margin: auto"/>
                </div>
                <div class="header-text">ChatBot</div>
                <button class="close-btn"><i class="material-icons"> close</i></button>
            </div>
            <div class="chat chat-area" id="content">
                <div class="incoming-msg">
                    <span class="bot-msg">Hi, I'm Chatbot</span>
                    <span class="bot-msg">How can I help you?</span>
                </div>
            </div>
            <c:if test="${sessionScope.CHAT_SESSION != null}">
                <c:if test="${not empty sessionScope.CHAT_SESSION}">
                    <c:forEach var="chat" items="${sessionScope.CHAT_SESSION}">
                        <c:set var="btMsg" value="${chat.getBotMsg()}"/>
                        <c:set var="botMsgSplit" value="${fn:split(btMsg, '-')}"/>
                        <c:choose>
                            <c:when test="${fn:contains(btMsg, '-')}"> 
                                <c:choose>
                                    <c:when test="${not empty chat.getuInput()}">
                                        <script type="text/javascript">
                                            chatArea = document.querySelector('.chat-area');
                                            var myMsg = `<div class="out-msg">
                                            <span class="my-msg">${chat.getuInput()}</span>
                                            </div>`;
    
                                            chatArea.insertAdjacentHTML("beforeend", myMsg);
                                            var botMsg = `<div class="incoming-msg">
                                            <span class="bot-msg">
                                            <form method ="post" action="/chatbot-test/product/detail.do">
                                            <button style="background:white; color:black;" type="submit">
                                            <input type="hidden" value="${botMsgSplit[0]}" name="id"/>
                                            <img  src="/chatbot-test/images/products/${botMsgSplit[0]}.jpg" width=50%/><br/>
                                            Discount:${botMsgSplit[3]}%<br/>\n
                                            Price: <strike><fmt:formatNumber value="${botMsgSplit[2]}" pattern="$#,##0.00" /></strike></br>
                                            <span style="color:red;font-size:20px;">\n<fmt:formatNumber value="${botMsgSplit[4]}" pattern="$#,##0.00" /></span><br/>
                                            </button>\n
                                            <input name='quantity' type='hidden' value='1'/>
                                            <button formaction=\"/chatbot-test/cart/add_chatbot.do\" style=\"border-radius: 5px;background: #212529;color: #fff;margin: 10px 5px 0 7px;font-size: 15px;padding: 2px 27px;border: solid 2px #212529;transition: all 0.5s ease-in-out 0s;\" type=\"submit\" class=\"round-black-btn\">Add to Cart</button>
                                            </form>
                                            </span>
                                            </div>`;
                                            chatArea.insertAdjacentHTML("beforeend", botMsg);

                                            chatArea.scrollTop = chatArea.scrollHeight;
                                        </script>
                                    </c:when>
                                    <c:otherwise>
                                        <script type="text/javascript">
                                            chatArea = document.querySelector('.chat-area');
                                            var botMsg = `<div class="incoming-msg">
                                            <span class="bot-msg">
                                            <form method ="post" action="/chatbot-test/product/detail.do">
                                            <button style="background:white; color:black;" type="submit">
                                            <input type="hidden" value="${botMsgSplit[0]}" name="id"/>
                                            <img  src="/chatbot-test/images/products/${botMsgSplit[0]}.jpg" width=50%/><br/>
                                            Discount:${botMsgSplit[3]}%<br/>\n
                                            Price: <strike><fmt:formatNumber value="${botMsgSplit[2]}" pattern="$#,##0.00" /></strike></br>
                                            <span style="color:red;font-size:20px;">\n<fmt:formatNumber value="${botMsgSplit[4]}" pattern="$#,##0.00" /></span><br/>
                                            </button>\n
                                            <input name='quantity' type='hidden' value='1'/>
                                            <button formaction=\"/chatbot-test/cart/add_chatbot.do\" style=\"border-radius: 5px;background: #212529;color: #fff;margin: 10px 5px 0 7px;font-size: 15px;padding: 2px 27px;border: solid 2px #212529;transition: all 0.5s ease-in-out 0s;\" type=\"submit\" class=\"round-black-btn\">Add to Cart</button>
                                            </form>
                                            </span>
                                            </div>`;
                                            chatArea.insertAdjacentHTML("beforeend", botMsg);

                                            chatArea.scrollTop = chatArea.scrollHeight;
                                        </script>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <script type="text/javascript">
                                    chatArea = document.querySelector('.chat-area');
                                    var myMsg = `<div class="out-msg">
                                    <span class="my-msg">${chat.getuInput()}</span>
                                    </div>`;
                                    chatArea.insertAdjacentHTML("beforeend", myMsg);

                                    var botMsg = `<div class="incoming-msg">
                                    <span class="bot-msg">${chat.getBotMsg()}</span>
                                    </div>`;
                                    chatArea.insertAdjacentHTML("beforeend", botMsg);

                                    chatArea.scrollTop = chatArea.scrollHeight;
                                </script>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:if>
            </c:if>
            <div class="input-area">
                <input name="msg" type="text" id="txtmsg" placeholder="Enter your message here..."/>
                <button class="submit-btn"  style="padding-left: 8px"> <i class="material-icons"> send</i></button>
            </div>
        </div>



    </section>
    <script>
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
acc[i].addEventListener("click", function() {
this.classList.toggle("active");
var panel = this.nextElementSibling;
if (panel.style.display === "block") {
panel.style.display = "none";
} else {
panel.style.display = "block";
}
});
}
    </script>            
    <script src="${root}/js/main.js"></script>

</html>

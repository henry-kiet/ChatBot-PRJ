<%-- 
    Document   : checkbill
    Created on : Jun 29, 2022, 12:16:35 AM
    Author     : SE151515 Cao Trong Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link href="${root}/css/emp.css" rel="stylesheet" type="text/css"/>


        <script>
            $(document).ready(function () {
                $("#flip").click(function () {
                    $("#panel").slideToggle("slow");
                });
            });

            $(document).ready(function () {
                $("#flip2").click(function () {
                    $("#panel2").slideToggle("slow");
                });
            });

            $(document).ready(function () {
                $("#flip3").click(function () {
                    $("#panel3").slideToggle("slow");
                });
            });
        </script>

    </head>
    <body>

        <!------------------------------------------------------------------------------------------------->       
        <h1>Check bills</h1>
        <hr>
        <div>
            <h2>New</h2>
            <div>
                <button id="flip" class="btn2"  >Show/Hide</button><br>
                <div id="panel">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th style="text-align: center;">No.</th>
                                <th style="text-align: center;">Order ID</th>
                                <th style="text-align: center;">Date</th>
                                <th style="text-align: center;">status</th>
                                <th style="text-align: center;">Customer id</th>
                                <th style="text-align: center;">Address</th>
                                <th style="text-align: center;">Operate</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="orderheader" items="${ohnew}" varStatus="loop">

                                <tr>
                            <form action="${pageContext.request.contextPath}/emp/update.do" method="post">
                                <td style="text-align: center;">${loop.count}</td>
                                <td style="text-align: center;">${orderheader.orderId}</td>
                                <td style="text-align: center;"><fmt:formatDate type = "date" 
                                                dateStyle = "short" timeStyle = "short" value = "${orderheader.date}" /></td>


                                <td style="text-align: center;">
                                    <select  name ="stt" id="abc">
                                        <option selected="selected">New</option>
                                        <option>Confirmed</option>
                                        <option>Canceled</option>
                                    </select>
                                </td>




                                <td style="text-align: center;">${orderheader.customerId}</td>
                                <td style="text-align: center;">${orderheader.shipToAddress}</td>
                                <td style="text-align: center;">


                                    <input type="hidden" value="${orderheader.orderId}" name="id"/> 
                                    <input type="hidden" value="${orderheader.customerId}" name="cusid"/>
                                    <input type="hidden" value="${orderheader.orderId}" name="orid"/> 
                                    <button class="manage_btn"><i class="bi bi-pencil"></i> Update</button>
                                    <button class="manage_btn" formaction="<c:url value="/emp/detail.do"/>"><i class="bi bi-eye"></i></i> View</button>
                                    
                                    
                            </form>

                            </td>
                            </form>
                            </tr>                             
                        </c:forEach> 
                            
                        </tbody>
                    </table>
                </div>
            </div>
        </div>



        <!------------------------------------------------------------------------------------------------->        
        <div>
            <h2>confirmed</h2>
            <div>
                <button id="flip2" class="btn2" >Show/Hide</button><br>
                <div id="panel2">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th style="text-align: center;">No.</th>
                                <th style="text-align: center;">Order ID</th>
                                <th style="text-align: center;">Date</th>
                                <th style="text-align: center;">status</th>
                                <th style="text-align: center;">Customer id</th>
                                <th style="text-align: center;">Address</th>
                                <th style="text-align: center;">Operate</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="orderheader" items="${ohcf}" varStatus="loop">

                                <tr>
                            <form action="${pageContext.request.contextPath}/emp/update.do" method="post">
                                <td style="text-align: center;">${loop.count}</td>
                                <td style="text-align: center;">${orderheader.orderId}</td>
                                <td style="text-align: center;"><fmt:formatDate type = "date" 
                                                dateStyle = "short" timeStyle = "short" value = "${orderheader.date}" /></td>


                                <td style="text-align: center;">
                                    <select  name ="stt" id="abc">
                                        <option >New</option>
                                        <option selected="selected">Confirmed</option>
                                        <option>Canceled</option>
                                    </select>
                                </td>




                                <td style="text-align: center;">${orderheader.customerId}</td>
                                <td style="text-align: center;">${orderheader.shipToAddress}</td>
                                <td style="text-align: center;">


                                    <input type="hidden" value="${orderheader.orderId}" name="id"/> 
                                    <input type="hidden" value="${orderheader.customerId}" name="cusid"/>
                                    <input type="hidden" value="${orderheader.orderId}" name="orid"/> 
                                    <button class="manage_btn"><i class="bi bi-pencil"></i> Update</button>
                                    <button class="manage_btn" formaction="<c:url value="/emp/detail.do"/>"><i class="bi bi-eye"></i></i> View</button>
                                    
                                    
                            </form>

                            </td>
                            </form>
                            </tr>                             
                        </c:forEach> 
                            
                        </tbody>
                    </table>
                </div>
            </div>
        </div>



        <!------------------------------------------------------------------------------------------------->

        <div>
            <h2>cancel</h2>
            <div>
                <button id="flip3" class="btn2" >Show/Hide</button><br>
                <div id="panel3">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th style="text-align: center;">No.</th>
                                <th style="text-align: center;">Order ID</th>
                                <th style="text-align: center;">Date</th>
                                <th style="text-align: center;">status</th>
                                <th style="text-align: center;">Customer id</th>
                                <th style="text-align: center;">Address</th>
                                <th style="text-align: center;">Operate</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="orderheader" items="${ohcel}" varStatus="loop">

                                <tr>
                            <form action="${pageContext.request.contextPath}/emp/update.do" method="post">
                                <td style="text-align: center;">${loop.count}</td>
                                <td style="text-align: center;">${orderheader.orderId}</td>
                                <td style="text-align: center;"><fmt:formatDate type = "date" 
                                                dateStyle = "short" timeStyle = "short" value = "${orderheader.date}" /></td>


                                <td style="text-align: center;">
                                    <select  name ="stt" id="abc">
                                        <option >New</option>
                                        <option>Confirmed</option>
                                        <option selected="selected">Canceled</option>
                                    </select>
                                </td>




                                <td style="text-align: center;">${orderheader.customerId}</td>
                                <td style="text-align: center;">${orderheader.shipToAddress}</td>
                                <td style="text-align: center;">


                                    <input type="hidden" value="${orderheader.orderId}" name="id"/> 
                                    <input type="hidden" value="${orderheader.customerId}" name="cusid"/>
                                    <input type="hidden" value="${orderheader.orderId}" name="orid"/> 
                                    <button class="manage_btn"><i class="bi bi-pencil"></i> Update</button>
                                    <button class="manage_btn" formaction="<c:url value="/emp/detail.do"/>"><i class="bi bi-eye"></i></i> View</button>
                                    
                                    
                            </form>

                            </td>
                            </form>
                            </tr>                             
                        </c:forEach> 
                            
                        </tbody>
                    </table>
                </div>
            </div>
        </div>




    </body>
</html>

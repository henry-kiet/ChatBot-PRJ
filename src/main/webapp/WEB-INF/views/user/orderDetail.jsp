

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>

<section style="background-color: #eee;">
    <div class="container py-5">
        <div class="row">
            <div class="col">
                <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item active" aria-current="page">Order</li>
                    </ol>
                </nav>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-4">
                <div class="card mb-4">          
                    <div class="card-body">
                        <h3 style="text-align: center;">Order</h3>
                        <br>
                        
                        <c:forEach var="x" items="${orlist}" varStatus="loop">
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0"><img src="${x.img}" width="100%" /></p>
                            </div>
                            <div class="col-sm-3">
                                <p class="mb-0">X${x.quantity}</p>
                            </div>
                            
                            <div class="col-sm-3">
                                <p class="text-muted mb-0"><fmt:formatNumber value="${x.price * (1-x.discount)}" pattern="$#,##0.00" /></p>
                            </div>
                        </div>
                        <hr>
                        </c:forEach>  
                    </div>
                </div>
                <div class="card mb-4 mb-lg-0">
                    <div>
                        <h3 style="text-align: right; margin-right: 20px; margin-top: 2px;">Total : <fmt:formatNumber value="${total}" pattern="$#,##0.00" /></h3>              
                    </div>          
                </div>
            </div>
            <div class="col-lg-8">
                <div class="card mb-4">
                    <div class="card-body">
                        <h3 style="text-align: center;">Customer</h3>
                        <br>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Full Name</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0">${name}</p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Email</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0">${email}</p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Phone</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0">${phone}</p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Address</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0">${addr}</p>
                            </div>
                        </div>
                    </div>      
                </div>
            </div>
        </div>
    </div>
</section>                           
</html>                            
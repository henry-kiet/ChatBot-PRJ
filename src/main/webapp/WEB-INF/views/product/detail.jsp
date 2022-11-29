<%-- 
    Document   : productdetail
    Created on : Jun 3, 2022, 1:45:10 AM
    Author     : SE151515 Cao Trong Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link href="${root}/css/details.css" rel="stylesheet" type="text/css"/>
</head>
<div class="pd-wrap">
    <div class="container">
        <div class="heading-section">
            <h2>Product Details</h2>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div id="slider" class="owl-carousel product-slider">
                    <div class="item">
                        <img src="${list.img}" width="90%" />
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="product-dtl">
                    <div class="product-info">
                        <div class="product-name">${list.name}</div>
                        <div class="reviews-counter">
<!--                            <div class="rate">
                                <input type="radio" id="star5" name="rate" value="5" checked />
                                <label for="star5" title="text">5 stars</label>
                                <input type="radio" id="star4" name="rate" value="4" checked />
                                <label for="star4" title="text">4 stars</label>
                                <input type="radio" id="star3" name="rate" value="3" checked />
                                <label for="star3" title="text">3 stars</label>
                                <input type="radio" id="star2" name="rate" value="2" />
                                <label for="star2" title="text">2 stars</label>
                                <input type="radio" id="star1" name="rate" value="1" />
                                <label for="star1" title="text">1 star</label>
                            </div>
                            <span>3 Reviews</span>-->
                        </div>
                        <c:if test="${list.discount > 0/100}">
                            <div class="product-price-discount" style="color:red;font-size:30px;"> <fmt:formatNumber value="${list.price*(1 - list.discount)}" pattern="$#,##0.00" /><span class="line-through"><fmt:formatNumber value="${list.price}" pattern="$#,##0.00" /></span></div>
                            </c:if>
                            <c:if test="${list.discount == 0/100}">
                            <h1>Price: <fmt:formatNumber value="${list.price}" pattern="$#,##0.00" /></h1><br/>
                        </c:if>
                    </div>

                    <p>${list.description}</p>
                    <div class="product-count">
                        <label for="size">Quantity</label>
                        <form action="${pageContext.request.contextPath}/cart/add.do"  class="display-flex" method="post">
                            <input class="qtyminus" onclick="var result = document.getElementById('quantity'); var qty = result.value; if (qty > 1) result.value--; return false;" type='button' value='-' />
                            <input class="qty" id='quantity' min='1' name='quantity' type='text' value='1' />
                            <input class="qtyplus" onclick="var result = document.getElementById('quantity');
                                    var qty = result.value;
                                    if (!isNaN(qty))
                                        result.value++;
                                    return false;" type='button' value='+' />
                            <input type="hidden" name="id" value="${list.id}" >
                            </div>
                            <button type="submit" class="round-black-btn">Add to Cart</button>
                        </form>


                    </div>
                </div>

            </div>
        </div>
    </div>
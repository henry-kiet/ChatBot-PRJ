<%-- 
    Document   : updateProduct
    Created on : Jun 30, 2022, 3:48:41 PM
    Author     : quckh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <form>
        <section style="background-color: #eee;">
            <div class="container py-5">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                            <ol class="breadcrumb mb-0">
                                <li class="breadcrumb-item active" aria-current="page">Manage Product</li>
                            </ol>
                        </nav>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-4">
                        <div class="card mb-4">          
                            <div class="card-body">
                                <h3 style="text-align: center;">Product</h3>
                                <br>

                                <c:forEach var="x" items="${productList}" varStatus="loop">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0"><img src="${x.img}" width="100%" /></p>
                                        </div>
                                    </div>
                                    <hr>  
                                </div>
                            </div>
                        </div>
                        <form>

                            <div class="col-lg-8">
                                <div class="card mb-4">
                                    <div class="card-body">
                                        <h3 style="text-align: center;">Detail</h3>
                                        <br>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <p class="mb-0">Name</p>
                                            </div>
                                            <div class="col-sm-9">
                                                <p class="text-muted mb-0"><input type="text" name="productName" value="${x.name}"required/></p>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <p class="mb-0">Description</p>
                                            </div>
                                            <div class="col-sm-9">
                                                <p class="text-muted mb-0"><input type="text" name="productDescription" value="${x.description}"required/></p>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <p class="mb-0">Price</p>
                                            </div>
                                            <div class="col-sm-9">
                                                <p class="text-muted mb-0"><input step=".01" type="number" name="productPrice" value="${x.price}"required/></p>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <p class="mb-0">Discount</p>
                                            </div>
                                            <div class="col-sm-9">
                                                <p class="text-muted mb-0"><input type="text" min="0" max="1" step=".01" name="productDiscount" value="${x.discount*100}"required/> </p>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <p class="mb-0">Image</p>
                                            </div>
                                            <div class="col-sm-9">
                                                <p class="text-muted mb-0"><input type="text" name="image" value="${x.img}"required/> </p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-9">
                                                <input type="hidden" value="${x.id}" name="id"/>
                                                <button formaction="${pageContext.request.contextPath}/admin/updateProductHandler.do">Save</button>
                                            </div>
                                        </div>

                                    </div> 
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </section>        
    </form>

</html>
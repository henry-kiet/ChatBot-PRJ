<%-- 
    Document   : addNewProduct
    Created on : Jul 18, 2022, 11:39:36 AM
    Author     : quckh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <section style="background-color: #eee;">
            <div class="container py-5">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                            <ol class="breadcrumb mb-0">
                                <li class="breadcrumb-item active" aria-current="page">Manage Chatbot</li>
                            </ol>
                        </nav>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-4">
                        <div class="card mb-4">          
                            <div class="card-body">
                                <h3 style="text-align: center;">Chatbot</h3>
                                <br>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">
                                            <img src="${root}/images/logo.png" alt=""/>
                                        </p>
                                    </div>
                                </div>
                                <hr>  
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="card mb-4">
                            <div class="card-body">
                                <h3 style="text-align: center;">Chatbot</h3>
                                <br>
                                <form>

                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Name</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0"><input type="text" name="name"required/></p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Description</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0"><input type="text" name="description"required/></p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Price</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0"><input type="number" name="price"required/></p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Discount</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0"><input type="text" name="discount"required/></p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Image</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p class="text-muted mb-0"><input type="text" name="image"required/></p>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-9">
                                            <button formaction="${pageContext.request.contextPath}/admin/addNewProduct_handler.do">Save</button>
                                        </div>   
                                    </div>
                                </form>
                            </div>      
                        </div>
                    </div>
                </div>
            </div>
        </section>        
</html>
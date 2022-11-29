<%-- 
    Document   : updateChatbot
    Created on : Jul 1, 2022, 11:07:15 AM
    Author     : quckh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
                                        <p class="mb-0">Keyword</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0"><input type="text" name="keyword" value="${keyword}"required/></p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Content</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0"><input type="text" name="content" value="${content}"required/></p>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-9">
                                        <button formaction="${pageContext.request.contextPath}/admin/updateChatbot_hander.do">Save</button>
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

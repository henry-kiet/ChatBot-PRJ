 <%-- 
    Document   : index
    Created on : Jun 28, 2022, 11:28:05 PM
    Author     : SE151515 Cao Trong Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
     <head>
        <link href="${root}/css/emp.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <body>
<section style="background-color: #eee;width: max-content;margin-top: 20px;">
  <div class="container py-5">
    <div class="row">
      <div class="col">
        <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
          <ol class="breadcrumb mb-0">
            <li class="breadcrumb-item active" aria-current="page">Your Profile</li>
          </ol>
        </nav>
      </div>
    </div>

    <div class="row">
      <div class="col-lg-4">
        <div class="card mb-4" style="border-radius: 50px;">
          <div class="card-body text-center">
            <img src="${root}/images/logo.png" height="50" alt="avatar"
              class="rounded-circle img-fluid" style="width: 150px;">
            <h5 class="my-3">${name}</h5>
            <p class="text-muted mb-1">${roleuser}</p>
            <p class="text-muted mb-4">${addr}</p>
            <div class="d-flex justify-content-center mb-2">

            </div>
          </div>
        </div>

      </div>
      <div class="col-lg-8">
        <div class="card mb-4">
          <div class="card-body">
            <div class="row">
              <div class="col-sm-3">
                  <p class="mb-0" style="margin-left: 10px;">Full Name</p>
              </div>
              <div class="col-sm-9">
                <p class="text-muted mb-0">${name}</p>
              </div>
            </div>
            <hr>
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0 "  style="margin-left: 10px;">Email</p>
              </div>
              <div class="col-sm-9">
                <p class="text-muted mb-0">${email}</p>
              </div>
            </div>
            <hr>
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0" style="margin-left: 10px;">Phone</p>
              </div>
              <div class="col-sm-9">
                <p class="text-muted mb-0">${phone}</p>
              </div>
            </div>
            <hr>
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"  style="margin-left: 10px;">Address</p>
              </div>
              <div class="col-sm-9">
                <p class="text-muted mb-0">${addr}</p>
              </div>
            </div>
            <hr>
                        <div class="row">
              <div class="col-sm-3">
                <p class="mb-0"  style="margin-left: 10px;">Salary</p>
              </div>
              <div class="col-sm-9">
                <p class="text-muted mb-0"><fmt:formatNumber value="${salary}" pattern="$#,##0.00" /></p>
              </div>
            </div>
          </div>
        </div>
          
          

      </div>
    </div>
  </div>
</section>
</html>
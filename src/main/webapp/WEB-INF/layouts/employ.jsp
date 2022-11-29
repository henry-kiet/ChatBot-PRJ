<%-- 
    Document   : employ
    Created on : Jun 28, 2022, 11:15:48 PM
    Author     : SE151515 Cao Trong Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
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
        
        
        <style>
.accordion {
  background-color: #eee;
  color: #444;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
  transition: 0.4s;
 
}

.active, .accordion:hover {
  background-color: #ccc; 
}

.panel {
    width: 20%;
  padding: 0 18px;
  display: none;
  background-color: white;
  overflow: hidden;
  float: right;
}
div.accordion.active:after {
    content: "\2212";
}
div.accordion:after {
    content: '\002B';
    color: #777;
    font-weight: bold;
    float: left;
    margin-left: 5px;
 
}
</style>
    </head> 
    <body>
 
                <div class="container-fluid">
                    <div class="row content">
                        <div class="menu col-sm-2 sidenav" >
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <h3> Welcome ${roleuser} </h3>                                    
                                </li>
                                <hr/>
                                <div class="logo"  >
                                    <a   href="<c:url value="/"/>">
                                        <img src="${root}/images/logo.png" height="60"/>
                                    </a>
                                   
                                </div>
                               
                                
                                 <div class="nav-link">
                                    <a href="<c:url value="/"/>" class="text-dark"><i class="bi bi-house-door-fill"></i> Home page</a>  
                                </div>
                                
                                
                                <div class="nav-link">
                                    <li><a href="<c:url value="/emp/index.do"/>"class="text-dark"><i class="bi bi-person-lines-fill"></i></i> Your profile</a></li>
                                </div>
                                
                                <div class="nav-link">
                                    <li><a href="<c:url value="/emp/checkbill.do"/>"class="text-dark"><i class="bi bi-card-checklist"></i></i> Check bills</a></li>
                                </div>
                               
                                
                                <div class="nav-link">
                                    <li><a href="<c:url value="/login/logout.do"/>"class="text-dark"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                                </div>

                                
                            </ul><br/>
                        </div>
                        <div class="col-sm-9">
                            <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp"/>
                          
                        </div>
                        
                    </div>
                </div>
                        
                        
                        
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
             </body>           
                        
                        
                        
</html>

<%-- 
    Document   : register
    Created on : May 23, 2022, 9:01:49 PM
    Author     : quckh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <link href="${root}/css/register2.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        
<!--      <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
      <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
        <title>Register</title>
    </head>
    <body style="background-image: url(/chatbot-test/images/layout.jpg); background-size: cover;">



        <main class="my-form">
            <div class="cotainer"  >
                <div class="row justify-content-center">
                    <div class="table">
                        <div class="card" style="background-color:#F2C2DC;">
                            
                     
                            <div class="head" >
                                <h2 style="font-weight: bold; font-size: 30px; text-align: center;">Register</h2>
                            </div>    
                    
                            
                        
                            <div class="card-body">
                                <form name="my-form" action="${pageContext.request.contextPath}/register/register.do" method="post">
<!------------------------->    
                                    <div class="form-group row">
                                        <label for="full_name" class="col-md-4 col-form-label" style="text-align: right;">Name</label>
                                        <div class="col-md-6">
                                            <input class="form-control" type="text" name="name" placeholder="Enter your name" required>
                                        </div>
                                    </div>
<!------------------------->
                                    <div class="form-group row">
                                        <label for="user_name" class="col-md-4 col-form-label" style="text-align: right;">User Name</label>
                                        <div class="col-md-6">
                                            <input class="form-control" type="text" name="uName" placeholder="Enter user name" required >
                                        </div>
                                    </div>
<!------------------------->
                                    <div class="form-group row">
                                        <label for="email_address" class="col-md-4 col-form-label" style="text-align: right;">E-Mail Address</label>
                                        <div class="col-md-6">
                                            <input class="form-control" type="email"  name="email" placeholder="Enter your email" required >
                                        </div>
                                    </div>
<!------------------------->
                                    <div class="form-group row">
                                        <label for="passwork" class="col-md-4 col-form-label" style="text-align: right;">Password</label>
                                        <div class="col-md-6">
                                            <input class="form-control" type="password" name="password" placeholder="Enter your password" required>
                                        </div>
                                    </div>
<!------------------------->
                                    <div class="form-group row">
                                        <label for="passwork" class="col-md-4 col-form-label" style="text-align: right;">Verify Password</label>
                                        <div class="col-md-6">
                                            <input class="form-control" type="password" name="vpassword" placeholder="Enter your password" required>
                                        </div>
                                    </div>  
<!------------------------->
                                    <div class="form-group row">
                                        <label for="phone_number" class="col-md-4 col-form-label" style="text-align: right;">Phone Number</label>
                                        <div class="col-md-6">
                                            <input class="form-control" type="text" onkeypress='validatePhone(event)' maxlength="10" name="phone" placeholder="Enter phone number" required>
                                        </div>
                                    </div>
<!------------------------->
                                    <div class="form-group row">
                                        <label for="present_address" class="col-md-4 col-form-label" style="text-align: right;"> Address</label>
                                        <div class="col-md-6">
                                            <input class="form-control" type="text" name="address" placeholder="Enter your address" maxlength="100" required>
                                        </div>
                                    </div>
<!------------------------->
                                    <div class="form-group row">
                                        <label for="nid_number" class="col-md-4 col-form-label" style="text-align: right;">Gender</label>
                                        <div class="col-md-6">
                                            <select name="gender" class="form-control">
                                                <option>Male</option>
                                                <option>Female</option>
                                                <option>Other</option>
                                            </select>
                                        </div>
                                    </div>
<!------------------------->
                                    <div >
                                        <button   class="register_button" type="submit" class="btn btn-primary" >
                                            Register
                                        </button>
                                    </div>
<!------------------------->
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


        </main>
        <script>
            function validatePhone(evt) {
                var theEvent = evt || window.event;

                // Handle paste
                if (theEvent.type === 'paste') {
                    key = event.clipboardData.getData('text/plain');
                } else {
                    // Handle key press
                    var key = theEvent.keyCode || theEvent.which;
                    key = String.fromCharCode(key);
                }
                var regex = /[0-9]|\./;
                if (!regex.test(key)) {
                    theEvent.returnValue = false;
                    if (theEvent.preventDefault)
                        theEvent.preventDefault();
                }
            }
        </script>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    </body>
</html>

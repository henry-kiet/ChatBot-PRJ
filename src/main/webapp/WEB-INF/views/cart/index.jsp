<%-- 
    Document   : cart
    Created on : May 23, 2022, 9:04:34 PM
    Author     : quckh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="${root}/css/cart.css" rel="stylesheet" type="text/css"/>
</head>

<c:if test="${cart.numOfProducts>0}">
    <div class="background"  >   
    <table class="table ">      
        <thead >
            <tr class="heat">
                <th style=" text-align: center;">No.</th>
                <th style=" text-align: center;">Image</th>
                <th style=" text-align: center;">Name</th>
                <th style=" text-align: center;">Description</th>
                <th style=" text-align: center;">Price</th>               
                <th style=" text-align: center;">Quantity</th>
                <th style=" text-align: center;">Cost</th>
                <th style=" text-align: center;">Operations</th>
            </tr>
        </thead>
      

        <tbody id="cart-b">
            <c:forEach var="item" items="${cart.items}" varStatus="loop">
                <tr>

                    <td style=" text-align: center; ">${loop.count}</td>
                    <td style=" text-align: center;" ><img src="${item.img}" width="30%" /><input type="hidden" value=${item.id} name="id" /> </td>
                    <td style=" text-align: center; padding-top: 20px;">${item.name}</td>
                    <td style=" text-align: center; padding-top: 20px;">${item.description}</td>
                    <td style=" text-align: center; padding-top: 20px;"><fmt:formatNumber value="${item.newPrice}" pattern="$#,##0.00" /></td>
                  
                    <td     style=" text-align: center;padding-top: 20px;">
                        
<!--                            <script>var name =${item.id} +"a";document.write(name);</script>-->
                        <c:set var = "abc" scope = "session" value = "${item.id+loop.count}a"></c:set>
                        <c:set var = "x" scope = "session" value = "${item.id+loop.count}ab"></c:set>
                        <c:if test="${item.status == 'new' }">     
                            <form  action="update.do" id='${abc}' >
                            
                            
                            
                            
                            <input type="hidden" name="id"  value=${item.id} >
                            <input type="hidden" name="price"  value=${item.price} >
                            <input type="hidden" name="discount"  value=${item.discount} >
                            
                            <input  onclick="var result = document.getElementById('${x}');
                                    var qty = result.value;
                                    if (qty > 1)
                                        result.value--;
                                    document.getElementById('${abc}').submit();
                                    return false;
                                   " type='button' value='-'  />
                            <input  id="${x}" onkeypress='validatePhone(event)' 
                                    onblur='
                                    var result = document.getElementById("${x}");
                                    var qty = result.value;
                                    if (isNaN(qty) === true || qty === "" || qty == 0) {
                                        document.getElementById("${x}").value = 1;
                                        document.getElementById("${abc}").submit();
                                        
                                    } else {
                                        document.getElementById("${abc}").submit();
                                        
//                                   
                                    }'
                                    
                                    
                                    
                                    
                                    style="width:50px;"  min="1" maxlength="7" name="quantity" type="text" value="${item.quantity}" required />
                            
                            
                            
                            
                            
                            
                            <script>
                                function autoup() {

                                    var result = document.getElementById('${x}');
                                    var qty = result.value;
                                    if (isNaN(qty) === true || qty === "") {
                                        document.getElementById('${x}').value = 1;
                                        document.getElementById('${abc}').submit();
                                        
                                    } else {
                                        document.getElementById('${abc}').submit();
                                        
//                                   
                                    }

                                    


                                }
                            </script>

                            <input onclick="var result = document.getElementById('${x}');
                                    var qty = result.value;
                                    if (!isNaN(qty))
                                        result.value++;
                                    document.getElementById('${abc}').submit();
                                    return false;
                                   " type='button' value='+'  />



                            </form>
                            </c:if>
                            
                            
                             <c:if test="${item.status == 'old' }">     
                            
                            <input type="hidden" name="id"  value=${item.id} >
                            <input type="hidden" name="price"  value=${item.price} >
                            <input type="hidden" name="discount"  value=${item.discount} >
                            
                            <input disabled  onclick="var result = document.getElementById('${x}');
                                    var qty = result.value;
                                    if (qty > 1)
                                        result.value--;
                                    document.getElementById('${abc}').submit();
                                    return false;
                                   " type='button' value='-'  />
                            <input disabled id="${x}" onkeypress='validatePhone(event)' onblur='autoup()'   style="width:50px;"  min="1" name="quantity" type="text" value="${item.quantity}" required />
                            
                            <script>
                                function autoup() {

                                    var result = document.getElementById('${x}');
                                    var qty = result.value;
                                    if (isNaN(qty) === true || qty === "") {
                                        document.getElementById('${x}').value = 1;
                                        document.getElementById('${abc}').submit();
                                    } else {
                                        document.getElementById('${abc}').submit();
//                                   
                                    }




                                }
                            </script>

                            <input disabled onclick="var result = document.getElementById('${x}');
                                    var qty = result.value;
                                    if (!isNaN(qty))
                                        result.value++;
                                    document.getElementById('${abc}').submit();
                                    return false;
                                   " type='button' value='+'  />




                            </c:if>
                    </td>

<!--                            <td class="text-right"><input type="number" value="${item.quantity}" style="width: 65px; text-align: right;"name="quantity" /></td>-->
                    <td style=" text-align: center; color: red; padding-top: 20px;"><fmt:formatNumber value="${item.cost}" pattern="$#,##0.00" /></td>
                    <td style=" text-align: center; padding-top: 15px;"> 
                        <form>
                             <input type="hidden" name="id"  value=${item.id} >
                            <input type="hidden" name="price"  value=${item.price} >
                            <input type="hidden" name="discount"  value=${item.discount} >
                        <button type="submit" class="btn btn-link" formaction="<c:url value="/cart/delete.do"/>">Delete<i class="bi bi-trash"></i></button>
                    </form>
                        </td>

                </tr>

                </div>
                </div>
                </form>

            </c:forEach>
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

    </tbody>
        <tfoot class="size" >
        <tr>
            <th>
                <form style=" text-align: center;  padding-top: 20px;"><a href="<c:url value="/cart/empty.do"/>">Empty your cart</a>
                    
                </form>
            </th> 
            
                
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th style=" text-align: center;  padding-top: 25px;">Total(<c:if test="${cart.numOfProducts==null}"> 0 </c:if><c:if test="${cart.numOfProducts!=null}"> ${cart.numOfProducts} </c:if> products) </th>
            <th style=" text-align: center;  padding-top: 25px;"><fmt:formatNumber value="${cart.total}" pattern="$#,##0.00" /></th>
            
            <th class="font-check" style="padding-top: 15px;text-align: center;"><form method="post" action="checkout.do" >
            <div>
            <input type="hidden" name="role" value="${role}">
            <button  class="checkout_button" type="submit"><span>Checkout</span></button>
            </div></form></th>
        </tr>
    </tfoot>
</table>


    </div> 

</c:if>
<c:if test="${cart.numOfProducts<=0 || cart.numOfProducts==null}">
    <div class="null" >
        <div class="container" >
        <img class="img-responsive" src="${root}/images/not.png" />
    </div > 
    <h1 style="text-align: center">
        Don't have anything in cart !
    </h1>
    <h1 style="text-align: center">
        <form action='${root}/product/index.do'>
            <button class="shopping_button" type='submit'><span>Go shopping</span></button>
        </form>     
    </h1>
        </div>
</c:if>
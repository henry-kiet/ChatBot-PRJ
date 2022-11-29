<%-- 
    Document   : index
    Created on : May 23, 2022, 11:42:52 PM
    Author     : quckh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link href="${root}/css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox" style="margin-top: 11px;">
                <div class="item active">
                    <img src="${root}/images/PNG home page/event1.png" alt="Image" style="padding: 15px;">
                    <div class="carousel-caption">
                        <h3 style="font-weight: bold;">Summer sale $$$</h3>
                        <p>Teddy will save your money !</p>
                    </div>      
                </div>
                <div class="item">
                    <img src="${root}/images/PNG home page/event2.png" alt="Image" style="padding: 15px;">
                    <div class="carousel-caption">
                        <h3></h3>
                        <p></p>
                    </div>      
                </div>
            </div>
            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
            <div class="">
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            </div>
        </div>

        <div class="container text-center">    
            <br>
            <div class="row" style="border: 2px solid black; height: 200px; padding: 10px;">
                <div class="col-sm-4">
                    <img src="${root}/images/PNG home page/topic1.png" class="img-responsive" style="width:100%" alt="Image">
                </div>
                <div class="col-sm-8"> 
                    <p style="text-align: left; height: fit-content; width: fit-content;">We are Brown Ted, a Teddy bear shop focusing on giving customers the best online cuddling material through our chatbot system. With our system, you can complete a purchase only using our texting method and don't have to move a muscle or wrack your brain navigating through the web store (you can still do it of course). We hope to give you a wonderful shopping time here at our shop!</p>
                    <form action="${root}/product/index.do">
                        <button class="product_button" type="submit"><span>See all product</span></button>
                    </form>
                </div>
            </div>
        </div>
        
        <br/>
    </body>
</html>


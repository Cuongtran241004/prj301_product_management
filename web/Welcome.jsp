<%-- 
    Document   : header-customer.jsp
    Created on : Jun 9, 2024, 10:28:08 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home Page</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="css/header-customer.css">
    </head>

    <body>
        <div>
            <div class="header-blue">
                <nav class="navbar navbar-dark navbar-expand-md navigation-clean-search">
                    <div class="container"><a class="navbar-brand" href="#">Cường Store</a><button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse"
                             id="navcol-1">
                            <ul class="nav navbar-nav">
                                <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Home</a></li>                               
                                <li class="nav-item" role="presentation"><a class="nav-link " href="#productView">Product</a></li>
                                <li class="nav-item" role="presentation"><a class="nav-link " href="#footer">Contact</a></li>
                            </ul>

                        </div>
                        <form class="form-inline" style="margin-right: 0" target="_self">
                            <div class="form-group"><label for="search-field"><i class="fa fa-search"></i></label><input class="form-control search-field" type="search" name="search" id="search-field"></div>
                        </form><span class="navbar-text"></span><a class="btn btn-light action-button" role="button" href="Login.jsp">Login</a></div>
                </nav>
                <div class="container hero">
                    <div class="row">
                        <div class="col-12 col-lg-6 col-xl-5 offset-xl-1">
                            <h1>Products Provider in Ho Chi Minh City</h1>
                            <p>Our company specializes in providing essential items in life at affordable prices. 
                                Product quality has been approved and is legal to market</p>
                            <p></p>
                            <a href="#productView"><button class="btn btn-light btn-lg action-button" type="button">View more</button></a>
                        </div>

                        <div
                            class="col-md-5 col-lg-5  d-none d-lg-block phone-holder avatar-img">
                            <img src="images/avatar.svg" >
                        </div>
                    </div>
                </div>
            </div>

            <jsp:include page="ProductPublic.jsp"></jsp:include>
            <jsp:include page="footer.html"></jsp:include>
        </div>

        <script>
            var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
            var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
                return new bootstrap.Popover(popoverTriggerEl)
            })
        </script>
    </body>

</html>
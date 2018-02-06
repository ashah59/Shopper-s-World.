<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login | Shopper's world</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">

        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    </head>
    <body>
        <div>
            <%@include file="header.jsp" %>

            <section id="form" class="body-main"><!--form-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-4 col-sm-offset-1">
                            <div class="login-form"><!--login form-->
                                <h2>Login to your account</h2>
                                <form action="Login?action=login" method="post">
                                    <input type="email" placeholder="Email Address" name='email' required />
                                    <input type="password" placeholder="Password" name='pwd' required/>
                                    <button type="submit" class="btn btn-default">Login</button>
                                </form>
                            </div><!--/login form-->
                        </div>
                        <div class="col-sm-1">
                            <h2 class="or">OR</h2>
                        </div>
                        <div class="col-sm-4">
                            <div class="signup-form"><!--sign up form-->
                                <h2>New User Sign up!</h2>
                                <form action="Login?action=signup" method="post">
                                    <input type="text" name='name' placeholder="Name"/>
                                    <input type="email" name='email' placeholder="Email Address"/>
                                    <input type="password" name='pwd' placeholder="Password"/>
                                    <p style="margin-bottom: 0px">Register as</p>
                                    <label style="width: 20%; text-align: center"><input style="height: 20px ; margin-bottom: 0px" name='userType' value='customer' type="radio" checked> Customer</label>
                                    <label style="width: 20%; text-align: center"><input style="height: 20px ; margin-bottom: 0px" name='userType' value="seller" type="radio"> Seller</label>
                                    <button type="submit" class="btn btn-default">Sign up</button>
                                </form>
                            </div><!--/sign up form-->
                        </div>
                    </div>
                </div>
            </section>
            <%@include file="footer.jsp" %>
            <script src="js/jquery.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/jquery.scrollUp.min.js"></script>
            <script src="js/price-range.js"></script>
            <script src="js/jquery.prettyPhoto.js"></script>
            <script src="js/main.js"></script>
        </div>
    </body>
</html>

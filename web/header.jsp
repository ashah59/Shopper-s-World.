<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header id="header"><!--header-->

    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="index.jsp"><img style="height:60px;width: 120px" src="images/home/logo1.jpg" alt="" /></a>
                    </div>
                </div>
                <div class="col-sm-8">
                    <div class="shop-menu pull-right">
                        <ul class="nav navbar-nav">
                            <c:if test="${user != null}">
                                <li style="padding-top: 10px">Hello, <c:out value="${user.name}!"></c:out></li>
                                <li><a href="profile.jsp"><i class="fa fa-user"></i> Profile</a></li>
                                <li><a href="cart.jsp"><i class="fa fa-crosshairs"></i> Checkout</a></li>
                            </c:if>
                                <c:choose>
                                    <c:when test="${user == null}">
                                    <li><a href="login.jsp"><i class="fa fa-lock"></i> Login</a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li>
                                        <form style="margin-top: 10px" action="Login?action=logout" id="logout" method="post">
                                            <a href="#" onclick="document.getElementById('logout').submit()"><i class="fa fa-unlock"></i> Logout</a>
                                        </form>
                                    </li>
                                </c:otherwise>
                            </c:choose>

                            <li><a href="contact_us.jsp"><i class="fa fa-envelope"></i> Contact us</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header-middle-->

</header><!--/header-->
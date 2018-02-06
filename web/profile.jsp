<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile | Shopper's world</title>
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
            <div class="header-bottom"><!--header-bottom-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-9">
                            <div class="mainmenu pull-left">
                                <ul class="nav navbar-nav collapse navbar-collapse">
                                    <c:choose>
                                        <c:when test="${user.userType == 'seller'}">
                                            <li class="dropdown"><a href="#">Products<i class="fa fa-angle-down"></i></a>
                                                <ul role="menu" class="sub-menu">
                                                    <li><a href="add_product.jsp">Add a product</a></li>
                                                    <li><a href="view_products.jsp">View products</a></li>
                                                </ul>
                                            </li>
                                        </c:when>
                                    </c:choose>
                                    <li><a href="view_orders.jsp">View orders</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-bottom-->
            <section class="body-main">
                <div class="container">
                    <div class="shopper-informations">
                        <div class="row">
                            <div class="col-sm-2">
                                <div class="order-message">
                                </div>	
                            </div>
                            <div class="col-sm-3">
                                <div class="shopper-info">
                                    <p>User Information</p>
                                    <form method="post" action="Profile?action=user">
                                        <input type="text" placeholder="Name" name='name' required value="<c:out value="${user.name}"></c:out>" />
                                        <input type="email" name='email' placeholder="Email Address" value="<c:out value="${user.email}"></c:out>" disabled/>
                                        <input type="password" name='pwd' placeholder="Update Password" value="<c:out value="${user.password}"></c:out>" required/>
                                        <label>Registered as <c:out value="${user.userType}"></c:out>.</label><br />
                                            <button type="submit" class="btn btn-primary">Update</button>
                                        </form>
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="order-message">
                                    </div>	
                                </div>	
                                <div class="col-sm-3 clearfix">
                                    <div class="bill-to">
                                        <p>Contact Details</p>
                                        <div class="form-two">
                                            <form method="post" action="Profile?action=contact">
                                                <input type="text" name="address" placeholder="Address" value="<c:out value="${user.address}"></c:out>" required />
                                            <input type="text" name="city" placeholder="City" value="<c:out value="${user.city}"></c:out>" required />
                                                <select name="state">
                                                    <option value="AL" ${user.stateCode == "AL" ? 'selected' : ''}>Alabama</option>
                                                <option value="AK" ${user.stateCode == "AK" ? 'selected' : ''}>Alaska</option>
                                                <option value="AZ" ${user.stateCode == "AZ" ? 'selected' : ''}>Arizona</option>
                                                <option value="AR" ${user.stateCode == "AR" ? 'selected' : ''}>Arkansas</option>
                                                <option value="CA" ${user.stateCode == "CA" ? 'selected' : ''}>California</option>
                                                <option value="CO" ${user.stateCode == "CO" ? 'selected' : ''}>Colorado</option>
                                                <option value="CT" ${user.stateCode == "CT" ? 'selected' : ''}>Connecticut</option>
                                                <option value="DE" ${user.stateCode == "DE" ? 'selected' : ''}>Delaware</option>
                                                <option value="DC" ${user.stateCode == "DC" ? 'selected' : ''}>District Of Columbia</option>
                                                <option value="FL" ${user.stateCode == "FL" ? 'selected' : ''}>Florida</option>
                                                <option value="GA" ${user.stateCode == "GA" ? 'selected' : ''}>Georgia</option>
                                                <option value="HI" ${user.stateCode == "HI" ? 'selected' : ''}>Hawaii</option>
                                                <option value="ID" ${user.stateCode == "ID" ? 'selected' : ''}>Idaho</option>
                                                <option value="IL" ${user.stateCode == "IL" ? 'selected' : ''}>Illinois</option>
                                                <option value="IN" ${user.stateCode == "IN" ? 'selected' : ''}>Indiana</option>
                                                <option value="IA" ${user.stateCode == "IA" ? 'selected' : ''}>Iowa</option>
                                                <option value="KS" ${user.stateCode == "KS" ? 'selected' : ''}>Kansas</option>
                                                <option value="KY" ${user.stateCode == "KY" ? 'selected' : ''}>Kentucky</option>
                                                <option value="LA" ${user.stateCode == "LA" ? 'selected' : ''}>Louisiana</option>
                                                <option value="ME" ${user.stateCode == "ME" ? 'selected' : ''}>Maine</option>
                                                <option value="MD" ${user.stateCode == "MD" ? 'selected' : ''}>Maryland</option>
                                                <option value="MA" ${user.stateCode == "MA" ? 'selected' : ''}>Massachusetts</option>
                                                <option value="MI" ${user.stateCode == "MI" ? 'selected' : ''}>Michigan</option>
                                                <option value="MN" ${user.stateCode == "MN" ? 'selected' : ''}>Minnesota</option>
                                                <option value="MS" ${user.stateCode == "MS" ? 'selected' : ''}>Mississippi</option>
                                                <option value="MO" ${user.stateCode == "MO" ? 'selected' : ''}>Missouri</option>
                                                <option value="MT" ${user.stateCode == "MT" ? 'selected' : ''}>Montana</option>
                                                <option value="NE" ${user.stateCode == "NE" ? 'selected' : ''}>Nebraska</option>
                                                <option value="NV" ${user.stateCode == "NV" ? 'selected' : ''}>Nevada</option>
                                                <option value="NH" ${user.stateCode == "NH" ? 'selected' : ''}>New Hampshire</option>
                                                <option value="NJ" ${user.stateCode == "NJ" ? 'selected' : ''}>New Jersey</option>
                                                <option value="NM" ${user.stateCode == "NM" ? 'selected' : ''}>New Mexico</option>
                                                <option value="NY" ${user.stateCode == "NY" ? 'selected' : ''}>New York</option>
                                                <option value="NC" ${user.stateCode == "NC" ? 'selected' : ''}>North Carolina</option>
                                                <option value="ND" ${user.stateCode == "ND" ? 'selected' : ''}>North Dakota</option>
                                                <option value="OH" ${user.stateCode == "OH" ? 'selected' : ''}>Ohio</option>
                                                <option value="OK" ${user.stateCode == "OK" ? 'selected' : ''}>Oklahoma</option>
                                                <option value="OR" ${user.stateCode == "OR" ? 'selected' : ''}>Oregon</option>
                                                <option value="PA" ${user.stateCode == "PA" ? 'selected' : ''}>Pennsylvania</option>
                                                <option value="RI" ${user.stateCode == "RI" ? 'selected' : ''}>Rhode Island</option>
                                                <option value="SC" ${user.stateCode == "SC" ? 'selected' : ''}>South Carolina</option>
                                                <option value="SD" ${user.stateCode == "SD" ? 'selected' : ''}>South Dakota</option>
                                                <option value="TN" ${user.stateCode == "TN" ? 'selected' : ''}>Tennessee</option>
                                                <option value="TX" ${user.stateCode == "TX" ? 'selected' : ''}>Texas</option>
                                                <option value="UT" ${user.stateCode == "UT" ? 'selected' : ''}>Utah</option>
                                                <option value="VT" ${user.stateCode == "VT" ? 'selected' : ''}>Vermont</option>
                                                <option value="VA" ${user.stateCode == "VA" ? 'selected' : ''}>Virginia</option>
                                                <option value="WA" ${user.stateCode == "WA" ? 'selected' : ''}>Washington</option>
                                                <option value="WV" ${user.stateCode == "WV" ? 'selected' : ''}>West Virginia</option>
                                                <option value="WI" ${user.stateCode == "WI" ? 'selected' : ''}>Wisconsin</option>
                                                <option value="WY" ${user.stateCode == "WY" ? 'selected' : ''}>Wyoming</option>
                                            </select>
                                            <input type="number" name="zip" placeholder="Zip / Postal Code" value="<c:out value="${user.zipCode}"></c:out>" required />
                                            <input type="number" name="phone" placeholder="Mobile Phone" value="<c:out value="${user.phone}"></c:out>" required />
                                                <button type="submit" class="btn btn-primary">Update</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>					
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

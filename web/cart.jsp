<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart | Shopper's world</title>
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
            <section id="cart_items">
                <div class="container">
                    <div class="table-responsive cart_info">
                        <table class="table table-condensed">
                            <thead>
                                <tr class="cart_menu">
                                    <td class="image">Product Name</td>
                                    <td class="price">Price</td>
                                    <td class="quantity">Select Quantity</td>
                                    <td class="price">Amount</td>
                                    <td class="total"></td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${cart.items}">
                                    <tr>
                                        <td class="cart_product">
                                            <c:out value="${item.product.name}" />
                                        </td>
                                        <td class="cart_price">
                                            <p><c:out value="${item.product.priceCurrencyFormat}" /></p>
                                        </td>
                                        <td class="cart_quantity">
                                            <div class="cart_quantity_button">
                                                <form action="Cart" method="post">
                                                    <input type="hidden" name="productCode" 
                                                           value="<c:out value='${item.product.id}'/>">
                                                    <input type="number" class="cart_quantity_input" name="quantity" 
                                                           value="<c:out value='${item.quantity}'/>" id="quantity"> &nbsp;&nbsp;
                                                    <input type="submit" class="btn btn-default" value="Update">
                                                </form>
                                            </div>
                                        </td>
                                        <td class="cart_total">
                                            <p class="cart_total_price"><c:out value="${item.totalCurrencyFormat}" /></p>
                                        </td>
                                        <td class="cart_delete">
                                            <form id="remove" action="Cart" method="post">
                                                <input type="hidden" name="productCode" 
                                                       value="<c:out value='${item.product.id}'/>">
                                                <input type="hidden" name="quantity" 
                                                       value="0">
                                                <a class="cart_quantity_delete" href="#" onclick="document.getElementById('remove').submit()"><i class="fa fa-times"></i></a>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="3">&nbsp;</td>
                                    <td colspan="2">
                                        <table class="table table-condensed total-result">
                                            <tr>
                                                <td>Cart Sub Total</td>
                                                <td><c:out value="${total}" /></td>
                                            </tr>
                                            <tr>
                                                <td>Tax (7.25%)</td>
                                                <td><c:out value="${tax}" /></td>
                                            </tr>
                                            <tr class="shipping-cost">
                                                <td>Shipping</td>
                                                <td>Free</td>										
                                            </tr>
                                            <tr>
                                                <td>Total</td>
                                                <td><span><c:out value="${totalWithTax}" /></span></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </section>
            <div id="contact-page" class="container">
                <div class="bg">
                    <div class="row">
                        <div class="col-sm-8">
                            <a class="btn btn-default" href="index.jsp">Continue Shopping</a></div>                        
                        <div class="col-sm-4" style="margin-bottom: 20px">
                            <div class="contact-form">
                                <h2 class="title text-center">Make Payment</h2>
                                <form id="main-contact-form" class="contact-form row" action="Order?action=makePay" name="contact-form" method="post">
                                    <div class="form-group col-md-12">
                                        <input type="number" name="cardNum" class="form-control" required="required" placeholder="16-digit card number">
                                    </div>  
                                    <div class="form-group col-md-4">
                                        <input type="number" name="expMonth" class="form-control" min="1" max="12" required="required" placeholder="Exp MM">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <input type="number" name="expYear" class="form-control" min="0" max="99" required="required" placeholder="Exp YY">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <input type="number" name="cvv" class="form-control" min="0" max="999" required="required" placeholder="CVV">
                                    </div>
                                    <div class="form-group col-md-12">
                                        <input type="text" name="name" class="form-control" required="required" placeholder="Name on card">
                                    </div>  
                                    <div class="form-group col-md-12">
                                        <input type="submit" name="submit" class="btn btn-primary pull-right" ${fn:length(cart.items) > 0 ? '' : 'disabled'} value="Place order">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div></div></div>
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

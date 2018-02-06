<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your orders | Shopper's world</title>
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
                                    <td class="image">Order ID</td>
                                    <td class="price">Total Amount</td>
                                    <td class="quantity">Paid By (Card)</td>
                                    <td class="total"></td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${orders}">
                                    <tr>
                                        <td class="cart_product">
                                            <c:out value="${item.orderId}" />
                                        </td>
                                        <td class="cart_price">
                                            <p><c:out value="${item.amount}" /></p>
                                        </td>
                                        <td class="cart_quantity">
                                            <p>xxxx-xxxx-xxxx-<c:out value="${fn:substring(item.cardNum, fn:length(item.cardNum) - 4, fn:length(item.cardNum))}" /></p>
                                        </td>
                                        <td class="cart_delete">
                                            <form id="remove" action="Order?action=cancel" method="post">
                                                <input type="hidden" name="orderId" 
                                                       value="<c:out value='${item.orderId}'/>">
                                                <a class="cart_quantity_delete" title="Cancel the order" href="#" onclick="document.getElementById('remove').submit()"><i class="fa fa-times"></i></a>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
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

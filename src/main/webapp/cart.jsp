<%@ page import="java.text.DecimalFormat" %>
<%@ page import="ge.tsu.shoppingcartproject.model.User" %>
<%@ page import="ge.tsu.shoppingcartproject.model.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.tsu.shoppingcartproject.dao.ProductDao" %>
<%@ page import="ge.tsu.shoppingcartproject.connection.DataBaseConnection" %>
<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("person", auth);
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    if (cart_list != null) {
        ProductDao pDao = new ProductDao(DataBaseConnection.getConnection());
        cartProduct = pDao.getCartProducts(cart_list);
        double total = pDao.getTotalCartPrice(cart_list);
        request.setAttribute("total", total);
        request.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/includes/header.jsp"%>
    <title>E-Commerce Cart</title>
    <style type="text/css">
        .table tbody td{
            vertical-align: middle;
        }
        .btn-incre, .btn-decre{
            box-shadow: none;
            font-size: 25px;
        }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="/includes/navbar.jsp"%>

<div class="container my-3">
    <div class="d-flex py-3"><h3>Total Price: $ ${(total > 0) ? dcf.format(total) : 0} </h3>
        <a class="mx-3 btn btn-primary" href="cart-check-out">Check Out</a>
    </div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Complexity</th>
            <th scope="col">Price</th>
            <th scope="col">Buy Now</th>
            <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (cart_list != null) {
                for (Cart item : cartProduct) {
        %>
        <tr>
            <td><%=item.getName()%></td>
            <td><%=item.getComplexity()%></td>
            <td><%= dcf.format(item.getPrice())%></td>
            <td>
                <form action="order-now" method="post" class="form-inline">
                    <div style="display: inline-block; margin-left: 10px;">
                        <button type="submit" class="btn btn-primary btn-sm">Buy</button>
                    </div>
                    <div style="display: inline-block; margin-left: 50px;">
                        <input type="hidden" name="id" value="<%= item.getId()%>" class="form-input">
                        <div class="form-group d-flex justify-content-between">
                            <a class="btn bnt-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=item.getId()%>"><i class="fas fa-minus-square"></i></a>
                            <input type="text" name="quantity" class="form-control"  value="<%=item.getQuantity()%>" readonly>
                            <a class="btn btn-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=item.getId()%>"><i class="fas fa-plus-square"></i></a>
                        </div>
                    </div>
                </form>
            </td>
            <td><a href="remove-from-cart?id=<%=item.getId() %>" class="btn btn-sm btn-danger">Remove</a></td>
        </tr>

        <%
                }}%>
        </tbody>
    </table>
</div>

<%@include file="/includes/footer.jsp"%>
</body>
</html>
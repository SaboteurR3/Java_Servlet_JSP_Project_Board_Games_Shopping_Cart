<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.*"%>
<%@ page import="ge.tsu.shoppingcartproject.model.User" %>
<%@ page import="ge.tsu.shoppingcartproject.model.Order" %>
<%@ page import="ge.tsu.shoppingcartproject.dao.OrderDao" %>
<%@ page import="ge.tsu.shoppingcartproject.connection.DataBaseConnection" %>
<%@ page import="ge.tsu.shoppingcartproject.model.Cart" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
    User auth = (User) request.getSession().getAttribute("auth");
    List<Order> orders = null;
    if (auth != null) {
        request.setAttribute("person", auth);
        OrderDao orderDao  = new OrderDao(DataBaseConnection.getConnection());
        orders = orderDao.userOrders(auth.getId());
    }else{
        response.sendRedirect("login.jsp");
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="/includes/header.jsp"%>
    <title>E-Commerce Cart</title>
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="/includes/navbar.jsp"%>
<div class="container">
    <div class="card-header my-3">All Orders</div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Date</th>
            <th scope="col">Name</th>
            <th scope="col">Complexity</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price</th>
            <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
        <%if(orders != null){
                for(Order order:orders){ %>
        <tr>
            <td><%=order.getDate() %></td>
            <td><%=order.getName() %></td>
            <td><%=order.getComplexity() %></td>
            <td><%=order.getQunatity() %></td>
            <td><%=dcf.format(order.getPrice()) %>$</td>
            <td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=order.getOrderId()%>">Cancel Order</a></td>
        </tr>
        <%}
        }%>
        </tbody>
    </table>
</div>
<%@include file="/includes/footer.jsp"%>
</body>
</html>

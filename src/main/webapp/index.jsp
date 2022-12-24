<%@page import="java.util.*"%>
<%@ page import="ge.tsu.shoppingcartproject.dao.ProductDao" %>
<%@ page import="ge.tsu.shoppingcartproject.model.User" %>
<%@ page import="ge.tsu.shoppingcartproject.connection.DataBaseConnection" %>
<%@ page import="ge.tsu.shoppingcartproject.model.Product" %>
<%@ page import="ge.tsu.shoppingcartproject.model.Cart" %>
<%@ page import="ge.tsu.shoppingcartproject.servlets.LogoutServlet" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("person", auth);
    }
    ProductDao pd = new ProductDao(DataBaseConnection.getConnection());
    List<Product> products = pd.getAllProducts();
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/includes/header.jsp"%>
    <title>Board games shopping Cart</title>
</head>
<body class="d-flex flex-column min-vh-100">
<%@include file="/includes/navbar.jsp"%>

<!-- Carousel -->
<div id="demo" class="carousel slide" data-bs-ride="carousel">
    <!-- Indicators/dots -->
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
        <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
    </div>
    <!-- The slideshow/carousel -->
    <div class="carousel-inner">
            <div class="carousel-item active">
                <div class="noColor">
                <a href="#products"><img class="d-block w-100" src="other-images/firstSlide.webp" alt="Second slide" style="width:100%;height:670px"></a>
                <div class="carousel-caption" style="background-color: rgba(0, 0, 0, 0.5);">
                    <h3 style="font-style: italic">Board Games</h3>
                    <p style="font-style: italic">"Bored? Who said I was bored?"</p>
                </div>
            </div>
        </div>
            <div class="carousel-item">
                <a href="cart.jsp"><img class="d-block w-100" src="other-images/A.jpg" alt="Second slide" style="width:100%;height:670px"></a>
                <div class="carousel-caption" style="background-color: rgba(0, 0, 0, 0.5);">
                    <h3 style="font-style: italic">Shopping Cart</h3>
                    <p style="font-style: italic">Enjoy shopping and get anything you want</p>
            </div>
        </div>
    </div>
    <!-- Left and right controls/icons -->
    <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
        <span class="carousel-control-prev-icon"></span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
        <span class="carousel-control-next-icon"></span>
    </button>
</div>

<div class="container" id="products">
    <div class="card-header my-3"><h2 style="text-align: center; font-style: italic">Our Products</h2></div>
    <div class="row">
        <%
            if (!products.isEmpty()) {
                for (Product p : products) {
        %>
        <div class="col-md-3 my-3">
            <div class="card w-100">
                <img class="card-img-top" src="product-images/<%=p.getImage()%>"
                     alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%=p.getName() %></h5>
                    <h6 class="price">Price: $<%=p.getPrice() %></h6>
                    <h6 class="complexity">Complexity: <%=p.getComplexity() %></h6>
                    <div class="mt-3 d-flex justify-content-between">
                        <a class="btn btn-dark" href="add-to-cart?id=<%=p.getId()%>">Add to Cart</a> <a
                            class="btn btn-primary" href="order-now?quantity=1&id=<%=p.getId()%>">Buy Now</a>
                    </div>
                </div>
            </div>
        </div>
        <%
                }
            } else {
                out.println("There is no product");
            }
        %>

    </div>
</div>

<%@include file="/includes/footer.jsp"%>
</body>
</html>
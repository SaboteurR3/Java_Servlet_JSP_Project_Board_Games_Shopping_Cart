<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container style="position: fixed;"">
        <a class="navbar-brand" style="font-size: 18px" href="index.jsp">Shopping Cart</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item active">
                    <a class="nav-link" style="font-size: 18px; " href="index.jsp">Home </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="font-size: 18px" href="cart.jsp">Cart <span class="badge badge-danger" style="color: white; background: darkred;">${cart_list.size()}</span></a>
                </li>
                <%
                    if (auth != null) {
                %>
                <li class="nav-item" style="font-size: 18px"><a class="nav-link " href="orders.jsp">Orders</a></li>
                <li class="nav-item" style="font-size: 18px"><a class="nav-link" href="log-out">Logout</a></li>
                <%
                } else {
                %>
                <li class="nav-item" style="font-size: 18px"><a class="nav-link" href="login.jsp">Login</a></li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</nav>

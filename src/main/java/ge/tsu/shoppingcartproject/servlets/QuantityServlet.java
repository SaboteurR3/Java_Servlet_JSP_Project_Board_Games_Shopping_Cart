package ge.tsu.shoppingcartproject.servlets;

import ge.tsu.shoppingcartproject.model.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/quantity-inc-dec")
public class QuantityServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            if (action != null && id >= 1) {
                if (action.equals("inc")) {
                    for (Cart item : cart_list) {
                        if (item.getId() == id) {
                            int quantity = item.getQuantity();
                            quantity++;
                            item.setQuantity(quantity);
                            response.sendRedirect("cart.jsp");
                        }
                    }
                }
                if (action.equals("dec")) {
                    for (Cart item : cart_list) {
                        if (item.getId() == id && item.getQuantity() > 1) {
                            int quantity = item.getQuantity();
                            quantity--;
                            item.setQuantity(quantity);
                            break;
                        }
                    }
                    response.sendRedirect("cart.jsp");
                }
            } else {
                response.sendRedirect("cart.jsp");
            }
        }
    }
}


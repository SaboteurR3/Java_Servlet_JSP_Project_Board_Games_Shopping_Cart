package ge.tsu.shoppingcartproject.servlets;

import ge.tsu.shoppingcartproject.model.Cart;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String bookId = request.getParameter("id");
            if (bookId != null) {
                ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                if (cart_list != null)
                    for (Cart item : cart_list) {
                        if (item.getId() == Integer.parseInt(bookId)) {
                            cart_list.remove(cart_list.indexOf(item));
                            break;
                        }
                    }
                response.sendRedirect("cart.jsp");
            } else {
                response.sendRedirect("cart.jsp");
            }
        }
    }
}

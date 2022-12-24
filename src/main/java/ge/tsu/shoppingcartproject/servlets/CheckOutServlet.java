package ge.tsu.shoppingcartproject.servlets;

import ge.tsu.shoppingcartproject.connection.DataBaseConnection;
import ge.tsu.shoppingcartproject.dao.OrderDao;
import ge.tsu.shoppingcartproject.model.Cart;
import ge.tsu.shoppingcartproject.model.Order;
import ge.tsu.shoppingcartproject.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            User auth = (User) request.getSession().getAttribute("auth");
            if (auth == null) {
                response.sendRedirect("login.jsp");
            } else if (cart_list != null && auth != null) {
                for (Cart c : cart_list) {
                    Order order = new Order();
                    order.setId(c.getId());
                    order.setUid(auth.getId());
                    order.setQunatity(c.getQuantity());
                    order.setDate(formatter.format(date));

                    OrderDao oDao = new OrderDao(DataBaseConnection.getConnection());
                    boolean result = oDao.insertOrder(order);
                    if (!result) break;
                }
                cart_list.clear();
                response.sendRedirect("orders.jsp");
            } else {
                response.sendRedirect("cart.jsp");
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}

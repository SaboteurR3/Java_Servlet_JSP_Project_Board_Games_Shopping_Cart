package ge.tsu.shoppingcartproject.servlets;

import ge.tsu.shoppingcartproject.connection.DataBaseConnection;
import ge.tsu.shoppingcartproject.dao.OrderDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try(PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            if(id != null) {
                OrderDao orderDao = new OrderDao(DataBaseConnection.getConnection());
                orderDao.cancelOrder(Integer.parseInt(id));
            }
            response.sendRedirect("orders.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

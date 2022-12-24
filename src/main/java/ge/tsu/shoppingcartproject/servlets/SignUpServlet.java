package ge.tsu.shoppingcartproject.servlets;

import ge.tsu.shoppingcartproject.connection.DataBaseConnection;
import ge.tsu.shoppingcartproject.dao.UserDao;
import ge.tsu.shoppingcartproject.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/user-signup")
public class SignUpServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Collect user submitted name, email and password
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");


            UserDao udao = new UserDao(DataBaseConnection.getConnection());
            boolean isValide = udao.userRegister(name, email, password);

            if(isValide) {
                response.sendRedirect("login.jsp");
            } else {
                Thread thread = new Thread();
                thread.sleep(2000);
                response.sendRedirect("error.jsp");
            }

        } catch (InterruptedException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

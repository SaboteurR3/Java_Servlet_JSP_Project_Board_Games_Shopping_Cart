package ge.tsu.shoppingcartproject.servlets;

import ge.tsu.shoppingcartproject.connection.DataBaseConnection;
import ge.tsu.shoppingcartproject.dao.UserDao;
import ge.tsu.shoppingcartproject.model.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
//    private final Logger logger = LogManager.getLogger(LogoutServlet.class);
private static final Logger logger = Logger.getLogger(LogoutServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Collect user submitted email and password
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            UserDao udao = new UserDao(DataBaseConnection.getConnection());
            User user = udao.userLogin(email, password);
            if (user != null) { // if user exists in our database
                request.getSession().setAttribute("auth", user);
                // Search for a field
                Field field = user.getClass().getDeclaredField ("name");
                // set the accessibility as true
                field.setAccessible(true);
                // store the value of private field in variable
                String name = (String)field.get(user);
                // logg the message
                String loggingMessage = "User '" + name + "' logged in";
                logger.log(Level.FINE, loggingMessage);

                response.sendRedirect("index.jsp");
            } else {
                Thread.sleep(2000);
                response.sendRedirect("error.jsp");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException | ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

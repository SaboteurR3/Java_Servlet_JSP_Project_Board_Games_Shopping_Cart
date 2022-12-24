package ge.tsu.shoppingcartproject.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/log-out")
public class LogoutServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LogoutServlet.class.getName());
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if(request.getSession().getAttribute("auth")!=null) {
                // save user
                Object user = (request.getSession().getAttribute("auth"));
                // Search for a field
                Field field = user.getClass().getDeclaredField ("name");
                // set the accessibility as true
                field.setAccessible(true);
                // store the value of private field in variable
                String name = (String)field.get(user);
                // logg the message
                String loggingMessage = "User '" + name + "' logged out";
                logger.log(Level.FINE, loggingMessage);
                // remove session
                request.getSession().removeAttribute("auth");
                Thread.sleep(2000);

                response.sendRedirect("index.jsp");
            }
        } catch (IOException | InterruptedException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}

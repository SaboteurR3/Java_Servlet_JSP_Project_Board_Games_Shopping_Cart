package ge.tsu.shoppingcartproject.connection;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class DataBaseConnectionTest {
    Connection connnection;

    // Test database connection
    @Test
    void testDbConnectionTest() throws SQLException, ClassNotFoundException {
        String expected = "conn0: url=jdbc:h2:~/test user=SA";
        String actual = String.valueOf(connnection = DataBaseConnection.getConnection());
        assertEquals(expected, actual);
    }
}
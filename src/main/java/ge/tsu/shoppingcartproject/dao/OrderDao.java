package ge.tsu.shoppingcartproject.dao;

import ge.tsu.shoppingcartproject.model.Order;
import ge.tsu.shoppingcartproject.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private Connection connection;
    private String query;
    private PreparedStatement statement;
    private ResultSet result;


    public OrderDao(Connection connection) {
        super();
        this.connection = connection;
    }

    public boolean insertOrder(Order model) {
        boolean result = false;
        try {
            query = "insert into orders (p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
            statement = this.connection.prepareStatement(query);
            statement.setInt(1, model.getId());
            statement.setInt(2, model.getUid());
            statement.setInt(3, model.getQunatity());
            statement.setString(4, model.getDate());
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


    public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        try {
            query = "select * from orders where u_id=? order by orders.o_id desc";
            statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                Order order = new Order();
                ProductDao productDao = new ProductDao(this.connection);
                int pId = result.getInt("p_id");
                Product product = productDao.getSingleProduct(pId);
                order.setOrderId(result.getInt("o_id"));
                order.setId(pId);
                order.setName(product.getName());
                order.setComplexity(product.getComplexity());
                order.setPrice(product.getPrice()*result.getInt("o_quantity"));
                order.setQunatity(result.getInt("o_quantity"));
                order.setDate(result.getString("o_date"));
                list.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cancelOrder(int id) {
        try {
            query = "delete from orders where o_id=?";
            statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

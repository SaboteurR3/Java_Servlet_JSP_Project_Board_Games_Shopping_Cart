package ge.tsu.shoppingcartproject.dao;

import java.sql.*;
import java.util.*;

import ge.tsu.shoppingcartproject.model.Cart;
import ge.tsu.shoppingcartproject.model.Product;

public class ProductDao {
    private Connection connection;

    private String query;
    private PreparedStatement statement;
    private ResultSet result;


    public ProductDao(Connection connection) {
        super();
        this.connection = connection;
    }


    public List<Product> getAllProducts() {
        List<Product> book = new ArrayList<>();
        try {
            query = "select * from products";
            statement = this.connection.prepareStatement(query);
            result = statement.executeQuery();

            while (result.next()) {
                Product row = new Product();
                row.setId(result.getInt("id"));
                row.setName(result.getString("name"));
                row.setComplexity(result.getString("complexity"));
                row.setPrice(result.getDouble("price"));
                row.setImage(result.getString("image"));

                book.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }


    public Product getSingleProduct(int id) {
        Product row = null;
        try {
            query = "select * from products where id=? ";

            statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                row = new Product();
                row.setId(result.getInt("id"));
                row.setName(result.getString("name"));
                row.setComplexity(result.getString("complexity"));
                row.setPrice(result.getDouble("price"));
                row.setImage(result.getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return row;
    }

    public double getTotalCartPrice(ArrayList<Cart> cartList) {
        double sum = 0;
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select price from products where id=?";
                    statement = this.connection.prepareStatement(query);
                    statement.setInt(1, item.getId());
                    result = statement.executeQuery();
                    while (result.next()) {
                        sum += result.getDouble("price") * item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }


    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> book = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select * from products where id=?";
                    statement = this.connection.prepareStatement(query);
                    statement.setInt(1, item.getId());
                    result = statement.executeQuery();
                    while (result.next()) {
                        Cart row = new Cart();
                        row.setId(result.getInt("id"));
                        row.setName(result.getString("name"));
                        row.setComplexity(result.getString("complexity"));
                        row.setPrice(result.getDouble("price")*item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        book.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }
}
package WebbShop;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Repository {
    Properties properties = new Properties();
   final String property_file = "C:\\Users\\samii\\OneDrive\\Skrivbord\\javascript läxor\\IdeaProjects\\DatabasTeknikHomework2\\src\\WebbShop\\Settings.Properties";


    public boolean validateUser(String userName, String passWord) throws IOException {

        properties.load(new FileInputStream(property_file));

        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("username"),
                properties.getProperty("password"));) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM customer WHERE namn = ? AND passwords = ?");

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, passWord);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    List<ProductTable> getProduct() throws IOException {

        properties.load(new FileInputStream(property_file));

        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("username"),
                properties.getProperty("password"));) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT brand, size, price, color, categoryID FROM product");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {


                List<ProductTable> product = new ArrayList<>();
                while (resultSet.next()) {
                    ProductTable temp = new ProductTable();
                    String brand = resultSet.getString("brand");
                    temp.setBrand(brand);
                    int size = resultSet.getInt("size");
                    temp.setSize(size);
                    product.add(temp);
                    int price = resultSet.getInt("price");
                    temp.setPrice(price);
                    int categoryid = resultSet.getInt("categoryid");
                    temp.setCategoryId(categoryid);
                    String color = resultSet.getString("color");
                    temp.setColor(color);


                }
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToCart(int userId,int orderId, int productId) throws IOException{

        properties.load(new FileInputStream(property_file));

        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("username"),
                properties.getProperty("password"));) {

            PreparedStatement preparedStatement = connection.prepareStatement("CALL AddToCart(?, ?, ?)");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, orderId);
            preparedStatement.setInt(3, productId);
            preparedStatement.execute();


        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }


    public List<CategoryTable> getCategories() throws IOException {
        // Assume 'properties' and 'property_file' are already defined and available in your class
        properties.load(new FileInputStream(property_file));
        List<CategoryTable> categories = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("username"),
                properties.getProperty("password"));
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, namn FROM category");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String namn = resultSet.getString("namn");
                CategoryTable category = new CategoryTable(id, namn);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
}






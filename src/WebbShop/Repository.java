package WebbShop;

import WebbShop.Tables.CategoryTable;
import WebbShop.Tables.CustomerTable;
import WebbShop.Tables.ProductTable;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Repository {
    Properties properties = new Properties();
    final String property_file = "src\\WebbShop\\Settings.Properties";


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

    public int getUserID(String userName, String passWord) throws IOException {

        properties.load(new FileInputStream(property_file));
        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("username"),
                properties.getProperty("password"));) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id FROM customer WHERE namn = ? AND passwords = ?");

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, passWord);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");

            } else System.out.println("Hittar ej användare");

        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        return -1;
    }

    List<ProductTable> getProduct() throws IOException {

        properties.load(new FileInputStream(property_file));

        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("username"),
                properties.getProperty("password"));) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT brand, size, price, color, id, categoryID FROM product");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {


                List<ProductTable> product = new ArrayList<>();
                while (resultSet.next()) {
                    ProductTable temp = new ProductTable();
                    String brand = resultSet.getString("brand");
                    temp.setBrand(brand);
                    int size = resultSet.getInt("size");
                    temp.setSize(size);
                    int price = resultSet.getInt("price");
                    temp.setPrice(price);
                    int categoryid = resultSet.getInt("categoryid");
                    temp.setCategoryId(categoryid);
                    int id = resultSet.getInt("id");
                    temp.setId(id);
                    String color = resultSet.getString("color");
                    temp.setColor(color);
                    product.add(temp);


                }
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToCart(int userId, int orderId, int productId) throws IOException {

        properties.load(new FileInputStream(property_file));

        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("username"),
                properties.getProperty("password"));) {

            PreparedStatement preparedStatement = connection.prepareStatement("CALL AddToCart(?, ?, ?)");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, orderId);
            preparedStatement.setInt(3, productId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected >0 ) {
                System.out.println("Beställningen lyckades!");
            } else {
                System.out.println("Beställning gick inte igenom");
            }

        } catch (SQLException e) {
            throw new RuntimeException("finns ej i lager");
        }
    }

    public List<CategoryTable> getCategories() throws IOException {
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

    public int getProductID(int size, String brand) throws IOException {

        properties.load(new FileInputStream(property_file));
        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("connectionString"),
                properties.getProperty("username"),
                properties.getProperty("password"));) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id FROM product WHERE brand = ? AND size = ? AND color = ?");

            preparedStatement.setString(1, brand);
            preparedStatement.setInt(2, size);
            preparedStatement.setString(3,color);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");


            } else System.out.println("hittar ej produkt");
            return -1;

        } catch (Exception e) {
            throw new RuntimeException("Produkten finns ej");
        }
    }
        List<CustomerTable> getPerson() throws IOException {

            properties.load(new FileInputStream(property_file));

            try (Connection connection = DriverManager.getConnection(
                    properties.getProperty("connectionString"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));) {

                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, socialSecurityNumber, phoneNumber, namn FROM Customer");
                try (ResultSet resultSet = preparedStatement.executeQuery()) {


                    List<CustomerTable> customerInfo = new ArrayList<>();
                    while (resultSet.next()) {
                        CustomerTable temp = new CustomerTable();
                        int id = resultSet.getInt("id");
                        temp.setId(id);
                        long socialSecurityNumber = resultSet.getLong("socialSecurityNumber");
                        temp.setSocialSecurityNumber(socialSecurityNumber);
                        int phoneNumber = resultSet.getInt("phoneNumber");
                        temp.setPhoneNumber(phoneNumber);
                        String namn = resultSet.getString("namn");
                        temp.setNamn(namn);
                        customerInfo.add(temp);

                    }
                    return customerInfo;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Personen finns inte i databasen");
            }
        }
    }






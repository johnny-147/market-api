package com.kemiex.marketapi;

import com.kemiex.marketapi.model.Product;
import com.kemiex.marketapi.model.Type;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarketService {

    public static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String DEFAULT_URL = "jdbc:sqlserver://localhost:1433;databaseName=MarketApi;encrypt=true;trustServerCertificate=true";
    private static final String DEFAULT_USERNAME = "prm_install";
    private static final String DEFAULT_PASSWORD = "prma1ert";
    public static final String SQL_GET_ALL_PRODUCTS = "SELECT * FROM PRODUCT";
    public static final String SQL_ADD_PRODUCT = "INSERT INTO PRODUCT VALUES(?,?,?,?,?,?,?)";
    public static final String SQL_EDIT_PRODUCT = "UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?, PRICE = ?, ITEMSINSTOCK = ? WHERE ID = ?";
    public static final String SQL_DELETE_PRODUCT = "DELETE FROM PRODUCT WHERE ID = ?";

    public List<Product> getAllProducts() {
        Connection connection = null;
        List<Product> productList = new ArrayList<>();
        try {
            connection = DatabaseService.getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = connection.prepareStatement(SQL_GET_ALL_PRODUCTS);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Product product = Product.builder().id(rs.getInt("ID"))
                            .name(rs.getString("NAME"))
                            .description(rs.getString("DESCRIPTION"))
                            .type(Type.valueOf(rs.getString("TYPE")))
                            .price(rs.getBigDecimal("PRICE"))
                            .createdDate(rs.getDate("CREATEDDATE"))
                            .itemsInStock(rs.getInt("ITEMSINSTOCK"))
                            .build();
                    productList.add(product);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DatabaseService.close(rs);
                DatabaseService.close(ps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseService.close(connection);
        }
        return productList;
    }

    public void addProduct(Product product) {
        Connection connection = null;
        try {
            connection = DatabaseService.getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = connection.prepareStatement(SQL_ADD_PRODUCT);
                ps.setInt(1,product.getId());
                ps.setString(2, product.getName());
                ps.setString(3, product.getDescription());
                ps.setString(4, product.getType().getType());
                ps.setBigDecimal(5, product.getPrice());
                ps.setDate(6, product.getCreatedDate());
                ps.setInt(7, product.getItemsInStock());
                rs = ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DatabaseService.close(rs);
                DatabaseService.close(ps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseService.close(connection);
        }
    }

    public void editProduct(Product product) {
        Connection connection = null;
        try {
            connection = DatabaseService.getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = connection.prepareStatement(SQL_EDIT_PRODUCT);
                ps.setString(1, product.getName());
                ps.setString(2, product.getDescription());
                ps.setBigDecimal(3, product.getPrice());
                ps.setInt(4, product.getItemsInStock());
                ps.setInt(5,product.getId());
                rs = ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DatabaseService.close(rs);
                DatabaseService.close(ps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseService.close(connection);
        }
    }

    public void deleteProduct(Integer productId) {
        Connection connection = null;
        try {
            connection = DatabaseService.getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = connection.prepareStatement(SQL_DELETE_PRODUCT);
                ps.setInt(1, productId);
                rs = ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DatabaseService.close(rs);
                DatabaseService.close(ps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseService.close(connection);
        }
    }
}

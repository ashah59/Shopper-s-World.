package project.data;

import java.sql.*;
import java.util.ArrayList;
import project.business.Product;

public class ProductDB {

    public static int insert(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO product (name, descr, price, qty, seller) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setString(5, product.getSeller());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE product SET "
                + "descr = ?, "
                + "qty = ?, "
                + "price = ? "
                + "WHERE productId = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getDescription());
            ps.setInt(2, product.getQuantity());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static Product selectProduct(int id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM product "
                + "WHERE productId = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Product product = null;
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("productId"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("descr"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("qty"));
                product.setSeller(rs.getString("seller"));
            }
            return product;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Product> selectProducts() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Product> products;

        String query = "SELECT * FROM product WHERE qty > 0";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            products = new ArrayList<Product>();
            Product product;
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("productId"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("descr"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("qty"));
                product.setSeller(rs.getString("seller"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Product> selectProductsBySeller(String sellerEmail) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Product> products;

        String query = "SELECT * FROM product WHERE seller = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, sellerEmail);
            rs = ps.executeQuery();
            products = new ArrayList<Product>();
            Product product;
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("productId"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("descr"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("qty"));
                product.setSeller(rs.getString("seller"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}

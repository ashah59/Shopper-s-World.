/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import project.business.Order;

/**
 *
 * @author shah
 */
public class OrderDB {
    public static int insert(Order order) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO orders (customer, amount, cardNumber, cardExpMM, cardExpYY, cardCVV, cardName) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, order.getCustEmail());
            ps.setString(2, order.getAmount());
            ps.setString(3, order.getCardNum());
            ps.setInt(4, order.getExpMM());
            ps.setInt(5, order.getExpYY());
            ps.setInt(6, order.getCvv());
            ps.setString(7, order.getCardName());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Order> selectOrdersByEmail(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Order> orders;

        String query = "SELECT * FROM orders WHERE customer = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            orders = new ArrayList<Order>();
            Order order;
            while (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setCustEmail(rs.getString("customer"));
                order.setAmount(rs.getString("amount"));
                order.setCardNum(rs.getString("cardNumber"));
                order.setExpMM(rs.getInt("cardExpMM"));
                order.setExpYY(rs.getInt("cardExpYY"));
                order.setCvv(rs.getInt("cardCVV"));
                order.setCardName(rs.getString("cardName"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int delete(int orderId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM orders "
                + "WHERE orderId = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}

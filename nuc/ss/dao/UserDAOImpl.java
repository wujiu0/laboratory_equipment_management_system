package nuc.ss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nuc.ss.db.DBConn;
import nuc.ss.entity.User;

public class UserDAOImpl implements UserDAO {

    @Override
    public void insert(User user) {
        String sql = "insert into user values(?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement prestmt = null;
        try {
            conn = DBConn.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1, user.getId());
            prestmt.setString(2, user.getPassword());
            prestmt.setString(3, user.getName());
            prestmt.setString(4, user.getSex());
            prestmt.setString(5, user.getPhoneNumber());
            prestmt.setString(6, user.getType());
            prestmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(prestmt, conn);
        }

    }

    @Override
    public void update(User user) {
        String sql = "update user set password = ?, name = ?, sex = ?, phoneNumber = ?, type = ? where id = ?";
        Connection conn = null;
        PreparedStatement prestmt = null;
        try {
            conn = DBConn.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1, user.getPassword());
            prestmt.setString(2, user.getName());
            prestmt.setString(3, user.getSex());
            prestmt.setString(4, user.getPhoneNumber());
            prestmt.setString(5, user.getType());
            prestmt.setString(6, user.getId());
            prestmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(prestmt, conn);
        }
    }

    @Override
    public void remove(String id) {
        String sql = "delete from user where id = ?";
        Connection conn = null;
        PreparedStatement prestmt = null;
        try {
            conn = DBConn.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1, id);
            prestmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(prestmt, conn);
        }

    }

    @Override
    public User query(String id) {
        String sql = "select * from user where id = ?";
        User user = null;
        Connection conn = null;
        PreparedStatement prestmt = null;
        ResultSet rs = null;
        try {
            conn = DBConn.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1, id);
            rs = prestmt.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String phoneNumber = rs.getString("phoneNumber");
                String type = rs.getString("type");
                user = new User(id, password, name, sex, phoneNumber, type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(rs, prestmt, conn);
        }
        return user;
    }

    @Override
    public List<User> queryAll() {
        String sql = "select * from user";
        List<User> list = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConn.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            User user = null;
            list = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String phoneNumber = rs.getString("phoneNumber");
                String type = rs.getString("type");
                user = new User(id, password, name, sex, phoneNumber, type);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(rs, stmt, conn);
        }
        return list;
    }

    @Override
    public boolean check(String id, String password) {
        if (id == null || password == null) {
            return false;
        }

        Connection con = null;
        PreparedStatement prestmt = null;
        ResultSet rs = null;
        try {
            con = DBConn.getConnection();
            String sql = "SELECT * FROM user where id = ? and password = ?";
            prestmt = con.prepareStatement(sql);
            prestmt.setString(1, id);
            prestmt.setString(2, password);
            rs = prestmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(rs, prestmt, con);
        }
        return false;
    }

    @Override
    public String toString() {
        return "成员管理";
    }
}

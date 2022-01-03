package nuc.ss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nuc.ss.db.DBConn;
import nuc.ss.entity.Equipment;

public class EquipmentDAOImpl implements EquipmentDAO {

    @Override
    public void insert(Equipment equipment) {
        String sql = "insert into equipment values(?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement prestmt = null;
        try {
            conn = DBConn.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1, equipment.getId());
            prestmt.setString(2, equipment.getName());
            prestmt.setString(3, equipment.getUser());
            prestmt.setString(4, equipment.getType());
            prestmt.setBoolean(5, equipment.isLent());
            prestmt.setBoolean(6, equipment.isScrap());
            prestmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(prestmt, conn);
        }

    }

    @Override
    public void update(Equipment equipment) {
        String sql = "update equipment set name = ?, user = ?, type = ?, lent = ?, scrap = ? where id = ?";
        Connection conn = null;
        PreparedStatement prestmt = null;
        try {
            conn = DBConn.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1, equipment.getName());
            prestmt.setString(2, equipment.getUser());
            prestmt.setString(3, equipment.getType());
            prestmt.setBoolean(4, equipment.isLent());
            prestmt.setBoolean(5, equipment.isScrap());
            prestmt.setString(6, equipment.getId());
            prestmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(prestmt, conn);
        }
    }

    @Override
    public void remove(String id) {
        String sql = "delete from equipment where id = ?";
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
    public List<Equipment> queryByUser(String user) {
        String sql = "select * from equipment where user = ?";
        List<Equipment> list = null;
        Connection conn = null;
        PreparedStatement prestmt = null;
        ResultSet rs = null;
        try {
            conn = DBConn.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1, user);
            rs = prestmt.executeQuery();
            Equipment equipment = null;
            list = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                boolean lent = rs.getBoolean("lent");
                boolean scrap = rs.getBoolean("scrap");
                equipment = new Equipment(id, name, user, type, lent, scrap);
                list.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(rs, prestmt, conn);
        }
        return list;
    }

    @Override
    public List<Equipment> queryByType(String type) {
        String sql = "select * from equipment where type = ?";
        List<Equipment> list = null;
        Connection conn = null;
        PreparedStatement prestmt = null;
        ResultSet rs = null;
        try {
            conn = DBConn.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1, type);
            rs = prestmt.executeQuery();
            Equipment equipment = null;
            list = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String user = rs.getString("user");
                boolean lent = rs.getBoolean("lent");
                boolean scrap = rs.getBoolean("scrap");
                equipment = new Equipment(id, name, user, type, lent, scrap);
                list.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(rs, prestmt, conn);
        }
        return list;

    }

    @Override
    public List<Equipment> queryByLent(boolean lent) {
        String sql = "select * from equipment where lent = ?";
        List<Equipment> list = null;
        Connection conn = null;
        PreparedStatement prestmt = null;
        ResultSet rs = null;
        try {
            conn = DBConn.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setBoolean(1, lent);
            rs = prestmt.executeQuery();
            Equipment equipment = null;
            list = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String user = rs.getString("user");
                String type = rs.getString("type");
                boolean scrap = rs.getBoolean("scrap");
                equipment = new Equipment(id, name, user, type, lent, scrap);
                list.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(rs, prestmt, conn);
        }
        return list;

    }

    @Override
    public List<Equipment> queryByScrap(boolean scrap) {
        String sql = "select * from equipment where scrap = ?";
        List<Equipment> list = null;
        Connection conn = null;
        PreparedStatement prestmt = null;
        ResultSet rs = null;
        try {
            conn = DBConn.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setBoolean(1, scrap);
            rs = prestmt.executeQuery();
            Equipment equipment = null;
            list = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String user = rs.getString("user");
                String type = rs.getString("type");
                boolean lent = rs.getBoolean("lent");
                equipment = new Equipment(id, name, user, type, lent, scrap);
                list.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(rs, prestmt, conn);
        }
        return list;

    }

    @Override
    public List<Equipment> query(String key, Object value) {
        String sql = "select * from equipment where " + key + " = ?";
        List<Equipment> list = null;
        Connection conn = null;
        PreparedStatement prestmt = null;
        ResultSet rs = null;
        try {
            conn = DBConn.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setObject(1, value);
            rs = prestmt.executeQuery();
            Equipment equipment = null;
            list = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String user = rs.getString("user");
                String type = rs.getString("type");
                boolean scrap = rs.getBoolean("scrap");
                boolean lent = rs.getBoolean("lent");
                equipment = new Equipment(id, name, user, type, lent, scrap);
                list.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(rs, prestmt, conn);
        }
        return list;
    }

    @Override
    public List<Equipment> queryAll() {
        String sql = "select * from equipment";
        List<Equipment> list = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConn.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            Equipment equipment = null;
            list = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String user = rs.getString("user");
                String type = rs.getString("type");
                boolean lent = rs.getBoolean("lent");
                boolean scrap = rs.getBoolean("scrap");
                equipment = new Equipment(id, name, user, type, lent, scrap);
                list.add(equipment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(rs, stmt, conn);
        }
        return list;
    }

    @Override
    public String toString() {
        return "设备管理";
    }

    @Override
    public Equipment queryById(String id) {
        String sql = "select * from equipment where id = ?";
        Equipment equipment = null;
        Connection conn = null;
        PreparedStatement prestmt = null;
        ResultSet rs = null;
        try {
            conn = DBConn.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1, id);
            rs = prestmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String user = rs.getString("user");
                String type = rs.getString("type");
                boolean lent = rs.getBoolean("lent");
                boolean scrap = rs.getBoolean("scrap");
                equipment = new Equipment(id, name, user, type, lent, scrap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConn.close(rs, prestmt, conn);
        }
        return equipment;
    }

}

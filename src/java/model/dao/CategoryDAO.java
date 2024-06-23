package model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import model.Category;
import utilities.ConnectDB;

public class CategoryDAO implements Serializable, Accessible<Category> {

    private ServletContext sc;
    private Connection con;

    public CategoryDAO()
            throws ClassNotFoundException, SQLException {
        con = new ConnectDB().getConnection();
    }

    public CategoryDAO(ServletContext sc)
            throws ClassNotFoundException, SQLException {
        this.sc = sc;
        con = new ConnectDB(sc).getConnection();
    }

    private Connection getConnect(ServletContext sc)
            throws ClassNotFoundException, SQLException {
        this.sc = sc;
        return con = new ConnectDB(sc).getConnection();
    }

    @Override
    public int insertRec(Category obj) throws SQLException {
        int result = 0;
        PreparedStatement smt = null;

        try {
            String sql = " INSERT INTO categories(categoryName, memo) VALUES (?, ?) ;";
            smt = con.prepareStatement(sql);
            smt.setString(1, obj.getCategoryName());
            smt.setString(2, obj.getMemo());

            result = smt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (smt != null) {
                smt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    @Override
    public int updateRec(Category obj) throws SQLException {
        int result = 0;
        PreparedStatement cmd = null;
        try {
            String sql = "UPDATE categories "
                    + "SET categoryName=?, memo=? "
                    + "WHERE typeId=? ;";
            cmd = this.con.prepareStatement(sql);
            cmd.setString(1, obj.getCategoryName());
            cmd.setString(2, obj.getMemo());
            cmd.setInt(3, obj.getTypeId());

            result = cmd.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cmd != null) {
                cmd.close();
            }
            if (con != null) {
                con.close();
            }

            return result;
        }
    }

    public int deleteRec(String key) throws SQLException {
        int result = 0;
        Statement cmd = null;

        try {
            String sql = "DELETE categories WHERE typeId='" + key.trim() + "';";
            cmd = con.createStatement();

            result = cmd.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cmd != null) {
                cmd.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return result;
    }

    @Override
    public int deleteRec(Category obj) throws SQLException {
        int result = 0;
        Statement cmd = null;

        try {
            String sql = "DELETE categories WHERE typeId='" + obj.getTypeId() + "';";
            cmd = con.createStatement();

            result = cmd.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cmd != null) {
                cmd.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return result;
    }

    @Override
    public List<Category> listAll() throws SQLException {
        List<Category> list = new ArrayList<>();
        Category c = null;
        Statement smt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM categories;";
            smt = con.createStatement();
            rs = smt.executeQuery(sql);

            while (rs.next()) {
                c = new Category();
                c.setTypeId(rs.getInt("typeId"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setMemo(rs.getString("memo"));

                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (smt != null) {
                smt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    @Override
    public Category getObjectById(String id) throws SQLException {
        Category ctg = null;
        Statement cmd = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * "
                    + "FROM categories "
                    + "WHERE typeId='" + id.trim() + "';";

            cmd = con.createStatement();
            rs = cmd.executeQuery(sql);

            // mỗi username chỉ được sử dụng một lần
            boolean flag = true;
            while (rs.next() && flag) {
                ctg = new Category();
                ctg.setTypeId(rs.getInt("typeId"));
                ctg.setCategoryName(rs.getString("categoryName"));
                ctg.setMemo(rs.getString("memo"));

                flag = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cmd != null) {
                cmd.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return ctg;
    }

    public Category getCateById(int id) throws SQLException {
        Category ctg = null;
        Statement cmd = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * "
                    + "FROM categories "
                    + "WHERE typeId=" + id + " ;";

            cmd = con.createStatement();
            rs = cmd.executeQuery(sql);

            // mỗi username chỉ được sử dụng một lần
            boolean flag = true;
            while (rs.next() && flag) {
                ctg = new Category();
                ctg.setTypeId(rs.getInt("typeId"));
                ctg.setCategoryName(rs.getString("categoryName"));
                ctg.setMemo(rs.getString("memo"));

                flag = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cmd != null) {
                cmd.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return ctg;
    }
    
    public Category getCateByName(String name) throws SQLException {
        Category ctg = null;
        Statement cmd = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * "
                    + "FROM categories "
                    + "WHERE categoryName= N'" + name + "' ;";

            cmd = con.createStatement();
            rs = cmd.executeQuery(sql);

            // mỗi username chỉ được sử dụng một lần
            boolean flag = true;
            while (rs.next() && flag) {
                ctg = new Category();
                ctg.setTypeId(rs.getInt("typeId"));
                ctg.setCategoryName(rs.getString("categoryName"));
                ctg.setMemo(rs.getString("memo"));

                flag = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cmd != null) {
                cmd.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return ctg;
    }
}

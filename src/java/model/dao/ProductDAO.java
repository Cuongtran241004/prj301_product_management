package model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import model.Account;
import model.Category;
import model.Product;
import utilities.ConnectDB;

/**
 * ProductDAO - connect to database and have CRUD methods to work with table
 * [dbo.products] ServletContext - host | instance | port | databaseName | user
 * | password
 */
public class ProductDAO implements Serializable, Accessible<Product> {

    private ServletContext sc;
    private Connection con;

    // Default constructor
    public ProductDAO()
            throws ClassNotFoundException, SQLException {
        con = new ConnectDB().getConnection();
    }

    // Constructor with ServletContext
    public ProductDAO(ServletContext sc)
            throws ClassNotFoundException, SQLException {
        this.sc = sc;
        con = new ConnectDB(sc).getConnection();
    }

    /**
     * @param ServletContext
     * @return Connection
     */
    private Connection getConnect(ServletContext sc)
            throws ClassNotFoundException, SQLException {
        this.sc = sc;
        con = new ConnectDB(sc).getConnection();
        return con;
    }

    /**
     * Add a new product to database
     *
     * @param Product
     * @return int
     */
    @Override
    public int insertRec(Product obj) throws SQLException {
        int result = 0;
        PreparedStatement smt = null;

        try {
            String sql = "INSERT INTO products "
                    + "(productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            smt = con.prepareStatement(sql);
            smt.setString(1, obj.getProductId());
            smt.setString(2, obj.getProductName());
            smt.setString(3, obj.getProductImage());
            smt.setString(4, obj.getBrief());
            smt.setTimestamp(5, (Timestamp) obj.getPostedDate());
            Category c = obj.getType();
            smt.setInt(6, c.getTypeId());
            Account a = obj.getAccount();
            smt.setString(7, a.getAccount());
            smt.setString(8, obj.getUnit());
            smt.setInt(9, obj.getPrice());
            smt.setInt(10, obj.getDiscount());

            result = smt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * Update existed product in database
     *
     * @param Product
     * @return int
     */
    @Override
    public int updateRec(Product obj) throws SQLException {
        int result = 0;
        PreparedStatement smt = null;

        try {
            String sql = " UPDATE products "
                    + "SET productName=?, brief=?, postedDate=?, "
                    + "typeId=?, account=?, unit=?, price=?, discount=? "
                    + "WHERE productId = ?;";
            smt = con.prepareStatement(sql);

            smt.setString(1, obj.getProductName());
            smt.setString(2, obj.getBrief());
            smt.setTimestamp(3, (Timestamp) obj.getPostedDate());
            Category c = obj.getType();
            smt.setInt(4, c.getTypeId());
            Account a = obj.getAccount();
            smt.setString(5, a.getAccount());
            smt.setString(6, obj.getUnit());
            smt.setInt(7, obj.getPrice());
            smt.setInt(8, obj.getDiscount());
            smt.setString(9, obj.getProductId());
            result = smt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * Update product image in database
     *
     * @param Product
     * @return int
     */
    public int updatePhoto(Product obj) throws SQLException {
        int result = 0;
        PreparedStatement smt = null;

        try {
            String sql = " UPDATE products "
                    + "SET productImage= ? WHERE productId = ?;";
            smt = con.prepareStatement(sql);
            smt.setString(1, obj.getProductImage());
            smt.setString(2, obj.getProductId());
            result = smt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * Delete existed product in database by productId
     *
     * @param String productId
     * @return int
     */
    public int deleteRec(String key) throws SQLException {
        int result = 0;
        Statement cmd = null;

        try {
            String sql = "DELETE products WHERE productId='" + key.trim() + "';";
            cmd = con.createStatement();

            result = cmd.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * Delete existed product in database
     *
     * @param Product
     * @return int
     */
    @Override
    public int deleteRec(Product obj) throws SQLException {
        int result = 0;
        Statement cmd = null;

        try {
            String sql = "DELETE products WHERE productId='" + obj.getProductId() + "';";
            cmd = con.createStatement();

            result = cmd.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<Product> listByCategory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Get all products in database
     *
     * @return List<Product>
     */
    @Override
    public List<Product> listAll() throws SQLException {
        List<Product> list = new ArrayList<>();
        Statement smt = null;
        ResultSet rs = null;
        Product product = null;
        try {
            String sql = "SELECT * FROM products";
            smt = con.createStatement();
            rs = smt.executeQuery(sql);

            while (rs.next()) {
                product = new Product();
                product.setProductId(rs.getString("productId"));
                product.setProductName(rs.getString("productName"));
                product.setProductImage(rs.getString("productImage"));
                product.setBrief(rs.getString("brief"));
                product.setType(new CategoryDAO().getObjectById(rs.getString("typeId")));
                product.setPostedDate(rs.getTimestamp("postedDate"));
                product.setUnit(rs.getString("unit"));
                product.setPrice(rs.getInt("price"));
                product.setDiscount(rs.getInt("discount"));

                list.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * Get product in database by id
     *
     * @param String productId
     * @return Product
     */
    @Override
    public Product getObjectById(String id) throws SQLException {
        Product product = null;
        Statement cmd = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * "
                    + "FROM products "
                    + "WHERE productId='" + id.trim() + "';";

            cmd = con.createStatement();
            rs = cmd.executeQuery(sql);

            // mỗi username chỉ được sử dụng một lần
            boolean flag = true;
            while (rs.next() && flag) {
                product = new Product();
                product.setProductId(rs.getString("productId"));
                product.setProductName(rs.getString("productName"));
                product.setProductImage(rs.getString("productImage"));
                product.setBrief(rs.getString("brief"));
                product.setType(new CategoryDAO().getObjectById(rs.getString("typeId")));
                product.setPostedDate(rs.getTimestamp("postedDate"));
                product.setUnit(rs.getString("unit"));
                product.setPrice(rs.getInt("price"));
                product.setDiscount(rs.getInt("discount"));
                flag = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
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

        return product;
    }

}

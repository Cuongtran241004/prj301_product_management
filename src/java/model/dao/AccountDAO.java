package model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import model.Account;
import utilities.ConnectDB;

/**
 * AccountDAO - connect to database and have CRUD methods to work with table
 * [dbo.accounts] ServletContext - host | instance | port | databaseName | user
 * | password
 */
public class AccountDAO implements Serializable, Accessible<Account> {

    private ServletContext sc;
    private Connection con;

    // Default constructor
    public AccountDAO()
            throws ClassNotFoundException, SQLException {
        this.con = new ConnectDB().getConnection();
    }

    // Constructor with ServletContext
    public AccountDAO(ServletContext sc)
            throws ClassNotFoundException, SQLException {
        ConnectDB connect = new ConnectDB(sc);
        con = connect.getConnection();
    }

    /**
     * @param ServletContext
     * @return Connection
     */
    private Connection getConnect(ServletContext sc)
            throws ClassNotFoundException, SQLException {
        ConnectDB connect = new ConnectDB(sc);
        con = connect.getConnection();
        return con;
    }

    /**
     * Add a new account to database
     *
     * @param Account
     * @return int
     */
    @Override
    public int insertRec(Account obj) throws SQLException {
        int result = 0;
        PreparedStatement cmd = null;
        try {
            String sql = "insert into accounts"
                    + " (account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem) "
                    + " values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            cmd = this.con.prepareStatement(sql);

            cmd.setString(1, obj.getAccount());
            cmd.setString(2, obj.getPass());
            cmd.setString(3, obj.getLastName());
            cmd.setString(4, obj.getFirstName());
            cmd.setDate(5, (Date) obj.getBirthday());
            cmd.setBoolean(6, obj.isGender());
            cmd.setString(7, obj.getPhone());
            cmd.setBoolean(8, obj.isIsUse());
            cmd.setInt(9, obj.getRoleInSystem());

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
        }

        return result;
    }

    /**
     * Update existed account in database
     *
     * @param Account
     * @return int
     */
    @Override
    public int updateRec(Account obj) throws SQLException {
        int result = 0;
        PreparedStatement cmd = null;

        try {
            String sql = "UPDATE accounts "
                    + "SET pass=?, lastName=?, firstName=?, birthday=?, gender=?, phone=?, isUse=?, roleInSystem=? "
                    + "WHERE account=? ;";
            cmd = this.con.prepareStatement(sql);

            cmd.setString(1, obj.getPass());
            cmd.setString(2, obj.getLastName());
            cmd.setString(3, obj.getFirstName());
            cmd.setDate(4, (Date) obj.getBirthday());
            cmd.setBoolean(5, obj.isGender());
            cmd.setString(6, obj.getPhone());
            cmd.setBoolean(7, obj.isIsUse());
            cmd.setInt(8, obj.getRoleInSystem());
            cmd.setString(9, obj.getAccount().trim());

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

    /**
     * Delete existed account in database by account
     *
     * @param String account
     * @return int
     */
    public int deleteRec(String key) throws SQLException {
        int result = 0;
        Statement cmd = null;

        try {
            String sql = "DELETE accounts WHERE account='" + key.trim() + "';";
            cmd = con.createStatement();

            result = cmd.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
     * Delete existed account in database
     *
     * @param Account
     * @return int
     */
    @Override
    public int deleteRec(Account obj) throws SQLException {
        int result = 0;
        Statement cmd = null;

        try {
            String sql = "DELETE accounts WHERE account='" + obj.getAccount().trim() + "';";
            cmd = con.createStatement();
            result = cmd.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);

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
     * Get all accounts in database by role
     *
     * @param int roleInSystem
     * @return List<Account>
     */
    public List<Account> listByRole(int role) throws SQLException {
        List<Account> list = new ArrayList<>();
        Statement smt = null;
        ResultSet rs = null;
        Account account = null;
        try {
            String sql = "SELECT * FROM accounts WHERE roleInSystem=" + role + ";";
            smt = con.createStatement();
            rs = smt.executeQuery(sql);

            while (rs.next()) {
                account = new Account();
                account.setAccount(rs.getString("account"));
                account.setLastName(rs.getString("lastName"));
                account.setFirstName(rs.getString("firstName"));
                account.setBirthday(rs.getDate("birthday"));
                account.setGender(rs.getBoolean("gender"));
                account.setPhone(rs.getString("phone"));
                account.setIsUse(rs.getBoolean("isUse"));
                account.setRoleInSystem(rs.getInt("roleInSystem"));

                list.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
     * Get all accounts in database
     *
     * @return List<Account>
     */
    @Override
    public List<Account> listAll() throws SQLException {
        List<Account> list = new ArrayList<>();
        Statement smt = null;
        ResultSet rs = null;
        Account account = null;
        try {
            String sql = "SELECT account, lastName, firstName, birthday, gender, phone, isUse, roleInSystem FROM accounts;";
            smt = con.createStatement();
            rs = smt.executeQuery(sql);

            while (rs.next()) {
                account = new Account();
                account.setAccount(rs.getString("account"));
                account.setLastName(rs.getString("lastName"));
                account.setFirstName(rs.getString("firstName"));
                account.setBirthday(rs.getDate("birthday"));
                account.setGender(rs.getBoolean("gender"));
                account.setPhone(rs.getString("phone"));
                account.setIsUse(rs.getBoolean("isUse"));
                account.setRoleInSystem(rs.getInt("roleInSystem"));

                list.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
     * Update isUse attribute account in database
     *
     * @param String account
     * @param boolean isUsed
     * @return int
     */
    public int updateIsUsed(String acc, boolean isUsed) throws SQLException {
        int result = 0;
        PreparedStatement smt = null;

        try {
            String sql = "UPDATE accounts SET isUse= ? WHERE account= ?";
            smt = con.prepareStatement(sql);
            smt.setBoolean(1, isUsed);
            smt.setString(2, acc.trim());
            result = smt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
     * Get an account in database
     *
     * @param String account
     * @param String pass
     * @return Account
     */
    public Account loginSuccess(String account, String pass) throws SQLException {
        Account acc = null;
        Statement cmd = null;
        ResultSet rs = null;
        if (account.charAt(0) == '\'') {
            return null;
        }
        try {
            String sql = "SELECT account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem "
                    + "FROM accounts "
                    + "WHERE account='" + account.trim() + "' "
                    + "AND pass='" + pass.trim() + "';";

            cmd = con.createStatement();
            rs = cmd.executeQuery(sql);

            // mỗi username chỉ được sử dụng một lần
            boolean flag = true;
            while (rs.next() && flag) {
                acc = new Account();
                acc.setAccount(rs.getString("account"));
                acc.setPass(rs.getString("pass"));
                acc.setLastName(rs.getString("lastName"));
                acc.setFirstName(rs.getString("firstName"));
                acc.setBirthday(rs.getDate("birthday"));
                acc.setGender(rs.getBoolean("gender"));
                acc.setPhone(rs.getString("phone"));
                acc.setIsUse(rs.getBoolean("isUse"));
                acc.setRoleInSystem(rs.getInt("roleInSystem"));
                flag = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return acc;
    }

    /**
     * Get an account in database by id
     *
     * @param String account
     * @return Account
     */
    @Override
    public Account getObjectById(String account) throws SQLException {
        Account acc = null;
        Statement cmd = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem "
                    + "FROM accounts "
                    + "WHERE account='" + account.trim() + "';";

            cmd = con.createStatement();
            rs = cmd.executeQuery(sql);

            // mỗi username chỉ được sử dụng một lần
            boolean flag = true;
            while (rs.next() && flag) {
                acc = new Account();
                acc.setAccount(rs.getString("account"));
                acc.setPass(rs.getString("pass"));
                acc.setLastName(rs.getString("lastName"));
                acc.setFirstName(rs.getString("firstName"));
                acc.setBirthday(rs.getDate("birthday"));
                acc.setGender(rs.getBoolean("gender"));
                acc.setPhone(rs.getString("phone"));
                acc.setIsUse(rs.getBoolean("isUse"));
                acc.setRoleInSystem(rs.getInt("roleInSystem"));
                flag = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return acc;
    }
}

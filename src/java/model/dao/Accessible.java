
package model.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Common methods used to perform business actions for Data Access Object
 * (Using generic type for all of DAO classes)
 * @author Tran Quoc Cuong
 */
public interface Accessible<T> {
    int insertRec(T obj) throws SQLException;
    int updateRec(T obj) throws SQLException;
    int deleteRec(T obj) throws SQLException;
    T getObjectById(String id) throws SQLException;
    List<T> listAll() throws SQLException;
}

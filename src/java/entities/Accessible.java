package entities;

import java.util.List;

/**
 *
 * @author ACER
 */
public interface Accessible<T> {

    int insertRec(T obj);

    int updateRec(T obj);

    int deleteRec(T obj);

    T getObjectById(String id);

    List<T> listAll();
}

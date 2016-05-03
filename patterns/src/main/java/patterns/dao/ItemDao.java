package patterns.dao;

import patterns.dao.model.Model;

import java.util.List;

public interface ItemDao<T extends Model> {
    T getById(Long id);

    List<T> getAll();

    void put(T item);

    boolean update(T item);

    boolean delete(T item);

}

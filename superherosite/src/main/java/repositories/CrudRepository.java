package repositories;

import java.util.LinkedList;
import java.util.List;

interface CrudRepository<T, ID> {
    T findById(ID id);
    List<T> findAll();
    void delete(ID id);
    void update(T model);
    void save(T model);
}

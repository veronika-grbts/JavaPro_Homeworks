package lesson30;

import java.util.List;

public interface GenericDao <T, ID>{
    void save(T entity);

    T findById(ID id);

    List<T> findByEmail(String email);

    List<T> findAll();

    Student update(T entity);

    boolean deleteById(ID id);
}

package pl.blogservice.repository;

import java.util.List;

public interface DataRepository<T> {

    void save(T entity);

    List<T> findAll();

    void remove(T entity);

}

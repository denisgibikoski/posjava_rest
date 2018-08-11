package br.edu.utfpr.pb.posjava.repository;

import br.edu.utfpr.pb.posjava.model.AbstractModel;
import java.io.Serializable;

import java.util.List;

public interface Repository<T extends AbstractModel, ID extends Serializable> {

    T save(T entity);

    T findOne(ID id);

    List<T> findAll();
    
    List<T> findAll(int pageNumber, int pageSize);
    
    ID getTotalRecords();

    void remove(ID id);
}

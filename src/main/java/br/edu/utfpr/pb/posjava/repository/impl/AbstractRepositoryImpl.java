package br.edu.utfpr.pb.posjava.repository.impl;

import br.edu.utfpr.pb.posjava.model.AbstractModel;
import br.edu.utfpr.pb.posjava.repository.Repository;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public abstract class AbstractRepositoryImpl<T extends AbstractModel, ID extends Serializable>
        implements Repository<T, ID> {

    @Inject
    protected EntityManager entityManager;

    private Class<T> typeParameterClass;

    public AbstractRepositoryImpl() {
        this.typeParameterClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T save(T entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
        entityManager.flush();
        return entity;
    }

    @Override
    public T findOne(ID id) {
        return entityManager.find(typeParameterClass, id);
    }

    @Override
    public List<T> findAll() {
       return entityManager.createQuery("from " + typeParameterClass.getName()
                + " Order By id").getResultList();
        /*
        return entityManager
                .createQuery(
                        String.format("select e from %s e order by id", typeParameterClass.getName()),
                        typeParameterClass)
                .getResultList();*/
    }

    @Override
    public List<T> findAll(int pageNumber, int pageSize) {
        return entityManager
                .createQuery(
                        String.format("select e from %s e order by id",
                                typeParameterClass.getName()),
                        typeParameterClass)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public ID getTotalRecords() {
        return (ID) entityManager.createQuery(
                String.format("select count(e.id) from %s e",
                        typeParameterClass.getName())).getSingleResult();
    }

    @Override
    public void remove(ID id) {
        entityManager.remove(findOne(id));
    }

    public boolean isValid(T entity) {
        final Validator validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        return (validator.validate(entity).isEmpty());
    }

    public String getErrors(T entity) {
        final Validator validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        final Set<ConstraintViolation<T>> violations = validator
                .validate(entity);
        String errors = "";
        if (!violations.isEmpty()) {
            errors = violations.stream().map((violation)
                    -> violation.getMessage() + "\n")
                    .reduce(errors, String::concat);
        }
        return errors;
    }
}

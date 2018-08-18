package app.dao.interfaces;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
@Transactional
public interface GenericDao<PK extends Serializable, T> {

	void persist(T entity);

    T getByKey(PK id);

    List<T> getAll();

    void update(T group);

    void deleteByKey(PK id);
}

package dao;

import java.io.Serializable;
import java.util.List;


/**
 * Created by yangxingrom on 2017/11/14.
 */
public interface BackDAO <T , ID extends Serializable>{
    public void save(T entity);
    public void update(T entity);
    public void delect(T entity);
    public void delect2(ID id);
    public T findByID(ID id);
    public List<T> findAll();
    public List<T> findByHql(String sql,Object... params);
}

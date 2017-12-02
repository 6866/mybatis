package dao;

import entity.Micro_message;

import java.util.List;

/**
 * Created by yangxingrom on 2017/11/15.
 */
public interface MicroMessageDao {

    void save(Micro_message message);

    void update(Micro_message message);

    List<Micro_message> findAll(Micro_message message);

    void delete(Integer id);

    Micro_message findById(Integer id);

    void deleteBatch(String[] ids);
}

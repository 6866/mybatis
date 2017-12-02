package dao;

import entity.Command;
import entity.Command_content;
import entity.Micro_message;
import entity.Pages;

import java.util.List;

/**
 * Created by yangxingrom on 2017/11/15.
 */
public interface CommandDao {

    void save(Command command);

    void update(Command command);

    List<Command> findAll(String command,String description, Pages pages);

    void delete(Integer id);

    Command findById(Integer id);

    void deleteBatch(Integer[] ids);
    void deleteContent(Integer[] id);
    int findCount(String command, String description);
    Command_content findOneRandom(String command);
}

package dao.Impl;

import dao.CommandDao;
import dao.IcommandDao;
import db.DBaccess;
import entity.Command;
import entity.Command_content;
import entity.Pages;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxingrom on 2017/11/21.
 */
public class CommandDaoImpl implements CommandDao {

    @Override
    public void save(Command command) {

    }

    @Override
    public void update(Command command) {
        SqlSession session = null;

        try {
            session = DBaccess.getSession();
            session.update("config.mapper.micro_message.update", command);
            List<Command_content> contents = command.getContents();
            if (contents != null && contents.size() > 0) {
                for (Command_content content : command.getContents()) {
                    if (content.getId() != null) {
                        session.update("config.mapper.command_content.updateContent", command.getContents());
                        break;
                    }
                }
                for (Command_content content : command.getContents()) {
                    if (content.getId() == null) {
                        session.insert("config.mapper.command_content.insert", command);
                        break;
                    }
                }


            }

            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }


    }
    //查找
    @Override
    public List<Command> findAll(String command, String description, Pages pages) {
        SqlSession session = null;
        List<Command> list = new ArrayList<>();
        try {
            session = DBaccess.getSession();
            Map<String,Object> map = new HashMap<>();
            map.put("message",new Command(null, command, description, null));
            map.put("page",pages);
            list = session.selectList("config.mapper.micro_message.findAllByPage", map);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;

    }
    //删除单条命令
    @Override
    public void delete(Integer id) {
        SqlSession session = null;

        try {
            session = DBaccess.getSession();
            session.delete("config.mapper.command_content.deleteCommand_id", id);

            session.delete("config.mapper.micro_message.delete", id);
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    /**
     * 查找一条命令的详细内容
     * @param id
     * @return
     */
    @Override
    public Command findById(Integer id) {
        SqlSession session = null;
        Command command = null;
        try {
            session = DBaccess.getSession();
            //command = session.selectOne("config.mapper.micro_message.findOne", id);
            IcommandDao dao = session.getMapper(IcommandDao.class);//利用接口式编程,接口的package和mapper.xml中的namespace要相同，并且加上类名
            command = dao.findOne(id);//他的作用是限制参数，返回类型。
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return command;
    }

    /**
     * 批量删除命令，并且删除命令中的内容
     * @param ids
     */
    @Override
    public void deleteBatch(Integer[] ids) {
        SqlSession session = null;

        try {
            session = DBaccess.getSession();
            session.delete("config.mapper.command_content.deleteCommand_ids", ids);
            session.delete("config.mapper.micro_message.deletes", ids);
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * 删除内容方法，批量删除，单个删除都可以用
     * @param id
     */
    @Override
    public void deleteContent(Integer[] id) {
        SqlSession session = null;
        try {
            session = DBaccess.getSession();
            session.delete("config.mapper.command_content.deleteContent", id);
            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * 根据命令去随机查一条内容
     * @param command 命令
     * @return
     */
    @Override
    public Command_content findOneRandom(String command) {
        Command_content content = new Command_content();
        SqlSession session = null;
        try {
            session = DBaccess.getSession();
            content = session.selectOne("config.mapper.micro_message.selectRandom",command);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return content;
    }

    public int findCount(String command, String description){
        SqlSession session = null;
        int page = 0;
        try {
            session = DBaccess.getSession();
            page = session.selectOne("config.mapper.micro_message.count",new Command(null, command, description, null));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return page;

    }


}

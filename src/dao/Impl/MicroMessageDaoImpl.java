package dao.Impl;

import dao.MicroMessageDao;
import db.DBaccess;
import entity.Micro_message;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxingrom on 2017/11/15.
 */
public class MicroMessageDaoImpl implements MicroMessageDao {
    private String backStatement = "config.mapper.micro_message.";
    @Override
    public void save(Micro_message message) {

    }

    @Override
    public void update(Micro_message message) {

    }

    @Override
    public List<Micro_message> findAll(Micro_message message) {
        System.out.println(message);
        String statement = backStatement + "findAll";
        SqlSession session = null;
        List<Micro_message> list = new ArrayList<>();
        try {
            session = DBaccess.getSession();
            if(message != null){
                list = session.selectList(statement,message);
            }else{
                list = session.selectList(statement);
            }
          //  System.out.println(list.get(0));

        } catch (IOException e) {
            System.out.println("出错了");
            e.printStackTrace();
        }finally {
            session.close();
        }

        return list;
    }

    @Override
    public void delete(Integer id) {
        String statement = backStatement + "deleteOne";
        SqlSession session = null;
        try {
            session = DBaccess.getSession();
            session.delete(statement,id);
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    @Override
    public Micro_message findById(Integer id) {
        String statement = backStatement + "getMessage";
        SqlSession session = null;
        Micro_message message = new Micro_message();
        try {
            session = DBaccess.getSession();
            message = session.selectOne(statement,1);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return message;
    }

    @Override
    public void deleteBatch(String[] ids) {
        String statement = backStatement + "deleteBatch";
        SqlSession session = null;
        try{
            session = DBaccess.getSession();
            session.delete(statement,ids);
            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}

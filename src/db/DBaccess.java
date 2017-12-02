package db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


/**
 * Created by yangxingrom on 2017/11/14.
 */
public class DBaccess {

    public static SqlSession getSession() throws IOException {
        System.out.println("DB开始加载。。。");
        //通过配置文件获取配置信息

          


        Reader reader =  Resources.getResourceAsReader("config/myBatis.xml");
        //通过配置信息构建一个SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        return sqlSessionFactory.openSession();


        }
    }


package dao.interceptor;

import entity.Pages;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yangxingrom on 2017/11/27.
 */
@Intercepts(value = {@Signature(type = StatementHandler.class, method = "prepare", args = Connection.class)})
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();//获取这个接口，这个接口主要包括执行sql语句的实现类
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);//通过mybatis提供的反射，获取其中的参数
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");//获取MappedStatement对象
        String sqlId = mappedStatement.getId();//获取配置文件中的id
        if (sqlId.matches(".+ByPage$")) {//判断id是否符合规则
            BoundSql boundSql = statementHandler.getBoundSql();//获取BoundSql对象
            String sql = boundSql.getSql();//通过BoundSql获取原始的sql语句
            String countSql = "select count(*) from(" + sql + ")a";//嵌套查询，查询count
            Connection connection = (Connection) invocation.getArgs()[0];//获取拦截方法中的参数，
            PreparedStatement countStaement = connection.prepareStatement(countSql);
            /**
             * 1）PreparedStatement 实例包含已编译的 SQL 语句。这就是使语句“准备好”。包含于 PreparedStatement 对象中的 SQL 语句可具有一个或多个 IN 参数。IN参数的值在 SQL 语句创建时未被指定。相反的，该语句为每个 IN 参数保留一个问号（“？”）作为占位符。每个问号的值必须在该语句执行之前，通过适当的setXXX 方法来提供。
             2）由于 PreparedStatement 对象已预编译过，所以其执行速度要快于 Statement 对象。因此，多次执行的 SQL 语句经常创建为 PreparedStatement 对象，以提高效率。
             */
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(countStaement);//在mybatis中设置PreparedStatement，然后才可以执行语句
            ResultSet resultSet = countStaement.executeQuery(countSql);
            Map<String, Object> parameterMap = (Map<String, Object>) boundSql.getParameterObject();
            Pages pages = (Pages) parameterMap.get("page");
            if (resultSet.next()){
                pages.setSumNumber(resultSet.getInt(1));
            }
            String pageSql = sql + " limit " + pages.getSqlIndex() + "," + pages.getSqlNumber();
            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("初始化信息：" +properties.get("test"));
    }
}
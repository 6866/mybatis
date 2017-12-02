package dao;

import entity.Command;

import java.util.List;

/**
 * Created by yangxingrom on 2017/11/26.
 */
public interface IcommandDao {

    Command findOne(Integer id);
}

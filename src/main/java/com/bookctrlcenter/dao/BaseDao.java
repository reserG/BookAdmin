package com.bookctrlcenter.dao;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface BaseDao<T> {

    //添加单个对象
    int insert(T t);

    //修改单个对象
    int update(T t);

    //删除单个对象
    int delete(T t);

    //查询单个对象
    T select(T t);

    int IfExit(T t);

    List<T> listAll(T t);

    int updateCount(T t);

    T selectByOptions(T t);

   int getDataCount(T t);
}

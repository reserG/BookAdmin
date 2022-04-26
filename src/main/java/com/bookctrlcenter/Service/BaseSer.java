package com.bookctrlcenter.Service;


import java.util.List;

public interface BaseSer<T> {

    //添加单个对象
    int insert(T t);

    //修改单个对象
    int update(T t);

    //删除单个对象
    int delete(T t);

    //查询单个对象
    T select(T t);

     int IfExit(T t);

     List<T>listAll(T t);

    int getDataCount(T t);

}

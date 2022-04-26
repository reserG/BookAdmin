package com.bookctrlcenter.Service.impl;


import com.bookctrlcenter.Service.BaseSer;
import com.bookctrlcenter.dao.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseSerImp<T> implements BaseSer<T> {

    @Autowired
    protected BaseDao<T> baseDao;

    @Autowired
    protected BookDao bookDao;

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected ClassDao classDao;

    @Autowired
    protected ClassSdDao classSdDao;

    @Autowired
    protected SupplierDao supplierDao;

    @Autowired
    protected PrintersDao printersDao;

    @Override
    public int getDataCount(T t) {
        return baseDao.getDataCount(t);
    }

    @Override
    public int insert(T t) {
        return baseDao.insert(t);
    }

    @Override
    public int update(T t) {
        return baseDao.update(t);
    }

    @Override
    public int delete(T t) {
        return baseDao.delete(t);
    }

    @Override
    public List<T> listAll(T t) {
        return baseDao.listAll(t);
    }



    @Override
    public T select(T t) {
        System.out.println(t+"baseimp");
        return (T)baseDao.select(t);
    }

    @Override
    public int IfExit(T t) {
        return baseDao.IfExit(t);
    }


}

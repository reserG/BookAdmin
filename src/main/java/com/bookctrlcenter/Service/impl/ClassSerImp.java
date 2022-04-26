package com.bookctrlcenter.Service.impl;

import com.bookctrlcenter.Service.ClassSer;
import com.bookctrlcenter.entity.Book;
import com.bookctrlcenter.entity.ClassInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("classSerImp")
public class ClassSerImp extends BaseSerImp<ClassInfo> implements ClassSer {
    @Override
    public int moneySubmit(ClassInfo classInfo) {
        return classDao.moneySubmit(classInfo);
    }

    @Override
    public List<Book> searchBookForClass(ClassInfo classInfo) {
        return classDao.searchBookForClass(classInfo);
    }

    @Override
    public List<ClassInfo> listAllBySMUserID(ClassInfo classInfo) {
        return classDao.listAllBySMUserID(classInfo);
    }

    @Override
    public int getTotalPrice(int classID) {
        return classDao.getTotalPrice(classID);
    }
}

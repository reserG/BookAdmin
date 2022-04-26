package com.bookctrlcenter.dao;

import com.bookctrlcenter.entity.Book;
import com.bookctrlcenter.entity.ClassInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClassDao extends BaseDao<ClassInfo> {
    List<Book> searchBookForClass(ClassInfo classInfo);
    List<ClassInfo> listAllBySMUserID(ClassInfo classInfo); //教材结算列表
    int getTotalPrice(int classID);
    int moneySubmit(ClassInfo classInfo);
}

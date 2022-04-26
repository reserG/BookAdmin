package com.bookctrlcenter.Service;

import com.bookctrlcenter.entity.Book;
import com.bookctrlcenter.entity.ClassInfo;

import java.util.List;

public interface ClassSer extends BaseSer<ClassInfo> {
    List<Book> searchBookForClass(ClassInfo classInfo);
    List<ClassInfo> listAllBySMUserID(ClassInfo classInfo);
    int getTotalPrice(int classID);
    int moneySubmit(ClassInfo classInfo);
}

package com.bookctrlcenter.dao;

import com.bookctrlcenter.entity.Book;
import com.bookctrlcenter.entity.ClassInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookDao  extends  BaseDao<Book> {
    List<Book> listAllFromBookCenter(Book book);
    List<Book> listAllFromSchoolBookCenter(Book book);
    List<Book> listAllFromDemandLibrary(Book book);
    List<Book> listAllFromSBDemandLibrary(Book book);
    List<Book>listAllByAdmin(Book book); //为 二级管理员查找 本院审核的书籍 admin教材审核
    List<Book>listAllByEditor(Book book); //为教师查询可领取的书籍  教师教材领取
    List<Book>listAllByUserID1(Book book);;// editor 我的教材征订
    List<Book>listAllByAdminSB(Book book); //
    List<Book>listAllSB(Book book); //
    List<Book>listAllByEditorSB(Book book); //
    List<Book>listAllByUserID1SB(Book book);;//
    int getStatusForAdmin(Book book);
    int getDataCountFromBookCenter(Book book);
    int getDataCountFromSchoolBookCenter(Book book);
    int getDataCountByEditor(Book book);
    int getDataCountByAdmin(Book book);
    int getDataCountSB(Book book);
    int getDataCountByEditorSB(Book book);
    int getDataCountByAdminSB(Book book);
    int insertSB(Book book);
    int insertToDL(Book book);
    int deleteSB(Book book);
    int insertToSBDL(Book book);
    int insertToSBBC(Book book);
   int  updateSBDLForWarehousing(Book book);
    int IfExitSB(Book book);
    int IfExitRecc(Book book);
    int getStuCount(Book book);
    int updateDL(Book book);
    int updateSBDL(Book book);
    int updateDLForWarehousing(Book book);
    int getDataCountFromDL(Book book);
    int getDataCountFromSBDL(Book book);
    int IfExitSBBC(Book book);
    Integer getDataFromBookCenter(String isbn);
    Integer getDataFromSchoolBookCenter(String isbn);
    int insertToBC(Book book);
    int updateBC(Book book);//发放书后更新BC
    int updateBCForReceBook(Book book);
    int updateForAdmin(Book book);
    int updateForEditor(Book book);
    int updateSB(Book book);//发放书后更新BC
    int updateForAdminSB(Book book);
    int updateForEditorSB(Book book);

    Integer getDataFromDM(String isbn);
    Integer getDataFromSBDM(String isbn);
    int insertSBForAdmin(Book book);
    int  insertSBForSuperAdmin(Book book);

    int updateBCinfo(Book book);
    int updateSBBCinfo(Book book);
    int deleteBC(Book book);
    int deleteSBBC(Book book);


    //退订库存
    int insertToUS(Book book);
    int insertToUSSB(Book book);
    List<Book> listAllFromUS(Book book);
    List<Book> listAllFromUSSB(Book book);
    Integer getDataCountFromUS(Book book);
    Integer getDataCountFromUSSB(Book book);
}

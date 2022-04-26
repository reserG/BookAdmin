package com.bookctrlcenter.Service;

import com.bookctrlcenter.entity.Book;

import java.util.List;

public interface BookSer extends BaseSer<Book>{
    List<Book> listAllFromBookCenter(Book book);
    List<Book> listAllFromSchoolBookCenter(Book book);
    int getDataCountFromSchoolBookCenter(Book book);

    int getDataCountFromBookCenter(Book book);
    List<Book>listAllByAdmin(Book book);
    List<Book>listAllByUserID1(Book book);
    int insertToDL(Book book);
    int insertToBC(Book book);
    int insertToSBBC(Book book);
    int IfExitSB(Book book);
    int insertSBForAdmin(Book book);
    int  insertSBForSuperAdmin(Book book);
    int getStuCount(Book book);
    int getDataCountByAdmin(Book book);
    List<Book>listAllByEditor(Book book);
    int  updateSBDLForWarehousing(Book book);
    int getDataCountByEditor(Book book);
    List<Book> listAllFromDemandLibrary(Book book);
    int getDataCountFromDL(Book book);
    int updateDL(Book book);
    int IfExitSBBC(Book book);
    int updateDLForWarehousing(Book book);
    Integer getDataFromBookCenter(String isbn);
    Integer getDataFromSchoolBookCenter(String isbn);
    int updateBC(Book book);
    int updateBCForReceBook(Book book);
    int updateForAdmin(Book book);
    int updateForEditor(Book book);
    int getStatusForAdmin(Book book);
    List<Book>listAllByAdminSB(Book book); //
    List<Book>listAllSB(Book book); //
    List<Book>listAllByEditorSB(Book book); //
    List<Book>listAllByUserID1SB(Book book);;//
    int getDataCountSB(Book book);
    int getDataCountByEditorSB(Book book);
    int getDataCountByAdminSB(Book book);
    int insertSB(Book book);
    int deleteSB(Book book);
    int IfExitRecc(Book book);
    List<Book> listAllFromSBDemandLibrary(Book book);
    int insertToSBDL(Book book);
    int getDataCountFromSBDL(Book book);
    int updateSBDL(Book book);

    Integer getDataFromDM(String isbn);
    Integer getDataFromSBDM(String isbn);

    int updateSB(Book book);//发放书后更新BC
    int updateForAdminSB(Book book);
    int updateForEditorSB(Book book);


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

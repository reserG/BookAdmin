package com.bookctrlcenter.Service.impl;

import com.bookctrlcenter.Service.BookSer;
import com.bookctrlcenter.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookSerImp")
public class BookSerImp extends BaseSerImp<Book> implements BookSer {

    @Override
    public Integer getDataFromBookCenter(String isbn) {
        return bookDao.getDataFromBookCenter(isbn);
    }

    @Override
    public int getDataCountFromBookCenter(Book book) {
        return bookDao.getDataCountFromBookCenter(book);
    }

    @Override
    public int updateDLForWarehousing(Book book) {
        return bookDao.updateDLForWarehousing(book);
    }


    @Override
    public List<Book> listAllByAdminSB(Book book) {
        return bookDao.listAllByAdminSB(book);
    }

    @Override
    public List<Book> listAllSB(Book book) {
        return bookDao.listAllSB(book);
    }

    @Override
    public List<Book> listAllByEditorSB(Book book) {
        return bookDao.listAllByEditorSB(book);
    }

    @Override
    public List<Book> listAllByUserID1SB(Book book) {
        return bookDao.listAllByUserID1SB(book);
    }

    @Override
    public int getDataCountSB(Book book) {
        return bookDao.getDataCountSB(book);
    }

    @Override
    public int getDataCountByEditorSB(Book book) {
        return bookDao.getDataCountByEditorSB(book);
    }

    @Override
    public List<Book> listAllFromSBDemandLibrary(Book book) {
        return bookDao.listAllFromSBDemandLibrary(book);
    }

    @Override
    public int insertToSBDL(Book book) {
        return bookDao.insertToSBDL(book);
    }

    @Override
    public int IfExitRecc(Book book) {
        return bookDao.IfExitRecc(book);
    }

    @Override
    public int updateSB(Book book) {
        return bookDao.updateSB(book);
    }

    @Override
    public int updateForAdminSB(Book book) {
        return bookDao.updateForAdminSB(book);
    }

    @Override
    public Integer getDataFromSchoolBookCenter(String isbn) {
        return bookDao.getDataFromSchoolBookCenter(isbn);
    }

    @Override
    public int deleteSB(Book book) {
        return bookDao.deleteSB(book);
    }

    @Override
    public int insertSB(Book book) {
        return bookDao.insertSB(book);
    }

    @Override
    public int updateSBDL(Book book) {
        return bookDao.updateSBDL(book);
    }

    @Override
    public int updateBCinfo(Book book) {
        return bookDao.updateBCinfo(book);
    }

    @Override
    public Integer getDataCountFromUS(Book book) {
        return bookDao.getDataCountFromUS(book);
    }

    @Override
    public Integer getDataCountFromUSSB(Book book) {
        return bookDao.getDataCountFromUSSB(book);
    }

    @Override
    public int updateSBBCinfo(Book book) {
        return bookDao.updateSBBCinfo(book);
    }

    @Override
    public int deleteBC(Book book) {
        return bookDao.deleteBC(book);
    }

    @Override
    public int insertToUS(Book book) {
        return bookDao.insertToUS(book);
    }

    @Override
    public int insertToUSSB(Book book) {
        return bookDao.insertToUSSB(book);
    }

    @Override
    public List<Book> listAllFromUS(Book book) {
        return bookDao.listAllFromUS(book);
    }

    @Override
    public List<Book> listAllFromUSSB(Book book) {
        return bookDao.listAllFromUSSB(book);
    }

    @Override
    public Integer getDataFromDM(String isbn) {
        return bookDao.getDataFromDM(isbn);
    }

    @Override
    public Integer getDataFromSBDM(String isbn) {
        return bookDao.getDataFromSBDM(isbn);
    }

    @Override
    public int deleteSBBC(Book book) {
        return bookDao.deleteSBBC(book);
    }

    @Override
    public int IfExitSBBC(Book book) {
        return bookDao.IfExitSBBC(book);
    }

    @Override
    public int updateSBDLForWarehousing(Book book) {
        return bookDao.updateSBDLForWarehousing(book);
    }

    @Override
    public int updateForEditorSB(Book book) {
        return bookDao.updateForEditorSB(book);
    }

    @Override
    public int getDataCountFromSBDL(Book book) {
        return bookDao.getDataCountFromSBDL(book);
    }

    @Override
    public int getDataCountByAdminSB(Book book) {
        return bookDao.getDataCountByAdminSB(book);
    }

    @Override
    public List<Book> listAllFromSchoolBookCenter(Book book) {
        return bookDao.listAllFromSchoolBookCenter(book);
    }

    @Override
    public int insertToSBBC(Book book) {
        return bookDao.insertToSBBC(book);
    }

    @Override
    public int IfExitSB(Book book) {
        return bookDao.IfExitSB(book);
    }

    @Override
    public int insertSBForAdmin(Book book) {
        return bookDao.insertSBForAdmin(book);
    }

    @Override
    public int insertSBForSuperAdmin(Book book) {
        return bookDao.insertSBForSuperAdmin(book);
    }

    @Override
    public int getDataCountFromSchoolBookCenter(Book book) {
        return bookDao.getDataCountFromSchoolBookCenter(book);
    }

    @Override
    public int updateBCForReceBook(Book book) {
        return bookDao.updateBCForReceBook(book);
    }

    @Override
    public int getStuCount(Book book){
        return bookDao.getStuCount(book);
    }

    @Override
    public int updateDL(Book book) {
        return bookDao.updateDL(book);
    }

    @Override
    public int updateBC(Book book) {
        return bookDao.updateBC(book);
    }

    @Override
    public List<Book> listAllByUserID1(Book book) {
        return bookDao.listAllByUserID1(book);
    }

    @Override
    public int getDataCountByEditor(Book book) {
        return bookDao.getDataCountByEditor(book);
    }


    @Override
    public int getDataCountFromDL(Book book) {
        return bookDao.getDataCountFromDL(book);
    }

    @Override
    public int insertToBC(Book book) {
        return bookDao.insertToBC(book);
    }

    @Override
    public List<Book> listAllByEditor(Book book) {
        return bookDao.listAllByEditor(book);
    }

    @Override
    public int getDataCountByAdmin(Book book) {
        return bookDao.getDataCountByAdmin(book);
    }

    @Override
    public int updateForAdmin(Book book) {
        return bookDao.updateForAdmin(book);
    }

    @Override
    public int updateForEditor(Book book) {
        return bookDao.updateForEditor(book);
    }

    @Override
    public List<Book> listAllByAdmin(Book book) {
        return bookDao.listAllByAdmin(book);
    }

    @Override
    public int getStatusForAdmin(Book book) {
        return bookDao.getStatusForAdmin(book);
    }

    @Override
    public List<Book> listAllFromDemandLibrary(Book book) {
        return bookDao.listAllFromDemandLibrary(book);
    }

    @Override
    public int insertToDL(Book book) {
        System.out.println("执行insertTODL");
        return bookDao.insertToDL(book);
    }

    @Override
    public List<Book> listAllFromBookCenter(Book book) {
        return bookDao.listAllFromBookCenter(book);
    }
}

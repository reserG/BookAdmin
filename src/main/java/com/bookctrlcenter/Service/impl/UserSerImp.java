package com.bookctrlcenter.Service.impl;

import com.bookctrlcenter.Service.UserSer;
import com.bookctrlcenter.entity.Book;
import com.bookctrlcenter.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userSerImp")
public class UserSerImp  extends BaseSerImp<User> implements UserSer {
    @Override
    public List<Book> listAllFromUserRece(User user) {
        return userDao.listAllFromUserRece(user);
    }

    @Override
    public int insertForUser(User user) {
        return userDao.insertForUser(user);
    }

    @Override
    public User getUserByUserID(String id) {
        return userDao.getUserByUserID(id);
    }

    @Override
    public int updateByEditor(User user) {
        return userDao.updateByEditor(user);
    }

    @Override
    public int getDataCountByAdmin(User user) {
        return userDao.getDataCountByAdmin(user);
    }

    @Override
    public int getDataCountBySuperAdmin(User user) {
        return userDao.getDataCountBySuperAdmin(user);
    }

    @Override
    public int updatePWByAdmin(User user) {
        return userDao.updatePWByAdmin(user);
    }

    @Override
    public List<Book> listAllFromAdmin(User user) {
        return userDao.listAllFromAdmin(user);
    }

    @Override
    public List<Book> listAllFromSuperAdmin(User user) {
        return userDao.listAllFromSuperAdmin(user);
    }

    @Override
    public int getDataCountBySuperAdminSM(User user) {
        return userDao.getDataCountBySuperAdminSM(user);
    }

    @Override
    public int updateByAdmin(User user) {
        return userDao.updateByAdmin(user);
    }

    @Override
    public List<User> listAllForSM(User user) {
        return userDao.listAllForSM(user);
    }

    @Override
    public int getDataCountForSM(User user) {
        return userDao.getDataCountForSM(user);
    }

    @Override
    public int updateForSM(User user) {
        return userDao.updateForSM(user);
    }

    @Override
    public int updateBySuperAdmin(User user) {
        return userDao.updateBySuperAdmin(user);
    }

    @Override
    public int updatePW(User user) {
        return userDao.updatePW(user);
    }

    @Override
    public int updateUserByAdmin(User user) {
        return userDao.updateUserByAdmin(user);
    }

    @Override
    public List<String> listAllType() {
        return userDao.listAllType();
    }

    @Override
    public List<User> listAllDp() {
        return userDao.listAllDp();
    }

    @Override
    public int getDataCoutOfUser(User user) {
        return userDao.getDataCoutOfUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.login(user);
    }
}

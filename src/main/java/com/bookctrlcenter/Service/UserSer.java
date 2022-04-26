package com.bookctrlcenter.Service;

import com.bookctrlcenter.entity.Book;
import com.bookctrlcenter.entity.User;

import java.util.List;

public interface UserSer extends BaseSer<User> {
    List<Book> listAllFromUserRece(User user);
    User login(User user);
    int insertForUser(User user);
    User getUserByUserID(String id);
    List<Book> listAllFromAdmin(User user);//我的教材提交列表
    List<Book> listAllFromSuperAdmin(User user);//我的教材提交列表
    List<User> listAllForSM(User user);
    int getDataCountForSM(User user);
    int updateForSM(User user);
    int updateByAdmin(User user);
    int updateUserByAdmin(User user);
    int updateByEditor(User user);
    int getDataCoutOfUser(User user);
    int updatePW(User user);
    int getDataCountBySuperAdminSM(User user);// sp领取审核
    List<String> listAllType();
    List<User> listAllDp();
    int updateBySuperAdmin(User user);
    int getDataCountByAdmin(User user);
    int getDataCountBySuperAdmin(User user);
    int updatePWByAdmin(User user);

}

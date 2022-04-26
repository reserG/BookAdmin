
package com.bookctrlcenter.dao;


import com.bookctrlcenter.entity.Book;
import com.bookctrlcenter.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface UserDao extends BaseDao<User>{
//    int insertUser(User user) throws SQLException;
//
//    int deleteUserByUsername(String username) throws SQLException;
//
//    int updateUserBalance(@Param("price") float price, @Param("username") String username) throws SQLException;
//
//    int updateUserPassword(String password, String username) throws SQLException;
//
//    int updateUserContactInformation(User user) throws SQLException;
//
//    User getUserByUsernameAndPassword(String username, String password) throws SQLException;
//
//    User getUserByUsername(@Param("username") String username) throws SQLException;
//
//    List<User> listAllUser() throws SQLException;

    User login(User user);
    int insertForUser(User user);//提交教材领取
    List<Book> listAllFromUserRece(User user);//我的教材提交列表
    List<Book> listAllFromAdmin(User user);//审核
    List<Book> listAllFromSuperAdmin(User user);//审核
    List<User> listAllForSM(User user);//教材结算列表
    int getDataCountByAdmin(User user);
    int getDataCountBySuperAdmin(User user);
    int getDataCountForSM(User user);
    int updateForSM(User user);
    int updateByAdmin(User user);
    int getDataCoutOfUser(User user);
    int updateBySuperAdmin(User user);
    int updateByEditor(User user);
    int updatePW(User user);
    int updateUserByAdmin(User user);
    int getDataCountBySuperAdminSM(User user);
    List<String> listAllType();
    List<User> listAllDp();
    User getUserByUserID(String id);
    int updatePWByAdmin(User user);
}

package com.bookctrlcenter.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookctrlcenter.Service.BookSer;
import com.bookctrlcenter.Service.UserSer;
import com.bookctrlcenter.api.CommonResult;
import com.bookctrlcenter.entity.Book;
import com.bookctrlcenter.entity.User;
import com.bookctrlcenter.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserSer userSerImp;

    @Autowired
    BookSer bookSerImp;

    //教师申请的书
    @RequestMapping(value = "/user/listAllFromUserRece", method = RequestMethod.POST)
    public Object listAllFromUserRece(@RequestBody User user) {
        System.out.println("!!!!!!!!!!!!!!!!");
        List<Book> bookList = userSerImp.listAllFromUserRece(user);
        int count = userSerImp.getDataCount(user);
//        System.out.println("booklist  sizze"+userList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList){
            System.out.println(book1.getUser());
            jsonObject = new JSONObject();
            jsonObject.put("id",book1.getId());
            jsonObject.put("bookName",book1.getBookName());
            jsonObject.put("author",book1.getAuthor());
            jsonObject.put("publisher",book1.getPublisher());
            jsonObject.put("isbn",book1.getIsbn());
            jsonObject.put("time1",book1.getUtil().getTime1());
            jsonObject.put("price",book1.getPrice());
            jsonObject.put("status1",book1.getUtil().getStatus1());
            jsonArray.add(jsonObject);
        }
        response.put("array",jsonArray);
        response.put("count",count);
        response.put("code",200);
        return response;
    }

    //admin审核教材领取表
    @RequestMapping(value = "/user/listAllFromAdmin", method = RequestMethod.POST)
    public Object listAllFromAdmin(@RequestBody User user) {
        System.out.println("!!!!!!!!!!!!listAllFromAdmin!!!!");
        List<Book> bookList = userSerImp.listAllFromAdmin(user);
        int count = userSerImp.getDataCountByAdmin(user);
//        System.out.println("booklist  sizze"+userList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList){
            jsonObject = new JSONObject();
            jsonObject.put("id",book1.getId());
            jsonObject.put("bookName",book1.getBookName());
            jsonObject.put("author",book1.getAuthor());
            jsonObject.put("publisher",book1.getPublisher());
            jsonObject.put("isbn",book1.getIsbn());
            jsonObject.put("time1",book1.getUtil().getTime1());
            jsonObject.put("classSchName",book1.getClassSchedule().getClassName());
            jsonObject.put("price",book1.getPrice());
            if (book1.getUtil().getStatus1() == 1 && book1.getUtil().getUserID2().equals("")){
                jsonObject.put("userName2",userSerImp.getUserByUserID(book1.getUtil().getUserID2()).getUsername());
                jsonObject.put("time2",book1.getUtil().getTime2());
            }
            jsonObject.put("userName1",book1.getUser().getUsername());
            jsonObject.put("status1",book1.getUtil().getStatus1());
            jsonArray.add(jsonObject);
        }
        response.put("array",jsonArray);
        response.put("count",count);
        response.put("code",200);
        return response;
    }

    //superadmin审核
    @RequestMapping(value = "/user/listAllFromSuperAdmin", method = RequestMethod.POST)
    public Object listAllFromSuperAdmin(@RequestBody User user) {
        System.out.println("!!!!!!!listAllFromSuperAdmin!!!!!!!!!");
        List<Book> bookList = userSerImp.listAllFromSuperAdmin(user);
        int count = userSerImp.getDataCountBySuperAdmin(user);
//        System.out.println("booklist  sizze"+userList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList){
            jsonObject = new JSONObject();
            jsonObject.put("id",book1.getId());
            jsonObject.put("bookName",book1.getBookName());
            jsonObject.put("author",book1.getAuthor());
            jsonObject.put("publisher",book1.getPublisher());
            jsonObject.put("department",book1.getUser().getDepartment());
            jsonObject.put("isbn",book1.getIsbn());
            if (book1.getUtil().getStatus2() == 1 && !book1.getUtil().getUserID3().equals("")){
                jsonObject.put("userName3",userSerImp.getUserByUserID(book1.getUtil().getUserID2()).getUsername());
                jsonObject.put("time3",book1.getUtil().getTime3());
            }
            jsonObject.put("time2",book1.getUtil().getTime2());
            jsonObject.put("price",book1.getPrice());
            jsonObject.put("status2",book1.getUtil().getStatus2());
            jsonObject.put("userName2",book1.getUser().getUsername());
            jsonArray.add(jsonObject);
        }
        response.put("array",jsonArray);
        response.put("count",count);
        response.put("code",200);
        return response;
    }

    //教师教材结算列表
    @RequestMapping(value = "/user/bookSM", method = RequestMethod.POST)
    public Object bookSM(@RequestBody User user) {
        System.out.println("/user/bookSM");
        List<User> userList = userSerImp.listAllForSM(user);
        int count = userSerImp.getDataCountBySuperAdminSM(user);
        System.out.println("userList  sizze"+userList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (User user1 : userList){
            jsonObject = new JSONObject();
            jsonObject.put("id",user1.getId());
            jsonObject.put("userID1",user1.getUtil().getUserID1());
            jsonObject.put("userName",user1.getUsername());
            jsonObject.put("bookName",user1.getBook().getBookName());
            jsonObject.put("department",user1.getDepartment());
            jsonObject.put("isbn",user1.getBook().getIsbn());
            if ( user1.getUtil().getUserID4()!=null && !user1.getUtil().getUserID4().equals("")){
                jsonObject.put("userName4",userSerImp.getUserByUserID(user1.getUtil().getUserID4()).getUsername());
                jsonObject.put("time4",user1.getUtil().getTime4());
            }
            jsonObject.put("price",user1.getBook().getPrice());
            jsonObject.put("status3",user1.getUtil().getStatus3());
            jsonObject.put("status2",user1.getUtil().getStatus2());
            jsonArray.add(jsonObject);
        }
        response.put("array",jsonArray);
        response.put("count",count);
        response.put("code",200);
        return response;
    }

    //教师教材结算
    @RequestMapping(value = "/user/updateForSM", method = RequestMethod.POST)
    public Object updateForSM(@RequestBody User user) {
        System.out.println("!!!!!!!listAllFromSuperAdmin!!!!!!!!!");
        userSerImp.updateForSM(user);
        return  CommonResult.success("更新成功");
    }

    @RequestMapping(value = "/user/updateReceBook", method = RequestMethod.POST)
    public Object updateReceBook(@RequestBody User user) {
        if (user.getType().equals("superAdmin")){
            System.out.println("superAdmin");
            if (user.getUtil().getStatus2() == 1 ){ //1标识通过
                bookSerImp.updateBCForReceBook(user.getBook());
            } else if(user.getUtil().getStatus2() == 2){ //2标识驳回
                user.getUtil().setStatus2(0);
                System.out.println("ccount"+user.getBook().getCount());
                bookSerImp.updateBCForReceBook(user.getBook());
            }
            userSerImp.updateBySuperAdmin(user);
        }else if (user.getType().equals("admin")){
            System.out.println("admin");
            userSerImp.updateByAdmin(user);
        }

        return CommonResult.success("更新成功");

    }

    @RequestMapping(value = "/user/insert", method = RequestMethod.POST)
    public Object insert(@RequestBody User user) {
        System.out.println("!!!!!!!!!!!!!!!!");
        user.getBook().setUserID(user.getUtil().getUserID1());
        if (bookSerImp.IfExitRecc(user.getBook())>0){
            return CommonResult.validateFailed("你已经申请过了，不可重复申请！");
        }else {
            userSerImp.insertForUser(user);
            return CommonResult.success(null,"领取申请提交成功!");
        }

    }

    @RequestMapping(value = "/user/insertUser", method = RequestMethod.POST)
    public Object insertUser(@RequestBody User user) {
        System.out.println("!!!!!!!!!!!!!!!!");
        String salt = MD5Util.getRandomString(8);
        String token =MD5Util.getRandomString(8);
        user.setSalt(salt);
        user.setToken(token+user.getUsername());
        user.setPassword(MD5Util.inputPassToDbPass(user.getPassword(),salt));
        userSerImp.insert(user);

        return CommonResult.success("添加成功");
    }

    @RequestMapping(value = "/user/updateUser", method = RequestMethod.POST)
    public Object updateUser(@RequestBody User user) {
        System.out.println("!!!!!!!!!!!!!!!!");
        userSerImp.update(user);
        return CommonResult.success("添加成功");
    }

    @RequestMapping(value = "/user/updatePW", method = RequestMethod.POST)
    public Object updatePW(@RequestBody User user) {
        System.out.println("!!!!!!!!!!!!!!!!");
        User user1 = new User();
        user1 = userSerImp.getUserByUserID(user.getId());
        System.out.println(user1.getPassword());
        System.out.println(MD5Util.inputPassToDbPass(user.getOldPassword(),user1.getSalt()));
        if ( user1.getPassword().equals(MD5Util.inputPassToDbPass(user.getOldPassword(),user1.getSalt()))){
            user.setSalt(MD5Util.getRandomString(8));
            System.out.println(user.getSalt());
            user.setPassword(MD5Util.inputPassToDbPass(user.getPassword(),user.getSalt()));
            userSerImp.updatePW(user);
            return CommonResult.success("1","更新成功");
        }else {
            return CommonResult.success("0","更新失败");
        }

    }

    @RequestMapping(value = "/user/updatePWByAdmin", method = RequestMethod.POST)
    public Object updatePWByAdmin(@RequestBody User user) {
        System.out.println("/user/updatePWByAdmin");
        user.setSalt(MD5Util.getRandomString(8));
        user.setPassword(MD5Util.inputPassToDbPass(user.getPassword(),user.getSalt()));
        userSerImp.updatePWByAdmin(user);
            return CommonResult.success("1","更新成功");


    }

    @RequestMapping(value = "/user/listAllType", method = RequestMethod.POST)
    public Object listAllType() {
        System.out.println("!!!!!!!!!!!!!!!!");
        List<String> typeList = userSerImp.listAllType();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (String s : typeList) {
            jsonObject = new JSONObject();
            jsonObject.put("value", s);
            if(s.equals("admin")){
                jsonObject.put("label", "教务处人员");
            }else if (s.equals("superAdmin")){
                jsonObject.put("label", "教材科人员");
            }else if (s.equals("editor")){
                jsonObject.put("label", "教师");
            }else if (s.equals("money")){
                jsonObject.put("label", "财务处人员");
            }
            jsonArray.add(jsonObject);
        }response.put("array",jsonArray);
        response.put("code",200);
        return response;
    }

    @RequestMapping(value = "/user/listAllDp", method = RequestMethod.POST)
    public Object listAllDp() {
        System.out.println("!!!!!!!!!!!!!!!!");
        List<User> dpList = userSerImp.listAllDp();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (User user : dpList) {
            jsonObject = new JSONObject();
            jsonObject.put("department", user.getDepartment());
            jsonObject.put("departmentID", user.getDepartmentID());
            jsonArray.add(jsonObject);
        }response.put("array",jsonArray);
        response.put("code",200);
        return response;
    }

    @RequestMapping(value = "/user/listAll", method = RequestMethod.POST)
    public Object listAll(@RequestBody User user) {
        System.out.println("!!!!!!!!!!!!!!!!");
        List<User> userList = userSerImp.listAll(user);
        int count = userSerImp.getDataCoutOfUser(user);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (User user1 : userList) {
            jsonObject = new JSONObject();
            jsonObject.put("id", user1.getId());
            jsonObject.put("username", user1.getUsername());
            jsonObject.put("password", user1.getPassword());
            jsonObject.put("gender", user1.getGender());
            jsonObject.put("phone", user1.getPhone());
            jsonObject.put("email", user1.getEmail());
            jsonObject.put("type", user1.getType());
            jsonObject.put("department", user1.getDepartment());
            jsonArray.add(jsonObject);
        }response.put("array",jsonArray);
        response.put("count",count);
        response.put("code",200);
        return response;
    }



    @RequestMapping(value = "/user/updateUserByAdmin", method = RequestMethod.POST)
    public Object updateUserByAdmin(@RequestBody User user) {
        System.out.println("/user/updateUserByAdmin");
        if(userSerImp.updateUserByAdmin(user)==1)
           return CommonResult.success("1","更新成功");
        else
            return CommonResult.success("0","更新失败");
    }

    @RequestMapping(value = "/user/deleteUser", method = RequestMethod.POST)
    public Object deleteUser(@RequestBody User user) {
        if(userSerImp.delete(user)==1)
            return CommonResult.success("1","删除成功");
        else
            return CommonResult.success("0","删除失败");
    }

}

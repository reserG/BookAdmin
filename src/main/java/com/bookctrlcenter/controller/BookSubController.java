package com.bookctrlcenter.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookctrlcenter.RabbTest.SendeMsg;
import com.bookctrlcenter.Service.BookSer;
import com.bookctrlcenter.Service.UserSer;
import com.bookctrlcenter.api.CommonResult;
import com.bookctrlcenter.entity.Book;
import com.bookctrlcenter.entity.MessageX;
//import com.bookctrlcenter.mq.MQSender;
import com.bookctrlcenter.redis.RedisService;
import io.lettuce.core.ScriptOutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookSubController {

    @Autowired
    BookSer bookSerImp;
    @Autowired
    UserSer userSerImp;
    @Autowired
    RedisService redisService;
    @Autowired
    SendeMsg sendeMsg;

    @RequestMapping(value = "/book/insert", method = RequestMethod.POST)
    public Object insert(@RequestBody Book book) {
        System.out.println("!!!!!!!!!!!!!!!!");
        System.out.println(book.getBookName() + "         " + book.getAuthor() + "    " + book.getPrice() + "  " + book.getTime());
        System.out.println(bookSerImp.IfExit(book));
        if (bookSerImp.IfExit(book) >= 1) {
            return CommonResult.validateFailed("该教材已存在！");
        } else {
            bookSerImp.insert(book);
            if (book.getStatus() == 1) {
                bookSerImp.insertToDL(book);
            }
            return CommonResult.success(null);
        }
    }

    @RequestMapping(value = "/book/insertSB", method = RequestMethod.POST)
    public Object insertSB(@RequestBody Book book) {
        System.out.println("!!!!!!!!!!!!!!!!");
        System.out.println(book.getBookName() + "         " + book.getAuthor() + "    " + book.getPrice() + "  " + book.getTime());
        System.out.println(bookSerImp.IfExit(book));
        book.setUserID(book.getUser().getId());
        if (bookSerImp.IfExitSB(book) >= 1) {
            return CommonResult.validateFailed("该教材已存在！");
        } else {
            if (book.getUser().getType().equals("superAdmin")) {
                bookSerImp.insertSBForSuperAdmin(book);
                bookSerImp.insertToSBDL(book);
            } else if (book.getUser().getType().equals("admin")) {
                bookSerImp.insertSBForAdmin(book);
            } else {
                bookSerImp.insertSB(book);
            }
            return CommonResult.success("添加成功");
        }
    }

    @RequestMapping(value = "/book/insertToSBBC", method = RequestMethod.POST)
    public Object insertToSBBC(@RequestBody Book book) {
        System.out.println("!!!!!!!!!!!!!!!!");
        System.out.println(book.getBookName() + "         " + book.getAuthor() + "    " + book.getPrice() + "  " + book.getTime());
        System.out.println(bookSerImp.IfExit(book));
        if (bookSerImp.IfExitSBBC(book) >= 1) {
            return CommonResult.validateFailed();
        } else {
            bookSerImp.insertToSBBC(book);
        }
        return CommonResult.success("添加成功");
    }

    //superadmin使用的方法
    @RequestMapping(value = "/book/listAll", method = RequestMethod.POST)
    public Object listAll(@RequestBody Book book) {
        if (book.getUser() != null)
            System.out.println(book.getUser().getUsername() + "      user " + book.getUser().getSubject());
        List<Book> bookList = bookSerImp.listAll(book);
        int count = bookSerImp.getDataCount(book);
        System.out.println("booklist  sizze" + bookList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            System.out.println(book1.getUser());
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonObject.put("time3", book1.getUtil().getTime3());
            jsonObject.put("time2", book1.getUtil().getTime2());
            jsonObject.put("price", book1.getPrice());
            jsonObject.put("status", book1.getUtil().getStatus2());
            jsonObject.put("userID", book1.getUserID());
            jsonObject.put("department", book1.getUser().getDepartment());
            System.out.println(book1.getTime3() + "3   1" + book1.getTime());
            if (book1.getUtil().getStatus2() != 0 && !book1.getUtil().getUserID3().equals("")) {
                jsonObject.put("userName3", userSerImp.getUserByUserID(book1.getUtil().getUserID3()).getUsername());
            }
            jsonObject.put("userName2", book1.getUser().getUsername());
            jsonObject.put("userSub", book1.getUser().getSubject());
            jsonObject.put("count", book1.getCount());
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }

    @RequestMapping(value = "/book/listAllByadmin", method = RequestMethod.POST)
    public Object listAllByadmin(@RequestBody Book book) {
        System.out.println("/book/listAllByadmin");
        List<Book> bookList = bookSerImp.listAllByAdmin(book);
        int count = bookSerImp.getDataCountByAdmin(book);
        System.out.println("booklist  sizze" + bookList.size());

        for (Book book1 : bookList) {
            if (book1.getUtil().getStatus1() != 1) {
//                System.out.println(book1.getIsbn() +"UID"+ book1.getUtil().getUserID1()+"DP"+book1.getUser().getDepartmentID());
//                redisService.set(book1.getIsbn() +"UID"+ book1.getUtil().getUserID1()+"DP"+book1.getUser().getDepartmentID(), ""+999999999, 60 * 10);
                redisService.set("bkADAD" + book1.getIsbn() + "UID" + book1.getUtil().getUserID1() + "DP" + book1.getUser().getDepartmentID(), "" + 1, 60 * 10);
//            }else {
//                System.out.println(book1.getIsbn() +"UID"+ book1.getUtil().getUserID1()+"DP"+book.getUser().getDepartmentID());
//                redisService.set(book1.getIsbn() +"UID"+ book1.getUtil().getUserID1()+"DP"+book1.getUser().getDepartmentID(), ""+1, 60 * 10);
//            }
            }

        }


        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonObject.put("time1", book1.getUtil().getTime1());
            jsonObject.put("userID1", book1.getUtil().getUserID1());
            jsonObject.put("price", book1.getPrice());
            jsonObject.put("departmentID", book1.getUser().getDepartmentID());
            jsonObject.put("status", book1.getUtil().getStatus1());
            if (book1.getUtil().getStatus1() != 0 && !book1.getUtil().getUserID2().equals("")) {
                jsonObject.put("userName2", userSerImp.getUserByUserID(book1.getUtil().getUserID2()).getUsername());
                jsonObject.put("time2", book1.getUtil().getTime2());
            }
            jsonObject.put("userName1", book1.getUser().getUsername());
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }

    //为教师查询可领取的书籍  教师教材领取
    @RequestMapping(value = "/book/listAllByEditor", method = RequestMethod.POST)
    public Object listAllByEditor(@RequestBody Book book) {
        if (book.getUser() != null)
            System.out.println(book.getUser().getUsername() + "      user " + book.getUser().getSubject() + "   " + book.getUser().getId());
        List<Book> bookList = bookSerImp.listAllByEditor(book);
        int count = bookSerImp.getDataCountByEditor(book);
        System.out.println("count" + count);
        System.out.println("booklist  sizze" + bookList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            System.out.println(book1.getUser());
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            jsonObject.put("userName", book1.getUser().getUsername());
            jsonObject.put("price", book1.getPrice());
            jsonObject.put("className", book1.getClassSchedule().getClassName());
            jsonObject.put("classSchID", book1.getClassSchedule().getClassSchID());
            jsonObject.put("status", book1.getStatus2());
            jsonObject.put("time", book1.getTime3());
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }

    // editor 我的教材征订
    @RequestMapping(value = "/book/listAllByUserID1", method = RequestMethod.POST)
    public Object listAllByUserID1(@RequestBody Book book) {
        System.out.println("/book/listAllByUserID1");
        if (book.getUser() != null)
            System.out.println(book.getUser().getUsername() + "      user " + book.getUser().getSubject() + "   " + book.getUser().getId());
        List<Book> bookList = bookSerImp.listAllByUserID1(book);
        int count = bookSerImp.getDataCountByEditor(book);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            System.out.println(book1.getUser());
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            jsonObject.put("userName", book1.getUser().getUsername());
            jsonObject.put("price", book1.getPrice());
            jsonObject.put("className", book1.getClassSchedule().getClassName());
            jsonObject.put("classSchID", book1.getClassSchedule().getClassSchID());
            jsonObject.put("status", book1.getUtil().getStatus1());
            jsonObject.put("time", book1.getUtil().getTime1());
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }


    //superadmin使用的方法
    @RequestMapping(value = "/book/listAllSB", method = RequestMethod.POST)
    public Object listAllSB(@RequestBody Book book) {
        if (book.getUser() != null)
            System.out.println(book.getUser().getUsername() + "      user " + book.getUser().getSubject());
        List<Book> bookList = bookSerImp.listAllSB(book);
        int count = bookSerImp.getDataCountSB(book);
        System.out.println("booklist  sizze" + bookList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            System.out.println(book1.getUser());
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonObject.put("time3", book1.getUtil().getTime3());
            jsonObject.put("time2", book1.getUtil().getTime2());
            jsonObject.put("price", book1.getPrice());
            jsonObject.put("status", book1.getUtil().getStatus2());
            jsonObject.put("userID", book1.getUserID());
            jsonObject.put("department", book1.getUser().getDepartment());
            System.out.println(book1.getTime3() + "3   1" + book1.getTime());
            if (book1.getUtil().getStatus2() != 0 && !book1.getUtil().getUserID3().equals("")) {
                jsonObject.put("userName3", userSerImp.getUserByUserID(book1.getUtil().getUserID3()).getUsername());
            }
            jsonObject.put("userName2", book1.getUser().getUsername());
            jsonObject.put("userSub", book1.getUser().getSubject());
            jsonObject.put("count", book1.getCount());
//            jsonObject.put("stuCount",bookSerImp.getStuCount(book1.getUserID()));
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }

    @RequestMapping(value = "/book/listAllByadminSB", method = RequestMethod.POST)
    public Object listAllByadminSB(@RequestBody Book book) {
        System.out.println("/book/listAllByadminSB");
        List<Book> bookList = bookSerImp.listAllByAdminSB(book);
        System.out.println(bookList.size());
        int count = bookSerImp.getDataCountByAdminSB(book);
        System.out.println("booklist  sizze" + bookList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            System.out.println(book1.getUser());
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonObject.put("time1", book1.getUtil().getTime1());
            jsonObject.put("price", book1.getPrice());
            jsonObject.put("departmentID", book1.getUser().getDepartmentID());
            jsonObject.put("status", book1.getUtil().getStatus1());
            System.out.println(book1.getUtil().getUserID1() + " " + book1.getUtil().getUserID2());
            if (book1.getUtil().getStatus1() != 0 && !book1.getUtil().getUserID2().equals("")) {
                jsonObject.put("userName2", userSerImp.getUserByUserID(book1.getUtil().getUserID2()).getUsername());
                jsonObject.put("time2", book1.getUtil().getTime2());
            }
            jsonObject.put("userName1", book1.getUser().getUsername());
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }

    @RequestMapping(value = "/book/listAllByEditorSB", method = RequestMethod.POST)
    public Object listAllByEditorSB(@RequestBody Book book) {
        if (book.getUser() != null)
            System.out.println(book.getUser().getUsername() + "      user " + book.getUser().getSubject() + "   " + book.getUser().getId());
        List<Book> bookList = bookSerImp.listAllByEditorSB(book);
        int count = bookSerImp.getDataCountByEditorSB(book);
        System.out.println("count" + count);
        System.out.println("booklist  sizze" + bookList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            System.out.println(book1.getUser());
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            jsonObject.put("userName", book1.getUser().getUsername());
            jsonObject.put("price", book1.getPrice());
            jsonObject.put("className", book1.getClassSchedule().getClassName());
            jsonObject.put("classSchID", book1.getClassSchedule().getClassSchID());
            jsonObject.put("status", book1.getStatus2());
            jsonObject.put("time", book1.getTime3());
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }

    @RequestMapping(value = "/book/listAllByUserID1SB", method = RequestMethod.POST)
    public Object listAllByUserID1SB(@RequestBody Book book) {
        System.out.println("/book/listAllByUserID1SB");
        if (book.getUser() != null)
            System.out.println(book.getUser().getUsername() + "      user " + book.getUser().getSubject() + "   " + book.getUser().getId());
        List<Book> bookList = bookSerImp.listAllByUserID1SB(book);
        int count = bookSerImp.getDataCountByEditorSB(book);
        System.out.println("count " + count);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            jsonObject.put("userName", book1.getUser().getUsername());
            jsonObject.put("price", book1.getPrice());
            jsonObject.put("className", book1.getClassSchedule().getClassName());
            jsonObject.put("classSchID", book1.getClassSchedule().getClassSchID());
            jsonObject.put("status", book1.getUtil().getStatus1());
            jsonObject.put("time", book1.getUtil().getTime1());
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }

    @RequestMapping(value = "/book/listAllfFromBC", method = RequestMethod.POST)
    public Object listAllfFromBC(@RequestBody Book book) {
        List<Book> bookList;
        int count;
        if (book.getIsSB().equals("1")) {
            bookList = bookSerImp.listAllFromSchoolBookCenter(book);
            count = bookSerImp.getDataCountFromSchoolBookCenter(book);
        } else {
            bookList = bookSerImp.listAllFromBookCenter(book);
            count = bookSerImp.getDataCountFromBookCenter(book);
        }
        System.out.println(count);
        System.out.println("booklist  sizze" + bookList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonObject.put("price", book1.getPrice());
            jsonObject.put("count", book1.getCountStock());
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }

    //    test
    @RequestMapping(value = "/book/listAllfFromBC2", method = RequestMethod.GET)
    public Object listAllfFromBC() {
        Book book = new Book();
        book.setIsSB("0");
        List<Book> bookList;
        int count;
        if (book.getIsSB().equals("1")) {
            bookList = bookSerImp.listAllFromSchoolBookCenter(book);
            count = bookSerImp.getDataCountFromSchoolBookCenter(book);
        } else {
            bookList = bookSerImp.listAllFromBookCenter(book);
            count = bookSerImp.getDataCountFromBookCenter(book);
        }
        System.out.println(count);
        System.out.println("booklist  sizze" + bookList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonObject.put("price", book1.getPrice());
            jsonObject.put("count", book1.getCountStock());
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }


    @RequestMapping(value = "/book/listAllfFromSBC", method = RequestMethod.POST)
    public Object listAllfFromSBC(@RequestBody Book book) {
        List<Book> bookList = bookSerImp.listAllFromSchoolBookCenter(book);
        int count = bookSerImp.getDataCountFromSchoolBookCenter(book);
        System.out.println(count);
        System.out.println("booklist  sizze" + bookList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonObject.put("price", book1.getPrice());
            jsonObject.put("count", book1.getCountStock());
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }

    @RequestMapping(value = "/book/listAllfFromDM", method = RequestMethod.POST)  //demandlibrary
    public Object listAllfFromDM(@RequestBody Book book) {
        List<Book> bookList;
        int count;
        if (book.getIsSB().equals("1")) {
            bookList = bookSerImp.listAllFromSBDemandLibrary(book);
            count = bookSerImp.getDataCountFromSBDL(book);
        } else {
            bookList = bookSerImp.listAllFromDemandLibrary(book);
            count = bookSerImp.getDataCountFromDL(book);
        }
        System.out.println("getDataCountFromDL" + count);
        System.out.println("booklist  sizze" + bookList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            if (book.getIsSB().equals("1")) {
                if (book1.getPrinterss() != null) {
                    jsonObject.put("printers", book1.getPrinterss().getPrinters());
                } else {
                    jsonObject.put("printers", "");
                }
            } else {
                if (book1.getSuppliers() != null) {
                    jsonObject.put("supplier", book1.getSuppliers().getSupplier());
                } else {
                    jsonObject.put("supplier", "");
                }
            }
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonObject.put("time", book1.getTime());
            jsonObject.put("warehousing", book1.getWarehousing());
            jsonObject.put("price", book1.getPrice());
            jsonObject.put("count", book1.getCount());
            if (book1.getUser() != null) {
                jsonObject.put("userName", book1.getUser().getUsername());
            }
            int countStock;
            if (book.getIsSB().equals("1")) {
                countStock = bookSerImp.getDataFromSchoolBookCenter(book1.getIsbn()) == null ? 0 : bookSerImp.getDataFromSchoolBookCenter(book1.getIsbn());
            } else {
                countStock = bookSerImp.getDataFromBookCenter(book1.getIsbn()) == null ? 0 : bookSerImp.getDataFromBookCenter(book1.getIsbn());
            }
            jsonObject.put("countStock", countStock);
            int needCount = book1.getCount() - countStock;
            if (needCount > 0) {
                jsonObject.put("needCount", getTen(needCount));
            } else {
                jsonObject.put("needCount", 0);
            }
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }

    @RequestMapping(value = "/book/listAllfFromDMForSettlement", method = RequestMethod.POST)  //demandlibrary
    public Object listAllfFromDMForSettlement(@RequestBody Book book) {
        List<Book> bookList;
        int count;
        if (book.getIsSB().equals("1")) {
            bookList = bookSerImp.listAllFromSBDemandLibrary(book);
            count = bookSerImp.getDataCountFromSBDL(book);
        } else {
            bookList = bookSerImp.listAllFromDemandLibrary(book);
            count = bookSerImp.getDataCountFromDL(book);
        }
        System.out.println("getDataCountFromDL" + count);
        System.out.println("booklist  sizze" + bookList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonObject.put("time", book1.getTime());
            jsonObject.put("warehousing", book1.getWarehousing());
            jsonObject.put("price", book1.getPrice());
            jsonObject.put("count", book1.getCount());
            if (book1.getUser() != null) {
                jsonObject.put("userName", book1.getUser().getUsername());
            }
            int countStock;
            if (book.getIsSB().equals("1")) {
                countStock = bookSerImp.getDataFromSchoolBookCenter(book1.getIsbn()) == null ? 0 : bookSerImp.getDataFromSchoolBookCenter(book1.getIsbn());
            } else {
                countStock = bookSerImp.getDataFromBookCenter(book1.getIsbn()) == null ? 0 : bookSerImp.getDataFromBookCenter(book1.getIsbn());
            }
            jsonObject.put("countStock", countStock);
            int needCount = book1.getCount() - countStock;
            if (needCount > 0) {
                jsonObject.put("needCount", getTen(needCount));
            } else {
                jsonObject.put("needCount", 0);
            }
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }

    public int getTen(int a) {
        int q = a % 10;
        if (q == 0) {
            return a;
        } else {
            return 10 - q + a;
        }
    }

    @RequestMapping(value = "/book/delete", method = RequestMethod.POST)
    public Object delete(@RequestBody Book book) {
        System.out.println(book.getId());
        bookSerImp.delete(book);
        System.out.println(book.getId() + "  delete " + book.getRecount());
        return CommonResult.success("删除成功");
    }

    @RequestMapping(value = "/book/deleteSB", method = RequestMethod.POST)
    public Object deleteSB(@RequestBody Book book) {
        System.out.println(book.getId());
        bookSerImp.deleteSB(book);
        System.out.println(book.getId() + "  delete " + book.getRecount());
        return CommonResult.success("删除成功");
    }

    @RequestMapping(value = "/book/update", method = RequestMethod.POST)
    public Object update(@RequestBody Book book) {
        if (book.getUser().getType().equals("superAdmin")) {
            System.out.println("superAdmin");
            book.setRecount(book.getRecount() - book.getCount());
            if (book.getUtil().getStatus2() == 1) { //1标识通过
                int countStock = 0;
                book.getUtil().setStatus1(1);
                countStock = bookSerImp.getDataFromBookCenter(book.getIsbn()) == null ? 0 : bookSerImp.getDataFromBookCenter(book.getIsbn());
                int needCount = 0;
                if (bookSerImp.getDataFromDM(book.getIsbn()) != null) {
                    needCount = bookSerImp.getDataFromDM(book.getIsbn());
                }
                int count = needCount + book.getCount() - countStock;
                System.out.println("count " + count + " needCount " +needCount + " countStock " + countStock);
                if (count > 0) {
                    book.setCount(count);
                    bookSerImp.insertToDL(book);
                } else {
                    book.setWarehousing(2);
                    book.setCount(0);
                    System.out.println("count<0 "+book.toString() );
                    bookSerImp.insertToDL(book);
                }
            } else if (book.getUtil().getStatus2() == 2) { //2标识驳回
                book.getUtil().setStatus1(0);
                book.getUtil().setStatus2(2);
                System.out.println("驳回操作");
//                bookSerImp.updateDL(book);
            } else if (book.getStatus() == 3) {
                book.getUtil().setStatus2(0);
                System.out.println("驳回操作");
                System.out.println(book.getStatus());
                System.out.println(book.getId());
                bookSerImp.updateDL(book);
            }
            bookSerImp.update(book);
        } else if (book.getUser().getType().equals("admin")) {
//            book1.getIsbn() +"UID"+ book1.getUtil().getUserID1()+"DP"+book1.getDepartmentID(),
            String key = "bkADAD" + book.getIsbn() + "UID" + book.getUtil().getUserID1() + "DP" + book.getDepartmentID();
            System.out.println("key     " + key);
            long stock = redisService.decr(key);//10
            if (stock < 0) {
                return CommonResult.success("0", "数据过期，2秒后自动刷新页面!!");
            }
            MessageX message = new MessageX();
            message.setUtil(book.getUtil());
            message.setKey(key);
            sendeMsg.sendMsg(message);
            System.out.println("开始while判定是否成功");
            while(true){
                System.out.println("while判定中");
                if (bookSerImp.getStatusForAdmin(book)==1)
                    break;
            }
            System.out.println("while判定成功");
            return CommonResult.success("1", "更新成功，2秒后自动刷新页面!!");

        } else {
            System.out.println("ed");

            bookSerImp.updateForEditor(book);
        }

        return CommonResult.success("更新成功");

    }

    @RequestMapping(value = "/book/updateSB", method = RequestMethod.POST)
    public Object updateSB(@RequestBody Book book) {
        System.out.println(book.getId());
        System.out.println("更新操作  " + book.getStatus() + "   " + book.getUser().getType());
        if (book.getUser().getType().equals("superAdmin")) {
            System.out.println("superAdmin");
            book.setRecount(book.getRecount() - book.getCount());
            if (book.getUtil().getStatus2() == 1) { //1标识通过
                int countStock = 0;
                countStock = bookSerImp.getDataFromSchoolBookCenter(book.getIsbn()) == null ? 0 : bookSerImp.getDataFromSchoolBookCenter(book.getIsbn());
                int needCount = 0;
                if (bookSerImp.getDataFromDM(book.getIsbn()) != null) {
                    needCount = bookSerImp.getDataFromDM(book.getIsbn());
                }
                int count = needCount + book.getCount() - countStock;
                if (count > 0) {
                    book.setCount(count);
                    bookSerImp.insertToSBDL(book);
                } else {
                    book.setWarehousing(2);
                    book.setCount(0);
                    bookSerImp.insertToSBDL(book);
                }
            } else if (book.getUtil().getStatus2() == 2) { //2标识驳回
                book.getUtil().setStatus2(2);
                System.out.println("驳回操作");
//                bookSerImp.updateSBDL(book);
            } else if (book.getStatus() == 3) {
                book.getUtil().setStatus2(0);
                System.out.println("驳回操作");
                System.out.println(book.getStatus());
                System.out.println(book.getId());
                bookSerImp.updateSBDL(book);
            }
            bookSerImp.updateSB(book);
        } else if (book.getUser().getType().equals("admin")) {
            System.out.println("admin");
            book.setCount(bookSerImp.getStuCount(book));
            System.out.println("count    " + book.getStuCount());
            bookSerImp.updateForAdminSB(book);
        } else {
            System.out.println("ed");

            bookSerImp.updateForEditorSB(book);
        }

        return CommonResult.success("更新成功");

    }

    @RequestMapping(value = "/book/searchBook", method = RequestMethod.POST)
    public Object searchBook(@RequestBody Book book) {
        List<Book> bookList = bookSerImp.listAll(book);
        int count = bookSerImp.getDataCount(book);
        System.out.println("booklist  sizze" + bookList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonObject.put("price", book1.getPrice());
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }


    @RequestMapping(value = "/book/deleteBC", method = RequestMethod.POST)
    public Object deleteBC(@RequestBody Book book) {
        System.out.println(book.getId());
        bookSerImp.deleteBC(book);
        System.out.println(book.getId() + "  delete " + book.getRecount());
        return CommonResult.success("1", "删除成功");
    }

    @RequestMapping(value = "/book/deleteSBBC", method = RequestMethod.POST)
    public Object deleteSBBC(@RequestBody Book book) {
        System.out.println(book.getId());
        bookSerImp.deleteSBBC(book);
        System.out.println(book.getId() + "  delete " + book.getRecount());
        return CommonResult.success("1", "删除成功");
    }

    @RequestMapping(value = "/book/updateSBBCinfo", method = RequestMethod.POST)
    public Object updateSBBCinfo(@RequestBody Book book) {
        System.out.println(book.getId());
        bookSerImp.updateSBBCinfo(book);
        System.out.println(book.getId() + "  delete " + book.getRecount());
        return CommonResult.success("1", "更新成功");
    }

    @RequestMapping(value = "/book/updateBCinfo", method = RequestMethod.POST)
    public Object updateBCinfo(@RequestBody Book book) {
        System.out.println(book.getId());
        bookSerImp.updateBCinfo(book);

        System.out.println(book.getId() + "  delete " + book.getRecount());
        return CommonResult.success("1", "更新成功");
    }

    @RequestMapping(value = "/book/insertToUS", method = RequestMethod.POST)
    public Object insertToUS(@RequestBody Book book) {
        System.out.println(book.getId());
        System.out.println(book.getCount());
        if (book.getIsSB().equals("1")) {
            bookSerImp.insertToUSSB(book);
        } else {
            bookSerImp.insertToUS(book);
        }
        return CommonResult.success("1", "提交成功");
    }


    @RequestMapping(value = "/book/listAllfFromUS", method = RequestMethod.POST)  //demandlibrary
    public Object listAllfFromUS(@RequestBody Book book) {
        List<Book> bookList;
        int count;
        if (book.getIsSB().equals("1")) {
            bookList = bookSerImp.listAllFromUSSB(book);
            Integer countx = bookSerImp.getDataCountFromUSSB(book);
            System.out.println(countx);

            if (countx == null) {
                count = 0;
            } else {
                count = countx;
            }
        } else {
            bookList = bookSerImp.listAllFromUS(book);
            Integer countx = bookSerImp.getDataCountFromUS(book);
            System.out.println(countx);
            if (countx == null) {
                count = 0;
            } else {
                count = countx;
            }
        }
        System.out.println("getDataCountFromDL" + count);
        System.out.println("booklist  sizze" + bookList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList) {
            jsonObject = new JSONObject();
            jsonObject.put("id", book1.getId());
            jsonObject.put("bookName", book1.getBookName());
            jsonObject.put("author", book1.getAuthor());
            if (book.getIsSB().equals("1")) {
                jsonObject.put("printers", book1.getPrinterss().getPrinters());
            } else {
                jsonObject.put("supplier", book1.getSuppliers().getSupplier());
            }
            jsonObject.put("publisher", book1.getPublisher());
            jsonObject.put("isbn", book1.getIsbn());
            jsonObject.put("time", book1.getTime());
            jsonObject.put("price", book1.getPrice());
            jsonObject.put("count", book1.getCount());
            if (book1.getUser() != null) {
                jsonObject.put("userName", book1.getUser().getUsername());
            }
            jsonArray.add(jsonObject);
        }
        response.put("array", jsonArray);
        response.put("count", count);
        response.put("code", 200);
        return response;
    }

}

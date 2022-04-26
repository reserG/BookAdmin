package com.bookctrlcenter.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookctrlcenter.Service.BookSer;
import com.bookctrlcenter.Service.ClassSer;
import com.bookctrlcenter.Service.PrintersSer;
import com.bookctrlcenter.Service.SupplierSer;
import com.bookctrlcenter.api.CommonResult;
import com.bookctrlcenter.entity.Book;
import com.bookctrlcenter.entity.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class BookStockController {
    @Autowired
    BookSer bookSerImp;

    @Autowired
    ClassSer classSerImp;

    @Autowired
    SupplierSer supplierSerImp;
    @Autowired
    PrintersSer printersSerImp;

    @Transactional
    @RequestMapping(value = "/bookStock/insert", method = RequestMethod.POST)
    public Object insert(@RequestBody Book book) {
        System.out.println("/bookStock/insert");
        System.out.println(book.getBookName()+"         "+book.getAuthor()+"    "+book.getPrice());
        System.out.println(bookSerImp.IfExit(book));
        if (book.getIsSB().equals("1")){
            bookSerImp.insertToSBBC(book);
            if (book.getCount() == book.getNeedCount()){
                book.setWarehousing(1);
                bookSerImp.updateSBDLForWarehousing(book);
            }else {
               book.setCount(book.getNeedCount() - book.getCount());
               book.setWarehousing(0);
                bookSerImp.updateSBDLForWarehousing(book);
            }
            book.getPrinterss().setTotalPrice(book.getCount() * book.getPrice());
            printersSerImp.insert(book.getPrinterss());

        }else {
            bookSerImp.insertToBC(book);
            if (book.getCount() == book.getNeedCount()){
                book.setWarehousing(1);
                bookSerImp.updateDLForWarehousing(book);
            }else {
//                book.setCount(book.getNeedCount() - book.getCount());
                book.setWarehousing(0);
                bookSerImp.updateDLForWarehousing(book);
            }
            book.getSuppliers().setTotalPrice(book.getCount() * book.getPrice());
            supplierSerImp.insert(book.getSuppliers());
        }

//        publisher.setTotalPrice(publisherSerImp.getPriceFromDMForSM(publisher));
//        publisherSerImp.update(publisher);
        return CommonResult.success("添加成功");
        }

        //发放教材后更新库存数量
    @RequestMapping(value = "/bookStock/updateBC", method = RequestMethod.POST)
    public Object updateBC(@RequestBody JSONArray bookJSON) {
        System.out.println("!!!!!!!!!!!!!!!!");
        System.out.println(bookJSON.size());
        JSONObject jsonObject = bookJSON.getJSONObject(0);
        ClassInfo classInfo = new ClassInfo();
        classInfo.setRealCount(Integer.parseInt(""+jsonObject.get("count")));
        System.out.println(classInfo.getRealCount()+" relcount");
        classInfo.setClassID((Integer)jsonObject.get("classID"));
        classInfo.setIssuing((Integer)jsonObject.get("issuing"));
        classInfo.setIssuingTime((String)jsonObject.get("time"));
        classInfo.setPrice(classSerImp.getTotalPrice(classInfo.getClassID()));
        classInfo.setTotalPrice(classInfo.getPrice()*classInfo.getRealCount());
        classInfo.setIssuingUserID(""+jsonObject.get("userID"));
        classSerImp.update(classInfo);
        System.out.println("userID" +  jsonObject.get("userID"));
        System.out.println("classID" +  jsonObject.get("classID"));
        System.out.println("issuing" +  jsonObject.get("issuing"));
        List<Map<String,Object>> bookList = (List) bookJSON.get(1);
        Iterator iterator = bookList.iterator();
        int i=0;
        Book book;
        while(iterator.hasNext()){
            Map<String,Object> map = (Map) iterator.next();
            System.out.println(map.get("status"));
            if ((Integer)map.get("status") == 1){
                book = new Book();
                book.setIsbn((String)map.get("isbn"));
                book.setCount(classInfo.getRealCount());
                bookSerImp.updateBC(book);
            }

            System.out.println("-----------------");
        }

        return CommonResult.success("添加成功");
    }

}

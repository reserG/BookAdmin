package com.bookctrlcenter.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookctrlcenter.Service.ClassSer;
import com.bookctrlcenter.api.CommonResult;
import com.bookctrlcenter.entity.Book;
import com.bookctrlcenter.entity.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClassController {

    @Autowired
    ClassSer classSerImp;

    @RequestMapping(value = "/class/listAllOG", method = RequestMethod.POST)
    public Object listAllForOutGo(@RequestBody ClassInfo classInfo) {

        List<ClassInfo> bookList = classSerImp.listAll(classInfo);
        int count = classSerImp.getDataCount(classInfo);
        System.out.println("booklist  sizze"+bookList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (ClassInfo classInfo1 : bookList){
            jsonObject = new JSONObject();
            jsonObject.put("classID",classInfo1.getClassID());
            jsonObject.put("departments",classInfo1.getDepartments());
            jsonObject.put("stuCount",classInfo1.getStuCount());
            jsonObject.put("userName",classInfo1.getUsername());
            jsonObject.put("time",classInfo1.getIssuingTime());
            jsonObject.put("issuing",classInfo1.getIssuing());
            jsonArray.add(jsonObject);
        }
        response.put("array",jsonArray);
        response.put("count",count);
        response.put("code",200);
        return response;
    }

    @RequestMapping(value = "/class/listAllSM", method = RequestMethod.POST)
    public Object listAllForSettleMent(@RequestBody ClassInfo classInfo) {

        List<ClassInfo> bookList = classSerImp.listAllBySMUserID(classInfo);
        int count = classSerImp.getDataCount(classInfo);
        System.out.println("booklist  sizze"+bookList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (ClassInfo classInfo1 : bookList){
            jsonObject = new JSONObject();
            jsonObject.put("classID",classInfo1.getClassID());
            jsonObject.put("departments",classInfo1.getDepartments());
            jsonObject.put("totalPrice",classInfo1.getTotalPrice());
            jsonObject.put("stuCount",classInfo1.getStuCount());
            jsonObject.put("realCount",classInfo1.getRealCount());
            jsonObject.put("userName",classInfo1.getUsername());
            jsonObject.put("price",classInfo1.getPrice());
            jsonObject.put("bmStatus",classInfo1.getBmStatus());
            jsonObject.put("mStatus",classInfo1.getmStatus());
            jsonObject.put("money",classInfo1.getMoney());
            jsonObject.put("bookMoney",classInfo1.getBookMoney());
            jsonObject.put("time",classInfo1.getSettlementTime());
            jsonObject.put("issuing",classInfo1.getIssuing());
            jsonObject.put("settlement",classInfo1.getSettlement());
            jsonArray.add(jsonObject);
        }
        response.put("array",jsonArray);
        response.put("count",count);
        response.put("code",200);
        return response;
    }


    @RequestMapping(value = "/class/smCost", method = RequestMethod.POST)
    public Object smCost(@RequestBody ClassInfo classInfo) {
        System.out.println(classInfo.getMoney() + "montey");
        classSerImp.update(classInfo);
        return CommonResult.success("成功");
    }

    @RequestMapping(value = "/class/moneySubmit", method = RequestMethod.POST)
    public Object moneySubmit(@RequestBody ClassInfo classInfo) {
        System.out.println(classInfo.getMoney() + "montey");
        classSerImp.moneySubmit(classInfo);
        return CommonResult.success("成功");
    }

    @RequestMapping(value = "/class/searchBookForClass", method = RequestMethod.POST)
    public Object searchBookForClass(@RequestBody ClassInfo classInfo) {
        System.out.println("!!!!!!!!!!!!!!!!");
        System.out.println(classInfo.getClassID());
        int count =0;
        List<Book> bookList = classSerImp.searchBookForClass(classInfo);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (Book book1 : bookList){
            jsonObject = new JSONObject();
            if (book1.getCountStock() < book1.getStuCount()){
                jsonObject.put("status",0); //status 是书籍 库存数量是否大于所需数量的标识 1 大于 0小于
                count++;
            }else{
                jsonObject.put("status",1);
            }
            jsonObject.put("bookName",book1.getBookName());
            jsonObject.put("isbn",book1.getIsbn());
            jsonObject.put("countStock",book1.getCountStock());
            jsonObject.put("stuCount",book1.getStuCount());
            jsonObject.put("classID",book1.getClassID());
            jsonArray.add(jsonObject);
        }
        if (count >0){
            response.put("issuing",2);
        }else {
            response.put("issuing",1);
        }
        response.put("array",jsonArray);
        response.put("code",200);

        return response;
    }
}

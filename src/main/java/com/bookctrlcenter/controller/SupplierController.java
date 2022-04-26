package com.bookctrlcenter.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookctrlcenter.Service.SupplierSer;
import com.bookctrlcenter.Service.UserSer;
import com.bookctrlcenter.api.CommonResult;
import com.bookctrlcenter.entity.Suppliers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplierController {

    @Autowired
    SupplierSer supplierSerImp;
    @Autowired
    UserSer userSerImp;

    @RequestMapping(value = "/pub/listAll", method = RequestMethod.POST)
    public Object listAll(@RequestBody Suppliers supplier) {

        List<Suppliers> publisherList = supplierSerImp.listAll(supplier);
        int count = supplierSerImp.getDataCount(supplier);
        System.out.println("booklist  sizze"+publisherList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (Suppliers publisher1 : publisherList){
            jsonObject = new JSONObject();
            jsonObject.put("id",publisher1.getId());
            jsonObject.put("supplier",publisher1.getSupplier());
            jsonObject.put("phone",publisher1.getPhone());
            jsonObject.put("totalPrice",publisher1.getTotalPrice());
            jsonObject.put("status1",publisher1.getUtil().getStatus1());
            jsonObject.put("status2",publisher1.getUtil().getStatus2());
            jsonObject.put("time1",publisher1.getUtil().getTime1());
            jsonObject.put("userName1",publisher1.getUtil().getUserName1());
            jsonObject.put("status3",publisher1.getUtil().getStatus3());
            jsonArray.add(jsonObject);
        }
        response.put("array",jsonArray);
        response.put("count",count);
        response.put("code",200);
        return response;
    }

    @RequestMapping(value = "/pub/listAllSUP", method = RequestMethod.POST)
    public Object listAllSUP(@RequestBody Suppliers supplier) {

        List<Suppliers> publisherList = supplierSerImp.listAllSUP(supplier);
        int count = supplierSerImp.getDataCountSUP(supplier);
        System.out.println("booklist  sizze"+publisherList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (Suppliers publisher1 : publisherList){
            jsonObject = new JSONObject();
            jsonObject.put("id",publisher1.getId());
            jsonObject.put("supplier",publisher1.getSupplier());
            jsonObject.put("phone",publisher1.getPhone());
            jsonArray.add(jsonObject);
        }
        response.put("array",jsonArray);
        response.put("count",count);
        response.put("code",200);
        return response;
    }

    @RequestMapping(value = "/pub/insert", method = RequestMethod.POST)
    public Object insert(@RequestBody Suppliers supplier) {

        supplierSerImp.insertSUP(supplier);
        return CommonResult.success("1","添加成功");
    }

    @RequestMapping(value = "/pub/listAllM", method = RequestMethod.POST)
    public Object listAllM(@RequestBody Suppliers supplier) {

        List<Suppliers> suppliersList = supplierSerImp.listAllM(supplier);
        int count = supplierSerImp.getDataCountM(supplier);
        System.out.println("booklist  sizze"+suppliersList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (Suppliers suppliers : suppliersList){
            jsonObject = new JSONObject();
            jsonObject.put("id",suppliers.getId());
            jsonObject.put("supplier",suppliers.getSupplier());
            jsonObject.put("phone",suppliers.getPhone());
            jsonObject.put("totalPrice",suppliers.getTotalPrice());
            jsonObject.put("status1",suppliers.getUtil().getStatus1());
            jsonObject.put("time1",suppliers.getUtil().getTime1());
            jsonObject.put("userName1",suppliers.getUtil().getUserName1());
            jsonObject.put("status2",suppliers.getUtil().getStatus2());
            jsonObject.put("status3",suppliers.getUtil().getStatus3());
            jsonObject.put("time2",suppliers.getUtil().getTime2());
            if (suppliers.getUtil().getUserID2()!=null)
                jsonObject.put("userName2",userSerImp.getUserByUserID(suppliers.getUtil().getUserID2()).getUsername());
            jsonArray.add(jsonObject);
        }
        response.put("array",jsonArray);
        response.put("count",count);
        response.put("code",200);
        return response;
    }

    @RequestMapping(value = "/pub/update", method = RequestMethod.POST)
    public Object update(@RequestBody Suppliers supplier) {

        supplierSerImp.updateForSuperAdmin(supplier);
        return CommonResult.success("更新成功");
    }

    @RequestMapping(value = "/pub/updateForM", method = RequestMethod.POST)
    public Object updateForM(@RequestBody Suppliers publisher) {
        if ( publisher.getUtil().getStatus3() == 0 ){
            System.out.println("3 = 0 "+ publisher.getUtil().getStatus3());
            supplierSerImp.updateForMoney(publisher);
        }else {
            System.out.println(publisher.getUtil().getStatus3() + "        ");
            publisher.getUtil().setStatus3(0);
            supplierSerImp.updateForMoneyR(publisher);
        }
        return CommonResult.success("更新成功");
    }


}

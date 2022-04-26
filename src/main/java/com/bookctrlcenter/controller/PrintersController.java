package com.bookctrlcenter.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookctrlcenter.Service.PrintersSer;
import com.bookctrlcenter.Service.UserSer;
import com.bookctrlcenter.api.CommonResult;
import com.bookctrlcenter.entity.Printerss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrintersController {

    @Autowired
    PrintersSer printersSerImp;
    @Autowired
    UserSer userSerImp;

    @RequestMapping(value = "/pri/listAll", method = RequestMethod.POST)
    public Object listAll(@RequestBody Printerss printers) {

        List<Printerss> publisherList = printersSerImp.listAll(printers);
        int count = printersSerImp.getDataCount(printers);
        System.out.println("booklist  sizze"+publisherList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (Printerss printers1 : publisherList){
            jsonObject = new JSONObject();
            jsonObject.put("id",printers1.getId());
            jsonObject.put("printers",printers1.getPrinters());
            jsonObject.put("phone",printers1.getPhone());
            jsonObject.put("totalPrice",printers1.getTotalPrice());
            jsonObject.put("status1",printers1.getUtil().getStatus1());
            jsonObject.put("time1",printers1.getUtil().getTime1());
            jsonObject.put("userName1",printers1.getUtil().getUserName1());
            jsonObject.put("status3",printers1.getUtil().getStatus3());
            jsonArray.add(jsonObject);
        }
        response.put("array",jsonArray);
        response.put("count",count);
        response.put("code",200);
        return response;
    }

    @RequestMapping(value = "/pri/listAllPri", method = RequestMethod.POST)
    public Object listAllPri(@RequestBody Printerss printers) {

        List<Printerss> publisherList = printersSerImp.listAllSUP(printers);
        int count = printersSerImp.getDataCountSUP(printers);
        System.out.println("booklist  sizze"+publisherList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (Printerss printers1 : publisherList){
            jsonObject = new JSONObject();
            jsonObject.put("id",printers1.getId());
            jsonObject.put("printers",printers1.getPrinters());
            jsonObject.put("phone",printers1.getPhone());
            jsonArray.add(jsonObject);
        }
        response.put("array",jsonArray);
        response.put("count",count);
        response.put("code",200);
        return response;
    }

    @RequestMapping(value = "/pri/insert", method = RequestMethod.POST)
    public Object insert(@RequestBody Printerss printers) {

        printersSerImp.insertSUP(printers);
        return CommonResult.success("1","添加成功");
    }

    @RequestMapping(value = "/pri/listAllM", method = RequestMethod.POST)
    public Object listAllM(@RequestBody Printerss printers) {

        List<Printerss> printerssList = printersSerImp.listAllM(printers);
        int count = printersSerImp.getDataCountM(printers);
        System.out.println("booklist  sizze"+printerssList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (Printerss printers1 : printerssList){
            jsonObject = new JSONObject();
            jsonObject.put("id",printers1.getId());
            jsonObject.put("printers",printers1.getPrinters());
            jsonObject.put("phone",printers1.getPhone());
            jsonObject.put("totalPrice",printers1.getTotalPrice());
            jsonObject.put("status1",printers1.getUtil().getStatus1());
            jsonObject.put("time1",printers1.getUtil().getTime1());
            jsonObject.put("userName1",printers1.getUtil().getUserName1());
            jsonObject.put("status2",printers1.getUtil().getStatus2());
            jsonObject.put("status3",printers1.getUtil().getStatus3());
            jsonObject.put("time2",printers1.getUtil().getTime2());
            if (printers1.getUtil().getUserID2()!=null)
                jsonObject.put("userName2",userSerImp.getUserByUserID(printers1.getUtil().getUserID2()).getUsername());
            jsonArray.add(jsonObject);
        }
        response.put("array",jsonArray);
        response.put("count",count);
        response.put("code",200);
        return response;
    }

    @RequestMapping(value = "/pri/update", method = RequestMethod.POST)
    public Object update(@RequestBody Printerss printers) {

        printersSerImp.updateForSuperAdmin(printers);
        return CommonResult.success("更新成功");
    }

    @RequestMapping(value = "/pri/updateForM", method = RequestMethod.POST)
    public Object updateForM(@RequestBody Printerss printers) {
        if ( printers.getUtil().getStatus3() == 0 ){
            System.out.println("3 = 0 "+ printers.getUtil().getStatus3());
            printersSerImp.updateForMoney(printers);
        }else {
            System.out.println(printers.getUtil().getStatus3() + "        ");
            printers.getUtil().setStatus3(0);
            printersSerImp.updateForMoneyR(printers);
        }
        return CommonResult.success("更新成功");
    }


}

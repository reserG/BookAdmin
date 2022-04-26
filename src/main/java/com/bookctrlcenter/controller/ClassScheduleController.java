package com.bookctrlcenter.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bookctrlcenter.Service.ClassSdSer;
import com.bookctrlcenter.Service.ClassSer;
import com.bookctrlcenter.entity.ClassInfo;
import com.bookctrlcenter.entity.ClassSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClassScheduleController {
    @Autowired
    ClassSdSer classSderImp;

    @RequestMapping(value = "/classSd/listAll", method = RequestMethod.POST)
    public Object listAll(@RequestBody ClassSchedule classSchedule) {

        List<ClassSchedule> classScheduleList = classSderImp.listAll(classSchedule);
        System.out.println("booklist  sizze"+classScheduleList.size());
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject ;
        JSONObject response = new JSONObject();
        for (ClassSchedule classInfo1 : classScheduleList){
            jsonObject = new JSONObject();
            jsonObject.put("classSchID",classInfo1.getClassSchID());
            jsonObject.put("className",classInfo1.getClassName());
            jsonObject.put("classID",classInfo1.getClassID());
            jsonArray.add(jsonObject);
        }
        response.put("array",jsonArray);
        response.put("code",200);
        return response;
    }
}

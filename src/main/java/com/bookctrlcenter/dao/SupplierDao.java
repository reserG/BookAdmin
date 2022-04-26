package com.bookctrlcenter.dao;

import com.bookctrlcenter.entity.Suppliers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SupplierDao extends BaseDao<Suppliers>{
    int getPriceFromDMForSM (Suppliers publisher);
    int updateForSuperAdmin (Suppliers publisher);
    int updateForMoney (Suppliers publisher);
    int updateForMoneyR (Suppliers publisher);
    List<Suppliers> listAllSUP(Suppliers publisher);
    int getDataCountSUP (Suppliers publisher);
int insertSUP(Suppliers supplier);
    List<Suppliers> listAllM(Suppliers publisher);
    int getDataCountM (Suppliers publisher);
}

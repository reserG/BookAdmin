package com.bookctrlcenter.Service;

import com.bookctrlcenter.entity.Suppliers;

import java.util.List;

public interface SupplierSer extends BaseSer<Suppliers>  {
    int getPriceFromDMForSM (Suppliers publisher);
    int updateForSuperAdmin (Suppliers publisher);
    int updateForMoney (Suppliers publisher);
    List<Suppliers> listAllM(Suppliers publisher);
    int getDataCountM (Suppliers publisher);
    int updateForMoneyR (Suppliers publisher);
    List<Suppliers> listAllSUP(Suppliers publisher);
    int getDataCountSUP (Suppliers publisher);
    int insertSUP(Suppliers supplier);
}

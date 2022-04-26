package com.bookctrlcenter.Service.impl;

import com.bookctrlcenter.Service.SupplierSer;
import com.bookctrlcenter.entity.Suppliers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("publisherSerImp")
public class SupplierSerImp extends BaseSerImp<Suppliers> implements SupplierSer {
    @Override
    public List<Suppliers> listAllM(Suppliers supplier) {
        return supplierDao.listAllM(supplier);
    }

    @Override
    public int getDataCountM(Suppliers supplier) {
        return supplierDao.getDataCountM(supplier);
    }

    @Override
    public List<Suppliers> listAllSUP(Suppliers supplier) {
        return supplierDao.listAllSUP(supplier);
    }

    @Override
    public int getDataCountSUP(Suppliers supplier) {
        return supplierDao.getDataCountSUP(supplier);
    }

    @Override
    public int insertSUP(Suppliers supplier) {
        return supplierDao.insertSUP(supplier);
    }

    @Override
    public int updateForMoneyR(Suppliers supplier) {
        return supplierDao.updateForMoneyR(supplier);
    }

    @Override
    public int updateForMoney(Suppliers supplier) {
        return supplierDao.updateForMoney(supplier);
    }

    @Override
    public int updateForSuperAdmin(Suppliers supplier) {
        return supplierDao.updateForSuperAdmin(supplier);
    }

    @Override
    public int getPriceFromDMForSM(Suppliers supplier) {
        return supplierDao.getPriceFromDMForSM(supplier);
    }
}

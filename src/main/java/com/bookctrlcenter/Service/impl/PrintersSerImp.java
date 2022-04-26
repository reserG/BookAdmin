package com.bookctrlcenter.Service.impl;


import com.bookctrlcenter.Service.PrintersSer;
import com.bookctrlcenter.entity.Printerss;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("printersSerImp")
public class PrintersSerImp extends BaseSerImp<Printerss> implements PrintersSer {
    @Override
    public List<Printerss> listAllM(Printerss printers) {
        return printersDao.listAllM(printers);
    }

    @Override
    public int getDataCountM(Printerss printers) {
        return printersDao.getDataCountM(printers);
    }

    @Override
    public List<Printerss> listAllSUP(Printerss printers) {
        return printersDao.listAllSUP(printers);
    }

    @Override
    public int getDataCountSUP(Printerss printers) {
        return printersDao.getDataCountSUP(printers);
    }

    @Override
    public int insertSUP(Printerss printers) {
        return printersDao.insertSUP(printers);
    }

    @Override
    public int updateForMoneyR(Printerss printers) {
        return printersDao.updateForMoneyR(printers);
    }

    @Override
    public int updateForMoney(Printerss printers) {
        return printersDao.updateForMoney(printers);
    }

    @Override
    public int updateForSuperAdmin(Printerss printers) {
        return printersDao.updateForSuperAdmin(printers);
    }

    @Override
    public int getPriceFromDMForSM(Printerss printers) {
        return printersDao.getPriceFromDMForSM(printers);
    }
}

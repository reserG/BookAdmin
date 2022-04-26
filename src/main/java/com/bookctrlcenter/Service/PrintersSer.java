package com.bookctrlcenter.Service;

import com.bookctrlcenter.entity.Printerss;

import java.util.List;

public interface PrintersSer extends BaseSer<Printerss>  {
    int getPriceFromDMForSM(Printerss printers);
    int updateForSuperAdmin(Printerss printers);
    int updateForMoney(Printerss printers);
    List<Printerss> listAllM(Printerss printers);
    int getDataCountM(Printerss printers);
    int updateForMoneyR(Printerss printers);
    List<Printerss> listAllSUP(Printerss printers);
    int getDataCountSUP(Printerss printers);
    int insertSUP(Printerss printers);
}

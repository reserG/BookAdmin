package com.bookctrlcenter.dao;

import com.bookctrlcenter.entity.Printerss;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PrintersDao extends BaseDao<Printerss>{
    int getPriceFromDMForSM(Printerss printers);
    int updateForSuperAdmin(Printerss printers);
    int updateForMoney(Printerss printers);
    int updateForMoneyR(Printerss printers);
    List<Printerss> listAllSUP(Printerss printers);
    int getDataCountSUP(Printerss printers);
int insertSUP(Printerss printers);
    List<Printerss> listAllM(Printerss printers);
    int getDataCountM(Printerss printers);
}

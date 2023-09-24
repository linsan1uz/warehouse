package com.fqf.warehouse.service.impl;

import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.BuyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.fqf.warehouse.mapper.BuyListMapper;
import com.fqf.warehouse.entity.BuyList;

import java.util.List;

@Service
public class BuyListServiceImpl implements BuyListService {
    @Autowired
    private BuyListMapper buyListMapper;
    @Override
    public Result insertPurchase(BuyList buyList) {
        int i = buyListMapper.insertPurchase(buyList);
        if (i>0){
            return Result.ok("采购单添加成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"采购单添加失败");
    }

    @Override
    public Page PurchaseService(Page page, BuyList buyList) {
        int purchaseCount = buyListMapper.selectPurchaseCount(buyList);
        List<BuyList> purchaseList = buyListMapper.selectPurchasePage(page, buyList);
        page.setTotalNum(purchaseCount);
        page.setResultList(purchaseList);
        return page;
    }

    @Override
    public Result deleteBuyList(Integer buyId) {
        int i = buyListMapper.removeBuyList(buyId);
        if (i>0){
            return Result.ok("删除成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"删除失败");
    }

    @Override
    public Result setBuyList(BuyList buyList) {
        int i = buyListMapper.updateBuyList(buyList);
        if(i>0){
            return Result.ok("采购单修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "采购单修改失败！");
    }
}

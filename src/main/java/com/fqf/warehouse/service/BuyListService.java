package com.fqf.warehouse.service;

import com.fqf.warehouse.entity.BuyList;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.page.Page;

public interface BuyListService {
    public Result insertPurchase(BuyList buyList);
    public Page PurchaseService(Page page, BuyList buyList);
    public Result deleteBuyList(Integer buyId);
    public Result setBuyList(BuyList buyList);
}

package com.fqf.warehouse.controller;

import com.fqf.warehouse.entity.*;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.BuyListService;
import com.fqf.warehouse.service.InStoreService;
import com.fqf.warehouse.service.StoreService;
import com.fqf.warehouse.utils.TokenUtils;
import com.fqf.warehouse.utils.WarehouseConstants;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/purchase")
@RestController
public class PurchaseController {
    @Autowired
    private BuyListService buyListService;
    @Autowired
    private StoreService storeService;

    @RequestMapping("/purchase-add")
    public Result addPurchase(@RequestBody BuyList buyList){
        Result result = buyListService.insertPurchase(buyList);
        return result;
    }

    @RequestMapping("/store-list")
    public Result storeList(){
        List<Store> storeList = storeService.queryAllStore();
        return Result.ok(storeList);
    }

    @RequestMapping("/purchase-page-list")
    public Result storeList(Page page,BuyList buyList){
        page = buyListService.PurchaseService(page,buyList);
        return Result.ok(page);
    }
    @RequestMapping("/purchase-delete/{buyId}")
    public Result deleteBuyList(@PathVariable Integer buyId){
        Result result = buyListService.deleteBuyList(buyId);
        return result;
    }
    @RequestMapping("/purchase-update")
    public Result updatePurchase(@RequestBody BuyList buyList){
        Result result = buyListService.setBuyList(buyList);
        return result;
    }
    @Autowired
    private InStoreService inStoreService;

    @Autowired
    private TokenUtils tokenUtils;
    @RequestMapping("/in-warehouse-record-add")
    public Result genBuyList(@RequestBody BuyList buyList, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int creatBy = currentUser.getUserId();
        InStore inStore = new InStore();
        inStore.setStoreId(buyList.getStoreId());
        inStore.setProductId(buyList.getProductId());
        inStore.setInNum(buyList.getFactBuyNum());
        inStore.setCreateBy(creatBy);
        Result result = inStoreService.saveInStore(inStore,buyList.getBuyId());
        return result;
    }
}

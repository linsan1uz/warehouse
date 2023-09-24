package com.fqf.warehouse.controller;

import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.entity.Store;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("store")
@RestController
public class StoreController {
    @Autowired
    private StoreService storeService;

    @RequestMapping("/store-page-list")
    public Result storePageList(Page page, Store store){
        Page page1 = storeService.queryStorePage(page, store);
        return Result.ok(page1);
    }
    /*@RequestMapping("/store-update")
    public Result updateStore(@RequestBody Store store){

    }*/
    @RequestMapping("/store-num-check")
    public Result checkStoreByNum(String storeNum){
        Result result = storeService.checkNum(storeNum);
        return result;
    }
    @RequestMapping("/store-add")
    public Result addStore(@RequestBody Store store){
        Result result = storeService.saveStore(store);
        return result;
    }

    @RequestMapping("/store-delete/{storeId}")
    public Result deleteStore(@PathVariable Integer storeId) {
        Result result = storeService.removeStore(storeId);
        return result;
    }
    @RequestMapping("/store-update")
    public Result updateStore(@RequestBody Store store){
        Result result = storeService.updateStore(store);
        return result;
    }
    @RequestMapping("/exportTable")
    public Result exportTable(Page page,Store store){
        page = storeService.queryStorePage(page, store);
        List<?> resultList = page.getResultList();
        return Result.ok(resultList);
    }
}

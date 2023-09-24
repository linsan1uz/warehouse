package com.fqf.warehouse.controller;

import com.fqf.warehouse.entity.InStore;
import com.fqf.warehouse.entity.Product;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.entity.Store;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.InStoreService;
import com.fqf.warehouse.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.fqf.warehouse.service.StoreService.*;

@RequestMapping("/instore")
@RestController
public class InStoreController {
    @Autowired
    private InStoreService inStoreService;
    @Autowired
    private StoreService storeService;
    @RequestMapping("/instore-page-list")
    public Result queryInStorePage(Page page, InStore inStore){
        page = inStoreService.queryInStorePage(page, inStore);
        return Result.ok(page);
    }
    @RequestMapping("/store-list")
    public Result storeList(){
        //执行业务
        List<Store> storeList = storeService.queryAllStore();
        //响应
        return Result.ok(storeList);
    }
    @RequestMapping("/instore-confirm")
    public Result confirmInStore(@RequestBody InStore inStore){
        //执行业务
        Result result = inStoreService.confirmInStore(inStore);
        //响应
        return result;
    }
}

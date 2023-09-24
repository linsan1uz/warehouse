package com.fqf.warehouse.controller;

import com.fqf.warehouse.entity.CurrentUser;
import com.fqf.warehouse.entity.OutStore;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.entity.Store;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.InStoreService;
import com.fqf.warehouse.service.OutStoreService;
import com.fqf.warehouse.service.StoreService;
import com.fqf.warehouse.utils.TokenUtils;
import com.fqf.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/outstore")
@RestController
public class OutStoreController {
    @Autowired
    private OutStoreService outStoreService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private StoreService storeService;

    @RequestMapping("/outstore-add")
    public Result addOutStore(@RequestBody OutStore outStore, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();
        outStore.setCreateBy(createBy);
        Result result = outStoreService.saveOutStore(outStore);
        return result;
    }

    @RequestMapping("/store-list")
    public Result storeList(){
        List<Store> storeList = storeService.queryAllStore();
        return Result.ok(storeList);
    }

    @RequestMapping("/outstore-page-list")
    public Result queryInStorePage(Page page, OutStore outStore){
        page = outStoreService.outStorePage(page, outStore);
        return Result.ok(page);
    }
    @RequestMapping("/outstore-confirm")
    public Result confimOutStore(@RequestBody OutStore outStore){
        Result result = outStoreService.confirmOutStore(outStore);
        return result;
    }
}

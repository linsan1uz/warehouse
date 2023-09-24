package com.fqf.warehouse.service;

import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.entity.Store;
import com.fqf.warehouse.page.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StoreService {
    public List<Store> queryAllStore();
    public Page queryStorePage(Page page, Store store);
    public Result saveStore(Store store);

    public Result checkNum(String storeNum);
    public Result removeStore(int storeId);

    public Result updateStore(Store store);
}

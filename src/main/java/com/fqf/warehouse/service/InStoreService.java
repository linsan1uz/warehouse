package com.fqf.warehouse.service;

import com.fqf.warehouse.entity.InStore;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.page.Page;

public interface InStoreService {
    public Result saveInStore(InStore inStore, Integer buyId);
    //分页查询入库单的业务方法
    public Page queryInStorePage(Page page, InStore inStore);
    public Result confirmInStore(InStore inStore);
}
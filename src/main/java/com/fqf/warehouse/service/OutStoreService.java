package com.fqf.warehouse.service;

import com.fqf.warehouse.entity.OutStore;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.page.Page;

public interface OutStoreService{
    //添加出库单的业务方法
    public Result saveOutStore(OutStore outStore);
    public Page outStorePage(Page page, OutStore outStore);
    //确认出库的业务方法
    public Result confirmOutStore(OutStore outStore);
}

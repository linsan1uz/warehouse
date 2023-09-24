package com.fqf.warehouse.service.impl;

import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.entity.Store;
import com.fqf.warehouse.mapper.StoreMapper;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@CacheConfig(cacheNames = "com.fqf.warehouse.service.impl.StoreServiceImpl")
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;

    @Cacheable(key = "'all:store'")
    @Override
    public List<Store> queryAllStore() {
        return storeMapper.findAllStore();
    }

    @Override
    public Page queryStorePage(Page page, Store store) {
        //查询仓库总行数
        int storeCount = storeMapper.selectStoreCount(store);

        //分页查询仓库
        List<Store> storeList = storeMapper.selectStorePage(page, store);

        //将查到的总行数和当前页数据封装到Page对象
        page.setTotalNum(storeCount);
        page.setResultList(storeList);

        return page;
    }

    @Override
    public Result saveStore(Store store) {
        Store queryStore = storeMapper.queryStore(store);
        if (queryStore != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "仓库已存在");
        }
        int i = storeMapper.insertStore(store);
        if (i > 0) {
            return Result.ok("仓库添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "仓库添加失败！");
    }

    @Override
    public Result checkNum(String storeNum) {
        Store store = new Store();
        store.setStoreNum(storeNum);
        Store queriedStore = storeMapper.queryStore(store);
        return Result.ok(queriedStore == null);
    }

    @Override
    public Result removeStore(int storeId) {
        int i = storeMapper.deleteStore(storeId);
        if (i > 0) {
            return Result.ok("删除成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "刪除失败");
    }

    @Override
    public Result updateStore(Store store) {
        int i = storeMapper.updateStore(store);
        if (i > 0) {
            return Result.ok("修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "修改失败");
    }
}

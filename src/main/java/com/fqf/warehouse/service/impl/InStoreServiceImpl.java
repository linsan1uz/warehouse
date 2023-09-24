package com.fqf.warehouse.service.impl;

import com.fqf.warehouse.entity.InStore;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.mapper.BuyListMapper;
import com.fqf.warehouse.mapper.InStoreMapper;
import com.fqf.warehouse.mapper.ProductMapper;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.BuyListService;
import com.fqf.warehouse.service.InStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InStoreServiceImpl implements InStoreService {
    @Autowired
    private InStoreMapper inStoreMapper;

    @Autowired
    private BuyListMapper buyListMapper;

    @Autowired
    private ProductMapper productMapper;
    @Transactional
    @Override
    public Result saveInStore(InStore inStore, Integer buyId) {
        int i = inStoreMapper.insertInStore(inStore);
        if(i>0){
            //根据id将采购单状态改为已入库
            int j = buyListMapper.updateIsInById(buyId);
            if(j>0){
                return Result.ok("入库单添加成功！");
            }
            return Result.err(Result.CODE_ERR_BUSINESS, "入库单添加失败！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "入库单添加失败！");

    }

    @Override
    public Page queryInStorePage(Page page, InStore inStore) {
        //查询入库单总行数
        int inStoreCount = inStoreMapper.selectInStoreCount(inStore);

        //分页查询入库单
        List<InStore> inStoreList = inStoreMapper.selectInStorePage(page, inStore);

        //将查询到的总行数和当前页数据封装到Page对象
        page.setTotalNum(inStoreCount);
        page.setResultList(inStoreList);
        return page;
    }

    @Transactional
    @Override
    public Result confirmInStore(InStore inStore) {
        int i = inStoreMapper.updateIsInById(inStore.getInsId());
        if(i>0){
            //根据商品id增加商品库存
            int j = productMapper.addInventById(inStore.getProductId(), inStore.getInNum());
            if(j>0){
                return Result.ok("入库成功！");
            }
            return Result.err(Result.CODE_ERR_BUSINESS, "入库失败！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "入库失败！");
    }
}

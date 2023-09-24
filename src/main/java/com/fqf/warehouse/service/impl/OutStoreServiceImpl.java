package com.fqf.warehouse.service.impl;

import com.fqf.warehouse.entity.OutStore;
import com.fqf.warehouse.entity.Product;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.mapper.OutStoreMapper;
import com.fqf.warehouse.mapper.ProductMapper;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.OutStoreService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutStoreServiceImpl implements OutStoreService {

    @Autowired
    private OutStoreMapper outStoreMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public Result saveOutStore(OutStore outStore) {
        int i = outStoreMapper.insertOutStore(outStore);
        if (i>-0){
            return Result.ok("添加出库但成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"添加出库单失败");
    }

    @Override
    public Page outStorePage(Page page, OutStore outStore) {
        int count = outStoreMapper.outStoreCount(outStore);
        List<OutStore> outStoreList = outStoreMapper.queryOutStorePage(page,outStore);
        page.setTotalNum(count);
        page.setResultList(outStoreList);
        return page;
    }

    @Override
    public Result confirmOutStore(OutStore outStore) {
        Product product = productMapper.selectProductById(outStore.getProductId());
        if (outStore.getOutNum()>product.getProductInvent()){
            return Result.err(Result.CODE_ERR_BUSINESS,"库存不足");
        }
        int i = outStoreMapper.updateIsOutById(outStore.getOutsId());
        if (i>0){
            int j = productMapper.addInventById(outStore.getProductId(), -outStore.getOutNum());
            if (j>0){
                return Result.ok("出库成功");
            }
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"出库失败");
    }
}

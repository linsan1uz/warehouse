package com.fqf.warehouse.service.impl;

import com.fqf.warehouse.entity.Supply;
import com.fqf.warehouse.mapper.SupplyMapper;
import com.fqf.warehouse.service.SupplyService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@CacheConfig(cacheNames = "com.fqf.warehouse.service.impl.SupplyServiceImpl")
@Service
public class SupplyServiceImpl implements SupplyService {
    @Autowired
    private SupplyMapper supplyMapper;
    @Cacheable(key = "'all:supply'")
    @Override
    public List<Supply> queryAllSupply() {
        return supplyMapper.allSupplyList();
    }
}

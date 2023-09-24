package com.fqf.warehouse.service.impl;

import com.fqf.warehouse.entity.Unit;
import com.fqf.warehouse.mapper.UnitMapper;
import com.fqf.warehouse.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "com.fqf.warehouse.service.impl.UnitServiceImpl")
@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitMapper unitMapper;

    @Cacheable(key = "'all:unit'")
    @Override
    public List<Unit> queryAllUnit() {
        return unitMapper.findAllUnit();
    }
}

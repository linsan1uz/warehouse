package com.fqf.warehouse.service.impl;

import com.fqf.warehouse.entity.Brand;
import com.fqf.warehouse.mapper.BrandMapper;
import com.fqf.warehouse.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@CacheConfig(cacheNames = "com.fqf.warehouse.service.impl.BrandServiceImpl")
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Cacheable(key = "'all:brand'")
    @Override
    public List<Brand> queryAllBrand() {
        return brandMapper.findAllBrand();
    }
}

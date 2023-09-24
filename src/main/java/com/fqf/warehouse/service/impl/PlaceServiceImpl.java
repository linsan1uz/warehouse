package com.fqf.warehouse.service.impl;

import com.fqf.warehouse.entity.Place;
import com.fqf.warehouse.mapper.PlaceMapper;
import com.fqf.warehouse.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@CacheConfig(cacheNames = "com.fqf.warehouse.service.impl.PlaceServiceImpl")
@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceMapper placeMapper;
    @Cacheable(key = "'all:place'")
    @Override
    public List<Place> queryAllPlace() {
        return placeMapper.findAllPlace();
    }
}

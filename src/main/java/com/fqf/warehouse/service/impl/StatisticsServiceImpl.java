package com.fqf.warehouse.service.impl;

import com.fqf.warehouse.entity.Statistics;
import com.fqf.warehouse.mapper.StatisticsMapper;
import com.fqf.warehouse.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;
    @Override
    public List<Statistics> statisticStoreInvent() {
        return statisticsMapper.statisticsStoreInvent();
    }
}

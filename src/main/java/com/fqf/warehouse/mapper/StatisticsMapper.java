package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.Statistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsMapper {
    public List<Statistics> statisticsStoreInvent();
}

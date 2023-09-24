package com.fqf.warehouse.service;

import com.fqf.warehouse.entity.Statistics;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface StatisticsService {
    public List<Statistics> statisticStoreInvent();

}

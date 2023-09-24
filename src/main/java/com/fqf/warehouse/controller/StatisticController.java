package com.fqf.warehouse.controller;

import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.entity.Statistics;
import com.fqf.warehouse.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static javafx.scene.input.KeyCode.R;

@RequestMapping("/statistics")
@RestController
public class StatisticController {
    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping("/store-invent")
    public Result inventStore(){
        List<Statistics> statisticsList = statisticsService.statisticStoreInvent();
        return Result.ok(statisticsList);
    }
}

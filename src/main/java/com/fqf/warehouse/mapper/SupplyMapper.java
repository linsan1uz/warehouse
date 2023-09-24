package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.Supply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SupplyMapper {
    public List<Supply> allSupplyList();
}

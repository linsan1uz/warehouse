package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.Unit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UnitMapper {
    public List<Unit> findAllUnit();
}

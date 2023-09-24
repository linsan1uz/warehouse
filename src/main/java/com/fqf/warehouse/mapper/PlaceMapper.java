package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.Place;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PlaceMapper {
    public List<Place> findAllPlace();
}

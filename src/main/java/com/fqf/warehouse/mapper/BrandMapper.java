package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    public List<Brand> findAllBrand();
}

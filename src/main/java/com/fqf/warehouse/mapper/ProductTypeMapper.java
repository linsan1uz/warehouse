package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.ProductType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ProductTypeMapper {
    public List<ProductType> findAllProductType();
    public ProductType findTypeByCode(ProductType productType);
    public Integer insertProductType(ProductType productType);
    public Integer deleteProductType(Integer typeId);
    public Integer updateProductType(ProductType productType);
}

package com.fqf.warehouse.service;

import com.fqf.warehouse.entity.BuyList;
import com.fqf.warehouse.entity.ProductType;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.page.Page;

import java.util.List;

public interface ProductTypeService {
    public List<ProductType> allProductTypeTree();
    public Result checkCode(String typeCode);

    public Result addProductType(ProductType productType);

    public Result deleteProductType(Integer typeId);
    public Result setProductType(ProductType productType);

}

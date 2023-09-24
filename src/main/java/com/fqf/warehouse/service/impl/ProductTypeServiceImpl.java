package com.fqf.warehouse.service.impl;

import com.fqf.warehouse.entity.BuyList;
import com.fqf.warehouse.entity.ProductType;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.mapper.ProductTypeMapper;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@CacheConfig(cacheNames = "com.fqf.warehouse.service.impl.ProductTypeServiceImpl")
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Cacheable(key = "'all:typeTree'")
    @Override
    public List<ProductType> allProductTypeTree() {
        //查询所有商品分类
        List<ProductType> allTypeList = productTypeMapper.findAllProductType();
        //将所有商品分类List<ProductType>转成商品分类树List<ProductType>
        List<ProductType> typeTreeList = allTypeToTypeTree(allTypeList, 0);
        //返回商品分类树List<ProductType>
        return typeTreeList;
    }
    //只写一个分类编码存在是否就可以了
    @Override
    public Result checkCode(String typeCode) {
        //根据分类编码查询分类
        ProductType productType = new ProductType();
        productType.setTypeCode(typeCode);
        ProductType prodType = productTypeMapper.findTypeByCode(productType);
        return Result.ok(prodType==null);
    }
    @CacheEvict(key = "'all:typeTree'")
    @Override
    public Result addProductType(ProductType productType) {
        //校验分类是否存在

        ProductType productCategory = productTypeMapper.findTypeByCode(productType);
        if (productCategory!=null){
            return Result.err(Result.CODE_ERR_BUSINESS,"分类名称已存在");
        }
        Integer i = productTypeMapper.insertProductType(productType);
        if (i>0)
        {
            return Result.ok("商品分类添加成功");
        }

        return Result.err(Result.CODE_ERR_BUSINESS,"商品类别增加失败");
    }
    @CacheEvict(key = "'all:typeTree'")
    @Override
    public Result deleteProductType(Integer typeId) {
        Integer i = productTypeMapper.deleteProductType(typeId);
        if (i>0){
            return Result.ok("删除成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"删除失败");
    }

    @CacheEvict(key = "'all:typeTree'")
    @Override
    public Result setProductType(ProductType productType) {
        Integer i = productTypeMapper.updateProductType(productType);
        if (i>0){
            return Result.ok("修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"修改失败");
    }




    //将查询到的所有商品分类List<ProductType>转成商品分类树List<ProductType>的算法
    private List<ProductType> allTypeToTypeTree(List<ProductType> allTypeList,
                                                Integer parentId){

        List<ProductType> typeList = new ArrayList<>();
        for (ProductType productType : allTypeList) {
            if(productType.getParentId().equals(parentId)){
                typeList.add(productType);
            }
        }

        for (ProductType productType : typeList) {
            List<ProductType> childTypeList =
                    allTypeToTypeTree(allTypeList, productType.getTypeId());
            productType.setChildProductCategory(childTypeList);
        }

        return typeList;

    }
}

package com.fqf.warehouse.controller;

import com.fqf.warehouse.entity.ProductType;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.service.ProductService;
import com.fqf.warehouse.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/productCategory")
@RestController
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("product-category-tree")
    public Result productCategory(){
        List<ProductType> typeTreeList = productTypeService.allProductTypeTree();
        return Result.ok(typeTreeList);
    }
    //校验用户编码是否存在的接口
    @RequestMapping("/verify-type-code")
    public Result checkTypeCode(String typeCode){
        Result result = productTypeService.checkCode(typeCode);
        return result;
    }

    @RequestMapping("type-add")
    public Result addType(@RequestBody ProductType productType){
        Result result = productTypeService.addProductType(productType);
        return result;
    }
    @RequestMapping("/type-delete/{typeId}")
    public Result deleteType(@PathVariable Integer typeId){
        Result result = productTypeService.deleteProductType(typeId);
        return result;
    }
    @RequestMapping("type-update")
    public Result updateType(@RequestBody ProductType productType){
        Result result = productTypeService.setProductType(productType);
        return result;
    }
}

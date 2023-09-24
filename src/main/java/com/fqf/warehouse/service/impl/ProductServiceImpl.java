package com.fqf.warehouse.service.impl;

import com.fqf.warehouse.entity.BuyList;
import com.fqf.warehouse.entity.Product;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.mapper.ProductMapper;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public Page queryProductPage(Page page, Product product) {
        int i = productMapper.selectProductCount(product);
        List<Product> productList = productMapper.selectProductPage(page, product);
        page.setTotalNum(i);
        page.setResultList(productList);
        return page;
    }
    @Value("${file.access-path}")
    private String accessPath;
    @Override
    public Result addProduct(Product product) {
        String path = "/"+accessPath+product.getImgs();
        System.out.println(path);
        product.setImgs(path);
        int i = productMapper.addProduct(product);
        if (i>0){
            return Result.ok("添加成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"添加失败");
    }

    @Override
    public Result updateProductState(Product product) {
        int i = productMapper.updateStateById(product);
        if (i>0){
            return Result.ok("修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"修改失败");
    }

    @Override
    public Result deleteProduct(Integer productId) {
        int i = productMapper.deleteProductById(productId);
        if (i>0){
            return Result.ok("删除成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"删除失败");
    }

    @Override
    public Result updateProduct(Product product) {
        if (!product.getImgs().startsWith(accessPath)){
            product.setImgs(accessPath+product.getImgs());
        }
        int i = productMapper.updateProductInfo(product);
        if (i>0){
            return Result.ok("商品修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"商品修改失败");
    }


}

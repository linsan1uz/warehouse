package com.fqf.warehouse.service;

import com.fqf.warehouse.entity.BuyList;
import com.fqf.warehouse.entity.Product;
import com.fqf.warehouse.entity.Result;
import com.fqf.warehouse.page.Page;

public interface ProductService {
    public Page queryProductPage(Page page, Product product);

    public Result addProduct(Product product);
    public Result updateProductState(Product product);
    public Result deleteProduct(Integer productId);

    public Result updateProduct(Product product);

}

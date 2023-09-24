package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.Product;
import com.fqf.warehouse.page.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    public int selectProductCount(Product product);
    public List<Product> selectProductPage(@Param("page")Page page,@Param("product") Product product);

    public int addProduct(Product product);

    public int updateStateById(Product product);

    public int deleteProductById(Integer productId);

    public int updateProductInfo(Product product);

    public int addInventById(Integer productId,Integer intvent);

    public Product selectProductById(Integer product);
}

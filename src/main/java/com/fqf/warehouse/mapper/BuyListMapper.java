package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.BuyList;
import com.fqf.warehouse.page.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BuyListMapper {
    public int insertPurchase(BuyList buyList);
    public int selectPurchaseCount(BuyList buyList);
    public List<BuyList> selectPurchasePage(@Param("page")Page page,@Param("buyList")BuyList buyList);
    public int removeBuyList(Integer buyId);

    public int updateBuyList(BuyList buyList);
    public int updateIsInById(Integer buyId);
}
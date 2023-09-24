package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.Store;
import com.fqf.warehouse.page.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper {
    public List<Store> findAllStore();
    public int selectStoreCount(Store store);
    public List<Store> selectStorePage(@Param("page") Page page,
                                       @Param("store") Store store);
    public int insertStore(Store store);
    public Store queryStore(Store store);
    public int deleteStore(Integer storeId);

    public int updateStore(Store store);
}
package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.OutStore;
import com.fqf.warehouse.entity.Product;
import com.fqf.warehouse.page.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OutStoreMapper {
    public int insertOutStore(OutStore outStore);
    public List<OutStore> queryOutStorePage(@Param("page") Page page,
                                  @Param("outStore") OutStore outStore
    );
    //查询出库单总行数的方法
    public int outStoreCount(OutStore outStore);

    public int updateIsOutById(Integer outsId);
}
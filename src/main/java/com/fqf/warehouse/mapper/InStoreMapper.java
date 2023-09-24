package com.fqf.warehouse.mapper;

import com.fqf.warehouse.entity.InStore;
import com.fqf.warehouse.page.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InStoreMapper {
    public int insertInStore(InStore inStore);
    public int selectInStoreCount(InStore inStore);

    //分页查询入库单的方法
    public List<InStore> selectInStorePage(@Param("page") Page page,
                                           @Param("inStore") InStore inStore);
    public int updateIsInById(Integer insId);
}
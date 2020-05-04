package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Spot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SpotMapper {

    Spot findSpotByAreaAndRoom(@Param("area") String area, @Param("room") String room);

    Spot findSpotById(@Param("id") String id);
}

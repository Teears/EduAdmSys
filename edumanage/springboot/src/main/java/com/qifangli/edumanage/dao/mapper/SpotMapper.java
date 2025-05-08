package com.qifangli.edumanage.dao.mapper;

import com.qifangli.edumanage.dao.entity.Spot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpotMapper {

    Spot findSpotByAreaAndRoom(@Param("area") String area, @Param("room") String room);

    Spot findSpotById(@Param("id") String id);

    List<String> findRoomByArea(@Param("area") String area);
}

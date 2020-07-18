package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Spot;

import java.util.List;

public interface SpotService {

    Spot findSpotByAreaAndRoom(String area, String room);

    Spot findSpotById(String id);

    List<String> findRoomByArea(String area);

}

package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.Spot;

public interface SpotService {

    Spot findSpotByAreaAndRoom(String area, String room);

    Spot findSpotById(String id);

}

package com.qifangli.edumanage.service;

import com.qifangli.edumanage.dao.entity.CourseArrange;
import com.qifangli.edumanage.dao.entity.Spot;

import java.util.List;

public interface RoomService {

    Spot findSpotByAreaAndRoom(String area, String room);

    List<CourseArrange> findByTermAndSpot(String term, String spot);

}

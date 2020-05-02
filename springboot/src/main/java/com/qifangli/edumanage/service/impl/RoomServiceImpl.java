package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.CourseArrange;
import com.qifangli.edumanage.dao.entity.Spot;
import com.qifangli.edumanage.dao.mapper.CourseArrangeMapper;
import com.qifangli.edumanage.dao.mapper.SpotMapper;
import com.qifangli.edumanage.service.RoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Resource
    private SpotMapper spotMapper;

    @Resource
    private CourseArrangeMapper courseArrangeMapper;

    @Override
    public Spot findSpotByAreaAndRoom(String area, String room) {
        return spotMapper.findSpotByAreaAndRoom(area, room);
    }

    @Override
    public List<CourseArrange> findByTermAndSpot(String term, String spot) {
        return courseArrangeMapper.findByTermAndSpot(term, spot);
    }
}

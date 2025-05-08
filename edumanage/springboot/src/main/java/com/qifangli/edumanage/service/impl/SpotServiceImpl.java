package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.Spot;
import com.qifangli.edumanage.dao.mapper.SpotMapper;
import com.qifangli.edumanage.service.SpotService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpotServiceImpl implements SpotService {
    @Resource
    private SpotMapper spotMapper;

    @Override
    public Spot findSpotByAreaAndRoom(String area, String room) {
        return spotMapper.findSpotByAreaAndRoom(area, room);
    }

    @Override
    public Spot findSpotById(String id) {
        return spotMapper.findSpotById(id);
    }

    @Override
    public List<String> findRoomByArea(String area) {
        return spotMapper.findRoomByArea(area);
    }

}

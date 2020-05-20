package com.qifangli.edumanage.service.impl;

import com.qifangli.edumanage.dao.entity.CourseArrange;
import com.qifangli.edumanage.dao.mapper.CourseArrangeMapper;
import com.qifangli.edumanage.service.CourseArrangeService;
import com.qifangli.edumanage.dao.entity.Spot;
import com.qifangli.edumanage.service.SpotService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseArrangeServiceImpl implements CourseArrangeService {
    @Resource
    private CourseArrangeMapper courseArrangeMapper;
    @Resource
    private SpotService spotService;



    @Override
    public List<CourseArrange> findByTermAndSpot(String term, String spot) {
        return courseArrangeMapper.findByTermAndSpot(term, spot);
    }

    @Override
    public List<CourseArrange> findByTermAndStuId(String term, String id) {
        return courseArrangeMapper.findByTermAndStuId(term,id);
    }

    @Override
    public List<CourseArrange> findByTermAndTeaId(String term, String id) {
        return courseArrangeMapper.findByTermAndTeaId(term,id);
    }

    @Override
    public List<CourseArrange> findByTermDptGrade(String term,String dpt,String grade) {
        return courseArrangeMapper.findByTermDptGrade(term,dpt,grade);
    }

    @Override
    public Map<String, Object> addArrange(List<Object> entityList) {
        int success = 0;
        List<CourseArrange> lis = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        for (Object object: entityList){
            CourseArrange courseArrange = (CourseArrange)object;
            System.out.println(courseArrange.getTeacherno()+courseArrange.getCourseno()+courseArrange.getTerm());
            Spot spot = spotService.findSpotByAreaAndRoom(courseArrange.getArea(),courseArrange.getRoom());
            courseArrange.setSpot(spot.getId());
            try {
                int i = courseArrangeMapper.insertArrange(courseArrange);
                if(i>0){
                    success++;
                }else {
                    lis.add(courseArrange);
                }
            }catch (DuplicateKeyException e){
                e.printStackTrace();
                System.out.println("违反唯一性约束：教室被占用");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        map.put("success",success);
        map.put("data",lis);
        return map;
    }

    @Override
    public int deleteArrangeById(String id) {
        return courseArrangeMapper.deleteArrangeById(id);
    }

    @Override
    public int updateArrange(CourseArrange courseArrange) {
        return courseArrangeMapper.updateArrange(courseArrange);
    }
}

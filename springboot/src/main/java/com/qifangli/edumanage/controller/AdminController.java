package com.qifangli.edumanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.qifangli.edumanage.dao.entity.*;
import com.qifangli.edumanage.service.*;
import com.qifangli.edumanage.util.ExcelUtils;
import com.qifangli.edumanage.util.result.Result;
import com.qifangli.edumanage.util.result.ResultUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;
    @Resource
    private ClassAndGradeService classAndGradeService;

    /**
     * 查询学生信息
     * @param
     * @return
     */
    @RequiresPermissions("admin_stuAdmin")
    @PostMapping("/stuAdmin/getStuData")
    public Result getStuInfo(@RequestBody JSONObject param){
        String dpt = param.getString("dpt");
        String grade = param.getString("grade");
        List<Student> students = studentService.findStuByDptAndGrade(dpt,grade);

        return ResultUtils.success(students);
    }

    /**
     * 获取所有班级编号列表
     * @return
     */
    @RequiresPermissions("admin_stuAdmin")
    @PostMapping("/stuAdmin/getAllClass")
    public Result getAllClass(){
        List<String > allClass = classAndGradeService.findAllClass();
        List<Map<String,String>> maps = new LinkedList<>();
        for(int i = 0; i< allClass.size();i++){
            Map<String,String> map = new HashMap<>();
            map.put("classId",allClass.get(i));
            maps.add(map);
        }
        return ResultUtils.success(maps);
    }

    /**
     * 添加单个学生
     * @param param
     * @return
     */
    @RequiresPermissions("admin_stuAdmin")
    @PostMapping("/stuAdmin/insertStudent")
    public Result insertStu(@RequestBody JSONObject param){
        String id = param.getString("id");
        String name = param.getString("name");
        String sex = param.getString("sex");
        String graduate = param.getString("graduate");
        String birth = param.getString("birth");
        String idCard = param.getString("idCard");
        String political = param.getString("political");
        String telephone = param.getString("telephone");
        String department = param.getString("department");
        String classAndGrade = param.getString("classAndGrade");
        String status = param.getString("status");

        Student student = new Student(id,name,sex,graduate,birth,idCard,telephone,political,department,classAndGrade,status);
        try {
            studentService.insertStu(student);
            return ResultUtils.success();
        }catch (Exception e){
            return ResultUtils.error(-1,"添加数据有误，请检查！");
        }
    }

    /**
     * 编辑学生信息
     * @param param
     * @return
     */
    @RequiresPermissions("admin_stuAdmin")
    @PostMapping("/stuAdmin/editStu")
    public Result editStu(@RequestBody JSONObject param){
        String id = param.getString("id");
        String name = param.getString("name");
        String sex = param.getString("sex");
        String graduate = param.getString("graduate");
        String birth = param.getString("birth");
        String idCard = param.getString("idCard");
        String political = param.getString("political");
        String telephone = param.getString("telephone");
        String department = param.getString("department");
        String classAndGrade = param.getString("classAndGrade");
        String status = param.getString("status");
        Student student = new Student(id,name,sex,graduate,birth,idCard,telephone,political,department,classAndGrade,status);
        if(studentService.updateStu(student)>0){
            return ResultUtils.success();
        }else {
            return ResultUtils.error(-1,"数据不存在");
        }
    }

    /**
     * 按学号删除学生
     * @param param
     * @return
     */
    @RequiresPermissions("admin_stuAdmin")
    @PostMapping("/stuAdmin/deleteStu")
    public Result deleteStu(@RequestBody JSONObject param){
        String id = param.getString("id");
        try {
            if(studentService.deleteStuById(id)>0){
                return ResultUtils.success();
            }else {
                return ResultUtils.error(-1,"要删除的数据不存在");
            }
        }catch (Exception e){
            return ResultUtils.error(-1,"不能删除该学生");
        }

    }

    /**
     * excel导入
     * @param file
     * @return
     */
    @RequiresPermissions("admin_stuAdmin")
    @RequestMapping("/stuAdmin/upload")
    public Result stuUpload(@RequestParam("file") MultipartFile file){
        InputStream is = null;
        Map<String,Object> map= new HashMap<>();
        try{
            is = file.getInputStream();
            List<Object> studentList = ExcelUtils.importDataFromExcel(new Student(),is,file.getOriginalFilename());
            if(studentList.size()==0){
                return ResultUtils.error(-1,"导入数据不能为空");
            }else{
                Map<String,Object> resmap = studentService.addStu(studentList);
                int totalNum = studentList.size();
                int failed = totalNum - Integer.parseInt(resmap.get("success").toString());
                map.put("success",resmap.get("success"));
                map.put("totalNum",totalNum);
                map.put("failed",failed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtils.success(map);
    }
}

<template>
<div style="width:1100px">
  <el-row type="flex" style="margin-bottom:5px">
    <el-col :span="4">
      <el-select v-model="deptSelected" @change="getDeptSelected" placeholder="请选择学院" style="width:150px" size="small">
            <el-option
            v-for="item in deptOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id">
            </el-option>
        </el-select>
    </el-col>
    <el-col :span="4">
        <el-select v-model="gradeSelected" @change="getGradeSelected" placeholder="请选择年级" style="width:150px" size="small">
            <el-option
            v-for="item in gradeOptions"
            :key="item.grade"
            :label="item.label"
            :value="item.grade">
            </el-option>
        </el-select>
    </el-col>
    <el-col :span="4">
      <el-select v-model="termSelected" @change="getTermSelected" placeholder="请选择学年" style="width:150px" size="small">
        <el-option
          v-for="item in termOptions"
          :key="item.term"
          :label="item.label"
          :value="item.term">
        </el-option>
      </el-select>
    </el-col>
    <el-col :span="4">
      <el-button type="primary" plain size="small" @click="selectOk">查询</el-button>
      <el-button type="primary" plain size="small" @click="dialogUploadVisible = true" icon="el-icon-folder-add">导入学生</el-button>
    </el-col>
      <el-button type="danger" size="small" @click="openOrClose">{{this.isOpen==true?"结束选课":"开启选课"}}</el-button>
  </el-row>

  <div style="background-color:#eff1f2;padding:5px;border-radius: 2px;">
    <el-table
    :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
    v-loading="loading"
    border
    stripe
    style="width: 100%"
    :header-row-style="{height:0+'px'}"
    :header-cell-style="{padding:0+'px'}"
    :row-style="{height:'20px'}"
    :cell-style="{padding:'2px'}">
    <el-table-column type="index" label="序号" width="59">
    </el-table-column>
    <el-table-column prop="courseno" label="课程编号" width="100">
    </el-table-column>
    <el-table-column prop="type" label="课程类型" width="150">
    </el-table-column>
    <el-table-column prop="courseName" label="课程名称" width="200">
    </el-table-column>
    <el-table-column prop="hours" label="学时" width="50">
    </el-table-column>
    <el-table-column prop="credit" label="学分" width="50">
    </el-table-column>
    <el-table-column prop="area,room" label="地点" width="200">
      <template slot-scope="scope"> {{scope.row.area}}{{scope.row.room}} </template>
    </el-table-column>
    <el-table-column prop="teacherName" label="任课教师" width="150">
    </el-table-column>
    <el-table-column prop="total" label="最大选课人数" width="130">
    </el-table-column>
    <el-table-column fixed="right" prop="operate" label="操作" width="100">
      <template slot-scope="scope">
          <el-button size="mini" plain type="primary"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" plain type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
    </el-table-column>
    </el-table>
  </div>
  <div>
  <el-pagination
  background
  layout="prev, pager, next"
  :page-size="pageSize"
  :total="totalCount"
  :current-page="currentPage"
  @current-change="handleCurrentChange"
  style="text-align:center">
  </el-pagination>
  </div>

  <el-dialog title="课程安排" :visible.sync="dialogFormVisible" :close-on-click-modal="false" width="40%">
  <el-form :model="form">
    <el-row type="flex" justify="space-between">
      <el-col :span="12">
        <el-form-item label="学院" label-width="80px">
          <el-input v-model="form.dept" :disabled="true"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="年级" label-width="80px">
          <el-input v-model="form.grade" :disabled="true"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
  <el-row type="flex" justify="space-between">
    <el-col :span="12">
      <el-form-item label="课程编号" label-width="80px">
        <el-input v-model="form.cnumber" :disabled="true"></el-input>
      </el-form-item>
    </el-col>
    <el-col :span="12">
      <el-form-item label="课程名称" label-width="80px">
        <el-input v-model="form.crsname" :disabled="true"></el-input>
      </el-form-item>
    </el-col>
  </el-row>
  <el-form-item label="上课地点" label-width="80px">
    <el-select v-model="form.areaSelected" placeholder="请选择区域" @change="getAreaSelected" style="width:180px">
      <el-option v-for="item in areaOptions"
      :key="item.area" :label="item.label" :value="item.area">
      </el-option>
    </el-select>
    <el-select v-model="form.roomSelected" placeholder="请选择教室" @change="getRoomSelected" style="width:180px;margin-left:10px">
      <el-option v-for="item in roomOptions"
      :key="item.room" :label="item.label" :value="item.room">
      </el-option>
    </el-select>
  </el-form-item>
  <el-form-item>
    <el-col :span="12">
      <el-form-item label="教师编号" label-width="80px">
        <el-input v-model="form.tnumber"></el-input>
      </el-form-item>
    </el-col>
    <el-col :span="12">
      <el-form-item label="教师姓名" label-width="80px">
        <el-input v-model="form.teaname"></el-input>
      </el-form-item>
    </el-col>
  </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="editOk">提交</el-button>
  </div>
  </el-dialog>

  <el-dialog title="上传文件" :visible.sync="dialogUploadVisible" close-on-click-modal=false>
  <el-upload
  class="upload-demo"
  ref="upload"
  multiple="false"
  accept=".xls,.xlsx"
  action=""
  with-credentials="true" 
  :on-preview="handlePreview"
  :on-remove="handleRemove"
  :file-list="fileList"
  :on-change="changeMe"
  :auto-upload="false">
  <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
  <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
  <div slot="tip" class="el-upload__tip">上传表格文件</div>
  </el-upload>
  </el-dialog>

</div>
</template>

<script>
import {getCookie} from '../global/cookie'
import areaOptions from '../global/areaOptions.js'
import roomOptions from '../global/roomOptions.js'
import {tryHideFullScreenLoading } from '../../loading.js'

  export default {
    data() {
      return {
        deptOptions:[],
        deptSelected: '',
        gradeSelected:'',
        termSelected:'',

        tableData:[],

        pageSize:17,
        currentPage:1,
        totalCount:1,

        areaOptions:areaOptions,
        roomOptions:'',

        isOpen:false,

        dialogFormVisible:false,
        form:{
          dept:'',
          grade:'',
          areaSelected:'',
          roomSelected:'',
          cnumber:'',
          crsname:'',
          tnumber:'',
          teaname:''
        },
        dialogUploadVisible: false,
        fileList:[]
      }
    },
    computed:{
      gradeOptions(){
        let myData = new Date()
        var year1 = myData.getFullYear()
        let month1 = myData.getMonth()
        var n = 4
        var options = []
          if(month1<8){
            n=5
          }
          for(var i=0;i<n;i++){
            options[i] = {
              grade:year1,
              label:year1+'级'
            }
              year1--
          }
        return options
      },
      termOptions(){
        let myData = new Date()
        var year1 = myData.getFullYear()
        let month1 = myData.getMonth()
        var options = []
        if(month1<8){
          options[0] = {
            term:year1+"2",
            label:year1+'-'+(year1+1)+'第一学年'
          }
          options[1] = {
            term:year1+"1",
            label:(year1-1)+'-'+year1+'第二学年'
          }
        }else{
          options[0] = {
            term:(year1+1)+"1",
            label:year1+'-'+(year1+1)+'第二学年'
          }
          options[1] = {
            term:year1+"2",
            label:year1+'-'+(year1+1)+'第一学年'
          }
        }
        return options
      }
    },

    methods:{
      getDptName(){
        this.$axios
        .post('/dpt/getDptName', {})
        .then((result)=> {
            if (result.data.code === 1) {//返回第一页数据，和
              this.deptOptions = result.data.datas
            }else{
              return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },

      selectOk(){
        if(this.deptSelected==""||this.gradeSelected==""||this.termSelected==""){
          this.$message({
            type: 'error',
            message: '请检查输入!'
          });
          return;
        }
        this.$axios
        .post('/admin/arrange/getCrsArrangeData', {
          dpt: this.deptSelected,
          grade: this.gradeSelected,
          term: this.termSelected
        })
        .then((result)=> {
            if (result.data.code === 1) {//返回第一页数据，和
              this.tableData = result.data.datas
              this.totalCount = result.data.datas.length
              this.currentPage = 1
            }else{
              return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },

      submitUpload() {
        this.$refs.upload.submit();
      },
      changeMe(file,fileList){
        this.fileList=fileList;
      },

      openOrClose(){

      },
      loadButton(){
        this.$axios
        .post('/admin/arrange/loadButton', {})
        .then((result)=> {
          if (result.data.code === 1) {
            this.isOpen = true
          }else if(result.data.code === -1){
            this.isOpen = false
          }else{
            return false;
          }
        })
        .catch((error)=> {
            alert(error)
        })
      },

      handleDelete(){

      },
      handleEdit(index,row){
        this.form.dept = this.tableInfo.dept
        this.form.grade = this.tableInfo.grade
        this.form.cnumber = row.cnumber
        this.form.crsname = row.crsname
        this.dialogFormVisible = true
      },
      editOk(){

      },
     
      handleCurrentChange(val) {
        this.currentPage=val
      },
  },
  created(){
    this.selectOk()
    this.getDptName()
    this.loadButton()
  }
}
</script>
<template>
<div style="width:1100px">
  <el-row type="flex" style="margin-bottom:5px">
    <el-col :span="4">
      <el-select v-model="deptSelected" placeholder="请选择学院" style="width:150px" size="small">
            <el-option
            v-for="item in deptOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id">
            </el-option>
        </el-select>
    </el-col>
    <el-col :span="4">
        <el-select v-model="gradeSelected" placeholder="请选择年级" style="width:150px" size="small">
            <el-option
            v-for="item in gradeOptions"
            :key="item.grade"
            :label="item.label"
            :value="item.grade">
            </el-option>
        </el-select>
    </el-col>
    <el-col :span="4">
      <el-select v-model="termSelected" placeholder="请选择学年" style="width:150px" size="small">
        <el-option
          v-for="item in termOptions"
          :key="item.term"
          :label="item.label"
          :value="item.term">
        </el-option>
      </el-select>
    </el-col>
    <el-col :span="6">
      <el-button type="primary" plain size="small" @click="selectOk">查询</el-button>
      <el-button type="primary" plain size="small" @click="dialogUploadVisible = true" icon="el-icon-folder-add">导入课程安排</el-button>
    </el-col>
    <el-col :span="4">
    </el-col>
          <el-button type="danger" size="small"  :style="{display: this.visible}" @click="openOrClose">{{this.isOpen==true?"结束选课":"开启选课"}}</el-button>

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
    <el-table-column type="index" label="序号" width="49">
    </el-table-column>
    <el-table-column prop="courseno" label="课程编号" width="80">
    </el-table-column>
    <el-table-column prop="type" label="课程类型" width="100">
    </el-table-column>
    <el-table-column prop="courseName" label="课程名称" width="180">
    </el-table-column>
    <el-table-column prop="hours" label="学时" width="50">
    </el-table-column>
    <el-table-column prop="credit" label="学分" width="50">
    </el-table-column>
    <el-table-column prop="area,room" label="地点" width="200">
      <template slot-scope="scope"> {{scope.row.area}}{{scope.row.room}} </template>
    </el-table-column>
    <el-table-column prop="teacherName" label="任课教师" width="130">
    </el-table-column>
    <el-table-column prop="total" label="人数" width="50">
    </el-table-column>
    <el-table-column fixed="right" prop="operate" label="操作" width="200">
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
    <el-form-item label="年级学年" label-width="80px">
      <el-select v-model="form.grade" placeholder="请选择年级" style="width:180px">
        <el-option v-for="item in gradeOptions" :key="item.grade" :label="item.label" :value="item.grade"> </el-option>
      </el-select>
      <el-select v-model="form.term" placeholder="请选择学年" style="width:180px;margin-left:10px">
        <el-option v-for="item in termOptions" :key="item.term" :label="item.label" :value="item.term"> </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="上课地点" label-width="80px">
      <el-select v-model="form.area" placeholder="请选择区域" @change="getAreaSelected" style="width:180px">
        <el-option v-for="item in areaOptions" :key="item.area" :label="item.label" :value="item.area"></el-option>
      </el-select>
      <el-select v-model="form.room" placeholder="请选择教室" style="width:180px;margin-left:10px">
        <el-option v-for="item in roomOptions" :key="item.room" :label="item.label" :value="item.room"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="上课时间" label-width="80px">
      <el-select v-model="form.week" placeholder="请选择星期" style="width:180px">
          <el-option label="星期一" value="Mon"></el-option>
          <el-option label="星期二" value="Tues"></el-option>
          <el-option label="星期三" value="Wed"></el-option>
          <el-option label="星期四" value="Thur"></el-option>
          <el-option label="星期五" value="Fri"></el-option>
      </el-select>
      <el-select v-model="form.time" placeholder="请选择时间" style="width:180px;margin-left:10px">
          <el-option label="第一节课（上午）" value="0"></el-option>
          <el-option label="第二节课（上午）" value="1"></el-option>
          <el-option label="第三节课（下午）" value="3"></el-option>
          <el-option label="第四节课（下午）" value="4"></el-option>
          <el-option label="第五节课（晚上）" value="6"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="任课教师" label-width="80px">
      <el-input v-model="form.teacherno" style="width:180px"></el-input>
    </el-form-item>
    <el-form-item label="课程编号" label-width="80px">
      <el-input v-model="form.courseno" style="width:180px"></el-input>
    </el-form-item>
    <el-form-item label="最大人数" label-width="80px" type="number">
      <el-input v-model="form.total" style="width:180px"></el-input>
    </el-form-item>

  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="editOk">提交</el-button>
  </div>
  </el-dialog>

  <el-dialog title="上传文件" :visible.sync="dialogUploadVisible" :close-on-click-modal=false>
  <el-upload
  class="upload-demo"
  ref="upload"
  multiple="false"
  accept=".xls,.xlsx"
  action="http://47.93.85.24:8080/adminArrange/arrange/uploadCrsArrange"
  with-credentials="true" 
  :on-success="handleAvatarSuccess"
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
        roomOptions:'',

        tableData:[],
        tableInfo:{
          term:'',
          grade:''
        },

        pageSize:17,
        currentPage:1,
        totalCount:1,

        areaOptions:areaOptions,
        roomOptions:'',

        isOpen:false,
        visible:'inline',

        dialogFormVisible:false,
        editIndex:0,
        form:{
          id:'',
          grade:'',
          term:'',
          teacherno:'',
          courseno:'',
          area:'',
          room:'',
          week:'',
          time:'',
          total:''
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
            label:year1+'-'+(year1+1)+'第一学期'
          }
        }else{
          options[0] = {
            term:(year1+1)+"1",
            label:year1+'-'+(year1+1)+'第二学期'
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
      getAreaSelected(){
        this.$axios
        .post('/spot/getRoomByArea', { 
          area:this.areaSelected
        })
        .then((result)=> {
          this.form.roomSelected = ""
          this.roomOptions = result.data.datas
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
        this.tableInfo.term = this.termSelected
        this.tableInfo.grade = this.gradeSelected
        this.$axios
        .post('/adminArrange/arrange/getCrsArrangeData', {
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
      handleAvatarSuccess(response, file, fileList){
        var success = response.datas.success
        var total = response.datas.totalNum
        var failed = response.datas.failed
        alert("导入成功，共添加"+total+"条，成功"+success+"条，失败"+failed+"条");
        this.selectOk()
      },
      
      openOrClose(){
        this.$confirm('确定'+(this.isOpen==true?"结束选课":"开启选课")+'吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios
          .post('/adminArrange/arrange/openOrCloseStuSelect', {})
          .then((result)=> {
            if (result.data.code === 1) {
              this.$message({
                type: 'success',
               message: result.data.msg
              });
              this.isOpen = !this.isOpen
            }else{
              return false;
            }
          })
        }).catch(() => {});
      },
      loadButton(){
        this.$axios
        .post('/adminArrange/arrange/loadButton', {})
        .then((result)=> {
          if (result.data.code === 1) {
            this.isOpen = result.data.datas.isOpen==1? true: false
            this.visible = result.data.datas.isSuper==1? 'inline':'none'
          }else{
            return false;
          }
        })
        .catch((error)=> {
            alert(error)
        })
      },

      handleDelete(index,row){
        this.$confirm('确定删除'+row.teacherName+'、'+row.courseName+'吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios
          .post('/adminArrange/arrange/deleteCrsArrange', {
            id:row.id
          })
          .then((result)=> {
            if (result.data.code === 1) {
              this.tableData.splice(index, 1)
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
            }
          }).catch((error)=> {
            alert(error)
          })
        }).catch(() => {});
      },
      handleEdit(index,row){
        this.form.id = row.id
        this.form.grade = this.tableInfo.grade
        this.form.term =this.tableInfo.term
        this.form.courseno = row.courseno
        this.form.teacherno = row.teacherno
        this.form.area = row.area
        this.form.room = row.room
        this.form.week = row.week
        this.form.time = row.time
        this.form.total = row.total
        this.editIndex = index
        this.dialogFormVisible = true
      },
      editOk(){
        this.$axios
        .post('/adminArrange/arrange/editCrsArrange', this.form)
        .then((result)=> {
          if (result.data.code === 1) {
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
            var i = this.editIndex
            if(this.form.grade!=this.tableInfo.grade||this.form.term!=this.tableInfo.term){
              this.tableData.splice(i, 1)
            }else{
              this.tableData[i].grade = this.form.grade
              this.tableData[i].term =this.form.term
              this.tableData[i].courseno = this.form.courseno
              this.tableData[i].teacherno = this.form.teacherno
              this.tableData[i].area = this.form.area
              this.tableData[i].room = this.form.room
              this.tableData[i].week = this.form.week
              this.tableData[i].time = this.form.time
              this.tableData[i].total = this.form.total
            }
            this.dialogFormVisible = false
          }else{
            return false
          }
        })
        .catch((error)=> {
            alert(error)
        })
      },
     
      handleCurrentChange(val) {
        this.currentPage=val
      },
  },
  created(){
    this.getDptName()
    this.loadButton()
  }
}
</script>
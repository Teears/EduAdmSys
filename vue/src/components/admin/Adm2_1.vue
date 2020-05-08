<template>
<div style="width:1100px">
    <el-row type="flex" style="margin-bottom:5px">
    <el-col :span="4">
      <el-select v-model="deptSelected" @change="getDeptSelected" placeholder="请选择学院" style="width:150px" size="small">
            <el-option
            v-for="item in deptOptions"
            :key="item.dept"
            :label="item.label"
            :value="item.dept">
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
      <el-button type="danger" size="small" @click="release" icon="el-icon-check">发布</el-button>
      <el-button type="danger" size="small" @click="stop">结束选课</el-button>
    </el-col>
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
    <el-table-column prop="cnumber" label="课程编号" width="100">
    </el-table-column>
    <el-table-column prop="type" label="课程类型" width="150">
    </el-table-column>
    <el-table-column prop="crsname" label="课程名称" width="200">
    </el-table-column>
    <el-table-column prop="hours" label="学时" width="50">
    </el-table-column>
    <el-table-column prop="credit" label="学分" width="50">
    </el-table-column>
    <el-table-column prop="port" label="上课地点" width="200">
    </el-table-column>
    <el-table-column prop="teaname" label="任课教师" width="150">
    </el-table-column>
    <el-table-column prop="total" label="最大选课人数" width="130">
    </el-table-column>
    <el-table-column fixed="right" prop="operate" label="操作" width="100">
      <template slot-scope="scope">
          <el-button size="mini" plain type="primary"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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
    <el-button type="primary" @click="dialogOk">提交</el-button>
  </div>
  </el-dialog>
</div>
</template>

<script>
import {getCookie} from '../global/cookie'
import deptOptions from '../global/deptOptions'
import areaOptions from '../global/areaOptions.js'
import roomOptions from '../global/roomOptions.js'
  export default {
    data() {
      return {
        deptOptions:deptOptions,
        deptSelected: '',
        //computed-gradeOptions
        gradeSelected:'',

        tableData:[],
        tableInfo:{},

        pageSize:17,
        currentPage:1,
        totalCount:1,
        loading:false,

        areaOptions:areaOptions,
        allRoomOptions:roomOptions,
        roomOptions:'',

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
        }
      }
    },
    computed:{
      gradeOptions(){
        let myData = new Date()
        var year1 = myData.getFullYear()
        let month1 = myData.getMonth()
        var options = []
          if(month1<8){
            year1--
          }
          for(var i=0;i<4;i++){
            options[i] = {
              grade:year1,
              label:year1+'级'
            }
              year1--
          }
        return options
      }
    },
    methods:{
      getDeptSelected(){
        if(tihs.deptSelected===''){
          return
        }
        this.getTableData()
      },
      getGradeSelected(){
        if(this.gradeSelected===''){
          return
        }
        this.getTableData()
      },
      handleEdit(index,row){
        this.form.dept = this.tableInfo.dept
        this.form.grade = this.tableInfo.grade
        this.form.cnumber = row.cnumber
        this.form.crsname = row.crsname
        this.dialogFormVisible = true
      },
      release(){

      },
      dialogOk(){

      },
      getTableData(){
        var tableInfo = {
          dept:this.deptSelected,
          grade:this.gradeSelected
        }
        this.$axios
        .post('/api/getPKTableData', {
         tableInfo
        })
        .then((result)=> {
            if (result.data.code === 1) {//返回第一页数据，和
              this.tableData = result.data.datas
              this.totalCount = result.data.datas.length
              this.currentPage = 1
              this.tableInfo = tableInfo
            }else{
              return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },
      handleCurrentChange(val) {
        this.currentPage=val
      },
      getAreaSelected(){
        for(var key in this.allRoomOptions){
          if(key == this.form.areaSelected){
            this.roomOptions = this.allRoomOptions[key]
            return
          }
        }
      }
    },
    created(){
      this.getTableData()
    }
  }
</script>
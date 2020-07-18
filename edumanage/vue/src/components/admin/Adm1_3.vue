<template>
  <div style="width:1100px" class="shadow">
  <el-row type="flex" class="row-bg" justify="space-between" style="margin-bottom:5px">
    <el-select v-model="deptSelected" @change="deptSelect" placeholder="请选择学院" style="width:150px" size="small">
        <el-option
          v-for="item in deptOptions"
          :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>
  <el-col :span="15">
    <el-input v-model="search" placeholder="请输入搜索内容"  size="small">
        <el-button slot="append" icon="el-icon-search" @click="searchOk">搜索</el-button>
    </el-input>
  </el-col>
     <el-button type="primary" size="small" @click="addCrsBtn" icon="el-icon-plus">添加课程</el-button>
     <el-button type="primary" plain size="small" @click="dialogUploadVisible = true" icon="el-icon-folder-add">导入课程</el-button>
  </el-row>

  <div style="background-color:#eff1f2;padding:5px;border-radius: 2px;">
  <el-table
    :data="tables.slice((currentPage-1)*pageSize,currentPage*pageSize)"
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
    <el-table-column prop="id" label="课程编号" width="150">
    </el-table-column>
    <el-table-column prop="name" label="课程名称" width="180">
    </el-table-column>
    <el-table-column prop="type" label="课程类别" width="150">
    </el-table-column>
    <el-table-column prop="department" label="开设学院" width="200">
    </el-table-column>
    <el-table-column prop="hours" label="学时" width="100">
    </el-table-column>
    <el-table-column prop="credit" label="学分" width="100">
    </el-table-column>
    <el-table-column prop="operate" label="操作" width="150">
      <template slot-scope="scope">
        <el-button size="mini" plain type="primary"
        @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button size="mini" plain type="danger"
        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  </div>

  <el-pagination
  background
  layout="prev, pager, next"
  :page-size="pageSize"
  :total="totalCount"
  :current-page="currentPage"
  @current-change="handleCurrentChange"
  style="text-align:center">
  </el-pagination>

  <el-dialog title="新开设课程" :visible.sync="dialogFormVisible" :close-on-click-modal="false" width="30%">
  <el-form :model="form">
    <el-form-item label="课程名称" :label-width="formLabelWidth">
      <el-input v-model="form.name" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="开设院校" :label-width="formLabelWidth">
      <el-select v-model="form.dpt" placeholder="请选择">
      <el-option v-for="item in deptOptions" :key="item.id" :label="item.name" :value="item.id">
          </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="课程类别" :label-width="formLabelWidth">
      <el-select v-model="form.type" placeholder="请选择">
        <el-option value="专业必修"></el-option>
        <el-option value="专业选修"></el-option>
        <el-option value="通识必修"></el-option>
        <el-option value="通识选修"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-col :span="12">
        <el-form-item label="学时" :label-width="formLabelWidth">
          <el-input v-model="form.hours" type="number" autocomplete="off"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="学分" :label-width="formLabelWidth" >
          <el-input v-model="form.credit" type="number" autocomplete="off"></el-input>
        </el-form-item>
      </el-col>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="addCourseData" :style="{display: this.visible1}">提交</el-button>
    <el-button type="primary" @click="editOk" :style="{display: this.visible2}">修改</el-button>
  </div>
</el-dialog>

<el-dialog title="上传文件" :visible.sync="dialogUploadVisible" :close-on-click-modal=false>
  <el-upload
  class="upload-demo"
  ref="upload"
  multiple="false"
  show-file-list="false"
  accept=".xls,.xlsx"
  action="http://47.93.85.24:8080/crsAdmin/upload"
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
  export default {
    data() {
      return {
        deptOptions:[],
        deptSelected: '',
        search: '',

        tableData:[],//目前前端所拥有的所有课程信息

        pageSize:17,
        currentPage:1,
        totalCount:1,

        dialogFormVisible: false,
        form: {
          id:'',
          name: '',
          dpt:'',
          type: '',
          hours: '',
          credit: '',
        },
        formLabelWidth: '80px',
        visible2:'none',
        visible1:'inline',
        isDisabled:false,

        dialogUploadVisible: false,
        fileList:[]
      }
    },
    computed:{
      tables () {
        const search = this.search
        if (search) {
          return this.tableData.filter(data => {
            return Object.keys(data).some(key => {
              return String(data[key]).toLowerCase().indexOf(search) > -1
            })
          })
        }
        return this.tableData
      }
    },
    methods:{
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
      },

      getDptName(){
        this.$axios
        .post('/dpt/getDptName', {})
        .then((result)=> {
            if (result.data.code === 1) {//返回第一页数据，和
              this.deptOptions = result.data.datas
              this.deptSelected = result.data.datas[0].id
            }else{
              return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },
      deptSelect(){
        if(this.gradeSelected == ""){
          return
        }
        this.getTableData()
      },

      //根据条件请求某一页数据
      getTableData(){
        if(this.deptSelected == ""){
          return
        }
        this.$axios
        .post('/crsAdmin/getCourseInfo', { 
          dpt:this.deptSelected
        })
        .then((result)=> {
            if (result.data.code === 1) {//返回第一页数据，和
              this.totalCount = result.data.datas.length
              this.tableData = result.data.datas
            }else{
              alert(result.data.msg)
              return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },

      addCrsBtn(){
        this.form.id = ""
        this.form.name = ""
        this.form.dpt = ""
        this.form.hours = ""
        this.form.type = ""
        this.form.credit = ""
        this.dialogFormVisible = true 
        this.visible2 = 'none'
        this.visible1 = 'inline'
        this.isDisabled=false
      },

      addCourseData(){
        this.$axios
        .post('/crsAdmin/addCourseData', this.form)
        .then((result)=> {
            if (result.data.code === 1) {
              this.$message({
                type: 'success',
                message: '添加成功!'
              });
              this.getTableData()
            }else{
              this.$message({
                type: 'error',
                message: result.data.msg
              });
            }
            this.dialogFormVisible = false
        })
        .catch((error)=> {
            alert(error)
        })
      },

      handleEdit(index,row){
          this.form.id = row.id
          this.form.name = row.name
          this.form.dpt = this.deptSelected
          this.form.hours = row.hours
          this.form.type = row.type
          this.form.credit = row.credit
          this.visible1 = 'none'
          this.visible2 = 'inline'
          this.isDisabled=true
          this.dialogFormVisible = true
      },

      editOk(){
        this.$axios
        .post('/crsAdmin/editCrs', this.form)
        .then((result)=> {
            if (result.data.code == 1) {//返回第一页数据，和
              this.$message({
                type: 'success',
                message: '修改成功!'
              });
              this.getTableData()
            }else{
              this.$message({
                type: 'error',
                message: result.data.msg
              });
            }
            this.dialogFormVisible = false
        })
        .catch((error)=> {
            alert(error)
        })
      },

      handleDelete(index,row){
        this.$confirm('确定删除'+row.name+'吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios
          .post('/crsAdmin/deleteCrs', {
            id:row.id
          })
          .then((result)=> {
            if (result.data.code === 1) {
              this.getTableData()
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
            }else{
              this.$message({
                type: 'error',
                message: result.data.msg
              });
            }
          }).catch((error)=> {
            alert(error)
          })
        }).catch(() => {});
      },

      handleCurrentChange(val) {
        this.currentPage = val
      }
    },
    watch: {
      deptSelected: function () {
        this.$nextTick(function () {
          this.getTableData()
        })
      }
    },
    created(){
      this.getDptName()
    }
  }
</script>
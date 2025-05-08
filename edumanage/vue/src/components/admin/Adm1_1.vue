<template>
  <div style="width:1100px">
  <el-row type="flex" justify="space-between" style="margin-bottom:5px">
    <el-col :span="3">
      <el-select v-model="deptSelected" @change="deptSelect" placeholder="请选择学院" style="width:150px" size="small">
        <el-option
          v-for="item in deptOptions"
          :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>
    </el-col>
    <el-col :span="3">
        <el-select v-model="gradeSelected" @change="gradeSelect" placeholder="请选择年级" style="width:150px" size="small">
            <el-option
            v-for="item in gradeOptions"
            :key="item.grade"
            :label="item.label"
            :value="item.grade">
            </el-option>
        </el-select>
    </el-col>
    <el-col :span="12">
      <el-input v-model="search" placeholder="请输入搜索内容"  size="small">
        <el-button slot="append" icon="el-icon-search" @click="searchOk">搜索</el-button>
      </el-input>
    </el-col>
     <el-button type="primary" size="small" @click="addStuBtn" icon="el-icon-plus">添加学生</el-button>
     <el-button type="primary" plain size="small" @click="dialogUploadVisible = true" icon="el-icon-folder-add">导入学生</el-button>
  </el-row>

  <div style="background-color:#eff1f2;padding:5px;border-radius: 2px;">
  <el-table id="tableId"
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
    <el-table-column prop="id" label="学号" width="110">
    </el-table-column>
    <el-table-column prop="name" label="姓名" width="150">
    </el-table-column>
    <el-table-column prop="status" label="状态" width="60">
    </el-table-column>
    <el-table-column prop="sex" label="性别" width="50">
    </el-table-column>
    <el-table-column prop="classAndGrade" label="班级" width="100">
    </el-table-column>
    <el-table-column prop="department" label="学院" width="180">
    </el-table-column>
    <el-table-column prop="idCard" label="身份证号" width="200">
    </el-table-column>
    <el-table-column prop="birth" label="出生日期" width="110">
    </el-table-column>
    <el-table-column prop="political" label="政治面貌" width="120">
    </el-table-column>
    <el-table-column prop="graduate" label="毕业学校" width="200">
    </el-table-column>
    <el-table-column prop="telephone" label="电话号码" width="120">
    </el-table-column>
    <el-table-column fixed="right" prop="operate" label="操作" width="150">
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

   <el-dialog title="新增学生档案" :visible.sync="dialogFormVisible" :close-on-click-modal=false width="50%">
  <el-form :model="form">
    <el-form-item>
      <el-col :span="10">
        <el-form-item label="学号" :label-width="formLabelWidth">
          <el-input v-model="form.id" autocomplete="off" :disabled="isDisabled" maxlength="10" show-word-limit></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="14">
        <el-form-item label="姓名" :label-width="formLabelWidth" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-col :span="10">
        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-select v-model="form.sex" placeholder="请选择">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="14">
        <el-form-item label="毕业院校" :label-width="formLabelWidth" >
          <el-input v-model="form.graduate" autocomplete="off"></el-input>
        </el-form-item>
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-col :span="10">
        <el-form-item label="出生日期" :label-width="formLabelWidth" >
          <el-date-picker v-model="form.birth" type="date" placeholder="选择日期" value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
      </el-col>
      <el-col :span="14">
        <el-form-item label="身份证号" :label-width="formLabelWidth" >
          <el-input v-model="form.idCard" autocomplete="off"></el-input>
        </el-form-item>
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-col :span="10">
        <el-form-item label="政治面貌" :label-width="formLabelWidth">
          <el-select v-model="form.political" placeholder="请选择">
            <el-option value="群众"></el-option>
            <el-option value="共青团员"></el-option>
            <el-option value="入党积极分子"></el-option>
            <el-option value="共产党员"></el-option>
            <el-option value="其他党派"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="14">
        <el-form-item label="电话号码" :label-width="formLabelWidth" >
          <el-input v-model="form.telephone" autocomplete="off"></el-input>
        </el-form-item>
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-col :span="8">
        <el-form-item label="学院" :label-width="formLabelWidth">
          <el-select v-model="form.department" placeholder="请选择">
          <el-option
            v-for="item in deptOptions" :key="item.id" :label="item.name" :value="item.id">
          </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="班级" :label-width="formLabelWidth">
          <el-select v-model="form.classAndGrade" placeholder="请选择">
          <el-option
              v-for="item in classOptions" :key="item.classId" :label="item.classId" :value="item.classId">
              </el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="状态" :label-width="formLabelWidth" >
          <el-select v-model="form.status" placeholder="请选择">
            <el-option value="毕业"></el-option>
            <el-option value="在校"></el-option>
            <el-option value="休学"></el-option>
            <el-option value="退学"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="addStudentData" :style="{display: this.visible1}">提交</el-button>
    <el-button type="primary" @click="editOk" :style="{display: this.visible2}">修改</el-button>
  </div>
</el-dialog>

<el-dialog title="上传文件" :visible.sync="dialogUploadVisible" :close-on-click-modal=false>
  <el-upload
  class="upload-demo"
  ref="upload"
  multiple="false"
  accept=".xls,.xlsx"
  action="http://47.93.85.24:8080/stuAdmin/upload"
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
import FileSaver from 'file-saver'
import XLSX from 'xlsx'
  export default {
    data() {
      return {
        deptOptions:[],
        deptSelected: '',
        gradeSelected:'',
        classOptions:'',
        search: '',

        tableData:[],//目前前端所拥有的所有课程信息

        pageSize:17,
        currentPage:1,
        totalCount:1,

        dialogFormVisible: false,
        form: {
          id:'',
          name: '',
          sex:'',
          graduate:'',
          birth:'',
          idCard: '',
          political:'',
          telephone:'',
          department:'',
          classAndGrade:'',
          status:''
        },
        formLabelWidth: '80px',
        visible2:'none',
        visible1:'inline',
        isDisabled:false,
        editIndex:0,

        dialogUploadVisible: false,
        fileList:[]
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
            for(var i=0;i<6;i++){
                options[i] = {
                    grade:year1,
                    label:year1+'级'
                }
                year1--
            }
            return options
        },
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
      gradeSelect(){
        if(this.deptSelected == ""){
          return
        }
        this.getTableData()
      },
      getTableData(){
        if(this.deptSelected == ""||this.gradeSelected == ""){
          return
        }
        this.$axios
        .post('/stuAdmin/getStuData', { 
          dpt:this.deptSelected,
          grade:this.gradeSelected,
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


      getAllClass(){
        this.$axios
        .post('/stuAdmin/getAllClass', {})
        .then((result)=> {
            if (result.data.code === 1) {//返回第一页数据，和
              this.classOptions = result.data.datas
            }else{
              return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },

      addStuBtn(){
        this.form.id = ""
        this.form.name = ""
        this.form.sex = ""
        this.form.graduate = ""
        this.form.birth = ""
        this.form.idCard = ""
        this.form.political = ""
        this.form.telephone = ""
        this.form.department = ""
        this.form.classAndGrade = ""
        this.form.status = ""
        this.dialogFormVisible = true 
        this.visible2 = 'none'
        this.visible1 = 'inline'
        this.isDisabled=false
      },

      addStudentData(){
        this.$axios
        .post('/stuAdmin/insertStudent', this.form)
        .then((result)=> {
            if (result.data.code === 1) {//返回第一页数据，和
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

      handleEdit(index,row){
          this.form.id = row.id
          this.form.name = row.name
          this.form.sex = row.sex
          this.form.graduate = row.graduate
          this.form.birth = row.birth
          this.form.idCard = row.idCard
          this.form.political = row.political
          this.form.telephone = row.telephone
          this.form.department = this.deptSelected
          this.form.classAndGrade = row.classAndGrade
          this.form.status = row.status
          this.visible1 = 'none'
          this.visible2 = 'inline'
          this.isDisabled=true
          this.dialogFormVisible = true
      },
      editOk(){
        this.$axios
        .post('/stuAdmin/editStu', this.form)
        .then((result)=> {
            if (result.data.code === 1) {//返回第一页数据，和
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
          .post('/stuAdmin/deleteStu', {
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
      this.getAllClass()
      this.gradeSelected = this.gradeOptions[2].grade
      this.getDptName()
    }
  }
</script>
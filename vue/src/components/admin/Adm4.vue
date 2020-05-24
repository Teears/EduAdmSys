<template>
  <div style="width:1100px">
  <div style="background-color:#eff1f2;padding:5px;border-radius: 2px;">
  <el-row type="flex" class="row-bg" justify="space-between" style="margin-bottom:5px">
    <el-col :span="22">
    <el-input v-model="search" placeholder="请输入搜索内容"  size="mini">
        <el-button slot="append" icon="el-icon-search"></el-button>
    </el-input>
    </el-col>
    <el-button size="mini" type="success"  @click="dialogFormVisible = true" icon="el-icon-plus" round>添加</el-button>
  </el-row>
  
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
    <el-table-column prop="uid" label="工号" width="150">
    </el-table-column>
    <el-table-column prop="teaName" label="姓名" width="150">
    </el-table-column>
    <el-table-column prop="department" label="学院" width="230">
    </el-table-column>
    <el-table-column prop="telephone" label="手机号" width="200">
    </el-table-column>
    <el-table-column prop="name" label="权限" width="100">
    </el-table-column>
    <el-table-column prop="operate" label="操作" width="200">
      <template slot-scope="scope">
        <el-col :span="12">
          <el-button size="mini" plain type="primary" @click="handleUp(scope.$index, scope.row)" :style="{display: (scope.row.name==='管理员')? 'inline':'none'}" >提权</el-button>
        </el-col>
        <el-col :span="12">
          <el-button size="mini" plain type="primary" @click="handleDown(scope.$index, scope.row)" :style="{display: (scope.row.name==='超级管理员')? 'inline':'none'}">降权</el-button>
        </el-col>
        <el-col :span="12">
          <el-button size="mini" plain type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </el-col>
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

  <el-dialog title="新增管理员" :visible.sync="dialogFormVisible" :close-on-click-modal=false width="30%">
    <el-form :model="form">
        <el-form-item label="工号" :label-width="formLabelWidth">
          <el-input v-model="form.uid" autocomplete="off"></el-input>
        </el-form-item>

      <el-form-item label="权限" :label-width="formLabelWidth">
        <el-select v-model="form.name" placeholder="请选择">
          <el-option label="管理员" value="admin"></el-option>
          <el-option label="超级管理员" value="super_admin"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="addData">提交</el-button>
    </div>
  </el-dialog>

</div>
</template>

<script>
import {getCookie} from '../global/cookie'
  export default {
    data() {
      return {
        tableData:[],

        search: '',

        pageSize:20,
        currentPage:1,
        totalCount:1,
        loading:false,

        dialogFormVisible: false,
        formLabelWidth: '80px',
        form: {
          name: '',
          uid:''
        }
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
      addData(){
        this.$axios
        .post('/adminAuthorize/authorize/addAdmin', {
          uid:this.form.uid,
          name:this.form.name
        })
        .then((result)=> {
            if (result.data.code === 1) {
              this.tableData.unshift(result.data.datas)
              this.dialogFormVisible = false
              this.$message({
                type: 'success',
                message: '添加成功!'
              });
            }else{
              this.$message({
                type: 'info',
                message: '该教师不存在或已经是管理员!'
              });
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },
      handleDelete(index,row){
        this.$confirm('确定删除'+row.teaName+'吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios
          .post('/adminAuthorize/authorize/deleteByUid', {
            id:row.uid
          })
          .then((result)=> {
            if (result.data.code === 1) {
              for(var i = 0; i < this.tableData.length;i++){
                if(this.tableData[i].uid===row.uid){
                  this.tableData.splice(i, 1)
                  break;
                }
              }
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
            }else{
              alert(result.data.msg)
              return false;
            }
          })
          .catch((error)=> {
            alert(error)
          })
        }).catch(() => {});
      },
      handleUp(index,row){
        this.$axios
        .post('/adminAuthorize/authorize/upToSuperAdmin', {
          id:row.uid
        })
        .then((result)=> {
            if (result.data.code === 1) {
              row.name = "超级管理员"
            }else{
              alert(result.data.msg)
              return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },
      handleDown(index,row){
        this.$axios
        .post('/adminAuthorize/authorize/upToAdmin', {
          id:row.uid
        })
        .then((result)=> {
            if (result.data.code === 1) {
              row.name = "管理员"
            }else{
              alert(result.data.msg)
              return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },
      getTableData(){
        this.$axios
        .post('/adminAuthorize/authorize', {})
        .then((result)=> {
            if (result.data.code === 1) {
              this.totalCount=result.data.datas.length
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
      handleCurrentChange(val) {
        this.currentPage=val
      }
    },
    created(){
        this.getTableData()
    }
  }
</script>
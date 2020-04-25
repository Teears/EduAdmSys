<template>
  <div style="width:1100px">
  <div style="background-color:#eff1f2;padding:5px;border-radius: 2px;">
  <el-row type="flex" class="row-bg" justify="space-between" style="margin-bottom:5px">
    <el-col :span="22">
    <el-input v-model="search" placeholder="请输入搜索内容"  size="mini">
        <el-button slot="append" icon="el-icon-search"></el-button>
    </el-input>
    </el-col>
    <el-button size="mini" type="success" @click="addData" icon="el-icon-plus" round>添加</el-button>
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
    <el-table-column prop="userid" label="工号" width="150">
    </el-table-column>
    <el-table-column prop="name" label="姓名" width="200">
    </el-table-column>
    <el-table-column prop="dept" label="学院" width="230">
    </el-table-column>
    <el-table-column prop="tel" label="手机号" width="200">
    </el-table-column>
    <el-table-column prop="superAdmin" label="超级管理员" width="100">
    </el-table-column>
    <el-table-column prop="operate" label="操作" width="150">
      <template slot-scope="scope">
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
        loading:false
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

        },
        handleDelete(){

        },
        getTableData(){
        var tableInfo = {
            userid: getCookie("userid"),
            identify: getCookie("identify"),
            superAdmin:getCookie("superAdmin"),
            dept:getCookie("dept")
        }
        this.$axios
        .post('/api/getAdminTableData', { //获取查询学生名单接口
            tableInfo
        })
        .then((result)=> {
            if (result.data.code === 1) {
              this.totalCount=result.data.datas.length
              this.tableData = result.data.datas
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
      }
    },
    created(){
        this.getTableData()
    }
  }
</script>
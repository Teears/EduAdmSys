<template>
  <div style="width:1100px">
  <el-row type="flex" class="row-bg" justify="space-between" style="margin-bottom:5px">
  <el-col :span="7">
    <label>学期：</label>
    <el-select v-model="termSelected" placeholder="请选择学期" @change="getTermSelected" style="width:180px" size="small">
    <el-option
      v-for="item in termOptions"
      :key="item.term"
      :label="item.label"
      :value="item.term">
    </el-option>
    </el-select>
  </el-col>
  <el-col :span="7">
    <label>班级：</label>
    <el-select v-model="classSelected" placeholder="请选择班级" @change="getClassSelected" style="width:180px" size="small">
    <el-option
      v-for="item in classOptions"
      :key="item.class"
      :label="item.label"
      :value="item.class">
    </el-option>
    </el-select>
  </el-col>
  <el-col :span="7">
    <label>课程：</label>
    <el-select v-model="crsSelected" placeholder="请选择课程" @change="getCrsSelected" style="width:180px" size="small">
    <el-option
      v-for="item in crsOptions"
      :key="item.crs"
      :label="item.label"
      :value="item.crs">
    </el-option>
    </el-select>
  </el-col>
     <el-button type="primary" size="small" @click="selectOk">查询</el-button>
     <el-button type="primary" plain size="small" @click="exportExcel">导出</el-button>
  </el-row>

  <div style="background-color:#eff1f2;padding:5px;border-radius: 2px;">
  <el-row type="flex" class="row-bg" justify="space-between" style="margin-bottom:5px">
    <el-col :span="20">
      <el-input v-model="search" placeholder="请输入搜索内容"  size="mini">
        <el-button slot="append" icon="el-icon-search"></el-button>
      </el-input>
    </el-col>
      <el-button-group>
        <el-button size="mini" type="primary" icon="el-icon-edit" @click="show = true" round></el-button>
        <el-button size="mini" type="primary" icon="el-icon-check" @click="show = false" round></el-button>
      </el-button-group>
      <el-button size="mini" type="success" @click="postListData" round>提交</el-button>
  </el-row>
  
  <el-table id="classListTable"
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
    <el-table-column prop="number" label="学号" width="200">
    </el-table-column>
    <el-table-column prop="class" label="班级" width="150">
    </el-table-column>
    <el-table-column prop="name" label="姓名" width="300">
    </el-table-column>
    <el-table-column prop="grade" label="成绩" width="200">
      <template slot-scope="scope">
        <el-input size="mini" type="number" v-show="show" v-model="scope.row.grade"></el-input>
        <span v-show="!show">{{scope.row.grade}}</span>
      </template>
    </el-table-column>
    <el-table-column prop="tag" label="标签" width="170">
        <template slot-scope="scope">
          <el-tag size="mini"
          :type="(scope.row.grade==='')? 'info':(scope.row.grade >= 60) ? 'success' : 'danger'"
          disable-transitions>{{(scope.row.grade==='')? '未录入':(scope.row.grade >= 60)?'合格':'不合格'}}</el-tag>
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
import termOptions from '../global/termOptions.js'
import FileSaver from 'file-saver'
import XLSX from 'xlsx'
  export default {
    data() {
      return {
        termOptions:termOptions,
        termSelected: '',
        classOptions:'',
        classSelected:'',
        crsOptions:'',
        crsSelected:'',

        classListTableData:[],
        tableInfo:{},
        search: '',
        show: false,

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
          return this.classListTableData.filter(data => {
            return Object.keys(data).some(key => {
              return String(data[key]).toLowerCase().indexOf(search) > -1
            })
          })
        }
        return this.classListTableData
      }
    },
    methods:{
      getTermSelected(){
        this.$axios
        .post('/api/getClassOptions', { //查询该老师某学期所教的班级
            userid: getCookie("userid"),
            identify: getCookie("identify"),
            term: this.termSelected
        })
        .then((result)=> {
            if (result.data.code === 1) {
              this.classOptions=result.data.datas
              this.classSelected = ''
            }else{
                return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },
      getClassSelected(){
        this.crsOptions = this.classOptions[this.classSelected].crs
        this.crsSelected = ''
      },
      postListData(){
        this.$axios
        .post('/api/postStuGrade', { //提交成绩接口
          dataInfo:this.tableInfo,
          datas:this.classListTableData
        })
        .then((result)=> {
            if (result.data.code === 1) {
              alert("成绩更新成功！")
            }else{
              alert("成绩更新失败！")
              return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },
      selectOk(){
        if(this.termSelected===''||this.classSelected===''||this.crsSelected===''){
          alert("请检查查询条件")
          return
        }
        var tableInfo = {
            userid: getCookie("userid"),
            identify: getCookie("identify"),
            term: this.termSelected,
            class: this.classOptions[this.classSelected].label,
            crs: this.crsSelected
        }
        this.$axios
        .post('/api/getClassListTableData', { //获取查询学生名单接口
            tableInfo
        })
        .then((result)=> {
            // result.data = JSON.stringify(result.data)
            // result.data = JSON.parse(result.data)
            if (result.data.code === 1) {
              this.totalCount=result.data.datas.length
              this.classListTableData = result.data.datas
              this.tableInfo = tableInfo
            }else{
              return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      },
      // exportExcel () {
      //    /* generate workbook object from table */
      //   this.pageSize = this.totalCount
      //   this.currentPage = 1
      //   var wb = XLSX.utils.table_to_book(document.querySelector('#classListTable'))
      //    /* get binary string as output */
      //   var wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' })
      //   try {
      //       FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), 'studentlist.xlsx')
      //   } catch (e) { if (typeof console !== 'undefined') console.log(e, wbout) }
      //   this.pageSize = 20
      //   return wbout
      // },
      exportExcel(){
        require.ensure([], () => {
          const { export_json_to_excel } = require('../../excel/Export2Excel');
          const tHeader = [ '学号', '班级', '姓名','成绩'];
          const filterVal = ['number', 'class', 'name','grade'];
          const list = this.classListTableData;
          const data = this.formatJson(filterVal, list);
          export_json_to_excel(tHeader, data, 'studentlist');
        })
      },
      formatJson(filterVal, jsonData){
        return jsonData.map(v => filterVal.map(j => v[j]))
      },
      handleCurrentChange(val) {
        this.currentPage=val
      },
    }
  }
</script>
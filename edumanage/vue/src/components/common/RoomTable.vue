<template>
<div style="width:1101px">
  <el-row type="flex" class="row-bg" justify="space-between" style="margin-bottom:10px">
  <el-col :span="7">
    <label>学期：</label>
    <el-select v-model="termSelected" placeholder="请选择学期" @change="getTermSelected" style="width:180px">
    <el-option
      v-for="item in termOptions"
      :key="item.term"
      :label="item.label"
      :value="item.term">
    </el-option>
    </el-select>
  </el-col>
  <el-col :span="7">
    <label>区域：</label>
    <el-select v-model="areaSelected" placeholder="请选择区域" @change="getAreaSelected" style="width:180px">
    <el-option
      v-for="item in areaOptions"
      :key="item.area"
      :label="item.area"
      :value="item.area">
    </el-option>
    </el-select>
  </el-col>
  <el-col :span="7">
    <label>教室：</label>
    <el-select v-model="roomSelected" placeholder="请选择教室" @change="getRoomSelected" style="width:180px">
    <el-option
      v-for="item in roomOptions"
      :key="item.room"
      :label="item.room"
      :value="item.room">
    </el-option>
    </el-select>
  </el-col>
  <el-col :span="2">
     <el-button type="primary" @click="selectOk">查询</el-button>
  </el-col>
  </el-row>
  <el-table
    :data="roomTableData"
    border
    style="width: 100%"
    :row-class-name="tableRowClassName">
    <el-table-column label="教室课表">
    <el-table-column
      prop="time"
      label=""
      width="100">
    </el-table-column>
    <el-table-column
      prop="Mon"
      label="星期一"
      width="200">
    </el-table-column>
    <el-table-column
      prop="Tues"
      label="星期二"
      width="200">
    </el-table-column>
    <el-table-column
      prop="Wed"
      label="星期三"
      width="200">
    </el-table-column>
    <el-table-column
      prop="Thur"
      label="星期四"
      width="200">
    </el-table-column>
    <el-table-column
      prop="Fri"
      label="星期五"
      width="200">
    </el-table-column>
    </el-table-column>
  </el-table>
  </div>
</template>

<script>
import termOptions from '../global/termOptions.js'
import roomTable from '../global/roomTable.js'
import areaOptions from '../global/areaOptions.js'
import qs from 'qs'
  export default {
    data() {
      return {
        roomTableData:roomTable,
        termOptions:termOptions,
        termSelected: '',
        areaOptions:areaOptions,
        areaSelected:'',
        roomOptions:'',
        roomSelected:''
      }
    },
    methods:{
      tableRowClassName({row, rowIndex}) {
        if (rowIndex === 2||rowIndex === 5) {
          return 'warning-row';
        }
        return '';
      },
      getAreaSelected(){
        this.$axios
        .post('/spot/getRoomByArea', { 
          area:this.areaSelected
        })
        .then((result)=> {
          this.roomSelected = ""
          this.roomOptions = result.data.datas
        })
        .catch((error)=> {
            alert(error)
        })
      },
      selectOk(){
        if(this.termSelected==''||this.areaSelected==''||this.roomSelected==''){
          alert("请检查查询条件")
          return
        }
        this.$axios
        .post('/spot/getRoomTable', { //获取查询教室课表接口
            term: this.termSelected,
            area: this.areaSelected,
            room: this.roomSelected
        })
        .then((result)=> {
            if (result.data.code === 1) {
              for(var i =0; i < 7; i++){
                if(i==2||i==5) continue;
                this.roomTableData[i].Mon = ""
                this.roomTableData[i].Tues = ""
                this.roomTableData[i].Wed = ""
                this.roomTableData[i].Thur = ""
                this.roomTableData[i].Fri = ""
              }
              var time,week,info
              for(var i = 0; i < result.data.datas.length; i++){
                time = result.data.datas[i].time
                week = result.data.datas[i].week
                info = result.data.datas[i].info
                switch (week) {
                  case "Mon":
                    this.roomTableData[time].Mon = info
                    break
                  case "Tues":
                    this.roomTableData[time].Tues = info
                    break
                  case "Wed":
                    this.roomTableData[time].Wed = info
                    break
                  case "Thur":
                    this.roomTableData[time].Thur = info
                    break
                  case "Fri":
                    this.roomTableData[time].Fri = info
                    break  
                }
              }
            }else{
                console.log("查询失败");
                return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
      }
    }
  }
</script>
<style>
.el-table .warning-row {
    background: #f5f7fa;
}
</style>
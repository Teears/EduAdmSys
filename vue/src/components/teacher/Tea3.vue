<template>
<div style="width:1050px">
  <el-table
    :data="taskTableData"
    style="width: 100%"
    stripe>
    <el-table-column  prop="courseno" label="课程编号" width="100">
    </el-table-column>
    <el-table-column  prop="crsname" label="课程名称" width="200">
    </el-table-column>
    <el-table-column prop="time" label="上课时间" width="150">
    </el-table-column>
    <el-table-column prop="port" label="上课地点" width="200">
    </el-table-column>
    <el-table-column prop="info" label="详情" width="400">
    </el-table-column>
    <!-- <el-table-column prop="docurl" label="下载" width="150">
      <template slot-scope="scope"> 
        <el-link type="primary" :href="scope.row.docurl">下载教学大纲</el-link> 
      </template>
    </el-table-column> -->
  </el-table>
</div>
</template>
<script>
export default {
  data(){
    return{
      taskTableData:[]
    }
  },
  methods: {
    getTaskTable(){
      this.$axios
      .post('/teacher/getTaskTable', {})
      .then((result)=> {
        if (result.data.code == 1) {
          this.taskTableData = result.data.datas
        }
      })
      .catch((error)=> {
        alert(error)
      })
    }
  },
  created(){
    this.getTaskTable()
  }
}
</script>
<template>
<div style="width:1150px">
  <el-table
    :data="infoTableData"
    border
    style="width: 100%">
    <el-table-column label="个人信息">
    <el-table-column
      prop="id"
      label="工号"
      width="100">
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      width="150">
    </el-table-column>
    <el-table-column
      prop="sex"
      label="性别"
      width="100">
    </el-table-column>
    <el-table-column
      prop="tel"
      label="联系电话"
      width="150">
    </el-table-column>
    <el-table-column
      prop="pol"
      label="政治面貌"
      width="150">
    </el-table-column>
    <el-table-column
      prop="title"
      label="职称"
      width="100">
    </el-table-column>
    <el-table-column
      prop="dpt"
      label="系别"
      width="150">
    </el-table-column>
    <el-table-column
      prop="degree"
      label="学历"
      width="100">
    </el-table-column>
    <el-table-column
      prop="workTime"
      label="工作时间"
      width="150">
    </el-table-column>
    </el-table-column>
  </el-table>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        infoTableData:[]
      }
    },
    methods:{
      getInfoTable(){
        this.$axios
        .post('/teacher/getInfoTable', {})
        .then((result)=> {
            if (result.data.code === 1) {
                this.infoTableData = result.data.datas
            }
        })
        .catch((error)=> {
            alert(error)
        })
      }
    },
    created(){
      this.getInfoTable()
    }
  }
</script>
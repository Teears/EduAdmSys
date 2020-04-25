<template>
  <div style="height:80px">
  <div id="header">
    <img id="markpng" src="../../assets/mark.png" width="80px" height="80px">
    <p id="header_p"><span id="header_span">{{schoolName}}</span>教务管理系统</p>
    <div id="topicon">
      <el-button type="text" icon="el-icon-chat-dot-round"></el-button>
      <el-button type="text" icon="el-icon-s-claim"></el-button>
      <el-button type="text" icon="el-icon-info"></el-button>
    </div>
  </div>
  </div>
</template>

<script>
export default {
    data(){
      return{
        schoolName: "XXX学校"
      }
    }
  }
</script>
<style>
  #header{
    height: 60px;
    background-color: #547387;
  }
  #markpng{
    position: absolute;
    left: 65px;
    top: 0px;
  }
  #header_p{
    position: relative;
    left: 135px;
    top:8px;
    font-size: 25px;
    font-weight: bold;
    color: white;
  }
  #header_span{
    font-size: 40px;
    font-weight: bold
  }
  #topicon{
    position: relative;
    text-align: right;
    top: -45px;
    right: 30px;
  }
  .el-button{
    margin-right: 20px;
  }
  .el-icon-s-claim{
    font-size: 16px;
    color: #3c4850;
  }
  .el-icon-s-claim:hover{
    color: #a1c4db;
  }
  .el-icon-chat-dot-round{
    font-size: 16px;
    color: #3c4850;
  }
  .el-icon-chat-dot-round:hover{
    color: #a1c4db;
  }
  .el-icon-info{
    font-size: 16px;
    color: #3c4850;
  }
  .el-icon-info:hover{
    color: #a1c4db;
  }
</style>
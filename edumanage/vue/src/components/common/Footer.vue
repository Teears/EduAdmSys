<template>
  <div id="footer">
    <p>{{copyRight}}</p>
    <p>{{mail}}</p>
  </div>
</template>

<script>
export default {
    data(){
      return{
        copyRight: "Copyright © www.xxx.edu.cn, All Rights Reserved.",
        mail:"Email : 5698401@qq.com  QQ: 5698401"
      }
    }
  }
</script>

<style>
  #footer{
    height: 40px;
    background-color: #547387;
  }
</style>
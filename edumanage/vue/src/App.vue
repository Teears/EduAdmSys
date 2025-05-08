<template>
  <div id="app">  
  <!-- <el-container">
    <el-header height="60px">
      <v-header></v-header>
    </el-header>
    <el-main style="position:absolute;padding:0;margin:0;margin-top:60px;height:100%;width:100%">
      <router-view>   
      </router-view>
    </el-main>
  </el-container"> -->
  <el-container>
  <el-header class="shadow_out">
      <v-header></v-header>
  </el-header>
    <router-view></router-view>
  </el-container>
</div>
</template>

<script>
  import header from './components/common/Header.vue'
  import footer from './components/common/Footer.vue'
  import loginUser from './components/login/LoginUser.vue'

  export default {
    name: 'App',
    components:{
      'v-header':header,
      'v-footer':footer,
      'v-loginUser':loginUser,
    }
  }
</script>

<style>

</style>
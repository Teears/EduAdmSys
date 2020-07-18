<template>
<!-- max-width:170px; min-width:170px;overflow-x:hidden -->
  <div style="margin:20px;overflow-x:hidden">
  <el-menu
      ref="elmenu"
      :default-active="this.$route.path.substring(1)"
      @open="handleOpen"
      @close="handleClose"
      unique-opened
      background-color="#547387"
      text-color="#fff"
      active-text-color="#a1c4db"
      router="true">
      
      <el-submenu v-for="item in menu" :index="item.id" :key="item.id">
        <template slot="title">
          <span v-text="item.name"></span>
        </template>
        <el-menu-item-group v-for="sub in item.sub" :key="sub.componentName">
          <el-menu-item :index="sub.componentName" v-text="sub.name">
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>

    </el-menu>
  </div>
</template>

<script>
import admMenu from '../global/admMenu.config.js'
import {getCookie} from '../global/cookie';
  export default {
    data(){
      return{
        menu:admMenu,
        superMenu:{
          name: '授权中心',
          id: '4',
          sub: [{
            name: '授权中心',
            componentName: 'Adm4'
          }]
        }
      }
    },
    methods: {
      isSuperAdmin(){
        if(getCookie("perm") === '2'){
          this.menu[3] = this.superMenu
        }
      }
    },
    created(){
      this.isSuperAdmin()
    }
  }
</script>
<style>
.el-menu-collapse .el-menu .el-submenu, .el-menu--popup {
  min-width: 260px;
}
</style>

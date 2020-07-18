<template>
  <div style="margin:20px;overflow-x:hidden">
  <el-menu
      ref="elmenu"
      :default-active="this.$route.path.substring(1)"
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
import stuMenu from '../global/stuMenu.config.js'
  export default {
    data(){
        return{
            menu:stuMenu
        }
    }
  }
</script>
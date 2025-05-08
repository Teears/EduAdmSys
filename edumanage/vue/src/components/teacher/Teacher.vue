<template>
<el-container>
    <el-aside width="240px">
        <v-leftMenuTea></v-leftMenuTea>
    </el-aside>
    <el-main class="shadow_in_img" style="margon-top:10px;height:700px"  :style="{backgroundImage: 'url(' + backImg + ')' }">
        <el-row type="flex" justify="center"><el-col :span="21">
            <router-view></router-view>
        </el-col></el-row>
    </el-main>
</el-container>
</template>

<script>
import {getCookie} from '../global/cookie'
import leftMenuTea from './LeftMenuTea.vue'
import backImg from '@/assets/back.jpg'
export default {
    data(){
        return{
            backImg
        }
    },
    components:{
        'v-leftMenuTea': leftMenuTea
    },
    created(){
        // if(getCookie("status") != 1||getCookie("identify")!=2){
        //     alert("请先登录")
        //     this.$router.replace({ path: '/' });
        // }
    }
}
</script>
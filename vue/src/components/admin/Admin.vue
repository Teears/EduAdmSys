<template>
<el-container>
    <el-aside width="240px">
        <v-leftMenuAdm></v-leftMenuAdm>
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
import leftMenuAdm from './LeftMenuAdm.vue'
import backImg from '@/assets/back.jpg'
export default {
    data(){
        return{
            backImg
        }
    },
    components:{
        'v-leftMenuAdm': leftMenuAdm
    },
    methods:{
        created(){
            if(getCookie("token") == null){
                alert("请先登录")
                this.$router.replace({ path: '/loginAdmin' });
            }
        }
    }
}
</script>
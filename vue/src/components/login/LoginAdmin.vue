<template>
<div id="loginAdmin" class="login_div">
  <span class="login_span">管理员</span>
  <el-divider></el-divider>
  <el-form :model="userForm" :rules="rules" ref="userForm">
  <el-form-item prop="user">
      <el-input v-model="userForm.user" placeholder="用户名"></el-input>
  </el-form-item>
  
  <el-form-item prop="pass">
    <el-input v-model="userForm.pass" placeholder="密码" show-password></el-input>
  </el-form-item>
  
  <el-form-item>
    <el-input v-model="userForm.vcode" maxlength="4" placeholder="验证码" style="width: 150px"></el-input> 
    <img id="code_img" @click="changeCode()" style="height:40px; width: 100px; margin-left: 28px; cursor: pointer;" title="点击更换" >
  </el-form-item>
  
  <el-form-item>
    <el-button type="primary" @click="submitForm('userForm')" style="width: 250px">登录</el-button>
  </el-form-item>
  
  <el-link type="primary" style="cursor: pointer;" @click="toForgot">忘记密码?</el-link>
  
</el-form>
  
</div>
</template>

<script>
import {setCookie,getCookie} from '../global/cookie'
import md5 from "js-md5"

  export default {
    data() {
      var validateUser = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('用户名不能为空'));
        } else {
          callback();
        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('密码不能为空'));
        } else {
          callback();
        }
      };
      return {
        userForm: {
          user: '',
          pass: '',
          vcode: ''
        },
        rules: {
          user: [
            { validator: validateUser, trigger: 'blur' }
          ],
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ] 
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios
            .post('/login/admin', {
              user: this.userForm.user,
              pass: md5(this.userForm.pass),
              vcode: this.userForm.vcode
            })
            .then((result)=> {
              if (result.data.code === 1) {
                alert("管理员登录成功")//测试返回数据
                setCookie("token",result.data.datas.token,1)
                this.$router.replace({
                  name:'/Adm1_1',
                  params:{
                    perm:result.data.datas.perm
                  }
                })
              }else{
                alert(result.data.msg)
                return false;
              }
            })
            .catch((error)=> {
              alert(error)
            })
          } else {
            alert('用户名和密码不能为空');
            return false;
          }
        })
      },
      changeCode(){
        //   axios.get( ".../user/v1/qrcode?param=..." , {
        //     responseType: "arraybuffer",
        //   }).then(res => {
        //     return 'data:image/png;base64,' + btoa(
        //     new Uint8Array(res.data)
        //     .reduce((data, byte) => data + String.fromCharCode(byte), '')
        //   );
        //   })
        //   .then(data => {
        //     $('#qrCode').attr('src', data);
        //    })
        //   .catch(ex => {
        //     console.error(ex);
        //   });
        // }
      },
      toForgot(){
        this.$router.push({
          name:'forgot',
          query:{
            from:'admin'
          }
        })
      }

    }
  }
</script>
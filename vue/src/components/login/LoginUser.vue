<template>
<div id="loginUser" class="login_div">
  <span class="login_span">用户登录</span>
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
</el-form>
  <el-link type="primary" style="cursor: pointer;" @click="toForgot">忘记密码?</el-link>
</div>
</template>

<script>
import {setCookie} from '../global/cookie';
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
          user: [{ validator: validateUser, trigger: 'blur' }],
          pass: [{ validator: validatePass, trigger: 'blur' }] 
        }
      }
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
            if (valid) {
                //提交表单
                if(this.userForm.user.length === 10){
                  var identify = 1
                }else if(this.userForm.user.length === 8){
                  var identify = 2
                }
                var data = JSON.stringify(this.userForm)
                // alert(data)//测试提交的数据
                this.$axios
                  .post('/api/login', { //用户登录表单提交接口,提交user,pass,vcode
                      identify:identify,
                      data
                  })
                  .then((result)=> {
                    if (result.data.code === 1) {
                      alert("学生登录成功")//测试返回数据
                      setCookie("userid",result.data.datas.userid,3)
                      setCookie("username",result.data.datas.username,3)
                      setCookie("identify",1,3)//identify==1表示学生
                      setCookie("status",1,3)
                      this.$router.replace({ path: '/Stu1_1' }); //跳转到student组件中
                    } else if(result.data.code === 2){
                      alert("老师登录成功")//测试返回数据
                      setCookie("userid",result.data.datas.userid,3)
                      setCookie("username",result.data.datas.username,3)
                      setCookie("identify",2,3)//identify==2表示老师
                      setCookie("status",1,3)
                      this.$router.replace({ path: '/Tea1_1' }); //跳转到teacher组件中   
                    }else if(result.data.code === -1){
                      alert("验证码错误")
                    }else if(result.data.code === -2){
                      alert("用户名或密码错误")
                    }else{
                      console.log("登录失败");
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
            from:'user'
          }
        })
      }

    }
  }
</script>

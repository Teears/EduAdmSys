<template>
<div class="changePT" style="height:200">
  <el-form :model="userForm" :rules="rules" ref="userForm" label-width="100px">
  <el-form-item prop="tel" label="手机号：">
      <el-input v-model="userForm.tel" > </el-input>
  </el-form-item>

  <el-form-item>
    <el-input v-model="userForm.vcode" maxlength="6" placeholder="验证码" style="width: 125px"></el-input> 
    <el-button type="primary" round style="margin-left:10px;width:145px" @click="getCode()" :disabled="getCodeBtnDisable">{{codeBtnWord}}</el-button>
  </el-form-item>
  
  <el-form-item>
    <el-button type="primary" @click="submitForm('userForm')" style="width:300px">确认</el-button>
  </el-form-item>
</el-form>
</div>
</template>

<script>
import {getCookie} from '../global/cookie'
  export default {
    data() {
        var validateTel = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('手机号不能为空'));
            } else {
                callback();
            }
        };
        return {
        userForm: {
            tel:'',
            vcode:''
        },
        rules: {
            tel: [{ validator: validateTel, trigger: 'blur'}],
        },
        codeBtnWord:'获取验证码',
        waitTime:61,
      };
    },computed:{
        // 用于校验手机号码格式是否正确
        phoneNumberStyle(){
            let reg = /^1[3456789]\d{9}$/
            if(!reg.test(this.userForm.tel)){
                return false
            }
            return true
        },
        getCodeBtnDisable:{
            get(){
                if(this.waitTime == 61){
                    if(this.phoneNumberStyle){
                        return false
                    }  
                }
                return true
            },
            set(){}
        }
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
            if (valid) {
                //提交表单
                var data = JSON.stringify(this.userForm)
                alert(data)//测试提交的数据
                this.$axios
                .post('/api/changePwd', { //忘记密码表单提交接口,后台根据from判断是admin还是stuTea
                    userid: getCookie("userid"),
                    data
                })
                .then((result)=> {
                if (result.data.code === 1) {
                    alert("手机号换绑成功")//测试返回数据
                }else if(result.data.code === -1){
                    alert("验证码错误")
                }else{
                    console.log("换绑失败");
                    return false;
                }
                })
                .catch((error)=> {
                    alert(error)
                })
            } else {
                alert('请检查输入');
                return false;
            }
        })
      },
      getCode(){
        var data = JSON.stringify(this.userForm)
        data = JSON.parse(data)
        this.$axios
        .post('/api/getTelCode', { //获取验证码接口
            user:getCookie("userid"),
            tel:data.tel,
            identify:getCookie("identify")
        })
        .then((result)=> {
            if (result.data.code === 1) {
            alert("验证码已发送")//测试返回数据
            }else if(result.data.code === -1){
                alert("用户名或手机号不正确")
                return false;
            }else{
                console.log("验证码发送失败");
                return false;
            }
        })
        .catch((error)=> {
            alert(error)
        })
        let that = this
        that.waitTime--
        this.codeBtnWord = `${that.waitTime}s 后重新获取`
        let timer = setInterval(function(){
            if(that.waitTime>1){
                that.waitTime--
                that.codeBtnWord = `${that.waitTime}s 后重新获取`
            }else{
                clearInterval(timer)
                that.codeBtnWord = '获取验证码'
                that.waitTime = 61
            }
        },1000)
      }
    }
  }
</script>
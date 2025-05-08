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
                this.$axios
                .post('/pwd/changePhoneCode', {
                    telephone:this.userForm.tel,
                    phoneCode:this.userForm.vcode
                })
                .then((result)=> {
                if (result.data.code === 1) {
                    this.$message({
                        type: 'success',
                        message: '手机号已更换'
                    });
                }else{
                    this.$message({
                        type: 'info',
                        message: result.data.msg
                    });
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
        this.$axios
        .post('/phone/sendChangePhoneCode', { //获取验证码接口
            phoneNumber:this.userForm.tel
        })
        .then((result)=> {
            if (result.data.code === 1) {
                this.$message({
                    type: 'success',
                    message: '验证码已发送,10分钟内有效!'
                });
            }else{
                this.$message({
                    type: 'info',
                    message: result.data.msg
                });
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
<template>
<div id="forgot" class="forgot_div">
  <span class="login_span">忘记密码</span>
  <el-divider></el-divider>
  <el-form :model="userForm" :rules="rules" ref="userForm" label-width="70px">
  <el-form-item prop="user" label="用户名">
      <el-input v-model="userForm.user"></el-input>
  </el-form-item>

  <el-form-item prop="tel" label="手机号">
      <el-input v-model="userForm.tel" > </el-input>
  </el-form-item>
 
  <el-form-item>
    <el-col :span="10">
      <el-input v-model="userForm.vcode" maxlength="6" placeholder="验证码" style="width: 125px"></el-input> 
    </el-col>
    <el-col :span="12">
      <el-button type="primary" round style="margin-left:10px;width:145px" @click="getCode" :disabled="getCodeBtnDisable">{{codeBtnWord}}</el-button>
    </el-col>
  </el-form-item>
  
   <el-form-item label="新密码" prop="pass">
    <el-input type="password" v-model="userForm.pass" autocomplete="off"></el-input>
  </el-form-item>

  <el-form-item label="确认密码" prop="checkPass">
    <el-input type="password" v-model="userForm.checkPass" autocomplete="off"></el-input>
  </el-form-item>
  
  <el-form-item>
    <el-col :span="16">
      <el-button type="primary" @click="submitForm('userForm')" style="width: 170px">确认</el-button>
    </el-col>
    <el-col :span="6">
      <el-button plain  @click="$router.back(-1)" style="width: 100px">返回登录</el-button>
    </el-col>
  </el-form-item>
</el-form>
</div>
</template>

<script>
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
      var validateTel = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('手机号不能为空'));
        } else {
          callback();
        }
      };
      var validateVcode = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('验证码不能为空'));
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
      var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.userForm.pass) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
      return {
        userForm: {
            user:'',
            tel:'',
            vcode:'',
            pass:'',
            checkPass:'',
        },
        codeBtnWord:'获取验证码',
        waitTime:61,
        rules: {
          user:[{ validator: validateUser, trigger: 'blur'}],
          tel: [{ validator: validateTel, trigger: 'blur'}],
          vcode: [{ validator: validateVcode, trigger: 'blur'}],
          pass:[{ validator: validatePass, trigger: 'blur'}],
          checkPass: [{ validator: validatePass2, trigger: 'blur'}]
        }
      };
    },
    computed:{
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
            this.userForm.pass = md5(this.userForm.pass)
            this.userForm.checkPass = md5(this.userForm.checkPass)
            this.$axios
            .post('/pwd/resetPwd',this.userForm)
            .then((result)=> {
              if (result.data.code === 1) {
                this.$message({
                  type: 'success',
                  message: '密码重置成功，请重新登录'
                });
                this.$router.back(-1)
              }else{
                this.$message({
                  type: 'error',
                  message: result.data.msg
                });
              }
            })
            .catch((error)=> {
              alert(error)
            })
          } else {
            this.$message({
              type: 'error',
              message: '请检查输入！'
            });
          }
        })
      },
      
      getCode(){
        this.$axios
        .post('/phone/sendCode', { //获取验证码接口
            id:this.userForm.user,
            phoneNumber:this.userForm.tel
        })
        .then((result)=> {
          if (result.data.code === 1) {
            this.$message({
              type: 'success',
              message: '验证码已发送，10分钟内有效！'
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

<style>
.forgot_div{
    border: 1px;
    border-radius: 2px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    height: 470px;
    width: 350px;
    padding: 20px;
    text-align: center;
}
</style>
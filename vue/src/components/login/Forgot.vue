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
    <el-input v-model="userForm.vcode" maxlength="6" placeholder="验证码" style="width: 125px"></el-input> 
    <el-button type="primary" round style="margin-left:10px;width:145px" @click="getCode()" :disabled="getCodeBtnDisable">{{codeBtnWord}}</el-button>
  </el-form-item>
  
   <el-form-item label="新密码" prop="pass">
    <el-input type="password" v-model="userForm.pass" autocomplete="off"></el-input>
  </el-form-item>

  <el-form-item label="确认密码" prop="checkPass">
    <el-input type="password" v-model="userForm.checkPass" autocomplete="off"></el-input>
  </el-form-item>
  
  <el-form-item>
    <el-button type="primary" @click="submitForm('userForm')" style="width: 170px">确认</el-button>
    <el-button plain  @click="$router.back(-1)" style="width: 100px">返回登录</el-button>
  </el-form-item>
</el-form>
</div>
</template>

<script>
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
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('密码不能为空'));
        } else {
          callback();
        }
      };var validatePass2 = (rule, value, callback) => {
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
            from:this.$route.query.identify
        },
        codeBtnWord:'获取验证码',
        waitTime:61,
        rules: {
          user:[{ validator: validateUser, trigger: 'blur'}],
          tel: [{ validator: validateTel, trigger: 'blur'}],
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
                //提交表单
                var data = JSON.stringify(this.userForm)
                alert(data)//测试提交的数据
                this.$axios
                  .post('/api/forgot', { //忘记密码表单提交接口,后台根据from判断是admin还是stuTea
                      data
                  })
                  .then((result)=> {
                    if (result.data.code === 1) {
                      alert("密码重置成功，请重新登录")//测试返回数据
                      if(result.data.data.from == 'user'){
                        this.$router.replace({ path: '/' }); //密码重置成功跳转到loginUser组件中
                      }else if(result.data.data.from == 'admin'){
                        this.$router.replace({ path: '/loginAdmin' }); //跳转到loginAdmin组件中
                      }
                    }else if(result.data.code === -2){
                      alert("验证码错误")
                    }else if(result.data.code === -1){
                      alert("用户名或手机号不正确")
                    }else{
                      console.log("密码重置失败");
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
            user:data.user,
            tel:data.tel,
            identify:data.identify
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
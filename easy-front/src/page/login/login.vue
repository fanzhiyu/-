<template>
  <div class="login">
    <div class="error_page">
      <!--/login-top-->
      <div class="error-top">
        <h2 class="inner-tittle page">Easy Work</h2>
        <div class="login">
          <h3 class="inner-tittle t-inner">Login</h3>
          <div class="buttons login">
          </div>
          <form action="javascript:;" id="loginForm">
            <input type="text" v-model="username" check="ckNull" message="用户账号" class="text" placeholder="员工编号/电话号码/邮箱">
            <input type="password" v-model="password" check="ckNull" message="密码" placeholder="密码">
            <div class="submit"><input type="button" @click="login()" value="Login" ></div>
            <div class="clearfix"></div>
            <div class="new">
              <div>
                <label class="checkbox00" >
                <input type="checkbox" name="checkbox" style="float: left"><span style="float: left"></span>
                <div style="float: left;margin-left: 20px;margin-top: 2px;">记住密码</div></label>
              </div>
              <div class="clearfix"></div>
            </div>
          </form>
        </div>
      </div>
      <!--//login-top-->
    </div>
  </div>
</template>
<script>
  export default{
      name: 'login',
      data(){
        return{
          username: '',
          password: '',
        }
      },
      mounted(){

      },
      methods: {
        /**
         * 登陆
         */
        login(){
          if(this.$stringUtil.validation("loginForm")){
            var param = {};
            param['username'] = this.username;
            param['password'] = this.password;
            this.$httpService.login(param).then((res)=>{
              if(res.code == '2000'){
                var data = res.data;
                localStorage.setItem("token",data.token);
                localStorage.setItem("refreshToken",data.refreshToken);
                this.$router.push({path:'/index'})
              }
            })
          }
        }
      }
  }
</script>
<style>

</style>

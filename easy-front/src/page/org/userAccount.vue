<template>
  <div name="userAccount" class="user-account">
    <form id="accountForm" action="javascript:;">
      <section class="group">
        <div class="titles"><h3>创建账号</h3></div>
        <div class="control">
          登陆账号<span class="required">*</span>：
          <input type="text" v-model="accountName" message="登陆账号" check="ckNull" placeholder="请输入登陆账号" />
        </div>
        <div class="control" >
          登陆密码<span class="required">*</span>：
          <input type="password" v-model="password" message="登陆密码" :check="accountId == '' ? 'ckNull' : ''" placeholder="请输入登陆密码" />
        </div>
        <div class="control" >
          确认密码<span class="required">*</span>：
          <input type="password" v-model="confirmPass" message="确认密码" :check="accountId == '' ? 'ckNull' : ''" placeholder="请输入确认密码" />
        </div>
        <div class="control">
          <label class="left">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态<span class="required">*</span>：</label>
          <div class="status">
            <div class='filter-box' id="status" style="width: 70%;">
              <div class='filter-text'>
                <input class='filter-title' type='text' check="ckNull" message="状态" readonly placeholder='选择状态' />
                <span class='icon-arrow'></span>
              </div>
              <select class='inputs' name='status' v-model="status">
                <option value="1">启用</option>
                <option value="2">禁用</option>
              </select>
            </div>
          </div>
        </div>
        <div class="control">
          <label class="left">账&nbsp;&nbsp;号&nbsp;&nbsp;锁<span class="required">*</span>：</label>
          <div class="status">
            <div class='filter-box' id="lock" style="width: 70%;">
              <div class='filter-text'>
                <input class='filter-title' type='text' check="ckNull" message="账号锁" readonly placeholder='选择账号锁' />
                <span class='icon-arrow'></span>
              </div>
              <select class='inputs' name='lock' v-model="lock">
                <option value="1" >解锁</option>
                <option value="2" >锁定</option>
              </select>
            </div>
          </div>
        </div>
      </section>
    </form>
    <div class="submit-btn">
      <ul>
        <li class="btn-cancel"><a href="javascript:;" @click="cancel()">取消</a></li>
        <li class="btn-confirm"><a href="javascript:;" @click="confirm()">确认</a></li>
        <div class="clearfix"></div>
      </ul>
    </div>
  </div>
</template>
<script>
  import {Indicator, MessageBox, Toast} from "mint-ui";
  export default{
      name: 'userAccount',
      data(){
          return {
            accountName: '',
            password: '',
            confirmPass: '',
            status: '',
            lock: '',
            userAccount: '',
            accountId: '',
          }
      },
      mounted(){
        this.init();
      },
      watch:{
        '$route': ['init']
      },
      methods: {

        init(){
          var $this = this;
          $this.status = '';
          $this.lock = '';
          $this.accountName = '';
          $this.accountId = '';
          var param= {};
          param['userId'] = this.$route.query.userId;
          $this.$httpService.getUserAccount(param).then((res)=>{
            if(res.code == '2000' && res.data){
              var data = res.data;
              $this.accountName =data.loginName;
              $this.status = data.status;
              $this.lock = data.accountLock;
              $this.accountId = data.accountId;
            }
            this.$nextTick(()=>{
              $("#status").selectFilter({
                callBack: (val)=>{
                  $this.status = val;
                }
              });
              $("#lock").selectFilter({
                callBack: (val)=>{
                  $this.lock = val;
                }
              })
            })
          });
        },

        /**
         * 确认
         */
        confirm(){
            if(this.$stringUtil.validation("accountForm")){
              if(this.password != this.confirmPass){
                  Toast("两次密码不一致");
                  return;
              }
              var param = {};
              param['accountId'] = this.accountId;
              param['loginName'] = this.accountName;
              param['password'] = this.confirmPass;
              param['status'] = this.status;
              param['accountLock'] = this.lock;
              param['userId'] = this.$route.query.userId;
              this.$httpService.saveUserAccount(param).then((res)=>{
                if(res.code == '2000'){
                  Toast("保存成功");
                }
              })
            }
        },

        /**
         * 取消
         */
        cancel(){
          this.$parent.$parent.$parent.hidePanel();
        }
      }
  }
</script>
<style>
.user-account{
  padding: 10px;
}
</style>

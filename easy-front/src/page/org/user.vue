<template>
  <div name="user" class="user" >
    <form action="javascript:;" id="userForm">
    <section class="group border-bottom-gray">
      <div class="titles"><h3>新建成员</h3></div>
      <div class="control">
        头&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;像&nbsp;：
        <input type="file" id="headerImg" style="display: none" @change="uploadHeaderImg()" />
        <div class="btn" @click="selectHeaderImg()" >选择</div>
      </div>
      <div class="control">
        姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名<span class="required">*</span>：
        <input type="text" v-model="userName" message="姓名" check="ckNull" placeholder="请输入姓名" />
      </div>
      <div class="control">
        手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机<span class="required">*</span>：
        <input type="text" v-model="userTel" message="手机号" check="ckNull,ckTel" placeholder="请输入手机号"/>
      </div>
      <div class="control">
        邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱<span class="required">*</span>：
        <input type="text" v-model="userEmail" message="邮箱" check="ckNull,ckEmail" placeholder="请输入邮箱"/>
      </div>
      <div class="control">
        工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号<span class="required">*</span>：
        <input type="text" v-model="userCode" message="工号" check="ckNull" disabled/>
      </div>
    </section>
      <section class="group border-bottom-gray ">
        <div class="control">
          <label class="left">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门<span class="required">*</span>：</label>
          <div class="dep">
            <div class='filter-box' id="dep" style="width: 70%">
              <div class='filter-text'>
                <input class='filter-title' type='text' message="部门" check="ckNull" readonly placeholder='选择部门' />
                <span class='icon-arrow'></span>
              </div>
              <select class='inputs' name='dep' v-model="depId">
                <option v-for="item in depList" :value="item.id">{{item.text}}</option>
              </select>
            </div>
          </div>
        </div>
        <div class="control">
          职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位<span class="required">*</span>：
          <input type="text" placeholder="职务" message="职位" check="ckNull"  v-model="userPost"/>
        </div>
        <div class="control">
          入职时间<span class="required">*</span>：
          <input type="text" id="entryDate" message="入职时间" check="ckNull"  @click="onEntryTime($event)" class="date" position="top" readonly="true" placeholder="选择入职时间"/>
        </div>
        <div class="control">
          转正时间<span class="required">*</span>：
          <input type="text" id="corrDate" message="转正时间" check="ckNull"  @click="onCorrTime($event)" class="date" position="top" readonly="true" placeholder="转正时间"/>
        </div>
      </section>
    <section class="group button-top" >
      <div class="control">
        <div style="float: left">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;：</div>
        <div style="float:left;width: 50px;">
          <input type="radio" v-model="userSex" value="1"  id="recmoned-yes" name="recmoned-set" class="regular-radio"/>
          <label for="recmoned-yes"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;男
        </div>
        <div style="float:left;width: 50px;">
          <input type="radio" id="recmoned-no" v-model="userSex" value="2" name="recmoned-set" class="regular-radio"/>
          <label for="recmoned-no"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;女
        </div>
      </div>
      <div class="control">
        <label class="left">籍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贯&nbsp;：</label>
        <div class="province">
          <div class='filter-box' id="province" style="width: 130px;">
            <div class='filter-text'>
              <input class='filter-title' type='text' check="ckNull" message="选择省" readonly placeholder='选择省' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='province' v-model="provinceId">
              <option v-for="item in provinceList" :value="item.id">{{item.name}}</option>
            </select>
          </div>
        </div>
        <label class="left">&nbsp;&nbsp; - - &nbsp;</label>
        <div class="city">
          <div class='filter-box' id="city" style="width: 130px;">
            <div class='filter-text'>
              <input class='filter-title' type='text' check="ckNull" message="选择市" readonly placeholder='选择市' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='city' v-model="cityId">
              <option v-for="item in cityList" :value="item.id">{{item.name}}</option>
            </select>
          </div>
        </div>
      </div>
      <div class="control">
        现&nbsp;&nbsp;居&nbsp;&nbsp;地&nbsp;：
        <input type="text" v-model="userAddress" placeholder="请输入现居住地"/>
      </div>
      <div class="control">
        年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄&nbsp;：
        <input type="number" v-model="userAge" maxlength="2" minlength="1" placeholder="请输入年龄" />
      </div>
      <div class="control">
        生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;：
        <input type="text" id="birthday" @click="onBirthdayTime($event)" class="date" position="top" readonly="true" placeholder="选择生日"/>
      </div>
      <div class="control">
        身&nbsp;&nbsp;份&nbsp;&nbsp;证&nbsp;：
        <input type="text" v-model="userCid" placeholder="身份证"/>
      </div>
    </section>

    </form>
    <div class="submit-btn">
      <ul>
        <li class="btn-cancel"><a href="javascript:;" @click="cancel()">取消</a></li>
        <li class="btn-confirm"><a href="javascript:;" @click="save()">确认</a></li>
      </ul>
    </div>
  </div>
</template>
<script>
  export default{
    name: 'user',
    data(){
      return {
        provinceList : [],
        provinceId: '',
        cityList: [],
        cityId: '',
        userName: '',
        userTel: '',
        userEmail: '',
        userCode: '',
        userSex: '1',
        userAge: '',
        userCid: '',
        userBirthdayDate: '',
        userEntryDate: '',
        userCorrDate: '',
        depId: '',
        userPost: '',
        depList: [],
        userAddress: '',
        userId : '',
      }
    },
    watch:{
      '$route': ['getDep','getUser','getUserCode']
    },
    mounted(){
        this.init();
    },
    methods: {

      /**
       * 初始化数据
       */
      init(){
        this.getUser();
        this.getProvince();
        this.getDep();
      },

      /**
       * 获取用户code
       */
      getUserCode(){
          var param = {};
          param['orgId'] = this.$route.query.orgId;
          this.$httpService.getUserCode(param).then((res)=>{
              if(res.code == '2000'){
                  this.userCode = res.data;
              }
          })
      },

      /**
       * 获取用户信息
       */
      getUser(){
          var userId = this.$route.query.userId;
          this.set();
          if(userId){
            var param = {};
            param['userId'] = userId
            this.$httpService.getUserByUserId(param).then((res)=>{
              if(res.code == '2000' && res.data){
                  var data = res.data;
                  this.userName = data.userName;
                  this.userTel = data.userTel;
                  this.userEmail = data.userEmail;
                  this.userCode = data.userCode;
                  this.userSex = data.userSex;
                  this.userBirthdayDate = data.userBirthday;
                  this.userEntryDate = data.userEntryDate;
                  this.userCorrDate = data.userCorrDate;
                  this.provinceId = data.userPlaceProvince;
                  this.cityId = data.userPlaceCity;
                  this.userAddress = data.userAddress;
                  this.userAge = data.userAge;
                  this.userCid = data.userCid;
                  this.depId = data.userDep;
                  this.userPost = data.userPost;
                  this.userId = data.userId;
                  this.setProvince();
                  this.getCity();
                  this.getDep();
                  $("#birthday").val(data.userBirthday);
                  $("#entryDate").val(data.userEntryDate);
                  $("#corrDate").val(data.userCorrDate);
              }
            })
          }
      },

      /**
       * 初始化值
       */
      set(){
        this.userId = "";
        this.userName = "";
        this.userTel = "";
        this.userEmail = "";
        this.userCode = "";
        this.userSex = "";
        this.provinceId = "";
        this.cityId = "";
        this.userAddress = "";
        this.userAge = "";
        this.userCid = "";
        this.depId = "";
        this.userPost = "";
        $("#province input").val("")
        $("#city input").val("");
        $("#birthday").val("");
        $("#entryDate").val("");
        $("#corrDate").val("");
      },

      /**
       * 查找省份
       */
      getProvince(){
        var $this = this;
        $this.$httpService.getProvinceList().then((res)=>{
            if(res.code == '2000'){
                $this.provinceList = res.data;
                this.setProvince();
            }
        })
      },

      /**
       * 设置省份
       */
      setProvince(){
        var $this = this;
        $this.$nextTick(()=>{
          $("#province").selectFilter({
            callBack : function (val){
              $this.provinceId = val;
              $this.getCity();
            }
          })
        })
      },

      /**
       * 查找市区
       */
      getCity(){
        var param = {};
        param['provinceId'] = this.provinceId;
        var $this = this;
        $this.$httpService.getCityList(param).then((res)=>{
            if(res.code == '2000'){
              $this.cityList = res.data;
              $this.setCity();
            }
        })
      },

      /**
       * 设置市
       */
      setCity(){
        var $this = this;
        $this.$nextTick(()=>{
          $("#city input").val("");
          $this.cityId = "";
          $("#city").selectFilter({
            callBack : function (val){
              $this.cityId = val;
            }
          })
        })
      },

      /**
       * 选择头像
       */
      selectHeaderImg(){
          document.getElementById("headerImg").click();
      },

      /**
       * 保存
       */
      save(){
          if(this.$stringUtil.validation("userForm")){
            var param = {};
            param['userId'] = this.userId;
            param['userName'] = this.userName;
            param['userTel'] = this.userTel;
            param['userEmail'] = this.userEmail;
            param['userCode'] = this.userCode;
            param['userSex'] = this.userSex;
            param['userAge'] = this.userAge;
            param['userAddress'] = this.userAddress;
            param['userBirthday'] = this.userBirthday;
            param['userCid'] = this.userCid;
            param['userEntryDate'] = this.userEntryDate;
            param['userCorrDate'] = this.userCorrDate;
            param['orgId'] = this.$parent.$parent.parentId;
            param['userPlaceProvince'] = this.provinceId;
            param['userPlaceCity'] = this.cityId;
            param['userDep'] = this.depId;
            param['userPost'] = this.userPost;
            this.$httpService.saveUser(param).then((res)=>{
              if(res.code == '2000'){
                this.$parent.$parent.getUser()
              }
            })
          }
      },

      /**
       * 上传头像
       */
      uploadHeaderImg(){
        console.log("123")
      },

      /**
       * 选择生日事件
       */
      onBirthdayTime($event){
        var $this = this;
        window.onTime($event.target,function(v){
          $this.userBirthday = v;
        })
      },

      /**
       * 选择入职时间
       */
      onEntryTime($event){
        var $this = this;
        window.onTime($event.target,function(v){
          $this.userEntryDate = v;
        })
      },

      /**
       * 查找部门
       */
      getDep(){
        var param = {};
        param['parentId'] = this.$route.query.orgId;
        var $this = this;
        $this.$httpService.getDepByParentId(param).then((res)=>{
          if(res.code == '2000'){
            $this.depList = res.data;
            $this.$nextTick(()=>{
                $("#dep").selectFilter({
                  callBack : (val)=>{
                    $this.depId = val;
                  }
                })
            })
          }
        })
      },

      /**
       * 转正时间
       */
      onCorrTime($event){
        var $this = this;
        window.onTime($event.target,function(v){
          $this.userCorrDate = v;
        })
      },

      /**
       * 取消
       */
      cancel(){
        this.$parent.$parent.$parent.hidePanel();
      },
    }
  }
</script>
<style>
  @import "user.scss";
</style>

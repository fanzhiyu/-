<template>
  <div name="addOrg" class="add-org">
    <form action="javascript:;" id="orgForm">
    <section class="group border-bottom-gray">
      <div class="titles"><h3>添加组织</h3></div>
      <div class="control">
        上级组织<span class="required">*</span>：
        <input type="text" placeholder="请选择上级组织" check="ckNull" message="上级组织" v-model="parentName" disabled/>
      </div>
      <div class="control">
        组织名称<span class="required">*</span>：
        <input type="text" placeholder="请填写组织名称" check="ckNull" message="组织名称" v-model="orgName"/>
      </div>
      <div class="control">
        名&nbsp;&nbsp;简&nbsp;&nbsp;写<span class="required">*</span>：
        <input type="text" placeholder="组织名称简写" check="ckNull" message="名称简写" v-model="orgNameAb"/>
      </div>
      <div class="control">
        组织编号<span class="required">*</span>：
        <input type="text" placeholder="组织编号" check="ckNull" message="组织编号" v-model="orgNo" disabled/>
      </div>
    </section>
    <section class="group">
      <div class="control">
        <label class="left">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型<span class="required">*</span>：</label>
        <div class="left" style="width: 70%">
          <div class='filter-box' id="type" style="width: 100%;">
            <div class='filter-text'>
              <input class='filter-title' type='text' check="ckNull" message="选择类型" readonly placeholder='选择类型' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='type' v-model="typeId">
              <option value="1">组织</option>
              <option value="2">部门</option>
            </select>
          </div>
        </div>
      </div>
      <div class="control" v-if="typeId == '1'">
        <label class="left">所&nbsp;&nbsp;在&nbsp;&nbsp;地<span class="required">*</span>：</label>
        <div class="left">
          <div class='filter-box' id="orgProvince" style="width: 130px;">
            <div class='filter-text'>
              <input class='filter-title' type='text' check="ckNull" message="省份" readonly placeholder='选择省份' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='province' v-model="provinceId">
              <option v-for="item in provinceList" :value="item.id">{{item.name}}</option>
            </select>
          </div>
        </div>
        <label class="left">&nbsp;&nbsp; - - &nbsp;</label>
        <div class="left">
          <div class='filter-box' id="orgCity" style="width: 130px;">
            <div class='filter-text'>
              <input class='filter-title' type='text' check="ckNull" message="市" readonly placeholder='选择市' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='city' v-model="cityId">
              <option v-for="item in cityList" :value="item.id">{{item.name}}</option>
            </select>
          </div>
        </div>
      </div>
      <div class="control" v-if="typeId == '1'">
        详细地址<span class="required">*</span>：
        <input type="text" v-model="address" check="ckNull" message="详细地址"/>
      </div>
      <div id="map" v-show="typeId == '1'">
      </div>
    </section>
    </form>
    <div class="submit-btn">
      <ul>
        <li class="btn-cancel"><a href="javascript:;" @click="cancel()">取消</a></li>
        <li class="btn-confirm"><a href="javascript:;" @click="saveOrg()">确认</a></li>
        <div class="clearfix"></div>
      </ul>
    </div>
  </div>
</template>
<script>
  export default{
    name: 'addOrg',
    data(){
      return {
        typeId: '',
        provinceId: '',
        provinceList: [],
        cityId: '',
        cityList: [],
        orgName: '',
        orgNameAb: '',
        orgNo: '',
        parentName: '',
        parentId: '',
        address: '',
        longitude: '',
        latitude: '',
        orgId: ''
      }
    },
    watch:{
      changeParentId: function(){
        this.init();
        this.getOrgNameByOrgId();
      },
      changeOrgId: function(){
        this.searchOrg();
      },
    },
    computed: {
      /**
       * 监听父组织编号
       */
      changeParentId() {
        this.parentId = this.$parent.$parent.parentId;
        return this.parentId;
      },
      /**
       * 监听组织编号
       */
      changeOrgId(){
        this.orgId = this.$parent.$parent.orgId;
        return this.orgId;
      },
    },
    mounted(){
      this.init();
      this.initMap();
      this.getProvince();
      this.getOrgNameByOrgId();
    },
    methods: {

        init(){
            var $this = this;
            $this.setType();
            $this.getOrgNo();
        },

        /**
         * 设置类型
         */
        setType(){
          var $this = this;
          $this.$nextTick(()=>{
            $("#type").selectFilter({
              callBack: (val)=>{
                $this.typeId = val;
                $this.getProvince();
              }
            });
          })
        },

        /**
         * 查找组织详细
         */
        searchOrg(){
          var param = {};
          param['orgId'] = this.orgId;
          this.set();
          this.$httpService.getOrgByOrgId(param).then((res)=>{
              if(res.code == '2000'){
                  var data = res.data;
                  this.orgName = data.orgName;
                  this.orgNameAb= data.orgNameab;
                  this.provinceId = data.orgProvince;
                  this.cityId = data.orgCity;
                  this.address = data.orgAddress;
                  this.typeId = data.orgType;
                  this.orgId = data.orgId;
                  this.parentId = data.parentId;
                  this.orgNo = data.orgNo;
                  this.setProvince();
                  this.getCity();
                  this.setType();
              }
          })
        },

        set(){
          this.orgName = "";
          this.orgNameAb= "";
          this.provinceId = "";
          this.cityId = "";
          this.address = "";
          this.typeId = "";
          this.orgId = "";
          this.parentId = "";
          this.getOrgNo();
          $("#orgProvince input").val("");
          $("#orgCity input").val("");
          $("#type input").val("");
        },

        /**
         * 初始化地图
         */
        initMap(){
          this.$nextTick(()=>{
            var map = new BMap.Map("map");
            var myGeo = new BMap.Geocoder();
            var cityName = $("#orgCity input").val() ? $("#orgCity input").val() : "北京市";
            console.log(cityName);
            var $this = this;
            myGeo.getPoint(cityName, function(point){
              $this.latitude = point.lat;
              $this.longitude = point.lng;
              map.centerAndZoom(new BMap.Point(point.lng, point.lat), 11);
            }, cityName);
          })
        },

        /**
         * 查找省份
         */
        getProvince(){
          var $this = this;
          $this.$httpService.getProvinceList().then((res)=>{
            if(res.code == '2000'){
              $this.provinceList = res.data;
              $this.setProvince();
            }
          })
        },

        /**
         * 设置省份
         */
        setProvince(){
          var $this = this;
          $this.$nextTick(()=>{
            if($("#orgProvince").length == 0){return;}
            $("#orgProvince").selectFilter({
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
          if(this.provinceId){
            var param = {};
            param['provinceId'] = this.provinceId;
            var $this = this;
            $this.$httpService.getCityList(param).then((res)=>{
              if(res.code == '2000'){
                $this.cityList = res.data;
                this.setCity();
                this.initMap();
              }
            })
          }
        },

        /**
         * 设置市区
         */
        setCity(){
          var $this = this;
          $this.$nextTick(()=>{
            if($("#orgCity").length == 0){return;}
            $("#orgCity").selectFilter({
              callBack : function (val){
                $this.cityId = val;
                $this.initMap();
              }
            })
          })
        },

        /**
         * 获取组织名
         */
        getOrgNameByOrgId(){
            var param = {};
            param['orgId'] = this.parentId;
            this.$httpService.getOrgByOrgId(param).then((res)=>{
                if(res.code == '2000'){
                    this.parentName = res.data ? res.data.orgName : "";
                }
            })
        },

        /**
         * 获取组织编号
         */
        getOrgNo(){
            var param = {};
            param['parentId'] = this.parentId;
            this.$httpService.getOrgNo(param).then((res)=>{
                if(res.code == '2000'){
                    this.orgNo = res.data;
                }
            })
        },

        /**
         * 保存组织
         */
        saveOrg(){
          if(this.$stringUtil.validation("orgForm")){
            var param = {};
            param['orgName'] = this.orgName;
            param['parentId'] = this.parentId;
            param['orgNameab'] = this.orgNameAb;
            param['orgNo'] = this.orgNo;
            param['orgProvince'] = this.provinceId;
            param['orgCity'] = this.cityId;
            param['orgType'] = this.typeId;
            param['orgAddress'] = this.address;
            param['orgLongitude'] = this.longitude;
            param['orgLatitude'] = this.latitude;
            this.$httpService.saveOrg(param).then((res)=>{
              if(res.code == '2000'){
                  this.$parent.$parent.init();
                  this.$parent.$parent.getOrgList();
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
  @import "user.scss";
  .add-org{
    width: 100%;
    height: 100%;
    padding: 10px;
  }
  #map {width: 100%;height: 300px;overflow: hidden;margin:0;font-family:"微软雅黑";}
</style>

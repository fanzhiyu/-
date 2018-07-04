<template>
  <div class="tabs-add">
    <section class="group border-bottom-gray" id="tabsForm">
      <div class="titles"><h3>创建表信息</h3></div>
      <div class="control">
        表&nbsp;&nbsp;&nbsp;名<span class="required">*</span>：
        <input type="text" v-model="tabName" check="ckNull" message="表名" placeholder="请输入表名"/>
      </div>
      <div class="control">
        备&nbsp;&nbsp;&nbsp;注<span class="required">*</span>：
        <input type="text" message="备注" check="ckNull" v-model="tabComment" placeholder="请输入表备注"/>
      </div>
      <div class="control">
        <div class="left">表类型<span class="required">*</span>：</div>
        <div class='filter-box' id="add-type" style="width: 70%">
          <div class='filter-text'>
            <input class='filter-title' type='text' check="ckNull" message="表类型" readonly placeholder='选择表类型' />
            <span class='icon-arrow'></span>
          </div>
          <select class='inputs' name='select' v-model="tabType">
            <option v-for="item in tabTypes" :value="item.code">{{item.name}}</option>
          </select>
        </div>
      </div>
    </section>
    <section class="group">
      <div class="titles"><h3>添加表字段</h3></div>
      <div class="tables tabs-add-table overflow">
        <table class="table table-bordered">
          <thead>
            <tr>
              <th width="8%">
               选择
              </th>
              <th width="8%">编号</th>
              <th width="15%">字段名</th>
              <th width="20%">备注</th>
              <th width="15%">类型</th>
              <th width="10%">长度</th>
              <th width="10%">主键</th>
              <th width="10%">非空</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item,index) in tabFields">
              <td>
                <label class="checkbox00" >
                  <input type="checkbox" v-model="item.tabCheckbox" value="1" name="checkbox"><span></span>
                </label>
              </td>
              <td>
                {{index+1}}
              </td>
              <td><input type="text" v-model="item.tabFieldName" /></td>
              <td><input type="text" v-model="item.tabFieldComment" /></td>
              <td>
                <select v-model="item.tabFieldType">
                  <option v-for="item in tabFieldTypes" :value="item.name">{{item.name}}</option>
                </select>
              </td>
              <td><input type="text" v-model="item.tabFieldLength" /></td>
              <td>
                <label class="checkbox00" >
                  <input type="checkbox" v-model="item.tabIsKey" value="1" ><span></span>
                </label>
              </td>
              <td>
                <label class="checkbox00" >
                  <input type="checkbox" v-model="item.tabIsNull" value="1" ><span></span>
                </label>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="opt-btns">
        <a href="javascript:;" @click="addField()"><i class="fa fa-plus"></i></a>&nbsp;&nbsp;&nbsp;
        <a href="javascript:;" @click="deleteField()"><i class="fa fa-minus"></i></a>
      </div>
    </section>
    <div class="submit-btn">
      <ul>
        <li class="btn-cancel"><a href="javascript:;" @click="cancel()">取消</a></li>
        <li class="btn-confirm"><a href="javascript:;" @click="save()">确认</a></li>
        <div class="clearfix"></div>
      </ul>
    </div>
  </div>
</template>
<script>
  import {Indicator, MessageBox, Toast} from "mint-ui";
  export default{
      name: '',
      data() {
          return {
              tabTypes: [],
              tabFieldTypes: [],
              tabName: '',
              tabComment: '',
              tabType: '',
              tabFields: [],
              tabId: ''
          }
      },
      mounted(){
        this.init();
      },
      watch:{
        change(){
            this.initData();
        }
      },
      computed: {
        change() {
          this.tabId = this.$parent.$parent.tabId;
          return this.tabId;
        }
      },
      methods: {

        /**
         * 初始化数据
         */
        initData(){
          var tabId= this.tabId;
          var $this = this;
          $this.clear();
          if(tabId){
            var param = {};
            param['tabId'] = tabId;
            $this.$httpService.getTabDetails(param).then((res)=>{
              if(res.code == '2000'){
                var data = res.data;
                $this.tabComment = data.tabComment;
                $this.tabName = data.tabSourceName;
                $this.tabType = data.tabType;
                data.fields.forEach((obj,index)=>{
                    obj.tabIsKey = obj.tabIsKey == "0" ? "" : true;
                    obj.tabIsNull = obj.tabIsNull == "0" ? "" : true;
                });
                $this.tabFields = data.fields;
                $this.tabId = data.tabId;
                $this.setType();
              }
            });
          }
        },

        /**
         * 清空数据
         */
        clear(){
          this.tabComment = "";
          this.tabName = "";
          this.tabType = "";
          this.tabId = "";
          this.tabFields = [];
          $("#add-type input").val("");
        },

        /**
         * 页面初始化
         */
        init(){
          var $this = this;
          $this.$httpService.getTabType().then((res)=>{
              if(res.code == '2000'){
                $this.tabTypes = res.data;
                $this.setType();
              }
          });
          $this.$httpService.getTabFieldType().then((res)=>{
              if(res.code == '2000'){
                  $this.tabFieldTypes = res.data;
              }
          });
          this.initData();
        },

        /**
         * 设置类型
         */
        setType(){
          var $this = this;
          $this.$nextTick(()=>{
            $("#add-type").selectFilter({
              callBack : function (val){
                $this.tabType = val;
              }
            });
          })
        },

        /**
         * 添加字段
         */
        addField(){
          var param = {};
          param['tabCheckbox'] = '';
          param['tabFieldName'] = '';
          param['tabFieldComment'] = '';
          param['tabIsKey'] = '';
          param['tabFieldType'] = '';
          param['tabFieldLength'] = '';
          param['tabIsNull'] = '';
          this.tabFields.push(param);
        },

        /**
         * 删除字段
         */
        deleteField(){
          var lists = this.tabFields;
          for(var i=0,len=lists.length;i<len;){
              var list = lists[i];
              if(list && list.tabCheckbox){
                lists.splice(i, 1);
                continue;
              }
              i++
          }
        },

        /**
         * 保存
         */
        save(){
          if(this.$stringUtil.validation("tabsForm")){
            var flag = true;
            var key = false;
            var reg = /^[a-zA-Z]{1}\w*$/;
            console.log(this.tabName);
            if(!reg.test(this.tabName)){
              Toast("字段名不是英文字母");
              return;
            }
            if(this.tabFields.length == 0){
                Toast("字段不能少于一个，并且至少添加一个主键");
                return;
            }
            for(var i =0,len=this.tabFields.length;i<len;i++)  {
                var field = this.tabFields[i];
                if(!field.tabFieldName){
                  flag = false;
                  Toast("编号"+i+"行表名不能为空");
                  break;
                }
                if(!reg.test(field.tabFieldName)){
                  flag = false;
                  Toast("编号"+i+"行表名不是英文字母");
                  break;
                }
                if(!field.tabFieldComment){
                  flag = false;
                  Toast("编号"+i+"行字段名不能为空");
                  break;
                }
                if(!field.tabFieldType){
                  flag = false;
                  Toast("编号"+i+"行类型不能为空");
                  break;
                }
                if(field.tabIsKey){
                  key = true;
                }
            }
            if(flag && !key){Toast("缺少主键");}
            if(flag){
              var param = {};
              var json = {};
              json['fieldList'] = this.tabFields;
              param['tabName'] = this.tabName;
              param['tabComment'] = this.tabComment;
              param['tabType'] = this.tabType;
              param['tabFiles'] = JSON.stringify(json);
              param['tabId'] = this.tabId;
              var $this = this;
              $this.$httpService.saveTab(param).then((res)=>{
                if(res.code == '2000'){
                  $this.$parent.$parent.init();
                  $this.$parent.$parent.$parent.hidePanel();
                }
              })
            }
          }
        },

        /**
         * 取消
         */
        cancel(){
          this.$parent.$parent.$parent.hidePanel();
        },
      },
  }
</script>
<style>
.tabs-add .group{
  padding: 10px;
}
.tabs-type{
  float: left;
}
.tabs-add-table{
  width: 100%;
  border-bottom: 1px solid #efefef;
  height: 300px;
}
.tabs-add-table table{width: 100%;}
.tabs-add-table th{
  font-weight: 100;
}
.tabs-add-table tbody td input{
  width: 100%;
  height: 100%;
  border: 0px;
}
.tabs-add-table tbody td select{
  height: 20px;
  line-height: 20px;
  width: 80px;
}
  .opt-btns{
    margin-top: 5px;
  }
</style>

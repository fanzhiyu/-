<template>
  <div>
    <form action="javascript:;" id="objectForm">
    <section class="group">
      <div class="titles"><h3>创建对象信息</h3></div>
      <div class="control">
        对&nbsp;&nbsp;象&nbsp;&nbsp;名<span class="required">*</span>：
        <input type="text" message="对象名" check="ckNull" v-model="objName"/>
      </div>
      <div class="control">
        <div class="left">对象类型<span class="required">*</span>：</div>
        <div class='filter-box' id="select-type" style="width: 70%">
          <div class='filter-text'>
            <input class='filter-title' type='text' check="ckNull" message="表类型" readonly placeholder='选择表类型' />
            <span class='icon-arrow'></span>
          </div>
          <select class='inputs' name='select' v-model="objType">
            <option value="1">添加对象</option>
            <option value="2">列表对象</option>
            <option value="3">查询对象</option>
          </select>
        </div>
      </div>
      <div class="control" v-if="objType == '2'">
        <div class="left">查询表单<span class="required">*</span>：</div>
        <div class='filter-box' id="searchForm" style="width: 70%">
          <div class='filter-text'>
            <input class='filter-title' type='text' check="ckNull" message="查询表单" readonly placeholder='选择查询表单' />
            <span class='icon-arrow'></span>
          </div>
          <select class='inputs' name='select' v-model="searchFormId">
            <option v-for="item in searchForm" :value="item.formId">{{item.formTitle}}</option>
          </select>
        </div>
      </div>
      <div class="control" v-if="objType == '2'">
        <div class="left">添加表单<span class="required">*</span>：</div>
        <div class='filter-box' id="addForm" style="width: 70%">
          <div class='filter-text'>
            <input class='filter-title' type='text' check="ckNull" message="添加表单" readonly placeholder='选择添加表单' />
            <span class='icon-arrow'></span>
          </div>
          <select class='inputs' name='select' v-model="addFormId">
            <option v-for="item in addForm" :value="item.formId">{{item.formTitle}}</option>
          </select>
        </div>
      </div>
      <div class="control">
        <div class="left">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态<span class="required">*</span>：</div>
        <div class='filter-box' id="select-status" style="width: 70%">
          <div class='filter-text'>
            <input class='filter-title' type='text' check="ckNull" message="表类型" readonly placeholder='选择表类型' />
            <span class='icon-arrow'></span>
          </div>
          <select class='inputs' name='select' v-model="objStatus">
            <option value="1">启用</option>
            <option value="2">禁用</option>
          </select>
        </div>
      </div>
    </section>
    </form>
    <section class="group">
      <div class="titles"><h3>选择数据表</h3></div>
      <div class="form-group p-t-xs overflow">
        <select id="optgroup" multiple="multiple">
          <option v-for="item in tabs" :value="item.tabId">{{item.tabComment}}</option>
        </select>
        <div class="clear"></div>
        <div class="titles"><h3>选择字段</h3></div>
        <div class="tables" style="margin-bottom: 40px;">
          <table class="table table-bordered" style="margin-bottom: 100px;">
            <thead>
            <tr>
              <th width="7%">
                选择
              </th>
              <th width="10%">字段编号</th>
              <th width="10%">字段名</th>
              <th width="10%">对应表</th>
              <th width="10%">类型</th>
              <th width="10%">长度</th>
              <th width="10%" v-if="objType == '1' || objType == '2'">主键列</th>
              <th width="10%" v-if="objType == '3'">查询条件</th>
            </tr>
            </thead>
            <tbody>
              <tr v-for="item in tabFields">
                <td >
                  <label class="checkbox00" >
                    <input type="checkbox" @click="selectAll($event)" v-model="checked" :value="item.tabFieldId" name="checkbox"><span></span>
                  </label>
                </td>
                <td>{{item.tabFieldId}}</td>
                <td>{{item.tabFieldComment}}</td>
                <td>{{item.tabComment}}</td>
                <td>{{item.tabFieldType}}</td>
                <td>{{item.tabFieldLength}}</td>
                <td v-if="objType == '1' || objType == '2'">
                  <label class="checkbox00" >
                    <input type="checkbox" v-model="item.objKey" name="checkbox"><span></span>
                  </label>
                </td>
                <td v-if="objType == '3'">
                  <select v-model="item.condition">
                    <option value="=">等于</option>
                    <option value=">">大于</option>
                    <option value="<">小于</option>
                    <option value=">=">大于等于</option>
                    <option value="<=">小于等于</option>
                    <option value="like">模糊</option>
                  </select>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
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
  import '../../static/js/jquery.multi-select'
  export default{
      name: '',
      data(){
        return {
          objName: '',
          objType: '',
          tabs: [],
          tabIds: [],
          tabFields: [],
          objStatus: '',
          objId: '',
          array: [],
          checked: [],
          searchForm: [],
          addForm: [],
          searchFormId: '',
          addFormId: ''
        }
      },
      watch:{
        change(){
          this.searchDetails();
        }
      },
      computed: {
        change() {
          this.objId = this.$parent.$parent.objId;
          return this.objId;
        }
      },
      mounted(){
        this.initTabAll();
      },
      methods: {
        init(){
          var $this = this;
          $('#optgroup').multiSelect({
            selectableHeader: '<div class=""><i class="fa fa-search search-i pull-right p-r-sm"></i><input  type="text" placeholder="查找数据表" class="search-table"/></div>',
            selectionHeader: '<div class="header">已选数据表</div>',
            selectableOptgroup: true,
            afterSelect: function (values) {
                console.log("1233");
                for(var i=0,len=values.length;i<len; i++){
                  $this.tabIds.push(values[i]);
                }
                $this.searchFieldList();
            },
            afterDeselect: function (values) {
                for(var i=0,len=values.length;i<len; i++){
                  var value = values[i];
                  var index = $this.tabIds.indexOf(value);
                  $this.tabIds.splice(index,1);
                  $this.searchFieldList();
                }

            }
          });
          $this.setType();
          $this.setStatus();
        },

        /**
         * 设置类型
         */
        setType(){
          var $this = this;
          $this.$nextTick(()=>{
            $("#select-type").selectFilter({
              callBack : function (val){
                $this.objType = val;
                $this.searchAllForms();
              }
            });
          })
        },

        /**
         * 查找对象详细
         */
        searchDetails(){
            var objId = this.objId;
            var $this = this;
            $this.clear();
            if(objId){
              var param = {};
              param['objId'] = this.objId;
              $this.$httpService.getObjDetails(param).then((res)=>{
                if(res.code == '2000'){
                  var data = res.data;
                  $this.objName = data.objName;
                  $this.objType = data.objType;
                  $this.objStatus = data.objStatus;
                  $this.setType();
                  $this.setStatus();
                  data.tabIds.forEach((obj,index)=>{
                    $this.array.push(obj.tabId);
                    obj.fieldIds.forEach((obj,index)=>{
                      $this.checked.push(obj);
                    })
                  });
                  $('#optgroup').multiSelect('select', $this.array);
                }
              })
            }
        },

        /**
         * 清空
         */
        clear(){
          this.objName = "";
          this.objType = "";
          this.objStatus = "";
          this.tabIds = [];
          $("#select-type input").val("");
          $("#select-status input").val("");
          $('#optgroup').multiSelect('deselect',this.array);
          this.array = [];
        },

        /**
         * 设置状态
         */
        setStatus(){
          var $this = this;
          $this.$nextTick(()=>{
            $("#select-status").selectFilter({
              callBack: function(val){
                $this.objStatus = val;
              }
            })
          })
        },

        /**
         * 多选
         */
        selectAll($event){
          var checked = $($event.target);
          var val = $($event.target).val();
          if(checked.is(":checked")){
            this.checked.push(val);
          }else{
            var index = this.checked.indexOf(val);
            this.checked.splice(index,1);
          }
        },

        /**
         * 初始化所有表
         */
        initTabAll(){
          this.$httpService.getTabAll().then((res)=>{
            if(res.code == '2000'){
                this.tabs = res.data;
                this.$nextTick(()=>{
                    this.init();
                })
            }
          })
        },

        /**
         * 查找字段列表
         */
        searchFieldList(){
            this.tabFields = [];
            console.log("12323");
            if(this.tabIds.length > 0){
              var param = {};
              var json = {'tabIds':this.tabIds};
              param['params'] = JSON.stringify(json)
              this.$httpService.getTabFieldList(param).then((res)=>{
                if(res.code == '2000'){
                  this.tabFields = res.data.fields;
                }
              })
            }
        },

        /**
         * 查询所有表单
         */
        searchAllForms(){
            if(this.objType == 2){
              this.$httpService.getAllForms().then((res)=>{
                if(res.code == '2000'){
                  this.searchForm = res.data;
                  this.addForm = res.data;
                  this.setSearchForm();
                  this.setAddForm();
                }
              })
            }
        },

        /**
         * 设置查询表单列
         */
        setSearchForm(){
          var $this = this;
          $this.$nextTick(()=>{
            $("#searchForm").selectFilter({
              callBack: function(val){
                $this.searchFormId = val;
              }
            })
          });
        },

        /**
         * 设置添加表单列
         */
        setAddForm(){
          var $this = this;
          $this.$nextTick(()=>{
            $("#addForm").selectFilter({
              callBack: function(val){
                $this.addFormId = val;
              }
            })
          })
        },

        /**
         * 保存
         */
        save(){
          if(this.$stringUtil.validation("objectForm")){
            var param = {};
            var tabs = []
            var data = this.tabIds;
            var fields = this.tabFields;
            for(var i=0,len=data.length;i<len;i++){
              var tabId = data[i];
              var fieldArray = [];
              for(var j=0,leng=fields.length;j<leng;j++){
                var field = fields[j];
                var json = {};
                if(field.tabId == tabId){
                  this.checked.forEach((obj,index)=>{
                      if(obj == field.tabFieldId){
                        json['fieldId'] = field.tabFieldId;
                        json['condition'] = field.condition;
                        json['objKey'] = field.objKey;
                        fieldArray.push(json)
                      }
                  })
                }
              }
              if(fieldArray.length == 0){continue};
              var json = {};
              json['tabId'] = tabId;
              json['fields'] = fieldArray;
              tabs.push(json)
            }
            param['objId'] = this.objId;
            param['objName'] = this.objName;
            param['objType'] = this.objType;
            param['objStatus'] = this.objStatus;
            param['searchFormId'] = this.searchFormId;
            param['addFormId'] = this.addFormId;
            param['params'] = JSON.stringify(tabs);
            this.$httpService.saveObj(param).then((res)=>{
              if(res.code == '2000'){
                  this.$parent.$parent.init();
                  this.$parent.$parent.$parent.hidePanel();
              }
            })
          }
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
  @import "../../static/css/multi-select.css";
  .group{
    padding: 10px;
  }
  .type{
    float: left;
  }
  .form-group{
    border: 1px solid #efefef;
    width: 100%;
    height: 400px;
    padding: 10px;
  }
  .search-table{
    height: 30px;
    line-height: 30px;
    border: 1px solid #efefef;
    width: 100%;
    border-radius: 5px;
    text-indent: 10px;
  }
  .search-i{
    position:relative;
    top:21px;
    z-index:1;
    right: 5px;
    cursor: pointer;
  }
</style>

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
            <option value="">选择对象</option>
            <option value="1">添加对象</option>
            <option value="2">列表对象</option>
            <option value="3">查询对象</option>
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
            <option value="">选择状态</option>
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
          <table class="table table-bordered">
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
            </tr>
            </thead>
            <tbody>
              <tr v-for="item in tabFields">
                <td >
                  <label class="checkbox00" >
                    <input type="checkbox" v-model="item.checkbox" value="1" name="checkbox"><span></span>
                  </label>
                </td>
                <td>{{item.tabFieldId}}</td>
                <td>{{item.tabFieldComment}}</td>
                <td>{{item.tabComment}}</td>
                <td>{{item.tabFieldType}}</td>
                <td>{{item.tabFieldLength}}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </section>
    <div class="submit-btn">
      <ul>
        <li class="btn-cancel"><a href="javascript:;">取消</a></li>
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
                $this.tabIds.push(values[0]);
                $this.searchFieldList();
            },
            afterDeselect: function (values) {
                var value = values[0];
                var index = $this.tabIds.indexOf(value);
                $this.tabIds.splice(index,1);
                $this.searchFieldList();
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
          $("#select-type").selectFilter({
            callBack : function (val){
              $this.objType = val;
            }
          });
        },

        /**
         * 设置状态
         */
        setStatus(){
          var $this = this;
          $("#select-status").selectFilter({
            callBack: function(val){
              $this.objStatus = val;
            }
          })
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
         * 保存
         */
        save(){
          if(this.$stringUtil.validation("objectForm")){
            var param = {};
            var tabs = []
            var data = this.tabIds;
            var fields = this.tabFields;
            for(var i=0,len=data.length;i<len;i++){
              var json = {};
              var tabId = data[i];
              var fieldArray = [];
              for(var j=0,leng=fields.length;j<leng;j++){
                var field = fields[j];
                if(field.checkbox && field.tabId == tabId){
                  fieldArray.push(field.tabFieldId)
                }
              }
              json['tabId'] = tabId;
              json['fieldIds'] = fieldArray;
              tabs.push(json)
            }
            param['objName'] = this.objName;
            param['objType'] = this.objType;
            param['objStatus'] = this.objStatus;
            param['params'] = JSON.stringify({"tabIds":tabs});
            this.$httpService.saveObj(param).then((res)=>{
              console.log(res);
            })
          }
        }
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

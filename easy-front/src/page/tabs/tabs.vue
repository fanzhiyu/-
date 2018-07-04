<template>
  <div name="tabs">
    <my-panel styles="width:40%"></my-panel>
    <div class="titles" style="margin-top:50px">
      <div class="title1 border-bottom">数据表列表</div>
    </div>
    <div class="process-table">
      <div class="search">

        <div class="control-inputs">
          <label>表&nbsp;&nbsp;名&nbsp;&nbsp;称：</label>
          <input type="text" v-model="tabComment"/>
        </div>
        <div class="control-inputs">
          <label>创建时间：</label>
          <input type="text" @click="startTime($event)" placeholder="开始时间" class="date" readonly="true" style="width: 32%" />
          - - <input type="text" class="date" @click="endTime($event)"  placeholder="结束时间" readonly="true" style="width: 32%" />
        </div>
        <div class="control-inputs">
          <label>创&nbsp;&nbsp;建&nbsp;&nbsp;人：</label>
          <input type="text" v-model="createUser" />
        </div>
        <div class="clear"></div>
        <div class="control-inputs">
          <label>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
          <input type="text" v-model="tabComment" />
        </div>
        <div class="control-inputs">
          <div style="float: left">
            <label>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</label>
          </div>
          <div class='filter-box' id="search-type" style="width: 70%">
            <div class='filter-text'>
              <input class='filter-title' type='text' readonly placeholder='选择表类型' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='select' v-model="tabType">
              <option v-for="item in tabTypes" :value="item.code">{{item.name}}</option>
            </select>
          </div>
        </div>
        <div class="search-btn"><a href="javascript:;" @click="initTabPager()">查询</a></div>
        <div class="clear"></div>
      </div>
      <div class="user-table">
        <div class="toolbar">
          <ul>
            <div class="control-btn" >
              <a href="javascript:;" class="btns" @click="showTabsAdd()">创建表</a>
            </div>
            <li>
              <a href="javascript:;" :class="active ? 'btns' : ''" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? modTab() : ''">修改</a>
            </li>
            <li>
              <a href="javascript:;" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? delTab() : ''">删除</a>
            </li>
            <li>
              <a href="javascript:;" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? lookTabStructure() : ''">查看表结构</a>
            </li>
            <li>
              <a href="javascript:;" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? lookTabData() : ''">查看表数据</a>
            </li>
          </ul>
        </div>
        <div class="tables">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th width="3%">
                  选择
                </th>
                <th width="5%">编号</th>
                <th width="10%">表名</th>
                <th width="10%">备注</th>
                <th width="10%">类型</th>
                <th width="10%">创建人</th>
                <th width="10%">创建时间</th>
                <th width="5%">修改人</th>
                <th width="10%">修改时间</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in tabs">
                <td>
                  <label class="checkbox00">
                    <input type="checkbox" @click="selectOne($event)" v-model="checked" :value="item.tabId" name="checkbox"><span></span>
                  </label>
                </td>
                <td>{{item.tabId}}</td>
                <td>{{item.tabSourceName}}</td>
                <td>{{item.tabComment}}</td>
                <td>{{item.tabType}}</td>
                <td>{{item.createrUser}}</td>
                <td>{{item.createDate}}</td>
                <td>{{item.updaterUser}}</td>
                <td>{{item.updateDate}}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <my-pager :page-index="currentPage" :total="count" :page-size="pageSize" @change="pageChange"></my-pager>
      </div>
    </div>
  </div>
</template>
<script>
  import {Indicator, MessageBox, Toast} from "mint-ui";
  import tabStructure from './tabStructure.vue'
  import tabData from './tabData.vue'
  export default{
      name: 'tabs',
      data(){
          return{
            pageSize : 10 , //每页显示20条数据
            currentPage : 1, //当前页码
            count : 0, //总记录数
            tabs: [],
            tabId: '',
            tabName: '',
            tabComment: '',
            createUser: '',
            tabTypes: [],
            tabType: '',
            startDate: '',
            endDate: '',
            checked: [],
            active: ''
          }
      },
      mounted(){
        this.init();
      },
      methods: {
        init(){
          this.initTabType();
          this.initTabPager();
        },

        /**
         * 初始化表类型
         */
        initTabType(){
          var $this = this;
          $this.$httpService.getTabType().then((res)=>{
              if(res.code == '2000'){
                var json = {};
                json['code'] = '';
                json['name'] = '全部';
                $this.tabTypes.push(json);
                for(var i=0,len=res.data.length;i<len;i++){
                  $this.tabTypes.push(res.data[i]);
                }
                $this.$nextTick(()=>{
                  $("#search-type").selectFilter({
                    callBack : function (val){
                      $this.tabType = val;
                    }
                  });
                })
              }
          })
        },

        /**
         * 单选
         */
        selectOne($event){
          this.checked = [];
          var checked = $($event.target).parents("table").find("tbody input[type='checkbox']:checked");
          if(checked.length == 0){
            this.active = false;
          }else{
            this.active = true;
            var val = $($event.target).val();
            this.checked.push(val);
          }
        },

        /**
         * 初始化数据表
         */
        initTabPager(){
          var $this = this;
          var param = {};
          param['pageSize'] = this.pageSize;
          param['pageNo'] = this.currentPage;
          param['tabName'] = this.tabName;
          param['tabComment'] = this.tabComment;
          param['createUser'] = this.createUser;
          param['tabType'] = this.tabType;
          param['startDate'] = this.startDate;
          param['endDate'] = this.endDate;
          $this.$httpService.getTabPager(param).then((res)=>{
            if(res.code == '2000'){
              this.count = res.total;
              this.tabs = res.rows;
            }
          })
        },

        /**
         * 查看表结构
         */
        lookTabStructure(){
          var message = "";
          this.tabs.forEach((obj,index)=>{
              if(obj.tabId == this.checked[0]){
                  message = obj.tabComment;
              }
          });
          this.$parent.popshow = true;
          this.$parent.message = message;
          this.$parent.content = tabStructure;
          this.$parent.style = "width:600px;";
          this.$parent.params = {"tabId":this.checked[0]}
        },

        /**
         * 查找表数据
         */
        lookTabData(){
          var message = "";
          this.tabs.forEach((obj,index)=>{
            if(obj.tabId == this.checked[0]){
              message = obj.tabComment;
            }
          });
          this.$parent.popshow = true;
          this.$parent.message = message;
          this.$parent.content = tabData;
          this.$parent.style = "width:700px;";
          this.$parent.params = {"tabId":this.checked[0]}
        },

        /**
         * 删除表
         */
        delTab(){
          var $this = this;
          $this.$parent.warning = true;
          $this.$parent.msg = "确定要删除么？"
          $this.$parent.callback = function(){
              var param = {};
              param['tabId'] = $this.checked[0];
              $this.$httpService.removeTab(param).then((res)=>{
                  console.log(res);
              })
          }
        },

        /**
         * 页面数据发生改变
         * @param page
         */
        pageChange (page) {
          this.currentPage = page
          this.initTabPager()
        },

        /**
         * 添加数据表
         */
        showTabsAdd(){
          this.tabId = "";
          this.$router.push({path:"/tabs-add"})
          this.$parent.showPanel();
        },

        /**
         * 修改
         */
        modTab(){
          this.tabId = this.checked[0];
          this.$router.push({path:"/tabs-add"})
          this.$parent.showPanel();
        },

        /**
         * 开始时间
         */
        startTime($event){
          var $this = this;
          window.onTime($event.target,function(v){
            $this.startDate = v;
          })
        },

        /**
         * 结束时间
         * @param $event
         */
        endTime($event){
          var $this = this;
          window.onTime($event.target,function(v){
            $this.endDate = v;
          })
        }
      }
  }
</script>
<style>
</style>

<template>
  <div>
    <my-panel styles="width:40%"></my-panel>
    <div class="titles" style="margin-top:50px">
      <div class="title1 border-bottom">数据对象列表</div>
    </div>
    <div class="process-table">
      <div class="search">
        <div class="control-inputs">
          <label>对&nbsp;&nbsp;象&nbsp;&nbsp;名：</label>
          <input type="text" v-model="objName"/>
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
          <div style="float: left">
            <label>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：&nbsp;</label>
          </div>
          <div class='filter-box' id="search-type" style="width: 70%">
            <div class='filter-text'>
              <input class='filter-title' type='text' readonly placeholder='选择表类型' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='select' v-model="type">
              <option value="">全部</option>
              <option value="1">添加对象</option>
              <option value="2">列表对象</option>
              <option value="2">查询对象</option>
            </select>
          </div>
        </div>
        <div class="search-btn"><a href="javascript:;" @click="search()">查询</a></div>
        <div class="clear"></div>
      </div>
      <div class="user-table">
        <div class="toolbar">
          <ul>
            <div class="control-btn" >
              <a href="javascript:;" class="btns" @click="showObjAdd()">创建对象</a>
            </div>
            <li>
              <a href="javascript:;" :class="active ? 'btns' : ''" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? modObj() : ''">修改</a>
            </li>
            <li>
              <a href="javascript:;" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? delObj() : ''">删除</a>
            </li>
          </ul>
        </div>
        <div class="tables">
          <table class="table table-bordered">
            <thead>
            <tr>
              <th width="5%">
                选择
              </th>
              <th width="5%">编号</th>
              <th width="10%">对象名</th>
              <th width="10%">类型</th>
              <th width="10%">状态</th>
              <th width="10%">创建人</th>
              <th width="10%">创建时间</th>
              <th width="5%">修改人</th>
              <th width="10%">修改时间</th>
            </tr>
            </thead>
            <tbody>
              <tr v-for="item in objs">
                <td>
                  <label class="checkbox00" >
                    <input type="checkbox" @click="selectOne($event)" :value="item.objId" name="checkbox"><span></span>
                  </label>
                </td>
                <td>{{item.objId}}</td>
                <td>{{item.objName}}</td>
                <td>
                  <div v-if="item.objType == '1'">
                    添加对象
                  </div>
                  <div v-if="item.objType == '2'">
                    列表对象
                  </div>
                  <div v-if="item.objType == '3'">
                    查询对象
                  </div>
                </td>
                <td>
                  <div v-if="item.objStatus == '1'">
                    启用
                  </div>
                  <div v-if="item.objStatus == '2'">
                    禁用
                  </div>
                </td>
                <td>{{item.createUser}}</td>
                <td>{{item.createDate}}</td>
                <td>{{item.updateUser}}</td>
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
  export default{
      name: '',
      data(){
          return {
            pageSize : 10 , //每页显示20条数据
            currentPage : 1, //当前页码
            count : 0, //总记录数
            objId: '',
            objName: '',
            startDate: '',
            endDate: '',
            createUser: '',
            objs: [],
            checked: [],
            type: '',
            active: false,
          }
      },
      mounted(){
        this.init();
      },
      methods: {

        init(){
          var param = {};
          param['pageSize'] = this.pageSize;
          param['pageNo'] = this.currentPage;
          param['objName'] = this.objName;
          param['startDate'] = this.startDate;
          param['endDate'] = this.endDate;
          param['createUser'] = this.createUser;
          param['objType'] = this.type;
          this.$httpService.getObjPager(param).then((res)=>{
            if(res.code == '2000'){
                this.objs = res.rows;
                this.count = res.total;
            }
          });
          this.setType();
        },

        /**
         * 设置类型
         */
        setType(){
          var $this = this;
          $("#search-type").selectFilter({
            callBack : function (val){
              $this.type = val;
            }
          });
        },

        /**
         * 查找
         */
        search(){
          this.init();
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
         * 页面数据发生改变
         * @param page
         */
        pageChange (page) {
          this.currentPage = page
          this.init()
        },

        /**
         * 新建对象
         */
        showObjAdd(){
          this.objId = "";
          this.$router.push({path:"/object-add"})
          this.$parent.showPanel();
        },

        /**
         * 修改对象
         */
        modObj(){
          this.objId = this.checked[0];
          this.$router.push({path:'/object-add'})
          this.$parent.showPanel();
        },

        /**
         * 删除对象
         */
        delObj(){
          var $this = this;
          $this.$parent.warning = true;
          $this.$parent.msg = "确定要删除么？"
          $this.$parent.callback = function(){
              var param = {};
              param['objId'] = $this.checked[0];
              $this.$httpService.removeObj(param).then((res)=>{
                  console.log(res);
              })
          }
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
         */
        endTime($event){
          var $this = this;
          window.onTime($event.target,function(v){
            $this.endDate = v;
          })
        },
      }
  }
</script>
<style>
</style>

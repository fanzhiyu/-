<template>
  <div name="role">
    <my-panel :styles="style"></my-panel>
    <div class="titles" style="margin-top:50px">
      <div class="title1 border-bottom">角色列表</div>
    </div>
    <div class="process-table">
      <div class="search">
        <div class="control-inputs">
          <label>编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
          <input type="text" v-model="roleCode" />
        </div>
        <div class="control-inputs">
          <label>角&nbsp;&nbsp;色&nbsp;&nbsp;名：</label>
          <input type="text" v-model="roleName" />
        </div>
        <div class="control-inputs">
          <label>创&nbsp;&nbsp;建&nbsp;&nbsp;人：</label>
          <input type="text" v-model="createUser" />
        </div>
        <div class="clear"></div>
        <div class="control-inputs">
          <label>创建时间：</label>
          <input type="text" @click="startTime($event)" placeholder="开始时间" class="date" readonly="true" style="width: 32%" />
          - - <input type="text" class="date" @click="endTime($event)"  placeholder="结束时间" readonly="true" style="width: 32%" />
        </div>
        <div class="control-inputs">
          <div style="float: left">
            <label>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：&nbsp;</label>
          </div>
          <div class='filter-box' id="search-type" style="width: 70%">
            <div class='filter-text'>
              <input class='filter-title' type='text' readonly placeholder='角色类型' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='select'>
              <option value="">全部</option>
              <option value="1">任务分配</option>
              <option value="2">管理角色</option>
              <option value="3">普通角色</option>
            </select>
          </div>
        </div>
        <div class="control-inputs">
          <div style="float: left">
            <label>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：&nbsp;</label>
          </div>
          <div class='filter-box' id="search-status" style="width: 70%">
            <div class='filter-text'>
              <input class='filter-title' type='text' readonly placeholder='角色状态' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='select'>
              <option value="">全部</option>
              <option value="1">启动</option>
              <option value="2">禁用</option>
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
              <a href="javascript:;" class="btns" @click="showRole()">创建角色</a>
            </div>
            <li>
              <a href="javascript:;" :class="active ? 'btns' : ''" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? modRole() : ''">修改</a>
            </li>
            <li>
              <a href="javascript:;" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? delRole() : ''">删除</a>
            </li>
            <li>
              <a href="javascript:;" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? roleAuth() : ''">权限</a>
            </li>
          </ul>
        </div>
        <div class="tables">
          <table class="table table-bordered">
            <thead>
            <tr>
              <th width="3%" >
                选择
              </th>
              <th width="5%">编号</th>
              <th width="10%">角色编号</th>
              <th width="10%">角色名</th>
              <th width="10%">状态</th>
              <th width="10%">类型</th>
              <th width="10%">可见范围</th>
              <th width="10%">创建人</th>
              <th width="10%">创建时间</th>
            </tr>
            </thead>
            <tbody>
              <tr v-for="(item,index) in roleList">
                <td>
                  <label class="checkbox00" >
                    <input type="checkbox" name="checkbox" v-model="checked" :value="item.roleId"><span></span>
                  </label>
                </td>
                <td>{{index+1}}</td>
                <td>{{item.roleCode}}</td>
                <td>{{item.roleName}}</td>
                <td>
                  <div v-if="item.roleStatus == '1'">启用</div>
                  <div v-if="item.roleStatus == '2'">禁用</div>
                </td>
                <td>
                  <div v-if="item.roleType == '1'">任务分配</div>
                  <div v-if="item.roleType == '2'">管理角色</div>
                  <div v-if="item.roleType == '3'">普通角色</div>
                </td>
                <td>{{item.rangeName}}</td>
                <td>{{item.createUser}}</td>
                <td>{{item.createDate}}</td>
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
  import roleAuth from './roleAuth.vue'
  var obj;
  export default{
      name: 'role',
      data(){
          return{
            pageSize : 10 , //每页显示20条数据
            currentPage : 1, //当前页码
            count : 0, //总记录数
            style: 'width:30%',
            roleList: [],
            checked: [],
            active: false,
            startDate: '',
            endDate: '',
            roleCode: '',
            roleName: '',
            createUser: '',
            roleType: '',
            roleStatus: '',
            roleId: ''
          }
      },
      mounted(){
        this.init();
      },
      methods: {

        /**
         * 初始化
         */
        init(){
          var param = {};
          param['pageNo'] = this.currentPage;
          param['pageSize'] = this.pageSize;
          param['roleCode'] = this.roleCode;
          param['roleName'] = this.roleName;
          param['createUser'] = this.createUser;
          param['roleType'] = this.roleType;
          param['roleStatus'] = this.roleStatus;
          param['startDate'] = this.startDate;
          param['endDate'] = this.endDate;
          var $this = this;
          $this.$httpService.getRolePager(param).then((res)=>{
            if(res.code == '2000'){
              $this.roleList = res.rows;
              $this.count = res.total;
              $this.initCheckbox();
            }
          });
          this.setType();
          this.setStatus();

        },

        /**
         * 查询
         */
        search(){
          this.init();
        },

        /**
         * 设置类型
         */
        setType(){
            var $this = this;
            $("#search-type").selectFilter({
              callBack: (val)=>{
                $this.roleType = val;
              }
            })
        },

        /**
         * 设置状态
         */
        setStatus(){
            var $this = this;
            $("#search-status").selectFilter({
              callBack: (val)=>{
                $this.roleStatus = val;
              }
            })
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
         * 选中
         */
        initCheckbox(){
          this.roleId ? this.checked.push(roleId) : this.checked = [];
          this.$nextTick(()=>{
            $("table tbody input[type='checkbox']").change((e)=>{
              this.checked = [];
              var checked = $(e.target).parents("table").find("tbody input[type='checkbox']:checked");
              if(checked.length == 0){
                this.active = false;
              }else{
                this.active = true;
                this.checked.push($(e.target).val());
              }
            })
          })
        },

        /**
         * 显示添加面板
         */
        showRole(){
          this.roleId = "";
          this.$router.push({path:'role-add'})
          this.$parent.showPanel();
        },

        /**
         * 删除角色
         */
        delRole(){
          var $this = this;
          $this.$parent.warning = true;
          $this.$parent.msg = "确定要删除么？"
          $this.$parent.callback = function(){
              var param = {};
              param['roleId'] = $this.checked[0];
              $this.$httpService.removeRole(param).then((res)=>{
                  if(res.code == '2000'){
                      $this.init();
                  }
              })
          }
        },

        /**
         * 权限管理
         */
        roleAuth(){
          this.$parent.popshow = true;
          this.$parent.message = "选择权限";
          this.$parent.style = "width:400px;height:420px;"
          this.$parent.content = roleAuth;
          this.$parent.params = {"roleId":this.checked[0]};
        },

        /**
         * 查找角色
         */
        modRole(){
            if(this.checked.length > 1){
              Toast("不能选择两条或两条以上的角色");
              return;
            }
            this.roleId = this.checked[0];
            this.$router.push({name:'roleAdd'});
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

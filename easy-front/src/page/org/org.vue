<template>
  <div name="org" class="org">
    <my-panel :styles="style"></my-panel>
    <div id="tree" class="overflow" style="height: 600px;width:20%;float:left;"></div>
    <div class="next-right">
      <section>
        <div class="org-title">
          <h4>组织人员管理</h4>
        </div>
        <div class="org-search">
          <i class="fa fa-search"></i><input type="text" placeholder="号码，姓名收索人员部门" />
        </div>
        <div class="clearfix"></div>
        <div class="user-table">
          <div class="toolbar">
            <ul>
              <div class="control-btn" >
                <a href="javascript:;" class="btns" @click="userShow()">添加成员</a>
              </div>
              <li>
                <a href="javascript:;" :class="active ? 'btns' : ''" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? modUser() : ''">修改</a>
              </li>
              <li>
                <a href="javascript:;" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? delUser() : ''">删除</a>
              </li>
              <li>
                <a href="javascript:;" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? roleUser() : ''">角色</a>
              </li>
              <li>
                <a href="javascript:;" :class="active ? 'btns' : ''" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? createAccount() : ''">创建账号</a>
              </li>
              <li>
                <a href="javascript:;" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? outUser() : ''">离职</a>
              </li>
            </ul>
          </div>
          <div class="tables user-table overflow" style="height: 300px;">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th width="3%" >
                    选择
                  </th>
                  <th width="4%">编号</th>
                  <th width="10%">工号</th>
                  <th width="10%">姓名</th>
                  <th width="10%">电话</th>
                  <th width="10%">所在组织</th>
                  <th width="10%">邮箱</th>
                  <th width="10%">创建时间</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(item,index) in userList">
                  <td>
                    <label class="checkbox00" >
                      <input type="checkbox" v-model="checked" :value="item.userId" name="checkbox"><span></span>
                    </label>
                  </td>
                  <th scope="row">{{index+1}}</th>
                  <td>{{item.userCode}}</td>
                  <td>{{item.userName}}</td>
                  <td>{{item.userTel}}</td>
                  <td>{{item.orgName}}</td>
                  <td>{{item.userEmail}}</td>
                  <td>{{item.createTime}}</td>
                </tr>
              </tbody>
            </table>
          </div>
          <my-pager :page-index="currentPage" :total="count" :page-size="pageSize" @change="pageChange"></my-pager>
        </div>
        <div class="org-table">
          <div class="toolbar">
            <ul>
              <div class="control-btn">
                <a href="javascript:;" class="btns" @click="orgShow()">添加组织/部门</a>
              </div>
              <li>
                <a href="javascript:;" :class="orgActive ? 'btns' : ''" :style="orgActive ? 'color: #00C6D7' : ''" @click.sync="orgActive ? modOrg() : ''">修改</a>
              </li>
              <li>
                <a href="javascript:;" :style="orgActive ? 'color: #00C6D7' : ''" @click.sync="orgActive ? delOrg() : ''">删除</a>
              </li>
              <li>
                <a href="javascript:;" :style="orgActive ? 'color: #00C6D7' : ''" @click.sync="orgActive ? authOrg() : ''">权限</a>
              </li>
            </ul>
          </div>
          <div class="tables overflow" style="height:200px;">
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th width="3%" style="padding-left: 10px;">
                    选择
                  </th>
                  <th width="4%">编号</th>
                  <th width="15%">上级组织</th>
                  <th width="15%">组织名/部门名</th>
                  <th width="15%">所在地</th>
                  <th width="15%">类型</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(item,index) in orgList">
                  <td>
                    <label class="checkbox00" style="padding-left: 2px;">
                      <input type="checkbox" v-model="orgChecked" :value="item.orgId" @change="checkboxBtn($event)" name="checkbox"><span></span>
                    </label>
                  </td>
                  <th scope="row">{{index+1}}</th>
                  <td>{{item.parentName}}</td>
                  <td>{{item.orgName}}</td>
                  <td>{{item.provinceName}} - {{item.cityName}} - {{item.orgAddress}}</td>
                  <td >
                    <div v-if="item.orgType == '1'">组织</div>
                    <div v-else-if="item.orgType == '2'">部门</div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>
<script>
  import {Indicator, MessageBox, Toast} from "mint-ui";
  import userRole from './userRole.vue';
  import orgAuth from './orgAuth.vue';
  export default{
    name: 'org',
    data(){
      return {
        toggle: true,
        pageSize : 10 , //每页显示20条数据
        currentPage : 1, //当前页码
        count : 0, //总记录数
        active: false,
        style: "width:30%",
        parentId: '',
        userList: [],
        orgList: [],
        roleHtml: '',
        checked: [],
        orgChecked: [],
        orgActive: false,
        orgId: '',
      }
    },
    mounted(){
      this.init();
      this.initCheckbox();
      this.getOrgList();
    },
    methods:{

      /**
       * 页面初始化
       */
      init(){
        var $this = this;
        $this.$httpService.getOrgTree().then((res)=>{
            var data = res.data;
            $('#tree').on("changed.jstree",function(e,data){
              if(data.selected.length) {
                $this.active = false;
                $this.parentId = data.selected[0];
                $this.orgActive = false;
                $this.getUser();
                $this.getOrgList();
              }
            }).jstree({
              'core' : {
                'data' : data,
              },
            });
        });
      },

      /**
       * 选中
       */
      initCheckbox(){
          this.checked = [];
          this.$nextTick(()=>{
            $(".user-table tbody input[type='checkbox']").change((e)=>{
              var checked = $(e.target).parents("table").find("input[type='checkbox']:checked");
              if(checked.length == 0){
                  this.active = false;
              }else{
                  this.active = true;
              }
            })
          })
      },

      /**
       * 选择组织
       */
      checkboxBtn($event){
          this.orgChecked = [];
          var checked = $($event.target).parents("table").find("tbody input[type='checkbox']:checked");
          if(checked.length == 0){
            this.orgActive = false;
          }else{
            this.orgActive = true;
            var val = $($event.target).val();
            this.orgChecked.push(val);
          }
      },

      /**
       * 修改角色
       */
      modOrg(){
        this.orgId = this.orgChecked[0];
        this.$router.push({path:'/addOrg'});
        this.$parent.showPanel();
      },

      /**
       * 删除组织
       */
      delOrg(){
        var $this = this;
        $this.$parent.warning = true;
        $this.$parent.msg = "确定要删除么？"
        $this.$parent.callback = function(){
            var param = {};
            param['orgId'] = $this.orgChecked[0];
            console.log(param);
            $this.$httpService.removeOrg(param).then((res)=>{
              console.log(res);
            })
        }
      },

      /**
       * 组织权限
       */
      authOrg(){
        this.$parent.popshow = true;
        this.$parent.message = "选择权限";
        this.$parent.style = "width:400px;height:420px;"
        this.$parent.content = orgAuth;
        this.$parent.params = {"orgId": this.orgChecked[0]};
      },

      /**
       * 获取所有用户
       */
      getUser(){
          var param = {};
          this.active = false;
          param['orgId'] = this.parentId;
          this.$httpService.getUserByOrg(param).then((res)=>{
              if(res.code == '2000'){
                  this.userList = res.rows;
                  this.count = res.total;
                  this.initCheckbox();
              }
          })
      },

      /**
       * 删除用户
       */
      delUser(){
        var $this = this;
        $this.$parent.warning = true;
        $this.$parent.msg = "确定要删除么？"
        $this.$parent.callback = function(){
            var param = {};
            var lists = $this.checked;
            var array = [];
            for(var i=0,len=lists.length;i<len;i++){
                var list = lists[i];
                var json = {};
                json['userId'] = list;
                array.push(json)
            }
            param['params'] = JSON.stringify(array);
            $this.$httpService.removeUser(param).then((res)=>{
                if(res.code == '2000'){
                  $this.getUser();
                }
            })
        }
      },

      /**
       * 修改
       */
      modUser(){
        this.$router.push({path:'/user',query:{userId:this.checked[0],orgId:this.parentId}})
        this.$parent.showPanel();
      },

      /**
       * 选择角色
       */
      roleUser(){
        this.$parent.popshow = true;
        this.$parent.message = "选择角色";
        this.$parent.style = "width:700px;height:420px;"
        this.$parent.content = userRole;
        this.$parent.params = {"userId":this.checked[0]};
      },

      /**
       * 创建账号
       */
      createAccount(){
        if(this.checked.length > 1){
          Toast("不能选择两条或两条以上的用户");
          return;
        }
        this.$router.push({path:'/userAccount',query:{userId:this.checked[0]}})
        this.$parent.showPanel();
      },

      /**
       * 离职
       */
      outUser(){
        console.log("离职")
      },

      /**
       * 成员面板打开
       */
      userShow(){
        this.$router.push({path:'/user',query:{orgId:this.parentId}})
        this.$parent.showPanel();
      },


      /**
       * 根据父节点id查找组织
       */
      getOrgList(){
          var $this = this;
          this.orgChecked = [];
          var param = {};
          param['parentId'] = this.parentId;
          $this.$httpService.getOrgByParentId(param).then((res)=>{
              if(res.code == '2000'){
                $this.orgList = res.data;
              }
          })
      },

      /**
       * 添加组织面板
       */
      orgShow(){
        this.orgId = "";
        this.$router.push({path:'/addOrg'});
        this.$parent.showPanel();
      },

      /**
       * 页面数据发生改变
       * @param page
       */
      pageChange (page) {
        this.currentPage = page
        this.init()
      },
    }
  }
</script>
<style>
  @import "org.scss";
  @import "../../static/css/style.min.css";
</style>

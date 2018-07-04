<template>
  <div name="auth" class="auth">
    <my-panel :styles="style"></my-panel>
    <div id="tree" class="overflow" style="height: 600px;width:20%;float:left;"></div>
    <div class="left" style="width: 80%">
      <div class="titles left" style="width:100%">
        <div class="title1 border-bottom">权限列表</div>
      </div>
      <div class="clear"></div>
      <div class="search" style="margin-top: 10px;">
        <div class="control-inputs">
          <label>菜单编号：</label>
          <input type="text" />
        </div>
        <div class="control-inputs">
          <label>菜&nbsp;&nbsp;单&nbsp;&nbsp;名：</label>
          <input type="text" />
        </div>
        <div class="control-inputs">
          <label>创建时间：</label>
          <input type="text" @click="startTime($event)" placeholder="开始时间" class="date" readonly="true" style="width: 32%" />
          - - <input type="text" class="date" @click="endTime($event)"  placeholder="结束时间" readonly="true" style="width: 32%" />
        </div>
        <div class="clear"></div>
        <div class="control-inputs">
          <label>创&nbsp;&nbsp;建&nbsp;&nbsp;人：</label>
          <input type="text" />
        </div>
        <div class="search-btn"><a href="javascript:;" @click="search()">查询</a></div>
        <div class="clear"></div>
      </div>
      <div class="toolbar">
        <ul>
          <div class="control-btn" >
            <a href="javascript:;" class="btns" @click="showAuth()">添加菜单</a>
          </div>
          <li>
            <a href="javascript:;" :class="active ? 'btns' : ''" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? modMenu() : ''">修改</a>
          </li>
          <li>
            <a href="javascript:;" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? delMenu() : ''">删除</a>
          </li>
        </ul>
      </div>
      <div class="tables">
        <table class="table table-bordered">
          <thead>
          <tr>
            <th width="5%" >
              选择
            </th>
            <th width="5%">编号</th>
            <th width="10%">菜单编号</th>
            <th width="10%">菜单名称</th>
            <th width="10%">菜单图标</th>
            <th width="10%">菜单路径</th>
            <th width="5%">状态</th>
            <th width="10%">创建人</th>
            <th width="10%">创建时间</th>
          </tr>
          </thead>
          <tbody>
            <tr v-for="(item,index) in menuList">
              <td>
                <label class="checkbox00" >
                  <input type="checkbox" @change="changeCheckbox($event)" name="checkbox" v-model="checked" :value="item.menuId"><span></span>
                </label>
              </td>
              <td>{{index+1}} </td>
              <td>{{item.menuCode}}</td>
              <td>{{item.menuName}}</td>
              <td>{{item.menuIcon}}</td>
              <td>{{item.menuUrl}}</td>
              <td><div v-if="item.menuStatus == 1">启用</div><div v-if="item.menuStatus == 2">禁用</div></td>
              <td>{{item.createDate}}</td>
              <td>{{item.createUser}}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
<script>
  export default{
      name: 'auth',
      data(){
          return{
            style: '',
            menuList: [],
            menuId: '',
            checked: [],
            active: false,
          }
      },
      mounted(){
        this.init();

      },
      methods: {
        init(){
            var $this = this;
            $this.$httpService.getMenuAllList().then((res)=>{
                if(res.code == '2000'){
                  var data = res.data;
                  $('#tree').on("changed.jstree",function(e,data){
                    if(data.selected.length) {
                      $this.menuId = data.selected[0]
                      $this.getMenuList();
                    }
                  }).jstree({
                    'core' : {
                      'data' : data
                    }
                  });
                }
            });
            this.getMenuParent();
        },

        /**
         * 根据菜单id查找下级菜单
         */
        getMenuList(){
          var param = {};
          param['menuId'] = this.menuId;
          this.$httpService.getMenuById(param).then((res)=>{
              if(res.code == '2000'){
                  this.menuList = res.data;
              }
          })
        },

        /**
         * checkbox改变
         */
        changeCheckbox($event){
          this.checked = [];
          var checked = $($event.target).parents("table").find("tbody input[type='checkbox']:checked");
          if(checked.length == 0){
            this.active = false;
          }else{
            this.active = true;
            this.checked.push($($event.target).val());
          }
        },

        /**
         * 获取所有父级菜单
         */
        getMenuParent(){
            this.$httpService.getMenuParent().then((res)=>{
                if(res.code == '2000'){
                    this.menuList = res.data;
                }
            })
        },

        /**
         * 显示添加菜单面板
         */
        showAuth(){
          this.menuId = [];
          this.$router.push({path:'/auth-add'})
          this.$parent.showPanel();
        },

        /**
         * 修改
         */
        modMenu(){
          this.menuId = this.checked[0];
          this.$router.push({path:'/auth-add'})
          this.$parent.showPanel();
        },

        /**
         * 删除菜单
         */
        delMenu(){
          var $this = this;
          $this.$parent.warning = true;
          $this.$parent.msg = "确定要删除么？"
          $this.$parent.callback = function(){
            var param = {};
            param['menuId'] = $this.checked[0];
            $this.$httpService.removeMenu(param).then((res)=>{
                if(res.code == '2000'){
                  $this.init();
                }
            })
          }
        },
      }
  }
</script>
<style>
  @import "../../static/css/style.min.css";
.auth{
  width: 100%;
  height: 100%;
  margin-top: 70px;
}

</style>

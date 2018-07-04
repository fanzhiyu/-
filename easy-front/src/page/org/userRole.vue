<template>
  <div>
    <div class="tables overflow" style="height:240px;">
      <table class="table table-bordered">
        <thead>
        <tr>
          <td width="3%">
            <label class="checkbox00" >
              <input type="checkbox" @click="checkAll($event)" name="checkbox"><span></span>
            </label>
          </td>
          <th width="5%">编号</th>
          <th width="10%">角色编号</th>
          <th width="10%">角色名</th>
          <th width="10%">可见范围</th>
          <th width="10%">类型</th>
          <th width="10%">操作</th>
        </tr>
        </thead>
        <tbody>
          <tr v-for="(item,index) in roleList">
            <td>
              <label class="checkbox00" >
                <input type="checkbox" v-model="checked" :value="item.roleId" name="checkbox"><span></span>
              </label>
            </td>
            <td>{{index}}</td>
            <td>{{item.roleCode}}</td>
            <td>{{item.roleName}}</td>
            <td>{{item.rangeName}}</td>
            <td>
              <div v-if="item.roleType == '1'">任务分配</div>
              <div v-if="item.roleType == '2'">管理角色</div>
              <div v-if="item.roleType == '3'">普通角色</div>
            </td>
            <td>查找权限</td>
          </tr>
        </tbody>
      </table>
    </div>
    <my-pager :page-index="currentPage" :total="count" :page-size="pageSize" @change="pageChange"></my-pager>
    <div class="submit-btn">
      <ul>
        <li class="btn-cancel"><a href="javascript:;" @click="cancel()">取消</a></li>
        <li class="btn-confirm"><a href="javascript:;" @click="confirm()">确认</a></li>
        <div class="clearfix"></div>
      </ul>
    </div>
  </div>
</template>
<script>
  export default{
      name: '',
      data(){
          return {
            roleList: [],
            pageSize : 10 , //每页显示20条数据
            currentPage : 1, //当前页码
            count : 0, //总记录数
            checked: [],
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
          param['pageNo'] = 1;
          param['pageSize'] = 10;
          this.$httpService.getRolePager(param).then((res)=>{
              if(res.code == '2000'){
                  this.roleList = res.rows;
                  this.count = res.total;
                  this.$nextTick(()=>{
                    var param = {};
                    param['userId'] = this.$parent.params.userId;
                    this.$httpService.getUserRole(param).then((res)=>{
                        if(res.code == '2000' && res.data){
                            for(var i=0,len=res.data.length;i<len;i++){
                                var list = res.data[i];
                                this.checked.push(list.roleId);
                            }
                        }
                    })
                  })
              }
          });
        },


        /**
         * 全选
         */
        checkAll($event){
          var checked = $($event.target).prop("checked");
          this.checked = [];
          if(checked){
            this.roleList.forEach((obj,index)=>{
              this.checked.push(obj.roleId);
            })
          }
        },

        /**
         * 确认
         */
        confirm(){
          var userId = this.$parent.params.userId;
          var params = {};
          var array = [];
          var lists = this.checked;
          for(var i=0,len=lists.length;i<len;i++){
            params = {};
            params['roleId'] = lists[i];
            array.push(params)
          }
          var obj = {};
          obj['userId'] = userId
          obj['list'] = JSON.stringify(array);
          var param= {};
          param['params'] = JSON.stringify(obj);
          this.$httpService.saveUserRole(param).then((res)=>{
            if(res.code == '2000'){
              this.$parent.close();
            }
          })
        },

        /**
         * 取消
         */
        cancel(){
          this.$parent.close();
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
</style>

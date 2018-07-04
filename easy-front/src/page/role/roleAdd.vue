<template>
  <div name="roleAde" class="role-add">
    <form action="javascript:;" id="roleForm">
    <section class="group border-bottom-gray">
      <div class="titles"><h3>添加角色</h3></div>
      <div class="control">
        角色编号<span class="required">*</span>：
        <input type="text" placeholder="填写角色编号" check="ckNull" message="角色编号" v-model="roleCode" />
      </div>
      <div class="control">
        角色名称<span class="required">*</span>：
        <input type="text" placeholder="填写角色编号" check="ckNull" message="角色名称" v-model="roleName" />
      </div>
      <div class="control">
        <label class="left">角色类型<span class="required">*</span>：</label>
        <div class="left" style="width: 70%">
          <div class='filter-box' id="type" style="width: 100%;">
            <div class='filter-text'>
              <input class='filter-title' type='text' check="ckNull" message="类型" readonly placeholder='选择类型' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='type' v-model="typeId">
              <option value="1">任务分配</option>
              <option value="2">管理角色</option>
              <option value="3">普通角色</option>
            </select>
          </div>
        </div>
      </div>
    </section>
    <section class="group">
      <div class="control">
        <label class="left">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态<span class="required">*</span>：</label>
        <div class="left" style="width: 70%">
          <div class='filter-box' id="status" style="width: 100%;">
            <div class='filter-text'>
              <input class='filter-title' type='text' check="ckNull" message="类型" readonly placeholder='选择状态' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='type' v-model="status">
              <option value="1">启用</option>
              <option value="2">禁用</option>
            </select>
          </div>
        </div>
      </div>
      <div class="control">
        <label class="left">可见范围<span class="required">*</span>：</label>
        <div class="left" style="width: 70%">
          <div class='filter-box' id="range" style="width: 100%;">
            <div class='filter-text'>
              <input class='filter-title' type='text' check="ckNull" message="可见范围" readonly placeholder='选择可见范围' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='type' v-model="range">
              <option  v-for="item in rangeList" :value="item.id">{{item.name}}</option>
            </select>
          </div>
        </div>
      </div>
      <div class="control">
        <label class="left">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述<span class="required">*</span>：</label>
        <textarea class="remark" check="ckNull" message="描述" v-model="remark" placeholder="描述"></textarea>
      </div>
    </section>
    </form>
    <div class="submit-btn">
      <ul>
        <li class="btn-cancel"><a href="javascript:;" @click="cancel()">取消</a></li>
        <li class="btn-confirm"><a href="javascript:;" @click="saveRole()">确认</a></li>
        <div class="clearfix"></div>
      </ul>
    </div>
  </div>
</template>
<script>
  export default{
      name: 'roleAde',
      data(){
          return {
            roleId: '',
            roleCode: '',
            roleName: '',
            typeId: '',
            status: '',
            range: '',
            remark: '',
            rangeList: []
          }
      },
      mounted(){
        this.init();
      },
      watch:{
        change: function(){
            this.searchRole();
        }
      },
      computed: {
        change() {
          return this.$parent.$parent.roleId;
        }
      },
      methods:{
        /**
         * 初始化
         */
        init(){
            var $this = this;
            $this.setRoleType();
            $this.setRoleStatus();
            $this.searchRangeList();
            $this.searchRole();
        },

        /**
         * 查找角色
         */
        searchRole(){
          var $this = this;
          $this.set();
          var roleId = $this.$parent.$parent.roleId;
          if(roleId){
            var param = {};
            param['roleId'] = roleId;
            this.$httpService.getRoleById(param).then((res)=>{
                if(res.code == '2000'){
                  var data = res.data;
                  $this.roleCode = data.roleCode;
                  $this.roleName = data.roleName;
                  $this.typeId = data.roleType;
                  $this.status = data.roleStatus;
                  $this.range = data.roleRange;
                  $this.remark = data.roleRemark;
                  $this.roleId = data.roleId;
                  $this.setRoleType();
                  $this.setRoleStatus();
                  $this.setRange();
                }
            })
          }
        },

        /**
         * 设置角色类型
         */
        setRoleType(){
          var $this = this;
          $this.$nextTick(()=>{
            $("#type").selectFilter({
              callBack: (val)=>{
                $this.typeId = val;
              }
            })
          })
        },

        /**
         * 设置角色状态
         */
        setRoleStatus(){
            var $this = this;
            $this.$nextTick(()=>{
              $("#status").selectFilter({
                callBack: (val)=>{
                  $this.status = val;
                }
              })
            });
        },

        /**
         * 设置值
         */
        set(){
          this.roleCode = "";
          this.roleName = "";
          this.typeId = "";
          this.status = "";
          this.range = "";
          this.remark = "";
          $("#type input").val("");
          $("#status input").val("");
          $("#range input").val("");
        },

        /**
         * 查找可见范围
         */
        searchRangeList(){
          this.$httpService.getRangeList().then((res)=>{
              if(res.code == '2000'){
                  this.rangeList = res.data;
                  this.setRange();
              }
          })
        },

        setRange(){
          var $this = this;
          $this.$nextTick(()=>{
            $("#range").selectFilter({
              callBack: (val)=>{
                $this.range = val;
              }
            })
          })
        },

        /**
         * 保存角色
         */
        saveRole(){
          if(this.$stringUtil.validation("roleForm")){
              var $this = this;
              var param = {};
              param['roleCode'] = $this.roleCode;
              param['roleName'] = $this.roleName;
              param['roleType'] = $this.typeId;
              param['roleStatus'] = $this.status;
              param['roleRange'] = $this.range;
              param['roleRemark'] = $this.remark;
              param['roleId'] = $this.roleId;
              $this.$httpService.saveRole(param).then((res)=>{
                if(res.code == '2000'){
                    $this.$parent.$parent.init();
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
  .remark{
    width: 70%;
    float: left;
    border-radius: 10px;
    border:1px solid #efefef;
    line-height: 20px;
    height: 100px;
    margin-left: 5px;
    padding: 5px;
  }
  .role-add{
    padding: 10px;
  }
</style>

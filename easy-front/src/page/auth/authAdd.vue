<template>
  <div name="authAdd" class="auth-add">
    <form action="javascript:;" id="menuFrom" >
    <section class="group border-bottom-gray">
      <div class="titles"><h3>添加菜单</h3></div>
      <div class="control">
        <label class="left">菜单类型<span class="required">*</span>：</label>
        <div class="left" style="width: 70%">
          <div class='filter-box' id="type" style="width: 100%;">
            <div class='filter-text'>
              <input class='filter-title' type='text' check="ckNull" message="选择类型" readonly placeholder='选择类型' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='type' v-model="typeId">
              <option value="1">根菜单</option>
              <option value="2">子菜单</option>
            </select>
          </div>
        </div>
      </div>
      <div class="control" v-if="typeId == '2'">
        <label class="left">父级菜单<span class="required">*</span>：</label>
        <div class="left" style="width: 70%">
          <div class='filter-box' id="parent" style="width: 100%;">
            <div class='filter-text'>
              <input class='filter-title' type='text' check="ckNull" message="选择父级菜单" readonly placeholder='选择父级菜单' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='type' v-model="parentId">
              <option v-for="item in  menuList" :value="item.menuId">{{item.menuName}}</option>
            </select>
          </div>
        </div>
      </div>
      <div class="control">
        菜单名称<span class="required">*</span>：
        <input type="text" placeholder="请填写菜单名称" check="ckNull" v-model="menuName" message="菜单名称"/>
      </div>
      <div class="control">
        菜单编码<span class="required">*</span>：
        <input type="text" placeholder="请填写菜单编码" check="ckNull" v-model="menuCode" message="菜单编码" />
      </div>
    </section>
    <section class="group">
      <div class="control">
        <label class="left">关联对象&nbsp;：</label>
        <div class="left" style="width: 70%">
          <div class='filter-box' id="object" style="width: 100%;">
            <div class='filter-text'>
              <input class='filter-title' type='text' readonly placeholder='选择关联对象' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='type' v-model="objId">
              <option v-for="item in objList" :value="item.objId">{{item.objName}}</option>
            </select>
          </div>
        </div>
      </div>
      <div class="control" v-if="objId == ''">
        菜单路径<span class="required">*</span>：
        <input type="text" placeholder="请填写菜单路径" check="ckNull" v-model="menuUrl" message="菜单路径"/>
      </div>
      <div class="control">
        菜单图标<span class="required">*</span>：
        <input type="text" placeholder="请填写菜单图标" check="ckNull" v-model="menuIcon" message="菜单图标" />
      </div>
      <div class="control">
        菜单排序<span class="required">*</span>：
        <input type="text" placeholder="请填写菜单排序" check="ckNull" v-model="menuSeq" message="菜单排序" />
      </div>
      <div class="control">
        <label class="left">菜单状态<span class="required">*</span>：</label>
        <div class="left" style="width: 70%">
          <div class='filter-box' id="status" style="width: 100%;">
            <div class='filter-text'>
              <input class='filter-title' type='text' check="ckNull" message="状态" readonly placeholder='选择状态' />
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
        <label class="left">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述<span class="required">*</span>：</label>
        <textarea class="remark" check="ckNull" message="描述" v-model="remark" placeholder="描述"></textarea>
      </div>
    </section>
    </form>
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
  export default{
      name: 'authAdd',
      data(){
          return{
            remark:'',
            typeId: '',
            parentId: '',
            status: '',
            menuName: '',
            menuCode: '',
            menuUrl: '',
            menuIcon: '',
            menuSeq: '',
            menuList: [],
            objId: '',
            objList: [],
          }
      },
      mounted(){
        this.init();
      },
      watch:{
        change: function(){
          this.searchMenu();
        }
      },
      computed: {
        change() {
          return this.$parent.$parent.menuId;
        }
      },
      methods: {

        /**
         * 页面初始化
         */
        init(){
            this.setType();
            this.setStatus();
            this.searchObj();
        },

        /**
         * 查找对象
         */
        searchObj(){
          this.$httpService.getObjAll().then((res)=>{
              if(res.code == '2000'){
                this.objList = res.data;
                this.setObject();
              }
          })
        },

        /**
         * 设置对象
         */
        setObject(){
            var $this = this;
            $this.$nextTick(()=>{
                $("#object").selectFilter({
                  callBack: (val)=>{
                    $this.objId = val;
                  }
                })
            })
        },

        /**
         * 查找菜单
         */
        searchMenu(){
            this.set();
            this.getMenuParent();
            var menuId = this.$parent.$parent.menuId;
            if(menuId && menuId.length > 0){
              var param = {};
              param['menuId'] = menuId;
              this.$httpService.getMenu(param).then((res)=>{
                  if(res.code == '2000'){
                    var data = res.data;
                    this.typeId = data.menuType;
                    this.menuName = data.menuName;
                    this.menuCode = data.menuCode;
                    this.parentId = data.parentId;
                    this.status = data.menuStatus;
                    this.menuUrl = data.menuUrl;
                    this.menuSeq = data.menuSeq;
                    this.remark = data.menuRemark;
                    this.menuIcon = data.menuIcon;
                    this.menuId = data.menuId;
                    this.setType();
                    this.setStatus();
                    this.setParent();
                  }
              })
            }
        },

        set(){
          this.typeId = "";
          this.menuName = "";
          this.menuCode = "";
          this.parentId = "";
          this.status = "";
          this.menuUrl = "";
          this.menuSeq = "";
          this.remark = "";
          this.menuIcon = "";
          this.menuId = "";
          $("#type input").val("")
          $("#status input").val("")
          $("#parent input").val("")
        },

        /**
         * 菜单类型
         */
        setType(){
            var $this = this;
            $this.$nextTick(()=>{
              $("#type").selectFilter({
                callBack: (val)=>{
                  $this.typeId = val;
                  val == '2' ? $this.getMenuParent() : '';
                }
              })
            });
        },

        /**
         * 菜单状态
         */
        setStatus(){
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
         * 保存
         */
        save(){
          if(this.$stringUtil.validation("menuFrom")){
              var param = {};
              param['menuId'] = this.menuId;
              param['parentId'] = this.parentId;
              param['menuName'] = this.menuName;
              param['menuCode'] = this.menuCode;
              param['menuUrl'] = this.menuUrl;
              param['menuIcon'] = this.menuIcon;
              param['menuSeq'] = this.menuSeq;
              param['menuStatus'] = this.status;
              param['menuRemark'] = this.remark;
              param['menuType'] = this.menuType;
              param['objId'] = this.objId;
              this.$httpService.saveMenu(param).then((res)=>{
                if(res.code == '2000'){
                  this.$parent.$parent.init();
                }
              })
          }
        },

        /**
         * 获取父节点菜单
         */
        getMenuParent(){
            var $this = this;
            $this.$httpService.getMenuParent().then((res)=>{
                if(res.code == '2000'){
                    $this.menuList = res.data;
                    $this.setParent();
                }
            })
        },

        /**
         * 设置父级
         */
        setParent(){
          var $this = this;
          $this.$nextTick(()=>{
            if($("#parent").length > 0){
              $("#parent").selectFilter({
                callBack: (val)=>{
                  $this.parentId = val;
                }
              })
            }
          })
        }
      }
  }
</script>
<style>
.auth-add{
  padding: 10px;
}
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
</style>

<template>
  <div name="roleAuth">
    <div id="tree" class="overflow" style="height: 280px;"></div>
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
      name: 'roleAuth',
      data(){
          return {
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
            this.$httpService.getMenuAllList().then((res)=>{
              this.initTree(res.data);
            })
          },

          /**
           * 初始化树形
           * @param data
           */
          initTree(data){
            var $this = this;
            $('#tree').on("loaded.jstree",function(e,data){
                var inst = data.instance;
                var param = {};
                param['roleId'] = $this.$parent.params.roleId;
                $this.$httpService.getCheckMenu(param).then((res)=>{
                  inst.select_node(res.data);
                });
            }).jstree({
              'core' : {
                'data' : data,
              },
              "plugins" : ["checkbox" ],
            });
          },

          /**
           * 确定
           */
          confirm(){
            var ref = $('#tree').jstree(true);
            var sel = ref.get_selected();
            var param = {};
            var json = {};
            json['roleId'] = this.$parent.params.roleId;
            json['list'] = sel;
            param['params'] = JSON.stringify(json);
            this.$httpService.saveRoleMenu(param).then((res)=>{
              if(res.code == '2000'){
                this.$parent.close();
              }
            });
          },

          /**
           * 取消
           */
          cancel(){
            this.$parent.close();
          }

      }
  }
</script>
<style>
  @import "../../static/css/style.min.css";
</style>

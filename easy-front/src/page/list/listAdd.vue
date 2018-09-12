<template>
  <div class="list-add" v-html="html">
    {{html}}
  </div>
</template>
<script>
  export default{
      name: 'list-add',
      data(){
          return {
              objId: '',
              html: '',
              index: '',
          }
      },
      watch:{
        "$router":['init'],
        change:()=>{
        },
      },
      computed: {
        change() {
          this.index = this.$parent.$parent.index;
          this.details();
          return this.index;
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
            param['objId'] = this.$route.query.objId;
            this.$httpService.getFormsByObjId(param).then((res)=>{
                if(res.code == '2000'){
                    this.html = res.data.formHtml;
                    this.setPageSelect();
                }
            })
        },

        /**
         * 查找详细
         */
        details(){
          $(".list-add form").find("input,textarea").not("input[type=button]").val("");
          var data = this.$parent.$parent.dataList;
          var json = {};
          json['objId'] = this.$route.query.objId;
          json['list'] = JSON.stringify(data[this.index]);
          var param = {};
          param['params'] = JSON.stringify(json);
          this.$httpService.getTabDataDetails(param).then((res)=>{
              if(res.code == '2000' && res.data){
                  var inputs = $(".list-add form").find("input,textarea");
                  var data = res.data;
                  for(var i=0,len=inputs.length;i<len;i++){
                      var input = $(inputs[i]);
                      var bindId = input.attr("bind-id");
                      for(var key in data){
                          if(bindId == key){
                              input.val(data[key]);
                              break;
                          }
                      }
                }
              }
          })
        },

        /**
         * 设置页面的下拉菜单
         */
        setPageSelect(){
          this.$nextTick(()=>{
            var selects = $(".list-add").find(".filter-box");
            for(var i=0,len=selects.length;i<len;i++){
              var select = $(selects[i]);
              select.selectFilter({
                callBack : function (val){
                  select.find("input[type=hidden]").val(val);
                }
              });
            }
          })
        }
      }
  }
</script>
<style>
  .form-title{
    margin-top: 20px;
  }
</style>

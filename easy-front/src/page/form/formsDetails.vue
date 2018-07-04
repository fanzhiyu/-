<template>
  <div name="" class="form-details">
    <div v-html="formHtml">{{formHtml}}</div>
  </div>
</template>
<script>
  export default{
      name: '',
      data(){
          return {
            formHtml : '',
          }
      },
      mounted(){
        this.init();
      },
      watch:{
        '$route': ['init']
      },
      methods: {

        /**
         * 初始化
         */
        init(){
          var param = {};
          param['formId'] = this.$route.query.formId;
          this.$httpService.getFormsDetails(param).then((res)=>{
            if(res.code = '2000'){
                this.formHtml = res.data.formHtml;
                this.$nextTick(()=>{
                  var selects = $(".form-details").find(".filter-box");
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
          })
        }
      }
  }
</script>
<style>
.form-details{
  padding: 10px;
}
</style>

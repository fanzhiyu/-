<template>
  <div name="structure">
    <div class="tables">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th width="15%">编号</th>
            <th width="20%">字段名</th>
            <th width="20%">物理名</th>
            <th width="15%">类型</th>
            <th width="10%">长度</th>
            <th width="10%">主键</th>
            <th width="10%">空</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item,index) in fields">
            <td>{{item.tabFieldId}}</td>
            <td>{{item.tabFieldComment}}</td>
            <td>{{item.tabFieldName}}</td>
            <td>{{item.tabFieldType}} </td>
            <td>{{item.tabFieldLength}}</td>
            <td>
              <div v-if="item.tabIsKey == '1'">是</div>
              <div v-if="item.tabIsKey == '0'">否</div>
            </td>
            <td>
              <div v-if="item.tabIsNull == '1'">非空</div>
              <div v-if="item.tabIsNull == '0'">空</div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
  export default{
      name: 'structure',
      data(){
        return {
          fields: []
        }
      },
      mounted(){
        this.init();
      },
      methods: {

        /**
         * 查找数据表结构
         * @param comment
         * @param tabId
         */
        init(){
          var $this = this;
          var tabId = this.$parent.params.tabId;
          if(tabId){
            var param = {};
            param['tabId'] = tabId;
            $this.$httpService.getTabFieldList(param).then((res)=>{
              if(res.code == '2000'){
                $this.fields = res.data.fields;
              }
            });
          }
        },
      }
  }
</script>
<style>

</style>

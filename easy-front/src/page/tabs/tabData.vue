<template>
  <div name="">
    <div class="tables overflow" style="height: 300px;">
      <table class="table table-bordered">
        <thead>
        <tr>
          <th v-for="item in titles">{{item}}</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(item,index) in dataList">
          <td v-for="field in item">{{field}}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <my-pager :page-index="currentPage" :total="count" :page-size="pageSize" @change="pageChange"></my-pager>
  </div>
</template>
<script>
  export default{
      name: '',
      data(){
          return {
            titles: [],
            dataList: [],
            currentPage: 1,
            count: 0,
            pageSize: 10,
          }
      },
      mounted(){
        this.init();
      },
      methods: {
        init(){
          var $this = this;
          var tabId = $this.$parent.params.tabId;
          if(tabId){
            var param = {};
            param['pageSize'] = $this.pageSize;
            param['pageNo'] = $this.currentPage;
            param['tabId'] = tabId;
            $this.$httpService.getTabDataPager(param).then((res)=>{
              if(res.code == '2000'){
                var data = res.data;
                $this.titles = data.titles;
                $this.dataList = data.resultList;
                $this.count = data.count;
              }
            })
          }
        },

        /**
         * 页面数据发生改变
         * @param page
         */
        pageChange (page) {
          this.currentPage = page
          this.init();
        },
      }
  }
</script>
<style>

</style>

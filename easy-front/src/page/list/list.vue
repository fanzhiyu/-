<template>
  <div name="list">
    <my-panel :styles="style"></my-panel>
    <div class="titles" style="margin-top:50px">
      <div class="title1 border-bottom">列表</div>
    </div>
    <div class="process-table">
      <form action="javascript:;" id="searchFrom">
        <div class="search" v-html="html">
          {{html}}
        </div>
        <div class="clear"></div>
      </form>
      <div class="user-table">
        <div class="toolbar">
          <ul>
            <div class="control-btn" >
              <a href="javascript:;" class="btns" @click="add()">添加</a>
            </div>
            <li>
              <a href="javascript:;" :class="active ? 'btns' : ''" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? mod() : ''">修改</a>
            </li>
            <li>
              <a href="javascript:;" :style="active ? 'color: #00C6D7' : ''" @click.sync="active ? del() : ''">删除</a>
            </li>
          </ul>
          <div class="toolbar-search">
            <div class="search-button">
              <a href="javascript:;" @click="search()">查询</a>
            </div>
          </div>
          <div class="clear"></div>
        </div>
        <div class="tables">
          <table class="table table-bordered">
            <thead>
            <tr>
              <th width="5%">选择</th>
              <th v-for="item in titles">{{item}}</th>
            </tr>
            </thead>
            <tbody>
              <tr v-for="(item,index) in dataList">
                <td>
                  <label class="checkbox00" >
                    <input type="checkbox" @click="selectOne($event)" v-model="checked" :value="index" name="checkbox"><span></span>
                  </label>
                </td>
                <td v-for="field in item">{{field}}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <my-pager :page-index="currentPage" :total="count" :page-size="pageSize" @change="pageChange"></my-pager>
      </div>
    </div>
  </div>
</template>
<script>
  export default{
      name : 'list',
      data() {
          return {
            pageSize : 10 , //每页显示20条数据
            currentPage : 1, //当前页码
            count : 0, //总记录数
            style: '',
            titles: [],
            dataList: [],
            active: '',
            checked: '',
            objId: '',
            html: '',
            paramList: '',
            index: '',
          }
      },
      watch:{
        "$route":['init','searchField']
      },
      mounted(){
        this.init();
        this.searchField();
      },
      methods: {

        /**
         * 初始化
         */
        init(){
          var $this = this;
          var objId = this.$route.query.objId;
          if(objId){
            var param = {};
            param['objId'] = objId;
            param['paramList'] = this.paramList;
            $this.$httpService.getObjDataPager(param).then((res)=>{
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
         * 查询
         */
        search(){
          var form = $(".search");
          var inputs = form.find("input,textarea").not("input[type='radio'],.filter-box input[type='text']");
          var radios = form.find("input[type='radio']")
          for(var i=0,len=radios.length;i<len;i++){
            var radio = $(radios[i]);
            if(radio.is(":checked")){
              inputs.push(radio);
            }
          }
          var array = [];
          for(var i=0,len=inputs.length; i<len; i++){
            var json = {};
            var input = $(inputs[i]);
            var key = input.attr("bind-id");
            if(key){
              var val = input.val();
              json[key] = val;
              array.push(json);
            }
          }
          this.paramList = JSON.stringify(array);
          this.init();
        },

        /**
         * 查找字段
         */
        searchField(){
            this.html = "";
            var $this = this;
            var objId = $this.$route.query.objId;
            if(objId){
                var param = {};
                param['objId'] = objId;
                $this.$httpService.getFieldByObiId(param).then((res)=>{
                    if(res.code == '2000' && res.data){
                        this.html = res.data.formHtml;
                        this.setPageSelect();
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
          this.init()
        },

        /**
         * 添加
         */
        add(){
          this.index = "";
          var objId = this.$route.query.objId;
          this.$router.push({path:'/list-add',query:{objId:objId}});
          this.$parent.showPanel();
        },

        /**
         * 修改
         */
        mod(){
          var objId = this.$route.query.objId;
          this.index = this.checked[0];
          this.$router.push({path:'/list-add',query:{objId:objId}});
          this.$parent.showPanel();
        },

        /**
         * 删除
         */
        del(){
          var $this = this;
          $this.$parent.warning = true;
          $this.$parent.msg = "确定要删除么？";
          $this.$parent.callback = function(){
            var index = $this.checked[0];
            var data = $this.dataList[index];
            var json = {};
            json['objId'] = $this.$route.query.objId;
            json['list'] = JSON.stringify(data);
            var param = {};
            param['params'] = JSON.stringify(json);
            $this.$httpService.removeTabData(param).then((res)=>{
              console.log(res);
              if(res.cdoe == '2000'){

              }
            })
          }
        },

        /**
         * 单选
         */
        selectOne($event){
          this.checked = [];
          var checked = $($event.target).parents("table").find("tbody input[type='checkbox']:checked");
          if(checked.length == 0){
            this.active = false;
          }else{
            this.active = true;
            var val = $($event.target).val();
            this.checked.push(val);
          }
        },

        /**
         * 设置页面的下拉菜单
         */
        setPageSelect(){
          this.$nextTick(()=>{
            var selects = $(".search").find(".filter-box");
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
.toolbar-search{
  float: right;

}

 .toolbar-search .search-button{
   width: 70px;
   height: 30px;
   line-height: 30px;
   text-align: center;
   float: left;
   margin-top: 10px;
   background: #00C6D7;
   border-radius:20px;
 }
.toolbar-search .search-button a{
  color: #ffffff;
  display: block;
  font-size: 13px;
}
</style>

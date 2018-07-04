<template>
  <div name="process" class="process">
    <div class="titles">
      <div class="title1 border-bottom">流程查看</div>
    </div>
    <div class="process-table">
      <div class="search">
        <div class="control-inputs">
          <label>流&nbsp;&nbsp;&nbsp;&nbsp;程&nbsp;&nbsp;&nbsp;&nbsp;名：</label><input type="text" />
        </div>
        <div class="control-inputs">
          <label>流程分类：</label><input type="text" />
        </div>
        <div class="search-btn"><a href="javascript:;">查询</a></div>
      </div>
      <div class="toolbar">
        <div class="control-btn">
          <a href="javascript:;">新建流程</a>
        </div>
        <ul>
          <li><a href="javascript:;">激活</a></li>
          <li><a href="javascript:;">删除</a></li>
        </ul>
      </div>
      <div class="graph" style="height: 550px;">
        <div class="tables">
          <table class="table" style="width: 100%">
            <thead>
            <tr>
              <th width="5%">
                <label class="checkbox00">
                  <input type="checkbox" @click="checkboxBtn($event)" name="checkbox"><span></span>
                </label>
              </th>
              <th width="5%">编号</th>
              <th>流程分类</th>
              <th>流程编号</th>
              <th>流程标识</th>
              <th>流程名称</th>
              <th>版本号</th>
              <th>部署时间</th>
              <th>创建时间</th>
              <th>最后更新时间</th>
            </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <label class="checkbox00">
                    <input type="checkbox" name="checkbox"><span></span>
                  </label>
                </td>
                <th scope="row"><div class="table-row">1</div></th>
                <td><div class="table-row">无分类</div></td>
                <td><div class="table-row">fa542531f041437dba7f1f24f746f76c</div></td>
                <td><div class="table-row">test_audit</div></td>
                <td><div class="table-row">test_audit.bpmn20.xml</div></td>
                <td><div class="table-row">V:1</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
              </tr>
              <tr>
                <td>
                  <label class="checkbox00">
                    <input type="checkbox" name="checkbox"><span></span>
                  </label>
                </td>
                <th scope="row"><div class="table-row">1</div></th>
                <td><div class="table-row">无分类</div></td>
                <td><div class="table-row">fa542531f041437dba7f1f24f746f76c</div></td>
                <td><div class="table-row">test_audit</div></td>
                <td><div class="table-row">test_audit.bpmn20.xml</div></td>
                <td><div class="table-row">V:1</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
              </tr>
              <tr>
                <td>
                  <label class="checkbox00">
                    <input type="checkbox" name="checkbox"><span></span>
                  </label>
                </td>
                <th scope="row"><div class="table-row">1</div></th>
                <td><div class="table-row">无分类</div></td>
                <td><div class="table-row">fa542531f041437dba7f1f24f746f76c</div></td>
                <td><div class="table-row">test_audit</div></td>
                <td><div class="table-row">test_audit.bpmn20.xml</div></td>
                <td><div class="table-row">V:1</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
              </tr>
              <tr>
                <td>
                  <label class="checkbox00">
                    <input type="checkbox" name="checkbox"><span></span>
                  </label>
                </td>
                <th scope="row"><div class="table-row">1</div></th>
                <td><div class="table-row">无分类</div></td>
                <td><div class="table-row">fa542531f041437dba7f1f24f746f76c</div></td>
                <td><div class="table-row">test_audit</div></td>
                <td><div class="table-row">test_audit.bpmn20.xml</div></td>
                <td><div class="table-row">V:1</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
              </tr>
              <tr>
                <td>
                  <label class="checkbox00">
                    <input type="checkbox" name="checkbox"><span></span>
                  </label>
                </td>
                <th scope="row"><div class="table-row">1</div></th>
                <td><div class="table-row">无分类</div></td>
                <td><div class="table-row">fa542531f041437dba7f1f24f746f76c</div></td>
                <td><div class="table-row">test_audit</div></td>
                <td><div class="table-row">test_audit.bpmn20.xml</div></td>
                <td><div class="table-row">V:1</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
              </tr>
              <tr>
                <td>
                  <label class="checkbox00">
                    <input type="checkbox" name="checkbox"><span></span>
                  </label>
                </td>
                <th scope="row"><div class="table-row">1</div></th>
                <td><div class="table-row">无分类</div></td>
                <td><div class="table-row">fa542531f041437dba7f1f24f746f76c</div></td>
                <td><div class="table-row">test_audit</div></td>
                <td><div class="table-row">test_audit.bpmn20.xml</div></td>
                <td><div class="table-row">V:1</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
              </tr>
              <tr>
                <td>
                  <label class="checkbox00">
                    <input type="checkbox" name="checkbox"><span></span>
                  </label>
                </td>
                <th scope="row"><div class="table-row">1</div></th>
                <td><div class="table-row">无分类</div></td>
                <td><div class="table-row">fa542531f041437dba7f1f24f746f76c</div></td>
                <td><div class="table-row">test_audit</div></td>
                <td><div class="table-row">test_audit.bpmn20.xml</div></td>
                <td><div class="table-row">V:1</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
              </tr>
              <tr>
                <td>
                  <label class="checkbox00">
                    <input type="checkbox" name="checkbox"><span></span>
                  </label>
                </td>
                <th scope="row"><div class="table-row">1</div></th>
                <td><div class="table-row">无分类</div></td>
                <td><div class="table-row">fa542531f041437dba7f1f24f746f76c</div></td>
                <td><div class="table-row">test_audit</div></td>
                <td><div class="table-row">test_audit.bpmn20.xml</div></td>
                <td><div class="table-row">V:1</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
              </tr>
              <tr>
                <td>
                  <label class="checkbox00">
                    <input type="checkbox" name="checkbox"><span></span>
                  </label>
                </td>
                <th scope="row"><div class="table-row">1</div></th>
                <td><div class="table-row">无分类</div></td>
                <td><div class="table-row">fa542531f041437dba7f1f24f746f76c</div></td>
                <td><div class="table-row">test_audit</div></td>
                <td><div class="table-row">test_audit.bpmn20.xml</div></td>
                <td><div class="table-row">V:1</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
              </tr>
              <tr>
                <td>
                  <label class="checkbox00">
                    <input type="checkbox" name="checkbox"><span></span>
                  </label>
                </td>
                <th scope="row"><div class="table-row">1</div></th>
                <td><div class="table-row">无分类</div></td>
                <td><div class="table-row">fa542531f041437dba7f1f24f746f76c</div></td>
                <td><div class="table-row">test_audit</div></td>
                <td><div class="table-row">test_audit.bpmn20.xml</div></td>
                <td><div class="table-row">V:1</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
                <td><div class="table-row">2017-08-06 18:20:47</div></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <my-pager :page-index="currentPage" :total="count" :page-size="pageSize" @change="pageChange"></my-pager>
  </div>
</template>
<script>
  export default{
    name: 'process',
    data(){
      return {
        pageSize : 10 , //每页显示20条数据
        currentPage : 1, //当前页码
        count : 0, //总记录数
        active: false,
      }
    },
    mounted(){
      this.init();
    },
    methods: {
      /**
       * 页面初始化
       */
      init(){
         var  $tis = this;
         $tis.$nextTick(()=>{
           $("table input[type='checkbox']").change(function(){
             var $this = $(this);
             var flage = false;
             var $inputs = $this.parents("table").find("tbody input[type='checkbox']");
             for(var i=0,len=$inputs.length; i<len; i++){
               var $input = $($inputs[i]);
               if($input.prop("checked")){
                 flage = true;
                 break;
               }
             }
             if(flage){
               $tis.activeA({
                 $this: $this,
                 active: true,
                 color: '#00C6D7'
               });
             }else{
               $tis.activeA({
                 $this: $this,
                 active: false,
                 color: '#c1cbd1'
               });
             }
           })
         })
      } ,

      /**
       * 全选
       */
      checkboxBtn($event){
        this.$stringUtil.checkedAll($event);
      },

      /**
       * 激活a标签
       */
      activeA(params){
        var $as = params.$this.parents(".process-table").find("li a");
        for(var i=0,len=$as.length;i<len;i++){
          var $a = $($as[i]);
          this.active = params.active;
          $a.css({"color":params.color})
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
    }
  }
</script>
<style>
  .process{
    width: 100%;
    height: 100%;
    margin-top: 70px;
  }
</style>

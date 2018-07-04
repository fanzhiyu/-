<template>
  <div name="forms">
    <my-panel styles="width:40%"></my-panel>
    <div class="titles" style="margin-top:50px">
      <div class="title1 border-bottom">表单列表</div>
    </div>
    <div class="process-table">
      <div class="search">
        <div class="control-inputs">
          <label>编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
          <input type="text" />
        </div>
        <div class="control-inputs">
          <label>创建时间：</label>
          <input type="text" onclick="onTime(this)" v-model="startDate" placeholder="开始时间" class="date" readonly="true" style="width: 32%" />
          - - <input type="text" class="date" v-model="endDate" onclick="onTime(this)"  placeholder="结束时间" readonly="true" style="width: 32%" />
        </div>
        <div class="control-inputs">
          <label>创&nbsp;&nbsp;建&nbsp;&nbsp;人：</label>
          <input type="text"  />
        </div>
        <div class="clear"></div>
        <div class="control-inputs">
          <div style="float: left">
            <label>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：&nbsp;</label>
          </div>
          <div class='filter-box' id="search-type" style="width: 70%">
            <div class='filter-text'>
              <input class='filter-title' type='text' readonly placeholder='选择表类型' />
              <span class='icon-arrow'></span>
            </div>
            <select class='inputs' name='select'>
              <option value="0">全部</option>
              <option value="1">外部提交</option>
              <option value="2">内部提交</option>
            </select>
          </div>
        </div>
        <div class="search-btn"><a href="javascript:;" @click="search()">查询</a></div>
        <div class="clear"></div>
      </div>
      <div class="user-table">
        <div class="toolbar">
          <ul>
            <div class="control-btn" >
              <a href="javascript:;" class="btns" @click="showPanel()">查看表单</a>
            </div>
            <li>
              <a href="javascript:;">修改</a>
            </li>
            <li>
              <a href="javascript:;">删除</a>
            </li>
          </ul>
        </div>
        <div class="tables">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th width="5%" style="text-align: right;padding-right: 15px;">
                  选择
                </th>
                <th width="5%">编号</th>
                <th width="10%">表单标题</th>
                <th width="10%">类型</th>
                <th width="10%">绑定对象</th>
                <th width="10%">创建人</th>
                <th width="10%">创建时间</th>
                <th width="5%">修改人</th>
                <th width="10%">修改时间</th>
                <th width="15%">操作</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in formsList">
                <td width="5%" style="text-align: center">
                  <label class="checkbox00" >
                    <input type="checkbox" v-model="item.checkbox" @click="checkedOne(item)" name="checkbox"><span></span>
                  </label>
                </td>
                <td>{{item.formId}}</td>
                <td>{{item.formTitle}}</td>
                <td>{{item.formType}}</td>
                <td>{{item.objName}}</td>
                <td>{{item.createUser}}</td>
                <td>{{item.createDate}}</td>
                <td>{{item.updateUser}}</td>
                <td>{{item.updateDate}}</td>
                <td>修改 | 删除</td>
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
  import {Indicator, MessageBox,Toast} from "mint-ui";
  export default{
    name: 'forms',
    data() {
      return{
        pageSize : 10 , //每页显示20条数据
        currentPage : 1, //当前页码
        count : 0, //总记录数
        formsList: [],
        startDate: '',
        endDate: '',
        formId: '',
      }
    },
    mounted() {
      this.init();
    },
    methods: {
      init(){
          var param = {};
          this.$httpService.getFormsPager(param).then((res)=>{
              console.log(res);
              if(res.code == '2000'){
                this.formsList = res.rows;
              }
          })
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
       * 单选
       */
      checkedOne(item){
          for(var i=0,len=this.formsList.length; i<len; i++){
            var list = this.formsList[i];
            if(list.checkbox){
                list.checkbox = false;
            }
            if(item.formId == list.formId){
              this.formId = item.formId;
            }
          }
      },

      /**
       * 打开面板
       */
      showPanel(){
        if(!this.formId){
            Toast({
              duration: 1500,
              message: '请选择一个表单'
            });
            Indicator.close();
            return;
        }
        setTimeout(()=>{
          var url = '/forms-details?formId='+this.formId;
          this.$router.push({path:url})
        },200);
        this.$parent.showPanel();
      }
    }
  }
</script>
<style>
</style>

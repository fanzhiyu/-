<template>
  <div name="admin" class="admin">
    <div class="admin-table">
      <div class="toolbar">
        <div class="control-btn"><a href="javascript:;">添加管理员</a> </div>
      </div>
      <div class="graph">
        <div class="tables">
          <table class="table" style="width: 100%">
            <thead>
            <tr>
              <th>
                <label class="checkbox00">
                  <input type="checkbox" @click="checkboxBtn($event)" name="checkbox"><span></span>
                </label>
              </th>
              <th >编号</th>
              <th >姓名</th>
              <th >组织</th>
              <th >部门</th>
              <th >职位</th>
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
              <td><div class="table-row">张三</div></td>
              <td><div class="table-row">艾特密大连分公司</div></td>
              <td><div class="table-row">技术部</div></td>
              <td><div class="table-row">技术部</div></td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  export default{
    name: 'admin',
    data(){
      return {

      }
    },
    mounted(){

    },
    methods: {
      /**
       * 全选
       */
      checkboxBtn($event){
        this.$stringUtil.checkedAll($event);
      },
    }
  }
</script>
<style>
.admin{
  width: 100%;
  height: 100%;
  margin-top: 70px;
}
</style>

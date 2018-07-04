<template>
  <div name="sign" class="sign">
    <div class="sign-title">连续签到有惊喜</div>
    <ul>
      <li v-for="item in list" >
        <div v-if="item.today" @click="sign(item)">
          <div class="branch" :class="item.today ? 'red':''" >{{item.branch}}</div>
          <div class="days" >{{item.today}}</div>
        </div>
        <div v-else>
          <div class="branch">{{item.branch}}</div>
          <div class="days" >{{item.month}}.{{item.day}}</div>
        </div>
      </li>
    </ul>
  </div>
</template>
<script>
  import {Indicator, MessageBox,Toast} from "mint-ui";
  export default{
     name: "sign",
     data() {
         return {
            list: [],
         }
     },
    mounted(){
      this.init();
    },
    methods:{
      init(){
          var $date = new Date();
          $date.setDate($date.getDate() - $date.getDay()-1);
          var branch = 4;
          var sid = "";
          for(var i=1; i<11; i++){
            $date.setDate($date.getDate()+1);
            var param = {};
            var json = {};
            param['createTime'] = $date.format("yyyy-MM-dd");
            var data;
            $.ajax({
              url: 'http://localhost:8085/user/getSign',
              type: 'get',
              data : {
                  createTime: $date.format("yyyy-MM-dd"),
                  token: localStorage.getItem("token"),
              },
              async: false,
              success: (res)=>{
                data = res.data;
              }
            })
            console.log(data);
            if(data){
                if(data.branch){
                    branch = data.branch;
                }else{
                    branch = 5;
                }
            }
            console.log(branch);
            if($date.getTime() - new Date().getTime() > 0 || this.dateCompare($date)){
                if(!data.branch){
                  branch = ++branch;
                  branch = branch < 10 ? branch : "10";
                  if(!sid && this.dateCompare($date)){
                    branch = 5;
                  }
                }
            }
            console.log(branch);
            if(this.dateCompare($date)){
              json['today'] = "今天" ;
            }
            json['branch'] = branch;
            json['month'] =  $date.getMonth()+1;
            json['day'] = $date.getDate();
            this.list.push(json);
            sid = data.id;
          }
      },

      dateCompare (date1) {
        var now = new Date();
        if(date1.getYear() == now.getYear() && date1.getMonth() == now.getMonth() && date1.getDate() == now.getDate()){
            return true;
        }
        return false;
      },

      sign(item){
        var branch =item.branch;
        var param = {};
        param['branch'] = branch;
        this.$httpService.sign(param).then((res)=>{
          console.log(res);
          if(res.code == '2000'){
            Toast({
              duration: 1500,
              message: '签到成功'
            });
            Indicator.close();
          }
        })
      }
    }
  }

  Date.prototype.format = function(fmt) {
    var o = {
      "M+" : this.getMonth()+1,                 //月份
      "d+" : this.getDate(),                    //日
      "h+" : this.getHours(),                   //小时
      "m+" : this.getMinutes(),                 //分
      "s+" : this.getSeconds(),                 //秒
      "q+" : Math.floor((this.getMonth()+3)/3), //季度
      "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
      fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
      if(new RegExp("("+ k +")").test(fmt)){
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
      }
    }
    return fmt;
  }
</script>
<style>
.sign-title{
  width: 100%;
  height: 50px;
  line-height: 50px;
  text-align: center;
}
.sign ul{
  width: 80%;
  margin: auto;
}
.sign li{
  float: left;
  margin-left: 10px;
}
  .sign li .branch{
    width: 50px;
    height: 50px;
    line-height: 50px;
    text-align: center;
    border:1px solid #efefef;
    border-radius: 50%;
    margin-top: 20px;
    font-size: 15px;
  }
  .sign .days{
    text-align: center;
    font-size: 13px;
    width: 50px;
  margin-top: 10px;
  }
  .red{
    background: red;
    color: #ffffff;
  }
</style>

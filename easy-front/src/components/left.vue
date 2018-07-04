<template>
  <div name="left">
    <div class="sidebar-menu">
      <header class="logo">
        <a href="javascript:;" @click="sidebar()" class="sidebar-icon"> <span class="fa fa-bars"></span> </a> <a href="index"> <span id="logo"> <h1>easy work</h1></span>
      </a>
      </header>
      <div style="border-top:1px solid rgba(69, 74, 84, 0.7)"></div>
      <!--/down-->
      <div class="down">
        <router-link to="/home"><img src="../static/images/admin.jpg"></router-link>
        <router-link to="/home"><span class=" name-caret">Jasmin Leo</span></router-link>
        <p>System Administrator in Company</p>
        <ul>
          <li><a class="tooltips" href="index.html"><span>Profile</span><i class="fa fa-user"></i></a></li>
          <li><a class="tooltips" href="index.html"><span>Settings</span><i class="fa fa-cog"></i></a></li>
          <li><a class="tooltips" href="index.html"><span>Log out</span><i class="fa fa-power-off"></i></a></li>
        </ul>
      </div>
      <!--//down-->
      <div class="menu">
        <ul id="menu" >
          <li v-for="item in menuList"><a :href="item.menuUrl"><div class="left" v-html="item.menuIcon">{{item.menuIcon}}</div>&nbsp;&nbsp;<span>{{item.text}}</span> <span class="fa fa-angle-right" style="float: right"></span></a>
            <ul>
              <li v-for="child in item.children"><router-link :to="child.menuUrl">{{child.text}}</router-link></li>
              <!--<li><router-link to="/admin">系统管理员</router-link></li>-->
              <!--<li><a href="widget.html">组织管理者</a></li>-->
              <!--<li><router-link to="/role">角色</router-link></li>-->
            </ul>
          </li>
          <!--<li><a href="#"><i class="fa fa-sitemap"></i> <span>组织</span> <span class="fa fa-angle-right" style="float: right"></span></a>-->
            <!--<ul>-->
              <!--<li><router-link to="/org">组织与人员</router-link></li>-->
              <!--<li><router-link to="/admin">系统管理员</router-link></li>-->
              <!--<li><a href="widget.html">组织管理者</a></li>-->
              <!--<li><router-link to="/role">角色</router-link></li>-->
            <!--</ul>-->
          <!--</li>-->
          <!--<li><a href="#"><i class="fa fa-gavel"></i> <span>审批管理</span> <span class="fa fa-angle-right" style="float: right"></span></a>-->
            <!--<ul>-->
              <!--<li><a href="/task">我的审批</a></li>-->
              <!--<li><a href="widget.html">我的申请</a></li>-->
            <!--</ul>-->
          <!--</li>-->
          <!--<li><a href="#"><i class="fa fa-line-chart"></i> <span>统计报表</span> <span class="fa fa-angle-right" style="float: right"></span></a>-->
            <!--<ul>-->
              <!--<li><a href="tabs.html">摘要统计</a></li>-->
              <!--<li><a href="widget.html">活跃排行</a></li>-->
            <!--</ul>-->
          <!--</li>-->
          <!--<li><a href="#"><i class="fa fa-retweet"></i> <span>流程管理</span> <span class="fa fa-angle-right" style="float: right"></span></a>-->
            <!--<ul>-->
              <!--<li><router-link to="/process">流程定义部署</router-link></li>-->
              <!--<li><a href="widget.html">运行中的流程</a></li>-->
            <!--</ul>-->
          <!--</li>-->
          <!--<li><a href="#"><i class="fa fa-wpforms"></i> <span>表单管理</span> <span class="fa fa-angle-right" style="float: right"></span></a>-->
            <!--<ul>-->
              <!--<li><router-link to="/make">制作表单</router-link></li>-->
              <!--<li><router-link to="/forms">表单列表</router-link></li>-->
            <!--</ul>-->
          <!--</li>-->
          <!--<li><a href="javascript:;"><i class="fa fa-cog"></i> <span>系统管理</span> <span class="fa fa-angle-right" style="float: right"></span></a>-->
            <!--<ul>-->
              <!--<li><router-link to="/tabs">查看数据表</router-link></li>-->
              <!--<li><router-link to="/object">查看实例对象</router-link></li>-->
              <!--<li><a href="/">类型维护</a></li>-->
            <!--</ul>-->
          <!--</li>-->
          <!--<li><a href="#"><i class="fa fa-cog"></i> <span>个人中心</span> <span class="fa fa-angle-right" style="float: right"></span></a>-->
            <!--<ul>-->
              <!--<li><a href="tabs.html">创建团队</a></li>-->
              <!--<li><a href="tabs.html">团队列表</a></li>-->
              <!--<li><a href="tabs.html">我的账号</a></li>-->
              <!--<li><a href="widget.html">修改头像</a></li>-->
              <!--<li><a href="widget.html">修改密码</a></li>-->
            <!--</ul>-->
          <!--</li>-->
        </ul>
      </div>
    </div>
  </div>
</template>
<script>
  export default{
    name: 'left',
    data(){
      return {
        menuList: [],
      }
    },
    mounted(){
      this.init();
    },
    methods:{

      init(){
        this.$httpService.getMenuList().then((res)=>{
            if(res.code == '2000'){
                this.menuList = res.data;
            }
        })
      },
      /**
       * 左侧菜单显示和隐藏
       */
      sidebar(){
        this.$parent.$data.toggle = !this.$parent.$data.toggle
      },
    }
  }
</script>
<style>

</style>

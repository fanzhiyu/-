<template>
  <div name="top" class="top">
    <div class="header-section">
      <!--menu-right-->
      <div class="top_menu">
        <div class="profile_details_left">
          <ul class="nofitications-dropdown">
            <li class="dropdown note dra-down">
              <div @click="downLoadUser()" id="dropdownthree" :class="active ? 'wrapper-dropdown-3 active' : 'wrapper-dropdown-3'" tabindex="1">
                shine
                <ul class="dropdown">
                  <li><a class="deutsch">France</a></li>
                  <li><a class="english"> Italy</a></li>
                  <li><a class="espana">Brazil</a></li>
                  <li><a class="russian">Usa</a></li>
                </ul>
              </div>
            </li>
            <li class="dropdown note" id="dropdownone" @click="download($event)">
              <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" ><i class="fa fa-envelope-o"></i> <span class="badge">3</span></a>
              <ul class="dropdown-menu two first open">
                <li>
                  <div class="notification_header">
                    <h3>您有3条新消息</h3>
                  </div>
                </li>
                <li>
                  <a href="javascript:;">
                    <div class="user-content">
                      <div class="user_img"><img src="../static/images/1.jpg" alt=""></div>
                      <div class="notification_desc">
                        <p>请假申请</p>
                        <p><span>一小时前</span></p>
                      </div>
                      <div class="clearfix"></div>
                    </div>
                  </a>
                </li>
                <li>
                  <a href="javascript:;">
                    <div class="user-content">
                      <div class="user_img"><img src="../static/images/1.jpg" alt=""></div>
                      <div class="notification_desc">
                        <p>请假申请</p>
                        <p><span>一小时前</span></p>
                      </div>
                      <div class="clearfix"></div>
                    </div>
                  </a>
                </li>
                <li>
                  <a href="javascript:;">
                    <div class="user-content">
                      <div class="user_img"><img src="../static/images/1.jpg" alt=""></div>
                      <div class="notification_desc">
                        <p>请假申请</p>
                        <p><span>一小时前</span></p>
                      </div>
                      <div class="clearfix"></div>
                    </div>
                  </a>
                </li>
                <li>
                  <div class="notification_bottom">
                    <a href="#">查找所有消息</a>
                  </div>
                </li>
              </ul>
            </li>
            <li class="dropdown note" id="dropdowntow" @click="download($event)">
              <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" ><i class="fa fa-bell-o"></i> <span class="badge">5</span></a>
              <ul class="dropdown-menu two">
                <li>
                  <div class="notification_header">
                    <h3>您有5条新的通知</h3>
                  </div>
                </li>
                <li>
                  <a href="javascript:;">
                    <div class="user-content">
                      <div class="user_img"><img src="../static/images/1.jpg" alt=""></div>
                      <div class="notification_desc">
                        <p>请假申请</p>
                        <p><span>一小时前</span></p>
                      </div>
                      <div class="clearfix"></div>
                    </div>
                  </a>
                </li>
              </ul>
            </li>
            <div class="clearfix"></div>
          </ul>
        </div>
      </div>
      <div class="clearfix"></div>
    </div>
  </div>
</template>
<script>
  var obj;
  export default{
    name: 'top',
    data(){
      return {
        toggle: true,
        active: false,
      }
    },
    mounted(){
    },
    methods: {
      /**
       * 头部下拉菜单user
       * @param $event
       */
      downLoadUser(){
        this.active = !this.active;
      },

      /**
       * 下拉
       * @param $event
       */
      download($event){
        var li = $event.target.parentNode.tagName == "A" ? $event.target.parentNode.parentNode : $event.target.parentNode;
        if(obj && obj != li){obj.classList.remove("open")};
        var classList = li.classList;
        for(var i=0,len=classList.length;i<len;i++){
          var classed = classList[i];
          if(classed == 'open'){
            classList.remove("open");
          }else{
            classList.add("open");
          }
        }
        obj = li;
      }
    }
  }
</script>
<style>

</style>

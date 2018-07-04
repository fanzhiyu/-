<template>
  <div name="home" class="home">
    <div class="custom-widgets">
      <div class="row-one">
        <div class="widget">
          <div class="stats-left ">
            <h5>Today</h5>
            <h4> Users</h4>
          </div>
          <div class="stats-right">
            <label>90</label>
          </div>
          <div class="clearfix"> </div>
        </div>
        <div class="widget states-mdl">
          <div class="stats-left">
            <h5>Today</h5>
            <h4>Visitors</h4>
          </div>
          <div class="stats-right">
            <label> 85</label>
          </div>
          <div class="clearfix"> </div>
        </div>
        <div class="widget states-thrd">
          <div class="stats-left">
            <h5>Today</h5>
            <h4>Tasks</h4>
          </div>
          <div class="stats-right">
            <label>51</label>
          </div>
          <div class="clearfix"> </div>
        </div>
        <div class="widget states-last">
          <div class="stats-left">
            <h5>Today</h5>
            <h4>Alerts</h4>
          </div>
          <div class="stats-right">
            <label>30</label>
          </div>
          <div class="clearfix"> </div>
        </div>
        <div class="clearfix"> </div>
      </div>
    </div>
    <div class="graph-visualization">
      <div class="col-md-6 map-1">
        <h3 class="sub-tittle">天气</h3>
        <div class="weather">
          <div class="weather-top">
            <div class="weather-top-left">
              <div class="degree" v-html="today.canvas">{{today.canvas}}
                <span>{{today.temperature}}</span>
                <div class="clearfix"></div>
              </div>
              <p>
                {{today.week}}
                <label>{{today.weather}}</label>
              </p>
            </div>
            <div class="weather-top-right">
              <p><i class="fa fa-map-marker"></i>{{today.city}}</p>
            </div>
            <div class="clearfix"> </div>
          </div>
          <div class="clearfix"> </div>
          <div class="weather-bottom">
            <div class="weather-bottom1" v-for="item in future">
              <div class="weather-head">
                <h4>{{item.weather}}</h4>
                <figure class="icons" v-html="item.canvas">{{item.canvas}}
                </figure>
                <h6>{{item.temperature}}</h6>
                <div class="bottom-head">
                  <p>{{item.week}}</p>
                </div>
              </div>
            </div>
            <div class="clearfix"> </div>
          </div>
        </div>
      </div>
      <div class="col-md-6 map-2">
        <div class="profile-nav alt">
          <section class="panel">
            <div class="user-heading alt clock-row terques-bg">
              <h3 class="sub-tittle clock">Easy Clock </h3>
            </div>
            <ul id="clock">
              <li id="sec"></li>
              <li id="hour"></li>
              <li id="min"></li>
            </ul>

            <ul class="clock-category">
              <li>
                <a href="#" class="active">
                  <img src="../../static/images/time.png" alt="">
                  <span>Clock</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <img src="../../static/images/alarm.png" alt="">
                  <span>Alarm</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <img src="../../static/images/watch.png" alt="">
                  <span>Stop watch</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <img src="../../static/images/timer.png" alt="">
                  <span>Timer</span>
                </a>
              </li>
            </ul>
          </section>
        </div>
      </div>
      <div class="clearfix"> </div>
    </div>
  </div>
</template>
<script>
  export default{
    name: 'home',
    data(){
      return {
        today: {},
        future: [],
      }
    },
    mounted(){
      this.init();
    },
    methods:{
      /**
       * 页面初始化
       */
      init(){
//        this.$httpService.getWeather().then((res)=>{
//          var data = res.data;
//          this.today = data.result.today
//          this.future = data.result.future;
//          this.setWeather(this.today);
//          for(var i=0,len=this.future.length;i<len;i++){
//            var list = this.future[i];
//            this.setWeather(list);
//          }
//          this.$nextTick(() =>{
//            this.initIcons();
//          });
//        })
      },

      /**
       * 初始化icons
       */
      initIcons(){
        var icons = new Skycons({"color": "#00C6D7"}),
          list  = [
            "clear-day", "clear-night", "partly-cloudy-day",
            "partly-cloudy-night", "cloudy", "rain", "sleet", "snow", "wind",
            "fog","partly-rain-day"
          ],i;
        for(i=0;i<list.length;i++){
          var element = document.getElementsByClassName(list[i]);
          for(var e = element.length; e--;){
            icons.set(element[e],list[i]);
          }
          icons.play();
        }
      },

      /**
       * 设置天气信息
       */
      setWeather(item){
        switch (item.weather){
          case "晴":
            item.canvas = "<canvas class='clear-day' width='40' height='40'></canvas>";
            break;
          case "多云":
            item.canvas = "<canvas class='cloudy' width='40' height='40'></canvas>";
            break;
          case "晴转多云":
            item.canvas = "<canvas class='partly-cloudy-day' width='40' height='40'></canvas>";
            break;
          case "多云转晴":
            item.canvas = "<canvas class='partly-cloudy-day' width='40' height='40'></canvas>";
            break;
          case "雨":
            item.canvas = "<canvas class='rain' width='40' height='40'></canvas>";
            break;
          case "雨夹雪":
            item.canvas = "<canvas class='sleet' width='40' height='40'></canvas>";
            break;
          case "雪":
            item.canvas = "<canvas class='snow' width='40' height='40'></canvas>";
            break;
          case "风":
            item.canvas = "<canvas class='wind' width='40' height='40'></canvas>";
            break;
          case "雾":
            item.canvas = "<canvas class='fog' width='40' height='40'></canvas>";
            break;
          case "多云转小雨":
            item.canvas = "<canvas class='rain' width='40' height='40'></canvas>";
            break;
          case "小雨转多云":
            item.canvas = "<canvas class='rain' width='40' height='40'></canvas>";
            break;
          default:
            break;
        }
      },
    }
  }
</script>
<style>
.home{
  width: 100%;
  height: 100%;
  margin-top: 70px;
}
</style>

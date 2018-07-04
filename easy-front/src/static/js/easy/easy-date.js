const cfg = require('./config/easy-cfg');
const env = cfg.EASYCFG.CONFIG.language;
const language = require('./lang/language_'+env+'.json');
!function(){

  "use strict";

  const weeks = language.EASY_DATE.week;

  const year_label = language.EASY_DATE.year;

  const month_label = language.EASY_DATE.month;

  var $left,$top,$height,$val;


  const calendarHtml = "<div class='calendar' ><div class='title'><div class='year'></div><div class='month'></div></div>" +
                  "<div class='content'><div class='days'><ul></ul></div></div></div>";
  const leftArrow = "<i class='fa fa-chevron-left'></i>";
  const rightArrow = "<i class='fa fa-chevron-right'></i>";
  var leftYearDom,rightYearDom,leftMonthDom,rightMonthDom;

  window.onTime = function(event,callback){
    var $this = $(event);
    $left = $this.offset().left;
    $top = $this.offset().top;
    $height = $this.height();
    setCalendar($this,callback);
  }

  /**
   * 获取年
   * @returns {number}
   */
  function getYear(){
    return new Date().getFullYear();
  }

  /**
   * 获取月
   * @returns {*}
   */
  function getMonth(){
    var month = new Date().getMonth()+1;;
    return month < 10 ? "0"+month : month;
  }

  /**
   * 获取日
   */
  function getDays($year,$month){
    var $days = new Date($year, $month, 0);
    return $days.getDate();
  }

  /**
   * 获取周
   */
  function getWeek($year,$month) {
    var $date = new Date($year,$month,0);
    $date = new Date($date.setDate(1));
    return $date.getDay();
  }

  /**
   * 设置页面
   */
  function setCalendar($this,callback) {
    $(".calendar").remove();
    var $position = $this.attr("position");
    var left = $left;
    var elm = $(calendarHtml).prependTo("body");
    setWeek();
    setYear($this,callback);
    setMonth($this,callback);
    setDays($this,callback);
    var $calHeight = $(".calendar").height();
    var top = $position == 'top' ? ($top-$calHeight-$height) : ($top+$height+5);
    elm.css({top:top+"px",left:left+"px"});
  }

  /**
   * 设置周title
   */
  function setWeek(){
    $(".content").find(".week").remove();
    $("<div class='week'><ul></ul></div>").appendTo($(".content"))
    var li = "";
    for(var i=0,len=weeks.length;i<len;i++){
      var week = weeks[i];
      li += "<li>"+week+"</li>";
    }
    $(li).appendTo($(".week ul"));
  }

  /**
   * 设置年
   */
  function setYear($this,callback){
    var $year = $(".year");
    var $val = $this.val() ? new Date($this.val()).format("yyyy"): getYear();
    var span = "<span>"+$val+year_label+"</span>"
    leftYearDom = $(leftArrow).bind('click',()=>{subtractYear($this)}).appendTo($year);
    $(span).bind('click',{callback:callback},(e)=>{onLoadYear($this,$(e.target),e.data['callback']);}).appendTo($year);
    rightYearDom = $(rightArrow).bind('click',()=>{addYear($this)}).appendTo($year);
  }

  /**
   * 加载年
   */
  function onLoadYear($this,$year,callback){
    unbind();
    var year = $year.text().replace("年","");
    $(".content").find(".week,.days,.years,.months").remove();
    $("<div class='years'><ul></ul></div>").appendTo($(".content"));
    $(".years").fadeIn("slow");
    var li = "";
    for(var i=year-7,len=new Number(year)+7;i<=len;i++){
      if(i == year){
        li += "<li class='this-year'>"+i+"年</li>"
      }else{
        li += "<li>"+i+"年</li>";
      }
    }
    $(li).bind("click",{callback:callback},(e)=>{
      var $y = $(e.target).text();
      $year.text($y);
      setWeek();
      setDays($this,e.data['callback']);
      bind($this);
      $(".years").hide();
    }).appendTo($(".years ul"));
  }

  /**
   * 元素解绑
   */
  function unbind() {
    leftYearDom.unbind("click");
    rightYearDom.unbind("click");
    leftMonthDom.unbind("click");
    rightMonthDom.unbind("click");
  }

  /**
   * 元素绑定
   */
  function bind($this){
    leftYearDom.bind('click',()=>{subtractYear($this)});
    rightYearDom.bind('click',()=>{addYear($this)});
    leftMonthDom.bind('click',()=>{subtractMonth($this)});
    rightMonthDom.bind('click',()=>{addMonth($this)})
  }

  /**
   * 加载月份
   */
  function onLoadMonth($this,$month,callback){
    unbind();
    $(".content").find(".week,.days,.years,.months").remove();
    $("<div class='months'><ul></ul></div>").appendTo($(".content"));
    $(".months").fadeIn("slow");
    var li = "";
    var month = $month.text().replace("月","");
    for(var i=1,len=12;i<=12;i++){
      i = i < 10 ? "0"+i : i;
      if(i == month){
        li += "<li class='this-month'>"+i+"月</li>"
      }else{
        li += "<li>"+i+"月</li>";
      }
    }
    $(li).bind("click",{callback:callback},(e)=>{
      var m = $(e.target).text();
      $month.text(m);
      setWeek();
      setDays($this,e.data['callback']);
      bind($this);
      $(".months").hide();
    }).appendTo($(".months ul"));

  }

  /**
   * 设置月
   * @param $this
   */
  function setMonth($this,callback) {
    var $month = $(".month");
    var m = new Date($this.val()).getMonth()+1
    var $val = $this.val() ? m < 10 ? "0" + m : m : getMonth();
    var span = "<span>"+$val+month_label+"</span>";
    leftMonthDom = $(leftArrow).bind('click',()=>{subtractMonth($this)}).appendTo($month);
    $(span).bind('click',{callback:callback},(e)=>{onLoadMonth($this,$(e.target),e.data['callback'])}).appendTo($month);
    rightMonthDom = $(rightArrow).bind('click',()=>{addMonth($this)}).appendTo($month);
  }

  /**
   * 设置天
   * @param $this
   */
  function setDays($this,callback){
    $(".content").find(".days").remove();
    $("<div class='days'><ul></ul></div>").appendTo($(".content"));
    var dayEle = $(".days ul");
    dayEle.find("li").remove()
    var year = $(".year").text().replace(year_label,"");
    var month = $(".month").text().replace(month_label,"");
    var week = getWeek(year, month);
    var days = getDays(year, month);
    var lastDays = getDays(year, month-1);
    var array = [];
    for(var j=lastDays; j>lastDays-week; j--){
      array.push(j);
    }
    for(var k=array.length;k > 0; k--){
      var day = array[k-1];
      day = day < 10 ? "0" + day : day;
      $("<li><div class='last-day'>"+day+"</div></li>").bind('click',{callback:callback},(e)=>{
        var $that = $(e.target);
        var $val = $that.text();
        setVal(year,month-1,$val,$this,e.data['callback']);
      }).appendTo(dayEle);
    }
    for(var i=1,len=days+1; i<len; i++){
      var day = i < 10 ? "0" + i : i;
      $("<li><div class='day'>"+day+"</div></li>").bind("click",{callback:callback},(e)=>{
          var $that = $(e.target);
          var $val = $that.text();
          setVal(year,month,$val,$this,e.data['callback']);
        }).appendTo(dayEle);;
    }
  }

  /**
   * 设置值
   * @param $this
   */
  function setVal(year,month,day,$this,callback){
    var $date = new Date(year,month-1,day).format("yyyy-MM-dd");
    $this.val($date);
    if(typeof callback === "function"){callback($date)}
    $(".calendar").remove();
  }

  /**
   * 加年
   */
  function addYear($this){
     var year = $(".year").text().replace(year_label,"");
     $(".year span").text(parseInt(year)+1+year_label);
     setDays($this);
  }

  /**
   * 减年
   */
  function subtractYear($this){
    var year = $(".year").text().replace(year_label,"");
    $(".year span").text(parseInt(year)-1+year_label);
    setDays($this);
  }

  /**
   * 加月
   * @param $this
   */
  function addMonth($this){
    var month = $(".month").text().replace(month_label,"");
    var m = parseInt(month)+1;
    if(m > 12){addYear($this);m=1}
    m =  m < 10 ? "0" + m : m;
    $(".month span").text(m+month_label);
    if(m<12){setDays($this)}
  }

  /**
   * 减月
   * @param $this
   */
  function subtractMonth($this){
    var month = $(".month").text().replace(month_label,"");
    var m = parseInt(month)-1;
    if(m <= 0){subtractYear($this);m=12}
    m =  m < 10 ? "0" + m : m;
    $(".month span").text(m+month_label);
    if(m>0){setDays($this)}
  }

  document.addEventListener("click",(e)=>{
    var $this = $(e.target);
    if($this.closest(".date").length == 0 && $this.closest(".calendar").length == 0){
      $(".calendar").remove();
    }
  })

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
}(window);

 define(function (require, exports, module) {
    require('jquery');
    require('backbone');
   // var template = require('artTemplate');
  // 控件
    var datetimepicker = require('../js/map_query/datetimepicker');
    datetimepicker.init();
    var alarmtable = {
        show:function(){
           $(".active").css('color','#222');
        }
    };
   alarmtable.show();
});
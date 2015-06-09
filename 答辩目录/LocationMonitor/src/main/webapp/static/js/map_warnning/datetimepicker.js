define(function (require, exports, module){
    // 加载依赖模块
    require('jquery');
    require('bootstrap');
    require('datetimepicker');
    var datetimepicker= {
        init: function () {
            $(function () {
            	$('#add_time').val("");
                $('#add_time').datetimepicker({
                	format: "yyyy/MM/dd/ hh:ii:mm",
                    //timePicker12Hour: false,
                    autoclose: true,
                    language: 'cn',
                    pickDate: true,
                    pickTime: true,
                    hourStep: 1,
                   // timePickerIncrement: 60,
                }); 
            });
        }
    };
    module.exports = datetimepicker;
});
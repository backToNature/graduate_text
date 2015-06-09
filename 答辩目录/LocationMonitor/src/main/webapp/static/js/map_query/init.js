// 入口
define(function (require, exports, module) {
    require('jquery');
    require('backbone');
    // 地图部分
    var map = require('../map_query/map');
    var template = require('artTemplate');
    map.init();



    // 日历控件
    var datetimepicker = require('../map_query/datetimepicker');
    datetimepicker.init();
    
   /* // 区域控件
    var city_select = require('../map_query/city_select');
    city_select.init();
    */

    // 表单
    var form = require('../form');
    form.init();

    // 列表逻辑
    var list = require('../map_query/list');
    list.init();


    //数据模型
    var $$map_model = require('../map_query/map_model');
    $$map_model.on('change:list', function () {

        var data = $$map_model.get('list'), i;

        if (!window.detail_status || !window.detail_status.length) {
            window.detail_status = [];
            for (i = 0 ; i < $('.show_data').find('li').length ; i++) {
                window.detail_status.push(1);
            }
        }
        if (data.data.items.length && data.data.items[0].data) {
            _.each(data.data.items[0].data, function (val, index) {
                if (val.items.length && val.items[0].longitude) {
                    var fristData = val.items[0];
                    window.myMap.panTo(new BMap.Point(fristData.longitude, fristData.latitude));
                    return;
                }
            });
//            var fristData = data.data.items[0].data[0].items[0];
//            window.myMap.panTo(new BMap.Point(fristData.longitude, fristData.latitude));
        }
        $('#search_IMEI').val(window.param.imei);
        $('#search_IMSI').val(window.param.imsi);
        $('#search_time').val(window.param.time);
        $('#search_phone').val(window.param.phone);

        var show_data_ul =  $('.show_data');

        var map_pagination = $('.map_pagination');

        var content_marquee = $('.map_marquee');



        if (data.data.totalCount == 0  || !data.data) {
            show_data_ul.append('<div class="no_data"><img src="../img/a6a41911xc66ca9b23837&690.jpg"><span>没有数据，请重新查询...</span></div>');
            map_pagination.html('');
            content_marquee.html('');
            return;
        }
        var lisr_render = template('obj_list', data.data.items[0]);

        var pagination_render = template('obj_pagination', data.data);
        $(".map_warning").find(".loading").remove();//ch
        show_data_ul.find('ul').remove();
        $('.map_pagination').html('');
		$('.map_marquee').html('');
        show_data_ul.append("<ul></ul>");
        show_data_ul.find('ul').append(lisr_render);
        map_pagination.attr('data-totalPage', data.data.totalPage)
            .attr('data-totalCount', data.data.totalCount)
            .attr('data-page', data.data.items[0].page)
            .append(pagination_render);

        // 跑马灯
        var marquee_render = template('Tpl_marquee', data.data.items[0]);
        content_marquee.html(marquee_render);
    });

    // 默认查询数据
    window.param = {
        imei: null,
        imsi: null,
        phone: null,
//        time: null,
        time: null,
//        longitudeA: 116.449,
//        latitudeA: 39.805,
//        longitudeB: 116.450,
//        latitudeB: 39.807,
        longitudeA: null,
        latitudeA: null,
        longitudeB: null,
        latitudeB: null,
        pageNumber: 1,
        recordPerPage: 6
    };
    $$map_model.search(window.param, function (data) {
    /*	 $('.map_pagination').html('');                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
         $('.map_marquee').html('');*/
    	 $(".map_warning").find(".loading").remove();//ch
        if (data.data.totalCount == 0  || !data.data) {
            $('.show_data').append('<div class="no_data"><img src="../img/a6a41911xc66ca9b23837&690.jpg"><span>没有数据，请重新查询...</span></div>');
            return;
        }
    } ,function (){
        $('.map_warning').append('<div class="loading"><img src="../img/loading.gif"><p>加载中，请稍后...</p></div>');
        return ;
    });
});
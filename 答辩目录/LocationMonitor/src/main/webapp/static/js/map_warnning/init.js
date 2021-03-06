//入口
define(function (require, exports, module) {
	require('jquery');
	require('backbone');
    var $$warnning_model = require('../map_warnning/warnning_model');


    // 地图部分
	var map = require('../map_warnning/warnning');
    var template = require('artTemplate');

    map.init();



    // 日历控件
    var datetimepicker = require('../map_warnning/datetimepicker');
    datetimepicker.init();

    // 列表部分
    var list = require('../map_warnning/list');
    list.init();



    $$warnning_model.on('change:list', function () {
        var data = $$warnning_model.get('list');
        var lisr_render = template('tpl_show_data', data.data.items[0]);
        var show_data_ul = $('.show_data');
        var map_pagination = $('.map_pagination');
        var pagination_render = template('obj_pagination', data.data);
        show_data_ul.html('<ul></ul>');
        show_data_ul.find('ul').html(lisr_render);
        map_pagination.attr('data-totalPage', data.data.totalPage)
            .attr('data-totalCount', data.data.totalCount)
            .attr('data-page', data.data.items[0].page)
            .html(pagination_render);

    });

    window.param = {
        pageNumber: 1,
        recordPerPage: 6
    };

    $$warnning_model.search(window.param);
});
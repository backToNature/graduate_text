define(function (require, exports, module){
	// 加载依赖模块
	require('jquery');
	var $$map_model = require('./map_query/map_model');
	var form = {
			init: function () {
				var self = this;
				$(function () {
					// 显示隐藏查询表单
					self.slide();
					// map查询表单请求
					self.search();
					// 是否展示轨迹
					self.locus();
					// warnning添加表单请求
					self.alide();
				});
			},
			slide: function () {
				$('.search_status').on('click', function () {
					var icon = $('.search_status p i');
					$('.search_form').slideToggle();
					if (icon.hasClass('icon-chevron-up')) {
						$('.search_status p span').text('显示查询条件 ');
					} else {
						$('.search_status p span').text('隐藏查询条件 ');
					}
					icon.toggleClass('icon-chevron-up');
					icon.toggleClass('icon-chevron-down');
				});
			},
			alide: function () {
				$('.add_status').on('click', function () {
					var icon = $('.add_status p i');
					$('.add_form').slideToggle();
					if (icon.hasClass('icon-chevron-up')) {
						$('.add_status p span').text('添加告警信息  ');
					} else {
						$('.add_status p span').text('查看已有告警信息 ');
					}
					icon.toggleClass('icon-chevron-up');
					icon.toggleClass('icon-chevron-down');
				});
			},
			// 对轨迹展示的控制
			locus: function () {
				$('[name="Trajectory"]').change(function () {
					if ($(this).val() == 1) {
						window.show_index = 'show';
						_.each(window.polylineArr, function (val, index) {
							if (window.show_index != 'hide' && window.show_index == index) {
								val.show();
								return;
							} else {
								val.show();
							}
						});
					} else {
						window.show_index = 'hide';
						_.each(window.polylineArr, function (val, index) {
							val.hide();
						});
					}
				});
			},
			search: function () {
				var search_button = $('#form_search');
				search_button.on('click', function (e) {
					$(".map_warning").find(".loading").remove();//ch
					imei = $('#search_IMEI').val();
					imsi = $('#search_IMSI').val();
					time = $('#search_time').val();
					phone = $('#search_phone').val();
					window.param.imei = imei;
					window.param.imsi = imsi;
					window.param.time = time;
					window.param.phone = phone;
					$(".map_warning").find(".no_data").remove();
					$(".map_warning").find(".loading").remove();//ch
					$(".show_data").append("<ul></ul>");
					$$map_model.search(window.param, function (data) {
						$(".map_warning").find(".loading").remove();//ch
						if (data.data.totalCount == 0  || !data.data) {
							$(".map_warning").find(".loading").remove();//ch
							$('.show_data').html('');
							$('.show_data').append('<div class="no_data"><img src="../img/a6a41911xc66ca9b23837&690.jpg"><span>没有数据，请重新查询...</span></div>');
							$('.map_pagination').html('');
							$('.map_marquee').html('');
							return;
						}
					} ,function (){
						/*$('.show_data').html('');
						$('.map_pagination').html('');
					    $('.map_marquee').html('');*/
						$('.map_warning').append('<div class="loading"><img src="../img/loading.gif"><p>加载中，请稍后...</p></div>');
						return ;
					});
				});
			}
	};
	module.exports = form;
});
define(function (require, exports, module) {
	require('jquery');
	var haderhoner = {
			header: function () {
				var htmlheader = "<li class=\"home\"><a href=\"../html/home.html\">首页</a></li><li>·</li><li class=\"map\"><a href=\"../html/map.html\">地图展示</a></li><li>·</li><li  class=\"map_warnning\"><a href=\"../html/map_warnning.html\">告警管理</a>" +
				"</li><li>·</li><li class=\"globle_header_tab\"><a href=\"#\" class=\"globle_open_list\">数据管理</a>" +
				"<div class=\"globle_header_nav_option\"><p class=\"tab_litterlist\"><a href=\"../html/statistics.html\" >数据统计</a></p>" +
				"<p class=\"tab_litterlist\"><a href=\"../html/card_analysis.html\" >换卡分析</a></p></div></li><li>·</li><li class=\"globle_header_tab\"><a href=\"#\" class=\"globle_open_list\">系统管理</a><div class=\"globle_header_nav_option\">" +
				"<p class=\"tab_litterlist\"><a href=\"../html/param_setting.html\">参数设置</a></p><p class=\"tab_litterlist\"><a href=\"#\">用户管理</a></p><p class=\"tab_litterlist\"><a href=\"../html/system.html\">运行状态</a></p><p class=\"tab_litterlist\"><a href=\"#\">日志查看</a></p>" +
				"</div></div></li>"+
				"<li class=\"globle_header_tab\"><a href=\"#\" class=\"globle_open_list\"><span class='glyphicon glyphicon-user'></span>&nbsp;当前登录：何宇恒</a><div class=\"globle_header_nav_option\"><p class=\"tab_litterlist\"><a href=\"#\">用户信息</a></p><p class=\"tab_litterlist\"><a href=\"#\">密码修改</a></p><p class=\"tab_litterlist\"><a href=\"#\">退出</a></p></div></li>";
				$(".globle_header_nav").append(htmlheader);
			},
			change: function () {
				$(function () {
					$('.globle_header_tab').hover(
							function () {
								$(this).children('.globle_header_nav_option').show(function () {
									$('.tab_litterlist').hover(
											function () {
												$(this).css('background', '#336699');
											},
											function () {
												$(this).css('background', '#33407A');
											});
								});
							},
							function () {
								$(this).children('.globle_header_nav_option').hide();
							}
					);
				});
			},
			fullScreen: function () {
				// 地图页面取消滚动条
				if ($('#myMap').length) {
					var height = $(window).height() - 56;
					$('.list_content').css('height', height + 'px');
					$('.map_list').css('height', height + 'px');
					$('.slide_child').css('height', height + 'px');
				}
			},
			slideBarHide: function () {
				// 地图页面侧边隐藏
				if ($('#myMap').length) {
					var height = $(window).height() - 56,
					list_hide = $('.list-hide'),
					map_content = $('.map_content'),
					list_hide_icon = list_hide.find('i');
					list_hide.css('top', height / 2);
					list_hide.on('click', function () {
						map_content.toggle();
						list_hide.toggleClass('list-hide-left');
						list_hide.toggleClass('list-hide-right');
						list_hide_icon.toggleClass('icon-chevron-left');
						list_hide_icon.toggleClass('icon-chevron-right');
					});
				}
			},
			//页脚信息
			footerShow:function(){
				var footer = "<div class=\"footer_border_left\"></div><img class=\"footer_border_center\" src=\"../img/bottom_center.png\"><div class=\"footer_border_right\"></div><p>遂宁市公安局 地址:和平西路186 邮政编码:629000</p><p>推荐使用浏览器IE8（及以上）</p><p>版权所有 遂宁市公安局</p>";
				$(".footer").append(footer);
			},
			//展示当前显示页面位置
			getNowPage:function(){
				var url= window.location.href;
				var f = url.indexOf("/html/");
				var l = url.indexOf(".html");
				var getUrlValue = url.substring(f+6,l);  
				$(function () {
					$("."+getUrlValue+"").addClass("nav_current");
				});
			}
	};
	haderhoner.header();
	haderhoner.change();
	haderhoner.fullScreen();
	haderhoner.slideBarHide();
	haderhoner.footerShow();
	haderhoner.getNowPage();
});


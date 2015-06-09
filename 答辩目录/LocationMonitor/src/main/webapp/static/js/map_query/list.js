define(function (require, exports, module){
    // 加载依赖模块
    var $$map_model = require('../map_query/map_model');
    require('jquery');
    var template = require('artTemplate');
    var list = {
        init: function () {
            var self = this;
            $(function () {
                self.only();
                self.hover();
                self.pagination();
                self.detail();
                self.slide_child_render();
                self.detail_pagination();
            });
        },
        only: function () {
            /**
             * 只展示某个对象的点
             */
            $('.show_data').delegate('.only_show', 'click', function (e) {
                var index = $(this).closest('li').attr('data-index');
                if ($(this).hasClass('active') && $(this).hasClass('active')) {
                    _.each(window.polylineArr, function (val ,inde) {
                            _.each(window.myCompOverlayArr[inde], function (v, i) {
                                v.show();
                            });
                            window.show_index = inde;
                            val.show();
                    });
                } else {
                    $('.only_show').removeClass('active').removeClass('btn-danger').addClass('btn-success');
                    _.each(window.polylineArr, function (val ,inde) {
                        if (inde != index) {
                            _.each(window.myCompOverlayArr[inde], function (v, i) {
                                v.hide();
                            });
                            val.hide();
                        } else {
                            _.each(window.myCompOverlayArr[inde], function (v, i) {
                                v.show();
                            });
                            window.show_index = inde;
                            val.show();
                        }
                    });
                }
                $(this).toggleClass('btn-success').toggleClass('active').toggleClass('btn-danger');

            });
        },

        detail: function () {
            /**
             * 展示列表详情
             */
            var show_data = $('.show_data'),
                slide_child = $('.slide_child'),
                slide_child_back = $('.slide_child_back'),
                win_width = $(window).width(),
                self = this, i, len = show_data.find('li').length;

                window.detail_status = [];
            for (i = 0 ; i < len ; i++) {
                window.detail_status.push(1);
            }

            slide_child.css('height', ($(window).height() - 56) + 'px');
            show_data.delegate('.list_detail', 'click', function (e) {
                e.stopPropagation();
                var id = $(this).closest('li').attr('id'),
                    index = $(this).closest('li').attr('data-index');
                slide_child.attr('data-id', id).attr('data-index', index);

                self.detail_search(id, window.detail_status[index]);
                slide_child.children('*').hide();
                slide_child.show();
                slide_child.animate({
                    width: (win_width - 300) + 'px'
                },'normal','swing',function () {
                    slide_child.children('*').fadeIn(500);
                    $('.list-hide').hide();
                });
            });
            slide_child.delegate('.slide_child_back', 'click', function () {
                slide_child.children('*').fadeOut(500, function () {
                    slide_child.animate({
                        width: 0
                    },'normal','swing',function () {
                        slide_child.hide();
                        $('.list-hide').show();
                    });
                });
                if(window.myCompOverlayArr[$('.slide_child').attr('data-index')][0]) {
                    window.setTimeout(function () {
                        window.myMap.panTo(window.myCompOverlayArr[$('.slide_child').attr('data-index')][0]._point);
                    }, 600);
                }
            });
        },
        hover: function () {
            /**
             * 鼠标悬浮在列表对象时，地图对应图标效果
             */
            var list = $('.show_data'),
                id;

            list.delegate('li', 'click', function (e) {
                e.stopPropagation();
                window.myMap.panTo(new BMap.Point($(this).attr('data-longitude'), $(this).attr('data-latitude')));
            });
            list.delegate('[data-index]', 'mouseover', function () {
                id = $(this).attr('id');
                $(this).find('.marker').css('background-position', '0 61px');
                $('.mark' + id).css({
                    'background-position': '0 -72px',
                    width: '25px',
                    height: '36px',
                    'font-size': '18px'
                });
            });

            list.delegate('[data-index]', 'mouseout', function () {
                id = $(this).attr('id');
                $(this).find('.marker').css('background-position', '0 93px');
                $('.mark' + id).css({
                    'background-position': '0 0',
                    width: '22px',
                    height: '29px',
                    'font-size': '15px'
                });
            });
        },
        detail_search: function (id, locPageNum) {
            var slide_child = $('.slide_child');
            $$map_model.search_detail({
                objId: id,
                locPageNum: locPageNum,
                locCount: 20
            }, function (data) {
                $('.loading').remove();
                if (data.data.totalCount == 0  || !data.data) {
                	//$('.loading').remove();
					$('.no_data').remove();
                    slide_child.prepend('<div class="no_data"><img src="../img/a6a41911xc66ca9b23837&690.jpg"><span>没有数据，请重新查询...</span></div>');
                    return;
                }

            } ,function (){
            	$('.loading').remove();
				$('.no_data').remove();
                slide_child.prepend('<div class="loading"><img src="../img/loading.gif"><p>加载中，请稍后...</p></div>');
                
                return;
            });
        },
        detail_pagination: function () {
            /**
             * 二级分页
             */
            var slide_child = $('.slide_child'),
                slide_child_table = $('.slide_child_table'),
                self = this;
            slide_child.delegate('.detail_next', 'click', function () {
                var page = parseInt(slide_child_table.attr('data-page')),
                    totalPage = parseInt(slide_child_table.attr('data-totalPage'));
                if (totalPage <  page) {
                    return;
                } else {
                    var id = slide_child.attr('data-id'),
                        index = slide_child.attr('data-index');
                    window.detail_status[index]++;
                    self.detail_search(id, window.detail_status[index]);
                }
            });

            slide_child.delegate('.detail_pre', 'click', function () {
                var page = parseInt(slide_child_table.attr('data-page'));
                if (page <= 1) {
                    return;
                } else {
                    var id = slide_child.attr('data-id'),
                        index = slide_child.attr('data-index');
                    window.detail_status[index]--;
                    self.detail_search(id, window.detail_status[index]);
                }
            });

            slide_child.delegate('.slide_pagenation_enter', 'keydown', function (e) {
                var inputPage = $('.slide_pagenation_enter').val(),
                    page = parseInt(slide_child_table.attr('data-page')),
                    totalPage = parseInt(slide_child_table.attr('data-totalPage'));
                re = /^[1-9]+[0-9]*]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
                if (e.which == 13 && re.test(inputPage)) {
                    inputPage = parseInt(inputPage);
                    if (inputPage > totalPage || inputPage < 1) {
                        alert('请输入正确的页码');
                    } else {
                        var id = slide_child.attr('data-id'),
                            index = slide_child.attr('data-index');
                        window.detail_status[index] = inputPage;
                        self.detail_search(id, inputPage);
                    }
                }
            });
        },
        pagination: function () {
            /**
             * 分页
             */
            var resetDeatilPageNum = function () {
                if (window.detail_status) {
                    _.each(window.detail_status ,function (v, i) {
                        v = 1;
                    });
                }
            };
            var pagination_wrapper = $('.map_pagination');
            pagination_wrapper.delegate('#pre_page', 'click', function () {
                var page = parseInt(pagination_wrapper.attr('data-page'));
                if (page <= 1) {
                    return;
                } else {
                    resetDeatilPageNum();
                    window.param.pageNumber = page - 1;
                    $$map_model.search(window.param, function (data) {
                    	 $(".map_warning").find(".no_data").remove();
                    	 $(".map_warning").find(".loading").remove();
                        if (data.data.totalCount == 0  || !data.data) {
                            $('.show_data').append('<div class="no_data"><img src="../img/a6a41911xc66ca9b23837&690.jpg"><span>没有数据，请重新查询...</span></div>');
                            $('.map_pagination').html('');
                            $('.map_marquee').html('');
                            return;
                        }
                    } ,function (){
                        $('.show_data').append('<div class="loading"><img src="../img/loading.gif"><p>加载中，请稍后...</p></div>');
                        $('.show_data').find("ul").html('');
                        $('.map_pagination').html('');
                        $('.map_marquee').html('');
                        return ;
                    });
                }
            });

            pagination_wrapper.delegate('#next_page', 'click', function () {
                var page = parseInt(pagination_wrapper.attr('data-page')),
                    totalPage = parseInt(pagination_wrapper.attr('data-totalPage'));

                if (totalPage <=  page) {
                    return;
                } else {
                    resetDeatilPageNum();
                    window.param.pageNumber = page + 1;
                    $$map_model.search(window.param, function (data) {
                    	$(".map_warning").find(".loading").remove();//ch
                    	$(".map_warning").find(".no_data").remove();
                        if (data.data.totalCount == 0  || !data.data) {
                            $('.show_data').append('<div class="no_data"><img src="../img/a6a41911xc66ca9b23837&690.jpg"><span>没有数据，请重新查询...</span></div>');
                            $('.map_pagination').html('');
                            $('.map_marquee').html('');
                            return;
                        }
                    } ,function (){
                        $('.show_data').append('<div class="loading"><img src="../img/loading.gif"><p>加载中，请稍后...</p></div>');
                        $('.show_data').find("ul").html('');
                        $('.map_pagination').html('');
                        $('.map_marquee').html('');
                        return ;
                    });
                }
            });

            pagination_wrapper.delegate('#enter_page', 'keydown', function (e) {
                var inputPage = $('#enter_page').val(),
                    page = parseInt(pagination_wrapper.attr('data-page')),
                    totalPage = parseInt(pagination_wrapper.attr('data-totalPage'));
                    re = /^[1-9]+[0-9]*]*$/; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
                if (e.which == 13 && re.test(inputPage)) {
                    inputPage = parseInt(inputPage);
                    if (inputPage > totalPage || inputPage < 1) {
                        alert('请输入正确的页码');
                    } else {
                        resetDeatilPageNum();
                        window.param.pageNumber = inputPage;
                        $$map_model.search(window.param, function (data) {
                        	 $(".map_warning").find(".loading").remove();//ch
                        	 $(".map_warning").find(".no_data").remove();
                            if (data.data.totalCount == 0  || !data.data) {
                                $('.show_data').append('<div class="no_data"><img src="../img/a6a41911xc66ca9b23837&690.jpg"><span>没有数据，请重新查询...</span></div>');
                                $('.map_pagination').append('');
                                $('.map_marquee').append('');
                                return;
                            }
                        } ,function (){
                        	$(".map_warning").find(".no_data").remove();
                            $('.show_data').append('<div class="loading"><img src="../img/loading.gif"><p>加载中，请稍后...</p></div>');
                            return ;
                        });
                    }
                }
            });
        },
        slide_child_render: function () {
            /**
             * 二级侧边栏渲染逻辑
             */
            $$map_model.on('change:detail', function () {
                var slide_child_table = $('.slide_child_table'),
                    slide_child = $('.slide_child'),
                    data = $$map_model.get('detail');
                if (data.data && data.data.items[0]) {
                    var items = data.data.items[0];
                    list_data = $$map_model.get('list');
                    list_data.data.items[0].data[slide_child.attr('data-index')].items = items.data;
                    $$map_model.set('list', list_data);
                    $$map_model.trigger("change:list");
                    slide_child_table.attr('data-page', items.page);
                    slide_child_table.attr('data-totalCount', data.data.totalCount);
                    slide_child_table.attr('data-totalPage', data.data.totalPage);
                    $('.slide_pagenation_totalPage').text('共'+ data.data.totalPage + '页');
                    $('.slide_pagenation_enter').val(items.page);
                    var lisr_render = template('Tpl_slideTable', items);
                    slide_child_table.html(lisr_render);
                }
            });
//            if(!+[1,]){
//                alert("这是ie浏览器");
//            } else {
//                alert("这不是ie浏览器");
//            }
        }
    };
    module.exports = list;
});
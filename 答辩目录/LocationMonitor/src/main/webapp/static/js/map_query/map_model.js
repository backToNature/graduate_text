/**
 * Created by wenshui on 15/3/28.
 */
define(function (require, exports, module){
    require('jquery');
    require('backbone');
    var Model = Backbone.Model.extend({
        defaults: {
            list: {},
            detail: {}
        },
        initialize: function () {
            var _this = this;
        },
        search: function (req, fn, be) {
        //$(".map_warning").find(".loading").remove();//ch
            var _this = this;
            $.ajax({
                type: "GET",
//                url: "../demo/mock/mapQuery.json",
//                url: "http://localhost:9999/demo",
                url: "http://acm.swust.edu.cn:8180/LocationMonitor/handler/locationQuery/queryByPage",
//                url: "http://192.168.1.101:8080/LocationMonitor/handler/locationQuery/queryByPage",
                data: req,
                beforeSend: function () {
                    if ($.isFunction(be)) {
                        be();
                    }
                },
                dataType: "jsonp",
                success: function (data) {
                    if (data.errcode === 0) {
                        _this.set('list', data);
                        if ($.isFunction(fn)) {
                            fn(data);
                        }
                    } else {
                        alert('接口错误');

                    }
                }
            });
        },
        search_detail: function (req, fn, be) {
            var _this = this;
            $.ajax({
                type: "GET",
//                url: "http://acm.swust.edu.cn:8180/LocationMonitor/handler/locationQuery/queryLocByPage",
                url: "http://acm.swust.edu.cn:8180/LocationMonitor/handler/locationQuery/queryLocation",
                data: req,
                beforeSend: function () {
                    if ($.isFunction(be)) {
                        be();
                    }
                },
                dataType: "jsonp",
                success: function (data) {
                    if (data.errcode === 0) {
                        _this.set('detail', data);
                        if ($.isFunction(fn)) {
                            fn(data);
                        }
                    } else {
                        alert('接口错误');
                        if ($.isFunction(fn)) {
                            fn(data);
                        }
                    }
                }
            });
        }
    });
    module.exports = new Model();
});
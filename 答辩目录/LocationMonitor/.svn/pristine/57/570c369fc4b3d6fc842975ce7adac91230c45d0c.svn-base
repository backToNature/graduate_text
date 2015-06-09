define(function(require, exports, module) {
	require('jquery');
	require('backbone');
	require('highcharts');

	var homehoner = {
		osname : null,
		jdkpath : null,
		userhome : null,
		appdir : null,
		servipaddr : null,
		freememory : null,
		totalmemory : null,
		maxmemory : null,
		servstatus : null,
		enginesummary : null,
		showserver : function() {
			$
					.ajax({
						type : "post",
						content : "application/x-www-form-urlencoded;charset=UTF-8",
						url : "../../handler/system/viewServerInfo",
						dataType : 'json',
						async : false,
						success : function(result) {
							if (result.ret == 1) {
								var msg = result.data.data;
								homehoner.osname = msg.osname + " V"
										+ msg.osversion + " " + msg.ostype;
								homehoner.jdkpath = msg.jdkpath + " V"
										+ msg.jdkversion;
								homehoner.userhome = msg.userhome;
								homehoner.appdir = msg.appdir;
								homehoner.servipaddr = msg.servipaddr;
								homehoner.freememory = msg.freememory + " MB";
								homehoner.totalmemory = msg.totalmemory + " MB";
								homehoner.maxmemory = msg.maxmemory + " MB";
							}
						}
					});
		},
		showserverInfo : function() {
			$("#osname").append(homehoner.osname);
			$("#jdkpath").append(homehoner.jdkpath);
			$("#userhome").append(homehoner.userhome);
			$("#appdir").append(homehoner.appdir);
			$("#servipaddr").append(homehoner.servipaddr);
			$("#freememory").append(homehoner.freememory);
			$("#totalmemory").append(homehoner.totalmemory);
			$("#maxmemory").append(homehoner.maxmemory);
		},
		showserverstatus : function() {
			$.ajax({
				type : "post",
				content : "application/x-www-form-urlencoded;charset=UTF-8",
				url : "../../handler/system/viewServerStatus",
				dataType : 'json',
				async : false,
				success : function(result) {
					if (result.ret == 1) {
						var msg = result.data.data;
						homehoner.servstatus = msg.servstatus;
					}
				}
			});
		},
		showserverstatusInfo : function() {
			// var serstatus_title = "<strong>60秒钟自动刷新服务器状态信息：</strong><br />";
			// $("#servstatus").empty().append(serstatus_title);
			$("#servstatus").append(homehoner.servstatus);
		},
		showenginestatus : function() {
			$.ajax({
				type : "post",
				content : "application/x-www-form-urlencoded;charset=UTF-8",
				url : "../../handler/system/viewEngineStatus",
				dataType : 'json',
				async : false,
				success : function(result) {
					if (result.ret == 1) {
						var msg = result.data.data;
						homehoner.enginestatus = msg.enginestatus;
						homehoner.enginesummary = msg.enginesummary;
					}
				}
			});
		},
		showenginestatusInfo : function() {
			// var serstatus_title = "<strong>60秒钟自动刷新服务器状态信息：</strong><br />";
			// $("#servstatus").empty().append(serstatus_title);
			$("#enginesummary").empty();
			$("#enginesummary").append(homehoner.enginesummary);
		},
	};
	homehoner.showserver();
	homehoner.showserverInfo();
	homehoner.showserverstatus();
	homehoner.showserverstatusInfo();
	homehoner.showenginestatus();
	homehoner.showenginestatusInfo();
	// setInterval("showserverstatus()", 60000);
});
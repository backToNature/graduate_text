define(function (require, exports, module){
	// ��������ģ��
	//alert("cc");
	require('jquery');
	require('bootstrap'); 

	var city_select = { 
			init: function () { 
				//alert("fx");
				$("#search_place").append("<div" +
						"style='width:250px;display:none;position:absolute;" +
						"z-index:3000;top:60px;left:0px;'" +
						"id='cityList' class='panel panel-primary'>" +
						"<div style='height:10%;width:100%;' class='panel-heading'>" +
						"<span id='hotcity' class='panel-title'><a href='#'>" +
						"&nbsp;热门</a></span> <span id='a_d' class='panel-title  '><a" +
						"	href='#'>&nbsp;ABCD</a></span> <span id='e_j' " +
						"class='panel-title  '><a	href='#'>&nbsp;EFGHJ</a></span>" +
						" <span id='k_n' class='panel-title  '><a" +
						"	href='#'>&nbsp;KLMN</a></span>" +
						" <span id='p_w' class='panel-title  '><a" +
						"	href='#'>&nbsp;PQRSTW</a></span>" +
						" <span id='x_z' class='panel-title  '><a" +
						"	href='#'>&nbsp;XYZ</a></span>	</div>" +
						"	<div style='height:90%;width:100%;' id='cityName'" +
						"class='panel-body'></div>	</div>");
				$(function () {  
					var hotCity="";
					var A_DCity="";
					var E_JCity="";
					var K_NCity="";
					var P_WCity="";
					var X_ZCity=""; 
					$.ajax({
		                type: "GET",
		                url: "../demo/mock/cityQuery.json", 
		                dataType: "json",
		                success: function (result) {
		                	var cityArray = result.city;
		                	for(var i=0;i<cityArray.length;i++){
		                		if(cityArray[i].cityType=='HOT_CITY'){
		                			hotCity = cityArray[i].cityName; 
		                		}
		                		if(cityArray[i].cityType=='A_D'){
		                			A_DCity = cityArray[i].cityName;
		                		}
		                		if(cityArray[i].cityType=='E_J'){
		                			E_JCity = cityArray[i].cityName;
		                		}
		                		if(cityArray[i].cityType=='K_N'){
		                			K_NCity = cityArray[i].cityName;
		                		}
		                		if(cityArray[i].cityType=='P_W'){
		                			P_WCity = cityArray[i].cityName;
		                		}
		                		if(cityArray[i].cityType=='X_Z'){
		                			X_ZCity = cityArray[i].cityName;
		                		} 
		                	} 
		                }
		            });
					
					
					$("#hotcity").click(function(){
						$("#cityName").html(cityStr(hotCity)); 
					}); 
					//a_d����ĸ�ĳ���
					$("#a_d").click(function(){  
						$("#cityName").html(cityStr(A_DCity));
					});

					//e_j����ĸ�ĳ���
					$("#e_j").click(function(){  
						$("#cityName").html(cityStr(E_JCity));
					});
					//k_n����ĸ�ĳ���
					$("#k_n").click(function(){  
						$("#cityName").html(cityStr(K_NCity));
					});
					//p_w����ĸ�ĳ���
					$("#p_w").click(function(){  
						$("#cityName").html(cityStr(P_WCity));
					});
					//
					//x_z����ĸ�ĳ���
					$("#x_z").click(function(){  
						$("#cityName").html(cityStr(X_ZCity));
					});   
					
					//���봰�ڵĵ���¼�
					var iClick=0; 
					$("#search_place").click(function(){   
						if(iClick%2==0){
							$("#cityList").show();
							$("#cityName").html(cityStr(hotCity)); 
							iClick++;
						}
						else{
							$("#cityList").hide();
							iClick--;
						}
					});  
					$("#cityList").delegate(".cityname","click",function(){ 
						$("#search_place").val(this.innerHTML); 
						$("#cityList").hide(); 
						iClick--;//�жϵ�������������ż�ı�־
					});  
					/*$("#cityList").blur(function(){ 
						alert("hide");
						$("#cityList").hide(); 
						iClick--;//�жϵ�������������ż�ı�־
					});*/ 
			});  //$(func
	}//init
}; //city_select
module.exports = city_select;
});

function cityStr(cityArray){
	var citystr=""; 
	for(var i=0;i<cityArray.length;i++){ 
		citystr += "<span  style='width:42px;padding:5px;margin:0;display:inline-block'>" +
				"<a href='#' class='cityname' >"+cityArray[i]+"</a></span>";
	}
	return citystr;
} 


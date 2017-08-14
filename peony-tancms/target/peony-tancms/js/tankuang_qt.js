$(document).ready(function() {
jQuery.tanchukuang = function(tabtit,tabtit2,shijian) {
$(tabtit).bind(shijian,function(){
	
$(tabtit2).toggle();
$("body").append("<div class='tneirong_3'></div>");
 var br_height = $(window).height()
 function dibugaodu() {
	 var br_height = $(window).height()
$(".tneirong_3").height(br_height);};
$(window).load(function() {   dibugaodu();});
$(window).resize(function() {  dibugaodu();});
$(".tneirong_3").height(br_height);
 var div_height = -$(tabtit2).find(".tneirong_1").height()/2
 var div_width = -$(tabtit2).find(".tneirong_1").width()/2
$(tabtit2).children(".tneirong_1").css("margin-top",div_height).css("margin-left",div_width);});
$(tabtit2).find(".fabiao1,.fabiao2,.guanbi,.tneirong_3").bind(shijian,function(){
	$(".tneirong_3").remove();
$(tabtit2).toggle();});};
	/*调用方法如下：*/
	$.tanchukuang(".grzx_znxx1_a",".grzx_znxx1","click");
	$.tanchukuang(".grzx_znxx2_a",".grzx_znxx2","click");
	$.tanchukuang(".grzx_znxx3_a",".grzx_znxx3","click");
	$.tanchukuang(".grzx_znxx4_a",".grzx_znxx4","click");
}); 



 
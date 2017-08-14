var pageContext;
var type;
type=$("#type").val();
pageContext = $("#pageContext").val();
$(document).ready(function(){ 
$('#listregionindex').load(pageContext+'/regionindex/listRegionIndex?timestamp='+Date.parse(new Date())+"&type="+type);
$('#listshengdistribute').load(pageContext+'/regionindex/listShengDistribute?timestamp='+Date.parse(new Date())+"&type="+type);
$('#listeventindexsheng').load(pageContext+'/eventindex/listEventIndexSheng?timestamp='+Date.parse(new Date())+"&type="+type);
$('#listtendency').load(pageContext+'/regionindex/listTendency?timestamp='+Date.parse(new Date())+"&type="+type);
$('#listgrowthrate').load(pageContext+'/regionindex/listGrowthRate?timestamp='+Date.parse(new Date())+"&type="+type);
$('#listmediacountsheng').load(pageContext+'/mediacount/listMediaCountSheng?timestamp='+Date.parse(new Date())+"&type="+type);
$('#listIndustrycount').load(pageContext+'/industryindex/listIndustryCount?timestamp='+Date.parse(new Date())+"&type="+type);
$('#listdistributesheng').load(pageContext+'/industryindex/listDistributeSheng?timestamp='+Date.parse(new Date())+"&type="+type);

$("#day").on("click", function() {
	window.location.href=pageContext+"/regionindex/listDrawing?type=1";
});

$("#lastmonth").on("click", function() {
	window.location.href=pageContext+"/regionindex/listDrawing?type=2";
});

$("#month").on("click", function() {
	window.location.href=pageContext+"/regionindex/listDrawing?type=3";
});

}); 

  
	     
	  	
   

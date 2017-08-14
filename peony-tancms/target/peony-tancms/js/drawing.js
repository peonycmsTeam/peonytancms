var pageContext;
var type;
type=$("#type").val(); 
pageContext = $("#pageContext").val();
$(document).ready(function(){ 
$('#listregionchina').load(pageContext+'/regionindex/listRegionChina?timestamp='+Date.parse(new Date())+"&type="+type);
$('#listchinadistribute').load(pageContext+'/regionindex/listChinaDistribute?timestamp='+Date.parse(new Date())+"&type="+type);
$('#listtendencychina').load(pageContext+'/regionindex/listTendencyChina?timestamp='+Date.parse(new Date())+"&type="+type);
$('#listgrowthratechina').load(pageContext+'/regionindex/listGrowthRateChina?timestamp='+Date.parse(new Date())+"&type="+type);
$('#listmediacount').load(pageContext+'/mediacount/listMediaCount?timestamp='+Date.parse(new Date())+"&type="+type);
$('#listindustryindex').load(pageContext+'/industryindex/listIndustryIndex?timestamp='+Date.parse(new Date())+"&type="+type);
$('#listdistribute').load(pageContext+'/industryindex/listDistribute?timestamp='+Date.parse(new Date())+"&type="+type);
$('#listeventindex').load(pageContext+'/eventindex/listEventIndex?timestamp='+Date.parse(new Date())+"&type="+type);


$("#day").on("click", function() {
	window.location.href=pageContext+"/regionindex/listDrawings?type=1";
});

$("#lastmonth").on("click", function() {
	window.location.href=pageContext+"/regionindex/listDrawings?type=2";
});

$("#month").on("click", function() {
	window.location.href=pageContext+"/regionindex/listDrawings?type=3";
});
	    
}); 
 

 
	     
	  	
   

var pageContext;
$(function() {
	pageContext = $("#pageContext").val();
});
function selectByTime(){
	var focusid=$("#focusid").val();
	var form = document.forms[0];
	form.action = pageContext+"/focus/listFocusInfo/"+focusid;
	form.method="post";
	form.submit();
}
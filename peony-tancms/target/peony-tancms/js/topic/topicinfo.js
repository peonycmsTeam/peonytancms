var pageContext;
$(function() {
	pageContext = $("#pageContext").val();
});
function selectByTime(){
	var focusid=$("#topicid").val();
	var form = document.forms[0];
	form.action = pageContext+"/topic/listTopicInfo/"+focusid;
	form.method="post";
	form.submit();
}
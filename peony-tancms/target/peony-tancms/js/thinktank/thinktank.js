/**
 * 点击分页
 * @param pageNo
 */
function selectPage(pageNo){
	$("#pageNo").attr("value",pageNo);
	$("#search").submit();
}
/**
 * 搜索提交表单
 */
function submitForm(){
	$("#search").submit();
}
function changeChannel(){
	var pageContext=$("#pageContext").val();
	var channelId=$("#groupChannel").val();
    if(channelId=='-1'){
    	document.getElementById("div_list").innerHTML="";
    	submitForm();
    	return;
    }
	
	 $.ajax({
			type : "post",
			url : pageContext + "/thinktank/getChannelList",
			dataType : "json",
			data : {
				channelPid:channelId
			},
			success : function(data) {
				var selecthtml='<select id="channelId" name="channelId">';
				for(var i=0;i<data.list.length;i++){
					if(i==0){
						selecthtml=selecthtml+'<option value="'+data.list[i].channelId+'" selected="selected">'+data.list[i].name+'</option>';
					}else{
						selecthtml=selecthtml+'<option value="'+data.list[i].channelId+'">'+data.list[i].name+'</option>';
						}
					}
				selecthtml=selecthtml+ '</select>';
				$("#div_list").html(selecthtml);
				submitForm();
			},
			error : function() {
				jBox.info("删除失败", "提示");
			}
		});
}
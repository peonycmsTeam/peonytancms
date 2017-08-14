/**
 * 按选择时间导出
 */
function selectTime(val){
	var pageContext = $("#pageContext").val();
	var btime=$("#stime").val();
	var etime=$("#etime").val();
	if(btime==''){
		jBox.info("开始时间不能为空!", "提示");
	}else{
		if(etime==''){
			jBox.info("结束时间不能为空!", "提示");
		}else{
			var start=new Date(btime.replace("-", "/").replace("-", "/"));  
			var end=new Date(etime.replace("-", "/").replace("-", "/"));  
			 if(end<start){  
				 jBox.info("结束日期要大于开始日期!", "提示");
			    } else{
			    	parent.showPop();
			    	$("#beginTime",window.parent.document).val(btime);
			    	$("#endTime",window.parent.document).val(etime);
			    	var form = parent.document.forms[0];
			    	var type=$("#type",window.parent.document).val();
			    	var polarityorientation=$("#polarityorientation",window.parent.document).val();
			    	if(val=='2'){
			    		form.action = pageContext+"/event/downloadBySelectTime?type="+type+"&polarityorientation="+polarityorientation;
			    	}else{
			    		form.action = pageContext+"/event/downloadBySelectTimeExcel?type="+type+"&polarityorientation="+polarityorientation;
			    	}
			    	form.method="post";
			    	form.submit();
			    }
		}
	}
}
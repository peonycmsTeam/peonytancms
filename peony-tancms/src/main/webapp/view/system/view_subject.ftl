<div class="bushi_x bushi_x2">
     <form name="form1" id="form1" action="" method="post" >
     	<input type="hidden" name="subjectId" id="subjectId" value="${subject.id}">
     	<input type="hidden" name="pid" id="pid" value="${subject.id}">
     	<input type="hidden" name="state" id="state" value="1">
     	<input type="hidden" name="textstate" id="textstate" value="0">
         <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <th >操作类型：</th>
              <td>
          		<#if subject.id==1 > 
          			<!--等于1是叶子节点-->
          			<input type="radio" id="next" name="next" checked="true" onclick="showAddButton()" value="2" />新增下级
       			<#else>
           			<input type="radio" id="next" name="next" checked="true" onclick="showAddButton()" value="1" />修改分类	
           			<#if subject.ischildnodes!=1>
       				<input type="radio" id="next" name="next" onclick="showAddButton()" value="2" />新增下级
       				</#if>
				</#if>
              </td>
              <td></td>
            </tr>
            
            <tr>
              <th >分类名称：</th>
              <td><input name="subjectName" type="text" class="input_text" id="subjectName" <#if subject.id==1> value="" <#else> value="${subject.name}" </#if> /></td>
              <td>&nbsp;</td>
            </tr>
        
        	<tr>
              <th ></th>
              <td>
              	<input type="checkbox" id="gjc" name="gjc" <#if subject.ischildnodes==1> checked="checked" disabled="disabled"</#if> onclick="open_area()" value="1" />是否末级
              	（末级为关键词节点）	
              </td>
              <td>&nbsp;</td>
            </tr>
          </table>
          
            <div id="keyWordInfo" style="display: none;";>
              <table border="0" cellspacing="0" cellpadding="0">
	            <tr>
	              <th valign="top">地域名称：</th>
	              <td><textarea class="input_text2 input_text_r" name="area" id="area" cols="45" rows="5" <#if userType==2>disabled="disabled"</#if>  ><#if userType==2>${region.regionname}<#else>${subjectKeywords.area}</#if></textarea><div class="x_tishi1">地域名称用空格隔开</div></td>
	              <td>
	              	<#if userType==2>
	              	<span class="jiafu" onclick="toShowAreaClass()">修改地域关键词</span>
	              	</#if>
	              </td>
	            </tr>
	            <tr>
	              <th valign="top">主关键词:</th>
	              <td><textarea class="input_text2 input_text_r" name="main" id="main" cols="45" rows="5">${subjectKeywords.main}</textarea><div class="x_tishi1">主关键词用空格隔开</div></td>
	              <td></td>
	            </tr>
	            <tr>
	              <th valign="top">倾向性分析词：</th>
	              <td><textarea class="input_text2 input_text_r" name="qxx" id="qxx" cols="45" rows="5">${subjectKeywords.qxx}</textarea><div class="x_tishi1">倾向性分析词用空格隔开</div></td>
	              <td valign="bottom" id="glc_qxx">
	              	<span class="jiafu" onclick="toShowBaseClass()">
	              		>负面词库
	              	</span>
	                <span class="jiajia color_bai" onclick="showqxx()">+</span></td>
	            </tr>
	            
	            <!--display:block-->
	            <tr id="qxx1" style="display: none;";>
	              <td valign="top"></td>
	              <td>
	              	  <textarea class="input_text2 input_text_r" name="qxx1textarea" id="qxx1textarea" cols="45" rows="5">${subjectKeywords.qxx1textarea}</textarea>
	              </td>
	              <td nowrap="nowrap"></td>
	            </tr>
	            <tr id="qxx2" style="display: none;";>
	              <td valign="top"></td>
	              <td>
	              	  <textarea class="input_text2 input_text_r" name="qxx2textarea" id="qxx2textarea" cols="45" rows="5">${subjectKeywords.qxx2textarea}</textarea>
	              </td>
	              <td nowrap="nowrap"></td>
	            </tr>
	            
	            
	            <tr>
	              <th valign="top">过滤词：</th>
	              <td><textarea class="input_text2 input_text_r" name="glc" id="glc" cols="45" rows="5">${subjectKeywords.glc}</textarea><div class="x_tishi1">过滤词用空格隔开</div></td>
	              <td valign="bottom" id="glc_oper"><span class="jiajia color_bai" onclick="showglc()">+</span></td>
	            </tr>
	            
	            <!--display:block-->
	            <tr id="glc1" style="display: none;";>
	              <td valign="top"></td>
	              <td>
	              	  <textarea class="input_text2 input_text_r" name="glc1textarea" id="glc1textarea" cols="45" rows="5">${subjectKeywords.glc1textarea}</textarea>
	              </td>
	              <td nowrap="nowrap"></td>
	            </tr>
	            <tr id="glc2" style="display: none;";>
	              <td valign="top"></td>
	              <td>
	              	  <textarea class="input_text2 input_text_r" name="glc2textarea" id="glc2textarea" cols="45" rows="5">${subjectKeywords.glc2textarea}</textarea>
	              </td>
	              <td nowrap="nowrap"></td>
	            </tr>
            </table>
          </div>
      </form> 
      <#if subject.id==1>
      <div class="HackBox"></div>
      <div class=" blank20px"></div>
      </#if>
      <div class=" text_center">
      	<#if subject.id==1>
		<input name="添加" id="mainAdd" type="button" style="cursor:pointer;" class="tb2 fabiao1 color_bai" onclick="operationForm(1)"  value="添加" />
		<#else>
		<input name="添加" id="add" style="display:none" style="cursor:pointer;"  type="button" class="tb2 fabiao1 color_bai" onclick="operationForm(1)"  value="添加" />
		<input name="修改" id="update"  type="button" style="cursor:pointer;" class="tb2 fabiao1 color_bai" onclick="operationForm(2)"  value="修改" />
		</#if>
		<#if subject.id!=1>
		<input name="删除" type="button" style="cursor:pointer;" class="tb2 fabiao2 color_bai" onclick="operationForm(3)" value="删除" />
		</#if>
      </div>
  </div>
  <script type="text/javascript">
  	<#if subject.ischildnodes==1>
  		$("#textstate").val(1);
		$("#keyWordInfo").attr("style","");
  	</#if>
  	var tmepName ;
  	function getCheckVal(){
		var v="0";
		$("input[name='gjc']:checked").each(function() { 
			v = "1"
		}); 
 		return v;
	}
  
  
  	function operationForm(option){
  	
  		var params = {};
		params.gjc = getCheckVal();
		params.subjectName = $("#subjectName").val();
		params.pid = $("#pid").val();
		params.area = $("#area").val();
		params.main = $("#main").val();
		params.qxx = $("#qxx").val();
		params.qxx1textarea = $("#qxx1textarea").val();
		params.qxx2textarea = $("#qxx2textarea").val();
		params.glc = $("#glc").val();
		params.glc1textarea = $("#glc1textarea").val();
		params.glc2textarea = $("#glc2textarea").val();
		if(option==1){
			$.ajax({
			type : "post",
			url : encodeURI(pageContext + "/system/doAddSubject"),
			dataType : "text",
			data : params,
			success : function(data) {
				if(data == -1){
					$.jBox.tip("添加失败,关键词总数超过用户个数上限", 'error',1000);
				}else if(data != "0"){
					var strs= data.split("&"); //字符分割 
					$("#KeyWordsCount").html("当前用户总共可以设置<font color='red'>"+strs[1]+"</font>个关键词，已设置<font color='red'>"+strs[2]+"</font>个，剩余<font color='red'>"+strs[3]+"</font>个"); 
					$.jBox.tip("添加成功", 'success',1000);
					refreshTree();
				}else{
					$.jBox.tip("添加失败", 'error',1000);
				}
			}
			});
		}else if(option==2){
			if(getCheckVal()=="0"){
			//没有勾选关键词节点,只去修改subject本身
				$.ajax({
					type : "post",
					url : encodeURI(pageContext + "/system/doUpdateSubject"),
					dataType : "text",
					data : params,
					success : function(data) {
						if(data == 1){
							$.jBox.tip("修改成功", 'success',1000);
							refreshTree();
						}else{
							$.jBox.tip("修改失败", 'error',1000);
						}
					}
				});
			}else{
			//勾选了关键词节点
				$.ajax({
					type : "post",
					url : encodeURI(pageContext + "/system/doUpdateSubjectKeyWords"),
					dataType : "text",
					data : params,
					success : function(data) {
						if(data == 2){
							$.jBox.tip("当前类别下的子类别已经创建了关键词，无法在创建", 'error',1000);
						}else if(data == -1){
							$.jBox.tip("修改失败,关键词总数超过用户个数上限", 'error',1000);
						}else{
							var strs= data.split("&"); //字符分割 
							$("#KeyWordsCount").html("当前用户总共可以设置<font color='red'>"+strs[1]+"</font>个关键词，已设置<font color='red'>"+strs[2]+"</font>个，剩余<font color='red'>"+strs[3]+"</font>个"); 
							$.jBox.tip("修改成功", 'success',1000);
							refreshTree();
						}
					}
				});
			}
		}else if(option==3){
			$.ajax({
			type : "post",
			url : encodeURI(pageContext + "/system/doDeleteSubject"),
			dataType : "text",
			data : params,
			success : function(data) {
				if(data == 0){
					$.jBox.tip("当前节点下存在子节点，请先删除子节点", 'error',1000);
				}else{
					var strs= data.split("&"); //字符分割 
					$("#KeyWordsCount").html("当前用户总共可以设置<font color='red'>"+strs[1]+"</font>个关键词，已设置<font color='red'>"+strs[2]+"</font>个，剩余<font color='red'>"+strs[3]+"</font>个"); 
					$.jBox.tip("删除成功", 'success',1000);
					refreshTree();
				}
			}
			});
		}
		
		
	}
  
	function showAddButton(){
		if($("#state").val()==1){
			//新增
			$("#state").val(0);
			$("#update").attr("style","display:none");
			$("#add").attr("style","");
			tmepName =  $("#subjectName").val();
			$("#subjectName").val("");
		}else{
			$("#state").val(1);
			$("#update").attr("style","");
			$("#add").attr("style","display:none");
			$("#subjectName").val(tmepName);
		}
	}
	function open_area(){
		if($("#textstate").val()==1){
			$("#textstate").val(0);
			$("#keyWordInfo").attr("style","display:none");
		}else{
			$("#textstate").val(1);
			$("#keyWordInfo").attr("style","");
		}
	}
	function showqxx(){
		if($("#qxx1").attr("style")=="display: none;"){
			$("#qxx1").attr("style","");
		}else{
			$("#qxx2").attr("style","");
			$("#glc_qxx").html("<span class='jiafu' onclick='toShowBaseClass()'>>负面词库</span><span class='jiajia color_bai' onclick='deleteqxx()'>-</span>");
		}
	}
	function deleteqxx(){
		document.getElementById("qxx1").style.display="none";
		document.getElementById("qxx2").style.display="none";
		$("#qxx1").val("");
		$("#qxx2").val("");
		$("#glc_qxx").html("<span class='jiafu' onclick='toShowBaseClass()'>>负面词库</span><span class='jiajia color_bai' onclick='showqxx()'>+</span>");
	}
	function deleteglc(){
		document.getElementById("glc1").style.display="none";
		document.getElementById("glc2").style.display="none";
		$("#glc1").val("");
		$("#glc2").val("");
		$("#glc_oper").html("<span class='jiajia color_bai' onclick='showglc()'>+</span>");
	}
	function showglc(){
		if($("#glc1").attr("style")=="display: none;"){
			$("#glc1").attr("style","");
		}else{
			$("#glc2").attr("style","");
			$("#glc_oper").html("<span class='jiajia color_bai' onclick='deleteglc()'>-</span>");
		}
	}
</script>
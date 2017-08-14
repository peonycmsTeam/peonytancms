      <form name="form1" id="form1" action="${request.getContextPath()}/event/toListEvent" method="post" >
      <input type="hidden" id="pageNo" name="pageNo" value="1"/>
      <input type="hidden" id="eventid" name="eventid" value="${currentEventnews.eventid}"/>
      <input type="hidden" id="regionID" name="regionID" value="${currentEventnews.regionID}"/>
      <input type="hidden" id="currentEventnewsId" name="currentEventnewsId" value=""/>
      <input type="hidden" id="userRegionID" name="userRegionID" value="${userRegionID}"/>
      <input type="hidden" id="groupseedid" name="groupseedid" value=""/>
      <input type="hidden" id="beginTime" name="beginTime" />
      <input type="hidden" id="endTime" name="endTime" />
        <div class="yuqing_list">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <#list pagination.list as eventnews>
              <tr>
                <td valign="top"><input type="checkbox" name="chk_list" id="chk_list" value="${eventnews.id}" /></td>
                <td colspan="2">
                	<h1 class="color_hongse_n" >
                		<a href="javascript:void(0);" onclick="loadEventnews('${eventnews.id}')" title="${eventnews.title}">
                			<#if eventnews.title?length lt 30>   
								${eventnews.title}
							<#else> 
							     ${eventnews.title[0..29]}... 
							</#if>
                		</a>
                        <span class="eventProperty" eventid="${eventnews.eventid}"></span>
            		</h1>
                  <p>${eventnews.summary}</p>
                  <div class="laiyuan color_huise">
                    <div class="on_right color_hongse_n">
                    <span>
                        <select name="operation_${eventnews.id}" id="operation_${eventnews.id}" onchange="addOperation('${eventnews.id}')">
							<option value="0">操作</option>
							<option value="1">加入预警</option>
							<option value="2">加入专题</option>
							<option value="3">加入收藏</option>
							<option value="4">加入简报</option>
				  		</select>
                    </span> 
                    </div>
                    <em>来源:${eventnews.website}</em>
                    <em>发布时间:${eventnews.publishdate?string("yyyy-MM-dd HH:mm:ss")}</em>
                    <em>相似信息个数:${eventnews.groupcount}</em>
                      <em>倾向性:
                    	<#if eventnews.polarity==1> 
                    		正面
                    	<#elseif eventnews.polarity==-1>
                    		负面
                    	<#else>
                    		争议
                    	</#if>
                    </em>
                  </div>
                </td>
              </tr>
          </#list>
          </table>
        <!--分页-出开始--->
            <#if pagination.totalCount gt 0 >
            <div class="dataTables_wrapper no-footer">
              <div class="dataTables_paginate paging_simple_numbers" id="example_paginate">
              <a <#if pagination.pageNo==1 > class="paginate_button previous disabled" <#else> class="paginate_button previous" </#if> 
              aria-controls="example" data-dt-idx="0" tabindex="0" id="example_previous"
              href="javascript:" onclick="setPageNo(1);" >首页</a>
              <span>
              	<#if pagination.totalPage gt 5 >
					<#if pagination.totalPage-pagination.pageNo gt 2 >
						<#if pagination.pageNo gt 2 >
							<#list pagination.pageNo-2..pagination.pageNo+2 as i>
								<a  aria-controls="example" data-dt-idx="1" tabindex="0"
		             			<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
		             	  		href="javascript:" onclick="setPageNo(${i});">${i}</a>
							</#list>
						<#else>
							<#list 1..5 as i>
			             		<a  aria-controls="example" data-dt-idx="1" tabindex="0"
			             		<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
			             	  	href="javascript:" onclick="setPageNo(${i});">${i}</a>
  							</#list>
						</#if>
					<#else>
						<#list pagination.totalPage-4..pagination.totalPage as i>
							<a  aria-controls="example" data-dt-idx="1" tabindex="0"
		             		<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
		             	  	href="javascript:" onclick="setPageNo(${i});">${i}</a>
						</#list>
					</#if>
				<#else>
					<#list 1..pagination.totalPage as i>
	             		<a  aria-controls="example" data-dt-idx="1" tabindex="0"
	             		<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
	             	  	href="javascript:" onclick="setPageNo(${i});">${i}</a>
  					</#list>
				</#if>
              </span>
              <a <#if pagination.pageNo==pagination.totalPage > class="paginate_button next disabled" <#else> class="paginate_button next" </#if> 
              aria-controls="example" data-dt-idx="7" tabindex="0" id="example_next"
               href="javascript:" onclick="setPageNo(${pagination.totalPage});" >尾页</a>
               <!--显示总页数-->
           		  共<a   class="paginate_buttonzhyz"  
              	aria-controls="example" data-dt-idx="1" tabindex="0">${pagination.totalCount}</a>条
              </div>
            </div>
            </#if>
        <!--分页---结束---> 
      </div>
      </form>
      <div class="HackBox"></div>

<#macro pagination pagination formName>
 <#if pagination.totalCount gt 0 >
            <div class="dataTables_wrapper no-footer">
              <div class="dataTables_paginate paging_simple_numbers" id="example_paginate">
              <a <#if pagination.pageNo==1 > class="paginate_button previous disabled" <#else> class="paginate_button previous" </#if> 
              aria-controls="example" data-dt-idx="0" tabindex="0" id="example_previous"
              href="javascript:" onclick="selPage(1);" >首页</a>
              <span>
              	<#if pagination.totalPage gt 5 >
					<#if pagination.totalPage-pagination.pageNo gt 2 >
						<#if pagination.pageNo gt 2 >
							<#list pagination.pageNo-2..pagination.pageNo+2 as i>
								<a  aria-controls="example" data-dt-idx="1" tabindex="0"
		             			<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
		             	  		href="javascript:" onclick="selPage(${i});">${i}</a>
							</#list>
						<#else>
							<#list 1..5 as i>
			             		<a  aria-controls="example" data-dt-idx="1" tabindex="0"
			             		<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
			             	  	href="javascript:" onclick="selPage(${i});">${i}</a>
  							</#list>
						</#if>
					<#else>
						<#list pagination.totalPage-4..pagination.totalPage as i>
							<a  aria-controls="example" data-dt-idx="1" tabindex="0"
		             		<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
		             	  	href="javascript:" onclick="selPage(${i});">${i}</a>
						</#list>
					</#if>
				<#else>
					<#list 1..pagination.totalPage as i>
	             		<a  aria-controls="example" data-dt-idx="1" tabindex="0"
	             		<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
	             	  	href="javascript:" onclick="selPage(${i});">${i}</a>
  					</#list>
				</#if>
              </span>
              <a <#if pagination.pageNo==pagination.totalPage > class="paginate_button next disabled" <#else> class="paginate_button next" </#if> 
              aria-controls="example" data-dt-idx="7" tabindex="0" id="example_next"
               href="javascript:" onclick="selPage(${pagination.totalPage});" >尾页</a>
                <!--显示总页数-->
           		  共<a   class="paginate_buttonzhyz"  
              	aria-controls="example" data-dt-idx="1" tabindex="0">${pagination.totalCount}</a>条
              </div>
            </div>
            </#if>
            <script type="text/javascript">
				function selPage(pageNo){
					$("#pageNo").attr("value",pageNo);
					$(${formName}).submit();
				}
        	</script>
</#macro>
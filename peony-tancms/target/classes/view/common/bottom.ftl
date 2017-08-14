<div class="HackBox"></div>
<div class="DD_png foot_bottom color_bai" id="foot">
  <div class="foot_in">
    <#if (agent.agentId>0) >
     <p>${agent.name}　${agent.record}</p>
     <p> 
     	<#if agent.phone?if_exists >
     		联系电话：${agent.phone}　
     	</#if>
     　	<#if agent.qq?if_exists && agent.qq2?if_exists>
                      在线客服：
	     <#if agent.qq?if_exists>
			<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${agent.qq}&site=qq&menu=yes"><span class="tb2 kef">客服1</span></a>	        
	     </#if>
	     <#if agent.qq2?if_exists>
	        <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${agent.qq2}&site=qq&menu=yes"><span class="tb2 kef">客服2</span></a>	        
	     </#if>
	 </#if>
	 <#if agent.address?if_exists >
     		地址：${agent.address}　
     	</#if>
     </p>
    <#else>
     <p>北京牡丹电子集团有限责任公司　京ICP备14013722号　京公网安备110401000087 </p>
     <p>联系电话:400-650-9297　　在线客服：
	     <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2955314308&site=qq&menu=yes"><span class="tb2 kef">客服1</span></a>
	     <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=670892279&site=qq&menu=yes"><span class="tb2 kef">客服2</span></a>	        	        
     </p>
    </#if>
  </div>
</div>
<script src="${request.getContextPath()}/js/foot.js" type="text/javascript"></script> 
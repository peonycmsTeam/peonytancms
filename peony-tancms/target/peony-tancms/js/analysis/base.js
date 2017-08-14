define(["jquery","base/jqext"],function(){

if(!window.pin) pin = {};

(function(P,$){
	var doc = document,
		ux = P.ux = {},
		ax = P.ax = {},
		mod= P.mod = {},
		Req = P.request = {};
	
	/**
	 * @class pin.util
	 * 实用工具包
	 */
	var Util = P.util = {
		/**
		 *@property ie
		 *当前浏览器是否为ie
		 *@type Boolean
		 * */
		ie : $.browser.msie ,
		/**
		 *@property ie6
		 *当前浏览器是否为ie6
		 *@type Boolean
		 * */
		ie6 :$.browser.msie && $.trim($.browser.version) == "6.0" ,
		/**
		 * 判断ie版本
		 * @return {Number}
		 */
		ieVer : function(){
			if(this.ie){
				var ie = (function () {
			        var undef,
			        v = 3,
			        div = document.createElement('div'),
			        all = div.getElementsByTagName('i');
			        while (
				        div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->',
				        all[0]
			    	);
			        return v > 4 ? v : undef;
			    })();
			    this.ieVer = function(){
			    	return ie;
			    }
			    return ie;
			} else{
				return false;
			}
		},
		isPC : function () { 
	       var userAgentInfo = navigator.userAgent; 
	       var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod"); 
	       var flag = true; 
	       for (var v = 0; v < Agents.length; v++) { 
	           if (userAgentInfo.indexOf(Agents[v]) > 0) { flag = false; break; } 
	       }
	       this.isPC = function(){return flag;}
	       return flag; 
        },
		  /**
		 * @class pin.request.AjaxConfig
		 * {@link pin.util#ajax}方法的请求参数
		 */
		 /**
		  * @cfg {String} url 请求目标URL
		  */
		 /**
		  * @cfg {String} method 请求方法 POST/GET
		  */
		 /**
		  * @cfg {String} encoding 发送内容的字符编码，未设置采用默认
		  */
		 /**
		  * @cfg {String} dt dataType，返回内容类据类型，text或json，默认为json，系统根据该类型传递对应类型的数据到回调方法的参数中。
		  */
		 /**
		  * @cfg {String|Object} data 请求时传递的数据，可为字符串，也可为键值对。
		  */
		  /**
		   * @cfg {Boolean} cache 请求时是否应用缓存，默认忽略缓存
		   */
		 /**
		  * @cfg {Object} scope 可指定回调方法调用时的this对象
		  */
		 /**
		  * @cfg {Function} success 请求成功后回调方法
		  * @param {Mixed} data 根据设定的数据类型传递不同的类型数据
		  * @param {XMLHttpRequest} ajax 
		  */
		 /**
		  * @cfg {Function} failure 请求失败后回调方法
		  * @param {String} responseText 根据设定的数据类型传递不同的类型数据
		  * @param {XMLHttpRequest} ajax 
		  */
		/**
		 * @class pin.util
		 */
		/**
		 * 发起一个ajax请求.
		 * @param {pin.request.AjaxConfig} param 请求参数
		 * @return {XMLHttpRequest}
		 */
		ajax:function(cfg){
			var ajax;
			
			if (window.XMLHttpRequest) {
				ajax = new XMLHttpRequest();
			} else {
				if (window.ActiveXObject) {
					try {
						ajax = new ActiveXObject("Msxml2.XMLHTTP");
					} catch (e) {
						try {
							ajax = new ActiveXObject("Microsoft.XMLHTTP");
						} catch (e) { }
						}
					}
			}
			if(ajax){
				var type = cfg.type = cfg.type ? cfg.type.toUpperCase() : 'GET';
				var url = cfg.url,d = cfg.data, c = !cfg.cache,isQ = url.indexOf('?') >= 0,qdata;
				var t = (new Date).getTime();
				if(d||c){
					if(d){
						if(typeof d === 'object'){
							qdata = Util.queryString(d);
						}
						if(type == "GET"){
							if(!isQ){
								url = url + "?" + qdata;
							} else {
								url  = url + "&" + qdata;
							}
						}
					}
					isQ = url.indexOf('?') >= 0;
					if(c){
						if(isQ){
							url += (url.indexOf('?') != url.length - 1 ? "&" : '') +'_=' + t;
						} else {
							url += '?_=' + t;
						}
					}
				}
				cfg.async = cfg.async === undefined ? true : cfg.async;
				ajax.open(type,url,cfg.async);
				if (cfg.type === 'POST')
					ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded; charset='+(cfg.encoding?cfg.encoding:''));
				ajax.onreadystatechange = function(){
					if (ajax.readyState === 4) {
						var ok = (ajax.status === 200);
						if(ok && cfg.success){
							try{
								var data = (!cfg.dataType || cfg.dataType == "json") ? eval("("+ajax.responseText+")") : ajax.responseText;
							}catch(e){
								if(__debug)
									console.error('数据格式有误:',ajax.responseText)
							}
							cfg.success.call(this,data,ajax);
							if(cfg.ajaxHistory) location.hash = t;
						} else {
							if(cfg.failure){
								cfg.failure.call(this,data,ajax);
							}
						}
					}
				}
				ajax.send(type==="POST" ? qdata : null);
			}
			return ajax;
		},
		/**
		 * 返回对象查询字符串表示形式.
		 * <pre><code>
		 * <t> var obj = {string:"132",int:20,object:{sex:'Male'},array:[1,2]};
		 * <t>
		 * <t> //显示 string=123&int=20&object[sex]=Male&array[0]=1&array[1]=2;
		 * <t> alert(queryString(obj));
		 * </code></pre>
		 * @param {Object} obj
		 * 当前对象
		 * @param {String} [_key]
		 * 当前对象的key
		 * @return {String} 对象的查询字符串表示形式
		 */
		queryString : function(obj,_key,simForm){
			simForm = simForm == undefined ? true: simForm
			var str=[],isarray = simForm && Object.prototype.toString.call(obj) == '[object Array]';
			if (isarray && obj.length == 0) return ""
			if(typeof obj == "object"){
				var keys = [];
				if( isarray ){
					for(var i=0,len=obj.length;i<len;i++){
						keys.push(i);
					}
				} else {
					keys = this.keys(obj);
				}
				for(var i=0,len=keys.length;i<len;i++){
					var key = keys[i];
					var value = obj[key]
						,type = typeof value;
					if(type == "object"){
						str.push(this.queryString(obj[key],key,simForm));
					} else {
						if(type == "function" && type != 'object')  value = "";
						if((undefined === value)||(null === value) || (isarray && obj.length == 0)){
							continue;
						}
						if(type == "string") value = encodeURIComponent(value);
						if(_key){
							if(!isarray){
								str.push(_key+'['+key+']=' + value);
							} else {
								str.push(_key+ '=' + value);
							}
						} else {
							str.push(key+'=' + value);
						}
					}
				}
			}
			if(_key && str.length == 0){
				return _key + "=";
			} else {
				return str.join('&');
			}
		},
		/**
		 * 分解URL中的参数到JS对象。<br/>
		 * 将JS对象组装为参数字符串方法参见{@link #queryString}<br/>
		 * 获得表单内所有元素提交参数字符串表示方法参见{@link #formQuery}
		 * @param {String} url
		 * @return {Object} 永远不会为空
		 */
	    dequery : function(url,merger){
	        var param = {};
	        url = url.substr(url.indexOf('?')+1);
	        if(url){
	            url = url.split('&');
	            for(var i=0,len=url.length;i<len;i++){
	            	if( url[i].indexOf('=') == -1 ){
	            		if(url[i] != '')  param[url[i]] = "";
	            		continue;
	            	}
	                var arr = url[i].split('=');
	                if(merger && param[arr[0]]){
	                	param[arr[0]] = param[arr[0]] + "," + decodeURIComponent(arr[1]);
	                } else {
	                	param[arr[0]] = decodeURIComponent(arr[1]);
	                }
	            }
	        }
	        return param;
	    },   
	    /**
	     * 获得一个表单所有表单元素的数据,并返回表单的查询字符串表示。
	     * <br/>
	     <code>
	       &lt;form id=&quot;f&quot;&gt;
	         &lt;input type=&quot;text&quot; name=&quot;username&quot; value=&quot;rock&quot;/&gt;
	         &lt;input type=&quot;text&quot; name=&quot;password&quot; value=&quot;123&quot;/&gt;
	       &lt;/form&gt;
	       &lt;script&gt;
	         //&gt;: username=rock&amp;password=123
	         alert(formQuery('f'));
	       &lt;/script&gt;
	       </code>
	     * @param {FormElement|String|jQuery} f form或form的id
	     * @return {String} 所有表单元素的查询字符串表示
	     */
	    formQuery: function(f,isStr) {
	        var formData = isStr?"":{}, elem = "", _f = f,f = $(f)[0], qid;
	        var elements = f.elements || _f;
	        var length = elements.length;
	        for (var s = 0; s < length; s++) {
	            elem = elements[s];
	            if (elem.tagName === 'INPUT') {
	                if (elem.type === 'radio' || elem.type === 'checkbox') {
	                    if (!elem.checked) {
	                        continue;
	                    }
	                }
	            }
	            
	            qid = elem.name || $.trim($(elem).attr("name")) || elem.id;
	            if(isStr){
		            if(qid){
			            if (formData != "") {
			                formData += "&";
			            }
			            formData += encodeURIComponent(qid) + "=" + encodeURIComponent($(elem).val());
		            }
	        	} else {
	        		formData[qid]=$(elem).val()
	        	}
	        }
	        return formData;
	    },
		/**
		 * 框架中创建、继承类使用的方法
		 * 使用该方法新建的类必须重写init方法完成类的初始工作
		 * <br />创建:<pre><code>
		 * new pin.util.create({init:function(){alert()}})();
		 * </pre></code>
		 * 继承:<pre><code>
		 * var Base = pin.util.create({name:"base",init:function(){}});
		 * var NewClass = pin.util.create(Base,{ show : function(){ alert(this.name);	} });
		 * new NewClass().show();
		 * </pre></code>
		 * @return {Class} 
		 * 返回新建的类
		 * */
		create : function(){
				var baseFun = function(){
					this.init.apply(this,arguments);
				}
				if(arguments.length===0) return baseFun;
					
				var obj, basePro, ags = $.makeArray( arguments );
				
				if( ags.length > 1 ){				
					basePro = ags[0];
					ags.shift();
				} else {
					basePro = function(){};
				}
					
				if(basePro)
					basePro = basePro.prototype;
					
				if (basePro) {
					function Bridge(){};
					Bridge.prototype = basePro;
					baseFun.prototype = obj = new Bridge();
				}
				for(var i=0,len=ags.length;i<len;i++)
					obj = $.extend(obj,ags[i]);
						
				obj.constructor = baseFun;
				return baseFun;
		},
		/**
		 *  计算文本剩余字数，默认最大长度为微博最大长度。
		 *  如果已超出最大限制字数，返回负值。
		 * @param {String} text
		 * @param {Number} [max] 可选，最大字数（一个字两个字符）
		 * @return {Number} 
		 */
		calInText : function(text, max){
			if(max === undefined)
				max = 140;
			var cLen=0;
			var matcher = text.match(/[^\x00-\xff]/g),
				wlen  = (matcher && matcher.length) || 0;
			return Math.floor((max*2 - text.length - wlen)/2);
		},
		/**
		 * 返回字符串的字节长度
		 * @param {String} text
		 * 原字符串
		 * @return {Number} 
		 * 字符串的字节长度
		 * */
		byteLen : function(text){
			var len = text.length;
			var matcher = text.match(/[^\x00-\xff]/g);
			if(matcher)
				len += matcher.length;
			return len;
		},
		/**
		 * 返回字符串按字节截取的长度
		 * @param {String} str
		 *	原字符串
		 * @param {Number} length
		 *  截取长度
		 * @return {String} 
		 * 截取后的字符串
		 * */
		byteCut : function(str, length) {
		  var wlen = Util.byteLen(str);
		  if(wlen>length){
			  // 所有宽字用&&代替
			  var c = str.replace(/&/g, " ")
						 .replace(/[^\x00-\xff]/g, "&&");
			  // c.slice(0, length)返回截短字符串位
			  str = str.slice(0, c.slice(0, length)
						// 由位宽转为JS char宽
						.replace(/&&/g, " ")
						// 除去截了半个的宽位
						.replace(/&/g, "").length
					);
		  }
		  return str;
		},
		/**
		 * @property uID
		 * 框架ID生成数，用以区分不用的UI节点
		 * @type Number
		 * */
		uID : 0,
		/**
		 * UI停靠方法，可以通过配置posDes方便的设置元素停靠的位置<br />
		 * posDes由两个字母组成 目前支持下面模式b<br />
		 * <pre><code>
		 * <L>   tl tc tr 
		 * <L>   ┌────────┐ 
		 * <L> lt│   ct   │  rt 
		 * <L> lc│   cc   │  rc 
		 * <L> lb│   cb   │　 rb
		 * <L>   └────────┘
		 * <L>    bl bc br 
		 * </pre></code>
		 * 例如 tl：上左对齐，rb:右边下对齐<br />
		 * <br />创建:<pre><code>
		 * pin.util.offsetTo('bc',dom1,dom2);
		 * </pre></code>
		 * @param {String} posDes
		 * 两个字母 用以描述位置
		 * @param {HTMLElement} target
		 * 目标节点
		 * @param {HTMLElement} self
		 * 自身
		 * */
		offsetTo : function(posDes,target,self,_offset){
			var target = $(target),self = $(self),
				split  = 4,
				ts = target.offset(),
				ss = self.offset(),
				offset = $.extend({},_offset||{t:0,l:0}),
				l = 0,t = 0;

			var marginLeft = parseInt(self.css('marginLeft'))||0,
				marginTop = parseInt(self.css('marginTop'))||0;
				
			ts.left -=  marginLeft;
			ts.top -= marginTop;

			_offset = _offset || {t:0,l:0};

			if(posDes.charAt(0) == "t" || posDes.charAt(0) == "b"){
				switch(posDes.charAt(1)){
					case "l":	l = ts.left;break;
					case 'c':	l = ts.left + (target.outerWidth() - self.outerWidth())/2;break;
					case 'r':	l = ts.left + target.outerWidth() - self.outerWidth(); offset.l -= _offset.l*2; break;
				}
				if(posDes.charAt(0) == "t"){
					t = ts.top - self.outerHeight() - split;
					offset.t -= _offset.t*2;
				} else {
					t = ts.top + target.outerHeight() + split ;
				}
			} else {
				switch(posDes.charAt(1)){
					case 't': t = ts.top;break;
					case 'c': t = ts.top + (target.outerHeight() - self.outerHeight())/2; break;
					case 'b': t = ts.top + target.outerHeight() - self.outerHeight();   offset.t -= _offset.t*2; break;
				}
				switch(posDes.charAt(0)){
					case 'l': l = ts.left - self.outerWidth() - split; offset.l -= _offset.l*2;break;
					case 'c': l = ts.left - (self.outerWidth() - target.outerWidth())/2;break;
					case 'r': l = ts.left + target.outerWidth() + split ; break;
				}
				if(posDes.length==3){
					//"ctl"
					switch(posDes.charAt(2)){
						case 'l': l = ts.left;break;
						case 'r': l = ts.left + target.outerWidth() - self.outerWidth() ;break;
					}
				}
			}
			self.css({ top: t + offset.t , left : l + offset.l});
		},
	   /**
		 * UI遮罩显示隐藏方法<br />
		 * posDes由两个字母组成 第一个字母为：t,b,c 第二个字母为：l,c,r
		 * <pre><code>
		 * pin.util.silt('bc',dom1,dom2);
		 * </pre></code>
		 * @param {String} posDes
		 * 两个字母 用以描述位置
		 * @param {HTMLElement} target
		 * 目标节点
		 * @param {HTMLElement} self
		 * 自身
		 * @param {Boolean} viewState
		 * 显示还是隐藏
		 * @param {Function} [callback]
		 * 回调函数
		 * */
		silt : function(posDes,target,self,viewState,callback){
			var target = $(target),self = $(self),
				split = 4,
				animateObj = {},
				warp;
			
			if( self.data('pin_animate_lock') ) return;
			
			warp = $('<div></div>').appendTo('body');
			viewState && self.cssDisplay(1);
			
			warp.append(self)
				.css({
					position: "absolute",
					width : self.outerWidth(),
					overflow : 'hidden',
					'z-index':self.css('z-index')
				});
				
			var t = self.css('position');
			var f = posDes.charAt(0) 
			self.addClass('pos-ststic').data('pin_animate_lock',1);
			
			if(viewState){
				if (f == "c"){
					warp.css({height:self.outerHeight(),width:0});
					Util.offsetTo(posDes,target,warp);
					animateObj.width = self.outerWidth();
					animateObj.left = target.offset().left +  target.outerWidth() - self.outerWidth();
				} else{
					warp.css({height:0});
					Util.offsetTo(posDes,target,warp);
					animateObj.height = self.outerHeight();
					f == "t" && ( animateObj.top = target.offset().top - self.height() - split );
				}
				
			} else {
				if (f == "c"){
					warp.css({height:self.outerHeight(),width:self.outerWidth()});
					Util.offsetTo(posDes,target,warp);
					animateObj.width = 0;
					animateObj.left = target.offset().left +  target.outerWidth();
				} else{
					warp.css({height : self.outerHeight()});
					Util.offsetTo(posDes,target,warp);
					animateObj.height = 0;
					f == "t" && ( animateObj.top = target.offset().top - split );
				}
			}
			warp.animate(animateObj,function(){
				$(self).appendTo('body')
					.css('position',t)
					.removeClass('pos-ststic')
					.removeData('pin_animate_lock');
				viewState ?	Util.offsetTo(posDes,target,self) : self.cssDisplay(0);
				warp.remove();
				callback && callback();
			  });
			
		},
		/**
		 * 获取UI节点唯一ID
		 * @return {Number}
		 * */
		getUID : function(){
			return ++this.uID;
		},
		/**
		 * 两个节点是否存在包含关系
		 * @param {HTMLElement} pDom
		 *   目标节点
		 * @param {HTMLElement} tDom
		 *    测试节点
		 * @param {Boolean} [equal]
		 *   当相等的时候也返回真
		 * @return {Boolean} 
		 *   第一个节点是否包含第二个节点
		 * */
		ancestorOf :function(pDom, tDom, equal){
			if(equal && pDom === tDom) return true;
			if(pDom.compareDocumentPosition){
                return !!(pDom.compareDocumentPosition(tDom) & 16);
            }else if(pDom.contains){
                return pDom !== tDom && (pDom.contains ? pDom.contains(tDom) : true);
            }
            while ((tDom = tDom.parentNode))
                if (pDom === tDom) return true;
            return false;
		},
		/**
		 * 光标定位到指定位置，默认是尾部
		 * @param {HTMLElement} inputor
		 *   输入框
		 * @param {Number} [num]
		 *    偏移量
		 * */
		focusEnd : function(inputor, num){
			inputor.focus();
			if(num === undefined)
				num = inputor.value.length;
			if(doc.selection) {
				var cr = inputor.createTextRange();
				cr.collapse();
				cr.moveStart('character', num);
				cr.moveEnd('character', num);
				cr.select();
			}else {
				inputor.selectionStart = inputor.selectionEnd = num;
			}
		},
		selectAll:function(inputor){
			num = inputor.value.length;
			if(doc.selection) {
				var cr = inputor.createTextRange();
				cr.collapse();
				cr.moveStart('character', 0);
				cr.moveEnd('character', num);
				cr.select();
			}else {
				inputor.selectionStart = 0;
				inputor.selectionEnd = num;
			}
		},
		selectNode : function(node){
			if (document.selection) {
		        var range = document.body.createTextRange();
		        range.moveToElementText(node);
		        range.select();
		    } else if (window.getSelection) {
		        var range = document.createRange();
		        range.selectNode(node);
		        window.getSelection().addRange(range);
		    }
		},
		/**
		 * 函数绑定作用域
		 * @param {Function} fn
		 * 需要绑定作用域的函数
		 * @param {Object} scope
		 * 作用域目标对象
		 * @return {Function}
		 * 返回绑定作用域后的函数
		 * */
		bind : function(fn,scope){
			  return function(){
				 return fn.apply(scope,arguments);
			  }
		},
		/**
		 * 将绑定作用域后的函数附加到作用域上，方便第二次获取
		 * @param {String} fnName
		 * 需要绑定作用域的函数名
		 * @param {Object} scope
		 * 作用域目标对象
		 * @return {Function}
		 * 返回绑定作用域后的函数
		 * */
		getBind : function(fnName,scope){
			if(!scope["bind_"+fnName]){
				scope["bind_"+fnName] = Util.bind(scope[fnName],scope);
			}
			return scope["bind_"+fnName];
		},
		/**
		 * 字符串切割函数
		 * @param {String} src
		 * 原字符串
		 * @param {String} splitChar
		 * 分割符
		 * @param {String} eacChar
		 * 转义符
		 * @return {Array}
		 * 返回分割后的字符串数组
		 * */
		split : function(str, splitChar, escChar){
			var c, arr = [], tmp = [];
			if(!escChar)
				escChar = '\\';
		
			for(var i=0,len=str.length;i<len;i++){
				c = str.charAt(i);
				if(c === splitChar){
					arr.push(tmp.join(''));
					tmp.length = 0;
					continue;
				}
				else if(c === escChar && str.charAt(i+1) === splitChar){
					c = splitChar;
					i++;
				}
				tmp[tmp.length] = c;
			}
			if(tmp.length)
				arr.push(tmp.join(''));
			return arr;
		},
		
		/**
		 * jd 字符串转换函数
		 * @param {String} strJd
		 * jd 字符串
		 * 以,分割:标识的键值对
		 * 例如 ：e:like,t:type1 转换后返回一个对象 {e:'like',t:'type1'}
		 * @return {Object}
		 * 返回分割的对象
		 * */
		parseKnV : function(strJd){
			var map = {}, kv, kvs = this.split(strJd||'', ',');
			try {
				for( var i=0,len=kvs.length;i<len;i++){
					if(kvs[i].indexOf(':') === -1){
						map[kvs[i]] = true;
					}else {
						kv = Util.split(kvs[i], ':');
						map[kv[0]] = kv[1];
					}
				}
			}catch(e) { 
				if(__debug) console.trace();
				throw 'jd字符串格式出错。' + strJd; 
			}
			return map;
		},
		fotmatJdString : function(str){
			if(typeof str == "string"){
				return str.replace(/[:,]/gm,function(s){
					return "\\"+s;
				});
			}
			return '';
		},
		/**
		* 上报函数
		* @param {String} url
		* 上报地址
		* @param {Object} param
		* 上报参数
		* @param {Function} callback
		* 回调函数 加载完成以后的回调函数
		* */
		reported : function(url,param,callback){
			var img = new Image();
			url += ( url.indexOf('?') == -1 ?  '?_=' : '&_=' ) + +new Date
			if(param){
				url += "&" + this.queryString(param);
			}
			img.onload = function(){
				callback && callback();
				img = null;
			}
			img.src = url;
		},
		/**
		* html编码,在html环境显示html编码
		* @param {String} str
		* 需转义的字符串
		* @return {String}
		* 返回转义的字符串
		* */
		htmlEncode : function(str){
			var div = document.createElement("div");
			div.appendChild(document.createTextNode(str));
			return div.innerHTML;
		},
		/**
		* html解码，把编码的html文本还原
		* @param {String} str
		* 需还原的字符串
		* @return {String}
		* 返回还原的字符串
		* */
		htmlDecode :function (str) {
			var div = document.createElement("div");
			div.innerHTML = str;
			return div.innerHTML;
		},

		loadImg : function(url,item){
			if(url == item.src) return; 
			item.onload = function(){};
			var val = url; 
			var img = new Image();
			img.onload = function(){
				var rs = img.readyState; 
				if(img.complete || rs === 'loaded' || rs === 'complete'){
					item.src = img.src;
					img = null;
				}
			};
			img.onerror = function(){
				img = null;
			};
		    img.src = val;
		},
	    /**
		* @param {Element} container 放置flash的容器元素
		* @param {Object} info swf的相关信息
		* @param {string|Object} [flashvars] 可选的参数
		* @param {Object} [params] 可选的参数
		*/
		getFlash : function (container, info, flashvars, params) {
	        /* IE使用appendChild添加object标签是没有用的，只能用innerHTML
	           在Chrome中，如果object标签前面有一个元素有background-image样式，则很有可能该object不显示
	           测试代码，保存为html文件，本地Chrome打开，刷新几次会出现该现象*/
	        // 只有flash的话，仅使用embed是可以的
	        //  http://www.w3help.org/zh-cn/causes/HO8001 参见“问题分析”的第4点和“解决方案”
	        // 但是要与flash交互（javascript <-> flash相互调用），IE下就必须用object
	        // XXX: 需要QA关注

	        // 由于默认的交互参数是JSON格式，会有双引号，需要转义掉，以免HTML解析出错
	        if(typeof flashvars == "object"){
	        	flashvars = this.queryString(flashvars);
	        } else {
	        	 flashvars = flashvars && flashvars.replace(/"/g, '&quot;');
	        }
	        var pArrkeys = [];
	        var getFlashStr = '<a href="http://www.adobe.com/go/getflash"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="获得 Adobe Flash Player" /></a>';
	        params = params || {};
	        pArrkeys = Util.keys(params);

	        var html,phtml = "";
	        info.id = info.id || 'yyZone_flash' + ( Math.random() * 1000000 | 0);
	        info.width = info.width || 1;
	        info.height = info.height || 1;
	        if ('classid' in document.createElement('object')) {//ie only
	        	for(var i=0,len=pArrkeys.length;i<len;i++) phtml += '<param name="'+pArrkeys[i]+'" value="'+params[pArrkeys[i]]+'" />';

	            html = '<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" name="' + info.id+ '" ' +
	            (info.id ? 'id="' + info.id + '" ' : '') +
	            'width="' + info.width + '" height="' + info.height + '">' +
	            '<param name="allowScriptAccess" value="'+(info.allowScriptAccess?info.allowScriptAccess:"always")+'" />' +
	            '<param name="quality" value="high" />' +
	            '<param name="wmode" value="'+(info.wmode?info.wmode:"window")+'" />' +
	            '<param name="movie" value="' + info.src + '" />' +
	            (flashvars ? '<param name="flashvars" value="' + flashvars + '" />' : '') + phtml + getFlashStr
	            '</object>';

	        } else {
	            //style="width:1px;height:1px" 是为了保证firefox下正常工作.
	            for(var i=0,len=pArrkeys.length;i<len;i++) phtml += ' '+pArrkeys[i]+'="'+params[pArrkeys[i]]+'"';
	            html = '<embed style="width:' + info.width +'px;height:' + info.height + 'px;" wmode="'+(info.wmode?info.wmode:"window")+'" src="' + info.src + '" quality="high" name="' + info.id + '" ' +
	            (info.id ? 'id="' + info.id + '" ' : '') +
	            (flashvars ? 'flashVars="' + flashvars + '" ' : '') +
	            'width="' + info.width + '" height="' + info.height +
	            '" allowScriptAccess="'+(info.allowScriptAccess?info.allowScriptAccess:"always")+'" ' + phtml +
	            'type="application/x-shockwave-flash"/>';
	        }

	        container.innerHTML = html;
	        return container.firstChild;
	    //应注意, container必须在DOM Tree中， 否则 先更新innerHTML,后进入DOM Tree, 部分IE8可能flash无法正常显示，并工作.
	    },
	    /**
		 * 删除第一个指定的元素
		 * @param {Array} arr
		 *   原数组
		 * @param {Object} obj
		 *    需要删除的元素
		 * */
		arrayRemove : function(arr, obj){
			if( arr[arr.length-1]  == obj)
				arr.pop();
			else arr.splice(Util.arrayIndexOf(arr,obj), 1);
		},
		/**
		 * 获取元素在数组中的下标
		 * @param {Array} arr
		 *   原数组
		 * @param {Object} obj
		 *    需要删除的元素
		 * */
		arrayIndexOf : function(arr, obj){
			for(var i=0,len=arr.length;i<len;i++)
				if(arr[i] === obj)
					return i;
			return -1;
		},
	    keys: function(obj){
	    	var hasOwn = ({}).hasOwnProperty;
	    	var DONT_ENUM = "propertyIsEnumerable,isPrototypeOf,hasOwnProperty,toLocaleString,toString,valueOf,constructor".split(",")
            var result = [];
            for(var key in obj ) if(hasOwn.call(obj,key)){
                result.push(key)
            }
            if(DONT_ENUM && obj){
                for(var i = 0 ;key =DONT_ENUM[i++]; ){
                    if(hasOwn.call(obj,key)){
                        result.push(key);
                    }
                }
            }
            return result;
        },
        setCursor : function(elem, pos, coverLen){
	        pos = pos == null ? elem.value.length : pos;
	        coverLen = coverLen == null ? 0 : coverLen;
	        elem.focus();
	        if(elem.createTextRange){
	            var range = elem.createTextRange();
	            range.move("character", pos);
	            range.moveEnd("character", coverLen);
	            range.select();
	        }else {
	            elem.setSelectionRange(pos, pos + coverLen);
	        }
	    },
	    /**
	     *  替换源字符串中某段为指定向字符串
	     * @param {String} source
	     * @param {String} replacement
	     * @param {Number} fromIndex
	     * @param {Number} toIndex
	     * @return {String} newString
	     */
	    stringReplace : function(source, text, from, to){
	        return source.substring(0, from) + text + source.substring(to);
	    },
        /**
		 * 获得输入框内光标下标
		 * @param {HTMLElement} element
		 * @return {Number} index
		 */
	    getCursorPos : function(elem){
	        var pos = 0;
	        if(!!$.browser.msie){
	            try{
	                elem.focus();
	                var range = null;
	                range = ds.createRange();
	                var tmpRange = range.duplicate();
	                tmpRange.moveToElementText(elem);
	                tmpRange.setEndPoint("EndToEnd", range);
	                elem.selectionStart = tmpRange.text.length - range.text.length;
	                elem.selectionEnd = elem.selectionStart + range.text.length;
	                pos = elem.selectionStart;
	            }catch(e) {};
	        }else{
	            if( elem.selectionStart || elem.selectionStart == '0' )
	                pos = elem.selectionStart;
	        }
	        
	        return pos;
	    },
        /**
         * 超出截断函数，避免出现半截字
         * @param {String} target 需要有截断的文本jq选择器表达式
         * */
        wordCutOff : function (target,childSelector){
        	var csskey = ["width", "paddingTop", "paddingLeft", "paddingRight", "paddingBottom",'fontFamily', 'borderStyle', 'borderWidth', 'fontSize', 'lineHeight','fontWeight','fontStyle'];
			var el = $("<div></div>");
			var target = $(target);
			var demoOne = target.eq(0);
			var css = {opacity: 0, position: "absolute",left:0,top:0};
			$.each(csskey, function(i, k){css[k] = demoOne.css(k);});
			el.css(css).html(1).appendTo("body");
			var maxLength;
			var height = el.height();
			target.each(function(){
				var s = $(this).text();
				var now = maxLength || 1;
				var tf = 1;
				el.html(s.substr(0,now));
				if(el.height()>height){
					tf = -1;
					while(el.height() > height && now != 1){
						now += tf;
						el.html(s.substr(0,now));
					}
				} else if(el.height()==height){
					tf = 1;
					while(el.height() == height && now < s.length){
						now += tf;
						el.html(s.substr(0,now));
					}
					if(el.height() > height) now-=1;
				}
				maxLength = now;
				if(childSelector && $(childSelector,this).length){
					$(childSelector,this).html(s.substr(0,now));
				} else {					
					$(this).html(s.substr(0,now));
				}
			});
			el.remove();
			el = null;
        }  
	};
	/**
	 * @class dw
	 * 本类是所有dw JavaScript交互应用的命名空间根目录。
	 */
	$.extend(P,{
		/**
		 * @property _cls
		 * 框架类存放位置
		 * @type Object
		 */
		_cls:{},
		/**
		 * @property event
		 * 框架全局事件存放位置
		 * @type Object
		 */
		event:{
			'pageStart':[{
				d : function(e){
					P.cfg = P.cfg || {};
					$.extend(P.cfg,e);
					delete pin.cfg.json;			
				},
				once:true
			}]
		},
		/**
		 * 注册一个类
		 * @param {String} name 
		 * 在框架中注册的名称
		 * @param {Function} cls
		 * 类
		 * @param {Boolean} override
		 * 在框架中已存在该名称的类的时候是否覆盖原来的内容
		 * @return {Function}
		 * 返回参数cls
		 */
		reg : function(name, cls, override){
			if(this._cls[name] !== undefined && !override){
				if(__debug) console.trace();
				throw Error('已定义类 ' + name);
			}
			this._cls[name] = cls;
			return cls;
		},
		/**
		 * 使用框架中的类创建一个对象
		 * @param {String} name 
		 * 在框架中注册的名称
		 * @param {Object} cfg 
		 * 创建类时候的参数
		 * @return {Object}
		 * 当指定类存在的时候返回该类的实例，否则返回null
		 */
		use : function(name){
			var cls = this._cls[name];
			if (cls) {
				if(typeof cls === 'object')
					return cls;
				var cfg = arguments[1];
				return new cls(cfg);
			}
			if(__debug){
				console.warn('名称 "' + name + '" 未加载！检查依赖或逻辑错误。');
			}
			return null;
		},
		/**
		 * 获取已经注册的类
		 * @param {String} name
		 * 已注册类的名称
		 * @return {Object}
		 * 返回已注册的类
		 * */
		getCls : function(name){
			var  cls = this._cls[name];
			return cls;
		},
		/**
		 * 返回指定全局配置的值 
		 * @param {String} name 
		 * 配置的名称
		 * @return {Object}
		 * 返回指定配置的值		 
		 */
		getCfg:function(name){
			if(name == "userId"){
				if(P.cfg && P.cfg[name]) {
					return P.cfg && P.cfg[name];
				} else {
					return $.cookie('statLoginUserName');
				}
			}
			return P.cfg && P.cfg[name];
		},
		/**
		 * 注册全局事件
		 * @param {String} name 
		 * 全局事件的名称
		 * @param {Function} callback 
		 * 全局事件回调函数
		 * @param {Boolean} once 
		 * 是否为一次型事件
		  * @param {Boolean} insertFirst 
		 * 是否把事件插入对开始
		 * @return {Object}
		 * 返回dw本身
		 */
		on : function(name,callback,once,insertFirst){
			var event = this.event;
			var ispageStart = +(name == 'pageStart');
			if(!event[name]){
				event[name]= [];
			}
			if(!insertFirst){
				event[name].push({d:callback,once:!!once});
			} else {
				event[name].splice(ispageStart,0,{d:callback,once:!!once});
			}
			return this;
		},
		/**
		 * 触发全局事件
		 * @param {String} eName 
		 * 全局事件的名称
		 * @param {Object} arg 
		 * 传递给回调函数的参数
		 */
		fire : function(eName,arg){
			if(__debug) console.log(eName,arg);
			var eArr = this.event[eName],tmp = {};
			if(eArr){
				for(var i=0 ;i < eArr.length ;i++){
					tmp = $.extend({},arg);
					if(eArr[i].d.call(this,tmp) === false){
						break;
					}
					if(eArr[i].once){
						Util.arrayRemove(eArr,eArr[i]);
						i--;
					}
				}
				if(eArr.length == 0 ) {
					delete this.event[eName]
				}
			} else {
				if(__debug) console.warn('没有注册事件 e:' + eName);
			}
		}
		
	});
	/**
	 * @event pageStart
	 * 页面开始事件
	 * <pre><code>
	 * pin.fire('pageStart',{uuid:'1236'});
	 * </code></pre>
	 * @param {Object} cfg 
	 * 当前页面的配置,该配置会传递给pin.cfg;
	 */
	
	var regTpl = /\{(\.?[\w_$]+)(\.[\w_$]+)?(\.?[\w_$]+)?\}/g,
		ifRegTpl = /\[\?(!?)(\.[\w_$]+)(\.?[\w_$]+)?(\.?[\w_$]+)?\?([\S\s]*?)\?\]/g;
		
	/**
	 * @class pin.Tpl 
	 * 模版工具类
	 */
	var Tpl = P.Tpl = {
		/**
		 * @property  tpls
		 * 模版数组
		 * @type {Array}
		 * */
		/**
		 * 模版解析方法，把对象的值解析到模版中。生成html代码
		 * @param {String} tpl
		 * 模版名称
		 * @param {Object} map
		 * 需要解析的对象
		 * @return {Strting}
		 * 返回解析后的html代码
		 */
		parse : function(tpl,map){
			!map && ( map = {} );
			if(tpl.charAt(0) !== '<'){
				var t = Tpl.tpls[tpl];
				t && (tpl = t);
			}
			tpl = tpl.replace(ifRegTpl,function(s,s0,s1,s2,s3,s4){
				var v = map[s1.substr(1)];
				if(s2){
					v = s2.charAt(0) =='.' ? v[s2.substr(1)] :v;
				}

				if(s3){
					v = s3.charAt(0) =='.' ? v[s3.substr(1)] :v;
				}
				if(s0 === '!'){
					return !v?s4:"";
				}
				return v?s4:'';
			});
			
			return tpl.replace(regTpl,function(s,s0,s1,s2){
				var v = s0.charAt(0) == '.' ? map[s0.substr(1)] : Tpl.tpls[s0];
				if(v == void 0) return '';
				
				if(s1){
					v = s1.charAt(0) =='.' ? v[s1.substr(1)] :v;
				}

				if(s2){
					v = s2.charAt(0) =='.' ? v[s2.substr(1)] :v;
				}
				
				if(v && v.toString().charAt(0) === '<')
					return Tpl.parse(v, map);
				
				if(Tpl.tpls[v])
					return Tpl.parse(Tpl.tpls[v], map);
				
				v = v === void 0 ? '' : v;

				return v;
				
			});
		},
		tpls : {}
		
	}
	//低版本浏览器没有console对象的时候使用空对象代替
/**
 * @class console
 * 系统控制台,如果存在firebug,利用firebug输出调试信息,否则忽略.
 * 在firbug中可直接进行对某个对象进行监视,
 * 无console时就为空调用,可重写自定输出.
 * @singleton
 */
	if(!window.console){
		window.console = {
		/**
		   * 在控制台输出一条消息，包含一个指向代码调用位置的超链接
		   *@param {arguments} arg  类似C语言中printf语法
		   *@method debug
		*/
		debug : $.noop,
		/**
		   * 在控制台输出当前堆栈信息
		   *@method trace
		*/
		trace : $.noop,
		/**
		   * %o表示参数为一个对象
		   * <pre><code>
		   * console.log('This an string "%s",this is an object , link %o','a string', CC);
		   * </pre></code>
		   *@param {arguments} arg
		   *@method log
		*/
		log : $.noop,
		/**
		 * 在控制台输出提示信息带警告图标
		 * @param {arguments} arg  类似C语言中printf语法
		 * @method warn
		 */
		warn : $.noop,
		/**
		 *  在控制台输出提示信息带错误图标 并带有堆栈信息
		 * @param {arguments} arg  类似C语言中printf语法
		 * @method error
		 */
		error : $.noop,
		/**
		 * 以分组显示信息
		 * @param {arguments} arg  类似C语言中printf语法
		 * @method group
		 */
		group:$.noop,
		/**
		 *  关闭近打开的分组
		 * @method groupEnd
		 */
		groupEnd:$.noop
		}
	}

	pin.log = function(str){
		if(__debug){
			if(typeof console.log == "function"){ 
				console.log.apply(window.console,arguments);
			} else {
				console.log(str);
			}
		}
	};
	
	/**
	 * @class pin.request
	 * 多玩数据层api
	 * */
	 $.extend(Req,{
		/**
		 * 对{@link pin.request.Ajaxconfig}进行封装传进工具类的ajax函数发起异步请求<br />
		 * 并且对success进行封装，对返回结果用{@link pin.request.RequestData}实列化传回原来的success函数进行处理。
		 * @param {pin.request.Ajaxconfig} cfg
		 * ajax配置信息处理
		 * */
		direct : function(cfg){
			var h = cfg.success;
			cfg.success = function(d,ajax){
				var data = new Req.RequestData(d,ajax,cfg.data);
				h.call(ajax,data);
			}
			return Util.ajax(cfg);
		},
		/**
		 * 普通的ajax发起函数
		 * @param {String} url
		 * 请求的url地址
		 * @param {Object} data
		 * ajax请求的数据
		 * @param {FunCtion} fn
		 * 当ajax有返回时的回调函数
		 * @param {Object} cfg
		 * 额外的配置参数
		 * */
		q : function(url,data,fn,cfg){
			if(!cfg) cfg = {};
			cfg.url = url;
			if(cfg.data){
				$.extend(cfg.data,data)
			} else cfg.data = data;
			cfg.success = fn;
			return this.direct(cfg);

		},
		/**
		 * 设置当前用户的昵称
		 * @param {String} api
		 * api命令名 -> '/api/' + api  异步请求地址
		 * @param {Object} data
		 * 异步请求的数据
		 * @param {FunCtion} fn
		 * 当ajax有返回时的回调函数
		 * @param {String} cfg
		 * 额外的配置参数
		 * */
		api:function(api,data,fn,cfg){
			var _fn = function(r){
				var returnValue = fn(r);
				if(r.isOk()){
					if(cfg && cfg.annex) r.annex = cfg.annex;
					P.fire(api,r);
				}
				return returnValue;
			}
			return this.postReq('/api/'+ api + ((cfg && cfg.q) || '') ,data,_fn,cfg);
		},
		/**
		 * 以post方式发送异步请求
		 * @param {String} url
		 * 异步请求地址
		 * @param {Object} data
		 * 异步请求的数据
		 * @param {FunCtion} fn
		 * 当ajax有返回时的回调函数
		 * @param {String} cfg
		 * 额外的配置参数
		 * */
		postReq : function(url,data,fn,cfg){
			if(!cfg) cfg = {};
			cfg.type = "POST";
			return this.q(url,data,fn,cfg);
		},
		ERRORMSG : {}
	});

	/**
	 * @class pin.request.RequestData
	 * 异步请求返回信息封装类，
	 * 对异步请求的返回结果进行封装，提供统一的处理过程
	 * @constructor 
	 * 初始化异步请求对象
	 * @param {Object} data
	 * 异步请求返回的数据
	 * @param {XMLHttpRequest} ajax
	 * ajax生成的xhr对象
	 * @param {Object} reqData
	 * 异步请求发送的数据
	 * */
	 /**
	  * @cfg {Object} data
	  * 异步请求返回的数据
	  * */
	 /**
	  * @cfg {Object} reqData
	  * 异步请求发送的数据
	  * */
	 /**
	  * @cfg {XMLHttpRequest} xhr
	  * ajax生成的xhr对象
	  * */
	 /**
	  * @property  data
	  * 异步请求返回的数据
	  * @type {Object}
	  * */
	 /**
	  * @property reqData
	  * 异步请求发送的数据
	  * @type {Object}
	  * */
	 /**
	  * @property  xhr
	  * ajax生成的xhr对象
	  * @type {XMLHttpRequest} 
	  * */
	Req.RequestData = function(data,ajax,reqData){
		this.data = data;
		this.reqData = reqData;
		this.xhr = ajax;
	}
	Req.RequestData.prototype = {
		/**
		 * 获取异步请求返回数据
		 * @return {Object}
		 * 异步请求返回数据 结果是json对象或者是字符串
		 * */
		getData:function(){
			return this.data.data != undefined ?  this.data.data : this.data;
		},
		/**
		 * 获取错误描述
		 * @return {String}
		 * 根据错误码重错误码表中获取错误吗描述
		 * */
		getMsg:function(defaultValue){
			var code = this.getCode();
			return code && P.request.ERRORMSG[code] || defaultValue || "请求不给力哦！";
		},
		/**
		 * 异步请求发送的数据
		 * @return {String}
		 * 返回步请求发送的数据
		 * */
		getReqData:function(){
			return this.reqData;
		},
		/**
		 * 获取错误码
		 * @return {String}
		 * 返回错误码
		 * */
		getCode:function(){
			return  this.data && this.data.code + "";
		},
		/**
		 * 在服务端数据执行是否成功
		 * @return {Boolean}
		 * 返回是否成功
		 * */
		isOk:function(){
			return this.getCode().toLowerCase() == "ok" || this.getCode() == "200";
		}
	};

})(pin,jQuery);

},"base/base.js");
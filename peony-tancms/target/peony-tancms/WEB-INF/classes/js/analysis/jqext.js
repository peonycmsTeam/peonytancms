define('jquery',function(){

(function($){
	/**
	 * @class jQuery
	 * jQuery扩展
	 */
	var _val = $.fn.val;
	load.loadjqExt = function(){
		var fn = {
			/**
			 * 显示隐藏控制函数
			 * <pre><code>
			 * $('#box').cssDisplay(true)
			 * </pre></code>
			 * 该函数是为节点增加删除 css hidden类
			 * @param {Boolean} show 元素是否显示
			 * @return {jQueryObject}
			 * jQuery对象本身
			 */ 
			cssDisplay : function(show,defaultCss){
				defaultCss = defaultCss || 'hidden';
				if(!show){
					$(this).addClass(defaultCss);
				} else {
					$(this).removeClass(defaultCss);
				}
				return this;
			},
			/**
			 * 为输入框绑定焦点默认文本方法。
			 * @param {String} css 状态改变时切换的css值
			 * @param {String} [text] 默认文本
			 * @param {String} [cssNode] 额外节点
			 * @param {Number|Boolean} [state] 工作模式
			 * 0|false 默认情况 失去焦点移除css类 获取焦点增加css类
			 * 1|true  获取焦点移除css类 失去焦点增加css类		 
			 * 3 获取焦点和额外节点一起增加css类，失去焦点和额外节点一起删除css类。失去焦点且文本框的值和默认不一样额外节点不删除css类。
			 * @return {jQueryObject}
			 * jQuery对象本身
			 */ 
			focusText : function(_css,_text,_cssNode,_state,_fns,_blurChk){
				$(this).each(function(){
					var __text;
					if($(this)[0].type=="text" && !_text){
						__text = $(this).attr("def") || $(this).attr("value");
					}
					var text = __text || _text || $(this).val(),
						cssNode = _cssNode || this,
						state = +( _state || 0 ),
						fns = _fns || [$.noop,$.noop],
						blurChk = _blurChk || $.noop,
						css = _css || '';

					$(this).val(text).data("_val",text);
					text = text.replace(/\r/g,'');

					$(this).blur(function(e){
						if(blurChk(e) == false) return;
						var val = this.value.replace(/\r/g,'');
						if( val== "" || val == text ){
							this.value = text;
							switch(state){
								case 1 :$(cssNode).addClass(css);break;
								case 0 :$(cssNode).removeClass(css);break;
								case 3 :$.merge($(cssNode),$(this)).removeClass(css);break;
							}
							fns[1]();
						} else {
							switch(state){
								case 3 :$(this).removeClass(css);$(cssNode).addClass(css);break;
							}
						}
						$(this).trigger("keydown");
					})
					.focus(function(){
						var val = this.value.replace(/\r/g,'');
						if(val == text){
							this.value = "";
						}
						switch(state){
							case 1 :$(cssNode).removeClass(css);break;
							case 0 :$(cssNode).addClass(css);break;
							case 3 :$.merge($(cssNode),$(this)).addClass(css);break;
						}
						if(val == text){
							fns[0]();
						}
						$(this).trigger("keydown");
					});
				})
				
				return this;
			},
			val : function(){
				if(typeof $(this).data("_valfn") == "function"){
					if($(this).length>1){
						var args = arguments;
						$(this).each(function(){
							$(this).val.apply($(this),args);
						});
						return $(this);
					}
					else {
						return $(this).data("_valfn")(arguments);
					}
				} else {
					if(arguments.length!=0){
						return _val.apply(this,arguments);
					} else {
						var val = _val.apply(this,arguments);
						if(val == $(this).data("_val")){
							return "";
						} else {
							return val;
						}
					}
				}
			},
			placeholder : function(){
				var allElem = $(this).find("[placeholder],[_placeholder]");
				return allElem.each(function(){
					var el = $(this);
					if(!el.data("bindFocusText")){
						var text = el.attr("placeholder")
						el.focusText("focus",text,el.parent(),0);
						el.data("bindFocusText",true);
	                    el.attr("_placeholder",text);
					} else {
						el.trigger("blur");
					}
				});
			},
			/**
	         * 检查是否含有某个样式,如果有,添加或删除该样式.
	         * @param {String} css 样式名称
	         * @param {Boolean} addOrRemove true 时添加样式，false时移除该样式
	         * @return {jQuery} this
	         */
			checkClass : function(cs, b){
			    if(cs){
				    this.each(function(){
				        var jq = $(this);
	        			var hc = jq.hasClass(cs);
	        			if(b){
	        				if(!hc)
	        				jq.addClass(cs);
	        			}else if(hc){
	        				jq.removeClass(cs);
	        			}			        
				    });
			    }
	    		return this;
			},
			/**
			 * 修改节点中数字的值
			 * @param {String} sel 目标节点的选择器，目标为当前的话该值为''
			 * @param {NumBer} cou 增量值可以为负数
			 * @return {jQueryObject}
			 * jQuery对象本身
			 */
			setDomCount : function(sel,cou){
				$(this).each(function(){
					var count = sel ? $(this).find(sel).text() : $(this).text() ;
					if(count){
						count = +count;
						count += cou;
						sel ? $(this).find(sel).text(count) : $(this).text(count) ;
					}
				})
				return this;
			},
			outerHTML : function(s) {
			  if(s){
			  	var h = $(s);
			  	this.before(h).remove();
			  	return h;
			  } else {
			  	return jQuery("<p></p>").append(this.eq(0).clone()).html()
			  }
			}
		};
		for(var key in fn){
			jQuery.fn[key]=fn[key];
		}
	}
	load.loadjqExt();
	
	/**
	 * jQuery 高亮查找插件，完全基于文本结点的DOM操作，对原有非文本结点不造成任何影响。
	 * @param {Array} keywords 高亮文本数组
	 * @param {String} [style] 应用于高亮文本的样式类
	 * 用法:
	 <pre><code>
	   高亮:
	   $('#ss').highlight(['keyword', ... ]);
	   清除:
	   $('#ss').clearHighlight();
	   
	   或者传递自定义的样式类
	   $('#ss').highlight(['keyword', ... ], 'cssClass');
	   清除:
	   $('#ss').clearhighlight('cssClass');   
	 </code></pre>
	 请确保每次高亮前清除先前已高亮内容。
	 可选配置信息：$.fn.highlight.cls
	 * @method highlight
	*/
	 
	(function(){

	// private
	var IGNORE,S,ESC,LT,GT, inited;

	// private
	function init(){
	    IGNORE = /INPUT|IMG|SCRIPT|STYLE|HEAD|MAP|AREA|TEXTAREA|SELECT|META|OBJECT|IFRAME/i;
	    S      = /^\s+$/;
	    ESC    = /(\.|\\|\/|\*|\?|\+|\[|\(|\)|\]|\{|\}|\^|\$|\|)/g;
	    LT     = /</g;
	    GT     = />/g;
	    inited = true;
	}

	// entry
	function entry(keys, cls){
	    if (!keys) {
	        return;
	    }
	    
	    if(!inited)
	      init();
	  
	    if(typeof keys === 'string')
	      keys = $.trim(keys).split(S);

	    // normalize
	    var arr = [];
	    for(var i=0,len=keys.length;i<len;i++){
	       if(keys[i] && !S.test(keys[i])) {
	          arr[arr.length] = keys[i].replace(LT, '&lt;')
	                                   .replace(GT, '&gt;')
	                                   .replace(ESC, '\\$1');
	       }
	    }
	    var reg     = new RegExp('(' + arr.join('|') + ')', 'gi'),
	        placing = '<span class="'+(cls||entry.cls)+'">$1</span>',
	        div     = document.createElement('DIV');
	    this.each(function(){
	      highlightEl(this, reg, placing, div);
	    });

		return this;
	}

	// public
	$.fn.highlight = entry;
	/**
	 * 清除高亮
	 * @see {@link #highlight}
	 * @method clearHighlight
	 */
	$.fn.clearHighlight = function(cls) {
	  if(!inited)
	    init();
	  var cls = cls||entry.cls;
	  this.each(function(){
	    clearElhighlight(this, cls);
	  });
	};


	// private
	function replaceTextNode(textNode, reg, placing, div) {
	  var data = textNode.data;
	  if(!S.test(data)){
	     data = data.replace(LT, '&lt;').replace(GT, '&gt;');
	     if(reg.test(data)){
	        if(!div)
	          div = document.createElement('DIV');
	        // html escape
	        div.innerHTML = data.replace(reg, placing);
	        // copy nodes
	        var chs = div.childNodes,
	            arr = [],
	            fr = document.createDocumentFragment();
	        
	        // copy to array
	        for(var i=0,len=chs.length;i<len;i++)
	          arr[i] = chs[i];
	        
	        for(i=0;i<len;i++)
	          fr.appendChild(arr[i]);
	        
	        textNode.parentNode.replaceChild(fr, textNode);
	     }
	  }
	}

	// private
	function highlightEl(el, reg, placing, div){
	    var chs = el.childNodes, 
	        arr = [], i, len, nd;
	      
	      // copy to array
	      for(i=0,len=chs.length;i<len;i++){
	        if(!IGNORE.test( chs[i].tagName ))
	          arr.push(chs[i]);
	      }
	      
	      for(i=0,len=arr.length;i<len;i++){
	        nd = arr[i];
	        // textnode
	        if(nd.nodeType === 3){
	          try { 
	            replaceTextNode(nd, reg, placing, div);
	          }catch(e){}
	        }else arguments.callee(nd, reg, placing, div);
	      }
	}


	// private
	function clearElhighlight(el, cls) {
	  var chs = el.childNodes, 
	      arr = [], i, len, nd, t;
	      
	  // copy to array
	  for(i=0,len=chs.length;i<len;i++){
	    if(!IGNORE.test( chs[i].tagName ))
	    arr.push(chs[i]);
	  }

	  for(i=0,len=arr.length;i<len;i++){
	    nd = arr[i];
	    t = nd.nodeType;
	    // textnode
	    if(t === 3)
	      continue;
	    // span
	    if(t === 1 && nd.tagName === 'SPAN' && nd.className === cls){
	      // merge text nodes
	      var textNode = nd.childNodes[0], 
	          p        = nd.parentNode,
	          pre      = nd.previousSibling,
	          nxt      = nd.nextSibling;
	      
	      if(pre && pre.nodeType === 3){
	        p.removeChild(pre);
	        textNode.data = pre.data + textNode.data;
	      }
	      
	      if(nxt && nxt.nodeType === 3){
	        p.removeChild(nxt);
	        textNode.data = textNode.data + nxt.data;
	      }

	      p.replaceChild(textNode, nd);
	    }else arguments.callee(nd, cls);
	  }
	}

	entry.cls = 'js-search-txt';

	})();


	jQuery.cookie = function(name, value, options) {
	    if (typeof value != 'undefined') { 
	        options = options || {};
	        if (value === null) {
	            value = '';
	            options.expires = -1;
	        }
	        var expires = '';
	        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
	            var date;
	            if (typeof options.expires == 'number') {
	                date = new Date();
	                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
	            } else {
	                date = options.expires;
	            }
	            expires = '; expires=' + date.toUTCString(); 
	        }
	        var path = options.path ? '; path=' + (options.path) : '';
	        var domain = options.domain ? '; domain=' + (options.domain) : '';
	        var secure = options.secure ? '; secure' : '';
	        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
	    } else { 
	        var cookieValue = null;
	        if (document.cookie && document.cookie != '') {
	            var cookies = document.cookie.split(';');
	            for (var i = 0; i < cookies.length; i++) {
	                var cookie = jQuery.trim(cookies[i]);
	                if (cookie.substring(0, name.length + 1) == (name + '=')) {
	                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
	                    break;
	                }
	            }
	        }
	        return cookieValue;
	    }
	};
			
})(jQuery);

},'base/jqext.js');
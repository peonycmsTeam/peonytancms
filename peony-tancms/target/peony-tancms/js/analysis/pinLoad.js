(function(){
	__debug = true;
	var A = load;

	function noop(){}
	if(!window.console){
		window.console = {
		debug : noop,
		trace : noop,
		log : noop,
		warn : noop,
		error : noop,
		group:noop,
		groupEnd:noop
		}
	}

	function pipeMsg(opt){
		A.mix(this,opt)
	}
	window.pipe = pipe ={
		isReady : false,
		start :function(){
			this.isReady = true;
			for(var i=0,len=this.list.length;i<len;i++){
				this._run.apply(this,this.list[i]);
			}
			delete this.list
		},
		fireLock:false,
		cfg:{},
		_var:{},
		mode:{},
		pipVar:function(pipeId,val){
			if(val == undefined){
				return this._var[pipeId] || {};
			} else{
				this._var[pipeId] = val;
			}
		},
		_setVar:function(pipeId){
			var n = this.mode[pipeId];
			var v={};
			if(!n){
				return ;
			}
			for(var i in n){
				var val =n[i]
				if(i.substr(0,1) == "_" && typeof val != 'function'){
					v[i] = val;
				}
			}
			v["tpl"] = n["tpl"]
			pipe.pipVar(pipeId,v);
		},
		getModFromTpl:function(tpl){
			for(var key in this.mode){
				if(this.mode[key].tpl == tpl){
					return this.mode[key]
				}
			}
			return undefined
		},
		getModFromId:function(id){
			return this.mode[id]
		},
		loadModel:function(url,data,runMode){
			runMode = runMode || "get"
			var _url = url;
			if(this.fireLock){
				pin.log("loadMode is lock: now is loading" + this.fireLock);
			} else{
				var scriptDom;
				this.fireLock = url;
				var Util = pin.util;
				data = data || {};
				data.jsUid = data.jsUid || Util.getUID();
				this.fireModeUid = data.jsUid;
				var qdata = Util.queryString(data);
				if(url.indexOf('?') == -1){
					url = url + "?" + qdata;
				} else {
					url  = url + "&" + qdata;
				}
				pin.on("pipeJsEnd",function(e){
					scriptDom && scriptDom.remove();
					if(e.jsUid == pipe.fireModeUid){
						pipe.fireLock = false;
					}
					if( pin.util.arrayIndexOf(pipe.cancalUidArr,e.jsUid)==-1 ){
						function runFn(){
							pin.isJsModel = true;
							pipe._setVar(e.pipeId);
							pipe.mode[e.pipeId] && pipe.mode[e.pipeId]._destroy();
							window[e.jsfnName]();
							pipe.cfg[e.pipeId] = e;
							try{
								delete window[e.jsfnName];
							}catch(e1){}
						}
						//Before必须回调callBack
						if(pin.event[e.jsfnName+"Before"]){
							pin.fire(e.jsfnName+"Before",{callBack:runFn});
						} else {
							runFn();
						}
					}
	            },1);
	            pin.on("pipeJsRunEnd:"+data.pipeId,function(e){
	            	var fn = function(){
	            		pin.isJsModel = false;
		            	pin.log("异步模块"+e.jsfnName+"运行完成!");
	            	}
	            	if(pipe.reqTotal!=0){
	            		pipe.endRequireCallBcak.push(fn);
	            	} else {
	            		fn();
	            	}
		            	
	            },1);
	            if(runMode == "get"){
	            	scriptDom = $("<script src='"+url+"'></script>").appendTo('head')
	            } else {
	            	var _data = {jsUid:data.jsUid,pipeId:data.pipeId};
	            	delete data.jsUid;
	            	delete data.pipeId;
	            	var qdata = Util.queryString(_data);
					_url = _url + (_url.indexOf('?') == -1 ?"?":"&") + qdata;
	            	pin.request.postReq(_url,data,function(r){Function(r.getData())();},{dataType:"text"})
	            }
            }
		},
		cancalUidArr:[],
		cancalLoadModel : function(){
			pipe.fireModeUid && this.cancalUidArr.push(pipe.fireModeUid);
		},
		_run : function(cls,opt){
			try{
				opt = opt || {};
				pin.log(new pipeMsg(opt) );
				var clone = A.mix({},opt);
				clone.view = $(opt.html);
				delete opt.html;
				if(cls == "pageStart"){
					
					pin.cfg = pin.cfg || {};
	    			$.extend(pin.cfg,opt.json);

					if(opt.cls == "defaultView"){
						pin.fire("pageStart",opt);
					} else {
						pin.pageCls = opt.cls;
						this.addRequire("pipe/"+pin.pageCls+".js",function(){
							pin.fire("pageStart",opt);
						});
					}
				} else if(cls=="pageEnd"){  
					if(pin.pageCls){
						require("pipe/"+pin.pageCls+".js",function () {
							pipe.allowRunFireEnd = true;
							pipe.allowFireEnd();
						});
					} else {
						pipe.allowRunFireEnd = true;
						pipe.allowFireEnd();
					}
				} else {
					if($("#"+opt.id).length == 0 && pin.cfg.outputModel =="js"){
						pin.log("'%s' 在页面上没有定义 -> pipe路径:%s -> 命中模版文件:%s" ,opt.tpl , opt.pipePath , opt.hitTpl)
					} else{
						if(pin._cls[cls]){
							pipe.mode[opt.id] = pin.use(cls,clone);
						} else {
							var requireJsFile = cls;
							if(opt.json.requireJsFile){
								if(opt.json.requireJsFile == "__main__"){
									requireJsFile = pin.pageCls
								} else{
									requireJsFile = opt.json.requireJsFile;
								}
								this.addRequire("pipe/"+requireJsFile+".js",function(){
									if(pin._cls[cls]){
										pipe.mode[opt.id] = pin.use(cls,clone);
									} else {
										pipe.addRequire("pipe/"+cls+".js",function(){
											pipe.mode[opt.id]=pin.use(cls,clone);
										});
									}
								})
							} else {
								this.addRequire("pipe/"+cls+".js",function(){
									pipe.mode[opt.id]=pin.use(cls,clone);
								})
							}
							
						}
					}
				}
			}catch(e){
				console.error(e.name,e.number,e.descripion,e.message);
				throw e;
			}
		},
		list :[],
		addList : function(cls,opt){
			this.list.push([cls,opt]);
		},
		reqTotal:0,
		addRequire : function(file,fn){
			this.reqTotal++;
			if($.browser.msie){
				load.loadjqExt();
			}
			require(file,function () {
				fn();
				pipe.reqTotal--;
				pipe.allowFireEnd();
			});
		},
		endRequireCallBcak:[],
		allowFireEnd : function(){
			if(this.reqTotal == 0 ){
				if( this.allowRunFireEnd){
					pin.fire("pageEnd");
				}
				var fn;
				while(fn=this.endRequireCallBcak.shift()) fn();
			}
		},
		fire : function(cls,opt){
			opt = opt || {}
			if(this.isReady)
				this._run(cls,opt);
			else 
				this.addList(cls,opt);
		}
	}

	require(["jquery","base"],function() {
		pin.isJsModel = false;
		pin.on('pipeJsReady',$.noop);
		pin.reg("defaultView",pin.util.create({
			innerViewReady:$.noop,
			getViewBefore:$.noop,
			getView:function(){
				if(pin.isJsModel || pin.getCfg("outputModel") == "script"){
		            var v = [];
		            for(var i=0,len=this.view.length;i<len;i++){
		                if(this.view[i].nodeType == 1){
		                    v.push(this.view[i])
		                }
		            }
		            this.view = $(v);
		            this.view.attr("id",this.id);
		            if(this.muChip){
		                this.view.each(function(i){
		                    $("#"+this.id+"[part="+i+"]").replaceWith(this);
		                    $(this).attr("part",i);
		                });
		            } else {
		            	$("#"+this.id).replaceWith(this.view);
		            }
	        	} else {
	        		if(this.muChip){
	        			view = $("#"+this.id+",#"+this.id+"[part]")
	        			view.sort(function(a,b){
	        				a = $(a).attr("part") || 0
	        				b = $(b).attr("part") || 0
	        				return a-b
	        			});
	        			this.view = $(view)
	        		} else {
	        			this.view = $("#"+this.id)
	        		}
	        	}
			},
			init : function(opt){
				A.mix(this,opt);
				this.getViewBefore();
				this.getView();
				this.innerViewReady();
	        	$.extend(this,pipe.pipVar(this.id));
	            this.onviewReady();
	        },
	        jq :function(sel){
	            return sel?this.view.find(sel):this.view;
	        },
	        destroy:$.noop,
	        _destroy:function(){
	        	this.destroy();
	        	delete this.view;
	        },
	        onviewReady:$.noop
		}));
		pipe.start();
	});

})();
(function(){

    __debug = true;

    /**
     * @class gloab
     * 全局可以访问的内容
     * */

    /**
     * 定义一个模块
     * @param {String|Array} [mode]
     * 需求列表
     * @param {Function} callback
     * 模块实体内容
     * @param {String} modeName
     * 模块路径，注册名称
     * @method define
     * */
    define = function(mode,callbask,modeName){
        var list = [];
        switch (Object.prototype.toString.call(mode)){
            case '[object Function]' :
                mode();
                load.loadList[load.addProMod(callbask)] = 2;
                load.loadCallback();
                return;
            case '[object String]' : 
                list = load.getDependList(mode);
                break;
            case '[object Array]' :
                var flag = false;
                for(var i = 0 , len = mode.length; i < len; i++){
                    list = list.concat( load.getDependList( mode[i] , flag) );
                    flag = true;
                }
                break;
        }
        if(typeof modeName == "string") modeName = load.addProMod(modeName);
        load.loadMode(list,callbask,modeName);
    };
    
    /**
     * 根据配置加载需要模块
     * @param {String|Array} mode
     * 需求列表
     * @param {Function} callback
     * 回调函数
     * @method require
     * */
    var callList = [];
    require = function(){
        callList.push(arguments);
    }

    /**
     * 加载一个page模块
     * @param {String} pageId
     * 页面id
     * @param {String|Array} mode
     * 需求列表
     * @param {Function} callback
     * 回调函数
     * @method loadPage
     * */

    loadPage  = function(){
        var args = Array.prototype.slice.call(arguments);
        var page = args.shift();
        page = "mod/page/"+page.replace(/\//g,'_')+".js";
        args[0] = args[0].toString().split(',');
        args[0].push(page);
        require.apply(window,args);
    }

    /**
    * @class load
    * 模块加载根控件
    * */

    load = {
        nowTime : +(new Date()),
        /**
         * @property basePath
         * 项目文件的起始地址
         * @type {String}
         * */
        /**
         * @property pro
         * 项目模块文件夹 eq: gm_mod/
         * @type {String}
         * */
        /**
         * 是否调用了require函数
         * @type {Boolean}
         * */
        isCallRequire : false,
        /**
         * 回调序号，在设置名称回调的时候用到
         * @type {Number}
         * */
        id : 0,
        /**
         * 没有加载完成的模块的回调函数列表
         * @type {Object}
         * */
        callbaskList : {},
        /**
         * 返回最新的序号
         * @return {Number}
         * 返回最新的序号
         * */
        getRunId : function(){
            return ++this.id;
        },
        /**
         * 根据指定的模块列表加载模块
         * @param {Array} list
         * 模块列表
         * @param {Function} callback
         * 完成加载回调
         * @param {String} modeName
         * 模块的名字
         * */
        loadMode : function(list,callbask,modeName){
            if(this.isload(list)){
                modeName && (this.loadList[modeName] = 2);
                callbask && callbask();
                modeName && this.loadCallback();
            } else {
                var key = '_asy' +"_"+modeName+"_"+this.getRunId();
                this.dependList[key] = list;
                this.pushModelist(key);
                this.callbaskList[key] = function(){
                    modeName && (load.loadList[modeName] = 2);
                    callbask && callbask();
                };
                if(this.compose && this.id>1 && !this.map){
                    this.loadComposeResources(list);
                } else {
                    for(var i = 0 , len = list.length; i < len; i++){
                        if(!this.loadList[list[i]]){
                            var tmp = list[i];
                            this.loadList[tmp] = 1;

                            if( tmp.indexOf(":\/\/") != -1 ){
                                this.loadScript(tmp,true);
                                continue;
                            }
                            if( tmp.indexOf("@") == 0 ){
                                this.loadScript(tmp.substr(1),true);
                                continue;
                            }

                            this.loadScript(tmp);
                        }
                    }
                }
            }
        },
        composeTimes : 0,
        loadComposeResources : function(list){
            var jsList = [],cssList = [];
            var tmp;

            this.composeTimes++;

            for(var i = 0 , len = list.length; i < len; i++){
                if(!this.loadList[list[i]]){
                    this.loadList[list[i]] = 1;
                    if(list[i].lastIndexOf('.css')==-1){
                        if( list[i].indexOf(":\/\/") != -1 ){
                            this.loadScript(list[i],true);
                            continue;
                        }
                        if( list[i].indexOf("@") == 0 ){
                            this.loadScript(list[i].substr(1),true);
                            continue;
                        }
                        jsList.push(list[i]);
                    } else {
                        this.loadScript(list[i]);
                    }
                }
            }
            jsList.length && this.composefileName(jsList,'js');
            cssList.length && this.composefileName(cssList,'css');
        },
        composefileName : function(list,type){
            var defaultValue = this.basePath+"compose?t="+type+"&l=";
            var tmp = defaultValue + list[0];
            for(var i = 1 , len = list.length; i < len; i++){
                if(tmp.length + list[i].length>1024){
                    this.loadScript(tmp,true,type);
                    tmp = defaultValue + list[i];
                    continue;
                }
                tmp += ',' + list[i];
            }
            if(tmp!=defaultValue){
                this.loadScript(tmp,true,type);
            }
        },
        /**
         * 加载资源
         * @param {String} src
         * 资源相对地址
         * @param {Boolean} [notAddBasePath]
         * 使用绝对地址模式
         * */
        loadScript : function(src,notAddBasePath,type){
            var _src;
            if(!notAddBasePath){
                if(this.map && this.map[src]){
                    _src = load.basePath.replace(/\/([^\/])+\/$/,"/"+ this.map[src] +"/") + src;
                } else {
                    _src = this.basePath + src;
                }
            } else {
                _src = src;
            }
            if(type == 'js' || src.lastIndexOf('.css')==-1){
                if( src.lastIndexOf('.js')==-1 ) _src+= ".js";
                var dom = document.createElement("script");
                dom.src = _src+"?v="+load.ver;
                document.getElementsByTagName("head")[0].appendChild(dom);
            } else{
                var dom = document.createElement("link");
                dom.rel = "stylesheet";
                dom.href = _src;
                document.getElementsByTagName("head")[0].appendChild(dom);
                !type && (this.loadList[src] = 2);
            }
        },
        /**
         * 检查是否有模块的所有依赖完成，完成的话就触发他的回调
         * */ 
        loadCallback : function(){
            var isChange = false;
            for(var j=0,jlen=this.modelist.length;j<jlen;j++){
                var key = this.modelist[j],
                    l = this.getDependList(key),
                    flag = this.isload(l);
                if(flag){
                    this.loadList[this.modelist[j]] = 2;
                    isChange = true;
                    this.modelist.splice(j,1);
                    if( this.callbaskList[key] ){
                        this.callbaskList[key]();
                        delete this.callbaskList[key];
                    }
                    break;
                }
            }
            if(isChange) {
                this.loadCallback();
            }
        },
        /** 
         * 根据模块列表，判断列表中所以的项目是否都加载完成
         * @param {Array} list
         * 需要检查的模块列表
         * */
        isload : function(l){
            var flag = true;
            for(var _j=0,_jlen=l.length;_j<_jlen;_j++){
                if(this.loadList[l[_j]]!=2){
                    flag = false;
                    break;
                }
            }
            return flag;
        },
        /**
         * 添加待完成加载模块的别名
         * @param {String} key
         * 待添加的别名
         * */
        pushModelist : function(key){
            if(this.loadList[key]==2) return;
            var listSting  = ","+this.modelist.toString()+",";
            if(listSting.indexOf(','+key+',')==-1){
                this.modelist.push(key);
            }
        },
        /**
         * 已加载的模块列表, 一个hash表，键有两个状态1，2分别表示：已加载但没有完成、完成加载
         * @type {Object}
         * */
        loadList : {},
        /**
         * 待完成模块列表
         * @type {Array}
         * */
        modelist : ['base'],
        /**
         * 获取指定别名对应的列表
         * @param {String} mode
         * 别名
         * @return {Array}
         * 别名列表
         * */
        dependList : function(mode){
            var val = load.dependList[mode];
            if(Object.prototype.toString.call(val) == "[object Array]") {
                return this.extend([],val,mode);
            } else {
                return this.addProMod(val);
            }
        },
        /**
         * 根据别名根据实际情况返回对应列表,默认所有的别名都加上base别名下的内容
         * @param {String} mode
         * 别名
         * @param {Boolean} [isSelf]
         * 是否在返回值中默认附近base别名内容
         * @return {Array}
         * 转换之后的列表
         * */
        getDependList : function (mode,isSelf){
            if(mode == void 0) return [];
            var list = [];
            if(!isSelf && "base,jquery,depend".indexOf(mode) == -1) list = this.getDependList('base',true);
            if(mode.indexOf('_asy') == 0 )  return load.dependList(mode);
            var val = load.dependList(mode),flag = true;
            if(Object.prototype.toString.call(val) == "[object Array]") {
                for(var i=0,len=val.length;i<len;i++){
                    var returnValue = load.getDependList(val[i],true);
                    list = list.concat(returnValue);
                }
                this.pushModelist(mode);
            } else if(Object.prototype.toString.call(val) == "[object String]"){
                var returnValue = load.getDependList(val,true);
                list = list.concat(returnValue);
            } else {
                list.push( this.addProMod(mode) ) ;
            }
            return list;
        },
        /**
         * 特殊clone函数,当指定模块已加载不进行复制
         * @param {Object} target
         * 待修改对象
         * @param {Object} Source
         * 被复制对象
         * @param {String} mode
         * 别名，当前指定的别名
         * @return {Object}
         * 返回修改后的目标对象
         * */
        extend : function(t,s,mode){
            if(this.loadList[mode] != 2 || this.notChkFileMode){
                for(var k in s){
                    t[k] = this.addProMod(s[k]);
                }
            }
            return t;
        },
        /**
         * 为模块相对路径添加项目前缀
         * @param {String} val
         * 模块原始名称
         * @return {String}
         * 返回经过检查之后的模块相对路径
         * */
        addProMod : function(val){
            if(this.pro && val && val.indexOf && val.indexOf('mod/') == 0){
                return val.replace('mod/',this.pro); 
            } else {
                return val;
            }
        },
        /**
         * 返回当前页面已经加载的模块列表
         * @return {Array}
         * */
        exportJsFileList : function(){
            var allList = [];
            var list = [];

            delete load.loadList['depend'];
            delete load.loadList[load.pro + 'depend.js'];
            this.notChkFileMode = true;

            for(var key in this.loadList){
                if( !/\.css$/.test(key) && !/^_asy_/.test(key)){
                    allList.push(key);
                    !/\.js$/.test(key) && list.push(key);
                }
            }
            allList = ',' + allList+ ',';
            for(var i = 0, len = list.length; i < len; i++){
                var tmp = load.dependList(list[i],false);
                if(tmp){
                    for(var j = 0, jLen = tmp.length; j < jLen; j++){
                        allList = allList.replace(',' + tmp[j] + ',',',');
                    }
                }
            }

            delete this.notChkFileMode;
            allList = allList.split(',');

            for(var i = 0, len = allList.length; i < len; i++){
                if(allList[i].length == 0) {
                    allList.splice(i,1);
                    i--;len--;
                } else {
                    allList[i] = allList[i].replace(load.pro,'mod/');
                }
            }
            return allList;
            
        },
        /**
         * 页面初始化完成执行函数列表
         * @type {Array}
         * */
        readyCallBack : [],
        /**
         * 注册页面初始化完成回调函数接口
         * @param {Function} fn
         * 回调函数
         * */
        ready :function(fn){
            if (load.isReady){
                if(fn) fn();
                return;
            } else {
                if(fn){
                    load.readyCallBack.push(fn);
                    return;
                }
                for(var i=0,len = load.readyCallBack.length;i<len;i++){
                    load.readyCallBack[i]();
                }
                load.readyCallBack = [];
            }
            load.isReady = true;
            this.loadExternal();
        },
        isWindow  : function(obj) {
            if (!obj)
                return false
            if (obj === window)
            return obj == obj.document && obj.document != obj
        },
        isPlainObject : function(obj) {
            if (Object.prototype.toString.call(obj) !== "[object Object]" || obj.nodeType || this.isWindow(obj)) {
                return false
            }
            try {
                if (obj.constructor && !Object.prototype.hasOwnProperty.call(obj.constructor.prototype, "isPrototypeOf")) {
                    return false
                }
            } catch (e) {
                return false
            }
            return true
        },
        mix : function() {
            var options, name, src, copy, copyIsArray, clone,
                    target = arguments[0] || {},
                    i = 1,
                    length = arguments.length,
                    deep = false

            // 如果第一个参数为布尔,判定是否深拷贝
            if (typeof target === "boolean") {
                deep = target
                target = arguments[1] || {}
                i = 2
            }

            //确保接受方为一个复杂的数据类型
            if (typeof target !== "object" && bject.prototype.toString.call(target) !== "[object Function]") {
                target = {}
            }

            //如果只有一个参数，那么新成员添加于mix所在的对象上
            if (length === i) {
                target = this
                --i
            }

            for (; i < length; i++) {
                //只处理非空参数
                if ((options = arguments[i]) != null) {
                    for (name in options) {
                        src = target[name]
                        copy = options[name]

                        // 防止环引用
                        if (target === copy) {
                            continue
                        }
                        if (deep && copy && (load.isPlainObject(copy) || (copyIsArray = Array.isArray(copy)))) {

                            if (copyIsArray) {
                                copyIsArray = false
                                clone = src && Array.isArray(src) ? src : []

                            } else {
                                clone = src && load.isPlainObject(src) ? src : {}
                            }

                            target[name] = load.mix(deep, clone, copy)
                        } else if (copy !== void 0) {
                            target[name] = copy
                        }
                    }
                }
            }
            return target
        },
        /**
         * 初始化ready实现方法
         * */
        bindReady : function(){
            if (document.addEventListener) {    
                document.addEventListener("DOMContentLoaded", function () {     
                    document.removeEventListener("DOMContentLoaded", arguments.callee, false);
                    load.ready();
                }, false);
            } else if (document.attachEvent) {      
                document.attachEvent("onreadystatechange", function () {
                    if (document.readyState === "complete") {
                        document.detachEvent("onreadystatechange", arguments.callee);
                        load.ready();
                    }
                });
                if (document.documentElement.doScroll && typeof window.frameElement === "undefined")
                    (function () {
                        if (load.isReady)
                            return;
                        try {
                            document.documentElement.doScroll("left");
                        } catch (error) {   
                            setTimeout(arguments.callee, 0);
                            return;
                        }
                        load.ready();
                    })();
            }
            if(window.addEventListener){
                document.addEventListener('load',function(){load.ready();},null);
            } else{
                document.attachEvent('onload',function(){load.ready();});
            }

        },
        /** 
         * 是否已经加载external列表
         * @type {Boolean}
         * */
        isLoadExternal : false,
        /**
         * 加载external列表
         * */
        runExternal : function(){
            if(this.isLoadExternal)return;
            this.isLoadExternal = true;
            var externalList = load.dependList.external;
            if(externalList){
                for(var i=0,len = externalList.length;i<len;i++){
                    load.loadScript(externalList[i],1);
                }
            }
        },
        /**
         * 根据待依赖加载完成判断是否处理external列表
         * */
        loadExternal : function(isload){
            define('depend',function(){
                if(!load.isCallRequire)
                    load.runExternal();
            });

        }
    };

    var script = document.getElementsByTagName('script');
    script = script[script.length-1];

    var src = script.hasAttribute ? script.src : script.getAttribute('src',4)
    load.basePath = src.substr(0,src.lastIndexOf('load.js'));

    if(script.getAttributeNode('pro')){
        load.pro = script.getAttributeNode('pro').value +'_mod/';
    }
    if(script.getAttributeNode('svn')){
        load.pro = load.pro + script.getAttributeNode('svn').value +"/";
    }
    if(script.getAttributeNode('map')){
        load.map = true;
    }
    if(script.getAttributeNode('patch')){
        load.hasPatch = true;
        var text = "(" + script.text + ")";
        try{
            load.patchList = eval(text);
        } catch(e){}
    }
    if(script.getAttributeNode('ver').value){
        load.ver = script.getAttributeNode('ver').value
    }

    var hash = /#v=([0-9]+|dev)/.exec(location.hash);
    if(hash){
        load.basePath = load.basePath.replace(/\/([^\/])+\/$/,"/"+hash[1]+"/");
    }
    load.compose = ! /(:82)|(\/dev)\/$/.test(load.basePath);
    if(/debug$/.test(location.hash)){
        load.compose = false;
    }
    load.compose = 0;
    if(load.pro){
        load.dependList["depend"] = ['mod/depend.js'];
        if(load.map) load.dependList["depend"].push("mod/map.js");
    } else {
        load.dependList["depend"] = [];
        load.dependList["base"] = [];
    }

    var rword = /[^, ]+/g;
    var class2type = {};
    var DONT_ENUM = "propertyIsEnumerable,isPrototypeOf,hasOwnProperty,toLocaleString,toString,valueOf,constructor".split(",")
    "Boolean Number String Function Array Date RegExp Object Error".replace(rword, function(name) {
        class2type["[object " + name + "]"] = name.toLowerCase()
    });


    if (!"pin".trim) {
        String.prototype.trim = function() {
            return this.replace(/^[\s\xA0]+/, "").replace(/[\s\xA0]+$/, '')
        }
    }
    for (var i in {
        toString: 1
    }) {
        DONT_ENUM = false
    }
    if (!Object.keys) {
        Object.keys = function(obj) { //ecma262v5 15.2.3.14
            var result = []
            for (var key in obj)
                if (obj.hasOwnProperty(key)) {
                    result.push(key)
                }
            if (DONT_ENUM && obj) {
                for (var i = 0; key = DONT_ENUM[i++]; ) {
                    if (obj.hasOwnProperty(key)) {
                        result.push(key)
                    }
                }
            }
            return result
        }
    }

    if (!(function(){}).bind) {
        Function.prototype.bind = function(scope) {
            if (arguments.length < 2 && scope === void 0)
                return this
            var fn = this,
                    argv = arguments
            return function() {
                var args = [],
                        i
                for (i = 1; i < argv.length; i++)
                    args.push(argv[i])
                for (i = 0; i < arguments.length; i++)
                    args.push(arguments[i])
                return fn.apply(scope, args)
            }
        }
    }

    function iterator(vars, body, ret) {
        var fun = 'for(var ' + vars + 'i=0,n = this.length; i < n; i++){' + body.replace('_', '((i in this) && fn.call(scope,this[i],i,this))') + '}' + ret
        return Function("fn,scope", fun)
    }
    if (![].map) {
        load.mix(Array.prototype, {
            //定位操作，返回数组中第一个等于给定参数的元素的索引值。
            indexOf: function(item, index) {
                var n = this.length,
                        i = ~~index
                if (i < 0)
                    i += n
                for (; i < n; i++)
                    if (this[i] === item)
                        return i
                return -1
            },
            //定位引操作，同上，不过是从后遍历。
            lastIndexOf: function(item, index) {
                var n = this.length,
                        i = index == null ? n - 1 : index
                if (i < 0)
                    i = Math.max(0, n + i)
                for (; i >= 0; i--)
                    if (this[i] === item)
                        return i
                return -1
            },
            //迭代操作，将数组的元素挨个儿传入一个函数中执行。Ptototype.js的对应名字为each。
            forEach: iterator('', '_', ''),
            //迭代类 在数组中的每个项上运行一个函数，如果此函数的值为真，则此元素作为新数组的元素收集起来，并返回新数组
            filter: iterator('r=[],j=0,', 'if(_)r[j++]=this[i]', 'return r'),
            //收集操作，将数组的元素挨个儿传入一个函数中执行，然后把它们的返回值组成一个新数组返回。Ptototype.js的对应名字为collect。
            map: iterator('r=[],', 'r[i]=_', 'return r'),
            //只要数组中有一个元素满足条件（放进给定函数返回true），那么它就返回true。Ptototype.js的对应名字为any。
            some: iterator('', 'if(_)return true', 'return false'),
            //只有数组中的元素都满足条件（放进给定函数返回true），它才返回true。Ptototype.js的对应名字为all。
            every: iterator('', 'if(!_)return false', 'return true')
        })
    }
    if (!document.documentElement.contains) {//safari5+是把contains方法放在Element.prototype上而不是Node.prototype
        Node.prototype.contains = function(arg) {
            return !!(this.compareDocumentPosition(arg) & 16)
        }
    }

    load.extend(load.dependList,{
        'jquery' : ['jquery.js'],
        'base' : ['jquery','base/jqext.js',"base/base.js","base/qrcode.js","base/request.js"],
        'ux' : ["ui","base/jqext.js","base/ux.js"],
        "ui" :["base/ui.js"],
        "action":["base/action.js"],
        "base/jqext":["base/jqext.js"],
        'tags':["tags.js"],
        'highstock':['highstock.js'],
        'highcharts-more':['highcharts-more.js'],
        "highcharts":['highcharts.js'],
        "chinaMinHigh":['maps/chinaMinHigh.js'],
        "ammap":['maps/ammap.src.js'],
        "raphael":['maps/raphael-min.js']
    });

    //define('depend',function(){

    load.extend(load.dependList,load.patchList);

    var len = callList.length;
    define('base',function(){
        if(len && window.dw){
            dw.on('pageStart',function(){
                load.runExternal();
            })
        }
    });
    if(len){
        load.isCallRequire = true;
        require = define;
        for(var i = 0; i < len; i++){
            define.apply(window,callList[i]);
        }
    } else {
        require = function(){
            load.isCallRequire = true;
            require = define;
            define.apply(window,arguments);
        }
    }
    //});


    load.bindReady();

})();
define(["jquery","base/base.js"],function(){

    $.extend(pin.request,{
        setResume : function(data,fn){
            return this.postReq("/account/setResume.do",data,fn);
        },
        search_major : function(searchKey,fn,extData){
            var extData = extData || {};
            return this.q("/search/searchJson.do",$.extend({searchKey:searchKey,searchType:"major"},extData),fn);
        },
        search_school : function(searchKey,fn,extData){
            var extData = extData || {};
            return this.q("/search/searchJson.do",$.extend({searchKey:searchKey,searchType:"sch"},extData),fn);
        },
        search_inc : function(searchKey,fn,extData){
            return this.q("/search/searchJson.do",$.extend({searchKey:searchKey,searchType:"inc"},extData),fn);
        },
        search_loc : function(searchKey,fn,extData){
            return this.q("/search/searchJson.do",$.extend({searchKey:searchKey,searchType:"loc"},extData),fn);
        }
    });

},'base/request.js');

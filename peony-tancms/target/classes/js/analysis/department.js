define("tags",function(){

    var Util = pin.util;

    pin.reg("company/details/department",Util.create(pin.getCls("defaultView"),{
        onviewReady:function(){
            var aList = this.jq(".charts a")
            aList.addClass("_tag")
            if(aList.length){
                new tags({
                    xradius:350,
                    yradius:80,
                    dir:"x",
                    tspeed:.5,
                    container:this.jq(".charts")[0]
                });
            }
        }
    }));

},"pipe/company/details/department.js");

/**
 * Description:
 * <br/>
 *
 * @author yanglin
 * @version 1.0.0
 */

var monthLengthOfNormalYear = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31); //days of month in normal year
var monthLengthOfLeapYear = new Array(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31); //days of month in leap year

/**
 * 根据type格式(yyyy-MM-dd、yyyy-MM-dd HH:mm:ss)，返回Date类型
 */
String.prototype.parseDate = function(type) {
    var value = this.trim();
    if (value.length == 0 || type == null || value.length != type.length) {
        return null;
    }
    var _year = '',_month = '',_day = '',_hour = '',_minute = '',_second = '';
    for (var i = 0; i < type.length; i++) {
        switch (type.charAt(i)) {
            case 'y':
                _year += value.charAt(i);
                break;
            case 'M':
                _month += value.charAt(i);
                break;
            case 'd':
                _day += value.charAt(i);
                break;
            case 'H':
                _hour += value.charAt(i);
                break;
            case 'm':
                _minute += value.charAt(i);
                break;
            case 's':
                _second += value.charAt(i);
                break;
            default:
               if(type.charAt(i)!=value.charAt(i)){
                    return null;
                }
                break;
        }
    }
    if (!( _year.isNumber() && _month.isNumber() && _day.isNumber())) {
        return null;
    }
    if (_year < 1900 || _year > 2100) {
        Logger.debug("String.parseDate:Year out of Range!");
        return null;
    }
    if (_month < 1 || _month > 12) {
        Logger.debug("String.parseDate:Month out of Range!");
        return null;
    }
    if (_hour < 0 || _hour > 23) {
        Logger.debug("String.parseDate:Hour out of Range!");
        return null;
    }
    if (_minute < 0 || _minute > 59) {
        Logger.debug("String.parseDate:Minute out of Range!");
        return null;
    }
    if (_second < 0 || _second > 59) {
        Logger.debug("String.parseDate:Second out of Range!");
        return null;
    }
    if (( _year % 100 == 0 && _year % 400 == 0 ) || ( _year % 100 != 0 && _year % 4 == 0 )) {      //LeapYear
        if (_day < 1 || _day > monthLengthOfLeapYear[_month - 1]) {
            Logger.debug("String.parseDate:Days of Leap Year out of Range!");
            return null;
        }
    } else {
        if (_day < 1 || _day > monthLengthOfNormalYear[_month - 1]) {
            Logger.debug("String.parseDate:Days of Year out of Range!");
            return null;
        }
    }
    return new Date(_year, _month - 1, _day, _hour, _minute, _second);
};
/**
 * 去掉首空格
 */
String.prototype.cleanPrevBlank = function() {
    return this.replace(/(^\s*)/g, "");
};
/**
 * 去掉尾空格
 */
String.prototype.cleanEndBlank = function() {
    return this.replace(/(\s*$)/g, "");
};
/**
 * 去掉首尾空格
 */
String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
};
/**
 * 去空格
 */
String.prototype.cleanBlank = function() {
    return this.replace(/\s/g, "");
};
/**
 * 判断字符串是否为空
 */
String.prototype.isEmpty = function() {
    return this.replace(/\s/g, "").length == 0 || this.replace(/\s/g, "").toLocaleLowerCase() =="null";
};
/**
 * 	判断字符串是否是由字符组成
 */
String.prototype.isLetter = function() {
    if (this.length == 0) {
        return true;
    }
    for (var i = 0; i < this.length; i++) {
        var ch = this.charAt(i);
        if (ch < 'A' || ch > 'z' || ( ch > 'Z' && ch < 'a' )) {
            return false;
        }
    }
    return true;
};
/**
 * 获取字符串长度
 */
String.prototype.getLength = function() {
    return this.replace(/[^\x00-\xff]/g, "aaa").length;
};

/**
 * 判断日期是否合法，type格式(yyyy-MM-dd、yyyy-MM-dd HH:mm:ss)
 */
String.prototype.isDate = function(type) {
    var value = this.trim();
    if (value.length == 0 || type == null || value.length != type.length) {
        return false;
    }
    var _year = '',_month = '',_day = '',_hour = '',_minute = '',_second = '';
    for (var i = 0; i < type.length; i++) {
        switch (type.charAt(i)) {
            case 'y':
                _year += value.charAt(i);
                break;
            case 'M':
                _month += value.charAt(i);
                break;
            case 'd':
                _day += value.charAt(i);
                break;
            case 'H':
                _hour += value.charAt(i);
                break;
            case 'm':
                _minute += value.charAt(i);
                break;
            case 's':
                _second += value.charAt(i);
                break;
            default:
                if(type.charAt(i)!=value.charAt(i)){
                    return false;
                }
                break;
        }
    }
    if (!( _year.isNumber() && _month.isNumber() && _day.isNumber() && _hour.isNumber() && _minute.isNumber() && _second.isNumber())) {
        return false;
    }
    if (_year < '1900' || _year > '2100') {
        Logger.debug("String.isDate:Year out of Range!");
        return false;
    }
    if (_month < 1 || _month > 12) {
        Logger.debug("String.isDate:Month out of Range!");
        return false;
    }
    if (( _year % 100 == 0 && _year % 400 == 0 ) || ( _year % 100 != 0 && _year % 4 == 0 )) {      //LeapYear
        if (_day < 1 || _day > monthLengthOfLeapYear[_month - 1]) {
            Logger.debug("String.isDate:Days of Leap Year out of Range!");
            return false;
        }
    } else {
        if (_day < 1 || _day > monthLengthOfNormalYear[_month - 1]) {
            Logger.debug("String.isDate:Days of Year out of Range!");
            return false;
        }
    }
    if (_hour < 0 || _hour > 23) {
        Logger.debug("String.isDate:Hour out of Range!");
        return false;
    }
    if (_minute < 0 || _minute > 59) {
        Logger.debug("String.isDate:Minute out of Range!");
        return false;
    }
    if (_second < 0 || _second > 59) {
        Logger.debug("String.isDate:Second out of Range!");
        return false;
    }
    return true;
};
/**
 * 字符串转Float
 */
String.prototype.toFloat = function() {
    try {
        return parseFloat(this.replace(/[^0-9.-]/g, ''));
    } catch(e) {
        Logger.debug(e);
        Logger.debug("String.toFloat:[" + this + "] parse float error!");
        return null;
    }
};
/**
 * 字符串转Integer
 */
String.prototype.toInteger = function() {
    try {
        return parseInt(this.replace(/[^\d]/g, ''), 10);
    } catch(e) {
        Logger.debug(e);
        Logger.debug("String.toInteger:[" + this + "] parse int error!");
        return null;
    }
};
/**
 * 判断字符串是否是由数字组成
 */
String.prototype.isInteger = function() {
    try {
        return this.trim() == "" + parseInt(this, 10);
    } catch(e) {
        Logger.debug(e);
        Logger.debug("String.isInteger:[" + this + "] parse int error!");
        return false;
    }
};
/**
 * 判断字符串是否是浮点型
 */
String.prototype.isFloat = function(degit) {
    try {
        if (this.trim() == "" + parseFloat(this)) {
            if (degit == null) return true;
            var dotSite = this.indexOf(".");
            if (dotSite == -1) return true;
            var sc = this.substring(dotSite + 1);
            return sc.trim().length <= degit;
        }
    } catch(e) {
        Logger.debug(e);
        Logger.debug("String.isFloat:[" + this + "] parse float error!");
    }
    return false;
};
/**
 * 判断字符串是否是数字OR字符
 */
String.prototype.isNumberOrLetter = function() {
    if (this.isEmpty()) {
        return false;
    }
    for (var i = 0; i < this.length; i++) {
        var ch = this.charCodeAt(i);
        if ((ch < 'A' || ch > 'z' || ( ch > 'Z' && ch < 'a' )) && (ch > '9' || ch < '0')) {
            return false;
        }
    }
    return true;
};
/**
 * 判断字符串是否是char类型
 */
String.prototype.isChar = function() {
    if (this.isEmpty()) {
        return false;
    }
    for (var i = 0; i < this.length; i++) {
        if (this.charCodeAt(i) > 128 || this.charAt(i) == ' ') {
            return false;
        }
    }
    return true;
};
/**
 * 判断字符串是否是Number类型
 */
String.prototype.isNumber = function() {
    return this.trim().replace(/[^\d]/g, '') == this.trim();
};
/**
 * 验证邮箱
 */
String.prototype.isEmail = function() {
    return this.trim().replace(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/, "").length == 0;
};
/**
 * 验证邮箱
 */
String.prototype.isEmail2 = function() {
    var emailReg = new RegExp("^[0-9a-zA-Z\._\-]+@[0-9a-zA-Z]+[\.]{1}[0-9a-zA-Z]+[\.]?[0-9a-zA-Z]+$");
    return emailReg.test(this);
};
/**
 * 字符串转数组
 * @param _split
 * @return
 */
String.prototype.toArray = function(_split) {
    return this.split(_split);
};
/**
 * 判断字符串开头
 * @param str
 * @return
 */
String.prototype.startWith = function(str) {
    return this.substring(0, str.length) == str;
};
/**
 * 判断字符串结尾
 * @param str
 * @return
 */
String.prototype.endWith = function(str) {
    return this.substring(this.length - str.length) == str;
};
/**
 * 忽略大小写，判断字符串开头
 * @param str
 * @return
 */
String.prototype.startWithIgnoreCase = function(str) {
    return this.substring(0, str.length).toUpperCase() == str.toUpperCase();
};
/**
 * 忽略大小写，判断字符串结尾
 * @param str
 * @return
 */
String.prototype.endWithIgnoreCase = function(str) {
    return this.substring(this.length - str.length).toUpperCase() == str.toUpperCase();
};
/**
 * 忽略大小写，判断字符串是否包含某字符串
 * @param str
 * @return
 */
String.prototype.indexOfIgnoreCase = function(str) {
    return this.toUpperCase().indexOf(str.toUpperCase()) > -1;
};
/**
 * 忽略大小写，判断字符串内容是否一致
 * @param o
 * @return
 */
String.prototype.equasIgnoreCase = function(o) {
    var a = this.toLowerCase();
    var b = o.toLowerCase();
    return a == b;
};
/**
 * 字符串替换，s1由s2替换
 * @param s1
 * @param s2
 * @return
 */
String.prototype.replaceAll = function(s1, s2) {
    return this.replace(new RegExp(s1, "gm"), s2);
};
/**
 * encoder方法
 * @return
 */
String.prototype.encoder = function() {
    return encodeURI(this);
};
/**
 * decoder方法
 * @return
 */
String.prototype.decoder = function() {
    return decodeURIComponent(this);
};
/**
 * 判断字符串长度是否超过maxSize
 * @param maxSize
 * @return
 */
String.prototype.checkMaxSize = function(maxSize) {
    return this.getLength <= maxSize;
};

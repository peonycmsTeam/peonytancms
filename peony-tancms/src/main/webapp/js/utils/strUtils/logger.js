function Log(level, object, viewType) {
    this.level = level;
    this.object = object;
    this.viewType = viewType;
}

Log.LEVEL_DEBUG = 0;
Log.LEVEL_INFO = 1;
Log.LEVEL_WARN = 2;
Log.LEVEL_ERROR = 3;
Log.LEVEL_FATAL = 4;

Log.VIEW_TYPE_ALERT = 0; //give hint by alert or innerHTML
Log.VIEW_TYPE_APPEND = 1; //give hint by adding html node


Log.prototype.setLevel = function(level) {
    this.level = level;
};

Log.prototype.setObject = function(o) {
    if (typeof o == 'string') {
        this.object = document.getElementById(o);
    } else {
        this.object = o;
    }
};

Log.prototype.setViewType = function(type) {
    this.viewType = type;
};

Log.prototype.log = function(s) {
    this.message(100, s);
};

Log.prototype.debug = function(s) {
    this.message(Log.LEVEL_DEBUG, s);
};

Log.prototype.info = function(s) {
    this.message(Log.LEVEL_INFO, s);
};

Log.prototype.warn = function(s) {
    this.message(Log.LEVEL_WARN, s);
};

Log.prototype.error = function(s) {
    this.message(Log.LEVEL_ERROR, s);
};

Log.prototype.fatal = function(s) {
    this.message(Log.LEVEL_FATAL, s);
};

Log.prototype.message = function(level, s) {
    if (level >= this.level) {
        if (this.object != null) {
            this.object.innerHTML = s;
        } else if (this.viewType == Log.VIEW_TYPE_ALERT) {
            alert(s);
        } else {
            document.appendChild(document.createTextNode(s));
            document.appendChild(document.createElement("br"));
        }
    }
};

var Logger = new Log(Log.LEVEL_INFO, null, Log.VIEW_TYPE_ALERT);

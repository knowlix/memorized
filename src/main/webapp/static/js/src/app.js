/**
 * 
 */
var App = $.Class.create({
    initialize: function (name, options) {
        this.name = name;
        var options = options || {};
        $.extend(this, options);
        this.context = "";
        this.templatesPath = "/static/templates";
        this.debug = options.debug || false;
        
        this.effectDuration = options.effectDuration || 0;
        this.memaIdPrefix = options.memaIdPrefix || 'm';
        this.cardIdPrefix = options.cardIdPrefix || 'c';
        this.microsoftTranslator = options.microsoftTranslator || 'http://api.microsofttranslator.com/V2/Ajax.svc/';
        
    }
});

var app = new App("memorized", {
    effectDuration: 300,
    debug: true,
    memaIdPrefix: 'mema',
    cardIdPrefix: 'card'
});
/**
 * Переводчик.
 */
var Translator = $.Class.create({
    
    initialize: function (app_id) {
        this.appId = app_id;
    },
    
    /**
     * Определение текущего языка.
     * */
    detectLanguage: function (text) {
        var s = document.createElement("script");
        var functionName = "Detect";
        window.detectLanguageCallback = function(response) {
            console.log(response);
            $(s).remove();
        };
        s.src = app.microsoftTranslator + functionName + "?oncomplete=detectLanguageCallback&appId=" + this.appId + "&text=" + text;
        document.getElementsByTagName("head")[0].appendChild(s);
    },
    
    /**
     * Перевод текста в направлении от {from} к {to}.
     * */
    translate: function(text, from, to) {
        var s = document.createElement("script");
        var functionName = "Translate";
        window.translateCallback = function (response) {
            console.log(response);
            $(s).remove();
        };
        s.src = app.microsoftTranslator + functionName + "?oncomplete=translateCallback&appId=" + this.appId + "&from=" + from + "&to=" + to + "&text=" + text;
        document.getElementsByTagName("head")[0].appendChild(s);
    }
    
});
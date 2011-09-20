/**
 * JQuery UI Widget карты-словаря
 */
(function($){
    $.widget("memorized.GuideWidget", $.memorized.CardWidget, {
        options: {
            template: "guideCard",
            typeClass: "guide"
        },
        _create: function() {
            $.memorized.CardWidget.prototype._create.call(this);
        },
        _bindEvents: function() {
            var $this = this;
            $.memorized.CardWidget.prototype._bindEvents.call(this);
        }
    });
}) (jQuery);
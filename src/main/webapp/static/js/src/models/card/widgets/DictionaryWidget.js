/**
 * JQuery UI Widget карты-словаря
 */
(function($){
    $.widget("memorized.DictionaryWidget", $.memorized.CardWidget, {
        options: {
            template: "dictionaryCard",
            typeClass: "dictionary"
        },
        _create: function() {
            $.memorized.CardWidget.prototype._create.call(this);
        },
        _bindEvents: function() {
            var $this = this;
            $.memorized.CardWidget.prototype._bindEvents.call(this);
            // перемешать
            this.element.find('.action_shuffle_mema').bind('click', function(){
                $this.shuffle();
            });
            // инвертировать
            this.element.find('.action_invert_mema').bind('click', function() {
                $this.invert();
                $(this).toggleClass("selected");
            });
        },
        // перемешать содержимое
        shuffle: function () {
            var model = this.options.model;
            if (model.memas.length <= 1) {return;}
            var isChanged = false;
            while (!isChanged) {
                for (var i = 0; i < model.memas.length; i++) {
                    if (typeof model.getMemaByPosition(i).element == 'undefined') {continue;}
                    var randomMema = null;
                    while (randomMema == null) {
                        randomMema = model.getRandomMema();
                        if (typeof randomMema.element == 'undefined') {
                            randomMema = null;
                        }
                    }
                    if (randomMema.memaId != model.getMemaByPosition(i).memaId) {
                        model.getMemaByPosition(i).moveAfter(randomMema);
                        isChanged = true;
                    }
                }
            }
        },
        // инвертировать заголовок с описанием
        invert: function () {
            var model = this.options.model;
            for (var i = 0; i < model.memas.length; i++) {
                var mema = model.memas[i];
                var title = mema.element.find(".title");
                var description = mema.element.find(".description");
                var titleValue = title.html();
                title.html(description.html());
                description.html(titleValue);
            }
        }
    });
}) (jQuery);
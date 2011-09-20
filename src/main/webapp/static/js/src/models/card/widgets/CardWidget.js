/**
 * JQuery UI Widget карты 
 */
(function($){
    $.widget('memorized.CardWidget', {
        options: {
            template: null,
            cardClass: "card",
            typeClass: "card",
            minWidth: 388,
            minHeight: 300
        },
        _create: function() {
            if (typeof this.options.model == 'undefined') {
                console.error("Виджет карты не привязан к модели");
            }
            var model = this.options.model;
            var html = $(new EJS({url: app.templatesPath + "/" + this.options.template, cache: !app.debug, ext: '.js'}).render(this.options.model));
            this.element.addClass(this.options.cardClass);
            this.element.addClass(this.options.typeClass);
            this.element.attr("id", "card" + model.cardId);
            this.element.css({
                position: "absolute",
                left: model.left,
                top: model.top,
                height: model.height,
                width: model.width,
                zIndex: model.zIndex
            });
            this.element.append(html);
            this._autoSizeInnerElement();
            this._bindEvents();
        },
        destroy: function() {
            $.Widget.prototype.destroy.call(this);
        },
        _bindEvents: function () {
            
            var model = this.options.model;
            var $this = this;
            var isCtrl = false;
            
            //this._trigger("onBindEvenets", null, this.element);
            
            this.element.find("div.title").bind("mousedown", function(){
                $this.updateZIndex($this.options.model.space.getMaxZIndex() + 1);
            });
            // делаем карту перемещаемой
            this.element.draggable({
                handle: ".title",
                containment: "#content",
                start: function(event, ui) {
                    //$this.updateZIndex($this.options.model.space.getMaxZIndex() + 1);                    
                },
                stop: function(event, ui) {
                    /* вычитает высоту header'a из-за относительного позиционирования */
                    $this.updateCoords(ui.offset.left, ui.offset.top - $("header").height());
                }
            });
            // делаем карту с изменяемым размером
            this.element.resizable({
                containment: "#content",
                minHeight: this.options.minHeight,
                minWidth: this.options.minWidth,
                stop: function(event, ui) {
                    $this.updateSize(ui.size.width, ui.size.height);
                },
                resize: function(event, ui) {
                    $this._autoSizeInnerElement();
                }
            });
            for (var i = 0; i < model.memas.length; i++) {
                model.memas[i].bindEvents(model, this.element);
            }
            
            // добавить
            this.element.find('.action_add_mema').bind('click', function(){
                $this.addMema();
            });
            
            // события формы добавления новой мемы
            this.element.find('.form_add_mema input').bind('keypress', function(e){
                if (e.keyCode == 13) {
                    $this.element.find('.form_add_mema textarea').focus();
                    return false;
                };
            });
            this.element.find('.form_add_mema textarea').bind('keydown', function(e){
                if (e.which == 13 && isCtrl) {
                    $this.element.find('.form_add_mema').submit();
                } else if (e.which == 17) {
                    isCtrl = true;
                }
            });
            this.element.find('.form_add_mema textarea').bind('keyup', function(e){
                if (e.which == 17) {
                    isCtrl = false;
                }
            });
            this.element.find('.form_add_mema').bind('submit', function(){
                var memaId = $(this).find('input[name="memaId"]').val();
                var title = $(this).find('input[name="title"]').val();
                var description = $(this).find('textarea[name="description"]').val();
                $(this).find('input[name="memaId"]').val("");
                $(this).find('input[name="title"]').val("");
                $(this).find('textarea[name="description"]').val("");
                if (memaId != "") {
                    var mema = $this.options.model.getMemaById(memaId);
                    mema.title = title;
                    mema.description = description;
                    mema.update();
                } else {
                    var mema = new Mema({
                        cardId: model.cardId,
                        title: title,
                        description: description,
                        position: model.memas.length,
                        card: model
                    });
                    mema.save();
                    model.memas = [mema].concat(model.memas);
                }
                $(this).fadeOut(app.effectDuration, function () {
                    $this.element.find('.memas').fadeIn(app.effectDuration);
                });
                $this.element.find('.action_cancel').remove();
                $this.element.find('.tools ul.right li').fadeIn(app.effectDuration);
                $this.element.find('.tools ul.left li').fadeIn(app.effectDuration);
                $this.element.find('.action_add_mema').removeClass('selected');
                $this.element.find('.action_add_mema').unbind('click').bind('click', function(){
                    $this.addMema();
                });
                return false;
            });
            
            this.element.find('.action_show_all_memas').bind('click', function(){
                $(this).siblings().removeClass("selected");
                $(this).addClass("selected");
                $this.showAllMemas();
            });
            this.element.find('.action_show_today_memas').bind('click', function(){
                $(this).siblings().removeClass("selected");
                $(this).addClass("selected");
                $this.showTodayMemas();
            });
            
            // рендеринг шрифтов карты
            Cufon.replace(this.element.find(".title h3"), {
                fontFamily: "Arial"
            });
            Cufon.replace(this.element.find(".tools li"), {
                fontFamily: "Tahoma"
            });
        },
        
        // показ формы добавления новой мемы и сохранение новой мемы
        addMema: function (memaId, title, description) {
            var $this = this; 
            
            var form = $this.element.find('.form_add_mema');
            var memaId = memaId || "";
            var title = title || "";
            var description = description || "";
                        
            $this.element.find('.tools .right li').not('.action_add_mema').fadeOut(app.effectDuration, function () {});
            this.element.find('.memas').fadeOut('slow', function(){
                $this.element.find('.action_add_mema').addClass('selected');
                $this.element.find('.action_add_mema').unbind('click').bind('click', function(){
                    $this.element.find('.form_add_mema').submit();
                });
                
                $this.element.find('.tools .right').append('<li class="action_cancel">отмена</li>');
                $this.element.find('.action_cancel').bind('click', function(){
                    $this.element.find('.action_add_mema').unbind('click').bind('click', function(){
                        $this.addMema();
                    });
                    $this.element.find('.form_add_mema').fadeOut(app.effectDuration, function(){
                        $this.element.find('.memas').fadeIn();
                    });
                    $(this).remove();
                    $this.element.find('.tools ul.right li').fadeIn(app.effectDuration);
                    $this.element.find('.tools ul.left li').fadeIn(app.effectDuration);
                    $this.element.find('.action_add_mema').removeClass('selected');
                });
                Cufon.replace($this.element.find(".action_cancel"), {
                    fontFamily: "Tahoma"
                });

                $this.element.find('.form_add_mema').fadeIn(app.effectDuration, function() {
                    $(this).find('input[name="memaId"]').val(memaId);
                    $(this).find('input[name="title"]').val(title);
                    $(this).find('textarea').val(description);
                    $(this).find('input').focus();
                });
            });
        },
        // сохранить координаты карты
        updateCoords: function(left, top) {
            var $this = this;
            this.options.model.left = left;
            this.options.model.top = top;
            $.ajax({
                url: app.context + "/cards/update/coords",
                type: "POST",
                cache: !app.debug,
                dataType: "json",
                data: {
                    spaceId: 1,
                    cardId: $this.options.model.cardId,
                    left: left,
                    top: top
                },
                success: function(data) {}
            });
        },
        // сохранить размеры карты
        updateSize: function(width, height) {
            var $this = this;
            this.options.model.width = width;
            this.options.model.height = height;
            $.ajax({
                url: app.context + "/cards/update/size",
                type: "POST",
                cache: !app.debug,
                dataType: "json",
                data: {
                    spaceId: 1,
                    cardId: $this.options.model.cardId,
                    width: width,
                    height: height
                },
                success: function(data){}
            });
        },
        updateZIndex: function(zIndex) {
            var $this = this;
            $this.options.model.zIndex = zIndex;
            $this.element.css("zIndex", zIndex);
            $.ajax({
                url: app.context + "/cards/update/z_index",
                type: 'post',
                dataType: 'json',
                cache: !app.debug,
                data: {
                    spaceId: 1,
                    cardId: $this.options.model.cardId,
                    zIndex: zIndex
                },
                success: function(data){}
            });
        },
        
        showAllMemas: function() {
            var memas = this.options.model.memas;
            for (var i=0; i<memas.length; i++) {
                memas[i].show();
                if (i > 0) {
                    memas[i].moveAfter(memas[i-1]);
                }
            }
        },
        
        showTodayMemas: function () {
            var memas = this.options.model.memas;
            for (var i=0; i<memas.length; i++) {
                var nowDate = Date.parse(new Date().toDateString());
                var memaDate = Date.parse(new Date(memas[i].created).toDateString());
                if (nowDate == memaDate) {
                    memas[i].show();
                    if (i > 0) {
                        memas[i].moveAfter(memas[i-1]);
                    }
                } else {
                    memas[i].hide();
                }
            }
        },
        
        showCustomMemas: function () {
            
        },
        
        // автоматический подгон размеров внутренних блоков под размеры карты
        _autoSizeInnerElement: function() {
            this.element.find(".memas").height(this.element.height() - 135);
            this.element.find(".form_add_mema textarea").height(this.element.height() - 195);
            this.element.find(".form_add_mema textarea").width(this.element.width() - 50);
            this.element.find(".form_add_mema textarea ~span").width(this.element.width() - 40);
            this.element.find(".form_add_mema input").width(this.element.width() - 108);
        }
    });
})(jQuery);
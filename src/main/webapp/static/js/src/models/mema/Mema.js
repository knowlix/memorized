var Mema = $.Class.create({
	
	initialize: function(data, params) {
		$.extend(this, data);
		var params = params || {};
		$.extend(this, params);
	},
	
	/*
	 * Добавление мемы в DOM
	 * */
	show: function() {
	    if (typeof this.element != 'undefined') {return;}
    	var html = new EJS({url: app.templatesPath + '/mema', cache: !app.debug}).render(this);
    	var card = this.getCard(); 
        var ol = card.find('.memas');
        ol.prepend(html);
        this.bindEvents();
	},
	
	/*
	 * Скрытие мемы с карты
	 * */
	hide: function () {
	    this.element.remove();
	    delete this.element;
	},

	/*
	 * Возвращает массив элементов мемы
	 * [0] - dt
	 * [1] - dd
	 * */
    getElement: function() {
        /*неверное получение элемента, возможно совпадение идентификаторов мем*/
        //if (typeof this.element == 'undefined') {
            this.element = $('#'+app.memaIdPrefix+this.memaId);
        //}
        return this.element;
    },
    
    /*
     * Возвращает элемент карты, в которой находится текущая мема
     * */
    getCard: function() {
    	return $("#" + app.cardIdPrefix+this.card_id);
    },
    
    /*
     * Инициализируем мему в DOM дереве.
     * */
    bindEvents: function(model) {
        var $this = this;
        if (typeof model!= 'undefined') {
        	this.card = model;
        }
        this.element = this.getElement();
        this.element.find('.title').bind('click', function() {
            $this.toggleDescription();
        });
        this.element.bind('mouseenter', function() {
        	$this.showEditBar();
        });
        this.element.bind('mouseleave', function() {
        	$this.hideEditBar();
        });
    },
    
    /**
     * Появление панели редактирования мемы.
     * */
    showEditBar: function() {
    	var $this = this;
    	var editBar = document.createElement("div");
    	editBar.setAttribute("class", "editBar");
    	editBar.setAttribute("style", "display: inline; padding-left: 10px;");
    	this.element.find(".title").append(editBar);
    	
    	var deleteButton = document.createElement("span");
    	deleteButton.setAttribute("style", "height: 15px;width: 30px;padding: 0 5px;border-radius: 15px; background-color: #6aa5c9; color: #fff !important;");
    	$(deleteButton).html("x");
    	$(deleteButton).bind('click', function () {
    		$this.remove();
    	});
    	
    	var editButton = document.createElement("span");
    	editButton.setAttribute("style", "height: 15px;width: 30px;padding: 0 5px;border-radius: 15px; background-color: #6aa5c9; color: #fff !important;");
    	$(editButton).html("e");
    	$(editButton).bind("click", function() {
    	    $this.edit();
    	});
    	
    	editBar.appendChild(deleteButton);
    	editBar.appendChild(editButton);
    },
    
    remove: function() {
    	this.element.remove();
    	delete this.element;
    	this.card.removeMema(this.position);
    	$.ajax({
    		url: app.context + "/memas/delete",
    		type: 'POST',
    		dataType: 'json',
    		cache: !app.debug,
    		data: {
    			memaId: this.memaId
    		},
    		success: function(data) {}
    	});
    },
    
    edit: function () {
        this.card.widget.GuideWidget('addMema', this.memaId, this.title, this.description);
        this.card.widget.DictionaryWidget('addMema', this.memaId, this.title, this.description);
    },
    
    update: function() {
        $this = this;
        $.ajax({
            url: app.context + "/memas/update",
            data: {
                memaId: $this.memaId,
                title: $this.title,
                description: $this.description
            },
            type: 'post',
            dataType: 'json',
            cache: !app.debug,
            success: function (data) {
                $this.element.find(".title").html(data.title);
                $this.element.find(".description").html(data.description);
            }
        });
    },
    
    /**
     * Удаление панели редактирования мемы.
     * */
    hideEditBar: function() {
    	this.element.find('.editBar').remove();
    },
    
    /*
     * Отображение/скрытие описания мемы.
     * */
    toggleDescription: function() {
        this.element.siblings().find(".description").slideUp(app.effectDuration);
        this.element.find('.description').slideToggle(app.effectDuration);
    },
    
    /*
     * Перемещение мемы после указанной позиции.
     * */
    moveAfter: function(target) {
        this.element.detach().insertAfter(target.element);
    },
    
    /*
     * Перемещение мемы до указанной позиции.
     * */
    moveBefore: function(position) {
        var target = (typeof position != 'object' ? $('#mema'+position) : position);
        this.detach().insertBefore(target);
    },
    
    save: function(){
    	$this = this;
    	this.cardId = this.cardId || console.error('Не указан идентификатор карты');
    	this.title = this.title || console.error('Не указан заголовок мемы');
    	this.description = this.description || "";
    	$.post(app.context + "/memas/add", {
				cardId: $this.cardId,
				title: $this.title,
				description: $this.description
			},
			function (response) {
				$.extend($this, response);
				$this.show();
			}
		);
    }
	
});
/**
 * 
 */
var Card = $.Class.create({
	
	initialize: function(data) {
	    data = data || {};
	    $.extend(this, data);
		for (var i = 0; i < data.memas.length; i++) {
		    this.memas[i] = new Mema(data.memas[i], {
		    	card: this,
		        position: i
		    });
		}
	},

	/**
	 * Отображает карту в UI.
	 * */
    show: function(space) {
        var space = space || $('.space');
        var newCard = $("<div></div>");
        if (typeof newCard[this.type + "Widget"] == 'undefined') {
            console.error("Виджет карты '" + this.type + "Widget" + "' неизвестен");
            return;
        }
        space.append(newCard);
        this.widget = newCard[this.type + "Widget"]({
            model: this
        });
    },
        
    /**
     * Удаляет карту с UI.
     * */
    hide: function(space) {
    	space = space || $('.space');
    	this.widget.DictionaryWidget("destroy");
    	this.widget.remove();
    },
            
    // возвращает случайную мему из карты
    getRandomMema: function() {
        return this.memas[Math.floor(Math.random() * this.memas.length)];
    },
    
    // возвращает мему по её позиции на карте, начиная с 0
    getMemaByPosition: function(position) {
        return this.memas[position];
    },
    
    /*
     * Получение мемы по идентификатору.
     * */
    getMemaById: function(memaId) {
    	for (var i = 0; i < this.memas.length; i++) {
    		if (this.memas[i].memaId == memaId) {
    			return this.memas[i];
    		}
    	}
    	return null;
    },
    
    /*
     * Удаление мемы из карты по идентификатору.
     * */
    removeMema: function(position) {
    	this.memas.splice(position, 1);
    	for (var i=0; i < this.memas.length; i++) {
    		this.memas[i].position = i;
    	}
    }
    
});
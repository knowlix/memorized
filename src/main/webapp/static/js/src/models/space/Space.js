/**
 * 
 */
var Space = $.Class.create({
	
	initialize: function (element, data) {
	    this.element = element;
	    $.extend(this, data);
		this.cards = data.cards || [];
	},
	
	/**
	 * вывод карт на экран
	 * */
	show: function () {
	    for (i = 0; i < this.cards.length; i++) {
	        this.cards[i].show(this.element);
	        this.cards[i].space = this;
	    } 
	},
	
	/**
	 * формирование карт
	 * */
    load: function (factory) {
        var factory =  factory || new CardFactory();
        $.extend(this.cards, factory.getCardsOnSpace(this.title));
        return this;
    },
    
    getCardById: function (cardId) {
    	for (var i=0; i < this.cards.length; i++) {
    		if (this.cards[i].cardId == cardId) {
    			return this.cards[i];
    		}
    	}
    	return null;
    },
    
    /**
     * Возвращает true, если card уже есть в пространстве.
     * */
    contains: function (card, update) {
        var update = update || false;
    	for (var i=0; i < this.cards.length; i++) {
    		if (this.cards[i].cardId == card.cardId) {
    		    if (update) {
    		        $.extend(this.cards[i], card);
    		    }
    			return true;
    		}
    	}
    	return false;
    },
        
    deleteCard: function (card) {
    	var newCards = [];
    	for (var i=0; i < this.cards.length; i++) {
    		if (this.cards[i].cardId != card.cardId) {
    			newCards.push(this.cards[i]);
    		} 
    	}
    	this.cards = newCards;
    },
    
    addToSpace: function (card) {
    	var $this = this;
    	$.ajax({
    		url: app.context + '/cards/addtospace',
    		type: 'POST',
    		cache: !app.debug,
    		dataType: 'json',
    		data: {
    			cardId: card.cardId,
    			spaceId: this.spaceId
    		},
    		success: function(data) {    			
    			if (!$this.contains(card, true)) {
    			    c = card.getComplex();
    			    c.space = $this;
    			    $this.cards.push(c);
    			} else {
    			    c = $this.getCardById(card.cardId);
    			}
    			c.show();
    		}
    	});
    },
    
    deleteFromSpace: function (card) {
    	if (!this.contains(card)) {
    		console.error("Карта с id=" + card.cardId + " в пространстве не найдена");
    	}
    	var $this = this;
    	var card = this.getCardById(card.cardId);
    	$.ajax({
    		url: app.context + '/cards/deletefromspace',
    		type: 'POST',
    		cache: !app.debug,
    		dataType: 'json',
    		data: {
    			spaceId: this.spaceId,
    			cardId: card.cardId
    		},
    		success: function(data) {
    			card.hide();
    		}
    	});
    },
    
    getMaxZIndex: function() {
        var maxZIndex = 0;
        for (var i=0; i<this.cards.length; i++) {
            if (this.cards[i].zIndex > maxZIndex) {
                maxZIndex = this.cards[i].zIndex;
            }
        }
        return maxZIndex;
    }
	
});
/**
 * Фабрика для работы с картами
 */

CardFactory = $.Class.create({
    
    initialize: function(cards) {
        this.cards = [];
        var cards = cards || [];
        this.syncronize(cards);
    },
    
    getCardById: function (cardId) {
    	for (var i=0; i < this.cards.length; i++) {
    		if (this.cards[i].cardId == cardId) {
    			return this.cards[i];
    		}
    	}
    	return null;
    },
    
    getAvaiable: function() {
        $this = this;
        $.ajax({
            url: app.context + '/cards/avaiable',
            type: 'POST',
            cache: !app.debug,
            dataType: 'json',
            async: false,
            success: function(data) {
                for (var i=0; i < data.length; i++) {
                    $this.syncronize(new EasyCard(data[i]));
                }
            }
        });
        return this.cards;
    },
    
    /**
     * Добавление в коллекцию новые карты, только если их нет ещё.
     * */
    syncronize: function(cards) {
        var isEquals = false;
        if (typeof cards.cardId != 'undefined') var cards = [cards];
        for (var i = 0; i < cards.length; i++) {
            for (var j = 0; j < this.cards.length; j++) {
                if (this.cards[j].cardId == cards[i].cardId) {
                    isEquals = true;
                    break;
                } else {
                    isEquals = false;
                }
            }
            if (!isEquals) {
                this.cards.push(new EasyCard(cards[i]));
            }
        }
    },
    
    getCardsOnSpace: function(space) {
        var result = [];
        var $this = this;
        $.ajax({
            url: app.context + '/cards/show',
            data: {
                title: space
            },
            type: 'POST',
            async: false,
            cache: !app.debug,
            dataType: 'json',
            success:  function (data) {
                for (var i = 0; i < data.length; i++) {
                    result.push(new EasyCard(new Card($.extend(data[i].card, {
                        left: data[i].left,
                        top: data[i].top,
                        height: data[i].height,
                        width: data[i].width,
                        zIndex: data[i].zIndex
                    }))));
                }
                $.extend($this.cards, result);
            }
        });
        return result;
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
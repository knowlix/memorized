/**
 * Упрощенный объект карты 
 */
EasyCard = $.Class.create({
    
    initialize: function(card){
        $.extend(this, card);
    },
    
    /**
     * Преобразует текущий объект EasyCard в Card.
     * */
    getComplex: function() {
    	var $this = this;
        if (typeof this.description == 'undefined') {
            $.ajax({
                url: app.context + '/cards/complex',
                data: {
                    spaceId: 1,
                    cardId: $this.cardId
                },
                dataType: 'json',
                type: 'POST',
                cache: !app.debug,
                async: false,
                success: function(data){
                    $this = new Card($.extend(data.card, {
                        left: data.left,
                        top: data.top,
                        height: data.height,
                        width: data.width,
                        zIndex: data.zIndex
                    }));
                }
            });
        }
        return $this;
    }
    
});
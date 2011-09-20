$(function() {
    
    function r() {
        var height = $("#wrapper").height() - $("footer").height() - $("header").height();
        $('ul.cards').height(height - 40);
        $('.space').height(height - 100);
    }
    $(window).resize(function(){
        r();
    });
    $(window).resize();    
    
	var space = new Space($('.space'),{spaceId: 1, title: 'default'});
	var cardFactory = new CardFactory();
	space.load(cardFactory).show();
	var cardList = cardFactory.getAvaiable();
	for (var i = 0; i < cardList.length; i++) {
	    var card = cardList[i];
	    $('ul.cards').append('<li id="ac'+ card.cardId +'" ' + (space.contains(card) ? 'class="selected"' : '') + '><a href="#" onclick="return false;">'+ card.title +'</a></li>');
	    $('li#ac' + card.cardId + ' a').bind('click', function() {
	    	var cardId = $(this).parent("li").attr("id").replace(/^ac/, "");
	    	var card = cardFactory.getCardById(cardId);
	    	if (card == null) console.error("Карта с идентификатором " + cardId + " не найдена");
	    	if ($(this).parent("li").attr("class") == 'selected') {
	    		space.deleteFromSpace(card);
	    		$(this).parent("li").removeClass("selected");
	    	} else {
	    		space.addToSpace(card);
	    		$(this).parent("li").addClass("selected");
	    	}
	    });
	}
	
	Cufon.replace($(".main_menu li"), {
        fontFamily: "Tahoma"
    });

	   Cufon.replace($("h2"), {
	        fontFamily: "Tahoma"
	    });
	
	/*
	$("#spacecards li").each(function(key, value) {
		var card = $(this).find("span").attr("class");
		if ($("#" + card).length) {
			$(this).addClass("active");
		}
	});
	*/
});
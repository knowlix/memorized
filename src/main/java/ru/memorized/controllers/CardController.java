package ru.memorized.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ru.memorized.data.entity.Card;
import ru.memorized.data.entity.Space;
import ru.memorized.data.entity.SpaceCard;
import ru.memorized.services.cards.CardService;
import ru.memorized.services.cardtype.CardTypeService;
import ru.memorized.services.spacecards.SpaceCardService;
import ru.memorized.services.spaces.SpaceService;

@Controller
@RequestMapping("/cards")
public class CardController {

    @Autowired(required = true)
    private CardService cardService;

    @Autowired(required = true)
    private SpaceService spaceService;

    @Autowired(required = true)
    private SpaceCardService spaceCardService;

    @Autowired
    private CardTypeService cardTypeService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("cardsIndexPage", "card",
                new Card());
        view.addObject("cards", cardService.getCards());
        view.addObject("cardTypes", cardTypeService.getCardTypes());
        return view;
    }

    @RequestMapping("/show")
    public @ResponseBody List<SpaceCard> show(@RequestParam("title") String title) {
        Space space = spaceService.getSpace(title);
        return spaceCardService.getSpaceCard(space, Boolean.TRUE);
    }

    @RequestMapping(value="/complex", method=RequestMethod.POST)
    public @ResponseBody SpaceCard getById(@RequestParam("spaceId") int spaceId, @RequestParam("cardId") int cardId) {
        return spaceCardService.getSpaceCard(spaceService.getSpace(spaceId), cardService.getCard(cardId));
    }

    /**
     * Возвращает список доступных карт для отобраения.
     *
     * @return список доступных карт
     *
     * */
    @RequestMapping(value="/avaiable", method=RequestMethod.POST)
    public @ResponseBody List<EasyCard> getAvaiableCards() {
        List<EasyCard> result = new ArrayList<EasyCard>();
        List<Card> cards = cardService.getCards();
        for (Card card : cards) {
            result.add(new EasyCard(card));
        }
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showCardForm() {
        ModelAndView view = new ModelAndView("cardFormPage", "card", new Card());
        return view;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCard(@ModelAttribute("card") Card card) {
        // card.setType("Dictionary");
        cardService.saveCard(card);
        return "redirect:/cards/";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String removeCard(@RequestParam int cardId) {
        cardService.removeCard(cardService.getCard(cardId));
        return "redirect:/cards/";
    }

    /**
     * Добавление карты в пространство.
     * @param cardId
     * 			- идентификатор добавляемой карты
     * @param spaceId
     * 			- идентификатор пространства, куда добавляется карта
     * @return объект SpaceCard {@link ru.memorized.data.entity.SpaceCard}, добавленной карты
     * */
    @RequestMapping(value = "/addtospace", method = RequestMethod.POST)
    public @ResponseBody SpaceCard addToSpace(
            @RequestParam("cardId") Integer cardId,
            @RequestParam("spaceId") Integer spaceId) {
        SpaceCard spaceCard = spaceCardService.getExistsSpaceCardOrCreateNew(spaceService.getSpace(spaceId),
                cardService.getCard(cardId));
        spaceCardService.enableSpaceCard(spaceCard);
        return spaceCard;
    }

    /**
     * Удаление карты с пространства.
     * @param cardId
     * 			- идентификатор добавляемой карты
     * @param spaceId
     * 			- идентификатор пространства, куда добавляется карта
     * @return объект SpaceCard {@link ru.memorized.data.entity.SpaceCard}, удаленной карты
     * */
    @RequestMapping(value = "/deletefromspace", method = RequestMethod.POST)
    public @ResponseBody SpaceCard deleteFromSpace (
            @RequestParam("spaceId") int spaceId,
            @RequestParam("cardId") int cardId) {
        SpaceCard spaceCard = spaceCardService.getSpaceCard(
                spaceService.getSpace(spaceId), cardService.getCard(cardId));
        spaceCardService.disableSpaceCard(spaceCard);
        return spaceCard;
    }

    /**
     * Обновление свойства z-index карты.
     *
     * @param cardId
     *            - идентификатор добавляемой карты
     * @param spaceId
     *            - идентификатор пространства, куда добавляется карта
     * @param zIndex
     *            - значение свойства z-index
     * @return объект SpaceCard {@link ru.memorized.data.entity.SpaceCard},
     *         обновленной карты
     *
     * */
    @RequestMapping(value = "/update/z_index", method = RequestMethod.POST)
    public @ResponseBody
    SpaceCard updateZIndex(@RequestParam("spaceId") int spaceId,
            @RequestParam("cardId") int cardId,
            @RequestParam("zIndex") int zIndex) {
        SpaceCard spaceCard = spaceCardService.getSpaceCard(
                spaceService.getSpace(spaceId), cardService.getCard(cardId));
        spaceCard.setzIndex(zIndex);
        spaceCardService.updateSpaceCard(spaceCard);
        return spaceCard;
    }

    /**
     * Обновление координат карты с пространства.
     * @param cardId
     *          - идентификатор добавляемой карты
     * @param spaceId
     *          - идентификатор пространства, куда добавляется карта
     * @param left
     *          - x координата карты
     * @param top
     *          - y координата карты
     * @return объект SpaceCard {@link ru.memorized.data.entity.SpaceCard}, обновленной карты
     * */
    @RequestMapping(value = "/update/coords", method = RequestMethod.POST)
    public @ResponseBody SpaceCard updateCoords(@RequestParam("spaceId") int spaceId,
            @RequestParam("cardId") int cardId, @RequestParam("left") int left,
            @RequestParam("top") int top) {
        SpaceCard spaceCard = spaceCardService.getSpaceCard(spaceService.getSpace(spaceId), cardService.getCard(cardId));
        spaceCard.setLeft(left);
        spaceCard.setTop(top);
        spaceCardService.updateSpaceCard(spaceCard);
        return spaceCard;
    }

    /**
     * Обновление размеров карты с пространства.
     * @param cardId
     *          - идентификатор добавляемой карты
     * @param spaceId
     *          - идентификатор пространства, куда добавляется карта
     * @param height
     *          - высота карты
     * @param width
     *          - ширина карты
     * @return объект SpaceCard {@link ru.memorized.data.entity.SpaceCard}, обновленной карты
     * */
    @RequestMapping(value = "/update/size", method = RequestMethod.POST)
    public @ResponseBody SpaceCard updateSize(@RequestParam("spaceId") int spaceId,
            @RequestParam("cardId") int cardId, @RequestParam("height") int height,
            @RequestParam("width") int width) {
        SpaceCard spaceCard = spaceCardService.getSpaceCard(spaceService.getSpace(spaceId), cardService.getCard(cardId));
        spaceCard.setHeight(height);
        spaceCard.setWidth(width);
        spaceCardService.updateSpaceCard(spaceCard);
        return spaceCard;
    }

    /**
     * Структура для передачи инфорции для списка карт, которые могут быть отображены в пространстве.
     *
     * @author Evgenij Kozhevnikov
     *
     * */
    private static class EasyCard {

        private int cardId;

        private String title;

        public EasyCard(Card card) {
            cardId = card.getCardId();
            title = card.getTitle();
        }

        @SuppressWarnings("unused")
		public int getCardId() {
            return cardId;
        }

        @SuppressWarnings("unused")
		public void setCardId(int cardId) {
            this.cardId = cardId;
        }

        @SuppressWarnings("unused")
		public String getTitle() {
            return title;
        }

        @SuppressWarnings("unused")
		public void setTitle(String title) {
            this.title = title;
        }

    }

}
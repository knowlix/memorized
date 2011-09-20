package ru.memorized.data.dao.card;

import java.util.List;

import ru.memorized.data.entity.Card;

public interface IDAOCard {

    public static final String TABLE_NAME = "Card";

    /**
     * Получение списка всез карт.
     * 
     * @return все карты, доступные пользователю
     * 
     * */
    public List<Card> getCards();

    /**
     * Получение объекта карты по идентификатору.
     * 
     * @param cardId
     *            идентификатор карты
     * @return объект карты
     * 
     * */
    public List<Card> getCards(int cardId);

    /**
     * Сохранение карты в базе.
     * 
     * */
    public void saveCard(Card card);

    /**
     * Удаление карты.
     * */
    public void removeCard(Card card);

}

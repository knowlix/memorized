package ru.memorized.services.cards;

import java.util.List;

import ru.memorized.data.entity.Card;

public interface CardService {

    public List<Card> getCards();

    public Card getCard(int cardId);

    public void saveCard(Card card);

    public void removeCard(Card card);

}
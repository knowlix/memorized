package ru.memorized.services.cardtype;

import java.util.List;

import ru.memorized.data.entity.CardType;

public interface CardTypeService {

    /**
     * @return список всех типов карт.
     * */
    List<CardType> getCardTypes();

}

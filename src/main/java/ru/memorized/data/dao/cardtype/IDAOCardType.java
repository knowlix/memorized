package ru.memorized.data.dao.cardtype;

import java.util.List;

import ru.memorized.data.entity.CardType;

public interface IDAOCardType {

    static final String TABLE_NAME = "CardType";

    /**
     * Получение всех типов карт.
     *
     * @return список типов карт.
     * */
    List<CardType> getCardTypes();

}

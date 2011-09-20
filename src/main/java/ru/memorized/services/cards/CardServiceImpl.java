package ru.memorized.services.cards;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.memorized.data.dao.card.IDAOCard;
import ru.memorized.data.entity.Card;
import ru.memorized.services.memas.MemaService;
import ru.memorized.services.spacecards.SpaceCardService;

@Service
@Transactional(readOnly = false)
public class CardServiceImpl implements CardService {

    @Autowired
    private IDAOCard dao;

    @Autowired
    private SpaceCardService spaceCardService;

    @Autowired
    private MemaService memaService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Card> getCards() {
        return dao.getCards();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCard(Card card) {
        dao.saveCard(card);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Card getCard(int cardId) {
        return dao.getCards(cardId).get(0);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeCard(Card card) {
        dao.removeCard(card);
    }

}
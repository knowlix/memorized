package ru.memorized.data.dao.card;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import ru.memorized.data.entity.Card;

@Repository
public class DAOCard extends HibernateDaoSupport implements IDAOCard {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @PostConstruct
    @SuppressWarnings("unused")
    private void init() {
        setSessionFactory(sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Card> getCards() {
        List<Card> cards = null;
        try {
            cards = createHibernateTemplate(sessionFactory).find(
                    "from " + TABLE_NAME + " where author = ?",
                    SecurityContextHolder.getContext().getAuthentication()
                            .getName());
        } catch (DataAccessException e) {
            // TODO: logger
            e.printStackTrace();
        }

        return cards;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Card> getCards(int cardId) {
        List<Card> cards = null;
        try {
            cards = createHibernateTemplate(sessionFactory).find(
                    "from " + TABLE_NAME + " where card_id=? and author=?",
                    cardId,
                    SecurityContextHolder.getContext().getAuthentication()
                            .getName());
        } catch (DataAccessException e) {
            // TODO: logger
            e.printStackTrace();
        }
        return cards;
    }

    @Override
    public void saveCard(Card card) {
        if (card.getAuthor() == null) {
            card.setAuthor(SecurityContextHolder.getContext()
                    .getAuthentication().getName());
        }
        try {
            getHibernateTemplate().save("Card", card);
        } catch (DataAccessException e) {
            // TODO: logger
            e.printStackTrace();
        }
    }

    @Override
    public void removeCard(Card card) {
        try {
            getHibernateTemplate().delete(card);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}

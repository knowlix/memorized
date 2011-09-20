package ru.memorized.data.dao.cardtype;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import ru.memorized.data.entity.CardType;

@Repository
public class DAOCardType extends HibernateDaoSupport implements IDAOCardType {

    @Autowired
    private SessionFactory sessionFactory;

    @PostConstruct
    @SuppressWarnings("unused")
    private void init() {
        setSessionFactory(sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CardType> getCardTypes() {
        List<CardType> cardTypes = new ArrayList<CardType>();
        cardTypes.addAll(getSession().createCriteria(CardType.class)
                .addOrder(Order.asc("cardTypeId")).list());
        return cardTypes;
    }

}

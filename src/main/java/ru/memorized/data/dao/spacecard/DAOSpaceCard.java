package ru.memorized.data.dao.spacecard;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import ru.memorized.data.entity.Card;
import ru.memorized.data.entity.Space;
import ru.memorized.data.entity.SpaceCard;

@Repository
public class DAOSpaceCard extends HibernateDaoSupport implements IDAOSpaceCard {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @PostConstruct
    @SuppressWarnings("unused")
    private void init() {
        setSessionFactory(sessionFactory);
    }

    @Override
    public void saveSpaceCard(SpaceCard spaceCard) {
        try {
            getHibernateTemplate().save(spaceCard);
        } catch (DataAccessException e) {
            // TODO: logger
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSpaceCard(SpaceCard spaceCard) {
        try {
            getHibernateTemplate().delete(spaceCard);
        } catch (DataAccessException e) {
            // TODO: logger
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Scope("request")
    public SpaceCard getSpaceCard(Space space, Card card) {
        List<SpaceCard> result = getHibernateTemplate().find(
                "from " + TABLE_NAME + " where space = ? and card = ?", space, card);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SpaceCard> getSpaceCard(Space space) {
        return getHibernateTemplate().find(
                "from " + TABLE_NAME + " where space = ?", space);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SpaceCard> getSpaceCard(Space space, Boolean enabled) {
        String author = SecurityContextHolder.getContext().getAuthentication()
                .getName();
        return getHibernateTemplate()
                .find("from " + TABLE_NAME + " where space = ? and enabled = ? and card.author = ?",
                        space, enabled, author);
    }

    @Override
    public void updateSpaceCard(SpaceCard spaceCard) {
        getHibernateTemplate().saveOrUpdate(spaceCard);
    }

}

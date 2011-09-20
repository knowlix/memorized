package ru.memorized.data.dao.space;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import ru.memorized.data.entity.Space;

@Repository
public class DAOSpace extends HibernateDaoSupport implements IDAOSpace {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @PostConstruct
    @SuppressWarnings("unused")
    private void initFactory() {
        setSessionFactory(sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Space> getSpaces() {
        return getHibernateTemplate().find("from " + TABLE_NAME);
    }

    @Override
    public Space getSpaces(int spaceId) {
        Space space = null;
        try {
            space = getHibernateTemplate().get(Space.class, spaceId);
        } catch (DataAccessException e) {
            // TODO: logger
            e.printStackTrace();
        }
        return space;
    }

    @Override
    public Space getSpace(String title) {
        Space space = null;
        try {
            space = (Space) getHibernateTemplate().find(
                    " from " + TABLE_NAME + " where title=?", title).get(0);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return space;
    }

}

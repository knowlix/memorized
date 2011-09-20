/**
 * DAO мем
 *
 * @author Evgenij_Kozhevnikov
 *
 * 10.03.2011
 *
 * */
package ru.memorized.data.dao.mema;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import ru.memorized.data.entity.Mema;

@Repository
public class DAOMema extends HibernateDaoSupport implements IDAOMema {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @PostConstruct
    @SuppressWarnings("unused")
    private void initFactory() {
        setSessionFactory(sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Mema> getMemas() {
        List<Mema> memas = null;
        try {
            memas = getHibernateTemplate().find("from " + TABLE_NAME);
        } catch (DataAccessException e) {
            // TODO: logger
            e.printStackTrace();
        }
        return memas;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Mema> getMemas(int cardId) {
        List<Mema> memas = null;
        try {
            memas = getHibernateTemplate().find(
                    "from " + TABLE_NAME + " where card_id = ?", cardId);
        } catch (DataAccessException e) {
            // TODO: logger
            e.printStackTrace();
        }
        return memas;
    }

    @Override
    public Long saveMema(Mema mema) {
        Long memaId = null;
        try {
            memaId = (Long) getHibernateTemplate().save("Mema", mema);
        } catch (DataAccessException e) {
            // TODO: logger
            e.printStackTrace();
        }
        return memaId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Mema> getMemas(int cardId, Date date) {
        List<Mema> memas = null;
        try {
            memas = getHibernateTemplate()
                    .find("from " + TABLE_NAME
                            + " where card_id = ? and created > ?", cardId,
                            date);
        } catch (DataAccessException e) {
            // TODO: logger
            e.printStackTrace();
        }
        return memas;
    }

	@Override
	public void deleteMema(Mema mema) {
		try {
            createHibernateTemplate(sessionFactory).delete(mema);
		} catch (DataAccessException e) {
			//TODO: logger
			e.printStackTrace();
		}
	}

	@Override
	public Mema getMema(Long memaId) {
		Mema mema = null;
		try {
			mema = getHibernateTemplate().get(Mema.class, memaId);
		} catch (DataAccessException e) {
			//TODO: logger
			e.printStackTrace();
		}
		return mema;
	}

    @Override
    public void deleteAllMemas(Set<Mema> memas) {
        try {
            createHibernateTemplate(sessionFactory).deleteAll(memas);
        } catch (DataAccessException e) {
            // TODO: logger
            e.printStackTrace();
        }
    }

    @Override
    public void updateMema(Mema mema) {
        try {
            getHibernateTemplate().update(mema);
        } catch (DataAccessException e) {
            // TODO: logger
            e.printStackTrace();
        }
    }

}

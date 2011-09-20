/**
 *
 */
package ru.memorized.data.dao.userinfo;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import ru.memorized.data.entity.UserInfo;

/**
 * @author Evgenij_Kozhevnikov
 *
 */
@Repository
public class DAOUserInfo extends HibernateDaoSupport implements IDAOUserInfo {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @PostConstruct
    @SuppressWarnings("unused")
    private void init() {
        setSessionFactory(sessionFactory);
    }

    /* (non-Javadoc)
     * @see ru.memorized.data.dao.userinfo.IDAOUserInfo#getByUsername(java.lang.String)
     */
    @Override
    public UserInfo getByUsername(String username) {
        return (UserInfo) getHibernateTemplate().find(
                "from " + TABLE_NAME + " where username=?", username).get(
                0);
    }

    /* (non-Javadoc)
     * @see ru.memorized.data.dao.userinfo.IDAOUserInfo#saveOrUpdate(ru.memorized.data.entity.UserInfo)
     */
    @Override
    public void saveOrUpdate(UserInfo userInfo) {
        getHibernateTemplate().saveOrUpdate(TABLE_NAME, userInfo);
    }

}

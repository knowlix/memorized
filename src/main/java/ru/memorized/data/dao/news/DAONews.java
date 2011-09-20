package ru.memorized.data.dao.news;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import ru.memorized.data.entity.News;

@Repository
public class DAONews extends HibernateDaoSupport implements IDAONews {

    @Autowired
    private SessionFactory sessionFactory;

    @PostConstruct
    @SuppressWarnings("unused")
    private void initFactory() {
        setSessionFactory(sessionFactory);
    }

    @Override
    public List<News> getNews() {
        @SuppressWarnings("unchecked")
        List<News> news = getHibernateTemplate().find(" from " + TABLE_NAME);
        return news;
    }

    @Override
    public List<News> getLastNews(int count) {
        @SuppressWarnings("unchecked")
        List<News> news = getSession().createCriteria(News.class)
                .addOrder(Order.asc("newsId")).setMaxResults(count).list();
        return news;
    }

}

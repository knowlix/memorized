package ru.memorized.services.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.memorized.data.dao.news.IDAONews;
import ru.memorized.data.entity.News;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class NewsServiceImpl implements NewsService {

    @Autowired
    private IDAONews dao;

    public NewsServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<News> getNews() {
        return dao.getNews();
    }

    @Override
    public List<News> getLastNews(int count) {
        return dao.getLastNews(count);
    }

}

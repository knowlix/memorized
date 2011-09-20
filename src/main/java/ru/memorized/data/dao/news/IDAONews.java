package ru.memorized.data.dao.news;

import java.util.List;

import ru.memorized.data.entity.News;

public interface IDAONews {

    public static final String TABLE_NAME = "News";

    public List<News> getNews();

    public List<News> getLastNews(int count);

}

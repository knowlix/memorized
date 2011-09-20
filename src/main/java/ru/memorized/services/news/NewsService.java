package ru.memorized.services.news;

import java.util.List;

import ru.memorized.data.entity.News;

public interface NewsService {

    public List<News> getNews();

    public List<News> getLastNews(int count);

}

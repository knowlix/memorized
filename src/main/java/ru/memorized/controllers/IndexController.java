package ru.memorized.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ru.memorized.services.news.NewsService;

/**
 * @author Evgenij_Kozhevnikov
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/")
    public ModelAndView showAllPads() {
        ModelAndView view = new ModelAndView("indexPage");
        view.addObject("news", newsService.getLastNews(5));
        return view;
    }

}

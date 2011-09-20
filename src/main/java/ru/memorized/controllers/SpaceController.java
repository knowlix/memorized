package ru.memorized.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ru.memorized.data.entity.Space;
import ru.memorized.data.entity.SpaceCard;
import ru.memorized.services.spacecards.SpaceCardService;
import ru.memorized.services.spaces.SpaceService;

/**
 * Контроллер пространств
 *
 * @author Evgenij Kozhevnikov
 *
 * */
@Controller
@RequestMapping("/spaces")
public class SpaceController {

    @Autowired(required = true)
    private SpaceService spaceService;

    @Autowired(required = true)
    private SpaceCardService spaceCardService;

    @RequestMapping("/")
    public ModelAndView show() {
        List<SpaceCard> spaceCards = new ArrayList<SpaceCard>();
        ModelAndView view = new ModelAndView("spaceShowPage");
        List<Space> spaces = spaceService.getSpaces();
        for (Space space : spaces) {
            spaceCards.addAll(spaceCardService.getSpaceCard(space));
        }
        view.addObject("spaceCards", spaceCards);
        return view;
    }

}

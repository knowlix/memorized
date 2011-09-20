/**
 * Управление ajax запросами
 * 
 * @author Evgenij_Kozhevnikov
 * 
 * 10.03.2011
 * 
 * */
package ru.memorized.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ru.memorized.data.entity.Mema;
import ru.memorized.services.memas.MemaService;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired(required = true)
    private MemaService memaService;

    /**
     * Перемешивание мем на карты
     * 
     * @param cardId
     *            идентификатор карты
     * 
     * */
    @RequestMapping(value = "card/memas/shuffle", method = RequestMethod.POST)
    public ModelAndView shuffleCardMemas(@RequestParam("cardId") int cardId) {
        ModelAndView view = new ModelAndView("memas-on-card-ajax");
        List<Mema> memas = memaService.getMemas(cardId);
        Collections.shuffle(memas);
        view.addObject("memas", memas);
        return view;
    }

    @RequestMapping(value = "/card/memas/show", method = RequestMethod.POST)
    public ModelAndView showCardMemas(@RequestParam("cardId") int cardId,
            @RequestParam("date") String date) {
        ModelAndView view = new ModelAndView("memas-on-card-ajax");
        List<Mema> memas = null;
        if (date.equals("all")) {
            memas = memaService.getMemas(cardId);
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            if (date.equals("week")) {
                c.add(Calendar.DATE, -7);
            } else if (date.equals("today")) {
                c.add(Calendar.DATE, -1);
            }
            memas = memaService.getMemas(cardId, c.getTime());
        }
        Collections.reverse(memas);
        view.addObject("memas", memas);
        return view;
    }

    @RequestMapping("/forms/mema/add")
    public ModelAndView showFormAddMema() {
        ModelAndView view = new ModelAndView("form-add-mema-ajax");
        return view;
    }

}

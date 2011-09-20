/**
 * Контроллер мем
 *
 * @author Evgenij_Kozhevnikov
 *
 * 14.03.2011
 *
 * */
package ru.memorized.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.memorized.data.entity.Mema;
import ru.memorized.services.memas.MemaService;

@Controller
@RequestMapping("/memas")
public class MemaController {

    @Autowired(required = true)
    private MemaService service;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Mema addMema(@RequestParam("cardId") int cardId,
            @RequestParam("title") String title,
            @RequestParam("description") String description) {
        Mema mema = new Mema();
        mema.setCard_id(cardId);
        mema.setTitle(title);
        mema.setDescription(description);
        mema.setCreated(new Date());
        service.saveMema(mema);
        return mema;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    Mema updateMema(@RequestParam("memaId") Long memaId,
            @RequestParam("title") String title,
            @RequestParam("description") String description) {
        Mema mema = service.getMema(memaId);
        mema.setTitle(title);
        mema.setDescription(description);
        service.updateMema(mema);
        return mema;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Mema deleteMema(@RequestParam("memaId") Long memaId) {
    	Mema mema = service.getMema(memaId);
    	service.deleteMema(mema);
    	return mema;
    }

}

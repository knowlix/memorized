/**
 * Контроллер страниц ошибок
 * 
 * @author Evgenij Kozhevnikov
 * 
 * 26.02.2011
 * 
 * */
package ru.memorized.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/404")
    public String error404() {
        return "errorPage";
    }

}

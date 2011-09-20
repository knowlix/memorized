package ru.memorized.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ru.memorized.data.entity.UserInfo;
import ru.memorized.security.data.User;
import ru.memorized.services.userinfo.UserInfoService;

/**
 * Управление пользователями.
 *
 * @author Evgenij_Kozhevnikov
 *
 * */
@Controller
public class UserController {

    @Autowired
    private JdbcUserDetailsManager userManager;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SaltSource saltSource;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView view = new ModelAndView("loginPage");
		return view;
	}

	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public ModelAndView rigester() {
        ModelAndView view = new ModelAndView("registerFormPage", "userInfo", new UserInfo());
		return view;
	}

	@RequestMapping(value="/registration", method=RequestMethod.POST)
    public ModelAndView rigester(@RequestParam("username") String username,
            @RequestParam("password") String password,
            @ModelAttribute("userInfo") UserInfo userInfo) {

        User user = new User(username, password, true, true, true, true,
                new ArrayList<GrantedAuthority>());
        user.setPassword(passwordEncoder.encodePassword(password,
                saltSource.getSalt(user)));
        userManager.createUser(user);
        userManager.addUserToGroup(username, "administrators");
        userInfo.setUsername(username);
        userInfoService.saveOrUpdate(userInfo);
        return new ModelAndView("redirect:/");
	}

}

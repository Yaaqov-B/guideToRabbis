package com.springapp.rabbis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private MessageSource messageSource;
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model ,Locale locale) {
        String message = messageSource.getMessage("message.title", null, locale );
		model.addAttribute("message", message);
		return "hello";
	}
}
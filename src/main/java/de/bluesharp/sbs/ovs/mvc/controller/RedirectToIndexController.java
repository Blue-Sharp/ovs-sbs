package de.bluesharp.sbs.ovs.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectToIndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:/index.jsf";
	}
}

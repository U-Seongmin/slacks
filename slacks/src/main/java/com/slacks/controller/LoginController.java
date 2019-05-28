package com.slacks.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.slacks.exception.EmailPasswordNotMatchingException;
import com.slacks.service.UserService;
import com.slacks.utils.AuthInfoLogin;
import com.slacks.utils.LoginCommand;

@Controller
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm(LoginCommand loginCommand) throws Exception {
		ModelAndView mav = new ModelAndView("user/login/loginForm");
		return mav;
	}

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginSuccess(@Valid LoginCommand loginCommand, BindingResult bindingResult, HttpSession session,
			HttpServletResponse response) throws Exception {
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("user/login/loginForm");
			return mav;
		}

		try {
			AuthInfoLogin authInfoLogin = userService.loginAuth(loginCommand);
			session.setAttribute("authInfoLogin", authInfoLogin);
		} catch (EmailPasswordNotMatchingException e) {
			bindingResult.rejectValue("pw", "notMatch", "Email과 비밀번호가 맞지않습니다.");
			ModelAndView mav = new ModelAndView("user/login/loginForm");
			return mav;
		}
		ModelAndView mav = new ModelAndView("/user/login/loginSuccess");
		return mav;
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mav = new ModelAndView("redirect:/");
		return mav;
	}
}

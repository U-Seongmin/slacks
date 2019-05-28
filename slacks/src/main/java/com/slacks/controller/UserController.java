package com.slacks.controller;

import java.util.Random;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.slacks.exception.AlreadyExistingEmailException;
import com.slacks.exception.CodeNotMatchException;
import com.slacks.exception.EmailNotFoundException;
import com.slacks.service.UserService;
import com.slacks.utils.ChangePassword;
import com.slacks.utils.CheckEmail;
import com.slacks.utils.InsertCode;
import com.slacks.utils.RegisterRequest;
import com.slacks.vo.UserVO;

@Controller
public class UserController {

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping("/register/step2")
	public ModelAndView step2() throws Exception {
		ModelAndView mav = new ModelAndView("user/register/step2");
		mav.addObject("registerRequest", new RegisterRequest());
		return mav;
	}

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("/register/step3")
	public ModelAndView step3(@Valid RegisterRequest regReq, BindingResult bindingResult) throws Exception {

		// @Valid 검증
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("user/register/step2");
			return mav;
		}

		// 비밀번호 확인
		boolean check = regReq.isPwEqualToCheckPw();
		if (!check) {
			bindingResult.rejectValue("checkPw", "noMatch", "비밀번호를 확인해주세요.");
			ModelAndView mav = new ModelAndView("user/register/step2");
			return mav;
		}

		try {
			userService.register(regReq);
		} catch (AlreadyExistingEmailException e) {
			bindingResult.rejectValue("email", "duplicate", "이미 가입된 이메일입니다.");
			ModelAndView mav = new ModelAndView("user/register/step2");
			return mav;
		}
		ModelAndView mav = new ModelAndView("user/register/step3");
		return mav;
	}

	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
	public ModelAndView FindPassword() throws Exception {
		ModelAndView mav = new ModelAndView("user/password/checkEmail");
		mav.addObject("checkEmail", new CheckEmail());
		return mav;
	}

	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public ModelAndView sendMail(@Valid UserVO vo, final CheckEmail checkEmail, BindingResult bindingResult,
			HttpSession session, HttpServletResponse response, Errors errors) throws Exception {

		Random random = new Random();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			if (random.nextBoolean()) {
				buf.append((char) ((int) (random.nextInt(26)) + 97));
			} else {
				buf.append((random.nextInt(10)));
			}
		}

		final String htmlContent = buf.toString();

		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("user/password/checkEmail");
			mav.addObject("checkEmail", new CheckEmail());
			return mav;
		}

		try {
			userService.checkEmail(checkEmail);
		} catch (EmailNotFoundException e) {
			bindingResult.rejectValue("email", "unduplicate", "가입되지 않은 E-mail입니다.");
			ModelAndView mav = new ModelAndView("user/password/checkEmail");
			return mav;
		}

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

				helper.setFrom("tlsdydwns0126@gmail.com");
				helper.setTo(checkEmail.getEmail());
				helper.setSubject("제목");
				helper.setText(htmlContent, true);
			}
		};
		mailSender.send(preparator);

		session.setAttribute("code", htmlContent);
		session.setAttribute("email", checkEmail.getEmail());
		ModelAndView mav = new ModelAndView("user/password/insertCode");
		mav.addObject("insertCode", new InsertCode());
		return mav;
	}

	@RequestMapping(value = "/checkCode", method = RequestMethod.GET)
	public ModelAndView checkCode() throws Exception {
		ModelAndView mav = new ModelAndView("user/password/insertCode");
		mav.addObject("insertCode", new InsertCode());
		return mav;
	}

	@RequestMapping(value = "/checkCode", method = RequestMethod.POST)
	public ModelAndView checkCode(@Valid HttpSession session, InsertCode insertCode, BindingResult bindingResult,
			InsertCode inputCode) throws Exception {
		String checkCode = (String) session.getAttribute("code");

		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("user/password/insertCode");
			mav.addObject("insertCode", new InsertCode());
			return mav;
		}

		try {
			userService.checkCode(inputCode, checkCode);
		} catch (CodeNotMatchException e) {
			bindingResult.rejectValue("code", "nomatch", "코드가 일치하지 않습니다.");
			ModelAndView mav = new ModelAndView("user/password/insertCode");
			return mav;
		}

		ModelAndView mav = new ModelAndView("user/password/changePassword");
		mav.addObject("changePassword", new ChangePassword());
		return mav;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public ModelAndView changePassword() throws Exception {
		ModelAndView mav = new ModelAndView("user/password/changePassword");
		mav.addObject("changePassword", new ChangePassword());
		return mav;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ModelAndView changeSuccess(@Valid ChangePassword changePassword, BindingResult bindingResult,
			HttpSession session, HttpServletResponse response) throws Exception {

		String email = (String) session.getAttribute("email");
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("user/password/changePassword");
			mav.addObject("changePassword", new ChangePassword());
			return mav;
		}

		// 비밀번호 확인
		boolean check = changePassword.isPwEqualToCheckPw();
		if (!check) {
			ModelAndView mav = new ModelAndView("user/password/changePassword");
			mav.addObject("changePassword", new ChangePassword());
			return mav;
		}

		try {
			userService.changePassword(changePassword, email);
		} catch (AlreadyExistingEmailException e) {
			bindingResult.rejectValue("checkPw", "noMatch", "비밀번호를 확인해주세요.");
			ModelAndView mav = new ModelAndView("user/password/changePassword");
			return mav;
		}
		ModelAndView mav = new ModelAndView("user/password/changeSuccess");
		return mav;
	}
}

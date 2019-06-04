package com.slacks.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.slacks.dao.UserDAO;
import com.slacks.exception.AlreadyExistingEmailException;
import com.slacks.exception.CodeNotMatchException;
import com.slacks.exception.EmailNotFoundException;
import com.slacks.exception.EmailPasswordNotMatchingException;
import com.slacks.utils.AuthInfoLogin;
import com.slacks.utils.AuthInfoPass;
import com.slacks.utils.ChangePassword;
import com.slacks.utils.CheckEmail;
import com.slacks.utils.InsertCode;
import com.slacks.utils.LoginCommand;
import com.slacks.utils.RegisterRequest;
import com.slacks.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource(name = "userDAO")
	private UserDAO userDAO;

	@Override
	public void register(RegisterRequest regReq) throws Exception {
		UserVO email = userDAO.selectByEmail(regReq.getEmail());
		if (email != null) {
			throw new AlreadyExistingEmailException();
		}
		userDAO.insertUser(regReq);
	}

	@Override
	public AuthInfoLogin loginAuth(LoginCommand loginCommand) throws Exception {
		UserVO user = userDAO.selectByEmail(loginCommand.getEmail());
		if (user == null) {
			throw new EmailPasswordNotMatchingException();
		}
		if (!user.matchPassword(loginCommand.getPw())) {
			throw new EmailPasswordNotMatchingException();
		}
		return new AuthInfoLogin(user.getEmail(), user.getName());
	}

	@Override
	public boolean checkEmail(CheckEmail checkEmail) throws Exception {
		UserVO Email = userDAO.selectByEmailFind(checkEmail.getEmail());
		if (Email == null)
			throw new EmailNotFoundException();
		else
			return true;
	}

	@Override
	public boolean checkCode(InsertCode insertCode, String code) throws Exception {
		if(insertCode.getCode().equals(code)) {
			return true;
		}
		throw new CodeNotMatchException();
	}

	@Override
	public void changePassword(ChangePassword changePassword, String email) throws Exception {
		String password = changePassword.getPassword();
		AuthInfoPass infoPass = new AuthInfoPass();
		infoPass.setEmail(email);
		infoPass.setPassword(password);
		userDAO.changePassword(infoPass);
	}

}

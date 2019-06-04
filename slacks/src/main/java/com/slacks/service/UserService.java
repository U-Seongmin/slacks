package com.slacks.service;

import com.slacks.utils.AuthInfoLogin;
import com.slacks.utils.ChangePassword;
import com.slacks.utils.CheckEmail;
import com.slacks.utils.InsertCode;
import com.slacks.utils.LoginCommand;
import com.slacks.utils.RegisterRequest;

public interface UserService {
	
	//user controller에서 사용하는 것들
	void register(RegisterRequest regReq) throws Exception;
	AuthInfoLogin loginAuth(LoginCommand loginCommand) throws Exception;
	boolean checkEmail(CheckEmail checkEmail) throws Exception;
	boolean checkCode(InsertCode insertCode, String code) throws Exception;
	void changePassword(ChangePassword changePassword, String email) throws Exception;
}

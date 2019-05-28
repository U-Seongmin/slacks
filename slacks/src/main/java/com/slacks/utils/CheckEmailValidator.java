package com.slacks.utils;

import javax.annotation.Resource;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.slacks.dao.UserDAO;
import com.slacks.vo.UserVO;

public class CheckEmailValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return CheckEmail.class.isAssignableFrom(clazz);
    }
	
	@Resource(name = "userDAO")
	private UserDAO userDAO;
	
	@Override
    public void validate(Object target, Errors errors) {
		CheckEmail checkEmail = (CheckEmail) target;
		
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required", "필수 정보 입니다.");
        
        if(!checkEmail.getEmail().isEmpty()) {
        	UserVO Email = userDAO.selectByEmailFind(checkEmail.getEmail());
            if(Email == null) {
                errors.rejectValue("email", "unduplicate", "가입되지 않은 E-mail입니다");
            }
        }
    }
}

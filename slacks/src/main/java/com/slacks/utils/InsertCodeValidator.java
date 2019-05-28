package com.slacks.utils;

import javax.servlet.http.HttpSession;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class InsertCodeValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InsertCode.class.isAssignableFrom(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
		InsertCode checkCode = (InsertCode) target;
		
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "required", "필수 정보 입니다.");
        
        if(!checkCode.getCode().isEmpty()) {
        	ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        	HttpSession session = attr.getRequest().getSession();
        	String code = (String) session.getAttribute("code");
        	if(!code.equals(checkCode.getCode().toString())) {
        		errors.rejectValue("code", "nomatch", "코드가 일치하지 않습니다.");
        	}
        }
    }
}

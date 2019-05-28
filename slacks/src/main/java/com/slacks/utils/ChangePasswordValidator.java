package com.slacks.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ChangePasswordValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return ChangePassword.class.isAssignableFrom(clazz);
    }
 
    @Override
    public void validate(Object target, Errors errors) {
        ChangePassword changePassword = (ChangePassword) target;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "필수 정보 입니다.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "checkPw", "required", "필수 정보 입니다.");
        if(!changePassword.getPassword().isEmpty()) {
            if(!changePassword.isPwEqualToCheckPw()) {
                errors.rejectValue("checkPw", "nomatch", "비밀번호가 일치하지 않습니다.");
            }
        }
    }
}

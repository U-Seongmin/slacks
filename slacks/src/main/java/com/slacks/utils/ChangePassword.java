package com.slacks.utils;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class ChangePassword {
	@Column
	// @NotEmpty(message="비밀번호를 입력해주세요.")
	@Size(min = 4, max = 20, message = "비밀번호를 4~20자로 입력해주세요.")
	private String password;

	@Column
	// @NotEmpty(message="비밀번호를 입력해주세요.")
	@Size(min = 4, max = 20, message = "비밀번호를 4~20자로 입력해주세요.")
	private String checkPw;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckPw() {
		return checkPw;
	}

	public void setCheckPw(String checkPw) {
		this.checkPw = checkPw;
	}

	//비밀번호 확인
    public boolean isPwEqualToCheckPw() {
        return password.equals(checkPw);
    }
}

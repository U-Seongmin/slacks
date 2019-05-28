package com.slacks.utils;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class LoginCommand {
	
	@NotEmpty(message="Email을 입력해주세요.")
	private String email;
	
	@NotEmpty(message="아이디를 입력해주세요.")
	private String pw;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
}

package com.slacks.utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class CheckEmail {
	@Column
    @NotEmpty(message="이메일을 입력해주세요.")
    @Email(message="이메일 형식에 맞춰 올바르게 입력해주세요.")
    private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

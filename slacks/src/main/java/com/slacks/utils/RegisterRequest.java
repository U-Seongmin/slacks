package com.slacks.utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class RegisterRequest {
	
	@Column
    @NotEmpty(message="이메일을 입력해주세요.")
    @Email(message="이메일 형식에 맞춰 올바르게 입력해주세요.")
    private String email;
    
    @Column
    @NotEmpty(message="이름을 입력해주세요.")
    @Pattern(regexp="\\S{2,8}", message="이름을 공백없이 2~6자로 입력해주세요.")
    private String name;
    
    @Column
    @NotEmpty(message="비밀번호를 입력해주세요.")
    @Size(min=4, max=20, message="비밀번호를 4~20자로 입력해주세요.")
    private String password;
    
    @Column
    @NotEmpty(message="비밀번호를 입력해주세요.")
    @Size(min=4, max=20, message="비밀번호를 4~20자로 입력해주세요.")
    private String checkPw;
    
    private int serial;
    
    public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = (int)(Math.random()*10000000)+2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
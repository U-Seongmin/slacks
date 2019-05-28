package com.slacks.vo;

public class UserVO {
	private int serial;
	private String name;
	private String email;
	private String password;

	public boolean matchPassword(String pw) {
        return this.password.equals(pw);
    }
	
	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

package com.slacks.utils;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

public class InsertCode {
	@Column
	@NotEmpty(message="Code를 입력해주세요.")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}

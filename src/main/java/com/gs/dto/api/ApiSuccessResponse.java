package com.gs.dto.api;

import com.gs.constants.SystemMessage;

public class ApiSuccessResponse {

	private String message;

	public ApiSuccessResponse(String message){

		this.message= message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}


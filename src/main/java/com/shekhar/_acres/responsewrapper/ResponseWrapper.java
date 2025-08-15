package com.shekhar._acres.responsewrapper;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ResponseWrapper {
	private String message;
	private Object data;
}

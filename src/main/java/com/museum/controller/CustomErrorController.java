package com.museum.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	public String viewError(HttpServletRequest request) {
		Object error = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(error != null) {
			Integer errorCode = Integer.valueOf(error.toString());
			if(HttpStatus.NOT_FOUND.value() == errorCode) {
				return "error";
			}
		}
		return "error";
	}
}

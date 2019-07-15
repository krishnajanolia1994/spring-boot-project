package com.example.demoRest.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoRest.entiy.Foo;


@RestController
@RequestMapping("/auth")
public class authController {
	 @PreAuthorize("#oauth2.hasScope('read')")
	@RequestMapping(method = RequestMethod.GET, value = "/foos/{id}")
	@ResponseBody
	public Foo findById(@PathVariable long id) {
	    return
	      new Foo();
	}
	}

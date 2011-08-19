package com.ethomasjoseph.javabumps.osgi.helloworld.service.internal;

import java.util.Date;

import com.ethomasjoseph.javabumps.osgi.helloworld.HelloService;

public class HelloServiceImpl implements HelloService {

	public String sayHello() {
		return "Hello from default Service @ " + (new Date().toString());
	}

	public String sayBye() {
		return "Good Bye from default Service @ " + (new Date().toString());
	}

}

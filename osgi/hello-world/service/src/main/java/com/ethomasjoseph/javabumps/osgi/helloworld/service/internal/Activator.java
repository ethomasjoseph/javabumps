package com.ethomasjoseph.javabumps.osgi.helloworld.service.internal;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.ethomasjoseph.javabumps.osgi.helloworld.HelloService;

public class Activator implements BundleActivator {
	private ServiceRegistration registration;
	private HelloService service = null;

	public void start(BundleContext context) throws Exception {
		if (service == null) {
			service = new HelloServiceImpl();
		}
		Dictionary<String, String> props = new Hashtable<String, String>();
        props.put("Language", "English");
        props.put("Speciality", "Has timestamp");
        registration = context.registerService(HelloService.class.getName(), service, props);
	}

	public void stop(BundleContext context) throws Exception {
		registration.unregister();
	}

}

package com.ethomasjoseph.javabumps.osgi.helloworld.serviceclient.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import com.ethomasjoseph.javabumps.osgi.helloworld.HelloService;

public class Activator implements BundleActivator {
	private static final Log LOG = LogFactory.getLog(Activator.class);

	private ServiceTracker serviceTracker;
	private boolean track = true;

	public void start(BundleContext context) throws Exception {
		LOG.info("Starting Client Consumer Bundle...");

		serviceTracker = new ServiceTracker(context,
				HelloService.class.getName(), null);
		serviceTracker.open();

		new Thread(new Runnable() {
			HelloService hello = (HelloService) serviceTracker.getService();

			public void run() {
				while (track) {
					if (hello == null) {
						hello = (HelloService) serviceTracker.getService();
						if (hello == null) {
							LOG.warn("HelloService is not available at" +
									" the moment. Please try again later.");
						}
					} else {
						LOG.info("Client LOG: " + hello.sayHello());
						LOG.info("Client LOG: " + hello.sayBye());
					}

					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						LOG.error("Exception with Thread", e);
					}
				}
			}
		}, "Client Execution Thread").start();

		LOG.info("Started Client Consumer Bundle!!!");
	}

	public void stop(BundleContext context) throws Exception {
		track = false;
		serviceTracker.close();
	}

}

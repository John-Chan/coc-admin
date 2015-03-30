package org.coc.tools.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Cocadmin implements EntryPoint {

/*
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
*/
	private final HandlerManager eventBus = new HandlerManager(null);
	private final AppController appViewer = new AppController(eventBus);
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		runMVP();
		//GWT.getVersion();
		//runHelloWorld();
	}
	
	public void runMVP() {

		GWT.log("running");
		//HandlerManager eventBus = new HandlerManager(null);
		//AppController appViewer = new AppController(eventBus);
		appViewer.go(RootPanel.get());
	}
}

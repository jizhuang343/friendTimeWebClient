package com.pod2.FriendTimeClient.client;

import java.util.Date;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FriendTimeClient implements EntryPoint {

	private VerticalPanel mainPanel = new VerticalPanel();

	/**
	 * Entry point method. 
	 * The most straightforward way to make a GWT app is to have a single page for the entire application, 
	 * and a single top-level module (defined in a .gwt.xml file). Each module has a single EntryPoint class. 
	 * Then all of your different "pages" are sub-sections of the same page, ideally using GWT's history 
	 * mechanism to keep track of state changes that in a non-AJAX web app would be new pages.
	 * https://developers.google.com/web-toolkit/doc/2.4/DevGuideMvpActivitiesAndPlaces  
	 */
	public void onModuleLoad() {

		// TODO: generate navigation bar here
		RootPanel navbar = RootPanel.get("navigation");
		navbar.add(new HTML("<h2>Navigation HTML Code Here</h2>"));
			
		Calendar calendar = new Calendar();
		calendar.setDate(new Date()); // calendar date, not required
		calendar.setDays(7); // number of days displayed at a time, not required
		calendar.setWidth("600px");
		calendar.setHeight("400px");

		mainPanel.add(calendar);

		// Associate the Main panel with the HTML host page.
		RootPanel.get("navigation").add(mainPanel);

	}

}
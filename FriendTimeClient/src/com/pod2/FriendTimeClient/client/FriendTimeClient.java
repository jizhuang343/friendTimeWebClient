package com.pod2.FriendTimeClient.client;

import java.util.Date;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
//import com.extjs.gxt.ui.client.event.Listener;
//import com.extjs.gxt.ui.client.event.MessageBoxEvent;
//import com.extjs.gxt.ui.client.widget.Header;
//import com.extjs.gxt.ui.client.widget.MessageBox;
//import com.extjs.gxt.ui.client.widget.Text;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.pod2.FriendTimeClient.shared.FacebookUtil;

public class FriendTimeClient implements EntryPoint {

	private VerticalPanel mainPanel = new VerticalPanel();
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);


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
		RootPanel rootPanel = RootPanel.get("rootPanel");
		rootPanel.add(new HTML("<h2>This is the Root Panel</h2>"));

		/*Location provides access to the browser's location's object. 
		The location object contains information about the current URL 
		and methods to manipulate it. 
		*/
		
        final String authToken = Location.getParameter("code");
        //sb.append("&code=").append(authCode);

        if (authToken == null || "".equals(authToken)) {
            redirect(FacebookUtil.getAuthorizedUrl());
        } else {
            greetingService.login(authToken, new AsyncCallback<String>() {
                public void onFailure(final Throwable caught) {
                    handleError(caught);
                }

                public void onSuccess(final String authToken) {
                	Label loggedInLabel = new Label();
                	loggedInLabel.setText("You have successfully logged in! authToken is" +authToken);

                	mainPanel.add(new HTML("<b>Logged In!</b>"));
                	
                   // rankFriends(authToken);
                	Calendar calendar = new Calendar();
            		calendar.setDate(new Date()); // calendar date, not required
            		calendar.setDays(7); // number of days displayed at a time, not required
            		calendar.setWidth("600px");
            		calendar.setHeight("400px");

            		mainPanel.add(calendar);

            		// Associate the Main panel with the HTML host page.
            		RootPanel.get("rootPanel").add(mainPanel);
                }
            });
        }
	}
	
	private void handleError(final Throwable caught) {
        Window.alert(caught.getMessage());
    }

//    private String getProfilePictureUrl(final FbFriend friend) {
//        return "http://graph.facebook.com/" + friend.getId() + "/picture";
//    }

    // redirect the browser to the given url
    public static native void redirect(String url)/*-{
		$wnd.location = url;
    }-*/;
	
}

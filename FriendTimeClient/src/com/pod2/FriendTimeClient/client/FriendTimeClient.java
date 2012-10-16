package com.pod2.FriendTimeClient.client;

import java.util.Date;
import java.util.List;

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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.pod2.FriendTimeClient.shared.FacebookUtil;
import com.pod2.FriendTimeClient.shared.FbFriend;

public class FriendTimeClient implements EntryPoint {

	private VerticalPanel mainPanel = new VerticalPanel();
	private VerticalPanel loginPanel = new VerticalPanel();
	private Button loginButton = new Button("Login With Facebook");

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * Entry point method. The most straightforward way to make a GWT app is to
	 * have a single page for the entire application, and a single top-level
	 * module (defined in a .gwt.xml file). Each module has a single EntryPoint
	 * class. Then all of your different "pages" are sub-sections of the same
	 * page, ideally using GWT's history mechanism to keep track of state
	 * changes that in a non-AJAX web app would be new pages.
	 * https://developers.
	 * google.com/web-toolkit/doc/2.4/DevGuideMvpActivitiesAndPlaces
	 */
	public void onModuleLoad() {

		// TODO: generate navigation bar here
		RootPanel rootPanel = RootPanel.get("rootPanel");
		rootPanel.add(new HTML("<h2><center>Welcome To FriendTime!</center></h2>"));
		
		final DialogBox loginDialogBox = new DialogBox();
		loginDialogBox.setText("Login Diaglog Box");
		loginDialogBox.setAnimationEnabled(true);
		
		// Associate the Main panel with the HTML host page.
		RootPanel.get("rootPanel").add(mainPanel);
		/*
		 * Location provides access to the browser's location's object. The
		 * location object contains information about the current URL and
		 * methods to manipulate it.
		 */

		final String authToken = Location.getParameter("code");
		// where code comes from: sb.append("&code=").append(authCode);

		if (authToken == null || "".equals(authToken)) {					
			final Label loginLabel = new Label("Click Below to Login!");
			loginPanel.add(loginLabel);
			loginPanel.add(loginButton);
			loginLabel.setHorizontalAlignment(Label.ALIGN_CENTER);
			loginPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);	
			loginDialogBox.setWidget(loginPanel);

			mainPanel.add(loginPanel);
			mainPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
			loginButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
				//	grabFriends(authToken);

					redirect(FacebookUtil.getAuthorizedUrl());
				}
			});
		}
		else {
			greetingService.login(authToken, new AsyncCallback<String>() {
				public void onFailure(final Throwable caught) {
					
					handleError(caught);
				}

				public void onSuccess(final String authToken) {
					Label loggedInLabel = new Label("You have successfully logged in! authToken is: "
							+ authToken);					
					mainPanel.add(loggedInLabel); 
					mainPanel.add(new HTML("<b>You Are Logged In!</b>"));
					// rankFriends(authToken);
					Calendar calendar = new Calendar();
					calendar.setDate(new Date()); // calendar date, not required
					calendar.setDays(7); // number of days displayed at a time,
											// not required
					calendar.setWidth("600px");
					calendar.setHeight("400px");
					mainPanel.add(calendar);
					
					
					//loginDialogBox.hide();
					grabFriends(authToken);
				}
			});
		}
	}

	
	private void grabFriends(final String authToken) {

        final RootPanel rootPanel = RootPanel.get();
       // greetingService.findFriendsThatUseApp(authToken, new AsyncCallback<List<FbFriend>>() {
        	
       greetingService.findFriendsThatUseApp(authToken, new AsyncCallback<List<FbFriend>>() {

            public void onFailure(final Throwable caught) {
        		rootPanel.add(new HTML("<h2><center>FAILED!</center></h2>"));
        		handleError(caught);
                Window.alert(caught.getMessage());
            }

            public void onSuccess(final List<FbFriend> friends) {
               //closeMessageBox();
               // final Header header = new Header();
               // header.setText("How famous are your friends");
               // rootPanel.add(header);
            	int friendListSize = friends.size();
            	
            	String lah = friendListSize+"";
            	final Label friendsize = new Label(lah);
            	rootPanel.add(friendsize);
         
            	for (final FbFriend friend : friends) {
                    final Panel friendPanel = new HorizontalPanel();

                    friendPanel.setStyleName("panel");
                    final Image profilePic = new Image("http://graph.facebook.com/" + friend.getId() + "/picture");
                    profilePic.setStyleName("profilePic");
                    friendPanel.add(profilePic);
                    friendPanel.add(new Hidden(friend.getId().toString()));
                    
                    final Label friendLabel = new Label(friend.getName());
                    friendPanel.add(friendLabel);        			
                
                 //   final Text countText = new Text("");
                 //   friendPanel.add(countText);
                    rootPanel.add(friendPanel);

//                    googleService.findFriendRanking(friend, new AsyncCallback<Long>() {
//
//                        public void onFailure(final Throwable caught) {
//                            handleError(caught);
//                        }
//
//                        public void onSuccess(final Long result) {
//                            if (result > 0) {
//                                countText.setText(result.toString());
//                            } else {
//                                countText.getParent().setVisible(false);
//                            }
//                        }
//                    });
                }
            }

        });

    }

	
	private void handleError(final Throwable caught) {
		Window.alert(caught.getMessage());
	}

	// private String getProfilePictureUrl(final FbFriend friend) {
	// return "http://graph.facebook.com/" + friend.getId() + "/picture";
	// }

	// redirect the browser to the given url
	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;

}

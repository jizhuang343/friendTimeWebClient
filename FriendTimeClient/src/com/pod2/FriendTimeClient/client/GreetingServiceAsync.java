package com.pod2.FriendTimeClient.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pod2.FriendTimeClient.shared.FbFriend;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	public void login(String authToken, AsyncCallback<String> asyncCallback);

	public void findFriendsThatUseApp(String authToken,
			AsyncCallback<List<FbFriend>> asyncCallback);
	 
	public void findFriends(String authToken,
			AsyncCallback<List<FbFriend>> asyncCallback);
	
    public void debug(String authToken, AsyncCallback<String> asyncCallback);     	

}

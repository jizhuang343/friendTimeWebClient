package com.pod2.FriendTimeClient.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	public void login(String authToken, AsyncCallback<String> asyncCallback);
}

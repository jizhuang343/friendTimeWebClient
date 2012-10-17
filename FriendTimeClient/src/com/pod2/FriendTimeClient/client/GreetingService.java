package com.pod2.FriendTimeClient.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pod2.FriendTimeClient.shared.FbFriend;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	//String greetServer(String name) throws IllegalArgumentException;
	
	public String login(String authToken);

	public List<FbFriend> findFriendsThatUseApp(String authToken);
	
	public List<FbFriend> findFriends(String authToken);
	
    public String debug(String authToken);     	

}

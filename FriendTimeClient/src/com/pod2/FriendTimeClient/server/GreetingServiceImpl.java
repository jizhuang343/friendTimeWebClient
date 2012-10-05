package com.pod2.FriendTimeClient.server;

import java.util.List;

//import com.google.gson.Gson;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.pod2.FriendTimeClient.client.GreetingService;
import com.pod2.FriendTimeClient.shared.FacebookUtil;
import com.pod2.FriendTimeClient.shared.Util;

/**
 * The server side implementation of the RPC service.
 */
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
    private static final long serialVersionUID = 1L;

    public String login(final String authToken) {
        final String url = FacebookUtil.getAccessTokenUrl(Util.encode(authToken));
        return Util.fetchURL(url);
    } 

//    public List<FbFriend> findFriends(final String authToken) {
//        final String friendsUrl = FacebookUtil.getFriendsUrl(encodeUrl(authToken));
//        final String json = Util.fetchURL(friendsUrl);
//        final FBFriends fbFriends = new Gson().fromJson(json, FBFriends.class);
//        return fbFriends.getData();
//    }

    private static String encodeUrl(final String unencodedToken) {
        final String[] parts = unencodedToken.split("=");

        if (parts.length < 2) {
            return "";
        }

        return "access_token=" + Util.encode(parts[1]);
    }

}

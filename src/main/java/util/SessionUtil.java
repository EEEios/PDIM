package util;

import attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUsername(), channel);
        channel.attr(Attributes.USERNAME).set(session.getUsername());
        channel.attr(Attributes.SESSION).set(session);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String username) {
        return userIdChannelMap.containsKey(username) ? userIdChannelMap.get(username) : null;
    }

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static String getChannelUsername(Channel channel) {
        Attribute<String> usernameAttr = channel.attr(Attributes.USERNAME);
        return usernameAttr.get();
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}

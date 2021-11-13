package util;

import attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.Attribute;
import io.netty.util.concurrent.EventExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    private static final Map<String, Channel> userMap = new ConcurrentHashMap<>();

    // 保存群组和群组中的用户 Channel
    private static final HashMap<String, ChannelGroup> groupMap = new HashMap<>();

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String username) {
        return userMap.containsKey(username) ? userMap.get(username) : null;
    }

    public static String getChannelUsername(Channel channel) {
        Attribute<String> usernameAttr = channel.attr(Attributes.USERNAME);
        return usernameAttr.get();
    }

    /**
     * 登出操作
     * @param username
     */
    public static void removeChannel(String username) {
        userMap.remove(username);
    }

    /**
     * 登录操作
     */
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }

    public static void bindSession(Session session, Channel channel) {
        userMap.put(session.getUsername(), channel);
        channel.attr(Attributes.USERNAME).set(session.getUsername());
        channel.attr(Attributes.SESSION).set(session);
    }

    /**
     * 群组操作
     */
    public static ChannelGroup createGroup(String groupName, EventExecutor executor) {
        ChannelGroup group = new DefaultChannelGroup(executor);
        groupMap.put(groupName, group);
        return group;
    }

    public static void addMember(String groupName, Channel channel) {
        groupMap.get(groupName).add(channel);
    }

    public static ChannelGroup getGroup(String groupName) {
        return groupMap.get(groupName);
    }
}

package attribute;

import io.netty.util.AttributeKey;
import util.Session;

public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

    AttributeKey<String> USERNAME = AttributeKey.newInstance("username");

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");


}

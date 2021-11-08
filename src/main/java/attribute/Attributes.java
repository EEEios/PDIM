package attribute;

import io.netty.util.AttributeKey;
import util.Session;

public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}

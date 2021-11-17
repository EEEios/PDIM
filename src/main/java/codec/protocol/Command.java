package codec.protocol;

public final class Command {

    public static final byte LOGIN_REQUEST = 1;

    public static final byte LOGIN_RESPONSE = 2;

    public static final byte MESSAGE_REQUEST = 3;

    public static final byte MESSAGE_RESPONSE = 4;

    public static final byte LOGOUT_REQUEST = 5;

    public static final byte LOGOUT_RESPONSE = 6;

    public static final byte RESPONSE = 7;

    public static final byte ENTER_GROUP = 8;

    public static final byte GROUP_MESSAGE = 9;

    public static final byte EXIT_GROUP = 10;
}

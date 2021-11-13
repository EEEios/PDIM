package client.command;

import io.netty.channel.Channel;

public class AbstractCommandHandler implements CommandHandler{

    /**
     * 参数数量：0代表参数数量不受限制
     */
    protected int ARGS_NUMS;

    public AbstractCommandHandler(){}

    public AbstractCommandHandler(int ARGS_NUMS) {
        this.ARGS_NUMS = ARGS_NUMS;
    }

    /**
     * 用于 CommanHandle 校验，无法被子类继承
     * @param strings
     * @param channel
     */
    @Override
    public final void exec(String[] strings, Channel channel) {
        if (ARGS_NUMS != 0 && strings.length != ARGS_NUMS) {
            System.out.println("- 命令参数错误");
        }
        execHandler(strings, channel);
    }

    public void execHandler(String[] strings, Channel channel){}
}

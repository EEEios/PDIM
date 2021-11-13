## 自定义协议格式

| 表头   | 版本号 | 序列化方法 | 指令类型 | 长度   | 内容 |
| ------ | ------ | ---------- | -------- | ------ | ---- |
| 4 byte | 1 byte | 1 byte     | 1 byte   | 4 byte |      |



## 指令

| 指令                      | 功能           |
| ------------------------- | -------------- |
| Logout                    | 退出登录       |
| send <username> <message> | 发送消息       |
| eng <groupname>           | 加入群聊       |
| sendg <groupname>         | 向群组发送消息 |



## 创建新命令流程

1. Commend 添加指令
2. 在 ConsoleCommandManager 当中添加配置
3. 创建对应 Packet
4. 在 PacketCodec 中加入解析
5. 创建 commandHandler
6. 创建请求/响应 handler
7. 将 handler 加入到 eventLoopGroup 中


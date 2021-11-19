## 自定义协议格式

| 表头   | 版本号 | 序列化方法 | 指令类型 | 长度   | 内容 |
| ------ | ------ | ---------- | -------- | ------ | ---- |
| 4 byte | 1 byte | 1 byte     | 1 byte   | 4 byte |      |



## 指令

| 指令                      | 功能           |
| ------------------------- | -------------- |
| logout                    | 退出登录       |
| send <username> <message> | 发送消息       |
| eng <groupname>           | 加入群聊       |
| sendg <groupname>         | 向群组发送消息 |
| exg <groupname>           | 退出群聊       |



## 创建新命令流程

1. Commend 添加指令
2. 创建对应 Packet
3. 创建 commandHandler
4. 在 ConsoleCommandManager 当中添加配置
5. 在 PacketCodec 中加入解析
6. 创建请求/响应 handler
7. 将 handler 加入到 eventLoopGroup 中



# 自定义注解 & 被注解类扫描

```Xml
<dependency>
    <groupId>org.reflections</groupId>
    <artifactId>reflections</artifactId>
    <version>0.9.10</version>
</dependency>
```


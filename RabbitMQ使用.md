# RabbitMQ 使用

## 安装

 - 安装`erlang`,[`点此`](http://www.erlang.org/downloads)下载对应版本并配置环境变量。
  
 - 安装`RabbitMQ`，[`点此`](http://www.rabbitmq.com/download.html) 下载对应版本。
 
## 启动

 - 解压下载的 RabbitMQ 压缩包，以 windows 为例，进入 `sbin` 目录，执行以下命令即可启动
 
 ```sbtshell
# 允许启动WEB管理里面，默认访问 http://localhost:15672 ，账号密码都是 guest
rabbitmq-plugins.bat enable rabbitmq_management

# 启动RabbitMQ
rabbitmq-server.bat start
```

## 使用

### 一对一模式

类似`ActiveMQ`的队列模式，参考代码 [teclan-SpringBoot](https://github.com/teclan/teclan-SpringBoot),
分支`RabbitMQ`,版本`v0.3.0-amqp`。

### 一对多模式（fanout模式）

类似`ActiveMQ`的订阅模式，参考代码 [teclan-SpringBoot](https://github.com/teclan/teclan-SpringBoot),
分支`master`,版本`v0.3.2`。

### 重要代码

 - 发送消息至指定队列
 
 ```java
@Component
public class Queue1SendServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Queue1SendServer.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private static final String QUEUE_NAME="fanout.queue1";


    public void sendMessage(Object message) {
        LOGGER.info("\n发送队列({})消息:{}...\n",QUEUE_NAME,message);
        // 发送消息至指定队列
        this.rabbitTemplate.convertAndSend( QUEUE_NAME, message);
    }
}

```

- 发送消息至交换器

```java
@Component
public class Queue2SendServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Queue2SendServer.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private static final String QUEUE_NAME="fanout.queue2";


    public void sendMessage(Object message) {
        LOGGER.info("\n发送队列({})消息:{}...\n",QUEUE_NAME,message);
        //  fanoutExchange 是交换器名称
        this.rabbitTemplate.convertAndSend("fanoutExchange", QUEUE_NAME, message);
    }
}
```

- 消息接收的注解使用


```
@RabbitListener(queues = QUEUE_NAME) // 不会自动创建队列

```

```
@RabbitListener(queuesToDeclare = @Queue(QUEUE_NAME)) //自动创建队列
```


```
 # Exchange.value：交换器名称 Exchange.type:交换器类型,可选 direct,fanout,topic,headers   
 @RabbitListener(bindings = @QueueBinding(value = @Queue(QUEUE_NAME),exchange = @Exchange(value = "fanoutExchange",type ="fanout" )))
```





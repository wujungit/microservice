# microservice
## 微服务架构
> 统一依赖管理
> spring-boot
> spring-cloud
## 业务拆分
> 订单中心
> 支付中心
## 技术点

### 一、RocketMQ
#### 1、Message 拉取与消费
* PushConsumer 通过注册监听的方式来消费信息（常用）
  不断轮询Broker获取消息，长轮询
* PullConsumer 通过拉取的方式来消费消息

### 统一异常处理
### 分页插件
### 封装组件（面向接口编程、自定义注解）


### RocketMQ可靠一致性的MySQL落地实现
* 1、上游系统，执行业务并发送指令给可靠消息服务并保留消息副本；
* 2、可靠消息服务和MQ消息组件，协调上下游消息的传递，并确保上下游消息的一致性；
* 3、下游系统，监听MQ的消息并执行自身业务并保留消息副本；
# microservice
## 微服务架构
* 在微服务架构中，需要几个基础的服务治理组件，
  包括服务注册与发现、服务消费、负载均衡、断路器、智能路由、配置管理等，
  由这几个基础组件相互协作，共同组建了一个简单的微服务系统。
* 1.parent-dependencies-server（父级依赖服务，版本依赖管理）
  2.register-eureka-server（服务注册）
  3.passthrough-server（服务透传）
  4.zuul-gateway-server（服务网管）
  5.component-server（组件相关服务）
  	component-common（项目DTO和VO）
  	component-redis（缓存）
  	component-utils（工具类）
  6.business-core-service（业务层）
  	core-pc-service（PC端）
  	core-app-service（移动端）
  7.service-xxxx-manager（基础层）
  8.service-xxpay-manager（支付中心服务）
  9.service-order-manager（订单服务）
> 统一依赖管理
> spring-boot
> spring-cloud
## 业务拆分
> 订单中心
> 支付中心
## 技术点
### 一、消息中间件：RocketMQ
#### 1、Message 拉取与消费
* PushConsumer 通过注册监听的方式来消费信息（常用），不断轮询Broker获取消息，长轮询；
* PullConsumer 通过拉取的方式来消费消息；
#### 2、RocketMQ可靠一致性的MySQL落地实现
* 上游系统，执行业务并发送指令给可靠消息服务并保留消息副本；
* 可靠消息服务和MQ消息组件，协调上下游消息的传递，并确保上下游消息的一致性；
* 下游系统，监听MQ的消息并执行自身业务并保留消息副本；
### 二、RestTemplate和WebClient
* 请求时同步阻塞，使用异步方式请求，可以使用AsyncRestTemplate；
* Spring5开始全面的引入了Reactive响应式编程，WebClient就属于Spring WebFlux的一部分；
* WebClient的请求模式属于异步非阻塞，能够以少量固定的线程处理高并发的HTTP请求；
### 三、NoSQL数据库：Redis
#### 1、数据库的原理
* redis作为缓存的作用就是减少对数据库的访问压力;
* 主要是：支持持久化、支持更多数据结构、支持主从同步；
* 缓存同步的原理：就是将redis中的key进行删除，下次访问的时候，redis中没有该数据，则从DB进行查询，再次更新到redis中；
#### 2、使用场景
* 使用Redis来解决邮箱激活的实效性；
  用户在注册的时候，虽然正则表达式能检查邮箱的格式是否正确，但是正则检查不了邮箱是否可用，于是让用户进行激活，就能避免用户填写一个不可用的邮箱；
* 快速登陆，短信验证码的校验；
* 缓存穿透
* 缓存雪崩
* 布隆过滤器拦截
* 服务降级
#### 3、API
* StringRedisTemplate 操作k-v都是字符串的；
* RedisTemplate 操作k-v都是对象的（字符串String、列表List、集合Set、散列Hash、有序集合ZSet）；
* 字符串：StringRedisTemplate.opsForValue()
* 列表：RedisTemplate.opsForList() 
* 集合：RedisTemplate.opsForSet()
* 哈希：RedisTemplate.opsForHash()
* 有序集合：RedisTemplate.opsForZSet()
### 统一异常处理
### 分页插件
### 封装组件（面向接口编程、自定义注解）

## 开发纪要
> 1、关联条件（on）和查询条件（where）对结果集的影响
关联条件（on）：先查出符合条件的待关联表，再去关联主表，没有的将以null补全；
查询条件（where）：对关联以后的结果集进行再筛选；
> 2、spring-boot-maven-plugin
能够将mvn package生成的软件包，再次打包为可执行的软件包，并将mvn package生成的软件包重命名为*.original
> 3、springboot启动时，@ComponentScan扫描的时候，只扫描当前程序所在包结构（包含子包）中声明的组件


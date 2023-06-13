网约车项目：
乘客端：
1.使用maven搭建项目骨架，并当做父工程，boot版本使用2.4.13，不使用最新版
是为了便于后期调试
2.创建子工程apipassenger乘客服务，创建test类，进行测试，测试没问题，进行乘客获取验证码的代码编写。
3.创建子工程verificationcode验证码服务，获取前端传入的验证码。进行测试，测试没问题进行获取验证码的编写。
4.把verificationcode服务注册进apipassenger服务，然后进行验证码的获取，并从redis中获取的验证码进行比较
5.创建service-passenger-user乘客用户服务，然后进行测试，测试没问题进行乘客用户的代码编写，根据手机号判断用户是否存在，不存在进行注册，存在直接获取。
6.把service-passenger-user乘客用户服务注册进apipassenger服务，判断原来是否有用户然后进行相应的处理
司机端：
1.描述乘客下单前置的功能介绍
2.介绍司机和车辆的关系以及司机信息录入的一个时序图
3.司机信息地区国标的拉取，设计地区字典表并创建表，并实用程序操作地区的字典表然后插入数据库中
4.根据国家网约车白皮书修正性别，并设计司机表
5.创建司机用户服务，然后进行操作数据库的骨架编写
6.进行司机信息日期管理的代码编写，然后设计司机信息接口并完成骨架代码编写
7.把service-driver-user服务注册到nacos服务，创建api-boss服务注册到nacos
8.进行api-boss服务接口的设计，然后调用service-driver-user服务，进行司机信息的修改
9.设计车辆数据库，使用mybatis-plus反向生成车辆实体类，然后设计添加车辆接口，并进行相关代码编写


## 各个服务之间端口统计

| 服务名                      | 端口名  |
|--------------------------|------|
| api-passenger            | 8081 |
| service-verificationcode | 8082 |
| service-passenger-user   | 8083 |
| service-price            | 8084 |
| service-map              | 8085 |
| service-driver-user      | 8086 |
| service-order            | 8089 |
| api-boss                 | 8087 |
| api-driver               | 8088 |


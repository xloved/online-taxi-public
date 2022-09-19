网约车项目：
1.使用maven搭建项目骨架，并当做父工程，boot版本使用2.4.13，不使用最新版
是为了便于后期调试
2.创建子工程apipassenger乘客服务，创建test类，进行测试，测试没问题，进行乘客获取验证码的代码编写。
3.创建子工程verificationcode验证码服务，获取前端传入的验证码。进行测试，测试没问题进行获取验证码的编写。
4.把verificationcode服务注册进apipassenger服务，然后进行验证码的获取，并从redis中获取的验证码进行比较
5.创建service-passenger-user乘客用户服务，然后进行测试，测试没问题进行乘客用户的代码编写，根据手机号判断用户是否存在，不存在进行注册，存在直接获取。
6.把service-passenger-user乘客用户服务注册进apipassenger服务，判断原来是否有用户然后进行相应的处理

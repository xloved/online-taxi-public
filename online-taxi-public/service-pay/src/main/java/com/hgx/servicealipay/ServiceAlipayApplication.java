package com.hgx.testalipay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServiceAlipayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAlipayApplication.class, args);
	}

	@PostMapping("/test")
	public String test(){
		System.out.println("支付宝回调啦");
		return "外网穿透测试";
	}
}

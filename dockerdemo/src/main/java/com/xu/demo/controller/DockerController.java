/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: DockerController
 * Author:   旭哥
 * Date:     2019/6/23 16:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/6/23
 * @since 1.0.0
 */@RestController
public class DockerController {

        @RequestMapping("/docker")
        public @ResponseBody String DcokerTest(){
            return "哈哈";
        }
}


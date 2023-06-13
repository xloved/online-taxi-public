package com.hgx.serviceorder.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @Description 代码生成工具类
 * @Author huogaoxu
 * @Date 2023-06-12 14:45
 * @Version 1.0
 **/
public class MysqlGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-order?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                        "root", "xuge")
                .globalConfig(builder -> {
                    builder.author("huogaoxu").fileOverride().outputDir("E:\\Study\\IDEA\\online-taxi-public\\service-order\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.hgx.serviceorder").pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                            "E:\\Study\\IDEA\\online-taxi-public\\service-driver-user\\src\\main\\java\\com\\hgx\\serviceorder\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("order_info");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}

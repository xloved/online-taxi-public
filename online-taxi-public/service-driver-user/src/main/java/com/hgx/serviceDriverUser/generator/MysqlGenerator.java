package com.hgx.serviceDriverUser.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @Description mysql代码生成器
 * @Author huogaoxu
 * @Date 2023-05-30 21:55
 * @Version 1.0
 **/
public class MysqlGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-driver-user?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                        "root", "xuge")
                .globalConfig(builder -> {
                    builder.author("huogaoxu").fileOverride().outputDir("E:\\Study\\IDEA\\online-taxi-public\\service-driver-user\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.hgx.serviceDriverUser").pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                            "E:\\Study\\IDEA\\online-taxi-public\\service-driver-user\\src\\main\\java\\com\\hgx\\serviceDriverUser\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("driver_user_work_status");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}

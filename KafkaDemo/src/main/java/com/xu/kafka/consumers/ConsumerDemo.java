/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ConsumerDemo
 * Author:   旭哥
 * Date:     2019/4/29 17:59
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.kafka.consumers;

import com.xu.kafka.util.PropertiesUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/4/29
 * @since 1.0.0
 */
public class ConsumerDemo {

    public static void main(String[] args) {
        Properties properties=new PropertiesUtil("/comsumer.properties").loadProperties();
        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
        //设置主题
        kafkaConsumer.subscribe(Arrays.asList("tp2"));
        //消费
        while(true){
            ConsumerRecords<String,String> poll = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : poll) {
                System.out.println("offset"+record.offset()+","+record.key()+","+record.value());
            }

        }

    }
}


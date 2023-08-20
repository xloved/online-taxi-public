/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: redisTest
 * Author:   旭哥
 * Date:     2019/4/2 11:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.digui;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Executors;

/*import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hexin.common.utils.convert.DateUtil;
import com.hexin.common.utils.convert.StringUtil;
import com.hexin.common.utils.file.FileUtil;
import com.hexin.common.utils.lock.DistributedLock;*/
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/4/2
 * @since 1.0.0
 */
public class redisTest {

       /* static DistributedLock distributedLock=null;

        public static void main(String[] args) {

            ApplicationContext context=new FileSystemXmlApplicationContext("classpath:testApplicationContext.xml");
            distributedLock = (DistributedLock)context.getBean("distributedLock");

            ExecutorService service = Executors.newCachedThreadPool();
            for(int i =0;i<10000;i++){
                new Thread(()->{
                    try {
                        testLock();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
//		service.shutdown();
        }

        *//**
         * 锁测试
         * @author csn V1.0 2019年3月28日 下午4:48:50 void
         * @throws IOException
         *//*
        public static  void testLock() throws IOException {
            String requestId = distributedLock.getRequestId();
            String lockKey = "123456";
            long time = 60*1000;//如果没有释放锁，60秒自动解锁
            try {
                boolean lockStatus = distributedLock.getLock(lockKey, requestId, time);
                if(!lockStatus){
                    return;
                }
                if(!StringUtil.isNull(distributedLock.get(lockKey))){
                    System.out.println(123);
                }
                FileUtils.write(new File("C:\\Users\\hx\\Desktop\\redis测试\\redis.txt"), "已拿到锁！开始执行业务。。。。"+Thread.currentThread().getName()+","+requestId+","+DateUtil.getYMDHMSS()+"\r\n", true);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                boolean flag = distributedLock.unLock(lockKey, requestId);
                if(flag){
                    FileUtils.write(new File("C:\\Users\\hx\\Desktop\\redis测试\\redis.txt"), "已释放锁。。。。"+Thread.currentThread().getName()+","+requestId+","+DateUtil.getYMDHMSS()+"\r\n", true);
                }
            }
        }*/
    }



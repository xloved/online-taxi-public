/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: redisSstock
 * Author:   旭哥
 * Date:     2019/4/2 11:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.digui;

import java.util.Collections;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/4/2
 * @since 1.0.0
 */
public class redisSstock {
    /*
     *  释放锁
	 * @author csn V1.0 2019年3月28日 下午3:35:59
     * @param key
	 * @return boolean
	 */
   /* public boolean unLock(final String lockKey,final String requestId){
        Long status=null;
        try {
            status = redisTemplate.execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection connection)
                        throws DataAccessException {

                    Object nativeConnection = connection.getNativeConnection();
                    // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                    // 集群模式
                    Long result=null;
                    if (nativeConnection instanceof JedisCluster) {
                        logger.info("key：{}，集群模式开始释放锁",lockKey);
                        result =  (Long) ((ScriptingCommands) nativeConnection).eval(UNLOCK_LUA, Collections.singletonList(lockKey), Collections.singletonList(requestId));
                    }
                    // 单机模式
                    else if (nativeConnection instanceof Jedis) {
                        logger.info("key：{}，单机模式开始释放锁",lockKey);
                        result = (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, Collections.singletonList(lockKey), Collections.singletonList(requestId));
                    }
                    logger.info("key：{},{}",lockKey,(result!=null&&result>0)?"已释放锁":"未释放锁");
                    return result;
                }
            });
        } catch (Exception e) {
            logger.error("释放锁出现异常：",e);
        }
        return (status!=null&&status>0);
    }*/

}


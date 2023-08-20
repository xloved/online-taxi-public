/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: redisHstock
 * Author:   旭哥
 * Date:     2019/4/2 11:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xu.digui;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 旭哥
 * @create 2019/4/2
 * @since 1.0.0
 */
public class redisHstock {
    /**
     * 获取锁
     * @author csn V1.0 2019年3月27日 下午2:08:42
     * @param lockKey 加锁的key
     * @param milliSeconds 存活时间单位：毫秒
     * @return boolean 如果锁成功则返回true 否则返回false
     */
  /*  public boolean getLock(final String lockKey,final String requestId,final long milliSeconds){
        String status=null;
        try {
            status = redisTemplate.execute(new RedisCallback<String>() {
                @Override
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    JedisCommands jedis = (JedisCommands) connection.getNativeConnection();
                    //nx = not exist 则设置, px= 单位是毫秒，此方法是单线程进入，设置成功返回OK，否则返回null
                    String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, milliSeconds);;
                    logger.info("{}获取锁，key:{}",(LOCK_SUCCESS.equals(result))?"已":"未",lockKey);
                    return result;
                }
            });
        } catch (Exception e) {
            logger.error("获取锁出现异常：",e);
        }
        return LOCK_SUCCESS.equals(status);
    }*/

}


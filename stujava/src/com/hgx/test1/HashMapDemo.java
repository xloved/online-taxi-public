package com.hgx.test1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {

    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("san","张三");
        map.put("li","李四");
        map.put("wang","王二");
        map.put("ha","哈哈");
        map.put("wu","吴用");
        map.put("liu","刘某");
        System.out.println("*********输出map*********");
        System.out.println(map);

        System.out.println("*********foreach遍历map中的key**********");
        Set<String> set = map.keySet();
        for (String key: set) {
            System.out.print(key + " ");
        }
        System.out.println();

        System.out.println("*******foreach遍历map中的value************");
        Collection<String> collection = map.values();
        for (String coll:collection) {
            System.out.print(coll+ " ");
        }
        System.out.println();

        Set<String> keys2 = map.keySet();
        for (String keys: keys2) {
            System.out.print(keys+ " :" + map.get(keys)+ " ");
        }
        System.out.println();


        Set<Map.Entry<String,String>> entries = map.entrySet();
        for (Map.Entry<String,String> entrys: entries) {
            System.out.print(entrys.getKey()+ "--" + entrys.getValue());
        }
        System.out.println();


        System.out.println(map.size());
        System.out.println(map.isEmpty());
        System.out.println(map.remove("san"));
        System.out.println(map);
        System.out.println(map.get("li"));
        System.out.println(map.containsKey("li"));
        System.out.println(map.containsValue("李四"));
        System.out.println(map.replace("li","李四1"));
        System.out.println(map);
    }
}

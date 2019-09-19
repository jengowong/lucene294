package org.apache.lucene.demo.test;

import com.google.common.collect.Maps;
import org.apache.lucene.LucenePackage;

import java.util.Map;

/**
 * {TestLucenePackage}
 */
public class TestLucenePackage {

    public static void main(String[] args) {
        Package pkg = LucenePackage.get();
        System.out.println(pkg);

        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("java.vendor"));
        System.out.println(System.getProperty("sun.arch.data.model"));

        Map<String, String> map = Maps.newTreeMap();
        for (Map.Entry<Object, Object> kv : System.getProperties().entrySet()) {
            map.put((String) kv.getKey(), (String) kv.getValue());
        }
        for (Map.Entry<String, String> kv : map.entrySet()) {
            System.out.println(kv.getKey() + "-->" + kv.getValue());
        }
    }

}

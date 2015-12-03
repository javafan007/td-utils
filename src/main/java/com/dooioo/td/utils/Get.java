package com.dooioo.td.utils;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 提供所有获取值的操作
 *
 * @author 闷骚乔巴
 * @since 15/12/3
 */
public final class Get {

    public static class map {

        public static <K> String getString(final Map<? super K, ?> map, final K key) {
            return StringUtils.defaultString(MapUtils.getString(map, key));
        }
    }

    public static void main(String[] args) {
        Map<String, String> strMap = new HashMap<String, String>(){{
            put("cc", "cc");
        }};

        System.out.println(Get.map.getString(strMap, "cc"));
        System.out.println(Get.map.getString(strMap, "tt"));
    }
}

package com.dooioo.td.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;

/**
 * Created by chopperkuang on 15/12/7.
 *
 * @author 闷骚乔巴
 * @since 15/12/7
 */
@RunWith(JUnit4.class)
public class TestIs {

    @Test
    public void testStringEmpty() {

    }

    @Test
    public void testObjectEmpty() {

        String nullStr = null;
        String emptyStr = "";
        String emptyAndSpaceStr = " ";
        String notEmpty = "cc";

        Assert.assertTrue(Is.empty(nullStr));
        Assert.assertTrue(Is.empty(emptyStr));
        Assert.assertTrue(Is.empty(emptyAndSpaceStr));
        Assert.assertFalse(Is.empty(notEmpty));


        Map<String, String> emptyMap = new HashMap<>();
        Map<String, String> notEmptyMap = new HashMap<String, String>(){{
            put("test", "ok");
        }};

        Assert.assertTrue(Is.empty(emptyMap));
        Assert.assertFalse(Is.empty(notEmptyMap));

        List nullList = null;
        List<String> emptyList = new ArrayList<>();
        List<String> notEmptyList = Arrays.asList("cc");

        Assert.assertTrue(Is.empty(emptyList));
        Assert.assertTrue(Is.empty(nullList));
        Assert.assertFalse(Is.empty(notEmptyList));

        String [] emptys = null;
        String [] notEmptys = new String[]{"1", "2"};

        Assert.assertTrue(Is.empty(emptys));
        Assert.assertFalse(Is.empty(notEmptys));
    }

    @Test
    public void testUrl() {
        Assert.assertTrue(Is.url("http://sh.lianjia.com/"));
        Assert.assertTrue(Is.url("https://sh.lianjia.com/"));
        Assert.assertTrue(Is.url("http://sh.lianjia.com"));
        Assert.assertTrue(Is.url("http://sh.lianjia.com:8080"));
        Assert.assertTrue(Is.url("ftp://sh.lianjia.com"));

        Assert.assertFalse(Is.url("ftp://192.168.1.1"));
        Assert.assertFalse(Is.url("http://localhost:8080"));
        Assert.assertFalse(Is.url("http://127.0.0.1:8080"));
        Assert.assertFalse(Is.url(""));
    }

    @Test
    public void testEmail() {
        Assert.assertTrue(Is.email("Chopperkuang@gmail.com"));
        Assert.assertTrue(Is.email("chopperkuang@gmail.com"));
        Assert.assertTrue(Is.email("中国@gmail.com"));

        Assert.assertFalse(Is.email("chopperkuanggmail.com"));
        Assert.assertFalse(Is.email("ch"));
        Assert.assertFalse(Is.email(""));
    }

    @Test
    public void testMobile() {
        Assert.assertTrue(Is.mobile("15601622811"));
        Assert.assertTrue(Is.mobile("13512341234"));
        Assert.assertTrue(Is.mobile("013512341234"));
        Assert.assertTrue(Is.mobile("015601622811"));
        Assert.assertTrue(Is.mobile("0156 0162 2811"));
        Assert.assertTrue(Is.mobile("0 1560 1622 811"));
        Assert.assertTrue(Is.mobile("08615601622811"));
        Assert.assertTrue(Is.mobile("8615601622811"));
        Assert.assertTrue(Is.mobile("+8615601622811"));

        Assert.assertFalse(Is.mobile("25601622811"));
        Assert.assertFalse(Is.mobile("256016228111"));
        Assert.assertFalse(Is.mobile("2560162281111"));
        Assert.assertFalse(Is.mobile("6370123"));
        Assert.assertFalse(Is.mobile("2"));
    }

    @Test
    public void testIP() {
        Assert.assertTrue(Is.ip("192.168.1.1")); //ipv4
        Assert.assertTrue(Is.ip("255.255.1.1")); //ipv4
        Assert.assertTrue(Is.ip("127.0.0.1")); //ipv4
        Assert.assertTrue(Is.ip("123.123.123.123")); //ipv4
//        Assert.assertTrue(Is.ip("2001:DB8:0:0:1::1")); //ipv6
//        Assert.assertTrue(Is.ip("1:50:198:2::1:2:8")); //ipv6
//        Assert.assertTrue(Is.ip("2000::1:2345:6789:abcd")); //ipv6

        Assert.assertFalse(Is.ip("1.2.3"));

    }
}

package com.dooioo.td.utils;

import com.sun.istack.internal.NotNull;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.trim;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Micro check tools
 *
 * @author 闷骚乔巴
 * @since 15/12/3
 */
public final class Is  {

    //=============
    //start String.
    /**
     * 字符串为空的检查
     * empty ("") or null.
     *
     * <pre>
     * Is.empty(null)      = true
     * Is.empty("")        = true
     * Is.empty(" ")       = true
     * Is.empty("bob")     = false
     * Is.empty("  bob  ") = false
     * </pre>
     *
     * @param s
     * @return
     */
    public static boolean empty(final String s) {
        return isEmpty(trim(s));
    }

    /**
     * 对象为空的检查
     * empty ("") or null.
     *
     * <pre>
     * Is.empty(null)      = true
     * Is.empty("")        = true
     * Is.empty(" ")       = true
     * Is.empty("bob")     = false
     * Is.empty("  bob  ") = false
     * </pre>
     * @param o
     * @return
     */
    public static boolean empty(final Object o) {
        return o == null || empty(o.toString());
    }

    //~end String.

    //=============
    //start Object.
    /**
     * 是否相等(Null-safe)
     *
     * <pre>
     * ObjectUtils.equals(null, null)                  = true
     * ObjectUtils.equals(null, "")                    = false
     * ObjectUtils.equals("", null)                    = false
     * ObjectUtils.equals("", "")                      = true
     * ObjectUtils.equals(Boolean.TRUE, null)          = false
     * ObjectUtils.equals(Boolean.TRUE, "true")        = false
     * ObjectUtils.equals(Boolean.TRUE, Boolean.TRUE)  = true
     * ObjectUtils.equals(Boolean.TRUE, Boolean.FALSE) = false
     * </pre>
     *
     * @param object1  the first object, may be {@code null}
     * @param object2  the second object, may be {@code null}
     * @return
     */
    public static boolean equals(final Object object1, final Object object2) {
        return Objects.equals(object1, object2);
    }

    //~end Object.

    //=============
    //start RegExp.
    /**
     * 检查email
     *
     * @param s
     * @return
     */
    public static boolean email(@NotNull final String s) {
        if(empty(s)) return false;

        return EMAIL.matcher(s).find();
    }

    public static boolean url(@NotNull final String s) {
        if(empty(s)) return false;

        return URL.matcher(s).find();
    }
    //~end RegExp.

    /**
     * 所有取非的操作
     */
    public final static class not {

        public static boolean empty(String s) {
            return !Is.empty(s);
        }

        public static boolean empty(Object o) {
            return !Is.empty(o);
        }

        public static boolean email(@NotNull final String s) {
            return !Is.empty(s);
        }

        public static boolean url(@NotNull final String s) {
            return !Is.url(s);
        }

    }

    private static final Pattern EMAIL = Pattern.compile("^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))$", Pattern.CASE_INSENSITIVE);
    private static final Pattern URL = Pattern.compile("^(?:(?:https?|ftp):\\/\\/)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))(?::\\d{2,5})?(?:\\/\\S*)?$", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) {
        Object o = null;

        System.out.println(Is.not.empty("")); //false
        System.out.println(Is.empty(""));     //true
        System.out.println(Is.empty(o));      //true
        System.out.println(Is.not.empty(o));  //false

        System.out.println("email ==> ");
        System.out.println(Is.email("ch"));  //false
        System.out.println(Is.not.email("ch"));  //true
        System.out.println(Is.email("Chopperkuang@gmail.com"));  //true
        System.out.println(Is.email("中国@gmail.com")); //true

        System.out.println("url ==> ");
        System.out.println(Is.url("http://sh.lianjia.com/")); //true
        System.out.println(Is.url("https://sh.lianjia.com/")); //true
        System.out.println(Is.url("ftp://192.168.1.1")); //true
        System.out.println(Is.not.url("中国@gmail.com")); //true
        System.out.println(Is.not.url("")); //true
    }
}

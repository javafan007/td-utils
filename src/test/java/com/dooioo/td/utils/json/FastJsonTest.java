package com.dooioo.td.utils.json;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * fastjson example，供学习用，并不是去测试FastJson
 *
 * @author 闷骚乔巴
 * @since 15/12/2
 */

@RunWith(JUnit4.class)
public class FastJsonTest {

    @Test
    public void testUser() {
        User user = new User();
        user.setId(83639);
        user.setName("chopperkuang");
        String text = JSON.toJSONString(user);
        String retText = "{\"id\":83639,\"name\":\"chopperkuang\"}";
        System.out.println(text);

        User userforJson = JSON.parseObject(retText, User.class);
        Assert.assertEquals(user, userforJson);

    }

    @Test
    public void testUserField() {
        UserField user = new UserField();
        user.setId(83639);
        user.setName("chopperkuang");
        String text = JSON.toJSONString(user);
        String retText = "{\"userCode\":83639,\"name\":\"chopperkuang\"}";

        System.out.println(text);

        UserField userforJson = JSON.parseObject(retText, UserField.class);
        Assert.assertEquals(user, userforJson);

    }

    @Test
    public void testJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 83639);
        jsonObject.put("name", "chopperkuang");

        String text = jsonObject.toJSONString();
        System.out.println(text);
    }

    @Test
    public void testMap() {
        Map<String, Object> map = new HashMap<String, Object>(){{
            put("id", 83639);
            put("name", "chopperkuang");
        }};

        String text = JSON.toJSONString(map);
        System.out.println(text);

        Map<String, Object> jsonToMap = JSON.parseObject(text, HashMap.class);
        System.out.println(jsonToMap);

        Assert.assertEquals(map, jsonToMap);
    }

    @Test
    public void testFilter() {
        ValueFilter filter = new ValueFilter() {

            public Object process(Object source, String name, Object value) {
                if (name.equals("name")) {
                    return "WSJ";
                }
                return value;
            }
        };

        NameFilter nameFilter = new NameFilter() {
            public String process(Object source, String name, Object value) {
                if (name.equals("id")) {
                    return "ID";
                }
                return name;
            }
        };

        String text = "{\"id\":123,\"name\":\"WJH\"}";

        Object object = JSON.parse(text);

        SerializeWriter out = new SerializeWriter();
        JSONSerializer serializer = new JSONSerializer(out);
        serializer.getValueFilters().add(filter);
        serializer.getNameFilters().add(nameFilter);

        serializer.write(object);

        String outText = out.toString();
        System.out.println(outText);
    }
}

class User extends Object {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object u) {

        User user = (User) u;

        return user.getId() == this.getId() && user.getName().equals(this.getName());
    }
}

class UserField extends Object {

    private int    id;
    private String name;

    @JSONField(name = "userCode")
    public int getId() { return id; }

    @JSONField(name = "userCode")
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean equals(Object u) {

        UserField user = (UserField) u;

        return user.getId() == this.getId() && user.getName().equals(this.getName());
    }
}

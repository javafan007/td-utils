经讨论，我们就统一采用`fastjson`进行`json`处理。

它的`API`设计简单，速度快，提供`JSONObject`等。

因`fastjson`的`API`已非常简洁，我们无需再做多余的封装，不再提供内部的工具包，自个儿去玩。

`fastjson`项目地址: [https://github.com/alibaba/fastjson](https://github.com/alibaba/fastjson)


提供以下几个简单的`example`，供上手参考，可在`com.dooioo.td.utils.json.FastJsonTest`中运行。

```java
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
```




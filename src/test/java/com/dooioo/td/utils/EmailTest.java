package com.dooioo.td.utils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevensong on 15/12/10.
 */
public class EmailTest {

    public static void main(String arg[]) throws Exception {
        sendEmail();
    }

    /**
     * senderEmail 为开始节点 是发送人的邮箱地址   参数为发件人的邮箱和密码
     * to   主收件人   参数可以是String类型，也可以是List类型 必填
     * cc   是抄送人，参数可以是String类型，List类型
     * bcc  是秘密抄送人，参数可以是String类型，List类型
     * attach   传附件，参数可以是String类型（单个附件路径），List类型（附件路径集合），Map类型（key为文件名，value为内容）
     * send()是结束节点，必须添加
     * @throws Exception
     */
    public static void sendEmail() throws Exception{
        List<String> list = new ArrayList<String>();
        list.add("ksjskbgshg@qq.com");
        list.add("");
        List<String> attachList = new ArrayList<>();
        attachList.add("");
        EmailUtils.senderEmail("", "")
                .to("")
                .to(list)
                .subject("邮件测试1")
                .content("Dear All,ahahahahahahahahahaa")
                .attach("")
                .attach(attachList)
                .send();
    }

}

package send;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2020/4/9
 */
public class Sender {

    /**
     * 使用加密的方式,利用465端口进行传输邮件,开启ssl
     * @param to    为收件人邮箱
     * @param message    发送的消息
     */
    public static void sendEmil(String host, String name, String pwd,String to, int times, String subject,String message) {
        System.out.println("send mail task, from -> "+name+", to -> "+to+", times -> " + times);
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            //设置邮件会话参数
            Properties props = new Properties();
            //邮箱的发送服务器地址
            props.setProperty("mail.smtp.host", host);
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            //邮箱发送服务器端口,这里设置为465端口
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.auth", "true");
            final String username = name;
            final String password = pwd;
            //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            //通过会话,得到一个邮件,用于发送
            Message msg = new MimeMessage(session);
            //设置发件人
            msg.setFrom(new InternetAddress(name));
            System.out.println("------------:" +msg.getFrom()[0].toString());
            //设置收件人,to为收件人,cc为抄送,bcc为密送
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
//            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(to, false));
//            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to, false));

            for (int i =1;i<=times;i++){
                System.out.println("正在发送第" + i + "封邮件");
                msg.setSubject(subject + i);
                //设置邮件消息
                msg.setText(message + i);
                //设置发送的日期
                msg.setSentDate(new Date());

                //调用Transport的send方法去发送邮件
                Transport.send(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        // kdstest001@163.com 发送 kdstest002@163.com
        Sender.sendEmil("smtp.163.com","kdstest001@163.com","IQNVQQMFXVFPPUTI","kdstest002@163.com",500,"subject","message");
        // kdstest001@163.com 发送 kdstest0001@gmail.com
        Sender.sendEmil("smtp.163.com","kdstest001@163.com","IQNVQQMFXVFPPUTI","kdstest0001@gmail.com",500,"subject","message");
        // kdstest0001@gmail.com 发送 kdstest002@163.com
//        Sender.sendEmil("smtp.gmail.com","kdstest0001@gmail.com","kaadas123","kdstest002@163.com",2,"subject","message");
        // kdstest0001@gmail.com 发送 chason777777@gmail.com
//        Sender.sendEmil("smtp.gmail.com","kdstest0001@gmail.com","kaadas123","chason777777@gmail.com",2,"subject","message");
    }

}

package receive;

import javax.mail.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2020/4/9
 */
public class ReceiveGm {
    //邮件通信会话
    Session session;
    //邮件接收处理对象
    Store store;
    BufferedReader br = null;
    private PrintWriter pw = null;
    private PrintWriter pw2 = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");


    // 337522212@qq.com
//        String username = "337522212@qq.com";
//        private String passwd = "dcsmsulchnpvbibj";
//        private String receiveHost = "imap.qq.com";

    // kdstest001@163.com
    // 163包含，开启认证，http://config.mail.163.com/settings/imap/index.jsp?kdstest001@163.com
//    String username = "kdstest001@163.com";
//    private String passwd = "IQNVQQMFXVFPPUTI";
//    private String receiveHost = "imap.163.com";

    // kdstest002@163.com
    // 163包含，开启认证，http://config.mail.163.com/settings/imap/index.jsp?kdstest002@163.com
//    String username = "kdstest002@163.com";
//    private String passwd = "VXZKANKCJTEEDRLK";
//    private String receiveHost = "imap.163.com";

    // kdstest0001@gmail.com
//    String username = "kdstest0001@gmail.com";
//    private String passwd = "kaadas123";
//    private String receiveHost = "imap.gmail.com";

    // kdstest0001@gmail.com
    String username = "chason777777@gmail.com";
    private String passwd = "lichao256808";
    private String receiveHost = "imap.gmail.com";

    /**
     * 邮件配置参数和连接接收邮件服务器
     *
     * @throws MessagingException
     */
    private void init() throws MessagingException {
        try {
//            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\windows10\\Desktop\\mail\\result1", false), "UTF-8"));
//            pw2 = new PrintWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\windows10\\Desktop\\mail\\originalData1", false), "UTF-8"));
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("./result1", false), "UTF-8"));
            pw2 = new PrintWriter(new OutputStreamWriter(new FileOutputStream("./originalData1", false), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        //设置发送和接收协议
        properties.put("mail.transport.protocal", "smtp");
        properties.put("mail.store.protocal", "imap");
        //设置ssl的端口
        properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.port", "993");
        properties.setProperty("mail.imap.socketFactory.port", "993");/*
        properties.put("mail.imap.port", "993");
        properties.put("mail.smtp.port", "465");*/
        //smtp认证
        properties.put("mail.smtp.auth", "true");
        //设置发送和接收处理类
        properties.put("mail.transport.class", "com.sun.mail.smtp.SMTPTransport");
        properties.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");
        //设置发送邮件服务器
//            properties.put("mail.smtp.host", sendHost);
        //获取邮件通信会话
        Authenticator auth = new MailAuthenticator();
        session = Session.getDefaultInstance(properties, auth);
        session.setDebug(true);
        //获取接收邮件对象
        store = session.getStore("imap");
        //连接接收邮件服务器
        store.connect(receiveHost, null, null);
    }

    /**
     * 关闭邮件接收服务器
     *
     * @throws MessagingException
     * @throws IOException
     */
    public void close() throws MessagingException, IOException {
        store.close();

    }

    /**
     * 接收邮件
     *
     * @throws Exception
     */
    public void receive() throws Exception {
        browseMessageFromFolder("INBOX");
    }

    /**
     * 根据邮件夹名称浏览邮件
     *
     * @param folderName
     * @throws Exception
     */
    private void browseMessageFromFolder(String folderName) throws Exception {
        Folder folder = store.getFolder(folderName);
        if (folder == null) throw new Exception(folderName + "邮件夹不存在");
        browseMessageFromFolder(folder);
    }

    /**
     * 根据邮件夹对象浏览邮件
     *
     * @param folder
     * @throws MessagingException
     * @throws IOException
     */
    private void browseMessageFromFolder(Folder folder) throws MessagingException, IOException {
        // TODO Auto-generated method stub
        folder.open(Folder.READ_ONLY);
        System.out.println("总共有" + folder.getMessageCount() + "封邮件");
        System.out.println("总共有" + folder.getUnreadMessageCount() + "封未读邮件");
        Message[] messages = folder.getMessages();
        for (int i = 1; i <= messages.length; i++) {
            pw.println("这是第" + i + "封邮件");
            pw2.println("这是第" + i + "封邮件");
            getMessageHeader(folder.getMessage(i));
            writeSubjectToOutPutStream(folder.getMessage(i));
        }
//            getMessageHeader(folder.getMessage(1));
//            writeSubjectToOutPutStream(folder.getMessage(1));
        folder.close(false);
    }

    /**
     * 遍历每封邮件的头部部分
     *
     * @param message
     * @throws MessagingException
     */
    private void getMessageHeader(Message message) throws MessagingException {
        try {
            // TODO Auto-generated method stub
            @SuppressWarnings("unchecked")
            Enumeration<Header> allHeader = message.getAllHeaders();
            for (; allHeader.hasMoreElements(); ) {
                Header header = allHeader.nextElement();
                pw2.println(header.getName() + ":" + header.getValue());
                String value = header.getValue().replace("\r\n", "");
                if (header.getName().equals("Received")) {
                    Date d1 = new Date(value.substring(value.lastIndexOf(",") + 2, value.lastIndexOf(":") + 3));
                    String sq = value.substring(value.lastIndexOf(":") + 4, value.lastIndexOf(":") + 7);
                    if (sq.indexOf("0") == 1)
                        sq = sq.substring(0, 1) + sq.substring(2, 3);
                    int sc = 8 - Integer.parseInt(sq);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d1);
                    cal.add(Calendar.HOUR, sc);
                    pw.println(header.getName() + ":" + sdf.format(cal.getTime()));
                } else if (header.getName().equals("Date")) {
                    Date d2 = new Date(header.getValue().substring(5, 25));
                    String sq = header.getValue().substring(26, 29);
                    if (sq.indexOf("0") == 1)
                        sq = sq.substring(0, 1) + sq.substring(2, 3);
                    int sc = 8 - Integer.parseInt(sq);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d2);
                    cal.add(Calendar.HOUR, sc);
                    pw.println(header.getName() + ":" + sdf.format(cal.getTime()));
                }else if (header.getName().equals("From"))
                    pw.println(header.getName() + ":" + header.getValue());
                else if (header.getName().equals("To"))
                    pw.println(header.getName() + ":" + header.getValue());
//                System.out.println(header.getName() + ":" + header.getValue());
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

    /**
     * 将每封邮件的主题写入输出流中
     *
     * @param message
     * @throws MessagingException
     */
    private void writeSubjectToOutPutStream(Message message) throws MessagingException {
        try {
            // TODO Auto-generated method stub
//            System.out.println("邮件主题为:" + message.getSubject());
            pw.println("邮件主题为:" + message.getSubject());
            pw2.println("邮件主题为:" + message.getSubject());
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

    public static void main(String[] args) {
        ReceiveGm receive = new ReceiveGm();
        try {
            receive.init();
            receive.receive();
            receive.pw.flush();
            receive.pw.close();
            receive.pw2.flush();
            receive.pw2.close();
            receive.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登陆认证
     *
     * @author u1
     */
    private class MailAuthenticator extends Authenticator {

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            // TODO Auto-generated method stub
            return new PasswordAuthentication(username, passwd);
        }

    }
}

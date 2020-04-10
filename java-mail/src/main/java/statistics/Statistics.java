package statistics;

import java.io.*;
import java.text.SimpleDateFormat;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2020/4/10
 */
public class Statistics {
    public static void main(String[] args) {
        BufferedReader br = null;
        PrintWriter pw = null;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\windows10\\Desktop\\mail\\kds\\chason777777@gmail.com\\result-kdstest002")));
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\windows10\\Desktop\\mail\\kds\\chason777777@gmail.com\\statistics-kdstest002", false), "UTF-8"), true);

            int index = 0;
            long count = 0;
            long max = 0;
            long avg = 0;
            int b10 = 0;
            int b30 = 0;
            int b60 = 0;
            int b120 = 0;
            int b180 = 0;
            int b240 = 0;
            int b300 = 0;


            String line = null;
            boolean lastDateFlag = false;
            String lastDate = null;
            String sendDate = null;
            String from = null;
            String to = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("这是第")) {
                    pw.println(line);
                    lastDateFlag = false;
                    index++;
                }
                if (line.startsWith("Received") && !lastDateFlag) {
                    lastDate = line.substring(9);
                    lastDateFlag = true;
                }
                if (line.startsWith("Date")) {
                    sendDate = line.substring(5);
                    long sc = (sdf.parse(lastDate).getTime() - sdf.parse(sendDate).getTime())/1000;
                    pw.println("接收延时：" + sc + "s");
                    if (sc >= 10)
                        b10++;
                    if (sc >= 30)
                        b30++;
                    if (sc >= 60)
                        b60++;
                    if (sc >= 120)
                        b120++;
                    if (sc >= 180)
                        b180++;
                    if (sc >= 240)
                        b240++;
                    if (sc >= 300)
                        b300++;
                 count += sc;
                 if (sc > max)
                     max = sc;
                }
            }
            // 统计
            pw.println();
            pw.println("统计条数：" + index);
            pw.println("总延时：" + count + "s");
            pw.println("最大延时：" + max + "s");
            pw.println("平均延时：" + (count/(index * 1.0)) + "s");
            pw.println("大于10s：" + b10);
            pw.println("大于30s：" + b30);
            pw.println("大于60s：" + b60);
            pw.println("大于120s：" + b120);
            pw.println("大于240s：" + b240);
            pw.println("大于300s：" + b300);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

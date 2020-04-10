package statistics;

import java.io.*;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2020/4/10
 */
public class Detachment {
    public static void main(String[] args) {
        BufferedReader br = null;
        PrintWriter pw = null;
        PrintWriter pw2 = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\windows10\\Desktop\\mail\\kds\\chason777777@gmail.com\\result1")));
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\windows10\\Desktop\\mail\\kds\\chason777777@gmail.com\\result-kdstest001", false), "UTF-8"), true);
            pw2 = new PrintWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\windows10\\Desktop\\mail\\kds\\chason777777@gmail.com\\result-kdstest002", false), "UTF-8"), true);

            String line = null;
            String group = null;
            String flag = null;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("这是第")) {
                    if (flag != null) {
                        if (flag.equals("kdstest001")) {
                            pw.println(group.substring(0, group.length() -4));
                        } else {
                            pw2.println(group.substring(0, group.length() -4));
                        }
                    }
                    flag = null;
                    group = line + "\r\n";
                }else if (line.startsWith("From:kdstest001")){
                    flag = "kdstest001";
                    group += (line + "\r\n");
                }else if (line.startsWith("From:kdstest002")){
                    flag = "kdstest002";
                    group += (line + "\r\n");
                }else {
                    group += (line + "\r\n");
                }
            }
            if (flag != null) {
                if (flag.equals("kdstest001")) {
                    pw.println(group.substring(0, group.length() -4));
                } else {
                    pw2.println(group.substring(0, group.length() -4));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

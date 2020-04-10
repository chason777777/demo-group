/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2020/3/28
 */
public class Test {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        },"666");
        thread.start();
        System.out.println(Thread.currentThread().getName());
    }
}

package Learning_HeadFirst.Thread;

public class MyThread extends Thread implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello toi la thread 1");
    }
}

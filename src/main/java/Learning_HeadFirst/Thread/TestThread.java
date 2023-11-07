package Learning_HeadFirst.Thread;

public class TestThread {


    public static void main(String[] args) {
        Thread myThread1 = new MyThread();
        Thread myThread2 = new Thread(() -> System.out.println("Hello tui la thread 2"));

        MyRunnable myRunnable = new MyRunnable() ;
        Thread myThread3 = new Thread(myRunnable);

        myThread2.start();
        myThread3.start();
        myThread1.start();

        System.out.println(myThread1.getName());
        System.out.println(myThread2.getName());
        System.out.println(myThread3.getName());
    }
}

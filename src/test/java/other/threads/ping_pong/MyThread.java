package other.threads.ping_pong;


/**Example of 2 parallel threads**/
public class MyThread implements Runnable {

    public void run() {
        System.out.printf("%s thread started... \n", Thread.currentThread().getName());

        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName()+ " is working:"+ i+" step");
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
            }

        }
        System.out.printf("%s thread finished... \n", Thread.currentThread().getName());
    }

}


class Run {
    public static void main(String[] args) throws InterruptedException {
        MyThread mgt1 = new MyThread();
        MyThread mgt2 = new MyThread();
        Thread tr1 = new Thread(mgt1);
        Thread tr2 = new Thread(mgt2);
        tr1.start();
        Thread.sleep(100);
        tr2.start();
    }
}

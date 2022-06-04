package threads;

public class MyTgread extends Thread {

    public void run() {
        System.out.printf("%s thread started... \n", Thread.currentThread().getName());

        for (int i = 0; i < 10; i++) {
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
    public static void main(String[] args) {
        MyTgread mgt1 = new MyTgread();
        MyTgread mgt2 = new MyTgread();
        mgt1.start();
        mgt2.start();
    }
}

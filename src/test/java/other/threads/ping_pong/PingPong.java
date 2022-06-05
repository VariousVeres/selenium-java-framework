package other.threads.ping_pong;

class MyThread1 extends Thread {
    String phrase;
    static int steps;

    public MyThread1(String str)  {
        phrase=str;
    }
    @Override
    public void run()  {
        for (int i=0;i< steps; i++)  {
            System.out.print(Thread.currentThread().getName()+ ": "+phrase);
            try{Thread.sleep(2000);} catch (Exception e) {}
        }
    }
}


public class PingPong {

    public static void main(String[] args) throws InterruptedException {
        MyThread1.steps=10;
        MyThread1 tr1 = new MyThread1("Ping \n");
        MyThread1 tr2 = new MyThread1("Pong \n");
        tr1.start();
        Thread.sleep(200);
        tr2.start();
    }



}

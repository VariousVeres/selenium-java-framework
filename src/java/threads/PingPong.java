package threads;

class MyThread extends Thread {
    String phrase;
    static int steps;

    public MyThread(String str, int number)  {
        phrase=str;
        steps=number;
    }
    @Override
    public void run()  {
        for (int i=0;i< steps; i++)  {
            System.out.print(Thread.currentThread().getName()+ ": "+phrase);
            try{Thread.sleep(2000);} catch (Exception e) {}
        }
    }
}


public class PingPong  {

    public static void main(String[] args) throws InterruptedException {
        MyThread tr1 = new MyThread("Ping \n", 10);
        MyThread tr2 = new MyThread("Pong \n", 10);
        tr1.start();
        Thread.sleep(10);
        tr2.start();
    }



}

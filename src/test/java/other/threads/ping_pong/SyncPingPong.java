package other.threads.ping_pong;

/**
 * Compicated realization with threads
 **/

class Say {
    public void say(String phrase) {
        System.out.println(phrase);
    }
}

public class SyncPingPong {
    public static void main(String[] args) {
        Say say = new Say();
        Thread ping = new PingPongThread(say, "Ping");
        Thread pong = new PingPongThread(say, "Pong");
        ping.start();
        pong.start();
    }
}

class PingPongThread extends Thread {

    private Say say;
    private String name;

    public PingPongThread(Say sayObj, String name) {
        this.say = sayObj;
        this.name = name;
    }

    @Override
    public void run() {

        synchronized (say) {
            for (int i = 1; i <= 5; i++) {
                try {
                    say.say(name);
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    //Зупиняю лок і дозволяю іншому потоку підхватити
                    say.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


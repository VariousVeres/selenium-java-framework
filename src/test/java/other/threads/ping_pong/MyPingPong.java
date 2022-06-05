package other.threads.ping_pong;

/**Usual realisation without threads**/
public class MyPingPong {

    public static int amount =23;

    public static void main(String[] args) {
        Player p1 = new Player("Ping");
        Player p2 = new Player("Pong");
        p1.setNextPlayer(p2);
        p2.setNextPlayer(p1);
        p1.say();
    }
}


class Player {

    public static int amount = MyPingPong.amount;
    private String name;
    Player nextPlayer;

    Player(String str) {
        name = str;
    }

    void say() {
        while (amount > 0) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.getStackTrace();
            }
            amount--;
            System.out.println(name);
            nextPlayer.say();
        }
    }

    public void setNextPlayer(Player p) {
        nextPlayer = p;
    }

}

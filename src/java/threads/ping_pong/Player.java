package threads.ping_pong_threads;

public class Player implements Runnable {

    private final String text;

    private int turns = Game.MAX_TURNS;

    private Player nextPlayer;

    private boolean mustPlay = false;

    public Player(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        while(turns>0) {

            while (!mustPlay); //Busy Waiting

            System.out.println(text);
            turns--;

            this.mustPlay = false;
            nextPlayer.mustPlay = true;

        }
    }



    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public void setMustPlay(boolean mustPlay) {
        this.mustPlay = mustPlay;
    }
}

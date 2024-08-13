public class Player {
    private final int playerId;
    private int curPosition;

    public Player(int playerId){
        this.playerId = playerId;
        this.curPosition = 0;
    }

    public int getPlayerId(){
        return playerId;
    }

    public int getCurPosition() {
        return curPosition;
    }

    public void setCurPosition(int curPosition) {
        this.curPosition = curPosition;
    }
}

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    private final int diceCount;

    public Dice(int diceCount){
        this.diceCount = diceCount;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public synchronized int rollDice(){
        int totalValue = 0;

        for(int dice=1;dice<=diceCount;dice++){
            totalValue+=(ThreadLocalRandom.current().nextInt(1,7));
        }

        return totalValue;
    }
}

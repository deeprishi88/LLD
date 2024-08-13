import java.util.Deque;
import java.util.LinkedList;

public class Game {
    Board board;
    Dice dice;
    Deque<Player> players = new LinkedList<>();
    Player winner = null;

    public Game(int playerCount){
        this.board = new Board(10,4,5);
        this.dice = new Dice(1);
        addPlayers(playerCount);
    }

    void addPlayers(int playerCount){
        for(int player=1;player<=playerCount;player++){
            Player curPlayer = new Player(player);
            players.add(curPlayer);
        }
    }

    synchronized void startGame(){
        while(winner == null){
            Player curPlayer = players.removeFirst();
            System.out.println(curPlayer.getPlayerId() + "'s turn , current position = " + curPlayer.getCurPosition());

            int diceValue = dice.rollDice();

            if(curPlayer.getCurPosition() + diceValue >= board.getBoardSize()*board.getBoardSize()){
                System.out.println("Got more value, try again");
                continue;
            }

            int newPosition = curPlayer.getCurPosition() + diceValue;
            System.out.println(curPlayer.getPlayerId() + "'s new position = " + newPosition);
            newPosition = jumpCheck(newPosition);
            curPlayer.setCurPosition(newPosition);
            System.out.println(curPlayer.getPlayerId() + "'s final position = " + curPlayer.getCurPosition());

            players.addLast(curPlayer);

            if(curPlayer.getCurPosition() == (board.getBoardSize()*board.getBoardSize())-1){
                winner = curPlayer;
            }
        }
        System.out.println(winner.getPlayerId() + " is Winner");
    }

    int jumpCheck(int position){
        if(position >= (board.getBoardSize()*board.getBoardSize())-1){
            return position;
        }

        Cell cell = board.getCell(position);

        if(cell.getJump()!=null && cell.getJump().startPosition==position){
            System.out.println(cell.getJump().endPosition<cell.getJump().startPosition?"Snake":"Ladder");
            return cell.getJump().endPosition;
        }

        return position;
    }
}

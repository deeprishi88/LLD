import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SnakeAndLadderGame snakeAndLadderGame = new SnakeAndLadderGame(10,1);
        Scanner sc = new Scanner(System.in);
        int snakesCnt = sc.nextInt();
        for(int i=0;i<snakesCnt;i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            snakeAndLadderGame.addJump(start,end);
        }

        int ladderCnt = sc.nextInt();
        for(int i=0;i<ladderCnt;i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            snakeAndLadderGame.addJump(start,end);
        }

        int playerCnt = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<playerCnt;i++){
            String name = sc.nextLine();
            snakeAndLadderGame.addPlayer(name);
        }

        snakeAndLadderGame.startGame();
    }
}

class SnakeAndLadderGame {
    Board board;
    Dice dice;
    Deque<Player> players = new LinkedList<>();
    Player winner = null;

    public SnakeAndLadderGame(int boardSize,int diceCnt){
        this.board = new Board(boardSize);
        this.dice = new Dice(diceCnt);
    }

    public void addJump(int start, int end){
        board.addSnakesOrLadder(start, end);
    }

    public void addPlayer(String name){
        Player player = new Player(name);
        players.add(player);
    }

    public void startGame() {
        System.out.println("Game Starts");
        while(winner == null){
            Player curPlayer = players.removeFirst();

            int diceRollValue = dice.rollDice();
            int oldpos = curPlayer.getCurpos();

            int newPos = curPlayer.getCurpos() + diceRollValue;
            if(newPos > 100){
                players.addLast(curPlayer);
            }

            while(board.getCells(newPos).getJump() != null && board.getCells(newPos).getJump().startPos == newPos){
                newPos = board.getCells(newPos).getJump().endPos;
            }

            curPlayer.setCurpos(newPos);
            players.addLast(curPlayer);

            System.out.println(curPlayer.getName() + " rolled a " + diceRollValue + " and moved from " + oldpos + " to " + newPos);

            if(newPos == board.getSize()*board.getSize()){
                winner = curPlayer;
            }

        }
        System.out.println(winner.getName() + " wins the game");
    }

}

class Board{
    Cell[][] board;
    int size;
    public Board(int n){
        this.size = n;
        initialiseBoard(n);
    }

    private void initialiseBoard(int n){
        board = new Cell[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                Cell cell = new Cell();
                board[i][j] = cell;
            }
        }
    }

    public int getSize(){
        return size;
    }

    public void addSnakesOrLadder(int start, int end){
        Jump jump = new Jump(start, end);

        Cell cell = getCells(start);
        if(cell.getJump() == null){
            cell.setJump(jump);
        }
    }

    public Cell getCells(int pos){
        pos = pos-1;
        int row = pos/10;
        int col = pos%10;
        return board[row][col];
    }
}


class Cell{
    Jump jump;
    public Jump getJump(){
        return jump;
    }
    public void setJump(Jump jump){
        this.jump = jump;
    }
}

class Jump{
    int startPos;
    int endPos;
    public Jump(int startPos, int endPos){
        this.startPos = startPos;
        this.endPos = endPos;
    }
}

class Dice {
    int diceCnt;
    public Dice(int diceCnt){
        this.diceCnt = diceCnt;
    }

    public int rollDice(){
        int move=0;
        for(int i=0;i<diceCnt;i++){
            move += (int)(Math.random() * (6 - 1)) + 1;
        }
        return move;
    }
}

class Player{
    String name;
    int curpos;
    public Player(String name){
        this.name = name;
        this.curpos = 0;
    }
    public String getName(){
        return name;
    }
    public int getCurpos(){
        return curpos;
    }
    public void setCurpos(int pos){
        this.curpos = pos;
    }
}
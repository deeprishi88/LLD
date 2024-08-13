import board.Board;
import player.Player;
import playingPiece.PlayingPiece;
import playingPiece.PlayingPieceO;
import playingPiece.PlayingPieceX;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    Deque<Player> players;
    Board board;
    int size;

    public TicTacToe(int size) {
        this.size = size;

        players = new LinkedList<>();

        PlayingPiece playingPieceX = new PlayingPieceX();
        Player player1 = new Player("Player1", playingPieceX);

        players.add(player1);

        PlayingPiece playingPieceO = new PlayingPieceO();
        Player player2 = new Player("Player2", playingPieceO);

        players.add(player2);

        board = new Board(size);
    }

    public String startGame() {
        int count = size*size;
        while(count>0){
            List<List<Integer>> freeCells = board.getFreeCells();
            if(freeCells.isEmpty()){
                break;
            }

            Player curPlayer = players.removeFirst();

            System.out.println("Player: " + curPlayer.getName() + " enter row,col : ");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            String[] input = s.split(",");
            int row = Integer.parseInt(input[0]);
            int col = Integer.parseInt(input[1]);

            boolean pieceAddedSuccess = board.addPiece(row,col,curPlayer.getPlayingPiece());
            if(!pieceAddedSuccess){
                System.out.println("Incorrect position choosen, Try Again");
                players.addFirst(curPlayer);
                continue;
            }
            players.addLast(curPlayer);

            board.printBoard();

            boolean winner = findWinner(row,col,curPlayer.getPlayingPiece());
            if(winner){
                System.out.println(curPlayer.getName() + " is winner and playingpiece is " + curPlayer.getPlayingPiece());
                return "Winner = " + curPlayer.getName();
            }
            count--;
        }
        System.out.println("No Winner");
        board.printBoard();
        return "Tie";
    }

    boolean findWinner(int r, int c, PlayingPiece playingPiece){
        boolean row = false, col = false, diagonal = false, antiDiagonal = false;

        int rcount=0;
        for(int j=0;j<size;j++){
            if(board.getCellValue(r,j)!=null &&
                    board.getCellValue(r, j).getPieceType().name().equals(playingPiece.getPieceType().name())){
                rcount++;
            }
        }
        if(rcount==size){
            row=true;
        }

        int ccount=0;
        for(int i=0;i<size;i++){
            if(board.getCellValue(i, c)!=null &&
                    board.getCellValue(i, c).getPieceType().name().equals(playingPiece.getPieceType().name())){
                ccount++;
            }
        }
        if(ccount==size){
            col=true;
        }

        int diagCount=0;
        for(int i=0;i<size;i++){
            if(board.getCellValue(i,i)!=null &&
                    board.getCellValue(i,i).getPieceType().name().equals(playingPiece.getPieceType().name())){
                diagCount++;
            }
        }
        if(diagCount == size){
            diagonal = true;
        }

        int antiDiagCount=0;
        for(int i=0;i<size;i++){
            if(board.getCellValue(i,size-i-1)!=null &&
                    board.getCellValue(i,size-i-1).getPieceType().name().equals(playingPiece.getPieceType().name())){
                antiDiagCount++;
            }
        }
        if(antiDiagCount == size){
            antiDiagonal = true;
        }

        return row || col || diagonal || antiDiagonal;
    }
}

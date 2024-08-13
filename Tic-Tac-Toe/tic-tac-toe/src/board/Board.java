package board;

import playingPiece.PlayingPiece;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public int size;
    public PlayingPiece[][] board;

    public Board(int size){
        this. size = size;
        board = new PlayingPiece[size][size];
    }

    public PlayingPiece getCellValue(int row,int col){
        return board[row][col];
    }

    public boolean addPiece(int row,int col, PlayingPiece piece){
        if(row>=size || col>=size || board[row][col] != null){
            return false;
        }
        board[row][col] = piece;
        return true;
    }

    public List<List<Integer>> getFreeCells(){
        List<List<Integer>> freeCells = new ArrayList<>();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j] == null){
                    List<Integer> cell = new ArrayList<>();
                    cell.add(i);
                    cell.add(j);
                    freeCells.add(cell);
                }
            }
        }
        return freeCells;
    }

    public void printBoard(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].getPieceType().name() + "   ");
                } else {
                    System.out.print("    ");

                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}

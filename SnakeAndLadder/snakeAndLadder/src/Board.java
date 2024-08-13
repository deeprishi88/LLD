import java.util.concurrent.ThreadLocalRandom;

public class Board {
    Cell[][] board;

    Board(int boardSize,int snakesCount,int ladderCount){
        initialiseCells(boardSize);
        addSnakesAndLadders(snakesCount, ladderCount);
    }

    int getBoardSize(){
        return board.length;
    }

    void initialiseCells(int boardSize){
        board = new Cell[boardSize][boardSize];

        for(int i=0;i<boardSize;i++) {
            for(int j=0; j<boardSize;j++) {
                Cell cellObj = new Cell();
                board[i][j] = cellObj;
            }
        }

    }

    void addSnakesAndLadders(int snakesCount, int ladderCount){
        while(snakesCount>0){
            int snakeHead = ThreadLocalRandom.current().nextInt(1,board.length*board.length-1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1,board.length*board.length-1);

            if(snakeTail >= snakeHead){
                continue;
            }

            Jump jump = new Jump();
            jump.setStartPosition(snakeHead);
            jump.setEndPosition(snakeTail);

            Cell cell = getCell(snakeHead);
            cell.setJump(jump);
            snakesCount--;
        }

        while(ladderCount>0){
            int ladderHead = ThreadLocalRandom.current().nextInt(1,board.length*board.length-1);
            int ladderTail = ThreadLocalRandom.current().nextInt(1,board.length*board.length-1);

            if(ladderTail <= ladderHead){
                continue;
            }

            Jump jump = new Jump();
            jump.setStartPosition(ladderTail);
            jump.setEndPosition(ladderHead);

            Cell cell = getCell(ladderTail);
            cell.setJump(jump);

            ladderCount--;
        }
    }

    Cell getCell(int position){
        int row = position/board.length;
        int col = position% board.length;
        return board[row][col];
    }
}

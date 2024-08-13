package playingPiece;

public class PlayingPiece {
    PieceType pieceType;

    public PlayingPiece(PieceType type){
        this.pieceType = type;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}

package net.femtoparsec.chess.board;

import java.util.Optional;

public interface MutableBoard extends Board {

  Optional<Piece> putPiece(Location location, Piece piece);

  Optional<Piece> removePiece(Location location);

  Optional<Piece> performMove(Color color, Move move);


}

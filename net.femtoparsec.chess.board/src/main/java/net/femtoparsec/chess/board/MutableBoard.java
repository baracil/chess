package net.femtoparsec.chess.board;

import lombok.NonNull;

import java.util.Optional;

public interface MutableBoard extends Board {

  @NonNull Optional<Piece> putPiece(@NonNull Location location, @NonNull Piece piece);

  @NonNull Optional<Piece> removePiece(@NonNull Location location);

  @NonNull Optional<Piece> performMove(@NonNull Color color, @NonNull Move move);


}

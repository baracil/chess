package net.femtoparsec.chess.board._private;

import lombok.NonNull;
import net.femtoparsec.chess.board.PieceMapping;
import net.femtoparsec.chess.board.PieceType;

import java.util.Optional;

public enum LocalePieceMapping implements PieceMapping {
  ENGLISH('K','Q','R','B','N'),
  FRENCH('R','D','T','F','C'),
  ;

  private final char[] names;

  private static final PieceType[] TYPES = {PieceType.KING,PieceType.QUEEN,PieceType.ROOK,PieceType.BISHOP,PieceType.KNIGHT};

  LocalePieceMapping(char... names) {
    this.names = names;
  }

  @Override
  public @NonNull Optional<PieceType> map(char character) {
    for (int i = 0; i < names.length; i++) {
      if (names[i] == character) {
        return Optional.of(TYPES[i]);
      }
    }
    return Optional.empty();
  }
}

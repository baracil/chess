package net.femtoparsec.chess.board._private;

import net.femtoparsec.chess.board.PieceMapping;
import net.femtoparsec.chess.board.PieceType;

import java.util.Optional;

public class FrenchPieceMapping implements PieceMapping {
  @Override
  public Optional<PieceType> map(char character) {
    final var type = switch (character) {
      case 'R' -> PieceType.KING;
      case 'D' -> PieceType.QUEEN;
      case 'T' -> PieceType.ROOK;
      case 'F' -> PieceType.BISHOP;
      case 'C' -> PieceType.KNIGHT;
      default -> null;
    };
    return Optional.ofNullable(type);
  }
}

package net.femtoparsec.chess.board;

import net.femtoparsec.chess.board._private.LocalePieceMapping;

import java.util.Optional;

public interface PieceMapping {

  Optional<PieceType> map(char character);

  static PieceMapping forLanguage(String language) {
    return switch (language.toLowerCase()) {
      case "fr" -> LocalePieceMapping.FRENCH;
      default -> LocalePieceMapping.ENGLISH;
    };
  }
}

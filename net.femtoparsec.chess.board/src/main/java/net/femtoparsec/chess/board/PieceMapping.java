package net.femtoparsec.chess.board;

import lombok.NonNull;
import net.femtoparsec.chess.board._private.LocalePieceMapping;

import java.util.Optional;

public interface PieceMapping {

  @NonNull Optional<PieceType> map(char character);

  static @NonNull PieceMapping forLanguage(@NonNull String language) {
    return switch (language.toLowerCase()) {
      case "fr" -> LocalePieceMapping.FRENCH;
      default -> LocalePieceMapping.ENGLISH;
    };
  }
}

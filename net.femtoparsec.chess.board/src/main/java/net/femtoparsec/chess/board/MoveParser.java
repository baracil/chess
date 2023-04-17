package net.femtoparsec.chess.board;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MoveParser {

  private final @NonNull PieceMapping pieceMapping;

  public @NonNull Move parse(@NonNull String move) {
    return new Parsing(move).parse();
  }

  @RequiredArgsConstructor
  private class Parsing {

    private final @NonNull String move;
    private Move parsedMove = null;
    private int pos = 0;
    private PieceType pieceType = null;
    private Discriminant discriminant = null;
    private boolean take = false;
    private boolean check = false;
    private Location location = null;
    private PieceType promotion = null;

    private @NonNull Move parse() {
      this.checkCastling();
      if (parsedMove != null) {
        return parsedMove;
      }
      this.extractPieceType();
      this.extractDiscriminant();
      this.checkTake();
      this.extractLocation();
      this.checkPromotion();
      this.checkCheck();
      this.buildMove();
      return parsedMove;
    }

    private void buildMove() {
      parsedMove = Move.Basic.builder()
          .sanNotation(move)
          .type(pieceType)
          .target(location)
          .discriminant(discriminant)
          .promotion(promotion)
          .take(take)
          .check(check)
          .build();
    }

    private void checkCheck() {
      this.check = is(pos, '+') || is(pos, '#');
    }

    private void checkPromotion() {
      if (is(pos, '=')) {
        this.pos++;
        this.promotion = parsePieceType(pos).orElseThrow(() -> new IllegalArgumentException("Could not parse promoted piece"));
      }
    }

    private void extractLocation() {
      final var file = File.from(this.move.charAt(pos++));
      final var rank = Rank.from(this.move.charAt(pos++));
      this.location = Location.with(file, rank);
    }

    private void checkTake() {
      if (isTake(pos)) {
        this.take = true;
        this.pos++;
      } else {
        this.take = false;
      }
    }

    private void checkCastling() {
      if (move.equals("O-O-O")) {
        parsedMove = Move.Castling.queenSide();
      } else if (move.equals("O-O")) {
        parsedMove = Move.Castling.kingSide();
      }
    }

    private void extractPieceType() {
      this.pieceType = parsePieceType(pos).orElse(null);
      if (pieceType == null) {
        pieceType = PieceType.PAWN;
      } else {
        this.pos++;
      }
    }

    private Optional<PieceType> parsePieceType(int position) {
      return pieceMapping.map(move.charAt(position));
    }


    private void extractDiscriminant() {
      final var isTakeOrFile1 = isTakeOrFile(pos + 1);
      if (isFile(pos) && isTakeOrFile1) {
        final var file = File.from(this.move.charAt(pos));
        this.discriminant = file.discriminant();
        this.pos++;
      } else if (isRank(pos) && isTakeOrFile1) {
        final var rank = Rank.from(this.move.charAt(pos));
        this.discriminant = rank.discriminant();
        this.pos++;
      } else if (isFile(pos) && isRank(pos + 1) && isTakeOrFile(pos + 2)) {
        final var file = File.from(this.move.charAt(pos));
        final var rank = Rank.from(this.move.charAt(pos + 1));
        this.discriminant = new Discriminant(file, rank);
        this.pos += 2;
      } else {
        this.discriminant = null;
      }
    }


    private boolean isTake(int position) {
      return is(position, 'x');
    }

    private boolean isFile(int position) {
      if (position >= move.length()) {
        return false;
      }
      final var c = move.charAt(position);
      return c >= 'a' && c <= 'h';
    }

    private boolean isRank(int position) {
      if (position >= move.length()) {
        return false;
      }
      final var c = move.charAt(position);
      return c >= '1' && c <= '8';
    }

    private boolean isTakeOrFile(int position) {
      if (position >= move.length()) {
        return false;
      }
      final var c = move.charAt(position);
      return c == 'x' || (c >= 'a' && c <= 'h');
    }

    private boolean is(int position, char expected) {
      if (position >= move.length()) {
        return false;
      }
      return move.charAt(position) == expected;
    }
  }
}


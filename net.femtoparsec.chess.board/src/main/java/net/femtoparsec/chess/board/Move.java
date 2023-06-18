package net.femtoparsec.chess.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.Optional;

public sealed interface Move permits Move.Castling, Move.Basic {


  @Value
  final class Castling implements Move {
    String sanNotation;
    @Getter
    boolean kingSide;

    public boolean isQueenSide() {
      return !kingSide;
    }

    static Castling queenSide() {
      return new Castling("O-O-O", false);
    }

    static Castling kingSide() {
      return new Castling("O-O", true);
    }
  }

  @Value
  @Builder
  final class Basic implements Move {
    String sanNotation;
    PieceType type;
    Location target;
    Discriminant discriminant;
    PieceType promotion;
    boolean take;
    boolean check;

    public Discriminant getDiscriminant() {
      return discriminant == null?Discriminant.NONE:discriminant;
    }

    public Optional<PieceType> getPromotion() {
      return Optional.ofNullable(promotion);
    }
  }

}

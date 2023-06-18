package net.femtoparsec.chess.board;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum Piece {
  WHITE_KING(PieceType.KING, Color.WHITE),
  WHITE_QUEEN(PieceType.QUEEN, Color.WHITE),
  WHITE_ROOK(PieceType.ROOK, Color.WHITE),
  WHITE_BISHOP(PieceType.BISHOP, Color.WHITE),
  WHITE_KNIGHT(PieceType.KNIGHT, Color.WHITE),
  WHITE_PAWN(PieceType.PAWN, Color.WHITE),

  BLACK_KING(PieceType.KING, Color.BLACK),
  BLACK_QUEEN(PieceType.QUEEN, Color.BLACK),
  BLACK_ROOK(PieceType.ROOK, Color.BLACK),
  BLACK_BISHOP(PieceType.BISHOP, Color.BLACK),
  BLACK_KNIGHT(PieceType.KNIGHT, Color.BLACK),
  BLACK_PAWN(PieceType.PAWN, Color.BLACK),
  ;

  private final PieceType type;
  private final Color color;

  Piece(PieceType type, Color color) {
    this.type = type;
    this.color = color;
  }

  public static Piece with(PieceType type, Color color) {
    return Holder.MAP.get(color).get(type);
  }

  private static class Holder {
    private static final Map<Color,Map<PieceType,Piece>> MAP = Arrays.stream(values())
        .collect(Collectors.groupingBy(Piece::getColor, Collectors.toMap(Piece::getType, Function.identity())));
  }

}

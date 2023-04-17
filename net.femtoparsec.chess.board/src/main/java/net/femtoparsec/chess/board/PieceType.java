package net.femtoparsec.chess.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PieceType {
  KING(6),
  QUEEN(5),
  ROOK(4),
  BISHOP(3),
  KNIGHT(2),
  PAWN(1),
  ;
  @Getter
  private final int index;
}

package net.femtoparsec.chess.board;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Color {
  WHITE(0),
  BLACK(8),
  ;
  @Getter
  private final int mask;


  public @NonNull Color swap() {
    return this == WHITE?BLACK:WHITE;
  }
}

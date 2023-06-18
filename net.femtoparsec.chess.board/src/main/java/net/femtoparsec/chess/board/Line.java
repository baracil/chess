package net.femtoparsec.chess.board;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Builder
@EqualsAndHashCode
public class Line {

  private final Color firstMoveColor;
  private final List<Move> moves;

  public List<Piece> applyToBoard(MutableBoard board) {
    final List<Piece> removed = new ArrayList<>();
    var color = firstMoveColor;
    for (Move move : moves) {
      board.performMove(color,move).ifPresent(removed::add);
      color = color.swap();
    }
    return removed;
  }


}

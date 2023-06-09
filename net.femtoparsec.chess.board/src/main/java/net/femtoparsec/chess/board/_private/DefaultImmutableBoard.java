package net.femtoparsec.chess.board._private;

import net.femtoparsec.chess.board.ImmutableBoard;
import net.femtoparsec.chess.board.Location;
import net.femtoparsec.chess.board.MutableBoard;
import net.femtoparsec.chess.board.Piece;

import java.util.Map;
import java.util.Optional;

public class DefaultImmutableBoard implements ImmutableBoard {

  private final Map<Location, Piece> pieces;

  DefaultImmutableBoard(Map<Location, Piece> pieces) {
    this.pieces = pieces;
  }

  @Override
  public ImmutableBoard createSnapshot() {
    return this;
  }

  @Override
  public MutableBoard createMutable() {
    return DefaultMutableBoard.create(pieces);
  }

  @Override
  public Optional<Piece> pieceAt(Location location) {
    return Optional.ofNullable(pieces.get(location));
  }

}

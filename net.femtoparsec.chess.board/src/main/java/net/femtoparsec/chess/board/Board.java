package net.femtoparsec.chess.board;

import net.femtoparsec.chess.board._private.DefaultMutableBoard;

import java.util.List;
import java.util.Optional;

public interface Board {

  ImmutableBoard createSnapshot();

  MutableBoard createMutable();

  Optional<Piece> pieceAt(Location location);

  static MutableBoard createEmpty() {
    return DefaultMutableBoard.createEmpty();
  }

  static MutableBoard createStart() {
    return DefaultMutableBoard.create(PieceWithLocation.INITIAL_DISPOSITION);
  }

  static MutableBoard createNew(List<PieceWithLocation> dispositions) {
    return DefaultMutableBoard.create(dispositions);
  }
}

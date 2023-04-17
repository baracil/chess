package net.femtoparsec.chess.board;

import lombok.NonNull;

public record Coordinate(int file, int rank) {

  public @NonNull Delta minus(@NonNull Coordinate other) {
    return new Delta(this.file-other.file, this.rank-other.rank);
  }
}

package net.femtoparsec.chess.board;

public record Coordinate(int file, int rank) {

  public Delta minus(Coordinate other) {
    return new Delta(this.file-other.file, this.rank-other.rank);
  }
}

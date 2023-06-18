package net.femtoparsec.chess.board;

public record Delta(int deltaFile, int deltaRank) {

  public boolean isHorizontal() {
    return deltaRank == 0;
  }

  public boolean isVertical() {
    return deltaFile == 0;
  }

  public boolean isDiagonal() {
    return Math.abs(deltaFile) == Math.abs(deltaRank);
  }

  public boolean isKnightLike() {
    return (Math.abs(deltaRank) == 2 && Math.abs(deltaFile) == 1)
        || (Math.abs(deltaFile) == 2 && Math.abs(deltaRank) == 1);
  }

  public Delta sign() {
    return new Delta(Integer.compare(deltaFile, 0),Integer.compare(deltaRank, 0));
  }

  public boolean isSingleStep() {
    return Math.abs(deltaFile) <= 1 && Math.abs(deltaRank) <=1;
  }
}

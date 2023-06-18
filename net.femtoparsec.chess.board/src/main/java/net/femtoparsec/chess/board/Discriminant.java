package net.femtoparsec.chess.board;

import lombok.Value;

import java.util.function.Predicate;

@Value
public class Discriminant implements Predicate<Location> {
  File file;
  Rank rank;

  public static final Discriminant NONE = new Discriminant();

  private Discriminant() {
    this.file = null;
    this.rank = null;
  }

  public Discriminant(File file, Rank rank) {
    this.file = file;
    this.rank = rank;
  }

  public Discriminant(File file) {
    this.file = file;
    this.rank = null;
  }

  public Discriminant(Rank rank) {
    this.file = null;
    this.rank = rank;
  }

  public boolean test(Location location) {
    final var r = this.rank == null || location.getRank() == this.rank;
    final var f = this.file == null || location.getFile() == this.file;
    return r && f;
  }

}

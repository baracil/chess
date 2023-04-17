package net.femtoparsec.chess.board;

import lombok.NonNull;
import lombok.Value;

import java.util.Optional;
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

  public Discriminant(@NonNull File file, @NonNull Rank rank) {
    this.file = file;
    this.rank = rank;
  }

  public Discriminant(@NonNull File file) {
    this.file = file;
    this.rank = null;
  }

  public Discriminant(@NonNull Rank rank) {
    this.file = null;
    this.rank = rank;
  }

  public boolean test(@NonNull Location location) {
    final var r = this.rank == null || location.getRank() == this.rank;
    final var f = this.file == null || location.getFile() == this.file;
    return r && f;
  }

}

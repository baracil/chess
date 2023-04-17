package net.femtoparsec.chess.board;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
public enum Rank {
  _1("1",0),
  _2("2",1),
  _3("3",2),
  _4("4",3),
  _5("5",4),
  _6("6",5),
  _7("7",6),
  _8("8",7),
  ;

  @Getter
  private final @NonNull String character;

  @Getter
  private final int index;

  public static Rank from(char chr) {
    return switch (chr) {
      case '1' -> _1;
      case '2' -> _2;
      case '3' -> _3;
      case '4' -> _4;
      case '5' -> _5;
      case '6' -> _6;
      case '7' -> _7;
      case '8' -> _8;
      default -> throw new IllegalArgumentException("Invalid char for Rank : '" + chr + "'");
    };
  }

  public static Stream<Rank> stream() {
    return Holder.RANK_VALUES.stream();
  }

  public Discriminant discriminant() {
    return new Discriminant(this);
  }

  public @NonNull Rank below() {
    return switch (this) {
      case _1 -> throw new IllegalArgumentException("No rank below 1");
      case _2 -> _1;
      case _3 -> _2;
      case _4 -> _3;
      case _5 -> _4;
      case _6 -> _5;
      case _7 -> _6;
      case _8 -> _7;
    };
  }

  public @NonNull Rank above() {
    return switch (this) {
      case _1 -> _2;
      case _2 -> _3;
      case _3 -> _4;
      case _4 -> _5;
      case _5 -> _6;
      case _6 -> _7;
      case _7 -> _8;
      case _8 -> throw new IllegalArgumentException("No rank below 8");
    };
  }

  private static class Holder {
    public static final List<Rank> RANK_VALUES = List.of(values());
  }

}

package net.femtoparsec.chess.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
public enum File {
  A("a",0),
  B("b",1),
  C("c",2),
  D("d",3),
  E("e",4),
  F("f",5),
  G("g",6),
  H("h",7),
  ;

  @Getter
  private final String character;
  @Getter
  private final int index;

  public static File from(char chr) {
    return switch (chr) {
      case 'a','A' -> A;
      case 'b','B' -> B;
      case 'c','C' -> C;
      case 'd','D' -> D;
      case 'e','E' -> E;
      case 'f','F' -> F;
      case 'g','G' -> G;
      case 'h','H' -> H;
      default -> throw new IllegalArgumentException("Invalid char for File : '" + chr + "'");
    };
  }

  public static Stream<File> stream() {
    return Holder.FILE_VALUES.stream();
  }

  public Discriminant discriminant() {
    return new Discriminant(this);
  }

  private static class Holder {
    public static final List<File> FILE_VALUES = List.of(values());
  }

}

package net.femtoparsec.chess.drawer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Size {
  _16(16,1),
  _32(32,2),
  _64(64,3),
  _128(128,4),
  ;

  @Getter
  private final int value;
  private final int code;

  public static Size fromCode(int code) {
    return switch (code) {
      case 1 -> _16;
      case 2 -> _32;
      case 3 -> _64;
      case 4 -> _128;
      default -> _64;
    };
  }
}

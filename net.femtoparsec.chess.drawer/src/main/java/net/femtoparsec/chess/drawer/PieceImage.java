package net.femtoparsec.chess.drawer;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.chess.board.Piece;

import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum PieceImage {
  WHITE_KING(Piece.WHITE_KING, "white_king"),
  WHITE_QUEEN(Piece.WHITE_QUEEN, "white_queen"),
  WHITE_ROOK(Piece.WHITE_ROOK, "white_rook"),
  WHITE_BISHOP(Piece.WHITE_BISHOP, "white_bishop"),
  WHITE_KNIGHT(Piece.WHITE_KNIGHT, "white_knight"),
  WHITE_PAWN(Piece.WHITE_PAWN, "white_pawn"),

  BLACK_KING(Piece.BLACK_KING, "black_king"),
  BLACK_QUEEN(Piece.BLACK_QUEEN, "black_queen"),
  BLACK_ROOK(Piece.BLACK_ROOK, "black_rook"),
  BLACK_BISHOP(Piece.BLACK_BISHOP, "black_bishop"),
  BLACK_KNIGHT(Piece.BLACK_KNIGHT, "black_knight"),
  BLACK_PAWN(Piece.BLACK_PAWN, "black_pawn"),
  ;

  @Getter
  private final Piece piece;
  private final String prefix;

  public static PieceImage findImage(Piece piece) {
    return Objects.requireNonNull(Holder.IMAGE_BY_PIECE.get(piece));
  }

  public URL getResourceUrl(Size size) {
    final var result = PieceImage.class.getResource("%s_%d.png".formatted(this.prefix,size.getValue()));
    if (result == null) {
      throw new IllegalStateException("No image defined for piece '"+this.piece+"' and size="+size.getValue());
    }
    return result;
  }

  private static class Holder {
    private static final Map<Piece, PieceImage> IMAGE_BY_PIECE = Arrays.stream(PieceImage.values()).collect(Collectors.toMap(PieceImage::getPiece, Function.identity()));
  }
}

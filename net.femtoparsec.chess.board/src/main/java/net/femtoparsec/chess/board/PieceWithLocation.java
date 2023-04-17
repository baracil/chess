package net.femtoparsec.chess.board;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor(staticName = "with")
public class PieceWithLocation {
  @NonNull Piece piece;
  @NonNull Location location;


  public static List<PieceWithLocation> INITIAL_DISPOSITION = List.of(
      with(Piece.WHITE_ROOK, Location.A1),
      with(Piece.WHITE_KNIGHT, Location.B1),
      with(Piece.WHITE_BISHOP, Location.C1),
      with(Piece.WHITE_QUEEN, Location.D1),
      with(Piece.WHITE_KING, Location.E1),
      with(Piece.WHITE_BISHOP, Location.F1),
      with(Piece.WHITE_KNIGHT, Location.G1),
      with(Piece.WHITE_ROOK, Location.H1),

      with(Piece.WHITE_PAWN, Location.A2),
      with(Piece.WHITE_PAWN, Location.B2),
      with(Piece.WHITE_PAWN, Location.C2),
      with(Piece.WHITE_PAWN, Location.D2),
      with(Piece.WHITE_PAWN, Location.E2),
      with(Piece.WHITE_PAWN, Location.F2),
      with(Piece.WHITE_PAWN, Location.G2),
      with(Piece.WHITE_PAWN, Location.H2),

      with(Piece.BLACK_ROOK, Location.A8),
      with(Piece.BLACK_KNIGHT, Location.B8),
      with(Piece.BLACK_BISHOP, Location.C8),
      with(Piece.BLACK_QUEEN, Location.D8),
      with(Piece.BLACK_KING, Location.E8),
      with(Piece.BLACK_BISHOP, Location.F8),
      with(Piece.BLACK_KNIGHT, Location.G8),
      with(Piece.BLACK_ROOK, Location.H8),

      with(Piece.BLACK_PAWN, Location.A7),
      with(Piece.BLACK_PAWN, Location.B7),
      with(Piece.BLACK_PAWN, Location.C7),
      with(Piece.BLACK_PAWN, Location.D7),
      with(Piece.BLACK_PAWN, Location.E7),
      with(Piece.BLACK_PAWN, Location.F7),
      with(Piece.BLACK_PAWN, Location.G7),
      with(Piece.BLACK_PAWN, Location.H7)
  );
}

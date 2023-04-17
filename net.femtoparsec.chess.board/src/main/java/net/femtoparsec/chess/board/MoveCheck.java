package net.femtoparsec.chess.board;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MoveCheck {


  public static boolean canReach(@NonNull Piece piece, @NonNull Location from, @NonNull Location target, @NonNull Board board) {
    return new MoveCheck(piece, from, target, board).canReach();
  }

  private final @NonNull Piece piece;
  private final @NonNull Location from;
  private final @NonNull Location target;
  private final @NonNull Board board;


  private boolean canReach() {
    if (target == from || !canMoveTo(target)) {
      return false;
    }
    return switch (piece.getType()) {
      case KING -> canKingReach();
      case QUEEN -> canQueenReach();
      case ROOK -> canRookReach();
      case BISHOP -> canBishopReach();
      case KNIGHT -> canKnightReach();
      case PAWN -> canPawnReach();
    };
  }

  private boolean canKingReach() {
    final var delta = target.minus(from);
    return (delta.deltaRank() >= -1
        && delta.deltaRank() <= 1
        && delta.deltaFile() <= 1
        && delta.deltaFile() >= -1);
  }

  private boolean canQueenReach() {
    final var delta = target.minus(from);

    if (!(delta.isHorizontal() || delta.isVertical() || delta.isDiagonal())) {
      return false;
    }

    return checkMultipleDisplacement(delta.sign());
  }

  private boolean canRookReach() {
    final var delta = target.minus(from);
    if (!(delta.isHorizontal() || delta.isVertical())) {
      return false;
    }
    return checkMultipleDisplacement(delta.sign());
  }

  private boolean canBishopReach() {
    final var delta = target.minus(from);
    if (!(delta.isDiagonal())) {
      return false;
    }
    return checkMultipleDisplacement(delta.sign());
  }

  private boolean canKnightReach() {
    final var delta = target.minus(from);
    return delta.isKnightLike();
  }

  private boolean canPawnReach() {
    final var delta = target.minus(from);
    if (delta.isVertical()) {
      if (!isEmpty(target)) {
        return false;
      }
      return switch (piece.getColor()) {
        case WHITE -> switch (delta.deltaRank()) {
          case 1 -> true;
          case 2 -> isEmpty(target.rankBelow()) && from.getRank() == Rank._2;
          default -> false;
        };
        case BLACK -> switch (delta.deltaRank()) {
          case -1 -> true;
          case -2 -> isEmpty(target.rankAbove()) && from.getRank() == Rank._7;
          default -> false;
        };
      };
    } else if (delta.isDiagonal()) {
      if (!delta.isSingleStep()) {
        return false;
      }
      return switch (piece.getColor()) {
        case WHITE -> canWhitePawnTake(delta);
        case BLACK -> canBlackPawnTake(delta);
      };
    }
    return false;
  }

  private boolean canBlackPawnTake(Delta delta) {
    if (delta.deltaRank() != -1) {
      return false;
    }
    if (isEmpty(target)) {
      final var piece1 = board.pieceAt(target.rankAbove()).orElse(null);
      if (piece1 == null) {
        return false;
      }
      return piece1.getType() == PieceType.PAWN && piece1.getColor() != piece.getColor() && target.getRank() == Rank._6;
    } else {
      return canTake(target);
    }
  }

  private boolean canWhitePawnTake(Delta delta) {
    if (delta.deltaRank() != 1) {
      return false;
    }
    if (isEmpty(target)) {
      final var piece1 = board.pieceAt(target.rankBelow()).orElse(null);
      if (piece1 == null) {
        return false;
      }
      return piece1.getType() == PieceType.PAWN && piece1.getColor() != piece.getColor() && target.getRank() == Rank._3;
    } else {
      return canTake(target);
    }
  }

  private boolean checkMultipleDisplacement(Delta delta) {
    var position = from.add(delta).orElse(null);
    while (position != null) {
      if (position == target) {
        return true;
      }
      if (!isEmpty(position)) {
        return false;
      }
      position = position.add(delta).orElse(null);
    }
    return false;
  }


  public boolean isEmpty(Location location) {
    return board.pieceAt(location).isEmpty();
  }

  public boolean canMoveTo(Location location) {
    final var piece = board.pieceAt(location).orElse(null);
    if (piece == null) {
      return true;
    }
    if (piece.getColor() == this.piece.getColor()) {
      return false;
    }
    return piece.getType() != PieceType.KING;
  }

  public boolean canTake(Location location) {
    final var piece = board.pieceAt(location).orElse(null);
    if (piece == null) {
      return false;
    }
    if (piece.getColor() == this.piece.getColor()) {
      return false;
    }
    return piece.getType() != PieceType.KING;
  }

}

package net.femtoparsec.chess.board._private;

import net.femtoparsec.chess.board.*;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultMutableBoard implements MutableBoard {

  public static MutableBoard createEmpty() {
    return new DefaultMutableBoard(new HashMap<>());
  }

  public static MutableBoard create(List<PieceWithLocation> pieces) {
    final var map = new HashMap<Location, Piece>();
    pieces.forEach(p -> map.put(p.getLocation(), p.getPiece()));
    return new DefaultMutableBoard(map);
  }

  public static MutableBoard create(Map<Location, Piece> pieces) {
    return new DefaultMutableBoard(new HashMap<>(pieces));
  }

  private final Map<Location, Piece> pieces;
  private final Map<Piece, Set<Location>> locationPerPieces;

  private DefaultMutableBoard(Map<Location, Piece> pieces) {
    this.pieces = pieces;
    this.locationPerPieces = pieces.keySet().stream().collect(Collectors.groupingBy(pieces::get, Collectors.toSet()));
  }

  @Override
  public ImmutableBoard createSnapshot() {
    return new DefaultImmutableBoard(Map.copyOf(pieces));
  }

  @Override
  public DefaultMutableBoard createMutable() {
    return new DefaultMutableBoard(new HashMap<>(this.pieces));
  }

  @Override
  public Optional<Piece> putPiece(Location location, Piece piece) {
    final var removed = this.removePiece(location);
    this.pieces.put(location, piece);
    this.locationPerPieces.computeIfAbsent(piece, p -> new HashSet<>()).add(location);
    return removed;
  }

  public Optional<Piece> removePiece(Location location) {
    final var current = this.pieces.remove(location);
    if (current == null) {
      return Optional.empty();
    }
    this.locationPerPieces.computeIfPresent(current, (p, s) -> {
      s.remove(location);
      return s.isEmpty() ? null : s;
    });
    return Optional.of(current);
  }

  @Override
  public Optional<Piece> performMove(Color color, Move move) {
    if (move instanceof Move.Castling castling) {
      this.performCastling(color, castling);
      return Optional.empty();
    } else if (move instanceof Move.Basic basic) {
      return this.performBasicMove(color, basic);
    } else {
      throw new IllegalArgumentException("Does not know this move '" + move + "'");
    }
  }

  private void performCastling(Color color, Move.Castling move) {
    switch (color) {
      case BLACK -> performBlackCastling(move);
      case WHITE -> performWhiteCastling(move);
    }
  }

  protected void performWhiteCastling(Move.Castling move) {
    if (move.isKingSide()) {
      move(Location.E1, Location.G1);
      move(Location.H1, Location.F1);
    } else {
      move(Location.E1, Location.C1);
      move(Location.A1, Location.D1);
    }
  }

  protected void performBlackCastling(Move.Castling move) {
    if (move.isKingSide()) {
      move(Location.E8, Location.G8);
      move(Location.H8, Location.F8);
    } else {
      move(Location.E8, Location.C8);
      move(Location.A8, Location.D8);
    }
  }

  protected Optional<Piece> performBasicMove(Color color, Move.Basic move) {
    final var start = findStart(color,move);
    final var target = move.getTarget();
    final var piece = Objects.requireNonNull(this.pieces.get(start));
    final var took = this.move(start,move.getTarget());

    if (move.isTake() && took.isEmpty() && piece.getType() == PieceType.PAWN) {
      //take en passant
      if (color == Color.BLACK) {
        final var removed = removePiece(target.rankAbove());
        assert removed.isPresent();
        return removed;
      }
      else {
        final var removed = removePiece(target.rankBelow());
        assert removed.isPresent();
        return removed;
      }
    }
    return took;
  }

  private Location findStart(Color color, Move.Basic move) {
    final var piece = Piece.with(move.getType(), color);
    return this.locationPerPieces.get(piece)
        .stream()
        .filter(move.getDiscriminant())
        .filter(current -> canReach(piece, current, move.getTarget()))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("Invalid move : " + move));
  }

  private boolean canReach(Piece piece, Location origin, Location target) {
    return MoveCheck.canReach(piece,origin,target,this);
  }

  private Optional<Piece> move(Location from, Location to) {
    final var piece = this.removePiece(from).orElseThrow(() -> new IllegalArgumentException("Invalid move from=" + from + " to=" + to));
    final var removed = this.removePiece(to);
    this.putPiece(to, piece);
    return removed;
  }

  @Override
  public Optional<Piece> pieceAt(Location location) {
    return Optional.ofNullable(pieces.get(location));
  }

}

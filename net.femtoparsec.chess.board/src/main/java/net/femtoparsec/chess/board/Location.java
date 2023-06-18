package net.femtoparsec.chess.board;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Location {
  A1, B1, C1, D1, E1, F1, G1, H1,
  A2, B2, C2, D2, E2, F2, G2, H2,
  A3, B3, C3, D3, E3, F3, G3, H3,
  A4, B4, C4, D4, E4, F4, G4, H4,
  A5, B5, C5, D5, E5, F5, G5, H5,
  A6, B6, C6, D6, E6, F6, G6, H6,
  A7, B7, C7, D7, E7, F7, G7, H7,
  A8, B8, C8, D8, E8, F8, G8, H8,
  ;

  private final Coordinate coordinate;
  private final File file;
  private final Rank rank;

  Location() {
    final var file = (this.name().charAt(0)-'A');
    final var rank = (this.name().charAt(1)-'1');
    this.rank = Rank.from(name().charAt(1));
    this.file = File.from(name().charAt(0));
    this.coordinate = new Coordinate(file,rank);
  }

  public Delta minus(Location other) {
    return this.coordinate.minus(other.coordinate);
  }

  public static Optional<Location> at(int file, int rank) {
    return fromCoordinate(new Coordinate(file, rank));
  }

  public static Optional<Location> fromCoordinate(Coordinate coordinate) {
    return Optional.ofNullable(Holder.COORDINATE_TO_POSITION.get(coordinate));
  }

  public Optional<Location> add(Delta delta) {
    return at(this.file.getIndex()+delta.deltaFile(), this.rank.getIndex()+delta.deltaRank());
  }

  public static Location with(File file, Rank rank) {
    return fromCoordinate(new Coordinate(file.getIndex(), rank.getIndex())).orElseThrow();
  }

  public static Stream<Location> stream() {
    return Holder.ALL_LOCATIONS.stream();
  }

  public Location rankBelow() {
    return with(file,rank.below());
  }

  public Location rankAbove() {
    return with(file,rank.above());
  }

  private static class Holder {
    public static final List<Location> ALL_LOCATIONS = List.of(values());
    public static final Map<Coordinate, Location> COORDINATE_TO_POSITION = Arrays.stream(values()).collect(Collectors.toMap(Location::getCoordinate, Function.identity()));
  }
}

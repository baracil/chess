package net.femtoparsec.chess.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MoveParseTest {

  public static Stream<Arguments> moves() {
    return Stream.of(
        Arguments.of("O-O", Move.Castling.kingSide()),
        Arguments.of("O-O-O", Move.Castling.queenSide()),
        Arguments.of("Ka1", new Move.Basic("Ka1",PieceType.KING, Location.A1, null, null,false,false)),
        Arguments.of("Kxa1", new Move.Basic("Kxa1",PieceType.KING, Location.A1, null, null,true,false)),
        Arguments.of("Be5", new Move.Basic("Be5",PieceType.BISHOP, Location.E5, null, null,false,false)),
        Arguments.of("cxb5", new Move.Basic("cxb5",PieceType.PAWN, Location.B5, File.C.discriminant(),null,true , false))
    );
  }

  private MoveParser moveParser;

  @BeforeEach
  public void setUp() {
    moveParser = new MoveParser(PieceMapping.forLanguage("en"));
  }

  @ParameterizedTest
  @MethodSource("moves")
  public void shouldBeCorrectlyParsed(String move, Move expected) {
    final var actual = moveParser.parse(move);
    Assertions.assertEquals(expected, actual);
  }
}

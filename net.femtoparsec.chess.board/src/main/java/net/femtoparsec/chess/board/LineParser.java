package net.femtoparsec.chess.board;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class LineParser {

  private static final Pattern PATTERN = Pattern.compile("[0-9]+\\.(?<extra>\\.\\.)? ?(?<move>.+)?");

  public static Line parse(String line, String language) {
    return new LineParser(line.trim(),new MoveParser(PieceMapping.forLanguage(language))).parse();
  }

  private final String line;
  private final MoveParser moveParser;
  private final List<Move> moves = new ArrayList<>();

  private Color color = null;

  private Line parse() {
    final var tokens = line.split(" ");

    for (String token : tokens) {
      parseOneMove(token);
    }

    return new Line(color, Collections.unmodifiableList(moves));
  }

  private void parseOneMove(String token) {
    final var match = PATTERN.matcher(token);
    if (match.matches()) {
      if (color == null) {
        this.color = match.group("extra") == null ? Color.WHITE:Color.BLACK;
      }
      final var move = match.group("move");
      if (move == null) {
        return;
      }
      moves.add(moveParser.parse(move));
    } else {
      if (color == null) {
        throw new IllegalArgumentException("Invalid line : '"+line+"'");
      }
      moves.add(moveParser.parse(token));
    }
  }
}

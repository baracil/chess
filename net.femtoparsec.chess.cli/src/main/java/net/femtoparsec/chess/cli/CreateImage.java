package net.femtoparsec.chess.cli;

import net.femtoparsec.chess.board.Board;
import net.femtoparsec.chess.board.LineParser;
import net.femtoparsec.chess.drawer.BoardDrawer;
import net.femtoparsec.chess.drawer.DrawParameter;
import net.femtoparsec.chess.drawer.ImageProvider;
import net.femtoparsec.chess.drawer.Size;
import picocli.CommandLine;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.List;

@CommandLine.Command(name="create")
public class CreateImage implements Runnable{

  @CommandLine.Option(names={"-o","--output"}) Path output;

  @CommandLine.Option(names={"-s","--size"}) int size;

  @CommandLine.Parameters
  List<String> parameters;

  public void run() {
    final var line = LineParser.parse(String.join(" ",parameters),"en");
    final var board = Board.createStart();
    line.applyToBoard(board);
    final var image = BoardDrawer.draw(
        board,
        DrawParameter.builder()
            .size(Size.fromCode(this.size))
            .build(),
        ImageProvider.cached());

    try {
      ImageIO.write(image, "PNG", output.toFile());
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

}

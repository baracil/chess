package net.femtoparsec.chess.server;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.chess.board.LineParser;
import net.femtoparsec.chess.drawer.DrawParameter;
import net.femtoparsec.chess.drawer.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ChessImageController {

  private final @NonNull ServerBoardDrawer drawer;

  @Get(value = "/v1/board.png", produces = MediaType.IMAGE_PNG)
  public byte[] getBoardImage(@QueryValue(value = "size", defaultValue = "3") int size,
                              @QueryValue(value = "lc",defaultValue = "D0D0D0") String lc,
                              @QueryValue(value = "dc",defaultValue = "649B64") String dc,
                              @QueryValue(value = "inv",defaultValue = "false") boolean inverted,
                              @QueryValue(value = "wc",defaultValue = "false") boolean withCoordinates,
                              @QueryValue(value = "line") String line) throws IOException {
    final var l = LineParser.parse(line.replace("_", " "), "en");
    final var image = drawer.draw(l,
        DrawParameter.builder()
            .lightColor(Color.decode("#"+lc))
            .darkColor(Color.decode("#"+dc))
            .size(Size.fromCode(size))
            .withCoordinates(withCoordinates)
            .inverted(inverted)
            .build());

    final var os = new ByteArrayOutputStream();
    try (var ob = new BufferedOutputStream(os)) {
      ImageIO.write(image, "PNG", ob);
    }
    return os.toByteArray();
  }
}

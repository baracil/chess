package net.femtoparsec.chess.drawer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.femtoparsec.chess.board.*;

import java.awt.Color;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardDrawer {

  public static BufferedImage draw(Board board,
                                            DrawParameter drawParameter,
                                            ImageProvider imageProvider) {
    return new BoardDrawer(board, drawParameter, imageProvider).draw();
  }

  private final Board board;
  private final DrawParameter parameter;
  private final ImageProvider imageProvider;

  private BufferedImage image = null;

  private BufferedImage draw() {
    this.createBoard();
    this.drawPieces();
    if (parameter.isWithCoordinates()) {
      this.drawLetters();
      this.drawNumbers();
    }

    return image;
  }

  private void drawPieces() {
    Location.stream().forEach(l -> board.pieceAt(l).ifPresent(p -> drawPiece(p, l)));
  }

  private void drawLetters() {
    final var rank = parameter.isInverted() ? Rank._8 : Rank._1;
    File.stream().map(f -> Location.with(f, rank)).forEach(this::drawLetter);
  }

  private void drawNumbers() {
    final var file = parameter.isInverted() ? File.H : File.A;
    Rank.stream().map(r -> Location.with(file, r)).forEach(this::drawNumber);
  }

  private void drawLetter(Location location) {
    final var letter = location.getFile().getCharacter();
    final var point = getCoordinate(location);
    final var color = getTextColor(location);
    final var g = image.createGraphics();
    try {
      g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      g.setColor(color);
      final var textLayout = new TextLayout(letter, g.getFont(), g.getFontRenderContext());
      final var width = textLayout.getBounds().getWidth();
      g.translate(parameter.getSizeValue() - width - 4, parameter.getSizeValue() - 5);
      textLayout.draw(g, point.x, point.y);
    } finally {
      g.dispose();
    }
  }

  private void drawNumber(Location location) {
    final var letter = location.getRank().getCharacter();
    final var point = getCoordinate(location);
    final var color = getTextColor(location);
    final var g = image.createGraphics();
    try {
      g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      g.setColor(color);
      final var textLayout = new TextLayout(letter, g.getFont(), g.getFontRenderContext());
      final var height = textLayout.getBounds().getHeight();
      g.translate(2, height+2);
      textLayout.draw(g, point.x, point.y);
    } finally {
      g.dispose();
    }
  }

  private Color getCellColor(Location location) {
    if ((location.getFile().getIndex() + location.getRank().getIndex()) % 2 == 0) {
      return parameter.getDarkColor();
    }
    return parameter.getLightColor();
  }

  private Color getTextColor(Location location) {
    if ((location.getFile().getIndex() + location.getRank().getIndex()) % 2 == 0) {
      return parameter.getLightColor();
    }
    return parameter.getDarkColor();
  }

  private void drawPiece(Piece piece, Location location) {
    final var point = getCoordinate(location);
    final var img = imageProvider.getImage(piece, parameter.getSize());
    var g = image.createGraphics();
    try {
      g.drawImage(img, point.x, point.y + parameter.getSizeValue() / 16, null);
    } finally {
      g.dispose();
    }
  }

  private void createBoard() {
    this.image = new BufferedImage(parameter.getSizeValue() * 8, parameter.getSizeValue() * 8, BufferedImage.TYPE_INT_ARGB);
    var g = image.createGraphics();
    try {
      g.setColor(parameter.getLightColor());
      g.fillRect(0, 0, this.image.getWidth(), this.image.getHeight());
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          if ((i + j) % 2 == 1) {
            g.setColor(parameter.getDarkColor());
            g.fillRect(i * parameter.getSizeValue(), j * parameter.getSizeValue(), parameter.getSizeValue(), parameter.getSizeValue());
          }
        }
      }
    } finally {
      g.dispose();
    }
  }

  private Point getCoordinate(Location location) {
    final var file = location.getCoordinate().file();
    final var rank = (7 - location.getCoordinate().rank());
    final var x = (parameter.isInverted() ? 7 - file : file) * parameter.getSizeValue();
    final var y = (parameter.isInverted() ? 7 - rank : rank) * parameter.getSizeValue();

    return new Point(x, y);
  }
}

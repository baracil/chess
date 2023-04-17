package net.femtoparsec.chess.drawer;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.awt.*;

@Value
@Builder(builderClassName = "Builder")
@RequiredArgsConstructor
public class DrawParameter {
  private static final Color DEFAULT_LIGHT_COLOR = new Color(208, 208, 208);
  private static final Color DEFAULT_DARK_COLOR = new Color(100, 155, 100);

  @NonNull Color lightColor;
  @NonNull Color darkColor;
  boolean withCoordinates;
  boolean inverted;
  @NonNull Size size;

  public static Builder builder() {
    return new Builder()
        .darkColor(DEFAULT_DARK_COLOR)
        .lightColor(DEFAULT_LIGHT_COLOR)
        .inverted(false)
        .size(Size._128);
  }

  public int getSizeValue() {
    return size.getValue();
  }
}

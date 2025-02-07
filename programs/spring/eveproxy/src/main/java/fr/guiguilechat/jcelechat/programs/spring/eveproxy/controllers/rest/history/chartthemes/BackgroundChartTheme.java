package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.history.chartthemes;

import java.awt.Color;
import java.util.List;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.history.ChartTheme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * chart theme based on a background color, deduces its hue, the text and lines
 * colors from it
 * <p>
 * text color is opposite of background color and brigthness, with half
 * saturation<br />
 * price are full saturation, vol are 0.9 saturation ; prices have opposite
 * brithnes of bg, vols have corresponding price ×0.9<br />
 * the hue of the prices are rotation starting from text hue
 * </p>
 */
@RequiredArgsConstructor
@Getter
public class BackgroundChartTheme implements ChartTheme {

	@Getter(lazy = true)
	private final float hue = hsb(backgGroundColor)[0];

	@Accessors(fluent = true)
	private final Color backgGroundColor;

	@Override
	public List<Color> priceColors(int nbCumulatedSeries) {
		float[] hsb = hsb(textColor());
		hsb[1]=1.0f;
		float baseHue = hsb[0];
		float rotation = 1.0f / (nbCumulatedSeries + 2);
		return IntStream.rangeClosed(0, nbCumulatedSeries).mapToObj(i -> {
			hsb[0] = rotate(baseHue, rotation * (1 + i));
			return rgb(hsb, 200);
		}).toList();
	}

	@Override
	public
	List<Color> volumeColors(int nbCumulatedSeries) {
		return priceColors(nbCumulatedSeries).stream().map(this::matchingVolume).toList();
	}

	protected Color matchingVolume(Color priceColor) {
		float[] hsb = hsb(priceColor);
		float bright = hsb[2];
		if (bright > 0.5f) {
			bright *= 0.8;
		} else {
			bright = 1.0f - (1.0f - bright) * 0.8f;
		}
		hsb[2] = bright;
		Color ret = rgb(hsb, 150);
		return ret;
	}

	@Override
	public Color textColor() {
		float[] hsb = hsb(backgGroundColor());
		flipHue(hsb);
		hsb[1] = 0.5f;
		flipBrightness(hsb);
		return rgb(hsb);
	}

	protected float[] hsb(Color rgb) {
		float[] hsb = new float[3];
		Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), hsb);
		return hsb;
	}

	protected Color rgb(float[] hsb) {
		return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
	}

	protected Color rgb(float[] hsb, int alpha) {
		int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
		int argb = rgb & 0xffffff | (alpha & 0xFF) << 24;
		return new Color(argb, true);
	}

	protected float rotate(float hue, float angle) {
		hue += angle;
		// 10.1=>.1
		if (hue > 1.0f) {
			hue -= Math.floor(hue);
		}
		// -5.5=>.5
		if (hue < .0f) {
			hue += Math.floor(hue);
		}
		return hue;
	}

	protected float flip(float source) {
		source += 0.5f;
		if (source > 1.0f) {
			source -= 1.0f;
		}
		return source;
	}

	protected void flipHue(float[] hsb) {
		hsb[0] = flip(hsb[0]);
	}

	protected void flipBrightness(float[] hsb) {
		hsb[2] = flip(hsb[2]);
	}

	public static BackgroundChartTheme forName(String name) {
		if (name == null) {
			return null;
		}
		try {
			int value = Integer.decode(name);
			return new BackgroundChartTheme(new Color(value));
		} catch (NumberFormatException e) {
			// can't decode
			return null;
		}
	}

}

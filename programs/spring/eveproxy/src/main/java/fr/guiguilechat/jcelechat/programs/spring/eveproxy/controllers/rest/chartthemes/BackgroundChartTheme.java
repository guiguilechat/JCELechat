package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.chartthemes;

import java.awt.Color;
import java.util.List;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.ChartTheme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * chart theme based on a background color, deduces its hue, the text and lines
 * colors from it
 */
@RequiredArgsConstructor
@Getter
public class BackgroundChartTheme implements ChartTheme {

	private final Color baseColor;

	@Getter(lazy = true)
	private final float hue = hsb(baseColor)[0];

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final Color backGroundColor = lowerSaturation(getBaseColor(), .15f);

	@Override
	public List<Color> firstAxisColor(int nbSeries) {
		float removedHueAngle = 0.1f;
		float[] hsb = new float[3];
		float[] bgHSB = hsb(backGroundColor());
		hsb[1]=1.0f;
		float baseHue = rotate(bgHSB[0], removedHueAngle);
		float anglePerColor = (1f - 2 * removedHueAngle) / (nbSeries + 1);
		float baseSaturation = 1f;
		float baseBrightness = Math.min(1f, bgHSB[2] + .6f);
//		System.err.println("base brightness is " + baseBrightness + " from bg " + bgHSB[2]);
//		System.err.println("base hue is " + baseHue + " from bg " + bgHSB[0]);
		return IntStream.range(0, nbSeries).mapToObj(i -> {
			hsb[0] = rotate(baseHue, anglePerColor * (1 + i));
//			System.err.println("hue " + i + "=" + hsb[0]);
			// saturation is base -0 -a -2a -0 -a -2a etc.
			hsb[1] = baseSaturation - .05f * (i % 3);
			// remove some brightness every odd index
			hsb[2] = baseBrightness * (1f - .15f * (i % 2));
			return rgb(hsb, 200);
		}).toList();
	}

	@Override
	public
	List<Color> secondAxisColor(int nbCumulatedSeries) {
		return firstAxisColor(nbCumulatedSeries).stream().map(this::matchingVolume).toList();
	}

	protected Color matchingVolume(Color priceColor) {
		float[] hsb = hsb(priceColor);
		Color ret = rgb(hsb, 150);
		return ret;
	}

	@Override
	public Color textColor() {
		float[] hsb = hsb(backGroundColor());
		flipHue(hsb);
		hsb[1] = 1f;
		opposeBrightness(hsb);
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

	protected void opposeBrightness(float[] hsb) {
		hsb[2] = hsb[2] < .6f ? 1f : 0f;
	}

	protected Color lowerSaturation(Color color, float maxSaturation) {
		float[] hsb = hsb(color);
		hsb[1] = Math.min(hsb[1], maxSaturation);
		return rgb(hsb);
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

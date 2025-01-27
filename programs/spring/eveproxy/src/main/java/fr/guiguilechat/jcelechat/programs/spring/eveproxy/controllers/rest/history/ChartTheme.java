package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.history;

import java.awt.Color;
import java.util.List;

import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.history.chartthemes.BackgroundChartTheme;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.history.chartthemes.EveChartTheme;

/**
 * defines background, text color, and lists of colors for main, and cumulated,
 * price and volume series
 * <p>
 * default transformation for price to volume is to use color with opposite hue
 * and half brightness
 * </p>
 */
public interface ChartTheme {

	/**
	 * @param nbCumulatedSeries
	 * @return 1+ nbcumulated colors to paint the price series
	 */
	List<Color> priceColors(int nbCumulatedSeries);

	/**
	 * @param nbCumulatedSeries
	 * @return 1+ nbcumulated colors to paint the volume series
	 */
	List<Color> volumeColors(int nbCumulatedSeries);

	Color backgGroundColor();

	Color textColor();

	static ChartTheme forName(String name) {
		ChartTheme ret = BackgroundChartTheme.forName(name);
		if (ret != null) {
			return ret;
		}
		return EveChartTheme.forName(name);
	}

}

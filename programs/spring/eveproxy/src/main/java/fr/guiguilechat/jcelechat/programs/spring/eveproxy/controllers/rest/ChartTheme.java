package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.awt.Color;
import java.util.List;

import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.chartthemes.BackgroundChartTheme;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.chartthemes.EveChartTheme;

/**
 * defines background, text color, and lists of colors for main, and secondary,
 * axis series
 */
public interface ChartTheme {

	/**
	 * @return nb series colors to paint the first axis
	 */
	List<Color> firstAxisColor(int nbSeries);

	/**
	 * @return nb series colors to paint the 2nd axis
	 */
	List<Color> secondAxisColor(int nbSeries);

	Color backGroundColor();

	Color textColor();

	static ChartTheme forName(String name) {
		ChartTheme ret = BackgroundChartTheme.forName(name);
		if (ret != null) {
			return ret;
		}
		return EveChartTheme.forName(name);
	}

}

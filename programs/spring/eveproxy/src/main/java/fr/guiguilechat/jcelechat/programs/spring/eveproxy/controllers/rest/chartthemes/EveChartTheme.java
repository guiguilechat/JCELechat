package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.chartthemes;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.ChartTheme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;


@RequiredArgsConstructor
@Getter
public class EveChartTheme implements ChartTheme {

	@Accessors(fluent = true)
	private final Color backgGroundColor;

	public EveChartTheme(int red, int green, int blue) {
		this(new Color(red, green, blue));
	}

	@Accessors(fluent = true)
	private final Color textColor = new Color(160, 160, 160);

	@Override
	public List<Color> firstAxisColor(int nbCumulatedSeries) {
		return List.of(new Color(145, 70, 0),
				new Color(22, 125, 152),
				new Color(152, 85, 22));
	}

	@Override
	public List<Color> secondAxisColor(int nbCumulatedSeries) {
		return List.of(new Color(0, 63, 79));
	}

	private static final EveChartTheme BG = new EveChartTheme(23, 23, 23);

	public static final Map<String, EveChartTheme> eve = Map.of(
			"amarr", new EveChartTheme(20, 22, 26),
			"caldari", new EveChartTheme(20, 27, 27),
			"carbon", new EveChartTheme(13, 13, 13),
			"gallente", new EveChartTheme(27, 24, 28),
			"minmatar", new EveChartTheme(17, 22, 22));

	public static EveChartTheme forName(String name) {
		return name == null ? BG : eve.getOrDefault(name.toLowerCase(), BG);
	}

}

package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.awt.Color;
import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import org.jfree.data.time.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.mer.services.KillService;
import fr.guiguilechat.jcelechat.libs.spring.mer.services.KillService.KillStats;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import jakarta.servlet.http.HttpServletResponse;

// @Slf4j
@RestController
@RequestMapping("/api/mer/kills")
public class MerKillsRestController {

	@Autowired
	private KillService killService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private GroupService groupService;

	static enum AGGREG_PERIOD {
		MONTH {
			@Override
			public String format(OffsetDateTime offsetted) {
				return new StringBuilder()
						.append(offsetted.get(ChronoField.YEAR))
						.append('-')
						.append(offsetted.get(ChronoField.MONTH_OF_YEAR))
						.toString();
			}

			@Override
			public List<KillStats> stats(List<Integer> types, KillService killservice) {
				return killservice.monthlyStats(types);
			}

			@Override
			public RegularTimePeriod of(OffsetDateTime odt) {
				return new Month(odt.getMonthValue(), odt.getYear());
			}

			@Override
			public int periodDays(OffsetDateTime odt) {
				return (int) ChronoUnit.DAYS.between(odt, odt.plusMonths(1));
			}
		},
		WEEK {
			@Override
			public String format(OffsetDateTime offsetted) {
				return new StringBuilder()
						.append(offsetted.get(ChronoField.YEAR))
						.append('w')
						.append(offsetted.get(ChronoField.ALIGNED_WEEK_OF_YEAR))
						.toString();
			}

			@Override
			public List<KillStats> stats(List<Integer> types, KillService killservice) {
				return killservice.weeklyStats(types);
			}

			@Override
			public RegularTimePeriod of(OffsetDateTime odt) {
				return new Week(odt.get(ChronoField.ALIGNED_WEEK_OF_YEAR), odt.getYear());
			}

			@Override
			public int periodDays(OffsetDateTime odt) {
				return 7;
			}
		},
		YEAR {
			@Override
			public String format(OffsetDateTime offsetted) {
				return new StringBuilder()
						.append(offsetted.get(ChronoField.YEAR))
						.toString();
			}

			@Override
			public List<KillStats> stats(List<Integer> types, KillService killservice) {
				return killservice.yearlyStats(types);
			}

			@Override
			public RegularTimePeriod of(OffsetDateTime odt) {
				return new Year(odt.getYear());
			}

			@Override
			public int periodDays(OffsetDateTime odt) {
				return (int) ChronoUnit.DAYS.between(odt, odt.plusYears(1));
			}
		},
		DAY {
			@Override
			public String format(OffsetDateTime offsetted) {
				return new StringBuilder()
						.append(offsetted.get(ChronoField.YEAR))
						.append('-')
						.append(offsetted.get(ChronoField.MONTH_OF_YEAR))
						.append('-')
						.append(offsetted.get(ChronoField.DAY_OF_MONTH))
						.toString();
			}

			@Override
			public List<KillStats> stats(List<Integer> types, KillService killservice) {
				return killservice.dailyStats(types);
			}

			@Override
			public RegularTimePeriod of(OffsetDateTime odt) {
				return new Day(odt.getDayOfMonth(), odt.getMonthValue(), odt.getYear());
			}

			@Override
			public int periodDays(OffsetDateTime odt) {
				return 1;
			}
		};

		public abstract String format(OffsetDateTime offsetted);

		public String format(Instant instant) {
			return format(instant.atOffset(ZoneOffset.UTC));
		}

		public abstract List<KillStats> stats(List<Integer> types, KillService killservice);

		public abstract RegularTimePeriod of(OffsetDateTime odt);

		public abstract int periodDays(OffsetDateTime odt);

		static AGGREG_PERIOD by(Optional<String> by) {
			String bys = by.orElse("month");
			if (bys.equalsIgnoreCase("week") || bys.equalsIgnoreCase("weekly")) {
				return WEEK;
			}
			if (bys.equalsIgnoreCase("year") || bys.equalsIgnoreCase("yearly")) {
				return YEAR;
			}
			if (bys.equalsIgnoreCase("day") || bys.equalsIgnoreCase("daily")) {
				return DAY;
			}
			return MONTH;
		}
	}

	static record TYPES_NAME(String name, List<Integer> typeIds) {
	}

	static enum TYPES_FILTER {
		TYPE_ID {
			@Override
			public TYPES_NAME resolve(String filterparam, TypeService typeService, GroupService groupService) {
				int typeId = Integer.parseInt(filterparam);
				Type type = typeService.byId(typeId).orElse(null);
				return type == null ? new TYPES_NAME("unknown t" + typeId, Collections.emptyList())
						: new TYPES_NAME(type.getName(), List.of(typeId));
			}
		},
		GROUP_ID {
			@Override
			public TYPES_NAME resolve(String filterparam, TypeService typeService, GroupService groupService) {
				int groupId = Integer.parseInt(filterparam);
				Group group = groupService.byId(groupId).orElse(null);
				return group == null ? new TYPES_NAME("unknown g" + groupId, Collections.emptyList())
						: new TYPES_NAME(group.getName(),
								typeService.byGroupId(groupId).stream().map(Type::getTypeId).distinct().sorted().toList());
			}
		},
		TYPE_NAME {
			@Override
			public TYPES_NAME resolve(String filterparam, TypeService typeService, GroupService groupService) {
				List<Type> list = typeService.byName(filterparam);
				if (list.isEmpty()) {
					return new TYPES_NAME("unmatched type name " + filterparam, Collections.emptyList());
				}
				String name = list.size() == 1 ? list.get(0).getName()
						: "matched " + list.size() + " type names " + filterparam;
				return new TYPES_NAME(name, list.stream().map(Type::getTypeId).sorted().toList());
			}
		},
		GROUP_NAME {
			@Override
			public TYPES_NAME resolve(String filterparam, TypeService typeService, GroupService groupService) {
				List<Group> list = groupService.byName(filterparam);
				if (list.isEmpty()) {
					return new TYPES_NAME("unmatched group name " + filterparam, Collections.emptyList());
				}
				String name = list.size() == 1 ? list.get(0).getName()
						: "matched " + list.size() + " group names " + filterparam;
				return new TYPES_NAME(name,
						list.stream().flatMap(g -> typeService.byGroupId(g.getGroupId()).stream()).map(Type::getTypeId)
								.distinct().sorted().toList());
			}
		},
		ERROR {
			@Override
			public TYPES_NAME resolve(String filterparam, TypeService typeService, GroupService groupService) {
				return new TYPES_NAME("invalid selector", Collections.emptyList());
			}
		};

		public abstract TYPES_NAME resolve(String filterparam, TypeService typeService, GroupService groupService);

		static TYPES_FILTER of(String filterBy) {
			if ("groupid".equalsIgnoreCase(filterBy)) {
				return GROUP_ID;
			}
			if ("groupname".equalsIgnoreCase(filterBy)) {
				return GROUP_NAME;
			}
			if ("typeid".equalsIgnoreCase(filterBy)) {
				return TYPE_ID;
			}
			if ("typename".equalsIgnoreCase(filterBy)) {
				return TYPE_NAME;
			}
			return ERROR;
		}
	}

	static record KillsFilters(
			List<Integer> types, String timeSort, AGGREG_PERIOD period) {
	}

	static final DateTimeFormatter FORMATTER_YM = DateTimeFormatter.ofPattern("YYYY-MM");

	// bug : does not work for some specific timestamp. see corresponding test class
	static String formatInstantAsYearMonth2(Instant instant) {
		return FORMATTER_YM.format(instant.atOffset(ZoneOffset.UTC));
	}

	static record PeriodKillStats(String periodStart, long numberKills, double totalMIskLost, double averageMIskLost,
			double medianMIskLost) {

		public PeriodKillStats(KillStats source, AGGREG_PERIOD period) {
			this(period.format(source.periodStart()),
					source.nbKills(),
					source.totalIskLost() / 1000000,
					source.totalIskLost() / 1000000 / source.nbKills(),
					source.medianIskLost() / 1000000);
		}

	}

	static record TypesKillsStats(KillsFilters filters, List<PeriodKillStats> stats) {

		TypesKillsStats(List<Integer> types, String timeSort, List<KillStats> periodStats, AGGREG_PERIOD period) {
			this(new KillsFilters(types, timeSort, period),
					periodStats.stream().map(s -> new PeriodKillStats(s, period)).toList());
		}
	}

	@GetMapping("/{filterBy}/{filter}")
	public ResponseEntity<?> statsByVictim(@PathVariable String filterBy, @PathVariable String filter,
			@RequestParam Optional<String> accept,
			@RequestParam Optional<String> time,
			@RequestParam Optional<String> period) {
		TYPES_FILTER typeFilter = TYPES_FILTER.of(filterBy);
		TYPES_NAME resolved;
		try {
			resolved = typeFilter.resolve(filter, typeService, groupService);
		} catch (NumberFormatException e) {
			return ResponseEntity.status(400).body("param " + filter + " should be a number");
		}
		String timeOrder = time.orElse("desc");
		AGGREG_PERIOD ap = AGGREG_PERIOD.by(period);
		List<KillStats> stats = ap.stats(resolved.typeIds(), killService);
		if (timeOrder.equals("asc")) {
			Collections.reverse(stats);
		}
		return RestControllerHelper.makeResponse(
				new TypesKillsStats(resolved.typeIds(), timeOrder, stats, ap),
				accept);
	}

	@GetMapping("/{filterBy}/{filter}/chart")
	public void chartStatsByVictimType(@PathVariable String filterBy, @PathVariable String filter,
			HttpServletResponse response,
			@RequestParam Optional<String> period,
			@RequestParam Optional<String> accept) throws IOException {
		TYPES_FILTER typeFilter = TYPES_FILTER.of(filterBy);
		TYPES_NAME resolved;
		try {
			resolved = typeFilter.resolve(filter, typeService, groupService);
		} catch (NumberFormatException e) {
			response.sendError(400, "param " + filter + " should be a number");
			return;
		}
		AGGREG_PERIOD ap = AGGREG_PERIOD.by(period);
		List<KillStats> stats = typeFilter == TYPES_FILTER.ERROR ? Collections.emptyList()
				: ap.stats(resolved.typeIds(), killService);
		JFreeChart chart = drawChart(stats, "kills of " + resolved.name(), ap);
		switch (accept.orElse("png").toLowerCase()) {
			case "jpg":
			case "jpeg":
				response.setContentType(MediaType.IMAGE_JPEG_VALUE);
				ChartUtils.writeBufferedImageAsJPEG(response.getOutputStream(), chart.createBufferedImage(2000, 1000));
			break;
			case "png":
			default:
				response.setContentType(MediaType.IMAGE_PNG_VALUE);
				ChartUtils.writeBufferedImageAsPNG(response.getOutputStream(), chart.createBufferedImage(2000, 1000));
		}
	}

	static JFreeChart drawChart(List<KillStats> stats, String title, AGGREG_PERIOD by) {
		TimeSeries medianValueSeries = new TimeSeries("median value");
		TimeSeries avgValueSeries = new TimeSeries("average value");
		TimeSeries minValueSeries = new TimeSeries("min value");
		TimeSeries qttySeries = new TimeSeries("daily kills");
		for (KillStats stat : stats) {
			OffsetDateTime dat = stat.periodStart().atOffset(ZoneOffset.UTC);
			RegularTimePeriod period = by.of(dat);
			medianValueSeries.add(period, stat.medianIskLost() / 1000000);
			avgValueSeries.add(period, stat.totalIskLost() / 1000000 / stat.nbKills());
			minValueSeries.add(period, stat.minIskLost() / 1000000);
			int periodDays = by.periodDays(dat);
			qttySeries.add(period, 1.0 / periodDays * stat.nbKills());
		}

		XYPlot plot = new XYPlot();
		DateAxis timeAxis = new DateAxis(null);
		timeAxis.setLowerMargin(0.02);
		timeAxis.setUpperMargin(0.02);
		plot.setDomainAxis(timeAxis);

		{
			NumberAxis valueAxis = new NumberAxis("M isk");
			valueAxis.setAutoRangeIncludesZero(true);
			plot.setRangeAxis(0, valueAxis);
			TimeSeriesCollection tsc = new TimeSeriesCollection(avgValueSeries);
			tsc.addSeries(medianValueSeries);
// tsc.addSeries(minValueSeries);
			plot.setDataset(0, tsc);
			XYLineAndShapeRenderer renderer0 = new XYLineAndShapeRenderer(false,
					true);
			plot.setRenderer(0, renderer0);
			plot.mapDatasetToRangeAxis(0, 0);
		}

		{
			NumberAxis qttyAxis = new NumberAxis("avg kills per day");
			plot.setRangeAxis(1, qttyAxis);
			TimeSeriesCollection qttyColl = new TimeSeriesCollection(qttySeries);
			plot.setDataset(1, qttyColl);
			XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(false,
					true);
			plot.setRenderer(1, renderer1);
			plot.mapDatasetToRangeAxis(1, 1);
		}

		JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
				plot, true);
		chart.setBackgroundPaint(Color.WHITE);

		return chart;
	}

}

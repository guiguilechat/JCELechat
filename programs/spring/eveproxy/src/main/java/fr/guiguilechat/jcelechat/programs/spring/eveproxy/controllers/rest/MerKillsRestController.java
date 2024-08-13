package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.awt.Color;
import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Group;
import fr.guiguilechat.jcelechat.libs.spring.items.type.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.mer.kill.KillService;
import fr.guiguilechat.jcelechat.libs.spring.mer.kill.KillService.KillStats;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// @Slf4j
@RestController
@RequestMapping("/api/mer/kills")
@RequiredArgsConstructor
public class MerKillsRestController {

	final private KillService killService;

	final private TypeService typeService;

	final private GroupService groupService;

	/**
	 * period to aggregate kills over
	 */
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

	static record NAMED_TYPELIST(String name, List<Integer> typeIds) {
	}

	/**
	 * request to filter the typed by
	 */
	static enum TYPE_FILTER_STRATEGY {
		TYPE_ID {
			@Override
			public NAMED_TYPELIST resolve(String filterparam, TypeService typeService, GroupService groupService) {
				int typeId = Integer.parseInt(filterparam);
				Type type = typeService.byId(typeId);
				return type == null ? new NAMED_TYPELIST("unknown t" + typeId, Collections.emptyList())
				    : new NAMED_TYPELIST(type.name(), List.of(typeId));
			}
		},
		GROUP_ID {
			@Override
			public NAMED_TYPELIST resolve(String filterparam, TypeService typeService, GroupService groupService) {
				int groupId = Integer.parseInt(filterparam);
				Group group = groupService.byId(groupId);
				return group == null ? new NAMED_TYPELIST("unknown g" + groupId, Collections.emptyList())
						: new NAMED_TYPELIST(group.getName(),
				        typeService.byGroupId(groupId).stream().map(Type::getId).distinct().sorted().toList());
			}
		},
		TYPE_NAME {
			@Override
			public NAMED_TYPELIST resolve(String filterparam, TypeService typeService, GroupService groupService) {
				List<Type> list = typeService.searchByName(filterparam);
				if (list.isEmpty()) {
					return new NAMED_TYPELIST("unmatched type name " + filterparam, Collections.emptyList());
				}
				String name = list.size() == 1 ? list.get(0).name()
						: "matched " + list.size() + " type names " + filterparam;
				return new NAMED_TYPELIST(name, list.stream().map(Type::getId).sorted().toList());
			}
		},
		GROUP_NAME {
			@Override
			public NAMED_TYPELIST resolve(String filterparam, TypeService typeService, GroupService groupService) {
				List<Group> list = groupService.byName(filterparam);
				if (list.isEmpty()) {
					return new NAMED_TYPELIST("unmatched group name " + filterparam, Collections.emptyList());
				}
				String name = list.size() == 1 ? list.get(0).getName()
						: "matched " + list.size() + " group names " + filterparam;
				return new NAMED_TYPELIST(name,
				    list.stream().flatMap(g -> typeService.byGroupId(g.getId()).stream()).map(Type::getId)
								.distinct().sorted().toList());
			}
		},
		ERROR {
			@Override
			public NAMED_TYPELIST resolve(String filterparam, TypeService typeService, GroupService groupService) {
				return new NAMED_TYPELIST("invalid selector", Collections.emptyList());
			}
		};

		public abstract NAMED_TYPELIST resolve(String filterparam, TypeService typeService, GroupService groupService);

		static TYPE_FILTER_STRATEGY of(String filterBy) {
			return switch (Objects.requireNonNullElse(filterBy, "").toLowerCase()) {
				case "gi", "gid", "groupid" -> GROUP_ID;
				case "gn", "gname", "groupname" -> GROUP_NAME;
				case "ti", "tid", "typeid" -> TYPE_ID;
				case "tn", "tname", "typename" -> TYPE_NAME;
				default -> ERROR;
			};
		}
	}

	static record KillsFilters(
			List<Integer> types, String timeSort, AGGREG_PERIOD period) {
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

	/**
	 * @param filterBy  method to filter the solar system. Must be one of gi, gn,
	 *                    ti,
	 *                    tn
	 * @param filter
	 * @param accept
	 * @param order
	 * @param aggregate
	 * @return
	 */

	@Transactional
	@GetMapping("/{filterBy}/{filter}")
	public ResponseEntity<TypesKillsStats> statsByVictim(
			@PathVariable String filterBy,
			@PathVariable String filter,
			@RequestParam Optional<String> accept,
	    @RequestParam Optional<String> order,
	    @RequestParam Optional<String> aggregate) {
		TYPE_FILTER_STRATEGY typeFilter = TYPE_FILTER_STRATEGY.of(filterBy);
		NAMED_TYPELIST resolved;
		try {
			resolved = typeFilter.resolve(filter, typeService, groupService);
		} catch (NumberFormatException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "param " + filter + " should be a number");
		}
		String timeOrder = order.orElse("desc");
		AGGREG_PERIOD ap = AGGREG_PERIOD.by(aggregate);
		List<KillStats> stats = ap.stats(resolved.typeIds(), killService);
		if (timeOrder.equals("asc")) {
			Collections.reverse(stats);
		}
		return RestControllerHelper.makeResponse(
				new TypesKillsStats(resolved.typeIds(), timeOrder, stats, ap),
				accept);
	}

	@Transactional
	@GetMapping("/{filterBy}/{filter}/chart")
	public void chartStatsByVictimType(
			@PathVariable String filterBy,
			@PathVariable String filter,
			HttpServletResponse response,
			@RequestParam Optional<String> aggregate,
			@RequestParam Optional<String> accept) throws IOException {
		TYPE_FILTER_STRATEGY typeFilter = TYPE_FILTER_STRATEGY.of(filterBy);
		NAMED_TYPELIST resolved;
		try {
			resolved = typeFilter.resolve(filter, typeService, groupService);
		} catch (NumberFormatException e) {
			response.sendError(400, "param " + filter + " should be a number");
			return;
		}
		AGGREG_PERIOD ap = AGGREG_PERIOD.by(aggregate);
		List<KillStats> stats = typeFilter == TYPE_FILTER_STRATEGY.ERROR ? Collections.emptyList()
				: ap.stats(resolved.typeIds(), killService);
		JFreeChart chart = drawChart(stats, "kills of " + resolved.name() + " by " + ap.name().toLowerCase(), ap);
		RestControllerHelper.addResponseChart(response, chart, accept);
	}

	static JFreeChart drawChart(List<KillStats> stats, String title, AGGREG_PERIOD by) {
		TimeSeries medianValueSeries = new TimeSeries("median value");
		TimeSeries avgValueSeries = new TimeSeries("average value");
		TimeSeries minValueSeries = new TimeSeries("min value");
		TimeSeries qttySeries = new TimeSeries("daily kills");
		for (KillStats stat : stats) {
			OffsetDateTime odt = stat.periodStart().atOffset(ZoneOffset.UTC);
			RegularTimePeriod period = by.of(odt);
			medianValueSeries.add(period, stat.medianIskLost() / 1000000);
			avgValueSeries.add(period, stat.totalIskLost() / 1000000 / stat.nbKills());
			minValueSeries.add(period, stat.minIskLost() / 1000000);
			int periodDays = by.periodDays(odt);
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

	@Transactional
	@GetMapping("/{filterBy}/{filter}/detail")
	public ResponseEntity<List<Entry<String, TypesKillsStats>>> statsByVictimTypeDetailed(
			@PathVariable String filterBy,
			@PathVariable String filter,
			@RequestParam Optional<String> accept,
			@RequestParam Optional<String> time,
			@RequestParam Optional<String> period) {
		TYPE_FILTER_STRATEGY typeFilter = TYPE_FILTER_STRATEGY.of(filterBy);
		NAMED_TYPELIST resolved;
		try {
			resolved = typeFilter.resolve(filter, typeService, groupService);
		} catch (NumberFormatException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "param " + filter + " should be a number");
		}
		String timeOrder = time.orElse("desc");
		AGGREG_PERIOD ap = AGGREG_PERIOD.by(period);
		Map<String, TypesKillsStats> statsByType = resolved.typeIds.parallelStream().collect(
		    Collectors.toMap(typeId -> typeService.byId(typeId).name(),
						typeId -> new TypesKillsStats(List.of(typeId), timeOrder, ap.stats(List.of(typeId), killService), ap)));
		if (timeOrder.equals("asc")) {
			for (TypesKillsStats stats : statsByType.values()) {
				Collections.reverse(stats.stats());
			}
		}
		return RestControllerHelper.makeResponse(
				statsByType.entrySet().stream().filter(e -> !e.getValue().stats().isEmpty())
						.sorted(Comparator.comparing(Entry::getKey)).toList(),
				accept);
	}

	@Transactional
	@GetMapping("/{filterBy}/{filter}/chart/{detail}")
	public void chartStatsByVictimTypeDetailed(
			@PathVariable String filterBy,
			@PathVariable String filter,
			@PathVariable String detail,
			HttpServletResponse response,
			@RequestParam Optional<String> period,
			@RequestParam Optional<String> accept) throws IOException {
		TYPE_FILTER_STRATEGY typeFilter = TYPE_FILTER_STRATEGY.of(filterBy);
		NAMED_TYPELIST resolved;
		try {
			resolved = typeFilter.resolve(filter, typeService, groupService);
		} catch (NumberFormatException e) {
			response.sendError(400, "param " + filter + " should be a number");
			return;
		}
		KILLS_DETAIL det = KILLS_DETAIL.of(detail);
		AGGREG_PERIOD ap = AGGREG_PERIOD.by(period);
		Map<String, List<KillStats>> statsByType = resolved.typeIds.parallelStream().collect(
		    Collectors.toMap(typeId -> typeService.byId(typeId).name(),
						typeId -> ap.stats(List.of(typeId), killService)));
		JFreeChart chart = drawChart(statsByType,
				det.legend + " for " + resolved.name() + ", by " + ap.name().toLowerCase(), ap,
				det);
		RestControllerHelper.addResponseChart(response, chart, accept);
	}

	/**
	 * requested kill details over a period to chart on
	 */
	static enum KILLS_DETAIL {
		AVG_LOST("average M isk lost") {
			@Override
			public double extract(KillStats stat, AGGREG_PERIOD by) {
				return stat.totalIskLost() / stat.nbKills() / 100000;
			}
		},
		KILLS_P_DAY("kills per day") {
			@Override
			public double extract(KillStats stat, AGGREG_PERIOD by) {
				OffsetDateTime odt = stat.periodStart().atOffset(ZoneOffset.UTC);
				int periodDays = by.periodDays(odt);
				return 1.0 * stat.nbKills() / periodDays;
			}
		},
		MEDIAN_LOST("median M isk lost") {
			@Override
			public double extract(KillStats stat, AGGREG_PERIOD by) {
				return stat.medianIskLost() / 1000000;
			}
		},
		MIN_LOST("min M isk lost") {
			@Override
			public double extract(KillStats stat, AGGREG_PERIOD by) {
				return stat.minIskLost() / 1000000;
			}
		},
		TOTAL_LOST_P_DAY("total M isk lost per day") {
			@Override
			public double extract(KillStats stat, AGGREG_PERIOD by) {
				OffsetDateTime odt = stat.periodStart().atOffset(ZoneOffset.UTC);
				int periodDays = by.periodDays(odt);
				return stat.totalIskLost() / periodDays / 1000000;

			}
		};

		public final String legend;

		private KILLS_DETAIL(String legend) {
			this.legend = legend;
		}

		public abstract double extract(KillStats stat, AGGREG_PERIOD by);

		static KILLS_DETAIL of(String requested) {
			switch (Objects.requireNonNullElse(requested, "").toLowerCase()) {
				case "av":
				case "avg":
				case "average":
					return AVG_LOST;
				case "kill":
				case "kills":
				case "nb":
				case "qtty":
				case "quantity":
					return KILLS_P_DAY;
				case "md":
				case "med":
				case "median":
					return MEDIAN_LOST;
				case "low":
				case "min":
				case "minimum":
					return MIN_LOST;
				case "lost":
				case "tot":
				case "total":
				case "val":
				case "value":
					return TOTAL_LOST_P_DAY;
			}
			return KILLS_P_DAY;
		}
	}

	private JFreeChart drawChart(Map<String, List<KillStats>> statsByType, String title, AGGREG_PERIOD by,
			KILLS_DETAIL det) {
		XYPlot plot = new XYPlot();
		DateAxis timeAxis = new DateAxis(null);
		timeAxis.setLowerMargin(0.02);
		timeAxis.setUpperMargin(0.02);
		plot.setDomainAxis(timeAxis);

		List<Entry<String, List<KillStats>>> sortedStatsList = statsByType.entrySet().stream()
				.sorted(Comparator.comparing(Entry::getKey)).toList();

		{
			NumberAxis valueAxis = new NumberAxis(det.legend);
			plot.setRangeAxis(0, valueAxis);
			TimeSeriesCollection tsc = new TimeSeriesCollection();
			for (Entry<String, List<KillStats>> e : sortedStatsList) {
				if (e.getValue().isEmpty()) {
					continue;
				}
				TimeSeries totalIskLostSeries = new TimeSeries(e.getKey());
				for (KillStats stat : e.getValue()) {
					OffsetDateTime dat = stat.periodStart().atOffset(ZoneOffset.UTC);
					RegularTimePeriod period = by.of(dat);
					totalIskLostSeries.add(period, det.extract(stat, by));
				}
				tsc.addSeries(totalIskLostSeries);
			}
			plot.setDataset(0, tsc);
			XYLineAndShapeRenderer renderer0 = new XYLineAndShapeRenderer(false,
					true);
			plot.setRenderer(0, renderer0);
			plot.mapDatasetToRangeAxis(0, 0);
		}

		JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
				plot, true);
		chart.setBackgroundPaint(Color.WHITE);

		return chart;
	}

}

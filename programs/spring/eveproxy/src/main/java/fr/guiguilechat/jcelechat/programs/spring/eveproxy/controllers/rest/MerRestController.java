package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.mer.services.KillService;
import fr.guiguilechat.jcelechat.libs.spring.mer.services.KillService.MonthlyStats;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.MerRestController.TypeMonthlyKills.MonthKillStats;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.MerRestController.TypeMonthlyKills.MonthlyKillsSelection;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/mer")
public class MerRestController {

	@Autowired
	private KillService killService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private GroupService groupService;

	static record TypeMonthlyKills(MonthlyKillsSelection filters, List<MonthKillStats> stats) {

		static record MonthlyKillsSelection(
				List<Integer> types, String timeSort) {
		}

		static record MonthKillStats(String month, long numberKills, double totalMIskLost, double averageMIskLost,
				double medianMIskLost) {

			static final DateTimeFormatter FORMATTER_YM = DateTimeFormatter.ofPattern("YYYY-MM");

			public MonthKillStats(MonthlyStats source) {
				this(FORMATTER_YM.format(source.month().atOffset(ZoneOffset.UTC)),
						source.nbKills(),
						source.totalIskLost() / 1000000,
						source.totalIskLost() / 1000000 / source.nbKills(),
						source.medianIskLost() / 1000000);
			}

		}

		TypeMonthlyKills(List<Integer> types, String timeSort, List<MonthlyStats> monthlyStats) {
			this(new MonthlyKillsSelection(types, timeSort), monthlyStats.stream().map(MonthKillStats::new).toList());
		}

	}

	@GetMapping("/monthlykills/byVictimTypeId/{typeId}")
	public ResponseEntity<?> monthlyStatsByVictimType(@PathVariable int typeId, @RequestParam Optional<String> accept,
			@RequestParam Optional<String> time) {
		String timeOrder = time.orElse("desc");
		List<Integer> types = List.of(typeId);
		List<MonthlyStats> stats = killService.monthlyStats(types);
		if (timeOrder.equals("asc")) {
			Collections.reverse(stats);
		}
		return RestControllerHelper.makeResponse(
				new TypeMonthlyKills(types, timeOrder, stats),
				accept);
	}

	@GetMapping("/monthlykills/byVictimTypeId/{typeId}/chart")
	public void chartStatsByVictimType(@PathVariable int typeId, HttpServletResponse response) throws IOException {
		List<Integer> types = List.of(typeId);
		List<MonthlyStats> stats = killService.monthlyStats(types);
		Type type = typeService.byId(typeId).orElse(null);
		JFreeChart chart = chart(stats, "kills of type " + (type == null ? typeId : type.getName()));
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		ChartUtils.writeBufferedImageAsPNG(response.getOutputStream(), chart.createBufferedImage(1024, 768));
	}

	@GetMapping("/monthlykills/byVictimGroupId/{groupId}")
	public ResponseEntity<?> monthlyStatsByVictimGroup(@PathVariable int groupId, @RequestParam Optional<String> accept,
			@RequestParam Optional<String> time) {
		String timeOrder = time.orElse("desc");
		List<Integer> types = typeService.byGroupId(groupId).stream().map(Type::getTypeId).toList();
		List<MonthlyStats> stats = killService.monthlyStats(types);
		if (timeOrder.equals("asc")) {
			Collections.reverse(stats);
		}
		return RestControllerHelper.makeResponse(
				new TypeMonthlyKills(types, timeOrder, stats),
				accept);
	}

	@GetMapping("/monthlykills/byVictimGroupId/{groupId}/chart")
	public void chartStatsByVictimGroup(@PathVariable int groupId, HttpServletResponse response) throws IOException {
		List<Integer> types = typeService.byGroupId(groupId).stream().map(Type::getTypeId).toList();
		List<MonthlyStats> stats = killService.monthlyStats(types);
		Group group = groupService.byId(groupId).orElse(null);
		JFreeChart chart = chart(stats, "kills of group " + (group == null ? groupId : group.getName()));
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		ChartUtils.writeBufferedImageAsPNG(response.getOutputStream(), chart.createBufferedImage(1024, 768));
	}

	static JFreeChart chart(List<MonthlyStats> stats, String title) {
		TimeSeries medianSeries = new TimeSeries("median");
		TimeSeries avgSeries = new TimeSeries("average");
		for (MonthlyStats stat : stats) {
			OffsetDateTime dat = stat.month().atOffset(ZoneOffset.UTC);
			Month mth = new Month(dat.getMonthValue(), dat.getYear());
			medianSeries.add(mth, stat.medianIskLost() / 1000000);
			avgSeries.add(mth, stat.totalIskLost() / 1000000 / stat.nbKills());
		}
		TimeSeriesCollection tsc = new TimeSeriesCollection(avgSeries);
		tsc.addSeries(medianSeries);
		JFreeChart chart = ChartFactory.createTimeSeriesChart(title, null, "M isk", tsc);
		chart.getXYPlot().getRangeAxis().setLowerBound(0);
		return chart;
	}

}

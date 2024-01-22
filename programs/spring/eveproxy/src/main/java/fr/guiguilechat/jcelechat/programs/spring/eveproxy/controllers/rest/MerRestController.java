package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.mer.services.KillService;
import fr.guiguilechat.jcelechat.libs.spring.mer.services.KillService.MonthlyStats;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;

@RestController
@RequestMapping("/api/mer")
public class MerRestController {

	@Autowired
	private KillService killService;

	@Autowired
	private TypeService typeService;

	static record TypeMonthlyKills(MonthlyKillsSelection filters, List<MonthKillStats> stats) {

		static record MonthlyKillsSelection(
				List<Integer> types, String timeSort) {
		}

		static record MonthKillStats(String month, long numberKills, double totalMIskLost, double averageMIskLost,
				double medianMIskLost) {

			static final DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY-MM");

			public MonthKillStats(MonthlyStats source) {
				this(format.format(source.month()), source.nbKills(), source.totalIskLost() / 1000000,
						source.totalIskLost() / 1000000 / source.nbKills(), source.medianIskLost() / 1000000);
			}

		}

		TypeMonthlyKills(List<Integer> types, String timeSort, List<MonthlyStats> monthlyStats) {
			this(new MonthlyKillsSelection(types, timeSort), monthlyStats.stream().map(MonthKillStats::new).toList());
		}

	}

	@GetMapping("/kills/byVictimTypeId/{typeId}/monthly")
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

	@GetMapping("/kills/byVictimGroupId/{groupId}/monthly")
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

}

package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

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

	record MonthlyKillsFilters(
			List<Integer> types, String timeSort) {
	}

	record TypeMonthlyKills(MonthlyKillsFilters filters, List<MonthlyStats> stats) {

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
				new TypeMonthlyKills(new MonthlyKillsFilters(types, timeOrder), killService.monthlyStats(types)),
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
				new TypeMonthlyKills(new MonthlyKillsFilters(types, timeOrder), killService.monthlyStats(types)),
				accept);
	}

}

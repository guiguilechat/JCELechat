package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/market/activity")
@RequiredArgsConstructor
public class MarketActivityRestController {

	private final TypeService typeService;

	/**
	 * @param typeId   type id we want the history of
	 * @param response http response to add data into.
	 * @param accept   format of the image. values depend on the builder chosen.
	 * @param builder  builder for the chart. Can be "jfreechart" or "xchart"
	 *                 (default) .
	 * @throws IOException
	 */
	@Operation(summary = "chart region+type market activity", description = "create a chart of the orders activity for a type in a region")
	@Transactional // otherwise bug from retrieving data in pg
	@GetMapping("/chart/regionId/{regionId}/typeId/{typeId}")
	public void chartActivityByRegionType(
			@PathVariable @Parameter(description = "region id we want to chart the activity in") int regionId,
			@PathVariable @Parameter(description = "type id we want to chart the activity of") int typeId,
			HttpServletResponse response,
			@RequestParam @Parameter(description = "format of the image. values depend on the builder chosen. typically jpg, or png (ignore case)") Optional<
					String> accept,
			@RequestParam @Parameter(description = "maximum days to show. Default 7 days") Optional<Integer> days)
			throws IOException {
		Type type = typeService.ofId(typeId);
		if (type == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " unknown");
		}
		long start = System.currentTimeMillis();
//		List<AggregatedHL> fetchedData =
//				aggregatedDailyHistoryService
//						.typeHistory(typeId, days);
//		if (fetchedData.isEmpty()) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type " + typeId + " has no sale record");
//		}
//		log.trace("retrieved {} days for {}, on {} days, in {}ms",
//				fetchedData.size(),
//				type.nameId(),
//				days,
//				System.currentTimeMillis() - start);
//		String title =
//				"universe sales of " + type.nameId()
//						+ (days.isPresent() ? " over the last " + days.get() + " days" : "");
//		RestControllerHelper.setResponseTitle(response, type.name());
//
//		ChartTheme ct = ChartTheme.forName(theme.orElse(null));
//		switch (ChartBuilder.orDefault(builder)) {
//		case jfreechart:
//			RestControllerHelper.addResponseJFreeChart(response,
//					drawJFreeChart(fetchedData, averageDays, title, "items", ct),
//					accept);
//			break;
//		case xchart:
//			RestControllerHelper.addResponseXChart(response, drawXChart(fetchedData, title), accept);
//			break;
//		default:
//			throw new UnsupportedOperationException("unsupported case " + ChartBuilder.orDefault(builder));
//		}
	}

}

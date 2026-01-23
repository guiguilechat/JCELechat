package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.market;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.marketranking.SpRateService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.StationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/html/market/accelerators")
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SpRateHTMLController {

	private final SpRateService acceleratorsRatingService;

	private final StationService stationService;

	@Transactional
	@GetMapping("rate")
	public String getAccelerators(Model model,
			@RequestParam Optional<Long> locationId,
			@RequestParam Optional<Long> sps)
			throws InterruptedException, ExecutionException {
		long locId = locationId.orElse(Station.JITA_HUB_ID);
		long spVal = sps.orElse(0L);
		model.addAttribute("sp", spVal);
		model.addAttribute("locId", locId);
		model.addAttribute("accelerators", acceleratorsRatingService.rate(locId, sps));

		Station sta = locId > Integer.MAX_VALUE
				? null
				: stationService.ofId((int) locId);
		String locationName = sta == null ? "location:" + locationId : sta.name();
		model.addAttribute("locationName", locationName);
		model.addAttribute("amarrUrl", rateAcceleratorsUrl(Station.AMARR_HUB_ID, spVal));
		model.addAttribute("dodixieUrl", rateAcceleratorsUrl(Station.DODIXIE_HUB_ID, spVal));
		model.addAttribute("hekUrl", rateAcceleratorsUrl(Station.HEK_HUB_ID, spVal));
		model.addAttribute("jitaUrl", rateAcceleratorsUrl(Station.JITA_HUB_ID, spVal));
		model.addAttribute("rensUrl", rateAcceleratorsUrl(Station.RENS_HUB_ID, spVal));
		return "market/sprate";
	}

	public String rateAcceleratorsUrl(Long locationId, long sps) {
		return MvcUriComponentsBuilder
				.fromMethodName(getClass(), "getAccelerators", null,
						locationId,
						sps)
				.build()
				.toString();
	}

	public String rootUrl() {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getAccelerators", (Model) null, null, null).build()
				.toUri()
				.toString();
	}

}

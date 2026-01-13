package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.market;

import java.net.URI;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.marketranking.AcceleratorsRatingService;
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
public class AcceleratorHTMLController {

	private final AcceleratorsRatingService acceleratorsRatingService;

	private final StationService stationService;

	@Transactional
	@GetMapping("rate")
	public String getAccelerators(Model model,
			@RequestParam Optional<Long> locationId)
			throws InterruptedException, ExecutionException {
		long locId = locationId.orElse(Station.JITA_HUB_ID);
		model.addAttribute("accelerators", acceleratorsRatingService.rate(locId));

		Station sta = locId > Integer.MAX_VALUE
				? null
				: stationService.ofId((int) locId);
		String locationName = sta == null ? "location:" + locationId : sta.name();
		model.addAttribute("locationName", locationName);
		model.addAttribute("amarrUrl", rateAcceleratorsURI(Station.AMARR_HUB_ID).toString());
		model.addAttribute("dodixieUrl", rateAcceleratorsURI(Station.DODIXIE_HUB_ID).toString());
		model.addAttribute("hekUrl", rateAcceleratorsURI(Station.HEK_HUB_ID).toString());
		model.addAttribute("jitaUrl", rateAcceleratorsURI(Station.JITA_HUB_ID).toString());
		model.addAttribute("rensUrl", rateAcceleratorsURI(Station.RENS_HUB_ID).toString());

		return "market/accelerators";
	}

	public URI rateAcceleratorsURI(Long locationId) {
		return MvcUriComponentsBuilder
				.fromMethodName(getClass(), "getAccelerators", null,
						locationId)
				.build()
				.toUri();
	}

	public String rootUrl() {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getAccelerators", (Model) null, null).build()
				.toUri()
				.toString();
	}

}

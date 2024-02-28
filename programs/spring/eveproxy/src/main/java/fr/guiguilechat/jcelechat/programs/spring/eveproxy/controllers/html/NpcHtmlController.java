package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.CorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.services.CorporationOfferService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.DogmaHtmlController.LinkedType;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.OfferValueService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.OfferValueService.OfferEval;

@Controller
@RequestMapping("/html/npc")
public class NpcHtmlController {

	@Autowired
	private CorporationOfferService corporationOfferService;

	@Autowired
	private OfferValueService offerValueService;

	private DogmaHtmlController dogmaHtmlController;

	@Autowired
	public void setDogmaHtmlController(@Lazy DogmaHtmlController dogmaHtmlController) {
		this.dogmaHtmlController = dogmaHtmlController;
	}

	@GetMapping("/corporation/{corporationId}/offer/{offerId}")
	public String getOffer(Model model, @PathVariable int corporationId, @PathVariable int offerId) {
		List<CorporationOffer> offers = corporationOfferService.forCorporationOffer(corporationId, offerId);
		if (offers.size() != 1) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"no offer for corporation " + corporationId + " and offer id " + offerId);
		}
		CorporationOffer offer = offers.get(0);

		return "npc/offer";
	}

	public URI uri(CorporationOffer offer) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getOffer", null,
				offer.getCorporation().getCorporationId(), offer.getOfferId()).build()
				.toUri();
	}

	public static record LinkedOffer(String url, OfferEval eval, LinkedType product) {

		public String name() {
			return "[" + eval.offer().getOfferId() + "] " + eval.offer().getQuantity() + "×" + eval.product().getName();
		}
	}

	LinkedOffer linkedOffer(OfferEval eval) {
		return new LinkedOffer(uri(eval.offer()).toString(), eval, dogmaHtmlController.linkedType(eval.product()));
	}

	@GetMapping("/corporation/{corporationId}")
	public String getCorporationOffers(Model model, @PathVariable int corporationId,
			@RequestParam Optional<Integer> lp,
			@RequestParam Optional<Double> brpct,
			@RequestParam Optional<Double> taxpct,
			@RequestParam Optional<Double> margin,
			@RequestParam Optional<Double> marginhour,
			@RequestParam Optional<Double> bpcost,
			@RequestParam Optional<Long> location) {
		model.addAttribute("corpName", "" + corporationId);
		List<CorporationOffer> offers = corporationOfferService.byCorporationId(corporationId);
		List<CompletableFuture<OfferEval>> futures = offers.stream()
				.filter(offer -> offer.getLpCost() > 0)
				.map(offer -> offerValueService.value(offer, lp.orElse(100000), brpct.orElse(2.0), taxpct.orElse(3.6),
						margin.orElse(5.0), marginhour.orElse(0.5), bpcost.orElse(100000.0),
						location.orElse(RegionLineService.JITAIV_ID)))
				.toList();
		model.addAttribute("offers",
				futures.stream().map(CompletableFuture::join)
				.sorted(Comparator.comparing(OfferEval::iplSobo).reversed())
				.map(this::linkedOffer)
				.toList());
		return "npc/corporation";
	}

	public URI uri(int corporationId) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getOffers", null, corporationId).build()
				.toUri();
	}
}

package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
import fr.guiguilechat.jcelechat.libs.spring.npc.model.LPStoreCorporation;
import fr.guiguilechat.jcelechat.libs.spring.npc.services.CorporationOfferService;
import fr.guiguilechat.jcelechat.libs.spring.npc.services.LPStoreCorporationService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.DogmaHtmlController.LinkedType;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.OfferValueService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.OfferValueService.OfferEval;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.OfferValueService.SourceType;

@Controller
@RequestMapping("/html/npc")
public class NpcHtmlController {

	@Autowired
	private CorporationOfferService corporationOfferService;

	@Autowired
	private LPStoreCorporationService lpStoreCorporationService;

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
		model.addAttribute("offer", offer);
		model.addAttribute("offerName", "" + offer.getType().getName());
		model.addAttribute("offerMats", offer.getRequirements().stream()
				.map(mat -> dogmaHtmlController.linkedMaterial(mat.getType(), mat.getQuantity())));
		return "npc/offer";
	}

	public URI uri(CorporationOffer offer) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getOffer", null,
				offer.getCorporation().getCorporationId(), offer.getOfferId()).build()
				.toUri();
	}

	public static record LinkedOffer(String url, OfferEval eval, LinkedType finalProduct) {

		public String name() {
			return "[" + eval.offer().getOfferId() + "] " + eval.offer().getQuantity() + "×" + eval.product().getName();
		}
	}

	LinkedOffer linkedOffer(OfferEval eval) {
		return new LinkedOffer(uri(eval.offer()).toString(), eval, dogmaHtmlController.linkedType(eval.finalProduct()));
	}

	@GetMapping("/corporation/{corporationId}")
	public String getCorporationOffers(Model model, @PathVariable int corporationId,
			@RequestParam Optional<Double> bpcost,
			@RequestParam Optional<Double> brpct,
			@RequestParam Optional<Long> location,
			@RequestParam Optional<Integer> lp,
			@RequestParam Optional<Double> margin,
			@RequestParam Optional<Double> marginhour,
			@RequestParam Optional<SourceType> sourcing,
			@RequestParam Optional<Double> taxpct) {

		double bpcost_ = bpcost.orElse(1000000.0);
		double brpct_ = brpct.orElse(2.0);
		long location_ = location.orElse(RegionLineService.JITAIV_ID);
		int lp_ = lp.orElse(100000);
		double margin_ = margin.orElse(5.0);
		double marginhour_ = marginhour.orElse(0.5);
		SourceType sourcing_ = sourcing.orElse(SourceType.sobo);
		double taxpct_ = taxpct.orElse(3.6);

		LPStoreCorporation corp = lpStoreCorporationService.byId(corporationId).orElse(null);
		model.addAttribute("corpName", corp == null ? "" + corporationId : corp.nameOrId());

		LPStoreCorporation prevCorp = lpStoreCorporationService.prevCorp(corp);
		if (prevCorp != null) {
			model.addAttribute("prevCorpName", prevCorp.nameOrId());
			model.addAttribute("prevCorpUrl",
					uri(prevCorp, bpcost, brpct, location, lp, margin, marginhour, sourcing, taxpct).toString());
		}

		LPStoreCorporation nextCorp = lpStoreCorporationService.nextCorp(corp);
		if (nextCorp != null) {
			model.addAttribute("nextCorpName", nextCorp.nameOrId());
			model.addAttribute("nextCorpUrl",
					uri(nextCorp, bpcost, brpct, location, lp, margin, marginhour, sourcing, taxpct).toString());
		}

		List<CorporationOffer> offers = corporationOfferService.byCorporationIdRequiringLp(corporationId);

		model.addAttribute("offers",
				offerValueService.value(offers, lp_, sourcing_, brpct_, taxpct_, marginhour_, margin_, bpcost_, location_)
						.stream()
						.sorted(Comparator.comparing(OfferEval::iskplp).reversed())
						.map(this::linkedOffer)
						.toList());

		model.addAttribute("bpcost", bpcost_);
		model.addAttribute("brpct", brpct_);
		model.addAttribute("location", location_);
		model.addAttribute("lp", lp_);
		model.addAttribute("margin", margin_);
		model.addAttribute("marginhour", marginhour_);
		model.addAttribute("sourcing", sourcing_);
		model.addAttribute("taxpct", taxpct_);

		return "npc/corporation";
	}

	public URI uri(int corporationId) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getCorporationOffers", null, corporationId).build()
				.toUri();
	}

	public URI uri(LPStoreCorporation lpc,
			Optional<Double> bpcost,
			Optional<Double> brpct,
			Optional<Long> location,
			Optional<Integer> lp,
			Optional<Double> margin,
			Optional<Double> marginhour,
			Optional<SourceType> sourcing,
			Optional<Double> taxpct) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getCorporationOffers", null, lpc.getCorporationId(),
				bpcost,
				brpct,
				location,
				lp,
				margin,
				marginhour,
				sourcing,
				taxpct).build()
				.toUri();
	}
}

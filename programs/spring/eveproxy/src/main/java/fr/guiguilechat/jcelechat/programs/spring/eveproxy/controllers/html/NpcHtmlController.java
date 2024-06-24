package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.net.URI;
import java.util.Comparator;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.npc.lp.LinkCorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.lp.LinkCorporationOfferService;
import fr.guiguilechat.jcelechat.libs.spring.npc.lp.ObservedCorporation;
import fr.guiguilechat.jcelechat.libs.spring.npc.lp.ObservedCorporationService;
import fr.guiguilechat.jcelechat.libs.spring.npc.lp.Offer;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.DogmaHtmlController.LinkedMaterial;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.DogmaHtmlController.LinkedType;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.LPOfferEvalService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.LPOfferEvalService.EvalParams;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.LPOfferEvalService.LPOfferEval;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/html/npc")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class NpcHtmlController {

	private final CorporationInfoService corporationInfoService;

	private final LinkCorporationOfferService linkCorporationOfferService;

	private final ObservedCorporationService observedCorporationService;

	private final LPOfferEvalService offerValueService;

	@Lazy
	private final DogmaHtmlController dogmaHtmlController;

	@Transactional
	@GetMapping("/corporation/{corporationId}/offer/{offerId}")
	public String getOffer(Model model, @PathVariable int corporationId, @PathVariable int offerId) {
		List<LinkCorporationOffer> offers = linkCorporationOfferService.forCorporationOffer(corporationId, offerId);
		if (offers.size() != 1) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"found " + offers.size() + " offer for corporation " + corporationId + " and offer id " + offerId);
		}
		LinkCorporationOffer link = offers.get(0);
		Offer offer = link.getOffer();
		model.addAttribute("offer", offer);
		model.addAttribute("offerMats", offer.getRequirements().stream()
				.map(mat -> dogmaHtmlController.linkedMaterial(mat.getType(), mat.getQuantity()))
				.toList());
		model.addAttribute("corporation", linkedObservedCorporation(link.getObserved()));
		model.addAttribute("product", dogmaHtmlController.linkedType(offer.getType()));
		return "npc/offer";
	}

	public URI uri(LinkCorporationOffer link) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getOffer", null,
		    link.getObserved().getId(), link.getOffer().getId()).build()
				.toUri();
	}

	public static record LinkedLPOffer(String url, LinkCorporationOffer offer) {
		public String name() {
			Offer off = offer.getOffer();
			return off.getType().getName() + (off.getQuantity() > 1 ? "×" + off.getQuantity() : "");
		}
	}

	public LinkedLPOffer linkedLPOffer(LinkCorporationOffer offer) {
		return new LinkedLPOffer(uri(offer).toString(), offer);
	}

	public static record LinkedLPOfferEval(String url, LPOfferEval eval, LinkedType finalProduct,
			List<LinkedMaterial> materials) {

		public String name() {
			return (eval.getOffer().getQuantity() > 1 ? eval.getOffer().getQuantity() + "×" : "")
					+ eval.getProduct().getName();
		}
	}

	public LinkedLPOfferEval linkedLPOfferEval(LPOfferEval eval) {
		return new LinkedLPOfferEval(
		    uri(eval.getCorpOffer()).toString(),
				eval,
				dogmaHtmlController.linkedType(eval.getFinalProduct()),
				eval.getMaterialsByTypeId().entrySet().stream()
						.map(e -> dogmaHtmlController.linkedMaterial(e.getKey(), e.getValue()))
						.sorted(Comparator.comparing(lm -> lm.type().getName()))
						.toList());
	}

	@GetMapping("/corporation/{corporationId}")
	public String getCorporationOffers(Model model, @PathVariable int corporationId,
			EvalParams params) {

		CorporationInfo corp = corporationInfoService.byId(corporationId);
		model.addAttribute("corpName", corp == null ? "absent_" + corporationId : corp.nameOrId());

		ObservedCorporation prevCorp = observedCorporationService.prevCorp(corp.nameOrId());
		if (prevCorp != null) {
			model.addAttribute("prevCorpName", prevCorp.getCorporationInfo().nameOrId());
			model.addAttribute("prevCorpUrl",
					uri(prevCorp, params).toString());
		}

		ObservedCorporation nextCorp = observedCorporationService.nextCorp(corp.nameOrId());
		if (nextCorp != null) {
			model.addAttribute("nextCorpName", nextCorp.getCorporationInfo().nameOrId());
			model.addAttribute("nextCorpUrl",
					uri(nextCorp, params).toString());
		}

		List<LinkCorporationOffer> offers = linkCorporationOfferService.byObservedIdRequiringLp(corporationId,
		    params.getLp());

		model.addAttribute("offers",
				offerValueService.value(offers, params.getLp(), params.getMaterialSourcing(), params.getProductValuator(),
						params.getBrpct(),
						params.getTaxpct(), params.getMargin(), params.getMarginPerHour(), params.getBpcost(), params.getLocation())
						.stream()
						.sorted(Comparator.comparing(LPOfferEval::getIskplp).reversed())
						.map(this::linkedLPOfferEval)
						.toList());

		model.addAttribute("params", params);

		return "npc/corporation";
	}

	public URI uri(ObservedCorporation corporation, EvalParams params) {
		return MvcUriComponentsBuilder
		    .fromMethodName(getClass(), "getCorporationOffers", null, corporation.getId(), params).build()
				.toUri();
	}

	public URI uri(ObservedCorporation corporation) {
		return uri(corporation, new EvalParams());
	}

	public static record LinkedObservedCorporation(String url, String name, int nbOffers) {

	}

	public LinkedObservedCorporation linkedObservedCorporation(ObservedCorporation corp) {
		return new LinkedObservedCorporation(uri(corp).toString(), corp.getCorporationInfo().getName(), corp.getNbOffers());
	}

	@GetMapping("/corporations")
	public String corporationsIndex(Model model) {
		model.addAttribute("corporations",
		    observedCorporationService.allById().values().stream()
						.map(this::linkedObservedCorporation)
						.filter(ci -> ci.nbOffers() > 0)
		        .sorted(Comparator.comparing(LinkedObservedCorporation::name))
						.toList());
		return "npc/corporations";
	}

}

package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.net.URI;
import java.util.Comparator;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.npc.model.CorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.LPStoreCorporation;
import fr.guiguilechat.jcelechat.libs.spring.npc.services.CorporationOfferService;
import fr.guiguilechat.jcelechat.libs.spring.npc.services.LPStoreCorporationService;
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

	private final CorporationOfferService corporationOfferService;

	private final LPStoreCorporationService lpStoreCorporationService;

	private final LPOfferEvalService offerValueService;

	@Lazy
	private final DogmaHtmlController dogmaHtmlController;

	@GetMapping("/corporation/{corporationId}/offer/{offerId}")
	public String getOffer(Model model, @PathVariable int corporationId, @PathVariable int offerId) {
		List<CorporationOffer> offers = corporationOfferService.forCorporationOffer(corporationId, offerId);
		if (offers.size() != 1) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"found " + offers.size() + " offer for corporation " + corporationId + " and offer id " + offerId);
		}
		CorporationOffer offer = offers.get(0);
		model.addAttribute("offer", offer);
		model.addAttribute("offerMats", offer.getRequirements().stream()
				.map(mat -> dogmaHtmlController.linkedMaterial(mat.getType(), mat.getQuantity()))
				.toList());
		model.addAttribute("corporation", linkedCorporationInfo(offer.getCorporation()));
		model.addAttribute("product", dogmaHtmlController.linkedType(offer.getType()));
		return "npc/offer";
	}

	public URI uri(CorporationOffer offer) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getOffer", null,
				offer.getCorporation().getCorporationId(), offer.getOfferId()).build()
				.toUri();
	}

	public static record LinkedLPOffer(String url, CorporationOffer offer) {

	}

	public LinkedLPOffer linkedLPOffer(CorporationOffer offer) {
		return new LinkedLPOffer(uri(offer).toString(), offer);
	}

	public static record LinkedLPOfferEval(String url, LPOfferEval eval, LinkedType finalProduct,
			List<LinkedMaterial> materials) {

		public String name() {
			return "[" + eval.getOffer().getCorporation().getName() + "] "
					+ (eval.getOffer().getQuantity() > 1 ? eval.getOffer().getQuantity() + "×" : "")
					+ eval.getProduct().getName();
		}
	}

	public LinkedLPOfferEval linkedLPOfferEval(LPOfferEval eval) {
		return new LinkedLPOfferEval(
				uri(eval.getOffer()).toString(),
				eval,
				dogmaHtmlController.linkedType(eval.getFinalProduct()),
				eval.getMaterialsByTypeId().entrySet().stream()
						.map(e -> dogmaHtmlController.linkedMaterial(e.getKey(), e.getValue()))
						.sorted(Comparator.comparing(lm -> lm.type().getName()))
						.toList()
		);
	}

	@GetMapping("/corporation/{corporationId}")
	public String getCorporationOffers(Model model, @PathVariable int corporationId,
			EvalParams params) {

		LPStoreCorporation corp = lpStoreCorporationService.byId(corporationId).orElse(null);
		model.addAttribute("corpName", corp == null ? "" + corporationId : corp.nameOrId());

		LPStoreCorporation prevCorp = lpStoreCorporationService.prevCorp(corp);
		if (prevCorp != null) {
			model.addAttribute("prevCorpName", prevCorp.nameOrId());
			model.addAttribute("prevCorpUrl",
					uri(prevCorp, params).toString());
		}

		LPStoreCorporation nextCorp = lpStoreCorporationService.nextCorp(corp);
		if (nextCorp != null) {
			model.addAttribute("nextCorpName", nextCorp.nameOrId());
			model.addAttribute("nextCorpUrl",
					uri(nextCorp, params).toString());
		}

		List<CorporationOffer> offers = corporationOfferService.byCorporationIdRequiringLp(corporationId);

		model.addAttribute("offers",
				offerValueService.value(offers, params.getLp(), params.getMaterialSourcing(), params.getProductValuator(),
						params.getBrpct(),
						params.getTaxpct(), params.getMarginhour(), params.getMargin(), params.getBpcost(), params.getLocation())
						.stream()
						.sorted(Comparator.comparing(LPOfferEval::getIskplp).reversed())
						.map(this::linkedLPOfferEval)
						.toList());

		model.addAttribute("params", params);

		return "npc/corporation";
	}

	public URI uri(LPStoreCorporation lpc) {
		return uri(lpc, new EvalParams());
	}

	public URI uri(LPStoreCorporation lpc, EvalParams params) {
		return MvcUriComponentsBuilder
				.fromMethodName(getClass(), "getCorporationOffers", null, lpc.getCorporationId(), params).build()
				.toUri();
	}

	public static record LinkedCorporationInfo(String url, String name, int nbOffers) {

	}

	public LinkedCorporationInfo linkedCorporationInfo(LPStoreCorporation corp) {
		return new LinkedCorporationInfo(uri(corp).toString(), corp.nameOrId(), corp.getOffers().size());
	}

	@GetMapping("/corporations")
	public String corporationsIndex(Model model) {
		model.addAttribute("corporations",
				lpStoreCorporationService.listActive(true).stream()
						.map(this::linkedCorporationInfo)
						.filter(ci -> ci.nbOffers() > 0)
						.sorted(Comparator.comparing(LinkedCorporationInfo::name))
						.toList());
		return "npc/corporations";
	}


}

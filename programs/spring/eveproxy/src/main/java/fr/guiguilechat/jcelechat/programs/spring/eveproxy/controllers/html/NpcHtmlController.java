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

import fr.guiguilechat.jcelechat.libs.spring.anon.lp.corp.LPCorporation;
import fr.guiguilechat.jcelechat.libs.spring.anon.lp.corp.LPCorporationService;
import fr.guiguilechat.jcelechat.libs.spring.anon.lp.eval.LPEvalParams;
import fr.guiguilechat.jcelechat.libs.spring.anon.lp.eval.LPOfferEval;
import fr.guiguilechat.jcelechat.libs.spring.anon.lp.eval.LPOfferEvalService;
import fr.guiguilechat.jcelechat.libs.spring.anon.lp.offer.LinkCorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.anon.lp.offer.LinkCorporationOfferService;
import fr.guiguilechat.jcelechat.libs.spring.anon.lp.offer.Offer;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.NpcCorporation;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.NpcCorporationService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.InventoryHtmlController.LinkedMaterial;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.InventoryHtmlController.LinkedType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/html/npc")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class NpcHtmlController {

	private final NpcCorporationService npcCorporationService;

	private final LinkCorporationOfferService linkCorporationOfferService;

	private final LPCorporationService lpCorporationService;

	private final LPOfferEvalService lpOfferEvalService;

	@Lazy
	private final InventoryHtmlController dogmaHtmlController;

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
		model.addAttribute("corporation",
				linkedObservedCorporation(link.getLpCorp(), link.getLpCorp().getNbOffers()));
		model.addAttribute("product", dogmaHtmlController.linkedType(offer.getType()));
		return "npc/offer";
	}

	public URI uri(Offer offer, int corporationId) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getOffer", null,
				corporationId, offer.getId()).build()
				.toUri();
	}

	public static record LinkedLPOffer(String url, LinkCorporationOffer offer) {
		public String name() {
			Offer off = offer.getOffer();
			return "[" +
					offer.getLpCorp().getCorporation().nameOrId() + "] " + off.getType().name()
					+ (off.getQuantity() > 1 ? "×" + off.getQuantity() : "");
		}
	}

	public LinkedLPOffer linkedLPOffer(LinkCorporationOffer offer) {
		return new LinkedLPOffer(uri(offer.getOffer(), offer.getLpCorp().getId()).toString(), offer);
	}

	public static record LinkedLPOfferEval(String url, LPOfferEval eval, LinkedType finalProduct,
			List<LinkedMaterial> materials) {

		public String name() {
			return (eval.getOffer().getQuantity() > 1 ? eval.getOffer().getQuantity() + "×" : "")
					+ eval.getProduct().name();
		}
	}

	public LinkedLPOfferEval linkedLPOfferEval(LPOfferEval eval, int corporationId) {
		return new LinkedLPOfferEval(
				uri(eval.getOffer(), corporationId).toString(),
				eval,
				dogmaHtmlController.linkedType(eval.getFinalProduct()),
				eval.getMaterialsByTypeId().entrySet().stream()
						.map(e -> dogmaHtmlController.linkedMaterial(e.getKey(), e.getValue()))
						.sorted(Comparator.comparing(lm -> lm.type().name()))
						.toList());
	}

	@Transactional
	@GetMapping("/corporation/{corporationId}")
	public String getCorporationOffers(Model model, @PathVariable int corporationId,
			LPEvalParams params) {

		NpcCorporation corp = npcCorporationService.ofId(corporationId);
		model.addAttribute("corpName", corp == null ? "corporation:" + corporationId : corp.nameOrId());

		LPCorporation prevCorp = lpCorporationService.prevCorp(corp.nameOrId());
		if (prevCorp != null) {
			model.addAttribute("prevCorpName", prevCorp.getCorporation().nameOrId());
			model.addAttribute("prevCorpUrl",
					uri(prevCorp, params).toString());
		}

		LPCorporation nextCorp = lpCorporationService.nextCorp(corp.nameOrId());
		if (nextCorp != null) {
			model.addAttribute("nextCorpName", nextCorp.getCorporation().nameOrId());
			model.addAttribute("nextCorpUrl",
					uri(nextCorp, params).toString());
		}

		List<Offer> offers = linkCorporationOfferService.byCorporationIdRequiringLp(
				corporationId,
				params.getLp());
		List<LinkedLPOfferEval> linked = lpOfferEvalService
				.value(offers, params)
				.stream()
				.sorted(Comparator.comparing(LPOfferEval::getIskplp).reversed())
				.map(lpo -> linkedLPOfferEval(lpo, corporationId))
				.toList();
		log.trace("having {} offers into {} linked", offers.size(), linked.size());

		model.addAttribute("offers", linked);

		model.addAttribute("params", params);

		return "npc/corporation";
	}

	public URI uri(LPCorporation corporation, LPEvalParams params) {
		return MvcUriComponentsBuilder
				.fromMethodName(getClass(), "getCorporationOffers", null, corporation.getId(), params).build()
				.toUri();
	}

	public URI uri(LPCorporation corporation) {
		return uri(corporation, new LPEvalParams());
	}

	public static record LinkedObservedCorporation(String url, String name, int nbOffers) {

	}

	public LinkedObservedCorporation linkedObservedCorporation(LPCorporation corp, int nbLpOffers) {
		return new LinkedObservedCorporation(uri(corp).toString(), corp.getCorporation().nameOrId(), nbLpOffers);
	}

	@Transactional
	@GetMapping("/corporations")
	public String corporationsIndex(Model model) {
		long startMs = System.currentTimeMillis();
		List<LinkedObservedCorporation> npcCorporations = linkCorporationOfferService
				.listCorporationsWithLPOffers().stream()
				.map(lpc -> linkedObservedCorporation(lpc.corporation(), lpc.nbLPOffers()))
				.sorted(Comparator.comparing(LinkedObservedCorporation::name))
				.toList();
		long postFetch = System.currentTimeMillis();
		log.trace("fetched {} observed LP corporations in {}s", npcCorporations.size(), (postFetch - startMs) / 1000);
		model.addAttribute("corporations", npcCorporations);
		return "npc/corporations";
	}

}

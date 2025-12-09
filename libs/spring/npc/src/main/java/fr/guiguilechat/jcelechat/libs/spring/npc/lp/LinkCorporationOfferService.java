package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Getter
@Setter
public class LinkCorporationOfferService {

	@Accessors(fluent = true)
	final private LinkCorporationOfferRepository repo;

	public LinkCorporationOffer save(LinkCorporationOffer entity) {
		return repo().saveAndFlush(entity);
	}

	public List<LinkCorporationOffer> saveAll(Iterable<LinkCorporationOffer> data) {
		return repo().saveAllAndFlush(data);
	}

	public void clearForObserved(Collection<LPCorporation> observed) {
		repo().deleteByCorporationId(observed.stream().map(LPCorporation::getId).distinct().toList());
	}

	public List<LinkCorporationOffer> forCorporationOffer(int corporationId, int offerId) {
		return repo().findAllByLpCorpIdAndOfferId(corporationId, offerId);
	}

	/**
	 * return all the offers of all corporation producting given type
	 *
	 * @param t
	 * @return
	 */
	public List<LinkCorporationOffer> producing(Type t) {
		return repo().findAllByOfferTypeOrderByLpCorpCorporationNameAscOfferIdAsc(t);
	}

	public List<Offer> byCorporationIdRequiringLp(int corporationId, int maxOfferLPs) {
		return repo().findAllByLpCorpIdAndOfferLpCostGreaterThanAndOfferLpCostLessThanEqualOrderByOfferIdAsc(
				corporationId,
				0, maxOfferLPs)
				.stream().map(LinkCorporationOffer::getOffer)
				.toList();
	}

	public static record CorporationLPOffers(LPCorporation corporation, int nbLPOffers) {

	}

	public List<CorporationLPOffers> listCorporationsWithLPOffers() {
		return repo().listCorporationsWithLPOffers().stream()
		    .map(arr -> new CorporationLPOffers(
		        (LPCorporation) arr[0],
		        ((Number) arr[1]).intValue()))
		    .toList();
	}

	/**
	 * @param productId the item we want to produce
	 * @return the list of corporationLink that produce given item, or its
	 *         blueprint, and require LP to do so
	 */
	public List<LinkCorporationOffer> listProducingWithLP(int productId){
		return repo.listProducingWithLP(productId);
	}

	/**
	 * @param corpIds accepted corporation ids
	 * @return all the lp links where the corporation is accepted and the offer
	 *         requires LP
	 */
	public List<LinkCorporationOffer> listCorpOffersWithLP(Iterable<Integer> corpIds) {
		return repo.listCorpOffersWithLP(corpIds);
	}

}

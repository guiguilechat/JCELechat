package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
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

	public void clearForObserved(Collection<ObservedCorporation> observed) {
		repo().deleteByObservedId(observed.stream().map(ObservedCorporation::getId).distinct().toList());
	}

	public List<LinkCorporationOffer> forCorporationOffer(int corporationId, int offerId) {
		return repo().findAllByCorporationIdAndOfferId(corporationId, offerId);
	}

	/**
	 * return all the offers of all corporation producting given type
	 * 
	 * @param t
	 * @return
	 */
	public List<LinkCorporationOffer> producing(Type t) {
		return repo().findAllByOfferTypeOrderByCorporationNameAscOfferIdAsc(t);
	}

	public List<LinkCorporationOffer> byObservedIdRequiringLp(int observedId, int lpQuantity) {
		return repo().findAllByObservedIdAndOfferLpCostGreaterThanAndOfferLpCostLessThanEqualOrderByOfferIdAsc(observedId,
		    0, lpQuantity);
	}

	public static record CorporationLPOffers(ObservedCorporation corporation, int nbLPOffers) {

	}

	public List<CorporationLPOffers> listCorporationsWithLPOffers() {
		return repo().listCorporationsWithLPOffers().stream()
		    .map(arr -> new CorporationLPOffers(
		        (ObservedCorporation) arr[0],
		        ((Number) arr[1]).intValue()))
		    .toList();
	}

}

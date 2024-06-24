package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfoService;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.ARemoteResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.npc.lpcorporation")
// depend on corporations
@Order(4)
public class ObservedCorporationService extends
    ARemoteResourceService<ObservedCorporation, Integer, List<R_get_loyalty_stores_corporation_id_offers>, ObservedCorporationRepository> {

	@Lazy
	private final CorporationInfoService corporationInfoService;

	@Lazy
	private final LinkCorporationOfferService linkCorporationOfferService;

	@Lazy
	private final OfferService offerService;

	@Override
	protected Requested<List<R_get_loyalty_stores_corporation_id_offers>> fetchData(Integer id,
	    Map<String, String> properties) {
		return ESIRawPublic.INSTANCE
		    .get_loyalty_stores_offers(id, properties).mapBody(List::of);
	}

	@Override
	protected ObservedCorporation create(Integer entityId) {
		ObservedCorporation ret = new ObservedCorporation();
		ret.setId(entityId);
		ret.setCorporationInfo(corporationInfoService.createIfAbsent(entityId));
		return ret;
	}

	@Override
	protected void updateResponseOk(
	    Map<ObservedCorporation, List<R_get_loyalty_stores_corporation_id_offers>> map) {
		super.updateResponseOk(map);
		// first check all the offers are unique by id
		Map<Integer, List<R_get_loyalty_stores_corporation_id_offers>> offersById = map.values().stream()
		    .flatMap(List::stream)
		    .collect(Collectors.groupingBy(o -> o.offer_id));
		Map<Integer, R_get_loyalty_stores_corporation_id_offers> offerById = new HashMap<>();
		for (Entry<Integer, List<R_get_loyalty_stores_corporation_id_offers>> e : offersById.entrySet()) {
			List<R_get_loyalty_stores_corporation_id_offers> l = e.getValue();
			if (l.size() > 1) {
				R_get_loyalty_stores_corporation_id_offers first = l.get(0);
				List<R_get_loyalty_stores_corporation_id_offers> different = l.stream().filter(o -> different(first, o))
				    .toList();
				if (!different.isEmpty()) {
					throw new RuntimeException(
					    "offer id " + e.getKey() + " has " + different.size() + " different from the first out of " + l.size());
				}
				offerById.put(e.getKey(), first);
			}
		}
		// then update the offers and retrieve the map
		Map<Integer, Offer> idToOffer = offerService.updateOffers(offerById);
		
		// then link the corporation observed to those offers
		linkCorporationOfferService.clearForObserved(map.keySet());
		Map<Integer, CorporationInfo> idtoCorporationInfo = corporationInfoService.createIfAbsent(map.keySet().stream().map(ObservedCorporation::getId).toList());
		List<LinkCorporationOffer> links = new ArrayList<>();
		for ( Entry<ObservedCorporation, List<R_get_loyalty_stores_corporation_id_offers>> e : map.entrySet()) {
			ObservedCorporation oc = e.getKey();
			CorporationInfo ci = idtoCorporationInfo.get(oc.getId());
			for (R_get_loyalty_stores_corporation_id_offers o : e.getValue()) {
				links.add(LinkCorporationOffer.builder()
				    .corporation(ci)
				    .observed(oc)
				    .offer(idToOffer.get(o.offer_id))
				    .build());
			}
		}
		linkCorporationOfferService.saveAll(links);
		
	}

	protected boolean different(R_get_loyalty_stores_corporation_id_offers o1,
	    R_get_loyalty_stores_corporation_id_offers o2) {
		if (o1 == null == (o2 != null)) {
			return true;
		}
		if (o1 == o2) {
			return false;
		}
		if (o1.ak_cost != o2.ak_cost) {
			return true;
		}
		if (o1.isk_cost != o2.isk_cost) {
			return true;
		}
		if (o1.lp_cost != o2.lp_cost) {
			return true;
		}
		if (o1.quantity != o2.quantity) {
			return true;
		}
		if (o1.type_id != o2.type_id) {
			return true;
		}

		if (o1.required_items == null == (o2.required_items != null)) {
			return true;
		}
		if (o1.required_items == o2.required_items) {
			return false;
		}
		Map<Integer, Integer> m1 = Stream.of(o1.required_items).collect(Collectors.toMap(r -> r.type_id, r -> r.quantity));
		Map<Integer, Integer> m2 = Stream.of(o2.required_items).collect(Collectors.toMap(r -> r.type_id, r -> r.quantity));
		if (!m1.equals(m2)) {
			return true;
		}
		return false;
	}

	// external usage

	public List<ObservedCorporation> allWithOffers() {
		return repo().findByNbOffersGreaterThan0OrderByNameAsc();
	}

	@Override
	public Map<Integer, ObservedCorporation> allById() {
		return repo().findAll().stream()
		    .collect(Collectors.toMap(ObservedCorporation::getId, oc -> oc));
	}

	public ObservedCorporation prevCorp(String name) {
		return repo().findTop1ByCorporationNameLessThanOrderByCorporationNameDesc(name);
	}

	public ObservedCorporation nextCorp(String name) {
		return repo().findTop1ByCorporationNameGreaterThanOrderByCorporationNameAsc(name);
	}

}

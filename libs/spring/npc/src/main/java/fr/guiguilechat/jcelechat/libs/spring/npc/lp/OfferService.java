package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers_required_items;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Getter
@Setter
public class OfferService {

	@Accessors(fluent = true)
	final private OfferRepository repo;

	@Lazy
	private final RequirementService requirementService;
	@Lazy
	private final TypeService typeService;

	public Offer save(Offer entity) {
		return repo().saveAndFlush(entity);
	}

	public List<Offer> saveAll(Iterable<Offer> data) {
		return repo().saveAllAndFlush(data);
	}

	public Map<Integer, Offer> updateOffers(Map<Integer, R_get_loyalty_stores_corporation_id_offers> offerById) {
		Map<Integer, Type> idToType = typeService.createIfAbsent(Stream.concat(
		    offerById.values().stream().map(o -> o.type_id),
		    offerById.values().stream().flatMap(
		        o -> o.required_items == null ? Stream.empty() : Stream.of(o.required_items).map(ri -> ri.type_id)))
		    .distinct().filter(i -> i > 0).toList());

		// first pass : create all the missing offers and update them.
		Map<Integer, Offer> ret = new HashMap<>();
		Map<Integer, Offer> existingById = repo.findAll().stream().collect(Collectors.toMap(Offer::getId, o -> o));
		for (R_get_loyalty_stores_corporation_id_offers co : offerById.values()) {
			int id = co.offer_id;
			Offer offer = existingById.get(id);
			if (offer == null) {
				offer = new Offer();
				offer.setId(id);
			}
			offer.update(co);
			offer.setType(idToType.get(co.type_id));
			ret.put(id, offer);
		}
		ret = repo.saveAll(ret.values()).stream().collect(Collectors.toMap(Offer::getId, o -> o));

		// second pass :create the requirements
		requirementService.deleteForOfferIds(offerById.keySet());
		List<Requirement> requirements = new ArrayList<>();
		for (R_get_loyalty_stores_corporation_id_offers co : offerById.values()) {
			if (co.required_items != null) {
				Offer offer = ret.get(co.offer_id);
				for( R_get_loyalty_stores_corporation_id_offers_required_items ri : co.required_items) {
					requirements.add(Requirement.of(ri, offer, idToType.get(ri.type_id)));
				}
			}
		}
		requirementService.saveAll(requirements);

		return ret;
	}

}

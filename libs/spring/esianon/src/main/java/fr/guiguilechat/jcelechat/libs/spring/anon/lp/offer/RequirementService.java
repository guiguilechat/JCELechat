package fr.guiguilechat.jcelechat.libs.spring.anon.lp.offer;

import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Getter
@Setter
public class RequirementService {

	@Accessors(fluent = true)
	final private RequirementRepository repo;

	public Requirement save(Requirement entity) {
		return repo().saveAndFlush(entity);
	}

	public List<Requirement> saveAll(Iterable<Requirement> data) {
		return repo().saveAllAndFlush(data);
	}

	public void deleteForOfferIds(Collection<Integer> offerIds) {
		repo.deleteByOfferId(offerIds);
	}

	public void deleteForOffers(Collection<Offer> offers) {
		deleteForOfferIds(offers.stream().map(Offer::getId).toList());
	}

}

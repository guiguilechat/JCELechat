package fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.industry.model.IndustryFetchResult;

public interface IndustryFetchResultRepository extends JpaRepository<IndustryFetchResult, Long> {

	public IndustryFetchResult findFirstByStatusInOrderByCreatedDateDesc(
			Iterable<IndustryFetchResult.STATUS> allowedStatus);

	public IndustryFetchResult findFirstByStatusOrderByCreatedDateDesc(
			IndustryFetchResult.STATUS status);

}

package fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.industry;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.industry.IndustryFetchResult;

public interface IndustryFetchResultRepository extends JpaRepository<IndustryFetchResult, Long> {

	public IndustryFetchResult findFirstByStatusInOrderByCreatedDateDesc(
			Iterable<IndustryFetchResult.STATUS> allowedStatus);

	public IndustryFetchResult findFirstByStatusOrderByCreatedDateDesc(
			IndustryFetchResult.STATUS status);

}

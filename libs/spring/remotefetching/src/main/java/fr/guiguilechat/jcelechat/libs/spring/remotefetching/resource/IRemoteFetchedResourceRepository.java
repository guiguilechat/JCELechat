package fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRemoteFetchedResourceRepository<Entity extends ARemoteFetchedResource<Id, ?>, Id>
    extends JpaRepository<Entity, Id> {

	public List<Entity> findByFetchActiveTrueAndExpiresLessThanOrderByExpiresAsc(Instant now, Limit limit);

	public int countByFetchActiveTrueAndExpiresLessThan(Instant now);

}

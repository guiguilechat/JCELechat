package fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.basic.IFetchedResourceRepository;

@NoRepositoryBean
public interface IRemoteResourceRepository<Entity extends ARemoteResource<Id, ?>, Id>
    extends IFetchedResourceRepository<Entity, Id> {

	public List<Entity> findByFetchActiveTrueAndExpiresLessThanOrderByExpiresAsc(Instant now, Limit limit);

	public long countByFetchActiveTrueAndExpiresLessThan(Instant now);

}

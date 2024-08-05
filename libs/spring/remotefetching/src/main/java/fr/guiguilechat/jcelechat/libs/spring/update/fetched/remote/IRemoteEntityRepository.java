package fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.IFetchedResourceRepository;

@NoRepositoryBean
public interface IRemoteEntityRepository<Entity extends ARemoteEntity<Id, ?>, Id extends Number>
    extends IFetchedResourceRepository<Entity, Id> {

	public List<Entity> findByFetchActiveTrueAndExpiresBeforeOrderByExpiresAsc(Instant now, Limit limit);
	
	public List<Entity> findByExpiresBeforeOrderByExpiresAsc(Instant now, Limit limit);

	public List<Entity> findByFetchActiveTrueOrderByExpiresAsc(Limit limit);

	public long countByFetchActiveTrueAndExpiresBefore(Instant now);

}

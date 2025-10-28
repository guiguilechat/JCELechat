package fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.FetchedEntityRepository;

@NoRepositoryBean
public interface RemoteEntityRepository<Entity extends RemoteEntity<Id, ?>, Id extends Number>
    extends FetchedEntityRepository<Entity, Id> {

	public List<Entity> findByFetchActiveTrueAndExpiresBeforeOrderByExpiresAsc(Instant now, Limit limit);
	
	public long countByFetchActiveTrueAndExpiresBefore(Instant now);

}

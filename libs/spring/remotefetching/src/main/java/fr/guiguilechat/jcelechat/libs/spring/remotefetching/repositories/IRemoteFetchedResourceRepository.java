package fr.guiguilechat.jcelechat.libs.spring.remotefetching.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.model.ARemoteFetchedResource;

@NoRepositoryBean
public interface IRemoteFetchedResourceRepository<Entity extends ARemoteFetchedResource<Id, ?>, Id>
    extends JpaRepository<Entity, Id> {

	public List<Entity> findTop1000ByFetchActiveTrueAndExpiresLessThan(Instant now);

}

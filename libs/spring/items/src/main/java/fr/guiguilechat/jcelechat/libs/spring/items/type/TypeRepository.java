package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.time.Instant;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.IRemoteFetchedResourceRepository;

public interface TypeRepository extends IRemoteFetchedResourceRepository<Type, Integer> {

	public List<Type> findTop10000ByFetchActiveTrueAndExpiresLessThan(Instant now);

}

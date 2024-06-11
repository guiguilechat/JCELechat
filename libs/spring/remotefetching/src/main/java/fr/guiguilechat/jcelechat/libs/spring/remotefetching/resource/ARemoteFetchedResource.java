package fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * representation of a fetched resource that is linked to a remote entity. The
 * actual implementation + {@link #getRemoteId()} should identify the remote
 * resource completely.
 * 
 * @param <RemoteId> type of remote id used, typically int or long.
 * @param <Fetched>  {@link AFetchedResource}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@MappedSuperclass
@Setter
public abstract class ARemoteFetchedResource<RemoteId, Fetched> extends AFetchedResource {

	@Id
	private RemoteId id;

	/**
	 * update that entity's scalar values from a newly fetched remote data.
	 * non-scalar values (eg reference to other entities) must be updated in the
	 * service's
	 * {@link ARemoteFetchedResourceService#updateResponseOk(ARemoteFetchedResource, fr.guiguilechat.jcelechat.jcesi.interfaces.Requested)
	 * updateResponseOk}
	 */
	public abstract void update(Fetched data);

}
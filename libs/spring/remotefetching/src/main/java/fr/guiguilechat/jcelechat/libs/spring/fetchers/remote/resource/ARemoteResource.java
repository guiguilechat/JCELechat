package fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource;

import java.time.Instant;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.basic.AFetchedResource;
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
public abstract class ARemoteResource<RemoteId, Fetched> extends AFetchedResource<RemoteId> {

	/** date the last successful update expires at */
	private Instant expires;

	/** true if we need to keep it up to date */
	private boolean fetchActive = true;

	/** returned etag value of the last successful update */
	private String lastEtag;

	/** date the last successful update had its remote value changed */
	private Instant lastModified;

	public void setExpiresIn(int seconds) {
		setExpires(Instant.now().plusSeconds(seconds));
	}

	public void setExpiresInRandom(int maxSeconds) {
		setExpiresInRandom(0, maxSeconds);
	}

	public void setExpiresInRandom(int minSeconds, int addedRandom) {
		setExpiresIn(minSeconds + (int) (Math.random() * addedRandom));
	}

	/** called when the resource is successfully updated */
	public void updateMetaOk(Instant lastModified, Instant expires, String etag) {
		super.updateMetaOk();
		setExpires(expires);
		setLastEtag(etag);
		setLastModified(lastModified);
	}

	/**
	 * update that entity's scalar values from a newly fetched remote data.
	 * non-scalar values (eg reference to other entities) must be updated in the
	 * service's
	 * {@link ARemoteResourceService#updateResponseOk(ARemoteFetchedResource, fr.guiguilechat.jcelechat.jcesi.interfaces.Requested)
	 * updateResponseOk}
	 */
	public abstract void update(Fetched data);

}
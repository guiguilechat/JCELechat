package fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote;

import java.time.Instant;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.AFetchedResource;
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
public abstract class ARemoteEntity<RemoteId extends Number, Fetched> extends AFetchedResource<RemoteId> {

	/** date the last successful update expires at */
	private Instant expires;

	/** true if we need to keep it up to date */
	private boolean fetchActive = true;

	/** returned etag value of the last successful update */
	private String lastEtag;

	/** date the last successful update had its remote value changed */
	private Instant lastModified;

	/** set to true once a 404 is returned */
	private boolean removed;

	public void setExpiresIn(int seconds) {
		setExpires(Instant.now().plusSeconds(seconds));
	}

	public void setExpiresInRandom(int maxSeconds) {
		setExpiresInRandom(0, maxSeconds);
	}

	/**
	 * set {@link #expires} to a random valuye between min and min+added
	 * 
	 * @param minSeconds  minimum seconds. Can be <0 to set in the past
	 * @param addedRandom added maximum seconds. Can be <0 to remove seconds from
	 *                      min
	 */
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
	 * {@link ARemoteEntityService#updateResponseOk(ARemoteFetchedResource, fr.guiguilechat.jcelechat.jcesi.interfaces.Requested)
	 * updateResponseOk}
	 */
	public abstract void update(Fetched data);

}
package fr.guiguilechat.jcelechat.libs.spring.update.entities.remote;

import java.time.Instant;

import org.hibernate.annotations.ColumnDefault;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.DeducedEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * managed resource that is linked to a remote entity, and therefore whose
 * informations are fetched from. The actual class +
 * {@link #getId()} should identify the remote resource completely.
 *
 * @param <IdType>  type of id used, typically int or long.
 * @param <Fetched> structure returned from remote server to describe this
 *                  entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@MappedSuperclass
@Setter
public abstract class RemoteEntity<IdType extends Number, Fetched> extends DeducedEntity<IdType> {

	/** date the last successful update expires at */
	private Instant expires;

	/// Priority for the next fetch. Higher is more priority
	///  - default value for newly created entities is 1
	///  - already fetched entities is 0
	///  - request-error response entities (4xx but 420) is -1
	///  - high-priority entities should be 100+
	///  - deduced priority for new values should be ]0..100[
	@ColumnDefault("1")
	private int fetchPriority = 1;

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
	 * set {@link #expires} to a random value between min and min+added
	 *
	 * @param minSeconds  minimum seconds. Can be <0 to set in the past
	 * @param addedRandom added maximum seconds. Can be <0 to remove seconds from
	 *                    min
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
		setFetchPriority(0);
	}

	/**
	 * update that entity's scalar values from a newly fetched remote data.
	 * non-scalar values (eg reference to other entities) must be updated in the
	 * service's
	 * {@link RemoteEntityService#updateResponseOk(ARemoteFetchedResource, fr.guiguilechat.jcelechat.jcesi.interfaces.Requested)
	 * updateResponseOk}
	 */
	public abstract void update(Fetched data);

}
package fr.guiguilechat.jcelechat.libs.spring.templates.model;

import java.time.Instant;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * abstract class that represent a local representation of a fetched
 * information
 * 
 * @param <Fetched> the fetched class, used to update this class' fields
 */
@AllArgsConstructor
@Getter
@MappedSuperclass
@NoArgsConstructor
@Setter
public abstract class AFetchedResource {

	/** date it was created */
	private Instant created;

	/** date the last successful update expires at */
	private Instant expires;

	/** true if we need to keep it up to date */
	private boolean fetchActive = true;

	/** true when the resource has already been successfuly fetched */
	private boolean fetched = false;

	/** returned etag value of the last successful update */
	private String lastEtag;

	/** date the last successful update had its remote value changed */
	private Instant lastModified;

	/** date of the last successful update */
	private Instant lastUpdate;

	/** number of failures we had since the last success or creation date */
	private int successiveErrors = 0;

	protected void resetErrors() {
		setSuccessiveErrors(0);
	}

	public int increaseSuccessiveErrors() {
		return ++successiveErrors;
	}

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
		setExpires(expires);
		setFetched(true);
		setLastEtag(etag);
		setLastModified(lastModified);
		resetErrors();
	}

}

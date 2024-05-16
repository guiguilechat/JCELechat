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

	private Instant created, lastUpdate, expires, lastModified;

	private String lastEtag;

	private boolean fetched = false;

	private boolean fetchActive = true;

	private int successiveErrors = 0;

	protected void resetErrors() {
		setSuccessiveErrors(0);
	}

	public int increaseSuccessiveErrors() {
		return ++successiveErrors;
	}

	/** called when the resource is successfully updated */
	protected void updateMetaOk(Instant lastModified, Instant expires, String etag) {
		setExpires(expires);
		setFetched(true);
		setLastEtag(etag);
		setLastModified(lastModified);
		resetErrors();
	}

}

package fr.guiguilechat.jcelechat.libs.spring.fetchers.basic;

import java.time.Instant;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * abstract class that represent a local representation of a fetched
 * information
 */
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AFetchedResource<IdType extends Number> {

	@Id
	private IdType id;

	/** date it was created, managed by service */
	private Instant created;

	/** true when the resource has already been successfully fetched */
	private boolean fetched = false;

	/** date of the last save, managed by service */
	private Instant lastUpdate;

	/** number of failures we had since the last success or creation date */
	private int successiveErrors = 0;

	protected void resetErrors() {
		setSuccessiveErrors(0);
	}

	public int increaseSuccessiveErrors() {
		return ++successiveErrors;
	}

	/** called when the resource is successfully updated */
	public void updateMetaOk() {
		setFetched(true);
		resetErrors();
	}

}

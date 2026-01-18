package fr.guiguilechat.jcelechat.libs.spring.update.entities;

import java.time.Instant;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * abstract class that represent a local deduced information.
 * The information can be an retrieved as an aggregation, meaning there is
 * no 1-1 link between the deduced entity and the information source.
 */
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class LocalEntity<IdType extends Number> {

	@Id
	private IdType id;

	/** date it was created, managed by service */
	@CreationTimestamp
	@ColumnDefault("current_timestamp")
	private Instant created;

	/** true when the resource has already been successfully fetched */
	@ColumnDefault("false")
	private boolean fetched = false;

	/** date of the last save, managed by service */
	@UpdateTimestamp
	@ColumnDefault("null")
	private Instant lastUpdate;

	/** number of failures we had since the last success or creation date */
	@ColumnDefault("0")
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

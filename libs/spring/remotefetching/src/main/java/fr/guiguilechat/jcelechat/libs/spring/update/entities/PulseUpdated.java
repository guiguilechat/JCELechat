package fr.guiguilechat.jcelechat.libs.spring.update.entities;

import java.time.Instant;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * entity that is updated by scheduled update pulses.
 */
@Getter
@Setter
@MappedSuperclass
public class PulseUpdated<Id> {

	//
	// auto managed variables
	//

	/**
	 * date this entity was saved in the db, auto managed by service or DBMS to
	 * handle mass insert
	 */
	@CreationTimestamp
	@ColumnDefault("current_timestamp")
	private Instant dateCreated;

	/** date of the last save, auto managed by service or DBMS */
	@UpdateTimestamp
	@ColumnDefault("null")
	private Instant dateUpdated;

	//
	// update process
	//

	/** true if we need to keep it up to date. When this is set to false, this entity is no more selected for updates */
	@ColumnDefault("true")
	private boolean updateActive = true;

	/**
	 * true when the resource has already been successfully fetched at least once
	 */
	@ColumnDefault("false")
	private boolean updatedOnce = false;

	/** number of failures we had since the last success or creation date */
	@ColumnDefault("0")
	private int updateNbErrors = 0;

	/** after when we should update */
	@ColumnDefault("current_timestamp")
	private Instant updateNext;

	@ColumnDefault("1")
	private int updatePriority = 1;

	/**
	 * set to true once the entity has been detected as removed : 404 or not present
	 * in a listing process
	 */
	@ColumnDefault("false")
	private boolean updateRemoved = false;

	//

	public int increaseSuccessiveErrors() {
		return ++updateNbErrors;
	}

	public void onUpdateSuccess(Instant nextUpdate) {
		updatedOnce = true;
		updateNbErrors = 0;
		updateNext = nextUpdate;
		updatePriority = 0;
		updateRemoved = false;
	}

	public void onUpdateError(Instant nextUpdate) {
		if (nextUpdate == null) {
			updateActive = false;
		}
		updateNbErrors++;
		updateNext = nextUpdate;
		updatePriority = -1;
	}

}

package fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic;

import java.time.Instant;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * an entity deduced from SDE files record
 *
 * @param <IdType>
 * @param <Source>
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class SdeEntity<IdType extends Number> {

	@Id
	private IdType id;
	@ColumnDefault("false")
	private boolean received = false;
	@ColumnDefault("false")
	private boolean removed = false;
	@Setter(value = AccessLevel.PRIVATE)
	@CreationTimestamp
	@ColumnDefault("current_timestamp")
	private Instant created;
	@UpdateTimestamp
	private Instant updated;

	public void receivedSource() {
		received = true;
		removed = false;
	}


}

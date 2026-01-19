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
 * an entity matching a SDE file's record. <br />
 * That entity can be created when referenced in another file, and therefore is
 * created with {@link #received} and {@link #removed} false.<br />
 * When receiving the corresponding SDE file, all entities are set to
 * {@link #removed} true, then those processed in the new file are set to
 * {@link #received} true and {@link #removed} false using
 * {@link #receivedSource()}
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

	@Setter(value = AccessLevel.NONE)
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

package fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * an entity deduced from SDE files record
 *
 * @param <IdType>
 * @param <Source>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class SdeEntity<IdType extends Number> {

	@Id
	private IdType id;
	private boolean received = false;
	private boolean removed = false;
	@CreationTimestamp
	private Instant created;
	@UpdateTimestamp
	private Instant updated;

	public void receivedSource() {
		received = true;
		removed = false;
	}


}

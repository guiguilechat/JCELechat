package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * status of a SDE fetch
 */
@Entity(name = "SdeResult")
@Table(name = "sde_updater_result")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SdeResult {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@CreationTimestamp
	private Instant createdDate;

	private Instant startedDate;

	private Long fetchedDurationMs;

	private Long processDurationMs;

	@Lob
	private String error;

	/**
	 * release date retrieved from the SDE meta data
	 */
	private String releaseDate;

	/**
	 * build number retrieved from the SDE meta data
	 */
	private long buildNumber;

	public enum Status {
		FAIL,
		CACHED,
		SUCCESS
	}

	@Builder.Default
	@Enumerated(EnumType.STRING)
	private Status status = Status.CACHED;

}

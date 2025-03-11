package fr.guiguilechat.jcelechat.libs.spring.gameclient.updater;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

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

@Entity(name = "GameClientUpdaterResult")
@Table(name = "gameclient_updater_updateresult")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class GameClientUpdate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * first time this is saved to DB. Can be after the processing
	 */
	@CreatedDate
	private Instant createdDate;

	/**
	 * time the corresponding update was started
	 */
	private Instant startedDate;

	/**
	 * time the corresponding update ended
	 */
	private Instant endDate;

	@Lob
	private String error;

	/**
	 * build number retrieved from the client info
	 */
	private String buildNumber;

	public enum Status {
		FAIL,
		CACHED,
		SUCCESS
	}

	@Builder.Default
	@Enumerated(EnumType.STRING)
	private Status status = Status.CACHED;

}

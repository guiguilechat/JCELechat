package fr.guiguilechat.jcelechat.libs.spring.sde.updater.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UpdateResult {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@CreatedDate
	private Instant createdDate;

	private Instant started;

	private Instant fetchedDate;

	private String error;

	public static enum STATUS {
		FAIL,
		CACHED,
		SUCCESS
	}

	@Builder.Default
	@Enumerated(EnumType.STRING)
	private STATUS status = STATUS.CACHED;

}

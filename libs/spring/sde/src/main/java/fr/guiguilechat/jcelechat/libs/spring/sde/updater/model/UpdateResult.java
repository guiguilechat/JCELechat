package fr.guiguilechat.jcelechat.libs.spring.sde.updater.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "SdeUpdaterResult")
@Table(name = "sde_updater_updateresult")
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

	private Instant startedDate;

	private Long fetchedDurationMs;

	private Long processDurationMs;

	private String error;

	public static enum STATUS {
		FAIL,
		CACHED,
		SUCCESS,
		SUCCESS_NEED_REFETCH
	}

	@Builder.Default
	@Enumerated(EnumType.STRING)
	private STATUS status = STATUS.CACHED;

}

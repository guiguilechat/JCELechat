package fr.guiguilechat.jcelechat.libs.spring.update.manager.servicepulse;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "JcesiManagerUpdaterPulse")
@Table(name = "jcesi_manager_updaterpulse", indexes = {
		@Index(columnList = "start"),
		@Index(columnList = "service, start")
})
@Getter
@Setter
@NoArgsConstructor
public class UpdaterPulse {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String service;

	private Instant start;
	private Instant end;
	private long durationMs;

	public UpdaterPulse(String service, Instant start, Instant end) {
		this.service = service;
		this.start = start;
		this.end = end;
		durationMs = end.toEpochMilli() - start.toEpochMilli();
	}

}

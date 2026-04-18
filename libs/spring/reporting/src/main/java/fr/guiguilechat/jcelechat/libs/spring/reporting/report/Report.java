package fr.guiguilechat.jcelechat.libs.spring.reporting.report;

import java.time.Instant;
import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity(name = "ReportingReport")
@Table(name = "reporting_report", indexes = {
		@Index(columnList = "status, tryAfter, priority"),
})
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Report {

	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/** used to identify the report generator */
	private String reportGenerator;

	/**
	 * when present, the starting day of the report period, included
	 */
	private LocalDate periodStart;

	/**
	 * when present, the end day of the report period, excluded
	 */
	private LocalDate periodEnd;

	/**
	 * in whatever format the generator specifies it
	 */
	@Lob
	private String params;

	public enum Status {
		READY,
		DONE,
		CANCELED
	}

	@Enumerated(EnumType.ORDINAL)
	private Status status = Status.READY;

	// 0 for default, 1 for manual, -1 for errored
	@ColumnDefault("0")
	private int priority;

	private Instant tryAfter = Instant.now();

	@Lob
	private String lastError;

	private Instant lastTry;

	@ColumnDefault("0")
	private int successiveErrors = 0;

	public void addError(String message) {
		successiveErrors++;
		setLastError(message);
		setLastTry(Instant.now());
		setTryAfter(Instant.now().plusSeconds((long) (3600 * Math.sqrt(successiveErrors))));
	}

}

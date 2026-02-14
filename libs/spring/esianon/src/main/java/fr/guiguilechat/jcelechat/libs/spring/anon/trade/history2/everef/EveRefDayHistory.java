package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.everef;

import java.time.Instant;
import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * a day we want to fetch from everef history
 */
@Entity(name = "EverefTradeFetchedHistory")
@Table(name = "everef_trade_fetchedhistory", indexes = {
		@Index(columnList = "success,nextFetch"),
		@Index(columnList = "aggregationStatus,date")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EveRefDayHistory {

	public enum AggregationStatus {
		UNABLE, AVAIL, DONE, FAILED;
	}

	@Enumerated(EnumType.STRING)
	@Builder.Default
	private AggregationStatus aggregationStatus = AggregationStatus.AVAIL;

	@Id
	private LocalDate date;

	/** last time we tried to fetch */
	private Instant lastFetch;

	/** next time we try to fetch */
	private Instant nextFetch;

	@ColumnDefault("false")
	@Builder.Default
	private boolean success = false;

	@ColumnDefault("0")
	@Builder.Default
	private int storedLines = 0;

	@ColumnDefault("0")
	@Builder.Default
	private int successiveErrors = 0;

	public void increaseSuccessiveErrors() {
		setSuccessiveErrors(getSuccessiveErrors() + 1);
	}

}

package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history;

import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "EverefTradeDayHistory")
@Table(name = "everef_trade_dayhistory", indexes = {
		@Index(columnList = "success,nextFetch")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EveRefDayHistory {

	/** id format is yyyyMMdd */
	@Id
	private int id;

	public static int makeId(int year, int month, int day) {
		return 10000 * year + 100 * month + day;
	}

	public LocalDate date() {
		int year = id / 10000;
		int month = id % 10000 / 100;
		int day = id % 100;
		return LocalDate.of(year, month, day);
	}

	private boolean success;

	@Builder.Default
	private int storedLines = 0;

	@Builder.Default
	private int successiveErrors = 0;

	/** last time we tried to fetch */
	private Instant lastFetch;

	/** next time we try to fetch */
	private Instant nextFetch;

}

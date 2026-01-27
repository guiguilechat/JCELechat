package fr.guiguilechat.jcelechat.libs.everef.history;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryEntry {
	private String date;
	private int region_id;
	private int type_id;
	private BigDecimal average;
	private BigDecimal highest;
	private BigDecimal lowest;
	private long volume;
	private long order_count;

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final Instant dateInstant = LocalDate.from(DateTimeFormatter.ISO_DATE.parse(date))
			.atStartOfDay()
			.toInstant(ZoneOffset.UTC);

}

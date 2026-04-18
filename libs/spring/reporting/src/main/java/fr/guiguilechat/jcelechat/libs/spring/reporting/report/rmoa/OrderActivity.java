package fr.guiguilechat.jcelechat.libs.spring.reporting.report.rmoa;

import java.time.Instant;

import fr.guiguilechat.jcelechat.libs.spring.reporting.report.Report;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * statistical info for an order over a report's period.
 * <p>
 * "Trunc" refers to updates' truncated hour, eg "2000-01-01T00:00:05" is the
 * same truncated hour as "2000-01-01T00:59:59". The {@link #truncHours} refers
 * to the number of active updates' truncated hours in the period, while
 * {@link #truncSpread} refers to the difference between the first and the last
 * update.
 * </p>
 */
@Entity(name = "ReportingRMOAOrder")
@Table(name = "reporting_rmoa_orderactivity", indexes = {
		@Index(columnList = "status, tryAfter, priority"),
})
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderActivity {

	@ManyToOne()
	private Report report;

	private long orderId;

	private int regionId;

	private int solarSystemId;

	private long locationId;

	/**
	 * number of order updates during the report period.
	 */
	private int nbUpdates;

	/**
	 * number of distinct hours in the report period during which there was an
	 * update for that order
	 */
	private int truncHours;

	/**
	 * number of distinct hours between the first and last updates, in the report
	 * period. Should be at least one.
	 */
	private int truncSpread;

	private Instant firstActivity;

	private Instant lastActivity;

}

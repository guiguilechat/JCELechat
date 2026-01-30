package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history;

import java.time.Instant;
import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history.TypeRegionHistory.TypeRegionKey;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.Region;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * requirement to fetch histories from esi's regional history
 */
@Entity(name = "EsiTradeTypeRegionHistory")
@Table(name = "esi_trade_typeregionhistory", indexes = {
		@Index(columnList = "fetchActive,fetchExpires,fetchPriority"),
		@Index(columnList = "typeId,regionId")
})
@Getter
@Setter
@NoArgsConstructor
@IdClass(TypeRegionKey.class)
public class TypeRegionHistory {

	@Embeddable
	public static record TypeRegionKey(Region region, Type type) {
	}

	@ManyToOne
	@Id
	private Region region;

	@ManyToOne
	@Id
	private Type type;

	//
	// auto managed variables
	//

	/** date it was created, managed by service */
	@CreationTimestamp
	@ColumnDefault("current_timestamp")
	private Instant createdDate;

	/** date of the last save, managed by service */
	@UpdateTimestamp
	@ColumnDefault("null")
	private Instant updatedDate;

	//
	// fetch process
	//

	/** true if we need to keep it up to date */
	@ColumnDefault("true")
	private boolean fetchActive = true;

	/** true when the resource has already been successfully fetched */
	@ColumnDefault("false")
	private boolean fetchDone = false;

	/** number of failures we had since the last success or creation date */
	@ColumnDefault("0")
	private int fetchErrors = 0;

	/** after when we should update */
	@ColumnDefault("current_timestamp")
	private Instant fetchExpires;

	@ColumnDefault("1")
	private int fetchPriority = 1;

	/** set to true once a 404 is returned */
	@ColumnDefault("false")
	private boolean fetchRemoved = false;

	//
	// received data
	//

	/** etag value of the last successful esi fetch */
	@ColumnDefault("null")
	private String receivedEtag;

	/**
	 * first LocalDate retrieved from ESI (from ALL corresponding fetches, not last
	 * one)
	 */
	@ColumnDefault("null")
	private LocalDate receivedFirstDate;

	/** last LocalDate retrieved from ESI */
	@ColumnDefault("null")
	private LocalDate receivedLastDate;

	/** date the last successful update had its remote value changed */
	@ColumnDefault("null")
	private Instant receivedLastModified;

	//

	public int increaseSuccessiveErrors() {
		return ++fetchErrors;
	}

	public void onFetched(String lastEtag, Instant lastModified, Instant expires) {
		fetchActive = true;
		fetchDone = true;
		fetchErrors = 0;
		fetchExpires = expires;
		fetchPriority = 0;
		fetchRemoved = false;
		receivedEtag = lastEtag;
		receivedLastModified = lastModified;
	}

}

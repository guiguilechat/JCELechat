package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2;

import java.time.Instant;
import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.TypeRegionHistory.TypeRegionKey;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.PulseUpdated;
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
 * region+type for which to fetch esi's history
 */
@Entity(name = "EsiTradeTypeRegionHistory")
@Table(name = "esi_trade_typeregionhistory", indexes = {
		@Index(columnList = "updateActive,updateNext,updatePriority"),
		@Index(columnList = "typeId,regionId")
})
@Getter
@Setter
@NoArgsConstructor
@IdClass(TypeRegionKey.class)
public class TypeRegionHistory extends PulseUpdated<TypeRegionKey> {

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

	public void onFetched(String lastEtag, Instant lastModified, Instant expires) {
		super.onUpdateSuccess(expires);
		receivedEtag = lastEtag;
		receivedLastModified = lastModified;
	}

}

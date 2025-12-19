package fr.guiguilechat.jcelechat.libs.spring.trade.history;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_history;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A requirement to observe the history : a type id and a region id.
 */
@Entity(name = "EsiTradeHistoryReq")
@Table(name = "esi_trade_historyreq", indexes = {
		@Index(columnList = "fetch_active,fetch_priority,expires"),
		@Index(columnList = "regionId,typeId"),
		@Index(columnList = "typeId")
})
@Getter
@Setter
@NoArgsConstructor
public class HistoryReq extends AFetchedList<Long, R_get_markets_region_id_history, HistoryLine> {

	@ManyToOne
	private Region region;

	@ManyToOne
	private Type type;


	private static long TYPE_ID_MULTIPLIER = 100000000L;

	public static long makeId(int regionId, int typeId) {
		return TYPE_ID_MULTIPLIER * typeId + regionId;
	}

	public static int getRegionId(long reqId) {
		return (int) (reqId % TYPE_ID_MULTIPLIER);
	}

	public static int getTypeId(long reqId) {
		return (int) (reqId / TYPE_ID_MULTIPLIER);
	}

	public HistoryReq(Region region, Type type) {
		setId(makeId(region.getId(), type.getId()));
		setRegion(region);
		setType(type);
	}

}

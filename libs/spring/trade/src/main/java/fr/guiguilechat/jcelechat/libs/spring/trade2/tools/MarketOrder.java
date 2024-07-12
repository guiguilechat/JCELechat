package fr.guiguilechat.jcelechat.libs.spring.trade2.tools;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractInfo;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractItem;
import fr.guiguilechat.jcelechat.libs.spring.trade2.regional.RegionLine;
import fr.guiguilechat.tools.FormatTools;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * class for representing both market orders and contract orders
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
public class MarketOrder implements Serializable {

	private int regionId;

	public static enum OrderType {
		MARKET, CONTRACT
	}

	private OrderType orderType;

	/**
	 * duration integer
	 */
	private int duration;

	/**
	 * is_buy_order boolean
	 */
	private boolean isBuyOrder;

	/**
	 * conversion of the order's issued string to an instant.
	 */
	private Instant issued;

	/**
	 * location_id integer
	 */
	private long locationId;

	private String locationName;

	/**
	 * min_volume integer
	 */
	private int minVolume;

	/**
	 * price number
	 */
	private double price;

	/**
	 * region name once set
	 */
	private String regionName;

	/**
	 * The solar system this order was placed
	 */
	private int systemId;

	/**
	 * type_id integer
	 */
	private int typeId;

	/**
	 * volume_remain integer
	 */
	private int volumeRemain;

	/**
	 * volume_total integer
	 */
	private int volumeTotal;

	private Instant expires;

	public String getFormatedExpires() {
		return FormatTools.formatDelay(expires);
	}

	@Getter(lazy = true)
	private final String formatedPrice = FormatTools.formatPrice(price);

	public static MarketOrder of(RegionLine line) {
		return builder()
		    .expires(line.getIssued().plus(line.getDuration(), ChronoUnit.DAYS))
		    .isBuyOrder(line.isBuyOrder())
		    .issued(line.getIssued())
		    .locationId(line.getLocationId())
		    .minVolume(line.getMinVolume())
		    .orderType(OrderType.MARKET)
		    .price(line.getPrice())
		    .regionId(line.getRegion().getRegionId())
		    .systemId(line.getSystemId())
		    .typeId(line.getTypeId())
		    .volumeRemain(line.getVolumeRemain())
		    .volumeTotal(line.getVolumeTotal())
		    .build();
	}

	public static MarketOrder of(ContractInfo contract) {
		if (!contract.isAsksOneTypeForIsks() && !contract.isOffersOneTypeForIsk()) {
			throw new RuntimeException("contract can't be used as a market order");
		}
		int volume = contract.getItems().stream().mapToInt(ContractItem::getQuantity).sum();
		long locationId = contract.getEndLocationId();
		double price = contract.isAsksOneTypeForIsks() ? contract.getReward() : contract.getPrice();
		return builder()
		    .expires(contract.getDateExpired())
		    .isBuyOrder(contract.isAsksOneTypeForIsks())
		    .issued(contract.getDateIssued())
		    .locationId(contract.getEndLocationId())
		    .minVolume(volume)
		    .orderType(OrderType.CONTRACT)
		    .price(price / volume)
		    .regionId(contract.getRegion().getId())
		    .systemId((int) (locationId < 100000000 ? locationId : 0))
		    .typeId(contract.getItems().get(0).getType().getId())
		    .volumeRemain(volume)
		    .volumeTotal(volume)
		    .build();
	}

	public MarketOrder resolveRegionName(Map<Integer, String> names) {
		setRegionName(names.get(regionId));
		if (getRegionName() == null) {
			setRegionName("region:" + regionId);
		}
		return this;
	}

	public MarketOrder resolveLocationName(Map<Integer, String> stationNames, Map<Long, String> structureNames) {
		if (locationId < 100000000) {
			setLocationName(stationNames.get((int) locationId));
		} else {
			setLocationName(structureNames.get(locationId));
		}
		if (getLocationName() == null) {
			setLocationName("location:" + getLocationId());
		}
		return this;
	}

}

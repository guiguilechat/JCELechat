package fr.guiguilechat.jcelechat.libs.spring.market.model;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import fr.guiguilechat.tools.FormatTools;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
public class MarketOrder implements Serializable {

	private ObservedRegion region;

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

	public int getRegionId() {
		return region.getRegionId();
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
				.region(line.getRegion())
				.systemId(line.getSystemId())
				.typeId(line.getTypeId())
				.volumeRemain(line.getVolumeRemain())
				.volumeTotal(line.getVolumeTotal())
				.build();
	}

	public static MarketOrder of(RegionContract contract) {
		if (!contract.isAsksOneTypeForIsks() && !contract.isOffersOneTypeForIsk()) {
			throw new RuntimeException("contract can't be used as a market order");
		}
		int volume = contract.getItems().stream().mapToInt(RegionContractItem::getQuantity).sum();
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
				.region(contract.getRegion())
				.systemId((int) (locationId < 100000000 ? locationId : 0))
				.typeId(contract.getItems().get(0).getTypeId())
				.volumeRemain(volume)
				.volumeTotal(volume)
				.build();
	}

	public MarketOrder resolveRegionName(Map<Integer, String> names) {
		if (region != null) {
			setRegionName(names.get(region.getRegionId()));
			if (getRegionName() == null) {
				setRegionName("region:" + region.getRegionId());
			}
		}
		return this;
	}

}

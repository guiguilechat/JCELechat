package fr.guiguilechat.jcelechat.libs.spring.anon.trade.tools;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketLine;
import fr.guiguilechat.tools.FormatTools;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * class for representing both regional orders and contract orders.
 * It's serializable to be cached easily, so does not contain any external
 * reference.
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
public class MarketOrder implements Serializable {

	private int regionId;

	public enum OrderType {
		MARKET, CONTRACT
	}

	private OrderType orderType;

	/** when it is a contract, the id of the contract */
	@Builder.Default
	private int contractId = 0;

	/**
	 * additional details for non-type items (BPC, researched BPOs)
	 */
	private String details;

	/**
	 * duration integer
	 */
	private int duration;

	private Instant expires;

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
	 * unit price in isk
	 */
	private double price;

	@Getter(lazy = true)
	private final String formatedPrice = FormatTools.formatPrice(price);

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
	 * internal url to list it, set by the fetching service
	 */
	private String url;

	/**
	 * volume_remain integer
	 */
	private int volumeRemain;

	/**
	 * volume_total integer
	 */
	private int volumeTotal;

	@Getter(lazy = true)
	private final String formatedExpires = FormatTools.formatDelay(expires);

	public static MarketOrder of(MarketLine line) {
		return builder()
		    .expires(line.getIssued().plus(line.getDuration(), ChronoUnit.DAYS))
		    .isBuyOrder(line.isBuyOrder())
		    .issued(line.getIssued())
		    .locationId(line.getLocationId())
		    .minVolume(line.getMinVolume())
		    .orderType(OrderType.MARKET)
		    .price(line.getPrice())
		    .regionId(line.getFetchResource().getId())
		    .systemId(line.getSolarSystemId())
		    .typeId(line.getTypeId())
		    .volumeRemain(line.getVolumeRemain())
		    .volumeTotal(line.getVolumeTotal())
		    .build();
	}

	public static MarketOrder of(ContractInfo contract) {
		// can't make an order out of a contract unless exactly one item is either
		// offered or required.
		if (contract.getNbTypesAsked() > 1 ||
		    contract.getNbTypesIncluded() > 1
		    || contract.getNbTypesAsked() + contract.getNbTypesIncluded() != 1) {
			throw new RuntimeException(
			    "contract id=" + contract.getId() + " has several items, can't be used as a market order");
		}
		int volume = 0;
		int typeId = 0;
		double price = 0.0;
		String details = null;
		if (contract.isRequestsItem()) {
			price = contract.getReward();
			typeId = contract.getAskedTypeId();
			volume = contract.getAskedQuantity().intValue();
		} else {
			if (contract.getOfferedCopy()
			    || contract.getOfferedMe() != null && contract.getOfferedMe() > 0
			    || contract.getOfferedTe() != null && contract.getOfferedTe() > 0) {
				details = "" + contract.getOfferedMe() + "/" + contract.getOfferedTe()
				    + (contract.getOfferedCopy() ? "(" + contract.getOfferedRuns() + "r)" : "");
			}
			price = contract.directPrice();
			typeId = contract.getOfferedTypeId();
			if (contract.getOfferedCopy()) {
				volume = contract.getOfferedRuns().intValue();
			} else {
				volume = contract.getOfferedQuantity().intValue();
			}
		}
		long locationId = contract.getEndLocationId();
		return builder()
		    .contractId(contract.getId())
		    .details(details)
		    .expires(contract.getDateExpired())
		    .isBuyOrder(contract.isAsksOneTypeForIsk())
		    .issued(contract.getDateIssued())
		    .locationId(contract.getEndLocationId())
		    .minVolume(volume)
		    .orderType(OrderType.CONTRACT)
		    .price(price / volume)// actually unit price for an order
		    .regionId(contract.getRegion().getId())
		    .systemId((int) (locationId < 100000000 ? locationId : 0))
		    .typeId(typeId)
		    .volumeRemain(volume)
		    .volumeTotal(volume)
		    .build();
	}

	public MarketOrder resolveRegionName(Map<Integer, String> regionNames) {
		setRegionName(regionNames.get(regionId));
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

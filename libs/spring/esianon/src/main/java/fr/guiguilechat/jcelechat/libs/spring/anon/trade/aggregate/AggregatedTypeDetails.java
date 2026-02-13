package fr.guiguilechat.jcelechat.libs.spring.anon.trade.aggregate;

import fr.guiguilechat.tools.FormatTools;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Common representation of trades for regional markets and contracts, including
 * type id and name
 */
@RequiredArgsConstructor
@Getter
@Setter
public class AggregatedTypeDetails {

	/**
	 * base type id. for a BPC, it will be the BP id.
	 */
	private final int typeId;

	/**
	 * representation of the item traded.
	 */
	private final String typeName;

	/** total isk value of the transactions performed */
	private final Number valueSold;

	@Getter(lazy = true)
	private final String formattedValueSold = FormatTools.formatPrice(getValueSold().doubleValue());

	/** quantity when {@link #original}, runs for BPC */
	private final Number quantitySold;

	@Getter(lazy = true)
	private final double unitPrice = getValueSold().doubleValue() / getQuantitySold().longValue();

	@Getter(lazy = true)
	private final String formattedUnitPrice = FormatTools.formatPrice(getUnitPrice());

	/** set to true for BPC ; false for base type or BPO */
	private final boolean copy;

	private final int me;

	private final int te;

	/** represent the type with cp/me/te values if needed */
	public String name() {
		StringBuilder sb = new StringBuilder(typeName);
		if (me > 0 || te > 0 || copy) {
			sb.append(" (");
			if (te > 0 || me > 0) {
				sb.append(me).append("/").append(te);
			}
			if (copy) {
				sb.append(" CP");
			}
			sb.append(")");
		}
		return sb.toString();
	}

}
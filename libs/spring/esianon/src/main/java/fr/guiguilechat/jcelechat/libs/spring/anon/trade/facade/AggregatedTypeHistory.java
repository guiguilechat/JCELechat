package fr.guiguilechat.jcelechat.libs.spring.anon.trade.facade;

import fr.guiguilechat.tools.FormatTools;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Common representation of historical trades for regional markets and contracts
 */
@RequiredArgsConstructor

@Getter
@Setter
public class AggregatedTypeHistory {

	/**
	 * base type id. for a BPC, it will be the BP id.
	 */
	private final int typeId;

	/**
	 * representation of the item traded.
	 */
	private final String typeName;

	/** total isk value of the transactions performed */
	private final double valueSold;

	@Getter(lazy = true)
	private final String valueSoldString = FormatTools.formatPrice(getValueSold());

	/** quantity when {@link #original}, runs for BPC */
	private final long quantitySold;

	@Getter(lazy = true)
	private final double unitPrice = getValueSold() / getQuantitySold();

	@Getter(lazy = true)
	private final String unitPriceString = FormatTools.formatPrice(getUnitPrice());

	/** set to true for BPC ; false for base type or BPO */
	private final boolean copy;

	private final int me;

	private final int te;

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
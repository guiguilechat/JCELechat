package fr.guiguilechat.jcelechat.libs.spring.anon.trade;

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

	private final int days;

	private final double valueSold;

	@Getter(lazy = true)
	private final String valueSoldString = FormatTools.formatPrice(getValueSold());

	private final long quantitySold;

	@Getter(lazy = true)
	private final double unitPrice = getValueSold() / getQuantitySold();

	@Getter(lazy = true)
	private final String unitPriceString = FormatTools.formatPrice(getUnitPrice());

	private int me;

	private int te;

	/**
	 * set externally depending on what we want to do about it. Should be set by the
	 * way it's retrieved, depending on how to use it
	 */
	private String url;

}
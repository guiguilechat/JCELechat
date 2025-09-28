package fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace;

import java.math.BigDecimal;

public class Position {
	public BigDecimal x;
	public BigDecimal y;
	public BigDecimal z;

	@Override
	public String toString() {
		return "[" + x + ":" + y + ":" + z + "]";
	}
}
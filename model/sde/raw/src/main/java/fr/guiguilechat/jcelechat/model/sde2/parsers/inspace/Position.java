package fr.guiguilechat.jcelechat.model.sde2.parsers.inspace;

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
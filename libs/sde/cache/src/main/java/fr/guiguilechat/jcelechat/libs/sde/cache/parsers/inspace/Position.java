package fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Position {

	public BigDecimal x;
	public BigDecimal y;
	public BigDecimal z;

	@Override
	public String toString() {
		return "[" + x + ":" + y + ":" + z + "]";
	}

	public double distance(Position other) {
		return Math.sqrt(
				Math.pow(x.doubleValue() - other.x.doubleValue(), 2)
						+ Math.pow(y.doubleValue() - other.y.doubleValue(), 2)
						+ Math.pow(z.doubleValue() - other.z.doubleValue(), 2));
	}

	/**
	 * @param other
	 * @return a new position with sum of values.
	 */
	public Position add(Position other) {
		return new Position(
				x.add(other.x),
				y.add(other.y),
				z.add(other.z));
	}
}
package fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Position2D {

	public BigDecimal x;
	public BigDecimal y;

	@Override
	public String toString() {
		return "[" + x + ":" + y + "]";
	}

	public double distance(Position2D other) {
		return Math.sqrt(
				Math.pow(x.doubleValue() - other.x.doubleValue(), 2)
						+ Math.pow(y.doubleValue() - other.y.doubleValue(), 2));
	}

	/**
	 * @param other
	 * @return a new position with sum of values.
	 */
	public Position2D add(Position2D other) {
		return new Position2D(
				x.add(other.x),
				y.add(other.y));
	}
}
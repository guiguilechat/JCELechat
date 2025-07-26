package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

public interface DoubleHolder extends NumberHolder<Double, DoubleHolder> {

	@Override
	default DoubleHolder add(DoubleHolder other) {
		return transform(other, (a, b) -> a + b);
	}

	@Override
	default DoubleHolder div(DoubleHolder other) {
		return transform(other, (a, b) -> a / b);
	}

	@Override
	default DoubleHolder mult(DoubleHolder other) {
		return transform(other, (a, b) -> a * b);
	}

	@Override
	default DoubleHolder sub(DoubleHolder other) {
		return transform(other, (a, b) -> a - b);
	}

	@Override
	default DoubleHolder toDouble() {
		return this;
	}

}

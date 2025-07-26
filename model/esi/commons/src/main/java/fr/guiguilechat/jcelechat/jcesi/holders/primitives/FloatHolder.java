package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

public interface FloatHolder extends NumberHolder<Float, FloatHolder> {

	@Override
	default FloatHolder add(FloatHolder other) {
		return transform(other, (a, b) -> a + b);
	}

	@Override
	default FloatHolder div(FloatHolder other) {
		return transform(other, (a, b) -> a / b);
	}

	@Override
	default FloatHolder mult(FloatHolder other) {
		return transform(other, (a, b) -> a * b);
	}

	@Override
	default FloatHolder sub(FloatHolder other) {
		return transform(other, (a, b) -> a - b);
	}

	@Override
	default FloatHolder toFloat() {
		return this;
	}

}

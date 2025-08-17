package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

public interface LongHolder extends NumberHolder<Long, LongHolder> {

	@Override
	default LongHolder add(LongHolder other) {
		return transform(other, (a, b) -> a + b);
	}

	@Override
	default LongHolder div(LongHolder other) {
		return transform(other, (a, b) -> a / b);
	}

	@Override
	default LongHolder mult(LongHolder other) {
		return transform(other, (a, b) -> a * b);
	}

	@Override
	default LongHolder sub(LongHolder other) {
		return transform(other, (a, b) -> a - b);
	}

}

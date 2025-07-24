package fr.guiguilechat.jcelechat.jcesi.holders.numbers;

public interface IntHolder extends NumberHolder<Integer, IntHolder> {

	@Override
	default IntHolder add(IntHolder other) {
		return transform(other, (a, b) -> a + b);
	}

	@Override
	default IntHolder div(IntHolder other) {
		return transform(other, (a, b) -> a / b);
	}

	@Override
	default IntHolder mult(IntHolder other) {
		return transform(other, (a, b) -> a * b);
	}

	@Override
	default IntHolder sub(IntHolder other) {
		return transform(other, (a, b) -> a - b);
	}

	@Override
	default IntHolder toInt() {
		return this;
	}

}

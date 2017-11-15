package fr.guiguilechat.eveonline.programs.panes;

import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;

public abstract class ScrollAdd<T extends Number> implements EventHandler<ScrollEvent> {

	protected T increment;
	protected TypedField<T> target;

	/**
	 *
	 * @param a
	 * @param b
	 * @return a+b
	 */
	protected abstract T add(T a, T b);

	/**
	 *
	 * @param a
	 * @param b
	 * @return a-b
	 */
	protected abstract T sub(T a, T b);

	public ScrollAdd(T increment, TypedField<T> target) {
		this.increment = increment;
		this.target = target;
	}

	@Override
	public void handle(ScrollEvent event) {
		boolean add = event.getDeltaY() > 0;
		target.setValue(add ? add(target.getValue(), increment) : sub(target.getValue(), increment));
		event.consume();
	}

	public static class IntScrollAdd extends ScrollAdd<Integer> {

		public IntScrollAdd(Integer increment, TypedField<Integer> target) {
			super(increment, target);
		}

		@Override
		protected Integer add(Integer a, Integer b) {
			return a + b;
		}

		@Override
		protected Integer sub(Integer a, Integer b) {
			return a - b;
		}
	}

	public static class DoubleScrollAdd extends ScrollAdd<Double> {

		public DoubleScrollAdd(Double increment, TypedField<Double> target) {
			super(increment, target);
		}

		@Override
		protected Double add(Double a, Double b) {
			return a + b;
		}

		@Override
		protected Double sub(Double a, Double b) {
			return a - b;
		}

	}

}

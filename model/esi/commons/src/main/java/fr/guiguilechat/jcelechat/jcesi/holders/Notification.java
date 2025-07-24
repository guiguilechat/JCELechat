package fr.guiguilechat.jcelechat.jcesi.holders;

import java.util.Set;
import java.util.function.Consumer;

/**
 * hierarchy of notification transmitted by a holder
 *
 * @param <T> internal type of the holder which emitted that notification
 */
public sealed interface Notification<T> {

	/**
	 * actions that were triggered as a result of passing a notification
	 *
	 * @return a set to add the Runnable into when receiving that notification.
	 */
	Set<Runnable> toExecute();

	/**
	 * a "original" holder received an updated value, as such the "parent" holder
	 * may have new
	 * value available. The original holder is transmitted along for debuging.
	 */
	public record DataAvailable<T>(Holder<?> original, Set<Runnable> toExecute, Holder<T> parent)
			implements Notification<T> {
		/**
		 * create the same notification but with different parent
		 *
		 * @param <U>
		 * @param child
		 * @return
		 */
		public <U> DataAvailable<U> ofParent(Holder<U> newParent) {
			return new DataAvailable<>(original, toExecute, newParent);
		}
	}

	/**
	 * An "original" holder received an updated value, but an "filtering" one
	 * filtered it out so we receive there is actually no notification. Mostly used
	 * for debuging.<br />
	 * Those MUST be passed down, can transmit as-is from the parent to the
	 * children.
	 */
	public record FilteredOut<T>(Holder<?> original, Set<Runnable> toExecute, Holder<?> filtering)
			implements Notification<T> {
	}

	/**
	 * An "original" holder received an exception "t" and transmits it down the
	 * line<br />
	 * Those MUST be passed down, can transmit as-is from the parent to the
	 * children.
	 *
	 * @param <T>
	 */
	public record FetchException<T>(Holder<?> original, Set<Runnable> toExecute, Throwable t)
			implements Notification<T> {
	}

	public interface Listener<T> extends Consumer<Notification<T>> {

	}
}
